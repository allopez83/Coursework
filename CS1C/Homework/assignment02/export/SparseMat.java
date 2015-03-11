package assignment02.export;

import java.util.Collections;
import java.util.ListIterator;

import cs_1c.*;

public class SparseMat<E> implements Cloneable
{
   protected int row_size, col_size;
   protected E default_val;
   protected FHarrayList<FHlinkedList<MatNode>> rows;

   /**
    * Create a SparseMatrix using the given parameters
    * @param num_rows rows that the matrix will have, starting from zero
    * @param num_cols columns that the matrix will have, starting from zero
    * @param default_v value of elements non-specified by the user
    */
   SparseMat(int num_rows, int num_cols, E default_v)
   {
      row_size = num_rows;
      col_size = num_cols;
      default_val = default_v;
      allocateEmptyMatrix();
   }

   /**
    * Creates the empty lists making up the matrix using the given size
    */
   private void allocateEmptyMatrix()
   {
      rows = new FHarrayList<FHlinkedList<MatNode>>(row_size);
      // Give all rows the default (empty) linked list
      for (int k = 0; k < row_size; k++)
         rows.add(k, new FHlinkedList<MatNode>());
   }

   /**
    * Retrieve the data stored in a MatNode on the given column and row using
    * the getMatNode() method
    * @param r row of the MatNode, starting from zero
    * @param c column of the MatNode, starting from zero
    * @return the data stored in a specified MatNode
    */
   E get(int r, int c)
   {
      if (r >= row_size || r < 0 || c >= col_size || c < 0)
         throw new IndexOutOfBoundsException();
      return getMatNode(r, c).data;
   }

   /**
    * Finds the MatNode at a specified location. If no user-defined MatNode is
    * found, a default one is created and returned, but not inserted into the
    * sparse matrix.
    * @param r row that the MatNode is on, starting from zero
    * @param c column that the MatNode is on, starting from zero
    * @return MatNode at the specified location
    */
   private MatNode getMatNode(int r, int c)
   {
      FHlinkedList<MatNode> columns = (FHlinkedList<MatNode>) rows.get(r);
      ListIterator<MatNode> iter = columns.listIterator();
      // Check if the value is default or not
      for (int k = 0; k < columns.size(); k++)
      {
         MatNode node = iter.next();
         if (c == node.getCol()) // Found a node, data is not default
            return columns.get(k);
      }
      // Specified node not found, it must be default, create one now
      return new MatNode(c, default_val);
   }

   /**
    * Changes the data at a specified location to one specified by the user
    * @param r row the target is on, starting from zero
    * @param c column the target is on, starting from zero
    * @param x data that will be entered
    * @return false if specified location is outside the matrix, true if data
    *         successfully entered
    */
   boolean set(int r, int c, E x)
   {
      // Filter out entries too big or small for matrix's dimension
      // Greater or equal since arrays start at 0
      if (r >= row_size || r < 0 || c >= col_size || c < 0)
         return false;
      if (x.equals(default_val))
      {
         removeMatNode(r, c); // No need to store a node with default data
         return true;
      } else if (get(r, c) != null) // If user entered an object there
      {
         // Delete the node
         removeMatNode(r, c);
         // Insert new node
         MatNode new_node = new MatNode(c, x);
         rows.get(r).add(new_node);
         return true;
      } else
      {
         MatNode new_node = new MatNode(c, x);
         rows.get(r).add(new_node); // Add to the linked list
         return true;
      }
   }

   /**
    * Removes the node at a given location
    * @param r row the target is on, starting from zero
    * @param c column the target is on, starting from zero
    */
   void removeMatNode(int r, int c)
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
      // Similar to allocate method, but bypasses creation of 'rows' array list
      for (int k = 0; k < row_size; k++)
         rows.set(k, new FHlinkedList<MatNode>());
   }

   /**
    * Prints a specified excerpt of the sparse matrix to the console. Parts of
    * the user-specified dimension exceeding matrix's own dimensions will not be
    * printed.
    * @param start both the row and column location of the square excerpt, the
    *           very top left of the square is considered the start. I.e. a
    *           start value of 5 will put the top-left corner of the square on
    *           row 5, column 5
    * @param size length of each side of the square excerpt
    */
   void showSubSquare(int start, int size)
   {
      if (start > row_size || start > col_size || start < 0)
         throw new IndexOutOfBoundsException();
      String defaultString = default_val.toString();
      // Go through the linked lists in all 'horizontal' rows
      for (int current_row = start; current_row - start < size
            && current_row < row_size; current_row++)
      {
         int printed = 0;
         boolean line_printed = false;
         FHlinkedList<MatNode> column = rows.get(current_row);
         ListIterator<MatNode> iter = column.listIterator();
         // Make sure the line is printed, and size won't be exceeded
         while (!line_printed && printed < size)
         {
            int last_entry = start - 1, difference = 0, next_entry = 0;
            // While there is still a node in the linked list
            if (iter.hasNext())
               Collections.sort(column);
            while (iter.hasNext())
            {
               MatNode node = iter.next();
               // Difference is important if a linked list has more than 1 node
               next_entry = node.getCol();
               difference = next_entry - last_entry;
               // Check if next entry is inside the user-specified location
               if (next_entry >= start && next_entry < start + size)
               {
                  // Print all elements before the node
                  for (int i = 0; i < difference - 1; i++)
                  {
                     System.out.printf("%5s", defaultString);
                     printed++;
                  }
                  System.out.printf("%5s", node.data);
                  last_entry = next_entry;
                  printed++;
               }
            }
            // Print out the last default elements
            for (int i = printed; i < size && i + start < col_size; i++)
               System.out.printf("%5s", defaultString);
            System.out.println();
            line_printed = true;
         }
      }
      System.out.println();
   }

   /**
    * Copies a matrix node in one location to another
    * @param rSrc row of the source node to be copied from
    * @param cSrc column of the source node to be copied from
    * @param rDst row of the destination location
    * @param cDst column of the destination location
    * @return true if the node has been successfully copied, false if it failed
    */
   boolean copyValue(int rSrc, int cSrc, int rDst, int cDst)
   {
      // If any of the locations are too too big or small
      if (rSrc > row_size || rSrc < 0 || cSrc > col_size || cSrc < 0
            || rDst > row_size || rDst < 0 || cDst > col_size || cDst < 0)
         return false;
      E copy_data = get(rSrc, cSrc);
      set(rDst, cDst, copy_data);
      return true;
   }

   /**
    * Creates and returns a deep copy of a SparseMat object
    */
   public SparseMat<E> clone()
   {
      SparseMat<E> new_matrix = new SparseMat<E>(row_size, col_size,
            default_val);

      // Clone all linked lists inside the sparse matrix
      for (int k = 0; k < row_size; k++)
      {
         FHlinkedList<MatNode> new_column = null;
         try
         {
            new_column = (FHlinkedList<MatNode>) rows.get(k).clone();
         } catch (CloneNotSupportedException e)
         {
            System.out.println("clone failed!");
            e.printStackTrace();
         }
         new_matrix.rows.set(k, new_column);
      }

      return new_matrix;

      /*
       * SparseMat<E> cloned_matrix = new SparseMat<E>(row_size, col_size,
       * default_val); cloned_matrix.allocateEmptyMatrix();
       */
   }

   /**
    * Each node represents an element of a SparseMat that is NOT default value.
    * It contains some data, and the column on which it is on.
    */
   protected class MatNode implements Comparable<MatNode>
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

      /**
       * Compares the col value of two MatNode objects
       */
      public int compareTo(MatNode other)
      {
         return (new Integer(this.col)).compareTo(other.col);
      }
   };
}
