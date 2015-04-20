package hw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Events implements Comparable<Events>
{
   // Each day obj is on a GregorianCalendar day
   private GregorianCalendar day;
   private ArrayList<Event> events;

   public Events(GregorianCalendar gc)
   {
      this.day = gc;
      events = new ArrayList<Event>();
   }
   
   void setDay(GregorianCalendar d)
   {
      this.day = d;
   }

   GregorianCalendar getDay()
   {
      return day;
   }

   public void add(String name, int start, int end)
   {
      events.add(new Event(name, start, end));
   }

   public boolean equals(Events d)
   {
      if (this.compareTo(d) == 0)
         return true;
      else
         return false;
   }
   
   public ArrayList<Event> getEvents()
   {
      return events;
   }
   
   public int getQuantity()
   {
      return events.size();
   }

   public boolean equals(GregorianCalendar gc)
   {
      System.out.println("equals gc");
      // extract y/m/d and send to other equals()
      int year = gc.get(Calendar.YEAR);
      int month = gc.get(Calendar.MONTH);
      int day = gc.get(Calendar.DATE);
      return this.equals(year, month, day);
   }

   /**
    * Check if the given day is represented by this Day object
    * @param y year to compare with
    * @param m month to compare with
    * @param d day to compare with
    * @return boolean representing if the day matches the given values
    */
   public boolean equals(int y, int m, int d)
   {
      // check if y/m/d matches
      System.out.println("equals ymd");
      if (this.day.get(Calendar.YEAR) == y)
         if (this.day.get(Calendar.MONTH) == m)
            if (this.day.get(Calendar.DATE) == d)
               return true;
      return false;
   }

   /**
    * A Day is equivalent to another only when it's on the same day. Time of day
    * is irrelevant
    */
   public int compareTo(Events d)
   {
      // Check if it's the same day
      if (this.equals(d.getDay()))
      {
         System.out.println("compareTo - equal");
         return 0;
      }
      // Else, compareTo
      System.out.println("compareTo - revert");
      return this.getDay().compareTo(d.getDay());
   }
   
}

class Event
{
   private String name;
   private int start;
   private int end;

   /**
    * Event entry with the given data, where time is in 24 hours, i.e. 1425
    * -> 2:25 pm
    * @param n name of the event
    * @param s start of the event
    * @param e end of the event
    */
   public Event(String n, int s, int e)
   {
      this.setName(n);
      this.setStart(s);
      this.setEnd(e);
   }

   // Get and Set methods
   
   public String getStartHours()
   {
      return String.format("%02d", start / 100);
   }

   public String getStartMinutes()
   {
      return String.format("%02d", start % 100);
   }

   public String getEndHours()
   {
      return String.format("%02d", end / 100);
   }

   public String getEndMinutes()
   {
      return String.format("%02d", end % 100);
   }

   public String getName() { return name; }
   private void setName(String name) { this.name = name; }
   
   public int getStart() { return start; }
   private void setStart(int start) { this.start = start; }

   public int getEnd() { return end; }
   private void setEnd(int end) { this.end = end; }

   public String toSring()
   {
      String s = getStartHours() + ":" + getStartMinutes();
      String e = getEndHours() + ":" + getEndMinutes();
      return s + "-" + e + "; " + name;
   }
}
