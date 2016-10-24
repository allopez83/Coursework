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
    private static DEBUG = true;
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
        if (DEBUG) System.out.println(" > BinOpExpr.evaluate()");

        Value v = null;
        switch (op) {
            // Arithmetic
            case ADD:      v = e1 + e2; break;
            case SUBTRACT: v = e1 - e2; break;
            case MULTIPLY: v = e1 * e2; break;
            case DIVIDE:   v = e1 / e2; break;
            case MOD:      v = e1 % e2; break;
            // Comparison
            case GT: v = e1 >  e2; break;
            case GE: v = e1 >= e2; break;
            case LT: v = e1 <  e2; break;
            case LE: v = e1 <= e2; break;
            case EQ: v = e1 == e2; break;
        }
        return v;
    }
}

/**
 * If-then-else expressions.
 * Unlike JS, if expressions return a value.
 */
class IfExpr implements Expression {
    private static DEBUG = true;
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
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        Value v = null;
        if (cond.evaluate(this.env))
            v = thn.evaluate(this.env)
        else
            v = els.evaluate(this.env)

        return v;
    }
}

/**
 * While statements (treated as expressions in FWJS, unlike JS).
 */
class WhileExpr implements Expression {
    private static DEBUG = true;
    private Expression cond;
    private Expression body;
    public WhileExpr(Expression cond, Expression body) {
        this.cond = cond;
        this.body = body;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        // Changes to env should persist
        while (cond.evaluate(env))
            body.evaluate(env);
        return null;
    }
}

/**
 * Sequence expressions (i.e. 2 back-to-back expressions).
 */
class SeqExpr implements Expression {
    private static DEBUG = true;
    private Expression e1;
    private Expression e2;
    public SeqExpr(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        // env changes should persist
        e1.evaluate(env);
        return e2.evaluate(env);
    }
}

/**
 * Declaring a variable in the local scope.
 */
class VarDeclExpr implements Expression {
    private static DEBUG = true;
    private String varName;
    private Expression exp;
    public VarDeclExpr(String varName, Expression exp) {
        this.varName = varName;
        this.exp = exp;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        env.createVar(varName, exp);
        return null;
    }
}

/**
 * Updating an existing variable.
 * If the variable is not set already, it is added
 * to the global scope.
 */
class AssignExpr implements Expression {
    private static DEBUG = true;
    private String varName;
    private Expression e;
    public AssignExpr(String varName, Expression e) {
        this.varName = varName;
        this.e = e;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        env.updateVar(varName, e)
        return null;
    }
}

/**
 * A function declaration, which evaluates to a closure.
 */
class FunctionDeclExpr implements Expression {
    private static DEBUG = true;
    private List<String> params;
    private Expression body;
    public FunctionDeclExpr(List<String> params, Expression body) {
        this.params = params;
        this.body = body;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        // Set local function env with current env as outer
        Environment localEnv = new Environment(env);

        // Create null parameters
        for (String p : params)
            localEnv.createVar(p, null);
        // Return might be different?
        return localEnv;
    }
}

/**
 * Function application.
 */
class FunctionAppExpr implements Expression {
    private static DEBUG = true;
    private Expression f;
    private List<Expression> args;
    public FunctionAppExpr(Expression f, List<Expression> args) {
        this.f = f;
        this.args = args;
    }
    public Value evaluate(Environment env) {
        // TODO test this
        if (DEBUG) System.out.println(" > IfExpr.evaluate()");

        for (String a : args)
            env.updateVar(a, )
        return null;
    }
}

