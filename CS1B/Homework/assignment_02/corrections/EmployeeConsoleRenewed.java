package assignment_02.corrections;


//import java.util.*;



/*
 * Reads user input from console to create new Employee object, and writes data into the console detailing a specified Employee object
 */
public class EmployeeConsoleRenewed {
	/*
	 * Asks user for input to create new Employee object, then initializes it
	 */
	/*
	public void readFromUser(Employee empl) {
		String userInput;
		Scanner kb = new Scanner(System.in);

		String name = null;
		String SSN = null;
		double salary = 0.00;
		boolean gotEmployeeName = false, gotEmployeeSSN = false, gotEmployeeSalary = false;

		while (gotEmployeeName == false) {
			try {
				System.out.println("What is the employee's name? ");
				userInput = kb.nextLine();
				if (false == userInput.matches("[a-zA-Z]"))
					throw new IllegalArgumentException();
				name = userInput;
				gotEmployeeName = true;
			} catch (Exception e) {
				System.out.println("Error: enter a valid name!");
			}
		}
		while (gotEmployeeSSN == false) {
			try {
				System.out
						.println("What is the employee's Social Security Number? ");
				userInput = kb.nextLine();
				//SSN = Integer.parseInt(userInput);
				gotEmployeeSSN = true;
			} catch (Exception e) {
				System.out.println("Error: enter an valid integer!");
			}
		}
		while (gotEmployeeSalary == false) {
			try {
				System.out.println("What is the employee's salary? ");
				userInput = kb.nextLine();
				salary = Double.parseDouble(userInput);
				gotEmployeeSalary = true;
			} catch (Exception e) {
				System.out.println("Error: enter a valid double!");
			}
		}
		
		empl.initialize(name, SSN, salary);
		kb.close();
	}
	*/

	/*
	 * Prints out the information contained in a specified Employee object into the console
	 */
	public void printToUser(Employee empl) {
		System.out.print(empl);
	}
}