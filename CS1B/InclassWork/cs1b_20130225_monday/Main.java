package cs1b_20130225_monday;

/**
 * Subclass example in class
 * 
 * @author HW
 */
class Main {
	public static void main(String args[]) {
		Dog d;
		d = new Dog();
		d.setName("Spotty");
		d.setAge(5);
		d.printDog();
		d.printMammal();
		// d is a Dog object, so it gets access to all Mammal methods.
		Mammal m1;
		m1 = new Mammal();
		m1.printMammal();
		
		// m.printDog();
		/*
		 * Compiler ERROR- "printDog" can't be called with just any Mammal
		 * object.
		 */
		
		// ((Dog)m).printDog();
		/*
		 * Runtime ERROR –"m.name" doesn’t exist. Because it is possible the "m"
		 * might be pointing to a Dog (SOME Mammals ARE Dogs) this compiles but
		 * it doesn’t run. We’ll see more on this later.
		 */
		
		Mammal m2;
		// If customer has a dog
		m2 = new Dog();
		// Reference to a superclass CAN point to object of subclass
		((Dog)m2).setName("Fido");
		m2.setAge(10);
	}
}


/*	inheritence quiz
1. Alpha is superclass, and beta is subclass

X2. super-, sub-	//other way around

3. true

4. true

5. Assuming dimensions are given;

r.printBillBoard();
r = new BillBoard();
r.printBillBoard();

5a.	//adding toString()

// Rectangle
Public String toString(){
return ("Length = " + length + ", Width = " + width);
}

// BillBoard
Public String toString(){
return (message + ", " + super);	//super.toString()
}
 */







