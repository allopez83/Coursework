package hw2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main
{

   public static void main(String[] args)
   {
      MyCalendar myCal = new MyCalendar();

      for (int i = 0; i < 5; i++)
      {
         GregorianCalendar gc = new GregorianCalendar();
         gc.add(Calendar.SECOND, i);
         Event event = new Event("Some Event #"+i, gc);
         myCal.addEvent(event);
      }

      myCal.listAll();
   }

   private static void calendarGet()
   {
      GregorianCalendar cal = new GregorianCalendar();
      System.out.println(cal.get(Calendar.YEAR));
      System.out.println(cal.get(Calendar.MONTH) + 1);
      System.out.println(cal.get(Calendar.DAY_OF_MONTH));
      // etc.
   }
}
