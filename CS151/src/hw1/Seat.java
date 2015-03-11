package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class Seat
{
   private int row;
   private int column;
   private char seatPos;
   private char seatType;
   private boolean empty;
   private Passenger pass;

   /**
     * 
     */
   public Seat()
   {
      this.row = -1;
      this.column = -1;
      this.seatPos = ' ';
      this.seatType = ' ';
      this.empty = true;
      this.pass = null;
   }

   /**
    * Seat with given location
    * @param row row in plane
    * @param column column in plane
    * @param position letter column
    * @param type [w]indow, [A]isle, or [C]enter
    */
   public Seat(int row, int column, char position, char type)
   {
      this.row = row;
      this.column = column;
      this.seatPos = position;
      this.seatType = type;
      this.empty = true;
      this.pass = null;
   }

   /**
    * @return
    * 
    */
   Passenger getPassenger()
   {
      return pass;
   }

   /**
    * @param p
    */
   void setPassenger(Passenger p)
   {
      if (p == null)
      {
         pass = null;
         empty = true;
      }
      pass = p;
      empty = false;
   }

   /**
    * @return
    * 
    */
   int getRow()
   {
      return row;
   }

   /**
    * @param r
    */
   void setRow(int r)
   {
      row = r;
   }

   /**
    * @return
    * 
    */
   int getColumn()
   {
      return column;
   }

   /**
     * 
     */
   void setColumn(int c)
   {
      column = c;
   }

   /**
    * Position of seat, such as A, B, C, D ...
    * @return
    */
   char getSeatPos()
   {
      return seatPos;
   }

   /**
    * @param c
    */
   void setSeatPos(char p)
   {
      seatPos = p;
   }

   /**
    * Kind of seat: window, aisle, or center
    * @return
    */
   char getSeatType()
   {
      return seatType;
   }

   /**
    * @param t
    */
   void setSeatType(char t)
   {
      seatType = t;
   }

   /**
    * Remove passenger and set to empty
    */
   void clearSeat()
   {
      pass = null;
      empty = true;
   }

   /**
    * Return boolean tracking seat occupancy
    * @return true for empty, false for occupied
    */
   boolean isEmpty()
   {
      return empty;
   }

}