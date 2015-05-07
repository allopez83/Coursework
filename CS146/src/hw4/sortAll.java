package hw4;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public class sortAll
{
    private static final int
    // Used to take averages of run times, also determines number of runs
    // Should be more than the value of EXCLUSION variable
            TOTAL_RUNS = 20,
            // Size of the array to be sorted
            ARRAY_SIZE = 10000,
            // Number of initial runs to exclude from the average
            EXCLUDE = 5;

    // Whether or not to print certain information; set as true to print.
    private static final boolean
    // Time the time of each individual sort; I recommend a low total_runs
            PRINT_EACH_RUN = false,
            // Prints the test prompt and nicely formats the run time average
            PRINT_PROMPT = true,
            // Includes "Average: " prefix to output
            PRINT_AVERAGE_EXTRAS = false,
            // Prints the unsorted array, DO NOT TURN ON FOR LARGE ARRAYS
            PRINT_ARRAY_ORIG = false,
            // Prints the sorted array, DO NOT TURN ON FOR LARGE ARRAYS
            PRINT_ARRAY_AFTER = false,
            // Prints size of array, total runs, and any other general test info
            PRINT_TEST_DETAILS = false;

    // Timer stuffs
    private static NumberFormat tidy = NumberFormat.getInstance(Locale.US);
    private static long start_time, end_time;
    private static long[] recordedTimes = new long[TOTAL_RUNS];
    
    private static int compares, moves;
    
    public static void main(String[] args)
    {
        // Preparation
        tidy.setMaximumFractionDigits(6);
        int i, j = ARRAY_SIZE;
        Integer[] ranTemplate = new Integer[ARRAY_SIZE];
        Integer[] incTemplate = new Integer[ARRAY_SIZE];
        Integer[] decTemplate = new Integer[ARRAY_SIZE];

        // Random
        for (i = 0; i < ARRAY_SIZE; i++)
        {
            ranTemplate[i] = (int) (Math.random() * ARRAY_SIZE);
        }
        // Increasing
        for (i = 0; i < ARRAY_SIZE; i++)
        {
            incTemplate[i] = i + 1;
        }
        // Decreasing
        for (i = 0; i < ARRAY_SIZE; i++)
        {
            decTemplate[i] = j;
            j--;
        }

        if (PRINT_ARRAY_ORIG) System.out.println("Random\n" + Arrays.toString(ranTemplate));
        if (PRINT_ARRAY_ORIG) System.out.println("Increasing\n" + Arrays.toString(incTemplate));
        if (PRINT_ARRAY_ORIG) System.out.println("Decreasing\n" + Arrays.toString(decTemplate) + "\n");

        if (PRINT_TEST_DETAILS)
            System.out.println("Size of array: " + ARRAY_SIZE + "\n"
                    + "Total runs to conduct: " + TOTAL_RUNS + "\n");

        // Dummy tests to solve "initial bad data issue"
        warmup(ranTemplate);
        warmup(ranTemplate);
        warmup(ranTemplate);

        // Actual tests
        
        // Quicksort
        quickTest("Quicksort-Random:", ranTemplate);
        quickTest("Quicksort-Increasing:", incTemplate);
        quickTest("Quicksort-Decreasing:", decTemplate);
        
        System.out.println();

        mergeTest("Mergesort-Random:", ranTemplate);
        mergeTest("Mergesort-Increasing:", incTemplate);
        mergeTest("Mergesort-Decreasing:", decTemplate);
        
        System.out.println();

        heapTest("Heapsort-Random:", ranTemplate);
        heapTest("Heapsort-Increasing:", incTemplate);
        heapTest("Heapsort-Decreasing:", decTemplate);

        System.out.println();
        
        shellSubOpTest("Supoptimal Shell Sort-Random:", ranTemplate);
        shellSubOpTest("Supoptimal Shell Sort-Increasing:", incTemplate);
        shellSubOpTest("Supoptimal Shell Sort-Decreasing:", decTemplate);
        
        System.out.println();
        
        insertionTest("Insertionsort-Random:", ranTemplate);
        insertionTest("Insertionsort-Increasing:", incTemplate);
        insertionTest("Insertionsort-Decreasing:", decTemplate);
        

        /*
        1. Quicksort
        2. Mergesort
        3. Heapsort
        4. Shellsort with suboptimal sequence,
        i.e., h starts at half the length of the array and is halved for each pass. Refer to Figure 7.4 in the
        textbook.
        5. Shellsort with optimal sequence proposed by Sedgewick,
        i.e., {1, 5, 19, 41, 109,…} Refer to page 278 of the textbook.
        6. Insertion Sort
        */
    }

    /**
     * Finds and prints the average of the runtime array, excluding first two
     * elements.
     * @param run_times array that contains the run times
     */
    private static void avgTime()
    {
        long total = 0;
        int k;
    
        // Find the total of all elements in the array, excluding initial few
        for (k = EXCLUDE; k < TOTAL_RUNS; k++)
            total += recordedTimes[k];
    
        // Find the average (excluding first few elements) and print
        if (PRINT_AVERAGE_EXTRAS) System.out.print("Average: ");
        total /= TOTAL_RUNS - EXCLUDE;
        double seconds = (total / 1e9);
        System.out.println(tidy.format(seconds) + " seconds\n");
    
        if (PRINT_AVERAGE_EXTRAS) System.out.println();
    }

    private static boolean ordered(Integer[] array)
    {
        for (int i = 1; i < array.length; i++)
            if (array[i].compareTo(array[i - 1]) < 0) return false;
        return true;
    }

    /**
     * Warmup run to solve "Initial bad data issue
     */
    private static void warmup(Integer[] template)
    {
        int i;
        Integer[] clone = new Integer[ARRAY_SIZE];

        for (i = 0; i < TOTAL_RUNS; i++)
        {
            System.arraycopy(template, 0, clone, 0, ARRAY_SIZE);
            quickSort(clone);
        }
    }

    /**
     * All test methods run an array speed test. THEY ARE THE SAME EXCEPT FOR
     * THE SORTING ALGORITHM USED. It takes an array, deep clones it, and stores
     * the time it took to sort it. This particular test is for quick sort.
     * @param prompt prints message to console explaining the test
     * @param template array to use for run time tests. It will be deep copied
     *            and used to run several consecutive run time tests. Original
     *            array will not be altered.
     */
    private static void quickTest(String prompt, Integer[] template)
    {
        compares = 0;
        moves = 0;
        Integer[] clone = new Integer[ARRAY_SIZE];

        if (PRINT_PROMPT) System.out.println(prompt);

        for (int i = 0; i < TOTAL_RUNS; i++)
        {
            System.arraycopy(template, 0, clone, 0, ARRAY_SIZE);

            // Time the operation
            start_time = System.nanoTime();
            quickSort(clone); // ACTION!
            end_time = System.nanoTime();

            // Integrity check
            if (!ordered(clone))
                System.out.println("NOT ORDERED");
            
            // Record the run times
            recordedTimes[i] = end_time - start_time;
            if (PRINT_EACH_RUN)
                System.out.println(tidy.format((end_time - start_time) / 1e9));

            clone = new Integer[ARRAY_SIZE];
        }

        if (PRINT_ARRAY_AFTER) System.out.println(Arrays.toString(clone));

        System.out.println("Comparisons: " + compares / TOTAL_RUNS);
        System.out.println("Moves: " + moves / TOTAL_RUNS);

        avgTime();
    }
    private static void mergeTest(String prompt, Integer[] template)
    {
        compares = 0;
        moves = 0;
        Integer[] clone = new Integer[ARRAY_SIZE];

        if (PRINT_PROMPT) System.out.println(prompt);

        for (int i = 0; i < TOTAL_RUNS; i++)
        {
            System.arraycopy(template, 0, clone, 0, ARRAY_SIZE);

            // Time the operation
            start_time = System.nanoTime();
            mergeSort(clone); // ACTION!
            end_time = System.nanoTime();

            // Integrity check
            if (!ordered(clone))
                System.out.println("NOT ORDERED");
            
            // Record the run times
            recordedTimes[i] = end_time - start_time;
            if (PRINT_EACH_RUN)
                System.out.println(tidy.format((end_time - start_time) / 1e9));
        }

        if (PRINT_ARRAY_AFTER) System.out.println(clone);

        System.out.println("Comparisons: " + compares / TOTAL_RUNS);
        System.out.println("Moves: " + moves / TOTAL_RUNS);

        avgTime();
    }
    private static void heapTest(String prompt, Integer[] template)
    {
        compares = 0;
        moves = 0;
        Integer[] clone = new Integer[ARRAY_SIZE];

        if (PRINT_PROMPT) System.out.println(prompt);

        for (int i = 0; i < TOTAL_RUNS; i++)
        {
            System.arraycopy(template, 0, clone, 0, ARRAY_SIZE);

            // Time the operation
            start_time = System.nanoTime();
            heapSort(clone); // ACTION!
            end_time = System.nanoTime();

            // Integrity check
            if (!ordered(clone))
                System.out.println("NOT ORDERED");
            
            // Record the run times
            recordedTimes[i] = end_time - start_time;
            if (PRINT_EACH_RUN)
                System.out.println(tidy.format((end_time - start_time) / 1e9));

            clone = new Integer[ARRAY_SIZE];
        }

        if (PRINT_ARRAY_AFTER) System.out.println(Arrays.toString(clone));

        System.out.println("Comparisons: " + compares / TOTAL_RUNS);
        System.out.println("Moves: " + moves / TOTAL_RUNS);

        avgTime();
    }
    private static void shellSubOpTest(String prompt, Integer[] template)
    {
        compares = 0;
        moves = 0;
        Integer[] clone = new Integer[ARRAY_SIZE];

        if (PRINT_PROMPT) System.out.println(prompt);

        for (int i = 0; i < TOTAL_RUNS; i++)
        {
            System.arraycopy(template, 0, clone, 0, ARRAY_SIZE);

            // Time the operation
            start_time = System.nanoTime();
            shellSortSubOp(clone); // ACTION!
            end_time = System.nanoTime();

            // Integrity check
            if (!ordered(clone))
                System.out.println("NOT ORDERED");
            
            // Record the run times
            recordedTimes[i] = end_time - start_time;
            if (PRINT_EACH_RUN)
                System.out.println(tidy.format((end_time - start_time) / 1e9));

            clone = new Integer[ARRAY_SIZE];
        }

        if (PRINT_ARRAY_AFTER) System.out.println(Arrays.toString(clone));

        System.out.println("Comparisons: " + compares / TOTAL_RUNS);
        System.out.println("Moves: " + moves / TOTAL_RUNS);

        avgTime();
    }
    private static void insertionTest(String prompt, Integer[] template)
    {
        compares = 0;
        moves = 0;
        Integer[] clone = new Integer[ARRAY_SIZE];

        if (PRINT_PROMPT) System.out.println(prompt);

        for (int i = 0; i < TOTAL_RUNS; i++)
        {
            System.arraycopy(template, 0, clone, 0, ARRAY_SIZE);

            // Time the operation
            start_time = System.nanoTime();
            insertionSort(clone); // ACTION!
            end_time = System.nanoTime();

            // Integrity check
            if (!ordered(clone))
                System.out.println("NOT ORDERED");
            
            // Record the run times
            recordedTimes[i] = end_time - start_time;
            if (PRINT_EACH_RUN)
                System.out.println(tidy.format((end_time - start_time) / 1e9));

            clone = new Integer[ARRAY_SIZE];
        }

        if (PRINT_ARRAY_AFTER) System.out.println(Arrays.toString(clone));

        System.out.println("Comparisons: " + compares / TOTAL_RUNS);
        System.out.println("Moves: " + moves / TOTAL_RUNS);

        avgTime();
    }

    /*
     * Sorting Algorithms
     */

    public static <E extends Comparable<? super E>> void insertionSort(E[] a)
    {
        int j, k, pos, array_size;
        E tmp;

        array_size = a.length;
        for (pos = 1; pos < array_size; pos++)
        {
            tmp = a[pos];
            for (j = pos; j > 0; j--)
            {
                compares++;
            }
            for (k = pos; k > 0 && tmp.compareTo(a[k - 1]) < 0; k--)
            {
                a[k] = a[k - 1];
                moves++;
            }
            a[k] = tmp;
        }
    }

    /**
     * Shell sort, sorts an array from small to big. Gap is determined by array
     * size divided by two continuously.
     * @param a the array to be sorted
     */
    public static <E extends Comparable<? super E>> void shellSortSubOp(E[] a)
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
                for (k = pos; k >= gap; k -= gap)
                    compares++;
                for (k = pos; k >= gap && tmp.compareTo(a[k - gap]) < 0; k -= gap)
                {
                    moves++;
                    a[k] = a[k - gap];
                }
                a[k] = tmp;
            }
    }

    // mergesort and helpers -------------------------------------------
    // input array 1: client[left_pos] ... client[right_pos-1]
    // input array 2: client[right_pos] ... client[right_stop]
    // working[] array supplied by client to avoid local allocation
    protected static <E extends Comparable<? super E>> void merge(E[] client,
            E[] working, int left_pos, int right_pos, int right_stop)
    {
        int left_stop, working_pos, array_size;

        working_pos = left_pos;
        left_stop = right_pos - 1;
        array_size = right_stop - left_pos + 1;

        // as soon as we reach the end of either input array, stop
        while (left_pos <= left_stop && right_pos <= right_stop)
        {
            if (client[left_pos].compareTo(client[right_pos]) < 0)
                working[working_pos++] = client[left_pos++];
            else
                working[working_pos++] = client[right_pos++];
            moves++;
            compares++;
        }

        // merge is over; copy the remainder of one or the other input array
        while (left_pos <= left_stop)
        {
            working[working_pos++] = client[left_pos++];
            moves++;
        }
        while (right_pos <= right_stop)
        {
            working[working_pos++] = client[right_pos++];
            moves++;
        }

        // copy back into client array
        for (; array_size > 0; array_size--, right_stop--)
        {
            client[right_stop] = working[right_stop];
            moves++;
        }
    }

    // mergesort internal function
    protected static <E extends Comparable<? super E>> void mergeSort(E[] a,
            E[] working, int start, int stop)
    {
        int right_start;

        if (stop - start < 1) return;

        right_start = (start + stop) / 2 + 1;
        mergeSort(a, working, start, right_start - 1);
        mergeSort(a, working, right_start, stop);
        merge(a, working, start, right_start, stop);
    }

    // mergesort public driver
    public static <E extends Comparable<? super E>> void mergeSort(E[] a)
    {
        if (a.length < 2) return;

        E[] working = (E[]) new Comparable[a.length];
        mergeSort(a, working, 0, a.length - 1);
    }

    // heapsort and helpers -------------------------------------------
    // percolateDown()
    protected static <E extends Comparable<? super E>> void percolateDown(
            E[] a, int hole, int array_size)
    {
        int child;
        E tmp;

        for (tmp = a[hole]; 2 * hole + 1 < array_size; hole = child)
        {
            child = 2 * hole + 1;
            // if 2 children, get the GREATER of the two (because MAX heap)
            compares++;
            if (child < array_size - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            compares++;
            if (tmp.compareTo(a[child]) < 0) // MAX heap, not min heap
            {
                a[hole] = a[child];
                moves++;
            }
            else
                break;
        }
        a[hole] = tmp;
    }

    // heapsort public driver
    public static <E extends Comparable<? super E>> void heapSort(E[] a)
    {
        int k, array_size;
        E temp;

        // order the array using percolate down
        array_size = a.length;
        for (k = array_size / 2; k >= 0; k--)
            percolateDown(a, k, array_size);

        // now remove the max element (root) and place at end of array
        for (k = array_size - 1; k > 0; k--)
        {
            // "remove" by placing at end of array
            temp = a[0];
            a[0] = a[k];
            a[k] = temp;
            moves++;
            percolateDown(a, 0, k); // k represents the shrinking array size
        }
    }

    // quicksort and helpers -------------------------------------------
    /**
     * median3 sorts a[left], a[center] and a[right]. it leaves the smallest in
     * a[left], the largest in a[right] and median (the pivot) is moved
     * "out-of-the-way" in a[right-1]. (a[center] has what used to be in
     * a[right-1])
     * @param a
     * @param left
     * @param right
     * @return
     */
    protected static <E extends Comparable<? super E>> E median3(E[] a,
            int left, int right)
    {
        int center;
        E tmp;

        // swaps are done in-line for speed; each compound line is a swap
        center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            { tmp = a[center]; a[center] = a[left]; a[left] = tmp; }
        if (a[right].compareTo(a[left]) < 0)
            { tmp = a[right]; a[right] = a[left]; a[left] = tmp; }
        if (a[right].compareTo(a[center]) < 0)
            { tmp = a[right]; a[right] = a[center]; a[center] = tmp; }

        compares++;
        moves++;

        tmp = a[center]; a[center] = a[right - 1]; a[right - 1] = tmp;

        moves++;

        return a[right - 1];
    }

    protected static int QS_RECURSION_LIMIT = 3;

    protected static <E extends Comparable<? super E>> void quickSort(E[] a,
            int left, int right)
    {
        E pivot, tmp;
        int i, j;

        if (left + QS_RECURSION_LIMIT <= right)
        {
            pivot = median3(a, left, right);
            for (i = left, j = right - 1;;)
            {
                while (a[++i].compareTo(pivot) < 0)
                    compares++;
                while (pivot.compareTo(a[--j]) < 0)
                    compares++;
                if (i < j)
                {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                    moves++;
                }
                else
                    break;
            }

            // restore pivot
            tmp = a[i];
            a[i] = a[right - 1];
            a[right - 1] = tmp;
            moves++;

            // recursive calls on smaller sub-groups
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
        else
            // non-recursive escape valve - insertion sort
            insertionSort(a, left, right);
    }

    // private insertion sort that works on sub-arrays --------------
    protected static <E extends Comparable<? super E>> void insertionSort(
            E[] a, int start, int stop)
    {
        int k, pos;
        E tmp;

        // we are not testing for ranges to keep times down - private so ok
        for (pos = start + 1; pos <= stop; pos++)
        {
            tmp = a[pos];
            moves++;
            for (k = pos; k > 0 && tmp.compareTo(a[k - 1]) < 0; k--, compares++)
            {
                a[k] = a[k - 1];
                moves++;
            }
            a[k] = tmp;
            moves++;
        }
    }

    // public quicksort
    public static <E extends Comparable<? super E>> void quickSort(E[] a)
    {
        quickSort(a, 0, a.length - 1);
    }
}
