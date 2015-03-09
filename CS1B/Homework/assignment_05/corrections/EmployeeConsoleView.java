package assignment_05.corrections;

import java.util.*;

/**
 * Reads user input from console to create new Employee object, and writes data into the console detailing a specified Employee object
 */
public class EmployeeConsoleView {
	private Scanner kb = new Scanner(System.in);
	/**
	 * Continually asks user for valid string, returns a value if valid
	 */
	private String UserInputString(String message) {
		String possibleString = null;
		boolean gotString = false;
		while (gotString == false) {
			try {
				System.out.println(message);
				possibleString = kb.nextLine();
				gotString = true;
			} catch (Exception e) {
				System.out.println("Error: make sure your input can be a string first!\n");
			}
		}
		return possibleString;
	}
	/**
	 * Uses UserInputString to get String, then checks if it can be a name
	 */
	private String UserInputName(String message, String onError) {
		String possibleName = null;
		boolean gotEmployeeName = false;
		while (gotEmployeeName == false) {
			possibleName = UserInputString(message);
			try{
				if (false == possibleName.matches("[a-zA-Z]+"))
					throw new IllegalArgumentException();
				gotEmployeeName = true;
			}catch(Exception e){
				System.out.println(onError);
			}
		}
		return possibleName;
	}
	/**
	 * Uses UserInputString to ask user for a string value, then checks if it can be a SSN
	 */
	private String UserInputSSN(String message, String onError) {
		String possibleSSN = null;
		boolean gotEmployeeSSN = false;
		while (gotEmployeeSSN == false) {
			possibleSSN = UserInputString(message);
			try{
				if (false == possibleSSN.matches("[0-9]+"))
					throw new IllegalArgumentException();
				gotEmployeeSSN = true;
			}catch(Exception e){
				System.out.println(onError);
			}
		}
		return possibleSSN;
	}
	/**
	 * Uses UserInputString to ask user for a string value, then converts the string to a double
	 */
	private double UserInputSalary(String message, String onError) {
		double possibleSalary = 0.00;
		boolean gotEmployeeSalary = false;
		while(gotEmployeeSalary == false) {
			try {
				possibleSalary = Double.parseDouble(UserInputString(message));
				gotEmployeeSalary = true;
			} catch (Exception e) {
				System.out.println(onError);
			}
		}
		return possibleSalary;
	}
	
	/**
	 * Asks user for input to create new Employee object, then initializes it
	 */
	public void readFromUser(Employee empl) {
		String name = null;
		String SSN = null;
		double salary = 0.00;

		String NameMessage = "What is the employee's name?", NameError = "Error: enter a valid name!";
		name = UserInputName(NameMessage, NameError);

		String SSNMessage = "What is the employee's Social Security Number?", SSNError = "Error: enter an appropriate SSN, numbers only!";
		SSN = UserInputSSN(SSNMessage, SSNError);

		String SalaryMessage = "What is the employee's salary?", SalaryError = "Error: enter an appropriate salary, numbers only!";
		salary = UserInputSalary(SalaryMessage, SalaryError);

		empl.initialize(name, SSN, salary);
		kb.close();
	}

	/**
	 * Prints out the information contained in a specified Employee object into the console
	 */
	public void printToUser(Employee empl) {
		System.out.print(empl);
	}
}