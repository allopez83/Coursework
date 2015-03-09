package cs1b_20130318_monday;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A JFrame which gets a temperature from the user and allows the user to
 * convert to/from Celcius and Fahranheit. Implements the ActionListeners as
 * ananymous inner classes.
 */
@SuppressWarnings("serial")
public class View extends JFrame {
	private JTextField userEnteredTemperature;
	private JButton toCelcius;
	private JButton toFahrenheit;
	
	/**
	 * Constructor of View
	 */
	public View(){
		initialize();
	}
	/**
	 * Initializer of View (initialize all the setting/layout of the frame)
	 */
	public void initialize(){
		setLayout(new FlowLayout());
		userEnteredTemperature = new JTextField("Please enter the temperature here",20);
		
		toCelcius = new JButton("Convert temperature to celcius from fahrenheit");
		toCelcius.addActionListener( new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Model m = new Model();
				String userInput = userEnteredTemperature.getText();
				userEnteredTemperature.setText(Double.toString(m.toCelcius(Double.parseDouble(userInput))));
			}  // end method def
		}    //end anonymous class def.
		);   //end call to addActionListener method
		
		toFahrenheit = new JButton("Convert temperature to fahrenheit from celcius");
		toFahrenheit.addActionListener(new ActionListener () { 	
			public void actionPerformed(ActionEvent e) {
				String userInput = userEnteredTemperature.getText();
				Model m = new Model();
				userEnteredTemperature.setText(Double.toString(m.toFahrenheit(Double.parseDouble(userInput))));
			}  // end method def
		}    //end anonymous class def.
		);   //end call to addActionListener method

		add(userEnteredTemperature);
		add(toCelcius);
		add(toFahrenheit);
		setSize(500,150);
		setTitle("Temperature Converter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	/**
	 * The model of the temperature converter. COntains two static methods for converting to and from
	 * Celcius and Fahrenheit
	 */
	public class Model{
		/**
		 * Convert temperature to celcius from parameter(fahrenheit), and return it.
		 */
		public double toCelcius(double fahrenheit){
			return ((double)5/9)*(fahrenheit-32);
			
		}
		/**
		 * Convert temperature to fahrenheit from parameter(celcius), and return it.
		 */
		public double toFahrenheit(double celcius){
			return ((double)9/5)*celcius+32;
		}
	}

}
