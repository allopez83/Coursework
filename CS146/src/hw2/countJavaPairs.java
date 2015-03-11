package hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class countJavaPairs
{
   // Keep track of symbols
   private static MyStack<Symbol> comment = new MyStack<Symbol>(),
         parentheses = new MyStack<Symbol>(), bracket = new MyStack<Symbol>(),
         curly = new MyStack<Symbol>();

   private static File defaultFile = new File("countJavaPairs.java");

   public static void main(String[] args) throws IOException
   {
      // User specified file
      Scanner userIn = new Scanner(System.in);
      System.out.println("Path to file:");
      String input = userIn.nextLine();
      File userFile = new File(input);
      
      // Count pairs of file
      try
      {
         if (userFile.exists())
            countPairs(userFile);
         else
         {
            System.out.println("Specified file not found, using default");
            countPairs(defaultFile);
         }
      } catch (Throwable ex)
      {
         System.err.println("Uncaught exception - " + ex.getMessage());
         ex.printStackTrace(System.err);
      }
      userIn.close();
   }

   /**
    * Checks if a file has balanced parentheses
    * @param file the file to check
    * @return boolean representing if it's balanced
    * @throws IOException if file goes wrong
    */
   private static void countPairs(File file) throws IOException
   {
      // Read input
      BufferedReader br = new BufferedReader(new FileReader(file));
      boolean end = false;
      char next = 0, prev = 0;
      String read;

      while (!end) // Still more lines
      {
         read = br.readLine();
         if (read == null) // End of file
            end = true;
         else
         {
            char[] thisLine = read.toCharArray();
            for (int i = 0; i < thisLine.length; i++) // Parse line
            {
               next = thisLine[i];
               if (next == '(')
                  parentheses.push(new Symbol());
               else if (next == '[')
                  bracket.push(new Symbol());
               else if (next == '{')
                  curly.push(new Symbol());
               else if (next == '*' && prev == '/')
                  comment.push(new Symbol());
               prev = next; // save as previous
            }
         }
      }
      br.close();

      // Print results
      System.out.println("/* */ = " + comment.getSize() + " pairs, () = "
            + parentheses.getSize() + " pairs, [] = " + bracket.getSize()
            + " pairs, {} = " + curly.getSize() + " pairs");
      // /* */ = 14 pairs, () = 30 pairs, [] = 56 pairs, {} = 153 pairs
   }

   /**
    * Wrapper class containing a parentheses type, it's line, and column
    */
   static class Symbol
   {
      char type;
      int line, column;

      /**
       * Default values
       */
      public Symbol() {}
      
      /**
       * Constructor
       * @param type open or close parentheses
       * @param line which line it's on
       * @param column the column it's on
       */
      Symbol(char type, int line, int column)
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