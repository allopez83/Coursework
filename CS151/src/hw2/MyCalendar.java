package hw2;

import java.util.*;

/**
 * 
 */
public class MyCalendar
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
   public MyCalendar()
   {

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
      GregorianCalendar start = e.getStart();
      GregorianCalendar key = new GregorianCalendar(start.get(Calendar.YEAR),
            start.get(Calendar.MONTH), start.get(Calendar.DAY_OF_MONTH));

      ArrayList<Event> arrList = events.get(key);
      if (arrList == null) // If there's no events on that day
         arrList = new ArrayList<Event>();
      arrList.add(e); // Else, add it to existing day
      Collections.sort(arrList);
      events.put(key, arrList);

//      if (events.isEmpty())
//      {
//         firstDay = e.getStart();
//         lastDay = e.getStart();
//      }
      
   }

   /**
    * Print events on a specific day
    * @param day the day events should be from
    */
   void printDay(GregorianCalendar day)
   {
      // TODO implement here
   }

   /**
    * Print month view marking days which have an event
    * @param month the month to print out
    */
   void printMonth(GregorianCalendar month)
   {
      // TODO implement here
   }

   /**
    * Initial output showing events of this month
    */
   void printMainMonth()
   {
      // TODO implement here
   }

   /**
    * Use HashMap.values() to get all values, then iterate and print
    */
   void listAll()
   {
      if (events.isEmpty())
      {
         System.out.println("No events found");
         return;
      }

      ArrayList<ArrayList<Event>> extract = new ArrayList<ArrayList<Event>>(
            events.values());

      // Get events in single Arraylist
      ArrayList<Event> allEvents = new ArrayList<Event>();
      ArrayList<Event> someDay = new ArrayList<Event>();
      for (int i = 0; i < extract.size(); i++)
      {
         someDay = extract.get(i);
         for (int j = 0; j < someDay.size(); j++)
            allEvents.add(someDay.get(j));
      }
      Collections.sort(allEvents);

      for (int i = 0; i < allEvents.size(); i++)
      {
         System.out.println(allEvents.get(i));
      }
   }

   /**
    * Delete a specific day
    * @param day a specific day to delete all events from 
    */
   void deleteDay(GregorianCalendar day)
   {
      Object result = events.remove(day);
   }

}