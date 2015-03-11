package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class ServiceClass
{
   /*
    * All the row and column numbers represent what the client would see, and
    * not what goes in the array. Ex: a seat at array column index zero would
    * have 'actual' seat column of 1, or seat position 'A', or seat type 'W' for
    * window.
    * 
    * When interacting with the array, numbers are converted into 'array format'
    * for use as indexes to get its position in array. Ex: the seat earlier with
    * column 1 would be decremented by 1, turning it into zero, and could be
    * used in the array, as an index, to find the correct seat.
    */
   private int rowStart;
   private int rowEnd;
   private int rows;
   private int columns;
   private int avail;
   private Seat[][] seating;

   /**
    * Default
    */
   public ServiceClass()
   {
      rowStart = -1;
      rowEnd = -1;
      rows = -1;
      columns = -1;
      avail = -1;
      seating = null;
   }

   /**
    * Create empty service class with the given dimensions. Ex: economy class
    * starts on row 10, ends on row 29, and has 6 columns of seats.
    * @param rowStart row the class begins with (10)
    * @param rowEnd last row of the class (29)
    * @param col total number of columns of seats (6)
    */
   public ServiceClass(int rowStart, int rowEnd, int col)
   {
      this.rowStart = rowStart;
      this.rowEnd = rowEnd;
      rows = (rowEnd - rowStart) + 1;
      this.columns = col;
      avail = rows * col;
      seating = blankSeats();
   }

   /**
     * 
     */
   int getRowStart()
   {
      return rowStart;
   }

   /**
    * @param start
    */
   void setRowStart(int start)
   {
      rowStart = start;
   }

   /**
     * 
     */
   int getRowEnd()
   {
      return rowEnd;
   }

   /**
    * @param end
    */
   void setRowEnd(int end)
   {
      rowEnd = end;
   }

   /**
     * 
     */
   int getColumns()
   {
      return columns;
   }

   /**
    * 
    * @param col
    */
   void setColumns(int col)
   {
      this.columns = col;
   }

   /**
    * @return
    * 
    */
   int getAvailable()
   {
      return avail;
   }

   /**
     * 
     */
   void setAvailable(int avail)
   {
      this.avail = avail;
   }

   /**
    * Decrements avail int by one to signify a seat taken
    */
   void oneLess()
   {
      this.avail--;
   }

   /**
    * Create blank seat array
    * @return
    */
   private Seat[][] blankSeats()
   {
      Seat[][] newSeats = new Seat[rows][columns];

      // Will be put in new seats
      int currentRow = rowStart;
      int currentCol;
      char seatPos;
      char seatType;

      // All rows
      for (int i = 0; i < rows; i++)
      {
         // All columns
         for (int j = 0; j < columns; j++)
         {
            currentCol = j + 1;
            // Convert seat position data to client-readable usable form
            seatPos = columnToSeatPos(currentCol);
            seatType = columnToSeatType(currentCol);
            newSeats[i][j] = new Seat(currentRow, j + 1, seatPos, seatType);
         }
         currentRow++;
      }

      return newSeats;
   }

   /**
    * Try to find location for passenger and mark as their seat
    * @param p passenger to find seat for
    * @return seat number and column, or null if no seat found
    */
   String addPassenger(Passenger p)
   {
      int[] coord = findSeat(p.getSeatPref());
      if (coord[0] == 0 && coord[1] == 0) // Not found
         return null;
      Seat target = seating[coord[0]][coord[1]];
      target.setPassenger(p);
      oneLess();
      String result = "";
      result += target.getRow();
      result += target.getSeatPos();
      return result;
   }

   /**
    * @param p
    * @param row
    * @param column
    */
   String addPassenger(Passenger p, int row, int column)
   {
      Seat target = seating[row][column];
      target.setPassenger(p);
      String result = "";
      result += target.getRow();
      result += target.getSeatPos();
      return result;
   }

   /**
    * @param row
    * @param col
    * @return
    */
   Passenger getPassenger(int row, int col)
   {
      return seating[row - rowStart][col - 1].getPassenger();
   }

   /**
     * 
     */
   void getPassengers()
   {
      // TODO implement here
   }

   /**
    * @param name
    */
   void removePassenger(String name)
   {
      // TODO implement here
   }

   /**
    * @param g
    */
   void removeGroup(String name)
   {
      // TODO implement here
   }

   /**
    * Find seat based on passenger's seat preference
    * @param seatPref
    * @return
    */
   int[] findSeat(char seatPref)
   {
      int[] result = { -1, -1 };
      Seat thisSeat;

      // All rows
      for (int i = 0; i < rows; i++)
      {
         // All columns
         for (int j = 0; j < columns; j++)
         {
            thisSeat = seating[i][j];

            if (thisSeat.getSeatType() == seatPref && thisSeat.isEmpty())
            {
               result[0] = i;
               result[1] = j;
               return result;
            }
         }
      }
      return result;
   }

   /**
    * @param gSize
    */
   int[] largestContiguous(int gSize)
   {
      int largest = 0, current = 0;
      int[] thisCoord = new int[2];
      int[] coord = new int[2];
      int[] result = new int[3];
      Seat thisSeat;

      // All rows
      for (int i = 0; i < rows; i++)
      {
         // All columns
         for (int j = 0; j < columns; j++)
         {
            thisSeat = seating[i][j];

            if (thisSeat.isEmpty())
            {
               current++;
               if (current == 1) // First in a contiguous group
               {
                  thisCoord[0] = i; // Save location
                  thisCoord[1] = j;
               }
               if (current > largest) // largest contiguous
               {
                  largest = current;
                  coord[0] = thisCoord[0];
                  coord[1] = thisCoord[1];
               }
               if (largest == gSize)
               {
                  result[0] = coord[0];
                  result[1] = coord[1];
                  result[2] = largest;
                  return result;
               }
            }
            else
               current = 0;
         }
         current = 0; // New row
      }
      result[0] = coord[0];
      result[1] = coord[1];
      result[2] = largest;
      return result ;

      // TODO implement here
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

   /**
    * Numeric column to char representing seat's position. 1=A, 2=B, 3=C, 4=D,
    * etc.
    * @param column represents the column position of the plane's seat
    * @return position of seat as
    */
   char columnToSeatPos(int column)
   {
      // How much to shift the char's ascii value
      int shift = column - 1;
      return (char) ('A' + shift);
   }

   /**
    * Numeric column to char representing seat type. W, A, C for window, aisle,
    * or column. Assumes only one central column on airplane.
    * @param column represents the column position of the plane's seat
    */
   char columnToSeatType(int column)
   {
      int window1, window2, aisle1, aisle2;
      window1 = 1; // farthest 'left'
      window2 = columns; // farthest 'right'
      aisle1 = columns / 2; // 'left' aisle is half of total columns
      aisle2 = aisle1++; // 'right' aisle is to the right of 'left' aisle seat

      if (column == window1 || column == window2)
         return 'W';
      else if (column == aisle1 || column == aisle2)
         return 'A';
      else
         return 'C';
   }

   /**
    * Char seat position to integer. A=1, B=2, C=3, D=4, etc.
    * @param seatPos position of seat client sees, such as A, B, C, etc.
    */
   int seatPosToColumn(char seatPos)
   {
      return (int) (seatPos - 'A' + 1);
   }

}