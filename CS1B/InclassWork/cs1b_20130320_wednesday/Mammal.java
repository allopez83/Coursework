package cs1b_20130320_wednesday;

public class Mammal {
	private int age;
	
	public Mammal(int a) {
		age = a;
	}
	
	public void doMammal() {
		System.out.println("Mammal has age: "+age);
	}
	
	public void print() {
		System.out.println("Inside mammal's print");
	}

}
