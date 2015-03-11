package cs1b_20130227_wednesday;

/**
 * In class inheritance practice stuff
 */
class Main {
	public static void main(String[] args) {
		// No toString defined in Rectangle
		Rectangle a1 = new Rectangle(5, 7);
		System.out.println(a1);
		
		// Equals demonstration
		Rectangle b1 = new Rectangle();
		Rectangle b2 = new Rectangle();
		if (b1.equals(b2))
			System.out.println("b1 equal b2");
		else System.out.println("b1 not equal b2");
		
		Rectangle b3 = new Rectangle();
		Rectangle b4 = b3;
		if (b3.equals(b4))
			System.out.println("b3 equal b4");
		else System.out.println("b3 not equal b4");

		Rectangle b5 = new Rectangle();
		Rectangle b6 = b5;
		if (b5 == b6)
			System.out.println("b5 equal b6");
		else System.out.println("b5 not equal b6");
	}
}
