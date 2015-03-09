package cs1b_20130318_monday;

/**
 * The model of the temperature converter. COntains two static methods for converting to and from
 * Celcius and Fahrenheit
 */
public class Model{
	/**
	 * Convert temperature to celcius from parameter(fahrenheit), and return it.
	 */
	public static double toCelcius(double fahrenheit){
		return ((double)5/9)*(fahrenheit-32);
		
	}
	/**
	 * Convert temperature to fahrenheit from parameter(celcius), and return it.
	 */
	public static double toFahrenheit(double celcius){
		return ((double)9/5)*celcius+32;
	}
}
