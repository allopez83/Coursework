package hw2;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Ensures user inputs valid input, and returns the input
 */
public class UserInput
{
   private Scanner userIn;
   private final String tryAgain = "Invalid input";

   /**
    * Constructor
    */
   public UserInput()
   {
      userIn = new Scanner(System.in);
   }
   
   /**
    * Process menu input from user
    * @return valid char of user's choice
    */
   char getMenu()
   {
      boolean gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.println("Select one of the following options:\n"
            + "[L]oad, [V]iew by, [C]reate, [G]o to, [E]vent list, [D]elete,"
            + " or [Q]uit");
         input = userIn.nextLine();
         if (input.matches("[LVCGEDQ]"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input.charAt(0);
      // TODO untested
   }

   /**
     * Ask if user wants to view calendar in days or months
     */
   char viewDayOrMonth()
   {
      boolean gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.print("[D]ay view or [M]onth view?");
         input = userIn.nextLine();
         if (input.matches("[DM]"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input.charAt(0);
      // TODO untested
   }

   /**
     * Get next step from user when viewing events
     */
   char viewNextStep()
   {
      boolean gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.print("[P]revious or [N]ext or [M]ain menu?");
         input = userIn.nextLine();
         if (input.matches("[PNM]"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input.charAt(0);
      // TODO untested
   }

   /**
    * Gets a name for an event from user, accepts string of any characters
    * @return
    */
   String eventTitle()
   {
      System.out.println("Title: ");
      return userIn.nextLine();
      // TODO untested
   }

   /**
    * Get a date in MM/DD/YYYY format from user 
    * @return string of the date
    */
   String eventDay()
   {
      boolean gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.println("Enter a day (MM/DD/YYYY format):");
         input = userIn.nextLine();
         if (input.matches("[0-9/]+"))
         {
            String[] split = input.split("/");
            int[] date = new int[3];
            if (split.length == 3)
            {
               for (int i = 0; i < 3; i++)
                  date[i] = Integer.parseInt(split[i]);
               if (0 < date[0] && date[0] < 13) // month
                  if (0 < date[1] && date[1] < 31) // day
                     if (1500 < date[2] && date[2] < 3000) // year
                        gotValid = true; // valid
            }
         }
         if (!gotValid)
            System.out.println(tryAgain);
      }
      return input;
      // TODO untested
   }

   /**
    * Get time of day in 24hr of an event's start
    * @return string representing hour and minute
    */
   String eventStart()
   {
      boolean gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.println("Starting time (24hr format, like 13:00):");
         input = userIn.nextLine();
         if (input.matches("[0-9:]+"))
         {
            String[] split = input.split(":");
            int[] time = new int[2];
            if (split.length == 2)
            {
               for (int i = 0; i < 2; i++)
                  time[i] = Integer.parseInt(split[i]);
               if (0 < time[0] && time[0] < 24) // hours
                  if (0 <= time[1] && time[1] < 60) // minutes
                     gotValid = true; // valid
            }
         }
         if (!gotValid)
            System.out.println(tryAgain);
      }
      return input;
      // TODO untested
   }

   /**
    * Get time of day in 24hr an event will end
    * @return string representing hour and minute, or null if not applicable
    */
   String eventEnd()
   {
      boolean gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.println("Ending Time (NA if not applicable):");
         input = userIn.nextLine();
         if (input.equals("NA")) // None applicable
         {
            input = null;
            gotValid = true;
         }
         else if (input.matches("[0-9:]+"))
         {
            String[] split = input.split(":");
            int[] time = new int[2];
            if (split.length == 2)
            {
               for (int i = 0; i < 2; i++)
                  time[i] = Integer.parseInt(split[i]);
               if (0 < time[0] && time[0] < 24) // hours
                  if (0 < time[1] && time[1] < 60) // minutes
                     gotValid = true; // valid
            }
         }
         if (!gotValid)
            System.out.println(tryAgain);
      }
      return input;
      // TODO untested
   }

   /**
     * 
     */
   void deleteSelecedOrAll()
   {
      // TODO implement here
   }

}