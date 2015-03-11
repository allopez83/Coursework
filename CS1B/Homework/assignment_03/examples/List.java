package assignment_03.examples;

import java.util.Arrays;

/*
 * One object represents a list of int
 */
public class List {
	//private variables here:
	private int[] newArray;		//the array that will be used
	private int allocatedSpace;	//space wanted in the array
	private int counter;		//integer for keeping count
	
	public String toString(){
		return Arrays.toString(newArray);
	}
	public List(int requiredSpace){
		allocatedSpace = requiredSpace;
		newArray = new int[allocatedSpace];
	}
	public void fill(int desiredValue){
		counter = 0;		//reset the counter
		while(counter<allocatedSpace){
			newArray[counter] = desiredValue;
			counter++;
		}
	}
	public void PrintNumbered(List theList){
		counter = 0;		//reset the counter
		while(counter<allocatedSpace){
			System.out.println(counter+1 + ". " + newArray[counter]);
			counter++;
		}
	}
}
