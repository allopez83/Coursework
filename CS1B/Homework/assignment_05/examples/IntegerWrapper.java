package assignment_05.examples;

import java.util.ArrayList;

/**
 * This is what you typed for testing out ArrayList and Integer object
 * @author HW
 * @version 2013.02.06
 */
public class IntegerWrapper {
	public static void main(String[] args) {
		Integer intObject = new Integer(5);
		System.out.println(intObject);
		
		ArrayList<Integer> datList = new ArrayList<Integer>();
		
		Integer a,b,c,d;
		a = new Integer(21);
		b = new Integer(22);
		c = new Integer(23);
		d = new Integer(24);
		datList.add(a);
		datList.add(b);
		datList.add(c);
		datList.add(d);
		
		System.out.println(datList);
		System.out.println("Is string empty: " + datList.isEmpty());
		System.out.println("Size: " + datList.size());
	}
}

/**
Write a program that store some Integers in an ArrayList
*/