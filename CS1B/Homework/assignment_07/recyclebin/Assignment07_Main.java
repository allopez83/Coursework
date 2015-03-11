package assignment_07.recyclebin;

import java.io.*;
import assignment_07.mainpackage.Employee;

/**
 * Attempts to store Employee objects to disk and reading them again.
 */
public class Assignment07_Main {
	public static final boolean TESTING = true;

	public static void main(String[] args) {
		if (TESTING) System.out.println("Begin testing\n");
		
		// Employee stuffs
		Employee Terry, Harris, Mackenzie;
		Terry = new Employee("Terry", "101", 30.00);
		Harris = new Employee("Harris", "102", 10.00);
		Mackenzie = new Employee("Mackenzie", "103", 20.00);
		if(TESTING) System.out.println(
		"/**** Original three employees: ****/\n"+
		Terry + "\n"+
		Harris + "\n"+
		Mackenzie + "\n");
		
		if(TESTING) System.out.println("/**** Output to disk ****/");
		FileOutputStream fileOutput;
		ObjectOutputStream objectOutput;
		try {
			if(TESTING) System.out.println("Attempting to write Employees");
			fileOutput = new FileOutputStream("StoredEmployees");
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject("blah");
			objectOutput.writeObject(Harris);
			objectOutput.writeObject(Mackenzie);
			objectOutput.close();
			fileOutput.close();
			if(TESTING) System.out.println("Employees written\n");
		} catch (Exception e) {
			System.out.println("File not written!\n" + e.getMessage());
		}
		
		// Set to null
		Terry = Harris = Mackenzie = null;
		
		if(TESTING) System.out.println("/**** Input to program ****/");
		FileInputStream fileInput;
		ObjectInputStream objectInput;
		try{
			if(TESTING) System.out.println("Attempting to read Employees from file");
			fileInput = new FileInputStream("StoredEmployees");
			objectInput = new ObjectInputStream(fileInput);
			Terry = (Employee) objectInput.readObject();
			Harris = (Employee) objectInput.readObject();
			Mackenzie = (Employee) objectInput.readObject();
			objectInput.close();
			fileInput.close();
			if(TESTING) System.out.println("File read and Employees converted\n");
		}catch(IOException ioe){
			System.out.println("File could not be read!\n" + ioe.getMessage());
		}catch(ClassNotFoundException cnfe){
			System.out.println("File could not be read!\n" + cnfe.getMessage());
		}
		
		// Print the retrieved Employees
		System.out.println(
		"Employees after reading from file:\n"+
		Terry + "\n"+
		Harris + "\n"+
		Mackenzie);	
		
		if (TESTING) System.out.print("\nTesting Done");
	}
}

/************PASTE FROM CONSOLE************

**************END OF PASTE*****************/