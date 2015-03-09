package assignment03.workspace;

import java.util.Iterator;

import cs_1c.FHarrayList;
import cs_1c.FHlinkedList;

public class SparseMatWMult extends SparseMat<Double>
{
   /**
    * Calls the SparseMat superclass to create a SparseMatrix using the given
    * parameters.
    * @param num_rows rows that the matrix will have, starting from zero
    * @param num_cols columns that the matrix will have, starting from zero
    * @param default_v value of elements non-specified by the user
    */
   SparseMatWMult(int num_rows, int num_cols, Double default_v)
   {
      super(num_rows, num_cols, default_v);
   }

   /**
    * Multiplies two sparse matrices, inserting the result into the current
    * sparse matrix. The two matrices should be square, the same size, and at
    * least have a size of 1. If the matrix is of a size smaller than 1, the
    * method will return false. True will be returned if the operation succeeds.
    * Note that the default value will be forcibly changed to 0.0 so the
    * operation will work correctly.
    * @param mat_a first matrix to be multiplied
    * @param mat_b second matrix to be multiplied
    * @return true if operation succeeds, false if either matrix has size of
    *         that is less than 1
    */
   boolean matrixMultiply(SparseMatWMult mat_a, SparseMatWMult mat_b)
   {
      // If the column or row size of either matrix is less than one
      if (mat_a.col_size * mat_a.row_size < 1
            || mat_b.col_size * mat_b.row_size < 1)
         return false;

      // Values of individual matrix elements
      Double val_B, val_C;
      MatNode node_A;
      // These are used to go through the matrices
      int row, col;
      // Size of a single side of one of the matrices
      int mat_size = mat_a.row_size;
      // Iterator is to go through a specified row
      Iterator<MatNode> iter;
      // Used for the iterator
      FHlinkedList<MatNode> current_row;
      
      // Set values
      default_val = 0.;
      row_size = mat_size;
      col_size = mat_size;
      // Rebuild the matrix
      rows = new FHarrayList<FHlinkedList<MatNode>>(row_size);
      for (int i = 0; i < row_size; i++)
         rows.add(i, new FHlinkedList<MatNode>());
      
      // Go 'horizontally' through each row in mat_a
      for (row = 0; row < mat_size; row++)
      {
         // Keep track of the row, iterator will need it
         current_row = mat_a.rows.get(row);
         // Go 'vertically' through each column in mat_b
         for (col = 0; col < mat_size; col++)
         {
            // Reset val_C to prevent bad data
            val_C = default_val;
            // Create iterator to go through the linked list on current row
            iter = current_row.iterator();
            // Go through all nodes in the linked list
            while (iter.hasNext())
            {
               // Get the next node in mat_a
               node_A = iter.next();
               // If the data is not default
               if (node_A.data != default_val)
               {
                  // Get a value from mat_b
                  val_B = mat_b.get(node_A.col, col);
                  if (val_B != default_val)
                  {
                     // Multiply the results
                     val_C += node_A.data * val_B;
                  }
               }
            }
            // Insert the multiplied value
            this.set(row, col, val_C);
         }
      }
      
      // Multiply operation successful!
      return true;
   }
}

/*
1. Are the times longer or shorter than for dynamic matrices?

   With the sparse matrices, run time is incredibly dependent on how populated the
   matrix is. Even with the same 5.0% population, the run time was noticeably
   higher than with dynamic matrices. However, with lower populations such as
   1.0% or below, the sparse matrix wins because dynamic matrices are not as
   affected, and thus, are not significantly faster due to the lower population.
   
2. Are the growth rates larger or smaller?  By how much?

   Growth rate is significantly higher, at roughly 10 times the previous when
   doubling the matrix sizes. With dynamic arrays, it is multiplied by 8 each time.

3. Create a table and answer the same questions as before.

   These times are from matrices with the given size filled to 5.0%
   (size  time)
   25    0.000563
   50    0.001773
   100   0.005647
   200   0.056606
   400   1.233076
   800   13.444975
   1600  168.3253
   It can easilly be seen that the time increases faster than 8 times each time
   the size is doubled.

4. What was the largest M you could use here, and was the reason the same or
different than for dynamic arrays?

   Using the same 5.0% population setting as when testing the dynamic matrices,
   I could only get to 1600 instead of 6400 since the run time increased at a
   Significantly faster rate. The reason is the same as before: it would take
   too long.

5. Try different sparseness values: 1%  5%  10%  0.2%, and report how the growth
rates behave in each case.

   I noticed that, compared to dynamic matrices, 1.0% is the 'tipping point'
   where sparse matrices near or are faster than dynamic matrices. This is
   especially true for sizes less than about 400. The run time can be decreased
   to 10% of a dynamic matrix's time or less, but the gap is closed as the matrix
   grows to around 800 and above. Therefore, a 0.2% population will be lightning
   fast for sparse matrices, while 5.0% will slow it down, and 10.0% will destroy
   it.

*/

//(Two 800 by 800 matrices filled to 1%)
/* START OF OUTPUT --------------------------------
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0 0.91 0.65  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0 0.93  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.00.6045  0.0  0.0  0.0  0.0
0.168  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.00.41281.0997999999999999
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.00.3744
  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.056999999999999995  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.00.096

Size = 800 Mat. Mult. Elapsed Time: 0.824 seconds.

END OF OUTPUT -------------------------------- */

//(Two 1600 by 1600 matrices filled to 1%)
/* START OF OUTPUT --------------------------------
0.0  0.0  0.0  0.0  0.0  0.0  0.0 0.38  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.8  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.00.2304  0.0  0.0  0.0  0.0  0.0  0.00.6586  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.00.17820000000000003
0.033600000000000005  0.0  0.0  0.0  0.0  0.0  0.0  0.00.3825  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.00.29109999999999997  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.00.015  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.00.1026  0.0  0.0  0.0
0.00.0456  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.00.058199999999999995  0.00.1008  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.00.66750.6885  0.0

Size = 1600 Mat. Mult. Elapsed Time: 9.6285 seconds.
*/

//(Two 3200 by 3200 matrices filled to 1%)
/* START OF OUTPUT --------------------------------
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0 0.99
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0 0.63  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0 0.24  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0

0.018  0.0  0.0  0.0  0.0  0.0  0.0  0.00.0475  0.0
0.1003  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.00.05500000000000001
0.0  0.0  0.0  0.0  0.0  0.00.0044  0.0  0.00.248
0.0  0.00.1387  0.0  0.0  0.0  0.00.15800000000000003  0.0  0.0
0.8977999999999999  0.00.022199999999999998  0.0  0.0  0.0  0.0  0.0  0.00.48360000000000003
0.0  0.0  0.0  0.0  0.00.86259999999999990.052000000000000005  0.00.48230000000000006  0.0
0.00.241899999999999980.051300000000000005  0.0  0.0  0.0  0.00.1682  0.0  0.0
0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0  0.0
0.8336  0.0  0.0  0.0  0.0  0.00.8351999999999999  0.0  0.0  0.0
0.5903999999999999  0.0  0.0  0.00.48040.1248  0.0  0.0  0.0  0.0

Size = 3200 Mat. Mult. Elapsed Time: 241.1946 seconds.

END OF OUTPUT -------------------------------- */