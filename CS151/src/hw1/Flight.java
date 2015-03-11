package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class Flight
{
   private static final boolean TESTING = true;

   // First class
   private ServiceClass first;
   // Economy class
   private ServiceClass econ;

   public Flight()
   {
      this.first = new ServiceClass(1, 2, 4);
      this.econ = new ServiceClass(10, 29, 6);
      if (TESTING) System.out.println(" > Created first and economy class");
   }

   /**
    * @param f
    * @param e
    */
   public Flight(ServiceClass f, ServiceClass e)
   {
      // TODO implement here
   }

   /**
     * 
     */
   private void getFirst()
   {
      // TODO implement here
   }

   /**
    * @param serv
    */
   private void setFirst(ServiceClass serv)
   {
      // TODO implement here
   }

   /**
     * 
     */
   private void getEcon()
   {
      // TODO implement here
   }

   /**
    * @param serv
    */
   private void setEcon(ServiceClass serv)
   {
      // TODO implement here
   }

  /**
   * Add a passenger reservation
   * @param p passenger to add
   * @return String of reserved seat
   */
   String addPassenger(Passenger p)
   {
      String servClass = p.getServClass();
      if (servClass.equals("First") && first.getAvailable() > 0)
         return first.addPassenger(p);
      else if (servClass.equals("Economy") && econ.getAvailable() > 0)
         return econ.addPassenger(p);
      else
         return null;
   }

   /**
    * Add a group reservation
    * @param g group to add
    * @return seats reserved for group
    */
   String addGroup(Passenger[] p)
   {
      int[] coord;
      int size = p.length;
      String servClass = p[0].getServClass();
      ServiceClass targetClass;
      if (servClass.equals("First") && size <= first.getAvailable())
         targetClass = first;
      else if (servClass.equals("Economy") && size <= econ.getAvailable())
         targetClass = econ;
      else
         return null;

      int seated = 0, alreadySeated = 0;
      String locations = "";
      while (alreadySeated < size)
      {
         coord = targetClass.largestContiguous(size - alreadySeated);
         for (int i = 0; i < coord[2] && i < size - alreadySeated
               && i < targetClass.getColumns(); i++)
         {
            Passenger toSeat = p[i + alreadySeated];
            int r = coord[0], c = coord[1] + i;
            locations += targetClass.addPassenger(toSeat, r, c) + "\n";
            seated++;
         }
         alreadySeated += seated;
         seated = 0;
      }

      return locations;
   }

   /**
    * @param name
    */
   void removePassenger(String name)
   {
      // TODO implement here
   }

   /**
    * @param name
    */
   void removeGroup(String name)
   {
      // TODO implement here
   }

   Passenger getPassenger(int row, int col)
   {
      if (row >= econ.getRowStart()) // After start of econ
         return econ.getPassenger(row, col);
      else if (row <= first.getRowEnd()) // Before end of first
         return first.getPassenger(row, col);
      else
         // Nonexistant seat in between
         return null;
   }

   /**
     * 
     */
   void printManifest()
   {
      // TODO implement here
   }

   /**
     * 
     */
   void printAvailability()
   {
      // TODO implement here
   }

}