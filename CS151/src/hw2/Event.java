package hw2;

import java.util.*;

/**
 * 
 */
public class Event implements Comparable<Event>
{
   private static final boolean TESTING = true;
   
   private String name;
   private GregorianCalendar start;
   private GregorianCalendar end;

   /**
    * Default with name, start, end as null
    */
   public Event()
   {
      
      this.setName(null);
      this.setStart(null);
      this.setEnd(null);
   }
   
   /**
    * New event with name and start, but end does not apply
    * @param name string which event is called
    * @param start starting time of event
    */
   public Event(String name, GregorianCalendar start)
   {
      
      this.setName(name);
      this.setStart(start);
   }
   
   /**
    * New event with name, start, and end
    * @param name string which event is called
    * @param start starting time of event
    * @param end time which event ends at
    */
   public Event(String name, GregorianCalendar start, GregorianCalendar end)
   {
      
      this.setName(name);
      this.setStart(start);
      this.setEnd(end);
   }
   
   /**
    * Uses Calendar from Java libraries to convert GregorianCalendar into a
    * string with proper formatting 
    */
   public String toString()
   {

      int dW= start.get(Calendar.DAY_OF_WEEK);
      String weekday = "na1";
      String[] listWkday =
      { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday" };
      for (int i = 0; i < listWkday.length; i++)
         if (i + 1 == dW)
            weekday = listWkday[i];

      /*
      if (dayOfWeek == Calendar.SUNDAY)
         weekday = "Sunday";
      else if (dayOfWeek == Calendar.MONDAY)
         weekday = "Monday";
      else if (dayOfWeek == Calendar.TUESDAY)
         weekday = "Tuesday";
      else if (dayOfWeek == Calendar.WEDNESDAY)
         weekday = "Wednesday";
      else if (dayOfWeek == Calendar.THURSDAY)
         weekday = "Thursday";
      else if (dayOfWeek == Calendar.FRIDAY)
         weekday = "Friday";
      else if (dayOfWeek == Calendar.SATURDAY)
         weekday = "Saturday";
      else
         weekday = "na2";
       */
      
      int m = start.get(Calendar.MONTH) + 1;
      String month = "na1";
      String[] listMonth =
      { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December", "" };
      for (int i = 0; i < listMonth.length; i++)
         if (i + 1 == m)
            month = listMonth[i];

      int day = start.get(Calendar.DATE);
      String begin = start.get(Calendar.HOUR_OF_DAY) + ":"
            + start.get(Calendar.MINUTE);
      String stop;
      String result;

      if (this.end != null)
      {
         stop = end.get(Calendar.HOUR_OF_DAY) + ":" + end.get(Calendar.MINUTE);
         result = weekday + ", " + month + " " + day + " " + begin + " - "
               + stop + " " + name;
      }
      else
         result = weekday + ", " + month + " " + day + " " + begin + " " + name;
      return result;
   }

   public int compareTo(Event other)
   {
      // TODO Auto-generated method stub
      return this.getStart().compareTo(other.getStart());
   }

   // Getters and setters
   
   public String getName()
   { return name; }
   public void setName(String name)
   { this.name = name; }

   public GregorianCalendar getStart()
   { return start; }
   public void setStart(GregorianCalendar start)
   { this.start = start; }

   public GregorianCalendar getEnd()
   { return end; }
   public void setEnd(GregorianCalendar end)
   { this.end = end; }
}