package assignment_05.examples;

import java.util.*;
import java.awt.Point;

public class LinkedListPoints {
	public static void main(String[] args) {
		System.out.println("Hi");
		
		//You can change ArrayList to LinkedList
		ArrayList<Point> someLists = new ArrayList<Point>();
		
		Point a,b,c;
		a = new Point(1,1);
		b = new Point(1,2);
		c = new Point(1,3);
		someLists.add(a);
		someLists.add(b);
		someLists.add(c);
		
		System.out.println(someLists);
		a.setLocation(2,1);
		System.out.println(someLists);
		
		System.out.println(someLists.contains(b));	//should be true
		System.out.println(someLists.contains(new Point(1,3)));	//should be true
		System.out.println(someLists.contains(new Point(5,5)));	//should be false
	}
}

/*
Write a main() that creates a new LinkedList (java.util)
of Point (java.awt) objects. Experiment with methods in
interface collection on your LinkedList object.
*/