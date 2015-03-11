package cs1b_20130114_monday;

/**
 * Tests class Rectangle and RectangleView by instantiating an object of the
 * class and calling all of the public methods on it.
 */
public class TestRectangle {
	public static void main(String args[]) {
		Rectangle floor;
		floor = new Rectangle(); // calls the default constructor
		// System.out.println("floor: " + floor + ", and area="
		// + floor.calculateArea());
		Rectangle ceiling;
		ceiling = new Rectangle(2, 4); // calls the constructor with parameters
		// System.out.println("ceiling: " + ceiling + ", and area="
		// + ceiling.calculateArea());

		RectangleView2 view;
		view = new RectangleView2();
		
		System.out.println("Floor");
		view.read(floor);
		view.write(floor);

		System.out.println("Ceiling");
		view.read(ceiling);
		view.write(ceiling);

		floor.thisTest(ceiling);
	}
}