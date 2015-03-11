package assignment08.export;

import java.text.NumberFormat;
import java.util.Locale;
import cs_1c.*;

/**
 * Assignment #8: Quicksort testing
 * 
 * @author HW
 *
 */
public class RecursionAnalysis
{
   /*
    * User-input parameter(s); changing these will result in a change planned
    * and intended by the developer, please follow any provided instructions or
    * the test program might crash/fail/blow up the world (Ok, maybe not global
    * destruction). Thank you!
    */
   private static final int
         // Used to take averages of run times, also determines number of runs
         // Should be more than the value of EXCLUSION variable
         TOTAL_RUNS = 125,
         // Size of the array to be sorted
         ARRAY_SIZE = 80000,
         // Value to start the limit from
         LIMIT_START = 1,
         // Value to end the limit at, generally 300
         LIMIT_END = 50,
         // Amount to increment the recursion limit
         LIMIT_INCREMENT = 1,
         // Number of initial runs to exclude from the average
         EXCLUDE = 2;
   // Whether or not to print certain information; set as true to print.
   private static final boolean
         // Time the time of each individual sort; I recommend a low total_runs
         PRINT_EACH_RUN = false,
         // Print "Average: ", labeling the average time when printing
         // Also prints name of array test being conducted
         PRINT_AVERAGE_EXTRAS = false,
         // Prints the unsorted array, DO NOT TURN ON FOR LARGE ARRAYS
         PRINT_ARRAY_BEFORE = false,
         // Prints the sorted array, DO NOT TURN ON FOR LARGE ARRAYS
         PRINT_ARRAY_AFTER = false,
         // Prints a short description on same line as average
         PRINT_SHORT = false,
         // Prints size of array, total runs, and any other general test info
         PRINT_TEST_DETAILS = true;

   /*
    * The "do not change" parameters. These can actually be changed, but they're
    * not exactly intended to be changed. And there's probably nothng much to
    * change.
    */
   // Timer stuffs
   private static NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   private static long start_time, end_time;
   // Stores the times for each sort
   private static long[] recorded_times = new long[TOTAL_RUNS];

   /**
    * The main, launches the test itself
    * 
    * Note that it will be assumed that the quick sort algorithm is 100%,
    * entirely, completely, and always flawless. There will be no integrity
    * checks to make sure the contents of the sorted arrays match.
    */
   public static void main(String[] args)
   {
      // ---------- Preparations----------------------------------------------
      // Number of decimals for timer to show
      tidy.setMaximumFractionDigits(6);
      // Used as counters in 'for loops'
      int i, k, random_int;
      Integer[] intArray = new Integer[ARRAY_SIZE];

      // Fill arrays with same random numbers
      for (k = 0; k < ARRAY_SIZE; k++)
      {
         random_int = (int) (Math.random() * ARRAY_SIZE);
         intArray[k] = random_int;
      }

      if (PRINT_TEST_DETAILS) System.out.println(
            "Size of array: " + ARRAY_SIZE + "\n"
            + "Total runs to conduct: " + TOTAL_RUNS + "\n");

      // ---------- Sorting ---------------------------------------------------
      // Dummy tests to solve "initial bad data issue"
      arrayTest("Dummy test #1, limit of 2:", 2, intArray);
      arrayTest("Dummy test #2, limit of 2:", 2, intArray);
      arrayTest("Dummy test #3, limit of 2:", 2, intArray);
      // Actual tests
      System.out.println("Begin 'actual' tests");
      for (i = LIMIT_START; i <= LIMIT_END; i += LIMIT_INCREMENT)
         arrayTest("Limit of " + i + " test:", i, intArray);
   }


   /**
    * Runs an array speed test. It takes an array, deep clones it, and stores
    * the time it took to sort it. This particular test is for quick sort.
    * @param prompt prints message to console explaining the test
    * @param recursion_limit sets the recursion limit for this test
    * @param template_array array to use for run time tests. It will be copied
    *           and used to run several consecutive run time tests. Original
    *           array will not be altered.
    */
   private static void arrayTest(String prompt, int recursion_limit,
         Integer[] template_array)
   {
      // Initial preparations stuff
      int k;
      Integer[] array_clone = new Integer[ARRAY_SIZE];
      FHsort.setRecursionLimit(recursion_limit);
      if (PRINT_AVERAGE_EXTRAS) System.out.println(prompt);
      if (PRINT_ARRAY_BEFORE) printArray(template_array);

      for (k = 0; k < TOTAL_RUNS; k++)
      {
         // Copy template_array to prevent data from being altered
         System.arraycopy(template_array, 0, array_clone, 0, ARRAY_SIZE);

         // Time the operation
         start_time = System.nanoTime();
         FHsort.quickSort(array_clone); // ACTION!
         end_time = System.nanoTime();

         // Record the run times
         recorded_times[k] = end_time - start_time;
         if (PRINT_EACH_RUN)
            System.out.println(tidy.format((end_time - start_time) / 1e9));
      }
      if (PRINT_ARRAY_AFTER) printArray(array_clone);
      if (PRINT_SHORT) System.out.print("Limit of " + recursion_limit + ": ");
      average_run_times();
   }

   /**
    * Finds and prints the average of the runtime array, excluding first two
    * elements.
    * @param run_times array that contains the run times
    */
   private static void average_run_times()
   {
      long total = 0;
      int k;
      // Find the total of all elements in the array, excluding first element
      for (k = EXCLUDE; k < TOTAL_RUNS; k++)
         total += recorded_times[k];
      // Find the average (excluding first few elements) and print
      if (PRINT_AVERAGE_EXTRAS) System.out.print("Average: ");
      System.out.println(tidy.format((total /= (TOTAL_RUNS - EXCLUDE)) / 1e9));
      if (PRINT_AVERAGE_EXTRAS) System.out.println();
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
}

/*
 * 1. "Comment on the results, describing the range that seems to be minimal"
 *       As expected, the optimal limit from my data is incredibly close to
 *       the minimum. I theorize that it actually is the minimum, 2, although
 *       data does not agree. I think this is because of the phenomenon I
 *       encountered where the first run time is always higher than the rest.
 *       Still, the data says that the optimal time is 20, so 20 it is.
 *       
 *       However, in more detailed runs incrementing by 1, I discovered the
 *       position to be split between 26, 27, and 31. There are other good
 *       contenders, but the winner varies with each run. I conclude from these 
 *       detailed runs that the optimal limit is roughly 24-40.
 *       
 *       Generally, the trend is for smaller limits to have better run times.
 *       But I cannot explain why the array size 80,000 doesn't follow this.
 *       The run time suddenly increases when approaching a limit of 200, but
 *       at 200, the run time is faster than the rest. Then it resumes the
 *       trend and decreases until reaching a limit of 300. This has happened
 *       for two consecutive runs.
 *       
 *       Also, I noticed that the run times have a very small  difference, even
 *       if the quick sort limit is incremented by ten each time.
 * 
 * 2. Table for run times
 * Refer to either "Week 8 spreadsheet snapshot.png", a picture uploaded with
 * the assignment, or the table inserted below. I suggest the picture since
 * it's more complete and easier to understand.
 * 
 * ---------- Picture --------------------------------------------------------
 * 
 * Left-side: There are two columns, "Limit" and "Time Difference". "Limit" has
 *            a list of the limits that were tested, applying to its entire row
 *            across the whole spreadsheet. "Time Difference" contains values
 *            representing the range between minimum and maximum recorded time
 *            for their corresponding limit values.
 * 
 * Center and Right-side: Here, there are several columns, each containing the
 *                        times for certain array sizes which are displayed at
 *                        the top. The array sizes are in thousands.
 *                        
 * Note: The spreadsheet cells containing run times are color coded. It
 * ranges from green for a lower value, to red for a larger value.
 * 
 * ---------- Table Provided Below -------------------------------------------
 * X-Axis: Array size, in thousands, of the array tested, i.e. 80k = 80,000
 *         elements in the array
 *         
 * Y-Axis: Limit that was given to quick sort
 * 
 * Note: The limit generally increments by 50 due to the similarity between
 * the individual run times. 

   Array Size  20          40          80       160         320         640         1280        2560
Limit
2           0.005203    0.012800   0.034666  0.060600    0.134827    0.310700    0.713466    1.778486
20          0.004744    0.012202   0.033216  0.052607    0.130524    0.303343    0.691755    1.65133
50          0.005048    0.012907   0.033415  0.053392    0.129484    0.306577    0.696874    1.688379
100         0.005371    0.013490   0.035899  0.059068    0.139797    0.319663    0.737729    1.81957
150         0.006058    0.014969   0.038734  0.066047    0.152643    0.348318    0.790275    1.846474
200         0.006899    0.016787   0.032491  0.071025    0.166561    0.364607    0.841939    1.949334
250         0.007211    0.018584   0.036307  0.077939    0.178564    0.391943    0.898020    2.066836
300         0.008425    0.021370   0.039257  0.102529    0.192896    0.419264    0.953027    2.186015

*/
