package cs1b_20130128_monday;

public class Rectangle implements Comparable<Rectangle>{
	private int length;
	private int width;
	void setLength(int value){
		length = value;
	}
	int getLength(){
		return length;
	}
	void setWidth(int value){
		width = value;
	}
	int getWidth(){
		return width;
	}
	Rectangle(int inputLength, int inputWidth){
		length = inputLength;
		width = inputWidth;
	}
	private int calculateArea(){
		return (length*width);
	}
	/**
	 * Returns >0 if this object's area is > obj's area. Returns <0 if this
	 * object's area is < obj's area. Returns 0 if this object's area == obj's
	 * area.
	 */
	public int compareTo(Rectangle obj){
		return (int)(this.calculateArea()) - ((obj).calculateArea());
	}
}