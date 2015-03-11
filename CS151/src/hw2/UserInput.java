package hw2;

import java.util.Scanner;

/**
 * 
 */
public class UserInput
{
   private Scanner userIn;
   private boolean gotValid;
   private String input;
   private final String tryAgain = "Invalid input";

   /**
    * Constructor
    */
   public UserInput()
   {
      userIn = new Scanner(System.in);
   }
   
   /**
    * 
    * @return
    */
   char getMenu()
   {
      input = userIn.nextLine();
      if (input.length() == 1 && input.matches("[LVCGEDQ]"))
         return input.charAt(0);
      return ' '; // Else
   }

   /**
     * 
     */
   void viewDayOrMonth()
   {
      // TODO implement here
   }

   /**
     * 
     */
   void viewNextStep()
   {
      // TODO implement here
   }

   /**
     * 
     */
   String eventTitle()
   {
      System.out.print("Title: ");
      return userIn.nextLine();
   }

   /**
     * 
     */
   void eventDay()
   {
      // TODO implement here
   }

   /**
     * 
     */
   void eventStart()
   {
      // TODO implement here
   }

   /**
     * 
     */
   void eventEnd()
   {
      // TODO implement here
   }

   /**
     * 
     */
   void day()
   {
      // TODO implement here
   }

   /**
     * 
     */
   void deleteSelecedOrAll()
   {
      // TODO implement here
   }

}