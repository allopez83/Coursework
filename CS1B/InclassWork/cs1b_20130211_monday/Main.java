package cs1b_20130211_monday;

import java.util.Arrays;
import java.util.Random;

/**
 * Searching through an array for an int
 */
public class Main {
	/**
	 * Counts from a certain number down to 0
	 * @param input number to start from
	 */
	public static void countDown(int input){
		System.out.print(input + ", ");
		if(input>1)
			countDown(input-1);
	}
	/**
	 * Main for the array stuff
	 */
	public static void main(String[] args) {
		final int MAX = 10;
		int array [];
		array = new int[MAX];
		Random rand = new Random();
		for (int i = 0; i<MAX; i++)
			array[i] = rand.nextInt()%11;
		for (int i = 0; i<MAX; i++)
			System.out.print(array[i] + ", ");
		System.out.println();
		
		//Search through array looking for target
		int target = 9;
		Arrays.sort(array);
		for (int i = 0; i<MAX; i++)
			System.out.print(array[i] + ", ");
		System.out.println();
		
		/**
		 * My Solution
		 *
		boolean isItThere = false;
		for (int i = 0; i<MAX; i++){
			if (array[i] == target)
				isItThere = true;
			if (i == MAX-1){
				System.out.println("\n" + isItThere);
			}
		}
		
		/**
		 * Teacher Solution
		 */
		boolean found = false;
		for (int i = 0; (i<MAX) && (!found) && array[i] < target; i++)
		{
			if (array[i] == target){
				found = true;
			}
			System.out.println("Loopdy loopy! " + i + found);
		}
		if(found)
			System.out.println("found it");
		else
			System.out.println("not here");
		
		/**
		 * Solution #2
		 *
		int i = 0;
		while((i<MAX) && (array[i] != target)){
			i++;
		}
		if(i == MAX)	// if equals max, means it went through entire array
			System.out.println("\n" + "Not found!");
		else
			System.out.println("\n" + "Found");
		 */
		countDown(10);
	}
}
