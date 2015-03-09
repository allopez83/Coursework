package cs1b_20130311_monday;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List someList = new List();
		someList.addAtEnd(new Integer(1));
		someList.addAtEnd(new Integer(5));
		someList.addAtEnd(new Integer(10));
		System.out.println(someList + "\n");
		someList.print();
	}

}
