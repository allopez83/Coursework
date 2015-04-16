package hw4;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;

/**
 * Engine of SimpleCalendar, interacts with data, does the processing, and
 * manipulates data
 * @author Hansen Wu
 *
 */
public class Model
{
   // Contains days and events
   HashMap<Integer, Day> calendar;

   // Buttons on view and associated listeners
   ArrayList<JButton> buttons;
   ArrayList<EventListener> listeners;

   // Stores the day user's system is on
   GregorianCalendar today;
   // Day that calendar is looking at
   GregorianCalendar currentDay;
   
   View view;

   public Model(View calView)
   {
      System.out.println("Model");
      
      // Read file
      // loadFile();
      
      calendar = new HashMap<Integer, Day>();
      today = new GregorianCalendar();
      currentDay = today;
      // calIterator go to today
      
      view = calView;
   }

   public void attachListeners()
   {
      System.out.println("attachListeners");
   }

   public void next()
   {
      System.out.println("next");
      // Advance
      int oldMonth = currentDay.get(Calendar.MONTH);
      currentDay.add(Calendar.DATE, 1);
      int newMonth = currentDay.get(Calendar.MONTH);
      // Check if month changed
      boolean monthSame = (oldMonth == newMonth);
      
      // Get
      Integer key = gcToInt(currentDay);
      Day result = calendar.get(key);
      
      // Update view
      view.current = this.currentDay;
      view.monthName = intToMonth(currentDay.get(Calendar.MONTH));
      view.drawDay(result);

      // Advance and get from hashmap
         // Send day to view for redraw
      // Check if day is on new month
         // Send day to month for redraw
   }

   private String intToMonth(int i)
   {
      String month = "wrong";
      DateFormatSymbols dfs = new DateFormatSymbols();
      String[] months = dfs.getMonths();
      if (i >= 0 && i <= 11 ) {
          month = months[i];
      }
      return month;
   }

   public void prev()
   {
      System.out.println("prev");
      
//      Day d = calIterator.next();
//      view.drawDay(d);
      
   }
   
   /**
    * Produces an integer in yyyymmdd format from given Gregorian Calendar
    * @param gc Gregorian Calendar object to extract date from
    * @return date of gc in yyyymmdd format, i.e. 20150024 for January 24th 2015
    */
   private Integer gcToInt(GregorianCalendar gc)
   {
      int year = gc.get(Calendar.YEAR);
      int month = gc.get(Calendar.MONTH);
      int day = gc.get(Calendar.DATE);

      int result = 0;
      result += day;
      result += month * 100;
      result += year * 100 * 100;
      // should result in yyyymmdd int

      return result;
   }

}
