package hw1;

import java.util.Scanner;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class UserInput
{
   private Scanner userIn;
   private boolean gotValid;
   private final String tryAgain = "Invalid input";

   /**
     * 
     */
   public UserInput()
   {
      userIn = new Scanner(System.in);
   }

   /**
    * Get name for individual, can only contain letters
    * @return valid name
    */
   String getName()
   {
      gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.print("Name: ");
         input = userIn.nextLine();
         if (input.matches("[a-zA-Z]+"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input;
   }

   /**
    * Get name for group, can only contain letters, so one word
    * @return valid name
    */
   String getGroupName()
   {
      gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.print("Group Name: ");
         input = userIn.nextLine();
         if (input.matches("[a-zA-Z ]+"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input;
   }

   /**
    * Gets members for a group, names separated by a comma
    * @return
    */
   String[] getMembers()
   {
      gotValid = false;
      String input = "";
//      while (!gotValid)
//      {
//         System.out.print("Names: ");
//         input = userIn.nextLine();
//         if (input.matches("[a-zA-Z, ]+"))
//            gotValid = true;
//         else
//            System.out.println(tryAgain);
//      }
      // Split them up
      //TODO testing code
      input = "p1, p2, p3, p4, p5, p6, p7";
      int size = input.length() - input.replace(",", "").length()+1;
      String[] names = input.split(",");
      return names;
   }

   /**
    * Determine if user wants First class or Economy class
    * @return First or Economy
    */
   String getServiceClass()
   {
      gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.print("Service Class: ");
         input = userIn.nextLine();
         if (input.matches("First") || input.matches("Economy"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input;
   }

   /**
    * Gets single char representing if user wants window seat, aisle seat, or
    * center seat
    * @return char W, A, or C
    */
   char getSeatPref()
   {
      gotValid = false;
      String input = "";
      while (!gotValid)
      {
         System.out.print("Seat Preference: ");
         input = userIn.nextLine();
         if (input.matches("[WAC]"))
            gotValid = true;
         else
            System.out.println(tryAgain);
      }
      return input.charAt(0);
   }

   /**
     * 
     */
   char getIndividualOrGroup()
   {
      // TODO implement here
      return 0;
   }

   /**
    * Get user's menu selection
    * @return a character
    */
   char getMenu()
   {
      String input = userIn.nextLine();
      if (input.length() == 1 && input.matches("[PGCAMQ]"))
         return input.charAt(0);
      return ' '; // Else
   }

}
