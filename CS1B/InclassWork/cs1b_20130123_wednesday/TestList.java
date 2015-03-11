package cs1b_20130123_wednesday;

public class TestList {
	public static void main(String[] args){
		System.out.println("Start TestList:\n");
		
		List myList;
		myList = new List(10);
		System.out.println(myList);
		myList.fill(99);
		System.out.println(myList);
		
		myList.PrintNumbered(myList);
		
		System.out.println("\nEnd TestList");
	}

}
