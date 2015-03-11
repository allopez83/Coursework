package hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class balanceSymbols
{

   private static MyStack<Parentheses> stack = new MyStack<Parentheses>();
   private static char open = '(', close = ')';
   private static String path = "input.txt";

   public static void main(String[] args) throws IOException
   {
      File file = new File(path);

      boolean result = balanced(file);
      if (result)
         System.out.println("Balanced");
      else
         System.out.println("Not Balanced!");

   }

   /**
    * Checks if a file has balanced parentheses
    * @param file the file to check
    * @return boolean representing if it's balanced
    * @throws IOException if file goes wrong
    */
   private static boolean balanced(File file) throws IOException
   {
      // Read input
      BufferedReader br = new BufferedReader(new FileReader(file));
      boolean end = false;
      String read;
      char next;
      int line = 0, column = 0;

      while (!end) // Still more lines
      {
         read = br.readLine();
         line++;
         if (read == null) // End of file
            end = true;
         else
         {
            char[] thisLine = read.toCharArray();
            for (int i = 0; i < thisLine.length; i++) // Parse line
            {
               column = i + 1;
               next = thisLine[i];
               if (next == open)
                  stack.push(new Parentheses(next, line, column));
               if (next == close)
               {
                  if (stack.getSize() != 0) // Open parentheses to pair with
                     stack.pop();
                  else
                  {
                     // Push onto stack so it can be printed
                     stack.push(new Parentheses(next, line, column));
                     end = true;
                  }
               }
            }
         }
      }
      br.close();

      int size = stack.getSize();

      // All-ok
      if (size == 0)
         return true;

      // Not ok
      Parentheses error;
      System.out.println("Problem with parentheses: " + stack.pop());
      return false;
   }

   /**
    * Wrapper class containing a parentheses type, it's line, and column
    */
   static class Parentheses
   {
      char type;
      int line, column;

      /**
       * Constructor
       * @param type open or close parentheses
       * @param line which line it's on
       * @param column the column it's on
       */
      Parentheses(char type, int line, int column)
      {
         this.type = type;
         this.line = line;
         this.column = column;
      }

      public String toString()
      {
         return ("'" + type + "' @ line " + line + ", column " + column);
      }
   }

   /**
    * Stack data type using generics
    */
   private static class MyStack<E>
   {
      StackedNode<E> bottom, top;
      private int size;

      public int getSize()
      {
         return size;
      }

      public MyStack()
      {
         bottom = new StackedNode<E>();
         top = new StackedNode<E>();
         bottom.above = top;
         top.below = bottom;
         size = 0;
      }

      /**
       * Pushes data to top of stack
       * @param x
       */
      public void push(E x)
      {
         top.insertBelow(x);
         size++;
      }

      /**
       * Delete top data and return to caller
       * @return
       */
      public E pop()
      {
         if (size == 0)
            throw new NoSuchElementException();
         size--;
         return (E) top.below.remove();
      }

      private class StackedNode<E>
      {
         private StackedNode<E> below, above;
         private E data;

         /**
          * New null node
          */
         public StackedNode()
         {
            below = null;
            above = null;
            data = null;
         }

         /**
          * Create new node containing given data
          * @param x data it contains
          * @param b node below
          * @param a node above
          */
         public StackedNode(E x, StackedNode<E> b, StackedNode<E> a)
         {
            below = b;
            above = a;
            data = x;
         }

         /**
          * Creates node with x data and places right below this one
          * @param x
          */
         public void insertBelow(E x)
         {
            StackedNode<E> newNode = new StackedNode<E>(x, this.below, this);
            this.below.above = newNode;
            this.below = newNode;
         }

         /**
          * Removes this node
          * @return
          */
         public E remove()
         {
            below.above = above;
            above.below = below;
            return data;
         }

      }

   }

}
