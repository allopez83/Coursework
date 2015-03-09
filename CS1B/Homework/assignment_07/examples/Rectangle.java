package assignment_07.examples;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rectangle implements Serializable {
	private double length; // instance variable

	private double getLength() {
		return length;
	} // getter

	private void setLength(double newLength) {
		length = newLength;
	} // setter

	private double width; // instance variable

	private double getWidth() {
		return width;
	} // getter

	private void setWidth(double newWidth) {
		width = newWidth;
	} // setter

	private void initialize(double initLength, double initWidth) {
		this.setLength(initLength);
		this.setWidth(initWidth);
	}

	public Rectangle() // default constructor
	{
		this.initialize(0, 0);
	}

	public Rectangle(double l, double w) {
		this.initialize(l, w);
	}

	/**
	 * Returns the area of the Rectangle that called it.
	 */
	public double calculateArea() {
		return this.getLength() * this.getWidth();
	}

	public String toString() // overrides Object’s method
	{
		return "length=" + this.getLength() + ", and width=" + this.getWidth();
	}
}