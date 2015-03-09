package assignment_07.mainpackage;

/**
 * Creates and manages an array containing Employee objects
 */
public class EmployeeList implements Cloneable {
	private static final boolean TESTING_CONTAINS = false, TESTING_SORT = false;
	
	private Employee[] employeeArray;
	private int allocatedSpace;
	private int occupiedSpace = 0;

	/**
	 * Creates an employee object array with an assigned amount of space
	 */
	public EmployeeList(int requestedSpace) {
		allocatedSpace = requestedSpace;
		employeeArray = new Employee[allocatedSpace];
	}

	/**
	 * Adds an employee object to the end of employeeArray
	 */
	public void add(Employee inputEmployee) {
		try {
			employeeArray[occupiedSpace] = inputEmployee;
			occupiedSpace++;
		} catch (ArrayIndexOutOfBoundsException arrayException) {
			System.out.println("Error: array has been filled!");
		}
	}

	/**
	 * Prints out EmployeeList array as a string, ends when last element
	 * of list is reached
	 */
	public String toString() {
		int countdown = 0;
		String result = "";
		while (countdown < allocatedSpace && employeeArray[countdown] != null) {
			result += (employeeArray[countdown] + "\n");
			countdown++;
		}
		return result;
	}
	
	/**
	 * Creates a deep copy of an existing EmployeeList
	 */
	public Object clone() {
		int countdown = 0;
		EmployeeList cloned = new EmployeeList(allocatedSpace);
		while (countdown < allocatedSpace && employeeArray[countdown] != null){
			cloned.add((Employee) (this.employeeArray[countdown].clone()));
			countdown++;
		}
		return cloned;
	}
	
	/**
	 * Takes in an Employee object and determines if the Employee exists in
	 * the EmployeeList 
	 */
	public boolean contains(Employee searchEmpl) {
		boolean found = false;
		int counter = 0;
		for(counter = 0; (counter<occupiedSpace) && (!found); counter++){
			if (TESTING_CONTAINS) System.out.println("Loop#" + (counter+1) );
			if( (employeeArray[counter]).equals(searchEmpl) )
				found = true;
		}
		return found;
	}
	
	/**
	 * Sorts the Employee objects in a given EmployeeList according to their name
	 */
	public void sort() {
		int location, index;
		Employee temporaryHolder;
		for (location = 0; location <= occupiedSpace; location++){
			for (index = location+1; index < occupiedSpace; index++){
				if (TESTING_SORT) System.out.println("Locations: " + location + "," + index);
				if (employeeArray[location].compareTo(employeeArray[index]) > 0){
					temporaryHolder = employeeArray[location];
					employeeArray[location] = employeeArray[index];
					employeeArray[index] = temporaryHolder;
					if (TESTING_SORT) System.out.println("!!!SWITCHED!!!");
				}
			}
		}
	}
	
	/**
	 * Returns an Employee object from a specified location in the array
	 */
	public Employee getEmployee(int location) {
		return employeeArray[location];
	}
	/**
	 * Changes an Employee object at a specified location to another user-provided Employee object
	 */
	public void changeEmployee(int location, Employee empl) {
		employeeArray[location] = empl;
	}
}