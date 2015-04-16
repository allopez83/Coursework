package hw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Day implements Comparable<Day>
{
   // Each day obj is on a GregorianCalendar day
   GregorianCalendar day;

   ArrayList<Event> events;

   GregorianCalendar getDay()
   {
      return day;
   }

   public void add(String name, int start, int end)
   {
      events.add(new Event(name, start, end));
   }

   /**
    * A Day is equivalent to another only when it's on the same day. Time of day
    * is irrelevant
    */
   public int compareTo(Day d)
   {
      // Check if it's the same day
      if (this.getDay().get(Calendar.YEAR) == d.getDay().get(Calendar.YEAR))
      {
         if (this.getDay().get(Calendar.MONTH) == d.getDay()
               .get(Calendar.MONTH))
         {
            if (this.getDay().get(Calendar.DATE) == d.getDay().get(
                  Calendar.DATE))
            {
               System.out.println("Same day");
               return 0; // on the same day
            }
         }
      }

      // Else, compareTo
      System.out.println("compareTo");
      return this.getDay().compareTo(d.getDay());
   }

   private class Event
   {
      private String name;
      private int start;
      private int end;
      private boolean ampm; // false for AM true for PM

      public Event(String n, int s, int e)
      {
         setName(n);
         this.setStart(s);
         this.setEnd(e);
      }
      
      public int getStartHours()
      { return start/100; }
      public int getStartMinutes()
      { return start%100; }

      public int getEndHours()
      { return end/100; }
      public int getEndMinutes()
      { return end%100; }

      private String getName()
      { return name; }
      private void setName(String name)
      { this.name = name; }
      
      private int getStart()
      { return start; }
      private void setStart(int start)
      { this.start = start; }

      private int getEnd()
      { return end; }
      private void setEnd(int end)
      { this.end = end; }

      
   }
}
