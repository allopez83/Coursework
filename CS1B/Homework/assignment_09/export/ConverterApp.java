package assignment_09.export;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.*;
import javax.swing.*;

/**
 * Contains a temperature converting program a program that converts accepts
 * user input preferably a number and converts it from one temperature unit to
 * the other. Uses a GUI.
 * 
 * Note: The placement of window components is optimized for Windows 7. This
 * program has not been tested on Linux, MacOS, etc.
 * 
 * @author Hansen Wu
 * 
 */
public class ConverterApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final boolean TESTING_CONVERTAPP = false,
								 TESTING_INITIALIZE = false,
								 TESTING_ACTIONLISTENER = false,
								 TESTING_RETRIEVE_DOUBLE = false,
								 TESTING_CONVERSION = false;
	
	private static final int WINDOW_LENGTH = 250,
							 WINDOWS7_TITLEBAR = 30,
							 WINDOWS7_BORDER = 8;
	
	//Ordered as they appear on the GUI, top to bottom
	private JPanel mainPanel;
	private JLabel directions;
	private JTextField temperatureField;
	private JLabel resultTitle;
	private JLabel resultConversion;
	private CheckboxGroup convertDirection;
	private Checkbox toFahrenheit;
	private Checkbox toCelcius;
	private JButton doConversion;
	private JButton exitApp;

	/**
	 * Creates the window for GUI components to be placed on.
	 */
	public ConverterApp() {
		if (TESTING_CONVERTAPP) System.out.println("Converter App Start");
		
		this.setContentPane(content());
		
		//A window's size includes the borders of the window, too
		this.setSize(WINDOW_LENGTH + WINDOWS7_BORDER*2, WINDOW_LENGTH + WINDOWS7_TITLEBAR + WINDOWS7_BORDER);
		this.setTitle("Temperature Converter");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setResizable(false);
		
		if (TESTING_CONVERTAPP) System.out.println("Converter App End");
	}
	
	/**
	 * Adds and lays out all necessary GUI components on a JPanel
	 * @return JPanel that has all components added onto it.
	 */
	private JPanel content() {
		if (TESTING_INITIALIZE) System.out.println(" > content() start");
		//Create a panel to put everything on
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		//Initial instructions, first line
		directions = new JLabel("Insert temperature quantity below.");
		directions.setSize(WINDOW_LENGTH, 20);
		directions.setHorizontalAlignment(0);
		directions.setLocation(0, 10);
		mainPanel.add(directions);
		
		//Initial instructions, second line
		directions = new JLabel("Please insert numbers only!");
		directions.setSize(WINDOW_LENGTH, 20);
		directions.setHorizontalAlignment(0);
		directions.setLocation(0, 30);
		mainPanel.add(directions);
		
		//Area for user to insert temperature
		temperatureField = new JTextField();
		temperatureField.setHorizontalAlignment(0);
		temperatureField.setSize(WINDOW_LENGTH/2, 25);
		temperatureField.setLocation(WINDOW_LENGTH/4, 55);
		mainPanel.add(temperatureField);
		
		//Result, first line
		resultTitle = new JLabel("Result:");
		resultTitle.setSize(WINDOW_LENGTH, 20);
		resultTitle.setHorizontalAlignment(0);
		resultTitle.setLocation(0, 80);
		mainPanel.add(resultTitle);
		
		//Result, second line, this is where data is returned to user
		resultConversion = new JLabel("(empty)");
		resultConversion.setHorizontalAlignment(0);
		resultConversion.setSize(WINDOW_LENGTH, 20);
		resultConversion.setLocation(0, 100);
		mainPanel.add(resultConversion);
		
		//Checkboxes for conversion direction
		convertDirection = new CheckboxGroup();
		toFahrenheit = new Checkbox("Celcius to Fahrenheit", convertDirection, true);
		toFahrenheit.setSize(140, 20);
		toFahrenheit.setLocation(WINDOW_LENGTH/2-68, 125);
		mainPanel.add(toFahrenheit);
		toCelcius = new Checkbox("Fahrenheit to Celcius", convertDirection, false);
		toCelcius.setSize(140, 20);
		toCelcius.setLocation(WINDOW_LENGTH/2-68, 145);
		mainPanel.add(toCelcius);
		
		//Button to initiate the conversion
		doConversion = new JButton("Convert");
		doConversion.setSize(100, 30);
		doConversion.setLocation((WINDOW_LENGTH-100)/2, 175);
		doConversion.addActionListener(this);
		mainPanel.add(doConversion);
		
		//Button to exit application
		exitApp = new JButton("Exit");
		exitApp.setSize(100, 30);
		exitApp.setLocation((WINDOW_LENGTH-100)/2, 215);
		exitApp.addActionListener(this);
		mainPanel.add(exitApp);
		
		if (TESTING_INITIALIZE) System.out.println(" > content() end");
		return mainPanel;
	}

	/**
	 * Defines what will happen when an action is performed, the two possible
	 * actions being the "Convert" or "Exit" buttons being pressed.
	 * 
	 * Convert button: checks if the user typed anything into the "temperatureField",
	 * and decides what to change "converted" to.
	 * Exit button: closes the application.
	 */
	public void actionPerformed(ActionEvent action) {
		if (TESTING_ACTIONLISTENER) System.out.println("Registered action performed");
		
		if (action.getSource() == doConversion){
			String rawUserInput = temperatureField.getText(), newLabel;
			if (TESTING_ACTIONLISTENER) System.out.println(" > User input: '" + rawUserInput + "'");
			
			mainPanel.remove(resultConversion);
			mainPanel.repaint();
			
			try {
				if (rawUserInput.equals(""))
					newLabel = "(empty)";
				else
					newLabel = temperatureConversion(retrieveTemperature(rawUserInput));
			} catch (Exception e) {
				if (TESTING_ACTIONLISTENER) System.out.println("!> Thrown exception:\n   " + e.getMessage());
				newLabel = "Oops, the data entered is unconvertible!";
			}
			
			resultConversion = new JLabel(newLabel);
			resultConversion.setHorizontalAlignment(0);
			resultConversion.setSize(WINDOW_LENGTH, 20);
			resultConversion.setLocation(0, 100);
			mainPanel.add(resultConversion);
			
			mainPanel.validate();
			if (TESTING_ACTIONLISTENER) System.out.println("Registered action ended");
		}
		else	//If 'convert' was not activated, then it must be 'exit'
			this.dispose();
	}

	/**
	 * Takes a string input and checks if it is a number. Decimals are allowed, but not required.
	 * @param input String that will be checked if it is a number.
	 * @return input converted into a double
	 * @throws IllegalArgumentException if input contains anything that makes it not a number
	 */
	public double retrieveTemperature(String input) {
		if (TESTING_RETRIEVE_DOUBLE) System.out.println(" > Inside retrieveTemperature()");
		String error = "Program accepts numbers only!";
		
		if (input.matches("[ ]*[.]*[ ]*"))
			throw new IllegalArgumentException(error);
		else if (false == input.matches("[0-9_.]+"))
			throw new IllegalArgumentException(error);
		//If the program got here, the number is a legitimate temperature and is convertible
		
		if (TESTING_RETRIEVE_DOUBLE) System.out.println(" > Completed retrieveTemperature()");
		return Double.parseDouble(input);
	}
	
	/**
	 * Converts a double into a desired temperature unit. The method will use ConverterApp's checkboxes to determine which direction the conversion is in.
	 * @param input
	 * @return
	 */
	public String temperatureConversion(double input) {
		if (TESTING_CONVERSION) System.out.println(" > Inside temperatureConversion()");
		if (TESTING_CONVERSION) System.out.println(" > Input value: " + input);
		
		if (toFahrenheit.getState())
			input = ((9.0/5.0)*input)+32.0;
		else
			input = ((input-32.0)*(5.0/9.0));
		
		if (TESTING_CONVERSION) System.out.println(" > Returning value: " + input);
		if (TESTING_CONVERSION) System.out.println(" > Completed temperatureConversion()");
		return input+"";
	}
	
}