package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class tester
{
   private static boolean
         FILEIO = false,
         FLIGHT = false,
         PASSENGER = false,
         RESERVATIONSYSTEM = true,
         SEAT = false,
         SERVICECLASS = false,
         USERINPUT = false,
         OTHERTEST = true;

   public static void main(String[] args)
   {
      System.out.println(" --------------- START --------------- ");

      // System.out.println("Flight name: " + args[0]);

      if (OTHERTEST)
      {
      }
      
      if (FILEIO)
      {
         System.out.println("\nFileIO:\n");
      }

      if (FLIGHT)
      {
         System.out.println("\nFlight:\n");
      }

      if (PASSENGER)
      {
         System.out.println("\nPassenger:\n");
      }

      if (RESERVATIONSYSTEM)
      {
         System.out.println("\nReservationSystem:\n");
         ReservationSystem sys = new ReservationSystem();
//         sys.mainMethod();
      }

      if (SEAT)
      {
         System.out.println("\nSeat:\n");
      }

      if (SERVICECLASS)
      {
         System.out.println("\nServiceClass:\n");
      }

      if (USERINPUT)
      {
         System.out.println("\nUserInput:\n");
      }

      System.out.println("\n ---------------- END ---------------- ");
   }
}
