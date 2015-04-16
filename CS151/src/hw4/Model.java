package hw4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EventListener;
import java.util.GregorianCalendar;
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
   LinkedList<Day> calendar;
   Iterator<Day> calIterator;

   // Buttons on view and associated listeners
   ArrayList<JButton> buttons;
   ArrayList<EventListener> listeners;

   // Stores the day user's system is on
   GregorianCalendar todayGC;
   // Day that calendar is looking at
   GregorianCalendar currentDay;
   
   Day prev, current, next;
   
   View view;

   public Model(View calView)
   {
      System.out.println("Model");
      
      // Read file
      
      calendar = new LinkedList<Day>();
      calIterator = calendar.iterator();
      todayGC = new GregorianCalendar();
      currentDay = todayGC;
      Day currentDay;
      // calIterator go to today
      
      view = calView;
   }

   public void attachListeners()
   {
      System.out.println("attachListeners");
   }

   public void next()
   {
      // Try to get next element
      next = calIterator.next();

      // Advance and check if next matches
      currentDay.add(Calendar.DATE, 1);
      if (next.equals(currentDay)) // If next day entry is the current day
      {
         current = next;
         if (calIterator.hasNext()) // Advance
            next = calIterator.next();
      }
      // Else, currentDay still hasn't reached next

      view.drawDay(current);
      System.out.println("next");
      
      // check with iterator if next day has events
         // Send day to view for redraw
      // Check if day is on new month
         // Send day to month for redraw
   }

   public void prev()
   {
      System.out.println("prev");
      
      Day d = calIterator.next();
      view.drawDay(d);
      
   }

}
