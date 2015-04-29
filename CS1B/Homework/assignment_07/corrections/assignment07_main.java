package assignment_07.corrections;

import java.io.*;

/**
 * Attempts to store Employee objects to disk and reading them again. This
 * version attempts to use methods instead of all the code being in main.
 */
public class Assignment07_Main {
	public static final boolean TESTING = false,
								TESTING_CREATE_FILE = false,
								TESTING_WRITE_EMPLOYEE = false,
								TESTING_CLOSE_FILE = false,
								TESTING_START_READ = false,
								TESTING_READ_EMPLOYEE = false,
								TESTING_END_READ = false;
	
	private static FileOutputStream fileOutput;
	private static ObjectOutputStream objectOutput;
	private static FileInputStream fileInput;
	private static ObjectInputStream objectInput;
	
	// File output methods
	/**
	 * Tries to create a stream out to a new file
	 */
	private static void createFile(String fileName){
		if(TESTING_CREATE_FILE) System.out.println("> Opening file");
		try {
			fileOutput = new FileOutputStream(fileName);
			objectOutput = new ObjectOutputStream(fileOutput);
			if(TESTING_CREATE_FILE) System.out.println("> File opened");
		} catch(Exception e) {
			System.out.println("Unable to create specified file!\n" + e.getMessage());
		}
	}
	
	/**
	 * Writes an employee object into the file that is open
	 */
	private static void writeEmployee(Employee empl){
		if(TESTING_WRITE_EMPLOYEE) System.out.println("> Writing Employee object");
		try{
			objectOutput.writeObject(empl);
			if(TESTING_WRITE_EMPLOYEE) System.out.println("> Employee object written");
		} catch(Exception e) {
			System.out.println("Unable to write Employee object!\n" + e.getMessage());
		}
	}
	
	/**
	 * Closes stream that is GOING TO a file created by the createFile() method
	 */
	private static void closeFile(){
		if(TESTING_CLOSE_FILE) System.out.println("> Closing file");
		try {
			objectOutput.close();
			fileOutput.close();
			if(TESTING_CLOSE_FILE) System.out.println("> File closed");
		} catch(Exception e) {
			System.out.println("Unable to close file being written!\n" + e.getMessage());
		}
	}
	
	// File input methods
	/**
	 * Tries to open a stream from a specified file to read from
	 */
	private static void startReading(String fileName){
		if(TESTING_START_READ) System.out.println("> Opening file");
		try{
			fileInput = new FileInputStream(fileName);
			objectInput = new ObjectInputStream(fileInput);
			if(TESTING_START_READ) System.out.println("> File opened");
		} catch(Exception e) {
			System.out.println("Unable to open specified file for reading!\n" + e.getMessage());
		}
	}
	
	/**
	 * Uses stream from startReading() method to attempt to find an Employee object
	 */
	private static Employee readEmployee(){
		if(TESTING_READ_EMPLOYEE) System.out.println("> Reading Employee object");
		Employee retrieved = new Employee();
		try{
			retrieved = (Employee)(objectInput.readObject());
			if(TESTING_READ_EMPLOYEE) System.out.println("> Employee object read");
		} catch(Exception e) {
			System.out.println("Could not find an Employee object!\n" + e.getMessage());
		}
		return retrieved;
	}
	
	/**
	 * Closes a stream COMING FROM a file created by the startReading() method
	 */
	private static void endReading(){
		if(TESTING_END_READ) System.out.println("> Closing file"); 
		try{
			fileInput.close();
			objectInput.close();
			if(TESTING_END_READ) System.out.println("> File closed");
		} catch(Exception e) {
			System.out.println("Unable to close file being read!\n" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		if (TESTING) System.out.println("Begin testing\n");
		
		if (TESTING) System.out.println("/**** Testing new EmployeeList methods ****/");
		/* 
		 * Conducting test on new methods in EmployeeList, getEmployee() and changeEmployee()
		 * getEmployee() : return an Employee object at a given location
		 * changeEmployee() : change an Employee object at a given location to another specified Employee object
		 */
		Employee Albert, Bobby, Charlie;
		Albert = new Employee("Albert", "001", 1.25);
		Bobby = new Employee("Bobby", "002", 2.25);
		Charlie = new Employee("Charlie", "003", 3.25);
		EmployeeList testEmployeeList = new EmployeeList(5);
		testEmployeeList.add(Albert);
		testEmployeeList.add(Bobby);
		testEmployeeList.add(Charlie);
		if (TESTING) System.out.println("Original testEmployeeList:\n" + testEmployeeList);
		
		if (TESTING) System.out.println("Testing getEmployee:");		// First two should be true, last two false
		System.out.println(testEmployeeList.getEmployee(2).equals(new Employee("noName", "003", 0.00)));
		System.out.println(Charlie.equals(testEmployeeList.getEmployee(2)));
		System.out.println(testEmployeeList.getEmployee(2).equals(new Employee("noName", "001", 0.00)));
		System.out.println(testEmployeeList.getEmployee(1).equals(Charlie) + "\n");
		
		if (TESTING) System.out.println("Testing changeEmployee:");		// First two should be true, last two false
		testEmployeeList.changeEmployee(0, Bobby);
		if (TESTING) System.out.println("New testEmployeeList:\n" + testEmployeeList);
		System.out.println(testEmployeeList.getEmployee(0).equals(new Employee("noName", "002", 0.00)));
		System.out.println(testEmployeeList.getEmployee(0).equals(Bobby));
		System.out.println(testEmployeeList.getEmployee(0).equals(new Employee("noName", "001", 0.00)));
		System.out.println(testEmployeeList.getEmployee(0).equals(Charlie) + "\n");
		testEmployeeList = null;
		// End of new methods testing
		
		
		Employee Terry, Harris, Mackenzie;
		Terry = new Employee("Terry", "101", 30.00);
		Harris = new Employee("Harris", "102", 10.00);
		Mackenzie = new Employee("Mackenzie", "103", 20.00);
		
		EmployeeList listedEmployees = new EmployeeList(3);
		listedEmployees.add(Terry);
		listedEmployees.add(Harris);
		listedEmployees.add(Mackenzie);
		
		if (TESTING) System.out.println("/**** listedEmployees before any changed ****/");
		System.out.println(listedEmployees);
		
		if (TESTING) System.out.println("/**** Output to disk ****/");
		if (TESTING) System.out.println("Attempting to write Employees");
		createFile("StoredEmployees");
		for(int i = 0; i < 3; i++){
			writeEmployee(listedEmployees.getEmployee(i));		// Employees are added in order of their SSN
		}
		closeFile();
		if (TESTING) System.out.println("Employees written\n");
		
		// Set to null
		Terry = Harris = Mackenzie = null;
		
		if (TESTING) System.out.println("/**** Input to program ****/");
		if (TESTING) System.out.println("Attempting to read Employees from file");
		startReading("StoredEmployees");
		for(int i = 0; i < 3; i++){
			listedEmployees.changeEmployee(i, readEmployee());	
		}
		endReading();
		if (TESTING) System.out.println("File read and Employees converted\n");
		
		if (TESTING) System.out.println("/**** listedEmployees with retrieved Employees ****/");
		System.out.println(listedEmployees);		// Should be same as in beginning
		
		if (TESTING) System.out.print("\nTesting Done");
	}
}

/************PASTE FROM CONSOLE************
true
true
false
false

true
true
false
false

Name: 'Terry', SSN: 101, Salary: 30.0
Name: 'Harris', SSN: 102, Salary: 10.0
Name: 'Mackenzie', SSN: 103, Salary: 20.0

Name: 'Terry', SSN: 101, Salary: 30.0
Name: 'Harris', SSN: 102, Salary: 10.0
Name: 'Mackenzie', SSN: 103, Salary: 20.0


**************END OF PASTE*****************/