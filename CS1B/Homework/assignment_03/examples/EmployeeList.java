package assignment_03.examples;

import assignment_03.mainpackage.Employee;

/**
 * Creates and manages arrays containing Employee objects
 */
public class EmployeeList {
	private Employee[] EmployeeArray;
	private int allocatedSpace;
	private int counter = 0;
	/*
	 * Creates an employee object array with an assigned amount of space
	 */
	public EmployeeList(int requestedSpace){
		allocatedSpace = requestedSpace;
		EmployeeArray = new Employee[allocatedSpace];
	}
	/*
	 * Adds an employee object to the end of the array 
	 */
	public void add(Employee employee){
		try{
			EmployeeArray[counter] = employee;
			counter++;
		}catch(ArrayIndexOutOfBoundsException arrayException){
			System.out.println("Error: array has been filled!");
		}
	}
	/*
	 * Adds an employee object at the user-specified location in the array, does NOT start at zero
	 */
	/*   ///////////// DELETE THIS /////////////
	public void add(Employee employee, int location){
		try{
			EmployeeArray[location-1] = employee;
		}catch(ArrayIndexOutOfBoundsException arrayException){
			System.out.println("Error: invalid array location!");
		}
	}
	*/   ///////////// DELETE THIS /////////////
	/*
	 * Prints out EmployeeList array as a string, stops when end of list is reached
	 */
	public String toString(){
		int countdown = 0;
		String result = "";
		while(countdown < allocatedSpace && EmployeeArray[countdown] != null){
			result += (EmployeeArray[countdown] + "\n");
			countdown++;
		}
		return result;
	}
}