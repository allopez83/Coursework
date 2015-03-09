package assignment_09.mainpackage;

/**
 * Contains methods that ConverterApp uses to function. This is restricted to non-GUI functions.
 * 
 * @author HW
 *
 */
public class ConvertTools {
	private static final boolean TESTING_RETRIEVE_DOUBLE = false,
								 TESTING_CONVERSION = false;
	
	/**
	 * Takes a string input and checks if it is a number. Decimals are allowed, but not required.
	 * @param input String that will be checked if it is a number.
	 * @return input converted into a double
	 * @throws IllegalArgumentException if input contains anything that makes it not a number
	 */
	public static double retrieveTemperature(String input) {
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
	public static String temperatureConversion(double input) {
		if (TESTING_CONVERSION) System.out.println(" > Inside temperatureConversion()");
		if (TESTING_CONVERSION) System.out.println(" > Input value: " + input);
		
		/*
		if (ConverterApp.toFahrenheit.getState())
			input = ((9.0/5.0)*input)+32.0;
		else
			input = ((input-32.0)*(5.0/9.0));
		 */
		
		if (TESTING_CONVERSION) System.out.println(" > Returning value: " + input);
		if (TESTING_CONVERSION) System.out.println(" > Completed temperatureConversion()");
		return input+"";
	}

}
