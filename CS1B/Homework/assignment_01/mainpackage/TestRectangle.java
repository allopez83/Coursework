package assignment_01.mainpackage;


/**
 * Tests class Rectangle by instantiating an object of the class and calling all
 * of the public methods on it.
 */
public class TestRectangle {
	public static void main(String args[]) {
		Rectangle floor;
		floor = new Rectangle(); // calls the default constructor
		System.out.println("floor: " + floor + ", and area="
				+ floor.calculateArea());
		Rectangle ceiling;
		ceiling = new Rectangle(2, 4); // calls the constructor with parameters
		System.out.println("ceiling: " + ceiling + ", and area="
				+ ceiling.calculateArea());
	}
}