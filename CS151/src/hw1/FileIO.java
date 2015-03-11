package hw1;

/**
 * HW #1 - Flight Reservation
 * @copyright 2015-02-20
 * @version 0.1
 */
public class FileIO
{
   private String fileName;

   /**
     * 
     */
   public FileIO()
   {
   }

   /**
    * @param flight
    */
   public FileIO(String flight)
   {
      // TODO implement here
      setFileName(flight+".txt");
   }

   /**
    * @return
    * 
    */
   String getFileName()
   {
      return fileName;
   }

   /**
    * @param flight
    */
   void setFileName(String flight)
   {
      fileName = flight;
   }

   /**
     * 
     */
   void readFlight()
   {
      // TODO implement here
   }

   /**
    * @param flight
    */
   void writeFlight(Flight flight)
   {
      // TODO implement here
   }

}