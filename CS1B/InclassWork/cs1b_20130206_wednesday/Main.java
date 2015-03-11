package cs1b_20130206_wednesday;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is closer to what the teacher has for ArrayList and Integer object
 * @author HW
 * @version 2013.02.06
 */
public class Main {
	public static void main(String[] args) {
		Integer intObject = new Integer(5);
		System.out.println(intObject.intValue());
		
		ArrayList<Integer> myList;
		myList = new ArrayList<Integer>();
		myList.add(new Integer(10));
		myList.add(new Integer(5));
		
		System.out.println(myList);
		System.out.println("The second element:\n" +
				myList.get(1));
		
		ArrayList<Point> yourList;
		yourList = new ArrayList<Point>();
		
		yourList.add(new Point(5,6));
		yourList.add(new Point(4,5));
		Collections.sort(myList);
		System.out.println(myList);
	}

}
