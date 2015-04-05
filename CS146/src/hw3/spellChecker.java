package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Hansen Wu
 * @copyright 2015-04-03
 *
 */
public class spellChecker
{

   static File dictionary = new File("dictionary.txt");
   static File input;
   static FHhashQP dict;
   
   public static void main(String[] args) throws IOException
   {
      System.out.println("----- start -----");
      input = new File(args[0]);
      System.out.println("argument: " + args[0]);
      if (dictionary.exists())
         if (input.exists())
            System.out.println("Files exist");

      count(input);

      System.out.println("-----  end  -----");
   }

   private static void count(File file) throws IOException
   {
      // Load dictionary
      BufferedReader dr = new BufferedReader(new FileReader(file));
      dict = new FHhashQP();
      
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
         else // Read next line
         {
            char[] thisLine = read.toCharArray();
            
            for (int i = 0; i < thisLine.length; i++) // Parse line
            {
               
            }
         }
      }
      br.close();

 
      // Print results
   }
}