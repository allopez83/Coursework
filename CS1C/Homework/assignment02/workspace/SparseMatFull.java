package assignment02.workspace;

import java.util.ListIterator;

import cs_1c.*;

public class SparseMatFull<E> implements Cloneable
{
   private static final boolean TEST = true; 
   protected int row_size, col_size;
   protected E default_val;
   protected FHarrayList<FHlinkedList<MatNode>> rows;

   /**
    * Create a SparseMatrix using the given parameters
    * @param num_rows rows that the matrix will have
    * @param num_cols columns that the matrix will have
    * @param default_v value of elements non-specified by the user
    */
   SparseMatFull(int num_rows, int num_cols, E default_v)
   {
      row_size = num_rows;
      col_size = num_cols;
      default_val = default_v;
      allocateEmptyMatrix();
   }

   /**
    * Creates the empty lists making up the matrix using the given size
    */
   void allocateEmptyMatrix()
   {
      rows = new FHarrayList<FHlinkedList<MatNode>>(row_size);
      // Give all rows the default (empty) linked list
      for (int k = 0; k < row_size; k++)
      {
         rows.add(k, new FHlinkedList<MatNode>());
         FHlinkedList<MatNode> column = rows.get(k);
         for (int i = 0; i < col_size; i++)
         {
            column.add(new MatNode());
         }
      }
   }

   /**
    * Retrieve the data stored in a MatNode on the given column and row using
    * the getMatNode() method
    * @param r row of the MatNode
    * @param c column of the MatNode
    * @return the data stored in a specified MatNode
    */
   E get(int r, int c)
   {
      if (r > row_size || c > col_size)
         throw new IndexOutOfBoundsException();
      return rows.get(r).get(c).data;
   }

   /**
    * Finds a specified MatNode and retrieves the data contained in it
    * @param r row that the MatNode is on
    * @param c column that the MatNode is on
    * @return the data stored in a specified MatNode
    */
   private E getMatNodeData(int r, int c)
   {
      FHlinkedList<MatNode> columns = (FHlinkedList<MatNode>) rows.get(r);
      ListIterator<MatNode> iter = columns.listIterator();
      // Check if the value is default or not
      for (int k = 0; k < columns.size(); k++)
      {
         MatNode node = iter.next();
         if (c == node.getCol()) // Found a node, data is not default
            return (E) columns.get(k).data;
      }
      // Specified node not found, it must be default
      return default_val;
   }

   /**
    * Changes the data at a specified location to one specified by the user
    * @param r row the target is on
    * @param c column the target is on
    * @param x data that will be entered
    * @return false if specified location is outside the matrix, true if data
    *         successfully entered
    */
   boolean set(int r, int c, E x)
   {
      // Greater or equal since arrays start at 0
      if (r >= row_size || c >= col_size)
         return false;
      if (x.equals(default_val))
      {
         if (TEST) System.out.println(x + " equals default value: " + default_val);
         removeNodMatNode(r, c); // No need to store a node with default data
         return true;
      } else
      {
         MatNode new_node = new MatNode(c, x);
         rows.get(r).set(c, new_node); // Add to the linked list
         return true;
      }
   }

   /**
    * Removes the node at a given location
    * @param r row the target is on
    * @param c column the target is on
    */
   private void removeNodMatNode(int r, int c)
   {
      boolean not_found = true;
      FHlinkedList<MatNode> columns = rows.get(r);
      ListIterator<MatNode> iter = columns.listIterator();
      // Check if the value is default or not
      for (int k = 0; k < columns.size() && not_found; k++)
      {
         MatNode node = iter.next();
         if (c == node.getCol())
         {
            // Found a node that user entered
            iter.remove();
            not_found = false;
         }
      }
      
   }

   /**
    * Removes all user-entered data from the sparse matrix
    */
   void clear()
   {
      for (int k = 0; k < row_size; k++)
         rows.set(k, new FHlinkedList<MatNode>());
   }

   /**
    * Prints a specified excerpt of the sparse matrix to the console
    * @param start both the row and column location of the square excerpt, the
    *           very top left of the square is considered the start. I.e. a
    *           start value of 5 will put the top-left corner of the square on
    *           row 5, column 5
    * @param size length of each side of the square excerpt
    */
   void showSubSquare(int start, int size)
   {
      if (start > row_size || start > col_size)
         throw new IndexOutOfBoundsException();
      int current_row;
      // Go through the linked lists in all 'horizontal' rows
      for (current_row = start; current_row < size; current_row++)
      {
         FHlinkedList<MatNode> column = rows.get(current_row);
         // If the column is not empty
         if (column != null)
         {
            // Find which column user-added elements are on
            ListIterator<MatNode> iter = column.listIterator();
            // Go through the linked list
            for (int k = 0; k < column.size() == true && iter.hasNext(); k++)
            {
               MatNode node = iter.next();
               int next_entry = node.getCol();
               // Print all elements before the node
               for (int i = 0; i < next_entry - 1; i++)
               {
                  System.out.print(" " + default_val + " ");
               }
               System.out.println(node);
            }
            int last_entry = column.getLast().col;
            // Print out the last elements
            for (int k = last_entry; k < start + size - 1; k++)
            {
               System.out.print(" " + default_val + " ");
            }
         }
         // And if column is empty, print default values
         System.out.println();
      }

      // get data
      // check if data is not default
      // print data
   }

   /*
    * void showSubSquare(int start, int size) - a display method that will show
    * a square sub-matrix anchored at (start, start) and whose size is size x
    * size. In other words it will show the rows from start to start + size -1
    * and the columns from start to start + size - 1. This is mostly for
    * debugging purposes since we obviously cannot see the entire matrix at
    * once.
    */

   boolean copyValue(int rSrc, int cSrc, int rDst, int cDst)
   {
      return true;
   }

   /**
    * Each node represents an element of a SparseMat that is NOT default value.
    * It contains some data, and the column on which it is on.
    */
   protected class MatNode
   {
      public int col;
      public E data;

      // we need a default constructor for lists
      MatNode()
      {
         col = 0;
         data = null;
      }

      /**
       * Creates a new MatNode with the specified column and data to be entered
       * @param cl Column on which the MatNode lies on
       * @param dt Data that will be stored in the MatNode
       */
      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }

      /**
       * Retrieves the column on which the MatNode is on
       * @return column the MatNode is on
       */
      public int getCol()
      {
         return col;
      }
   };

}
