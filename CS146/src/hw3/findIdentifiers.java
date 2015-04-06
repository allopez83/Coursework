package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Hansen Wu
 * @copyright 2015-04-03
 *
 */
public class findIdentifiers
{

   public static void main(String[] args)
   {
      File userInput = new File(args[0]);

      // Finds variable names
      try
      {
         countIdentifiers(userInput);
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private static void countIdentifiers(File file) throws IOException
   {
      BinarySearchTree vars = new BinarySearchTree();
      // Read input
      BufferedReader br = new BufferedReader(new FileReader(file));
      boolean end = false, done = false;
      int line = 0, start, stop;
      String read;

      while (!end) // Still more lines
      {
         read = br.readLine();
         line++;
         done = false;
         if (read == null) // End of file or already found a var
            end = true;
         else
         {
            char[] thisLine = read.toCharArray();
            for (int i = 0; i < thisLine.length; i++) // Parse line
            {
               if (thisLine[i] == ';') done = true;
               // Look for a var
               else if (thisLine[i] != 32 || done) // First nonspace on a line
               {
                   // found variable type
                  
                  // Go to variable name
                  while (thisLine[i] != 32)
                  {
                     i++;
                     if (thisLine[i] == ';')
                        done = true;
                  }
                  i++;
                  
                  // At variable name
                  String thisChar = thisLine[i] + "";
                  if (thisChar.matches("[a-zA-Z]"))
                  {
                     start = i;
                     thisChar = thisLine[i] + "";
                     while (thisChar.matches("[a-zA-Z]"))
                     {
                        i++;
                        if (thisLine[i] == ';')
                        {
                           done = true;
                           break;
                        }
                        thisChar = thisLine[i] + "";
                     }
                     stop = i;
                     done = true;
                     
                     // save variable
                     String name = new String(thisLine, start, stop-start);
                     
                     vars.insert(new VariableInstance(name, line));
                  }
                  else
                     done = true;
               }
               else
               {} // do nothing
               if(done)
                  i = thisLine.length;
            }
         }
      }
      br.close();

      // Print results
      vars.printTree();
   }

   /**
    * Wrapper class containing a parentheses type, it's line, and column
    */
   static class VariableInstance implements Comparable
   {
      LinkedList<Integer> lines = new LinkedList<Integer>();
      String name;

      /**
       * Default values
       */
      public VariableInstance()
      {
      }

      /**
       * Constructor
       * @param name variable name
       */
      VariableInstance(String name, int i)
      {
         this.name = name;
         lines.add(i);
      }

      public String getName()
      {
         return name;
      }

      /**
       * Add a line number that the var appears on
       * @param line which line it is on
       */
      void add(int line)
      {
         lines.add(line);
      }

      public String toString()
      {
         return (name + " appears on lines:\n" + lines.toString());
      }

      @Override
      public int compareTo(Object o)
      {
         return this.name.compareTo(((VariableInstance)o).name);
      }
   }

}
