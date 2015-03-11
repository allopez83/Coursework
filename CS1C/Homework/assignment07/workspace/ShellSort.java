package assignment07.workspace;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Description pending
 * 
 * @author HW
 * 
 */
public class ShellSort
{
   /*
    * User-input parameter(s), please follow any provided instructions or the
    * test program might crash/fail/blow up the world. Thank you!
    */
   // Size of the array that will be sorted, and the gap sequence array
   private static final int ARRAY_SIZE = 25000, GAP_SEQUENCE_SIZE = 20;
   // Used to take averages of run times
   private static final int total_runs = 175; // Also determines number of runs
   // Whether or not to print certain information; set as true to print.
   private static final boolean
         PRINT_TEST_TITLE = true, // Title of tests
         PRINT_EACH_RUN = false, // Time result of each individual sort
         PRINT_ARRAY = false, // Entire sorted array
         PRINT_AVERAGE_EXTRAS = false, // Print "Average: "; false for raw data
         // Tests array elements for consistency. I recommend setting only one
         // test as true.
         TEST_SHOW_ELEMENTS = false, // Prints each element at select locations
         TEST_PASS_PRINT = false, // Same as above but prints "pass" or "error"
         TEST_SILENT = true;

   /*
    * The "do not change" parameters. These can actually be changed, but they're
    * not exactly intended to be changed.
    */
   // Timer stuffs
   private static NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   private static long start_time, end_time;
   // Stores the times for each sort
   private static long[] recorded_times = new long[total_runs];

   /**
    * Shell sort, sorts an array from small to big. Contained data type needs to
    * implement comparable.
    * @param a the array to be sorted
    */
   public static <E extends Comparable<? super E>> void shellSort1(E[] a)
   {
      int gap = 1;
      int k, pos, array_size;
      E tmp;

      array_size = a.length;
      // Decrease gap by factor of two, starting from half of array size
      for (gap = array_size / 2; gap > 0; gap /= 2)
         // Set pos as gap and increment each time
         for (pos = gap; pos < array_size; pos++)
         {
            tmp = a[pos];
            for (k = pos; k >= gap && tmp.compareTo(a[k - gap]) < 0; k -= gap)
               a[k] = a[k - gap];
            a[k] = tmp;
         }
   }

   /**
    * Shell sort, sorts an array from small to big. Contained data type needs to
    * implement comparable. Elements are sorted according to a specified
    * sequence.
    * @param sort_arr array to be sorted
    * @param sequence the sequence to sort the elements in the array
    */
   public static <E extends Comparable<? super E>> void shellSort2(
         E[] sort_arr, int[] sequence)
   {
      int gap, gap_position, array_size, k, pos;
      E tmp;

      gap_position = sequence.length - 1;
      array_size = sort_arr.length;
      // Traverse the gap array from end to start
      for (gap = sequence[gap_position]; gap_position > -1; gap_position--)
      {
         gap = sequence[gap_position];
         // Set pos as gap and increment each time
         for (pos = gap; pos < array_size; pos++)
         {
            tmp = sort_arr[pos];
            for (k = pos; k >= gap && tmp.compareTo(sort_arr[k - gap]) < 0; k -= gap)
               sort_arr[k] = sort_arr[k - gap];
            sort_arr[k] = tmp;
         }
      }
      if (PRINT_ARRAY)
         printArray(sort_arr);
      return;
   }

   /**
    * Intent: time the speed of four sorting methods on int arrays, all with the
    * same data.
    * 
    * Sorting methods:
    * 1. Shell sort with implied gap sequence
    * 2. Shell sort with explicit gap sequence
    * 3. Sedgewick's gap sequence
    * 4. Gap sequence devised by user
    */
   public static void main(String[] args)
   {
      // ---------- Preparations----------------------------------------------
      tidy.setMaximumFractionDigits(6); // Number of decimals for timer to show
      // Used as counters in for loops
      int i, k, n;
      // Used in checking if array elements at certain location are equal
      boolean elements_equal, noProblem = true;
      int interval;

      // Gaps that will be used for sorting
      int sortGap_1[] = new int[GAP_SEQUENCE_SIZE];
      int sortGap_2[] = new int[GAP_SEQUENCE_SIZE];
      int sortGap_3[] = new int[GAP_SEQUENCE_SIZE];

      // Create explicit gap sequence
      double total_1, total_2, total_3;
      for (k = 0; k < GAP_SEQUENCE_SIZE; k++)
      {
         total_1 = 1;
         // 2^(i)
         for (i = 0; i < k; i++)
            total_1 *= 2;
         sortGap_1[k] = (int) total_1;
      }
      // Create Sedgewick's gap sequence
      for (k = 0; k < GAP_SEQUENCE_SIZE; k++)
      {
         if (k % 2 == 0) // The equations alternate
         {
            n = k/2;
            // Intent: 9(4^k - 2^k) + 1
            total_1 = 0;
            total_2 = 1;
            total_3 = 1;
            for (i = 0; i < n; i++) // 4^k
               total_2 *= 4;
            for (i = 0; i < n; i++) // 2^k
               total_3 *= 2;
            total_1 = total_2 - total_3;  // 4^k - 2^k
            total_1 *= 9;           // 9(4^k - 2^k)
            total_1++;              // 9(4^k - 2^k) + 1
            sortGap_2[k] = (int) total_1;
         } else
         {
            n = k/2;
            // Intent: 2^(k+2) (2^(k+2) � 3 ) + 1
            total_1 = 0;
            total_2 = 2;
            for (i = 0; i < n + 1; i++)   // 2^(k+2)
               total_2 *= 2;
            total_1 = total_2 - 3;     // 2^(k+2) � 3 
            total_1 *= total_2;     // 2^(k+2) (2^(k+2) � 3 )
            total_1++;              // 2^(k+2) (2^(k+2) � 3 ) + 1
            sortGap_2[k] = (int) total_1;
         }
      }
      // Create user gap sequence
      for (k = 0; k < GAP_SEQUENCE_SIZE; k++)
      {
         total_1 = 3;
         for (i = 0; i < k; i++)
            total_1 *= 4.7;
         int intTotal = (int) total_1;
         sortGap_3[k] = (int) intTotal;
      }
      sortGap_3[0] = 1;
      /*
      printArray(sortGap_3);

      // Oops, creates a Hibbard gap sequence.
      for (k = 0; k < GAP_SEQUENCE_SIZE; k++)
      {
         total = 2;
         // 2^(i+1) - 1
         for (i = 0; i < k; i++)
            total *= 2;
         total--;
         sortGap_2[k] = (int) total;
      }
      */

      // Create arrays
      Integer[] intArray_0 = new Integer[ARRAY_SIZE];
      Integer[] intArray_1 = new Integer[ARRAY_SIZE];
      Integer[] intArray_2 = new Integer[ARRAY_SIZE];
      Integer[] intArray_3 = new Integer[ARRAY_SIZE];
      Integer[] intArray_4 = new Integer[ARRAY_SIZE];
      Integer[] intArray_5 = new Integer[ARRAY_SIZE];
      Integer[] intArray_6 = new Integer[ARRAY_SIZE];
      Integer[] intArray_7 = new Integer[ARRAY_SIZE];
      Integer[] intArray_8 = new Integer[ARRAY_SIZE];
      Integer[] intArray_9 = new Integer[ARRAY_SIZE];
      Integer[] intArray_10 = new Integer[ARRAY_SIZE];

      // Fill arrays with same random numbers
      // int random_int;
      for (k = 0; k < ARRAY_SIZE; k++)
      {
         // random_int = (int) (Math.random() * ARRAY_SIZE);
         // intArray_0[k] = random_int;
         // intArray_1[k] = random_int;
         // intArray_2[k] = random_int;
         // intArray_3[k] = random_int;
         // intArray_4[k] = random_int;
         intArray_0[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_1[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_2[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_3[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_4[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_5[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_6[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_7[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_8[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_9[k] = (int) (Math.random() * ARRAY_SIZE);
         intArray_10[k] = (int) (Math.random() * ARRAY_SIZE);
      }

      // ---------- Sorting --------------------------------------------------
      
      // Timing for shellSort1, implied gap sequence
//      arrayTest("Implied gap sequence:", intArray_1, intArray_0, null);

      // Timing for shellSort2, explicit gap sequence
//      arrayTest("Explicit gap sequence:", intArray_2, intArray_0, sortGap_1);

      // Timing for shellSort2 using Sedgewick's gap sequence
//      arrayTest("Sedgewick's gap sequence:", intArray_3, intArray_0, sortGap_2);

      // Timing for shellSort2 using User gap sequence
//      arrayTest("User gap sequence:", intArray_4, intArray_0, sortGap_3);
      arrayTest("User gap sequence:", intArray_1, intArray_1, sortGap_3);
      arrayTest("User gap sequence:", intArray_2, intArray_2, sortGap_3);
      arrayTest("User gap sequence:", intArray_3, intArray_3, sortGap_3);
      arrayTest("User gap sequence:", intArray_4, intArray_4, sortGap_3);
      arrayTest("User gap sequence:", intArray_5, intArray_5, sortGap_3);
      arrayTest("User gap sequence:", intArray_6, intArray_6, sortGap_3);
      arrayTest("User gap sequence:", intArray_7, intArray_7, sortGap_3);
      arrayTest("User gap sequence:", intArray_8, intArray_8, sortGap_3);
      arrayTest("User gap sequence:", intArray_9, intArray_9, sortGap_3);
      arrayTest("User gap sequence:", intArray_10, intArray_10, sortGap_3);

      // ---------- Equality Test --------------------------------------------
      if (TEST_SHOW_ELEMENTS || TEST_PASS_PRINT || TEST_SILENT)
         System.out.println("Testing for equality among arrays...");

      interval = ARRAY_SIZE / 10;
      for (k = 0; k < ARRAY_SIZE; k += interval)
      {
         // If the option to show each element is enabled
         if (TEST_SHOW_ELEMENTS)
         {
            System.out.println("\nAt position: " + k +
                  "\nintArray_1: " + intArray_1[k] +
                  "\nintArray_2: " + intArray_2[k] +
                  "\nintArray_3: " + intArray_3[k] +
                  "\nintArray_4: " + intArray_4[k]);
         }
         // If the option to simply print "pass" or "error" is enabled
         if (TEST_PASS_PRINT)
         {
            // All elements at position k are the same
            elements_equal =
                  (intArray_1[k].equals(intArray_2[k])) ==
                  (intArray_1[k].equals(intArray_3[k])) ==
                  (intArray_1[k].equals(intArray_4[k])) ==
                  true;
            errTest("Equal at " + k + ": " + elements_equal, elements_equal);
         }
      }
      
      // If silent testing is enabled
      if (TEST_SILENT)
      {
         interval = 1;
         for (k = 0; k < ARRAY_SIZE && noProblem; k++)
         {
            elements_equal =
                  (intArray_1[k].equals(intArray_2[k])) ==
                  (intArray_1[k].equals(intArray_3[k])) ==
                  (intArray_1[k].equals(intArray_4[k])) ==
                  true;
            if (!elements_equal)
            {
               System.out.println(
                     "\nError at position: " + k +
                     "\nintArray_1: " + intArray_1[k] +
                     "\nintArray_2: " + intArray_2[k] + 
                     "\nintArray_3: " + intArray_3[k] + 
                     "\nintArray_4: " + intArray_4[k]);
               noProblem = false;
               break;
            }
         }
         if (noProblem) System.out.println("Tested elements are equal");
      }
   }

   /**
    * Runs an array test. The first run will sort the array passed in, rest of
    * the tests are only to calculate times.
    * @param prompt prints message to console explaining the test
    * @param sort_array the array which will be sorted on first run
    * @param template_array array to use for run time tests. Will be copied and
    *           used to run several consecutive run time tests.
    * @param gap_sequence the gap sequence to use, if any. Set null if no gap
    *           sequence.
    */
   private static void arrayTest(String prompt, Integer[] sort_array,
         Integer[] template_array, int[] gap_sequence)
   {
      int k;
      Integer[] arrayClone = new Integer[ARRAY_SIZE];
      
      if (PRINT_AVERAGE_EXTRAS) System.out.println(prompt);
      for (k = 0; k < total_runs; k++)
      {
         // Sort original array on first run for consistency test
         if (k == 0)
         {
            if (gap_sequence == null)  // If a gap sequence was not passed in
               shellSort1(sort_array);
            else
               shellSort2(sort_array, gap_sequence);
         }
         // Copy template_array to prevent data from being altered
         System.arraycopy(template_array, 0, arrayClone, 0, ARRAY_SIZE);
         // Time the operation
         start_time = System.nanoTime();
         // If a gap sequence was not passed in
         if (gap_sequence == null)
            shellSort1(arrayClone);
         else
            shellSort2(arrayClone, gap_sequence);
         end_time = System.nanoTime();
         // Record the run times
         recorded_times[k] = end_time - start_time;
         if (PRINT_EACH_RUN)
            System.out.println(tidy.format((end_time - start_time) / 1e9));
      }
      average_run_times(recorded_times);
   }

   /**
    * Finds and prints the average of the runtime array, excluding first two
    * elements
    * @param run_times array that contains the run times
    */
   private static void average_run_times(long[] run_times)
   {
      long total = 0;
      int k;
      // Find the total of all elements in the array, excluding first element
      for (k = 2; k < total_runs; k++)
         total += run_times[k];
      // Find the average (excluding first two elements) and print
      if (PRINT_AVERAGE_EXTRAS) System.out.print("Average: ");
      System.out.println(tidy.format((total /= (total_runs - 2)) / 1e9));
      if (PRINT_AVERAGE_EXTRAS) System.out.println();
   }

   /**
    * Prints out a message along with "pass" or "error" depending on the
    * condition. Used to shorten and simplify testing code.
    * @param text message to print to console, should describe what the test is
    * @param intent boolean representing the intended result of the test. For
    *           this method to work, the intended result should return true.
    *           That represents the idea that, for an operation's result,
    *           "yes, this is what I want to get from the operation"
    */
   private static void errTest(String text, boolean intent)
   {
      // Prints text
      if (PRINT_TEST_TITLE) System.out.println(text);
      // Prints pass or fail to tell client if the result is the intended result
      System.out.println((intent == true) ? "  > Pass" : "!!> Error");
   }

   /**
    * Print contents of an array
    * @param arr array to be printed
    */
   private static <E extends Comparable<? super E>> void printArray(E[] arr)
   {
      int k, size = arr.length;

      for (k = 0; k < size; k++)
         System.out.print("[" + arr[k] + "] ");
      System.out.println();
   }

   /**
    * Print contents of an array
    * @param arr array to be printed
    */
   private static <E extends Comparable<? super E>> void printArray(int[] arr)
   {
      int k, size = arr.length;

      for (k = 0; k < size; k++)
         System.out.print("[" + arr[k] + "] ");
      System.out.println();
   }
}

/*
 * 1. Why do you think Shell's gap sequence implied by shellSort1() gives a
 * different timing result than the explicit array described above and passed to
 * shellSort2()?
 *    I believe the reason is that the implicit sequence is "custom tailored"
 *    for each array passed in while the explicit array is not. This produces 
 *    numbers that are quite unique for the implicit sequence. All the numbers
 *    in the explicit sequence are also multiples of two. In a massive array,
 *    that becomes a waste of time as a higher number of comparisons will not
 *    result in a swap. Therefore, the comparison with a gap of two becomes a
 *    great big waste of time.
 * 
 */

/*
 * Array Size: size, in thousands, of the array tested i.e. 10k = 10,000
 *             elements in the array
 * Implicit, Explicit, Sedgewick's, and User: the type of gap sequence
 * 
 * Note that all times shown are averages, in seconds. These averages were
 * computed in the program by recording several run times. The average for all,
 * excluding the first two times, was then calculated.
 * 
 * This is to deal with two problems that result in faulty data:
 * 1. Minute variations in a computer's speed
 * 2. First run time recorded is consistently higher than normal
 * 
 * The times for Array Sizes 10k to 40k are averages of 175 runs. For the
 * purpose of conserving time, run times for 80k to 320k are averages of 50
 * runs.

               Implicit    Explicit    Sedgewick's    User
Array Size
10k            0.002615    0.004562    0.002384       0.00223
20k            0.008913    0.012654    0.00622        0.006333
40k            0.043887    0.062917    0.031845       0.025613
80k            0.118097    0.178716    0.103096       0.078363
160k           0.258566    0.478665    0.194232       0.160853
320k           0.64441     1.261435    0.444968       0.369933


*/
