package assignment03.workspace;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;

import assignment03.workspace.SparseMat.MatNode;

import cs_1c.FHlinkedList;

public class Foothill
{
   /*
    * Determines whether or not to print out extra data, or just the run time.
    * True will print the extra data, recommended when only doing one run. False
    * will just show the time, recommended when running more than once at a
    * time.
    */
   private static final boolean MAT_PRINT = false, SPARSE_MAT_PRINT = false;

   public static void main(String[] args) throws Exception
   {
      testMat();
      //testSparseMat();
   }

   private static void testMat()
   {
      // Length of a side of the array (it's a square shaped array)
      int mat_size = 200;
      // Number of runs that the program will make
      int runs = 20;
      // How many percent you want i.e. 10% would be a value of 10
      double percentage = 1.0;

      // Used for adding elements
      int rand_row, rand_col;
      double target;
      // A counter for number of elements that have been converted
      int changed;
      // Number of elements that need to be changed to get desired percentage
      double small_percent;
      // A counter that will increment to keep track of occupation
      double counter = 0.;
      // Stuff for the timer
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long start_time, end_time;

      // Non-sparse matrices
      double[][] mat, mat_ans;

      for (int r = 0; r < runs; r++)
      {
         // Allocate matrices
         mat = new double[mat_size][mat_size];
         mat_ans = new double[mat_size][mat_size];

         if (MAT_PRINT)
            System.out.println("Adding elements...");
         // generate small% of non-default values bet 0 and 1
         // small_percent is the number of elements that makes up n%, in this
         // case, 5%
         small_percent = mat_size / (100 / percentage) * mat_size; // 5%
         for (changed = 0; changed < small_percent;)
         {
            // Find a random location
            rand_row = (int) (Math.random() * mat_size);
            rand_col = (int) (Math.random() * mat_size);
            target = mat[rand_row][rand_col];
            // If not set, then set value
            if (target == 0.)
            {
               mat[rand_row][rand_col] = Math.random();
               changed++;
            }
         }

         if (MAT_PRINT)
            System.out.println("Checking occupation...");
         // Check total occupied percentage
         // Row
         if (MAT_PRINT)
            for (int row = 0; row < mat_size; row++)
            {
               // Column
               for (int col = 0; col < mat_size; col++)
               {
                  if (mat[row][col] != 0.0)
                  {
                     counter += 1.0;
                  }
               }
            }
         if (MAT_PRINT)
            System.out.println("Total changed elements: " + counter
                  + "\nTotal elements: " + mat_size * mat_size
                  + "\nPercentage: " + (counter / (mat_size * mat_size)) * 100.
                  + "\nPlanned percentage: " + percentage + "%");

         // 10x10 submatrix in lower right
         if (MAT_PRINT)
            MatrixMultiply.matrixShow(mat, mat_size - 10, 10);

         if (MAT_PRINT)
            System.out.println("Multiplying...");
         start_time = System.nanoTime();
         MatrixMultiply.matrixMultiply(mat, mat, mat_ans);
         end_time = System.nanoTime();

         if (MAT_PRINT)
            MatrixMultiply.matrixShow(mat_ans, mat_size - 10, 10);

         if (MAT_PRINT)
            System.out.println("Size = " + mat_size
                  + " Mat. Mult. Elapsed Time: "
                  + tidy.format((end_time - start_time) / 1e9) + " seconds.");
         else
            System.out.println(tidy.format((end_time - start_time) / 1e9));
      }
   }

   private static void testSparseMat()
   {
      // Length of a side of the array (it's a square shaped array)
      int mat_size = 800;
      // Number of runs that the program will make
      int runs = 10;
      // How many percent you want i.e. 10% would be a value of 10
      double percentage = 5.0;

      // Used for adding elements
      int rand_row, rand_col;
      double target;
      NumberFormat twoDecimal = NumberFormat.getInstance(Locale.US);
      twoDecimal.setMaximumFractionDigits(2);
      // A counter for number of elements that have been converted
      int changed;
      // Number of elements that need to be changed to get desired percentage
      double small_percent;
      // A counter that will increment to keep track of occupation
      double counter = 0.;
      // Stuff for the timer
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long start_time, end_time;

      for (int r = 0; r < runs; r++)
      {
         // Create the matrices
         SparseMatWMult mat_a = new SparseMatWMult(mat_size, mat_size, 0.),
                        mat_b = new SparseMatWMult(mat_size, mat_size, 0.),
                        mat_result = new SparseMatWMult(mat_size, mat_size, 0.);

         if (SPARSE_MAT_PRINT)
            System.out.println("Adding elements...");
         // Matrix A
         // Find how many elements need to be changed
         small_percent = mat_size / (100. / percentage) * mat_size;
         // Change elements until requirement reached
         for (changed = 0; changed < small_percent;)
         {
            // Find a random location
            rand_row = (int) (Math.random() * mat_size);
            rand_col = (int) (Math.random() * mat_size);
            target = mat_a.get(rand_row, rand_col);
            // If not set, then set value
            if (target == 0.)
            {
               String insert = twoDecimal.format(Math.random());
               Double value = Double.parseDouble(insert);
               mat_a.set(rand_row, rand_col, value);
               changed++;
            }
         }
         // Matrix B
         // Find how many elements need to be changed
         small_percent = mat_size / (100. / percentage) * mat_size;
         // Change elements until requirement reached
         for (changed = 0; changed < small_percent;)
         {
            // Find a random location
            rand_row = (int) (Math.random() * mat_size);
            rand_col = (int) (Math.random() * mat_size);
            target = mat_b.get(rand_row, rand_col);
            // If not set, then set value
            if (target == 0.)
            {
               String insert = twoDecimal.format(Math.random());
               Double value = Double.parseDouble(insert);
               mat_b.set(rand_row, rand_col, value);
               changed++;
            }
         }

         if (SPARSE_MAT_PRINT)
         {
            System.out.println("Checking occupation of A...");
            // Check total occupied percentage
            // Row
            for (int row = 0; row < mat_size; row++)
            {
               // Column
               for (int col = 0; col < mat_size; col++)
               {
                  if (mat_a.get(row, col) != 0.)
                  {
                     counter += 1.0;
                  }
               }
            }
            System.out.println(" ----- Matrix A ----- "
                  + "\nTotal changed elements: " + counter
                  + "\nTotal elements: " + mat_size * mat_size
                  + "\nPercentage: " + (counter / (mat_size * mat_size)) * 100.
                  + "\nPlanned percentage: " + percentage + "%");
         }

         if (SPARSE_MAT_PRINT)
         {
            counter = 0.;
            System.out.println("Checking occupation of B...");
            // Check total occupied percentage
            // Row
            for (int row = 0; row < mat_size; row++)
            {
               // Column
               for (int col = 0; col < mat_size; col++)
               {
                  if (mat_a.get(row, col) != 0.)
                  {
                     counter += 1.0;
                  }
               }
            }
            System.out.println(" ----- Matrix B ----- "
                  + "\nTotal changed elements: " + counter
                  + "\nTotal elements: " + mat_size * mat_size
                  + "\nPercentage: " + (counter / (mat_size * mat_size)) * 100.
                  + "\nPlanned percentage: " + percentage + "%");
         }

         // Show both matrices
         if (SPARSE_MAT_PRINT)
            mat_a.showSubSquare(mat_size - 10, 10);
         if (SPARSE_MAT_PRINT)
            mat_b.showSubSquare(mat_size - 10, 10);

         if (SPARSE_MAT_PRINT)
            System.out.println("Multiplying...");
         start_time = System.nanoTime();
         mat_result.matrixMultiply(mat_a, mat_b);
         end_time = System.nanoTime();

         if (SPARSE_MAT_PRINT)
            mat_result.showSubSquare(mat_size - 10, 10);

         if (SPARSE_MAT_PRINT)
            System.out.println("Size = " + mat_size
                  + " Mat. Mult. Elapsed Time: "
                  + tidy.format((end_time - start_time) / 1e9) + " seconds.");
         else
            System.out.println(tidy.format((end_time - start_time) / 1e9));
      }
   }
}



/*
// Set values
         { // First matrix
            int row = 0;
            Double value = 1.;
            for (int col = 0; col < mat_size; col++)
            {
               mat_a.set(row, col, value);
               value++;
            }
            row = 1;
            value = -1.;
            for (int col = 0; col < mat_size; col++)
            {
               mat_a.set(row, col, value);
               value--;
            }
            mat_a.set(2, 0, 1.0);
            mat_a.set(2, 1, 3.0);
            mat_a.set(2, 2, 1.0);
            mat_a.set(2, 3, 3.0);
            mat_a.set(2, 4, 1.0);

            mat_a.set(3, 0, 0.0);
            mat_a.set(3, 1, 1.0);
            mat_a.set(3, 2, 0.0);
            mat_a.set(3, 3, 1.0);
            mat_a.set(3, 4, 0.0);

            mat_a.set(4, 0, -1.0);
            mat_a.set(4, 1, -1.0);
            mat_a.set(4, 2, -1.0);
            mat_a.set(4, 3, -1.0);
            mat_a.set(4, 4, -1.0);
         }
         { // Second matrix
            mat_b.set(0, 0, 2.0);
            mat_b.set(0, 1, 1.0);
            mat_b.set(0, 2, 5.0);
            mat_b.set(0, 3, 0.0);
            mat_b.set(0, 4, 2.0);

            mat_b.set(1, 0, 1.0);
            mat_b.set(1, 1, 4.0);
            mat_b.set(1, 2, 3.0);
            mat_b.set(1, 3, 2.0);
            mat_b.set(1, 4, 7.0);

            for (int col = 0; col < mat_size; col++)
               mat_b.set(2, col, 4.0);

            mat_b.set(3, 0, 7.0);
            mat_b.set(3, 1, 1.0);
            mat_b.set(3, 2, -1.0);
            mat_b.set(3, 3, -1.0);
            mat_b.set(3, 4, -1.0);

            mat_b.set(4, 0, 0.0);
            mat_b.set(4, 1, 0.0);
            mat_b.set(4, 2, 8.0);
            mat_b.set(4, 3, -1.0);
            mat_b.set(4, 4, -6.0);
         }
*/