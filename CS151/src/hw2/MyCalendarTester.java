package hw2;

import java.util.GregorianCalendar;

/**
 * 
 */
public class MyCalendarTester
{
   private static final boolean TESTING = true;
   // private static final String UNABLE = "Request failed";

   private static MyCalendar eventCal;
   private static UserInput userIn;
   private static FileIO io;
   private static boolean running;

   /**
     * 
     */
   public static void main(String[] args)
   {
      eventCal = new MyCalendar();
      userIn = new UserInput();
      io = new FileIO();
      running = true;
      char in = 0;

      // Main menu loop
      while (running)
      {
         in = userIn.getMenu();
         
         if (in == 'L')
            load();
         else if (in == 'V')
            viewBy();
         else if (in == 'C')
            create();
         else if (in == 'G')
            goTo();
         else if (in == 'E')
            eventList();
         else if (in == 'D')
            delete();
         else if (in == 'Q')
            quit();
         else
            System.out.println("Invalid menu selection");

         // Whitespace
      }
   }

   /**
    * Load a presaved event calendar
    */
   static void load()
   {
      // TODO implement here
   }

   /**
    * View events as month or day
    */
   static void viewBy()
   {
      // TODO implement here
   }

   /**
    * Creates new Event object and adds it to Calendar
    */
   static void create()
   {
      String title = userIn.eventTitle();
      String date = userIn.eventDay();
      String start = userIn.eventStart();
      String end = userIn.eventEnd();

      // Start time GregorianCalendar
      String[] split = date.split("/"); // MM/DD/YYYY format
      int year = Integer.parseInt(split[2]);
      int month = Integer.parseInt(split[0]);
      int day = Integer.parseInt(split[1]);
      split = start.split(":"); // HH:mm format
      int hour = Integer.parseInt(split[0]);
      int minute = Integer.parseInt(split[1]);

      GregorianCalendar startGC = new GregorianCalendar(year, month, day, hour, minute);
      
      Event e;
      if (end == null)
         e = new Event(title, startGC);
      else
      {
         // Also create end time GregorianCalendar
         split = date.split("/"); // MM/DD/YYYY format
         year = Integer.parseInt(split[2]);
         month = Integer.parseInt(split[0]);
         day = Integer.parseInt(split[1]);
         split = start.split(":"); // HH:mm format
         hour = Integer.parseInt(split[0]);
         minute = Integer.parseInt(split[1]);
         
         GregorianCalendar endGC = new GregorianCalendar();
         e = new Event(title, startGC, endGC);
      }
      if (TESTING) System.out.println(e);
      eventCal.addEvent(e);
   }

   /**
    * View a specific day
    */
   static void goTo()
   {
      // TODO implement here
   }

   /**
    * Show all events
    */
   static void eventList()
   {
      eventCal.listAll();
   }

   /**
     * 
     */
   static void delete()
   {
      String input = userIn.eventDay();

      // Start time GregorianCalendar
      String[] split = input.split("/"); // MM/DD/YYYY format
      int year = Integer.parseInt(split[2]);
      int month = Integer.parseInt(split[0])-1;
      int day = Integer.parseInt(split[1]);
      
      GregorianCalendar target = new GregorianCalendar(year, month, day);
      eventCal.deleteDay(target);
      // TODO implement here
   }

   /**
     * Exit and saves the program
     */
   static void quit()
   {
      running = false;
      io.saveCalendar(eventCal);
   }
}