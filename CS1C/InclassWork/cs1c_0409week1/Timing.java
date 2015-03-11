package cs1c_0409week1;

import java.text.NumberFormat;
import java.util.*;

public class Timing {
	public static void main(String[] args){
		System.out.println("begin");
		
		NumberFormat tidy = NumberFormat.getInstance(Locale.US);
		tidy.setMaximumFractionDigits(4);
		long startTime, endTime;
		startTime = System.nanoTime();

		// dat program
		final int arraySize =
				20
				//200
				//2000
				//20000
				//200000
				;
		
		int k;
		Integer[] arrayOfInts = new Integer[arraySize];
		// WOOPS, DUNNO
		
		endTime = System.nanoTime();
		
		System.out.println("Elapsed Time:\n" +
						   tidy.format((endTime-startTime)/1e9) +
						   " seconds");
		
		System.out.println("end");
	}
}
