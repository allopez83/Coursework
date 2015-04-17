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
   HashMap<Integer, Events> calendar;

   // Buttons on view and associated listeners
   ArrayList<JButton> buttons;
   ArrayList<EventListener> listeners;

   // Stores the day user's system is on
   GregorianCalendar today;
   // Day that calendar is looking at
   GregorianCalendar currentDay;

   View view;
   DateFormatSymbols dfs;

   public Model()
   {
      System.out.println("Model");
      
      // Read file
      // loadFile();
      
      calendar = new HashMap<Integer, Events>();
      today = new GregorianCalendar();
      currentDay = today;
      
      dfs = new DateFormatSymbols();
   }
   
   /**
    * Initial association between model and view
    * @param v
    */
   public void setView(View v)
   {
      System.out.println("Model-setView");
      
      this.view = v;
      view.setDay(currentDay);
      view.setDayText(getDayViewString(currentDay));
      view.setMonthText(getMonthViewString(currentDay));
   }

   private void updateView()
   {
      System.out.println("Model-updateView");

      // Are there any events on the current day?
      Integer key = gcToInt(currentDay);
      Events events = calendar.get(key);
      
      // Update date information on view
      view.setDay(currentDay);
      view.setDayText(getDayViewString(currentDay));
      view.setMonthText(getMonthViewString(currentDay));
      view.update(events, 3);
   }

   public void next()
   {
      System.out.println("Model-next");

      currentDay.add(Calendar.DATE, 1);
      updateView();
   }

   public void prev()
   {
      System.out.println("Model-prev");

      currentDay.add(Calendar.DATE, 1);
      updateView();
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

   /**
    * Get month as a String from a GregorianCalendar
    * @param gc GregorianCalendar to extract month name from
    * @return String of month name, ex: January, February, etc.
    */
   private String getMonthViewString(GregorianCalendar gc)
   {
      int monthInt = gc.get(Calendar.MONTH);
      String[] months = dfs.getMonths();
      return months[monthInt] + " " + gc.get(Calendar.YEAR);
   }

   private String getDayViewString(GregorianCalendar gc)
   {
      String result = "";

      // Get time units
      String weekday = dfs.getWeekdays()[currentDay.get(Calendar.DAY_OF_WEEK)];
      int month = currentDay.get(Calendar.MONTH) + 1;
      int day = currentDay.get(Calendar.DATE) + 1;
      result = weekday + " " + month + "/" + day;
      return result;
   }

}
