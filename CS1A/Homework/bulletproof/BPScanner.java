package bulletproof;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Contains methods to retrieve desirable values from user via input through
 * console
 * @author HW
 * @date v1.0: 2013.10.23 v2.0: 2013.06.03
 */
public class BPScanner
{
   private Scanner kb = null;

   // Constructor
   public BPScanner()
   {
      kb = new Scanner(System.in);
   }

   // String
   public String getStringFromUser(String prompt)
   {
      while (true)
      {
         try
         {
            System.out.print(prompt);
            String input = kb.nextLine();
            return input;
         } catch (NoSuchElementException nsee)
         {
         }
         System.out.println("Error: String! Enter an appropriate value!\n"
               + "Examples of a String: Bob, Sandwich, Server Racks, go on a run");
         System.out.println();
         kb.close();
         kb = new Scanner(System.in);
      }
   }

   // Double
   public double getDoubleFromUser(String prompt, double low, double high)
   {
      while (true)
      {
         try
         {
            System.out.print(prompt);
            String input = kb.nextLine().trim();
            double theDouble = Double.parseDouble(input);
            if (low <= theDouble && theDouble <= high)
               return theDouble;
         } catch (NumberFormatException e1)
         {
         } catch (NoSuchElementException e2)
         {
         }
         System.out
               .println("Error: Bound Double! Enter an appropriate value!\n"
                     + "Examples of a double: 1.25, 5.999, 100.1");
         System.out.println();
         kb.close();
         kb = new Scanner(System.in);
      }
   }

   public double getUnboundDoubleFromUser(String prompt)
   {
      while (true)
      {
         try
         {
            System.out.print(prompt);
            String input = kb.nextLine().trim();
            double theUnboundDouble = Double.parseDouble(input);
            return theUnboundDouble;
         } catch (NumberFormatException e1)
         {
         } catch (NoSuchElementException e2)
         {
         }
         System.out
               .println("Error: Unbound Double! Enter an appropriate value!\n"
                     + "Examples of a double: 1.25, 5.999, 100.1");
         System.out.println();
         kb.close();
         kb = new Scanner(System.in);
      }
   }

   // Integer
   public int getIntegerFromUser(String prompt, double low, double high)
   {
      while (true)
      {
         try
         {
            System.out.print(prompt);
            String input = kb.nextLine().trim();
            int theInteger = Integer.parseInt(input);
            if (low <= theInteger && theInteger <= high)
               return theInteger;
         } catch (NumberFormatException e1)
         {
         } catch (NoSuchElementException e2)
         {
         }
         System.out.println("Error: Integer! Enter an appropriate value!\n"
               + "Examples of an integer: 1, 5, 200");
         System.out.println();
         kb.close();
         kb = new Scanner(System.in);
      }
   }

   public int getUnboundIntegerFromUser(String prompt)
   {
      while (true)
      {
         try
         {
            System.out.print(prompt);
            String input = kb.nextLine().trim();
            int theUnboundInteger = Integer.parseInt(input);
            return theUnboundInteger;
         } catch (NumberFormatException e1)
         {
         } catch (NoSuchElementException e2)
         {
         }
         System.out
               .println("Error: Unbound Integer! Enter an appropriate value!\n"
                     + "Examples of an integer: 1, 5, 200");
         System.out.println();
         kb.close();
         kb = new Scanner(System.in);
      }
   }

   // Menu
   public String getMenuStringFromUser(String title, String... items)
   {
      String menu = "\n" + title + "\n\n";
      int count = 0;
      while (count < items.length)
      {
         menu += (count + 1) + ". " + items[count] + "\n";
         count++;
      }
      menu += "\nEnter selection [1-" + items.length + "] : ";

      int sel = getIntegerFromUser(menu, 1, items.length);

      return items[sel - 1];
   }
}