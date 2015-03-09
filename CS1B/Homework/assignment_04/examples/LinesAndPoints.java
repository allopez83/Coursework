package assignment_04.examples;

import java.awt.Point;

public class LinesAndPoints {
	public static void main(String args[]) {
		
		Point start = new Point(10, 20);
		Point end = new Point(20, 30);
		Line line1 = new Line(start, end);
		Line line2 = (Line) (line1.clone());
		line1.increment();
		System.out.println("Printing out line1 and line2, should be different");
		System.out.println("Line 1:\n" + line1);
		System.out.println("Line 2:\n" + line2);
		
		/*
		Line line1;
		line1 = new Line(10,20,30,40);
		Line line2;
		line2 = new Line();
		System.out.println(line1);
		System.out.println(line2);
		*/

	}
}