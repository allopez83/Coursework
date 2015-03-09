package assignment01.exercises;

//Main file for arrays vs. ArrayList
//CS 1C, Foothill College, Michael Loceff, creator

import java.util.*;
import java.text.*;

public class ArrayBenchmark
{
   public static void main(String[] args) throws Exception
   {
      final int ARRAY_SIZE = 20000000;
      int k;
      double avg;

      // for timing:
      long start_time, end_time;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      int[] array_of_ints = new int[ARRAY_SIZE]; // simple array ---
      // START TIME -------------------------------
      start_time = System.nanoTime();
      for (k = 0; k < ARRAY_SIZE; k++)
         array_of_ints[k] = (int) (Math.random() * 100);

      for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
         avg += array_of_ints[k];
      avg /= ARRAY_SIZE;

      System.out.println("Average: " + avg);
      // end timing
      end_time = System.nanoTime();

      // report algorithm time
      System.out.println("\nElapsed time for Time for simple array " + tidy
            .format((end_time - start_time) / 1e9) + " seconds.\n");

      // ArrayList using add() / get()
      ArrayList<Integer> list_of_ints = new ArrayList<Integer>(ARRAY_SIZE);
      // START TIME -------------------------------
      start_time = System.nanoTime();
      for (k = 0; k < ARRAY_SIZE; k++)
         list_of_ints.add((int) (Math.random() * 100));

      for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
         avg += list_of_ints.get(k);
      avg /= ARRAY_SIZE;

      System.out.println("Average: " + avg);
      // end timing
      end_time = System.nanoTime();

      // report algorithm time
      System.out
            .println("\nElapsed time for Time for ArrayList add/get " + tidy
                  .format((end_time - start_time) / 1e9) + " seconds.\n");

      // ArrayList using set() / get()
      // START TIME -------------------------------
      start_time = System.nanoTime();
      for (k = 0; k < ARRAY_SIZE; k++)
         list_of_ints.set(k, (int) (Math.random() * 100));

      for (k = 0, avg = 0.; k < ARRAY_SIZE; k++)
         avg += list_of_ints.get(k);
      avg /= ARRAY_SIZE;

      System.out.println("Average: " + avg);
      // end timing
      end_time = System.nanoTime();

      // report algorithm time
      System.out
            .println("\nElapsed time for Time for ArrayList set/get " + tidy
                  .format((end_time - start_time) / 1e9) + " seconds.\n");
   }
}

/*
 * -------------------- run --------------------
 * 
 * --------- 100,000 ints -----------
 * 
 * Average: 49.37006
 * 
 * Elapsed time for Time for simple array 0.0145 seconds.
 * 
 * Average: 49.45096
 * 
 * Elapsed time for Time for ArrayList add/get 0.0163 seconds.
 * 
 * Average: 49.5306
 * 
 * Elapsed time for Time for ArrayList set/get 0.0144 seconds.
 * 
 * --------- 20,000,000 ints ----------
 * 
 * Average: 49.5001975
 * 
 * Elapsed time for Time for simple array 1.1182 seconds.
 * 
 * Average: 49.50457025
 * 
 * Elapsed time for Time for ArrayList add/get 1.6265 seconds.
 * 
 * Average: 49.51661085
 * 
 * Elapsed time for Time for ArrayList set/get 1.5925 seconds.
 * 
 * ------------------------------------------------
 */