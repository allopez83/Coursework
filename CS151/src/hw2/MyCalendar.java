package hw2;

/**
 * 
 */
public class MyCalendar
{
   private static final boolean TESTING = true;
   private static final String UNABLE = "Request failed";

   private static Calendar events;
   private static UserInput userIn;
   private static FileIO io;
   private static boolean running;

   /**
     * 
     */
   public static void main(String[] args)
   {
      events = new Calendar();
      userIn = new UserInput();
      io = new FileIO();
      running = true;
      char in = 0;

      if (TESTING) System.out.println(" > MyCalendar Main Method");

      // Main menu loop
      while (running)
      {
         menu();
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
     * 
     */
   static void menu()
   {
      if (TESTING) System.out.println(" > menu");
      System.out.println("Select one of the following options:\n"
            + "[L]oad, [V]iew by, [C]reate, [G]o to, [E]vent list, [D]elete,"
            + " [Q]uit");
   }

   /**
     * 
     */
   static void load()
   {
      if (TESTING) System.out.println(" > load");
      // TODO implement here
   }

   /**
     * 
     */
   static void viewBy()
   {
      if (TESTING) System.out.println(" > viewBy");
      // TODO implement here
   }

   /**
    * Creates new Event() and passes to Calendar
    */
   static void create()
   {
      if (TESTING) System.out.println(" > create");
      // TODO implement here
   }

   /**
     * 
     */
   static void goTo()
   {
      if (TESTING) System.out.println(" > goTo");
      // TODO implement here
   }

   /**
     * 
     */
   static void eventList()
   {
      if (TESTING) System.out.println(" > eventList");
      // TODO implement here
   }

   /**
     * 
     */
   static void delete()
   {
      if (TESTING) System.out.println(" > delete");
      // TODO implement here
   }

   /**
     * 
     */
   static void quit()
   {
      if (TESTING) System.out.println(" > quit");
      running = false;
      io.saveCalendar(events);
      // TODO implement here
   }

}