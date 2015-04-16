package hw4;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Launches SimpleCalendar
 * @author Hansen Wu
 *
 */
public class SimpleCalendar
{

   public static void main(String[] args)
   {
      new Controller();

      // Check time conversion
      /*
      int time = 1408;
      System.out.println("raw: " + time);
      System.out.println("hour: " + (time / 100));
      System.out.println("min: " + (time % 100));
       */
      
      // GC to int conversion
      /*
      GregorianCalendar gc = new GregorianCalendar();
      int year = gc.get(Calendar.YEAR);
      int month = gc.get(Calendar.MONTH);
      int day = gc.get(Calendar.DATE);
      int result = 0;
      result += day;
      result += month * 100;
      result += year * 100 * 100;
      System.out.println(result);
      // should result in yyyymmdd int
       */
   }

}
