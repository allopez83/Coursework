package hw2;

import java.util.*;

/**
 * 
 */
public class CopyOfMyCalendar
{
   private static final boolean TESTING = true;
   
   /**
    * Data is organized so that the HashMap represents the entire calendar. Each
    * entry is an ArrayList representing one day, and stores the individual
    * Event objects.
    */
   private HashMap<GregorianCalendar, ArrayList<Event>> events;
//   public GregorianCalendar firstDay;
//   public GregorianCalendar lastDay;

   /**
    * New Calendar
    */
   public CopyOfMyCalendar()
   {
      if (TESTING) System.out.println("Calendar()");

      events = new HashMap<GregorianCalendar, ArrayList<Event>>();
//      firstDay = null;
//      lastDay = null;
   }
   
   /**
    * Add an event in the events hashmap
    * @param e event to save
    */
   void addEvent(Event e)
   {
      if (TESTING) System.out.println("addEvent()");
      
//      if (events.isEmpty())
//      {
//         firstDay = e.getStart();
//         lastDay = e.getStart();
//      }
      
   }

   /**
    * @param day
    */
   void printDay(GregorianCalendar day)
   {
      // TODO implement here
   }

   /**
    * @param day
    */
   void printMonth(GregorianCalendar day)
   {
      // TODO implement here
   }

   /**
    * @param day
    */
   void printMainMonth(GregorianCalendar day)
   {
      // TODO implement here
   }

   /**
    * Use HashMap.values() to get all values, then iterate and print
    */
   void listAll()
   {
      // TODO implement here
   }

   /**
    * @param day
    */
   void deleteDay(GregorianCalendar day)
   {
      // TODO implement here
   }

   public static void get()
   {
      // TODO Auto-generated method stub
      
   }

}