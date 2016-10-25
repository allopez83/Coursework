package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

/**
 * FWJS expressions.
 */
public interface Expression {
    /**
     * Evaluate the expression in the context of the specified environment.
     */
    public Value evaluate(Environment env);
}

// NOTE: Using package access so that all implementations of Expression
// can be included in the same file.

/**
 * FWJS constants.
 */
class ValueExpr implements Expression {
    private Value val;
    public ValueExpr(Value v) {
        this.val = v;
    }
    public Value evaluate(Environment env) {
        return this.val;
    }
}

/**
 * Expressions that are a FWJS variable.
 */
class VarExpr implements Expression {
    private String varName;
    public VarExpr(String varName) {
        this.varName = varName;
    }
    public Value evaluate(Environment env) {
        return env.resolveVar(varName);
    }
}

/**
 * A print expression.
 */
class PrintExpr implements Expression {
    private Expression exp;
    public PrintExpr(Expression exp) {
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
        Value v = exp.evaluate(env);
        System.out.println(v.toString());
        return v;
    }
}
/**
 * Binary operators (+, -, *, etc).
 * Currently only numbers are supported.
 */
class BinOpExpr implements Expression {
    private Op op;
    private Expression e1;
    private Expression e2;
    public BinOpExpr(Op op, Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @SuppressWarnings("incomplete-switch")
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.BinOpExpr()");

        Value v = new NullVal();
        // Ensure expressions are ints
        if (e1.evaluate(env) instanceof IntVal && e2.evaluate(env) instanceof IntVal) {
            int i1 = ((IntVal) e1.evaluate(env)).toInt();
            int i2 = ((IntVal) e2.evaluate(env)).toInt();

            switch (op) {
                // Arithmetic
                case ADD:      v = new IntVal(i1 + i2); break;
                case SUBTRACT: v = new IntVal(i1 - i2); break;
                case MULTIPLY: v = new IntVal(i1 * i2); break;
                case DIVIDE:   v = new IntVal(i1 / i2); break;
                case MOD:      v = new IntVal(i1 % i2); break;
                // Comparison
                case GT: v = new BoolVal(i1 >  i2); break;
                case GE: v = new BoolVal(i1 >= i2); break;
                case LT: v = new BoolVal(i1 <  i2); break;
                case LE: v = new BoolVal(i1 <= i2); break;
                case EQ: v = new BoolVal(i1 == i2); break;
            }
        }
        return v;
    }
}

/**
 * If-then-else expressions.
 * Unlike JS, if expressions return a value.
 */
class IfExpr implements Expression {
    private Expression cond;
    private Expression thn;
    private Expression els;
    public IfExpr(Expression cond, Expression thn, Expression els) {
        this.cond = cond;
        this.thn = thn;
        this.els = els;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.IfExpr()");

        Value v = null;
        if (((BoolVal) cond.evaluate(env)).toBoolean())
            v = thn.evaluate(env);
        else
            v = els.evaluate(env);

        return v;
    }
}

/**
 * While statements (treated as expressions in FWJS, unlike JS).
 */
class WhileExpr implements Expression {
    private Expression cond;
    private Expression body;
    public WhileExpr(Expression cond, Expression body) {
        this.cond = cond;
        this.body = body;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.WhileExpr()");

        while (((BoolVal) cond.evaluate(env)).toBoolean())
            // Changes from body should persist in env
            body.evaluate(env);
        return null;
    }
}

/**
 * Sequence expressions (i.e. 2 back-to-back expressions).
 */
class SeqExpr implements Expression {
    private Expression e1;
    private Expression e2;
    public SeqExpr(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.SeqExpr()");

        // env changes should persist
        e1.evaluate(env);
        return e2.evaluate(env);
    }
}

/**
 * Declaring a variable in the local scope.
 */
class VarDeclExpr implements Expression {
    private String varName;
    private Expression exp;
    public VarDeclExpr(String varName, Expression exp) {
        this.varName = varName;
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.VarDeclExpr()");
        if(exp == null) {
            return new NullVal();
        }
        env.createVar(varName, exp.evaluate(env));
        return null;
    }
}

/**
 * Updating an existing variable.
 * If the variable is not set already, it is added
 * to the global scope.
 */
class AssignExpr implements Expression {
    private String varName;
    private Expression e;
    public AssignExpr(String varName, Expression e) {
        this.varName = varName;
        this.e = e;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.AssignExpr()");

        env.updateVar(varName, e.evaluate(env));
        return e.evaluate(env); // should not be null
    }
}

/**
 * A function declaration, which evaluates to a closure.
 */
class FunctionDeclExpr implements Expression {
    private List<String> params;
    private Expression body;
    public FunctionDeclExpr(List<String> params, Expression body) {
        this.params = params;
        this.body = body;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.FunctionDeclExpr()");

        // wrong wrong everything is wrong
        return new ClosureVal(params, body, env);

        // this is fine
        // im okay with the events that are unfolding currently
        // thats okay, things are going to be ok

        // return null;
    }
}

/**
 * Function application.
 */
class FunctionAppExpr implements Expression {
    private Expression f;
    private List<Expression> args;
    public FunctionAppExpr(Expression f, List<Expression> args) {
        this.f = f;
        this.args = args;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        System.out.println(" > Expression.FunctionAppExpr()");

        // The horror, the horror!
        List argVals = new ArrayList<Value>();
        for (Expression e : args) {
            argVals.add(e.evaluate(env));
        }

        // this is not fine
        // oh my god everythings on fire
        // what is my problem
        // oh my god
        // what was i even thinking
        // there was no reason to let it last this long and get this bad
        return ((ClosureVal) f.evaluate(env)).apply(argVals);

        // return null;
    }
}

