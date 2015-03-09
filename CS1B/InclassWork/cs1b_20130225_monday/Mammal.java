package cs1b_20130225_monday;

/**
 * Subclass example in class
 * @author HW
 *
 */
class Mammal {
	private int age;

	private int getAge() {
		return age;
	}

	void setAge(int newAge) {
		age = newAge;
	}

	public void printMammal() {
		System.out.println("doing the Mammal thang at age = " + getAge());
	}
}