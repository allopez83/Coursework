package hw4;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Engine of SimpleGUICalendar, interacts with data, does the processing, and
 * manipulates data
 * @author Hansen Wu
 *
 */
public class Model
{

   // Contains days and events
   LinkedList calendar;
   Iterator calIterator;

   // Buttons on view and associated listeners
   ArrayList buttons;
   ArrayList listeners;

   // Stores the day user's system is on
   GregorianCalendar today;
   // Day that calendar is looking at
   GregorianCalendar currentDay;

   public Model()
   {
      System.out.println("Model");
      calIterator = calendar.iterator();
      //calIterator go to today
   }

   public void attachListeners()
   {
      System.out.println("attachListeners");
   }
}
