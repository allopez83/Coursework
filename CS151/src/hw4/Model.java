package hw4;

import java.util.ArrayList;
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

   public Model(View calView)
   {
      System.out.println("Model");
      calendar = new LinkedList<Day>();
      calIterator = calendar.iterator();
      todayGC = new GregorianCalendar();
      currentDay = todayGC;
      Day currentDay;
      // calIterator go to today
   }

   public void attachListeners()
   {
      System.out.println("attachListeners");
   }

   public void next()
   {
      System.out.println("next");
      // check with iterator if next day has events
         // Send day to view for redraw
      // Check if day is on new month
         // Send day to month for redraw
   }

   public void prev()
   {
      System.out.println("prev");

   }
}
