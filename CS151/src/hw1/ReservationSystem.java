package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class ReservationSystem
{
   private static final boolean TESTING = true;
   private static final String UNABLE = "Request failed";

   private static Flight flight;
   private static UserInput userIn;
   private static FileIO io;
   private static boolean running;

   /**
    * Main method
    */
   public static void main(String[] args)
   {
      flight = new Flight();
      userIn = new UserInput();
      io = new FileIO();
      running = true;
      char in = 0;

      if (TESTING) System.out.println(" > ResvSys Main Method");


      // Attempt to load with FileIO

      // Main menu loop
      while (running)
      {
         menu();
         in = userIn.getMenu();
         if (in == 'P')
            addPassenger();
         else if (in == 'G')
            addGroup();
         else if (in == 'C')
            cancelReservation();
         else if (in == 'A')
            printSeatingAvailabilityChart();
         else if (in == 'M')
            printManifest();
         else if (in == 'Q')
            quit();
         else
            System.out.println("Invalid input");

         // Whitespace
         System.out.println();
      }

   }

   /**
    * Prints main menu
    */
   static void menu()
   {
      String prompt = "Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print \n"
            + "Seating [A]vailability Chart, Print [M]anifest, [Q]uit";
      System.out.println(prompt);
   }

   /**
     * 
     */
   static void addPassenger()
   {
      String result;
      String name = userIn.getName();
      String servClass = userIn.getServiceClass();
      char seatPref = userIn.getSeatPref();

      result = flight.addPassenger(new Passenger(name, servClass, seatPref));
      if (result == null)
         System.out.println(UNABLE);
      else
         System.out.println("Seated at: " + result);
   }

   /**
     * 
     */
   static void addGroup()
   {
      String result;
      String groupName = userIn.getGroupName();
      String[] members = userIn.getMembers();
      String servClass = userIn.getServiceClass();

      Passenger[] group = new Passenger[members.length];
      for (int i = 0; i < members.length; i++)
         group[i] = new Passenger(members[i], groupName, servClass);

      result = flight.addGroup(group);
      if (result == null)
         System.out.println(UNABLE);
      else
         System.out.println("Seated at: " + result);

      // TODO implement here
   }

   /**
     * 
     */
   static void cancelReservation()
   {
      // TODO implement here
   }

   /**
     * 
     */
   static void printSeatingAvailabilityChart()
   {
      flight.printAvailability();
      // TODO implement here
   }

   /**
     * 
     */
   static void printManifest()
   {
      flight.printManifest();
      // TODO implement here
   }

   /**
     * 
     */
   static void quit()
   {
      // FileIO stuff
      // TODO implement here
      running = false;
   }
}
