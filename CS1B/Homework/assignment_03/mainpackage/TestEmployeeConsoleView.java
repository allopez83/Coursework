package assignment_03.mainpackage;

/*
 * Tests EmployeeConsoleView by calling all of its methods
 */
public class TestEmployeeConsoleView {
	public static void main(String[] args){
        System.out.println("Starting EmployeeConsoleView test\n");
        
        Employee exampleEmployee = new Employee();
        EmployeeConsoleView console = new EmployeeConsoleView();
        console.readFromUser(exampleEmployee);
        console.printToUser(exampleEmployee);
        
        System.out.println("\n\nEmployeeConsoleView test completed");
   }
}

/*
 * how to decide to set privacy as default or public?
 */