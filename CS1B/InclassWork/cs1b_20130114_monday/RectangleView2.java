package cs1b_20130114_monday;

import java.util.*;

public class RectangleView2 {
	/**
	 * Takes integer input from user for various purposes
	 * @return integer that user types in
	 */
	public double inputDouble(){
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		return kb.nextDouble();
	}
	/**
	 * Asks user for a rectangle object, then changes the dimensions to user
	 * specifications
	 * @param rectangle that user wants to change
	 */
	public void read(Rectangle rect){
		double length, width;
		System.out.println("!Start Rectangle Read!");
		System.out.println("Input new length:");
		length = inputDouble();
		System.out.println("Input new width:");
		width = inputDouble();
		rect.initialize(length, width);
	}
	public void write(Rectangle rect){
		System.out.println("!Start Rectangle Write!");
		System.out.println(rect);
	}
}



/*
add a third class Rectangle view that has method read() and write()
		RectangleView view;
		view = new RectangleView;
		view.read(floor);
		view.write(floor);
*/