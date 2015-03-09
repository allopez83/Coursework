package assignment_02.mainpackage;


import java.util.*;


/*fdsa
 * Reads user input from console to create new Employee object, and writes data into the console detailing a specified Employee object
 */
public class EmployeeConsoleView {
	/*
	 * Asks user for input to create new Employee object, then initializes it
	 */
	public void readFromUser(Employee empl) {
		String userInput;
		Scanner kb = new Scanner(System.in);

		String name = null;
		int SSN = 0;
		double salary = 0.00;
		boolean gotEmployeeName = false, gotEmployeeSSN = false, gotEmployeeSalary = false;

		while (gotEmployeeName == false) {
			try {
				System.out.println("What is the employee's name? ");
				userInput = kb.nextLine();
				CharSequence unwanted1 = "1", unwanted2 = "2", unwanted3 = "3", unwanted4 = "4", unwanted5 = "5", unwanted6 = "6", unwanted7 = "7", unwanted8 = "8", unwanted9 = "9", unwanted10 = "0", unwanted11 = "<", unwanted12 = ">", unwanted13 = "[", unwanted14 = "]", unwanted15 = "{", unwanted16 = "}", unwanted17 = "@", unwanted18 = "#", unwanted19 = "$", unwanted20 = "%", unwanted21 = "^", unwanted22 = "&", unwanted23 = "?", unwanted24 = "+", unwanted25 = "~", unwanted26 = "=", unwanted27 = ";", unwanted28="*";
				if (true == userInput.contains(unwanted1)
						|| userInput.contains(unwanted2)
						|| userInput.contains(unwanted3)
						|| userInput.contains(unwanted4)
						|| userInput.contains(unwanted5)
						|| userInput.contains(unwanted6)
						|| userInput.contains(unwanted7)
						|| userInput.contains(unwanted8)
						|| userInput.contains(unwanted9)
						|| userInput.contains(unwanted10)
						|| userInput.contains(unwanted11)
						|| userInput.contains(unwanted12)
						|| userInput.contains(unwanted13)
						|| userInput.contains(unwanted14)
						|| userInput.contains(unwanted15)
						|| userInput.contains(unwanted16)
						|| userInput.contains(unwanted17)
						|| userInput.contains(unwanted18)
						|| userInput.contains(unwanted19)
						|| userInput.contains(unwanted20)
						|| userInput.contains(unwanted21)
						|| userInput.contains(unwanted22)
						|| userInput.contains(unwanted23)
						|| userInput.contains(unwanted24)
						|| userInput.contains(unwanted25)
						|| userInput.contains(unwanted26)
						|| userInput.contains(unwanted27)
						|| userInput.contains(unwanted28))
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
				SSN = Integer.parseInt(userInput);
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

	/*
	 * Prints out the information contained in a specified Employee object into the console
	 */
	public void printToUser(Employee empl) {
		System.out.print(empl);
	}
}