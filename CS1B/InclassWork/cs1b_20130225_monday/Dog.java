package cs1b_20130225_monday;

/**
 * Subclass example in class
 * 
 * @author HW
 * 
 */
class Dog extends Mammal {
	private String name;

	private String getName() {
		return name;
	}

	void setName(String newName) {
		name = newName;
	}

	public void printDog() {
		System.out.println(getName() + " is a dog");
	}
}