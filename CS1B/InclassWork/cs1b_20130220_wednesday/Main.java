package cs1b_20130220_wednesday;

import java.io.*;

public class Main {
	public static void main(String args[]) {
		Rectangle r = new Rectangle(3, 4);
		Rectangle s = new Rectangle(5, 6);
		// 1) Write the 2 Rectangle objects to the file
		FileOutputStream outFile;
		ObjectOutputStream outObject;
		try {
			outFile = new FileOutputStream("data");
			outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(r);
			outObject.writeObject(s);
			outFile.close();
			outObject.close();
		} catch (IOException ioe) {
			System.out.println("Error writing objects to the file: "
					+ ioe.getMessage());
		}
		// 2) Set the Rectangle references to null
		r = s = null;
		// 3) Read the 2 Rectangle objects back in from the file
		FileInputStream inFile;
		ObjectInputStream inObject;
		try {
			inFile = new FileInputStream("data");
			inObject = new ObjectInputStream(inFile);
			r = (Rectangle) inObject.readObject();
			s = (Rectangle) inObject.readObject();
			inFile.close();
			inObject.close();
		} catch (IOException ioe) {
			System.out.println("Error reading from the file: "
					+ ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error in casting to Rectangle: " + cnfe);
		}
		// 4) Print the 2 Rectangle objects to see if they were read in
		// correctly
		System.out.println(r);
		System.out.println(s);
	}
}