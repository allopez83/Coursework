package assignment03.export;

import java.text.NumberFormat;
import java.util.Locale;

public class MatrixMultiply
{
   // Also known as the variable 'M'
   private static final int MAT_SIZE = 5;

   private static double[][] mat_1, mat_result, mat_transpose;

   public static void main(String[] args)
   {
      System.out.println("Begin\n");

      // Initialize matrices
      mat_1 = new double[MAT_SIZE][MAT_SIZE];
      mat_result = new double[MAT_SIZE][MAT_SIZE];
      mat_transpose = new double[MAT_SIZE][MAT_SIZE];

      // Assign values
      double increase = 0.1;
      // Row
      for (int row = 0; row < MAT_SIZE; row++)
      {
         // Column
         for (int col = 0; col < MAT_SIZE; col++)
         {
            mat_1[row][col] = increase;
            increase += (double) 0.1;
         }
      }

      matrixMultiply(mat_1, mat_1, mat_result);
      System.out.println(mat_result[0][0]);
      System.out.println(mat_1[1][0]);

      matrixTranspose(mat_1, mat_transpose);

      // Print Original
      matrixShow(mat_1, 0, MAT_SIZE);
      // Print Results
      matrixShow(mat_result, 0, MAT_SIZE);
      // Print Transposed
      matrixShow(mat_transpose, 0, MAT_SIZE);

      System.out.println("\nEnd");
   }

   /**
    * Multiplies two matrices and stores the result into a third matrix. The
    * matrices are assumed to be the same size.
    * @param mat_a first matrix to be multiplied
    * @param mat_b second matrix to be multiplied
    * @param mat_c matrix that results will be stored in
    */
   public static void matrixMultiply(double[][] mat_a, double[][] mat_b,
         double[][] mat_c)
   {
      if (mat_a.length != mat_b.length)
         throw new IndexOutOfBoundsException();
      // Values of individual matrix elements
      double val_A, val_B, val_C;
      // Counter for going through matrix elements
      int row, col, k;
      // Go through each row in mat_a
      for (row = 0; row < mat_a.length; row++)
      {
         // Go through each column in mat_b
         for (col = 0; col < mat_b.length; col++)
         {
            // Reset val_C to prevent bad data
            val_C = 0.0;
            // Go through all elements in a select row/column
            for (k = 0; k < mat_c.length; k++)
            {
               // Get value in matrix A
               val_A = mat_a[row][k];
               // Proceed if value is not zero
               if (val_A != 0.)
               {
                  // Get value in matrix B
                  val_B = mat_b[k][col];
                  // Proceed if value is not zero
                  if (val_B != 0.)
                     // Multiply and insert the results
                     val_C += val_A * val_B;
               }
            }
            // Enter values into the answer matrix
            mat_c[row][col] = val_C;
         }
      }
      // Done!
   }

   /**
    * Prints a specified excerpt of the matrix to the console.
    * @param mat_a matrix from which the excerpt will be taken from
    * @param start both the row and column location of the square excerpt, the
    *           very top left of the square is considered the start. I.e. a
    *           start value of 5 will put the top-left corner of the square on
    *           row 5, column 5
    * @param size length of each side of the square excerpt
    */
   public static void matrixShow(double[][] mat_a, int start, int size)
   {
      NumberFormat cleaner = NumberFormat.getInstance(Locale.US);
      cleaner.setMaximumFractionDigits(2);
      cleaner.setMinimumFractionDigits(2);
      double target;
      int row, col;
      // Go through each row
      for (row = start; row < size + start; row++)
      {
         // Go through columns
         for (col = start; col < size + start; col++)
         {
            // Get and print the result
            target = mat_a[row][col];
            String result = cleaner.format(target);
            System.out.printf("%6s", result);
         }
         System.out.println();
      }
      System.out.println();
   }

   public static void matrixTranspose(double[][] mat_orig, double[][] mat_trans)
   {
      double element;
      int row, col;
      // Go through each row
      for (row = 0; row < mat_orig.length; row++)
      {
         // Go through columns
         for (col = 0; col < mat_orig.length; col++)
         {
            // Switch elements
            element = mat_orig[row][col];
            mat_trans[col][row] = element;
         }
      }
   }
}

/*
Big-O time complexity estimate for matrix multiplication
M times - Go through all horizontal elements of first matrix
{
   M times - Go through all vertical elements of second matrix
   {
      {
      M times - Go through all elements of a certain row and column according to position
         Some multiplication and constant stuff
      }
   }
}

The algorithm consists mainly of three loops stacked together, resulting in:  
T(M) = O(M^3)

Big-Theta would be the same, although if one of the values is a zero, the
operation will be shortened. Still, the computer has to go through every element
in the array before testing if every element is zero. Thus, the runtime
complexity is the same:
T(M) = Theta(M^3)
*/

/*
1. What was the smallest M that gave you a non-zero time?

   An M of 20 would, on rare occasions, give a non-zero time. This is likely due
   to jitters in system resource usage. An M of 29 would give more consistent
   non-zero times.
   
2. What happened when you doubled M, tripled it, quadrupled it, etc?  Give
several M values and their times in a table.

   Taking the average of 200 consecutive runs, I got the following times:
   (format: size - time)
   100 - 0.0024065
   200 - 0.0161385
   300 - 0.0576485
   400 - 0.151393
   500 - 0.317525
   600 - 0.5713275
   700 - 0.91402
   800 - 1.447419
   900 - 2.102484 (average of 100 runs)
   1000 - 3.10248 (average of 10 runs)
   Data was collected from processing 5.0% full matrices.
   Initially, the run time increases at a fast rate: the time from 100 to 200
   is multiplied by 6. But it quickly stabilizes, and the times is multiplied by
   roughly 1.5.
   A better result can be seen by doubling the size each time. In this case, the
   time is more consistant. The time from 200 to 400, to 800, to 1600 and finally
   to 3200 can be predicted by multiplying the time by 8.
   
3. How large an M can you use before the program refuses to run (exception or
run-time error due to memory overload) or it takes so long you can't wait for
the run?

   I stopped at an M of 6400 because I didn't want to wait. 3200 took over a
   minute, so 6400 would have taken roughly ten minutes.
   
4. How did the data agree or disagree with your original time complexity
estimate?

   According to my recorded data, the time increased at varying rates. My
   prediction, where M*2 would mean Time*2^3, was somewhat adhered to.
 */