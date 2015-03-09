package assignment_02.mainpackage;


import java.util.*;




/*
 * Reads user input from console to create new Employee object, and writes data into the console detailing a specified Employee object
 */
public class EmployeeConsoleViewScrapped {
	Scanner kb = new Scanner(System.in);

	/*
	 * Continually asks for the user's input and returns the value as a string
	 */
	private String UserInputString(String message) {
		while (true) {
			try {
				System.out.print(message+" ");
				String input = kb.nextLine();
				return input;
			} catch (Exception e) {
				System.out.println("Error: make sure your input can be a string first!\n");
				kb.close();
				kb = new Scanner(System.in);
			}
		}
	}
	/*
	 * Uses UserInputString to ask user for a string value, then converts the string to an integer
	 */
	private int UserInputInt(String message, String onError) {
		while (true) {
			try {
				return Integer.parseInt(UserInputString(message));
			} catch (Exception e) {
				System.out.println(onError);
			}
		}
	}
	/*
	 * Uses UserInputString to ask user for a string value, then converts the string to a double
	 */
	private double UserInputDouble(String message, String onError) {
		while (true) {
			try {
				return Double.parseDouble(UserInputString(message));
			} catch (Exception e) {
				System.out.println(onError);
			}
		}
	}

	/*
	 * Asks user for input to create new Employee object, then initializes it
	 */
	public void readFromUser(Employee empl) {
		String name = null;
		int SSN = 0;
		double salary = 0.00;

		String NameMessage = "What is the employee's name?";
		name = UserInputString(NameMessage);

		String SSNMessage = "What is the employee's Social Security Number?", SSNError = "Error: enter an appropriate integer!";
		SSN = UserInputInt(SSNMessage, SSNError);

		String SalaryMessage = "What is the employee's salary?", SalaryError = "Error: enter an appropriate double value!";
		salary = UserInputDouble(SalaryMessage, SalaryError);

		empl.initialize(name, SSN, salary);
		kb.close();
	}

	/*
	 * Prints out the information contained in a specified Employee object into the console
	 */
	public void printToUser(Employee empl) {
		System.out.print(empl);
	}
}