package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class Passenger
{
   private String name;
   private String group;
   private String servClass;
   private char seatPref;

   /**
    * Default
    */
   public Passenger()
   {
      name = null;
      group = null;
      servClass = null;
      seatPref = ' ';
   }

   /**
    * Passenger reserving individual seat 
    * @param n name of passenger
    * @param serv service class such as first or economy
    * @param seat preference such as window, aisle, or center
    */
   public Passenger(String n, String serv, char seat)
   {
      name = n;
      servClass = serv;
      seatPref = seat;
   }

   /**
    * Passenger who is in a group
    * @param n
    * @param g
    * @param serv
    */
   public Passenger(String n, String g, String serv)
   {
      name = n;
      group = g;
      servClass = serv;
   }

   /**
     * 
     */
   String getName()
   {
      return name;
   }

   /**
    * @param n
    */
   void setName(String n)
   {
      name = n;
   }

   /**
     * 
     */
   String getGroup()
   {
      return group;
   }

   /**
    * @param g
    */
   void setGroup(String g)
   {
      group = g;
   }

   /**
     * 
     */
   String getServClass()
   {
      return servClass;
   }

   /**
    * @param c
    */
   void setServClass(String c)
   {
      servClass = c;
   }

   /**
     * 
     */
   char getSeatPref()
   {
      return seatPref;
   }

   /**
    * @param p
    */
   void setSeatPref(char p)
   {
      seatPref = p;
   }

}