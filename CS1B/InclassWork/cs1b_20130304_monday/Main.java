package cs1b_20130304_monday;

import javax.swing.*;

/**
 * In class GUI stuff
 * 
 * @author HW
 */
@SuppressWarnings("unused")
public class Main {
	public static void main(String[] args) {
		System.out.println("Start");
		
		/* Displays window that asks for String input, then returns the string
		String name;
		name = JOptionPane.showInputDialog("What is your name?");	// Returns a String
		JOptionPane.showMessageDialog(null, "Hello, " + name + ", This is a modal dialog.");
		*/
		
		/* Creates a window with the title "Hello World"
		JFrame myWindow;
		myWindow = new JFrame();
		myWindow.setSize(300,200);
		myWindow.setTitle("Hello World");
		myWindow.setVisible(true);
		*/
		
		/* Uses MyFrame class to create a window
		MyFrame myWindow;
		myWindow = new MyFrame();
		myWindow.setSize(300,200);
		myWindow.setTitle("Hello World");
		myWindow.setVisible(true);
		*/
		
		/* Uses MyFrame class to create window
		MyFrame myWindow;
		myWindow = new MyFrame();
		*/
		
		GUIApp myWindow;
		myWindow = new GUIApp();
		
		System.out.println("End");
	}
}
