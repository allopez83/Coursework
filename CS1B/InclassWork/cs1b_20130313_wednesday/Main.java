package cs1b_20130313_wednesday;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List someList = new List();
		someList.addAtEnd(new Integer(1));
		someList.addAtEnd(new Integer(5));
		someList.addAtEnd(new Integer(10));
		System.out.println(someList + "\n");;
		someList.addAtStart(new Integer(15));
		System.out.println(someList + "\n");
		someList.recursiveAddAtEnd(new Integer(20));
		System.out.println(someList + "\n");
		someList.addAtStart(new Integer(25));
		System.out.println(someList + "\n");
		//someList.print();
	}

}
