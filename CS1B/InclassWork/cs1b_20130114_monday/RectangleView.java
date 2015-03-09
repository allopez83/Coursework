package cs1b_20130114_monday;

import java.util.*;

public class RectangleView {
	/**
	 * Asks user for a rectangle object, then changes the dimensions to user 
	 * specifications
	 * @param rectangle that user wants to change
	 */
	public void read(Rectangle rect){
		double length, width;
		Scanner kb = new Scanner(System.in);
		System.out.println("!Start Rectangle Read!");
		System.out.println("Input new length:");
		length = kb.nextDouble();
		System.out.println("Input new width:");
		width = kb.nextDouble();
		rect.initialize(length, width);
		kb.close();
	}
	public void read2(Rectangle rect){
		double length, width;
		String userInput;
		Scanner kb = new Scanner(System.in);
		System.out.println("!Start Rectangle Read!");
		
		System.out.println("Input new length:");
		userInput = kb.nextLine();
		try{
			length=Double.parseDouble(userInput);
		}catch(Exception e){
			System.out.println("Error: no value found, set to 0 as default");
			length = 0;
		}
		
		System.out.println("Input new width:");
		userInput = kb.nextLine();
		try{
			width=Double.parseDouble(userInput);
		}catch(Exception e){
			System.out.println("Error: no value found, set to 0 as default");
			width = 0;
		}
		
		rect.initialize(length, width);
		kb.close();
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