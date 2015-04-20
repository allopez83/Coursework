package hw4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * All GUI and visual related aspects of SimpleCalendar
 * @author Hansen Wu
 *
 */
public class View extends JFrame
{
   private final int DAY_IN_WEEK = 7, WEEK_IN_MONTH = 6, DAY_HOURS = 24;
   private JPanel left, right, top;
   private JPanel day, dayTime, dayEvents;
   private JPanel month;
   private JButton next, prev, createEvent, quit;
   private JLabel monthLabel, dayLabel;
   
   private GregorianCalendar currentDay;
   private Events events;
   
   /**
    * Creates the primary window
    */
   public View()
   {
      System.out.println("View");
      
      monthLabel = new JLabel();
      dayLabel = new JLabel();
   }

   public void display()
   {
      System.out.println("View-display");

      createEvent = new JButton("Create Event");
      prev = new JButton("<-");
      next = new JButton("->");
      quit = new JButton("Quit");
      
      topPanel();
      leftPanel();
      rightPanel();

      top.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      month.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      day.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      dayTime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      dayEvents.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      day.setPreferredSize(new Dimension(300, 800));

      // Main
      this.setLayout(new BorderLayout());
      this.add(top, BorderLayout.NORTH);
      this.add(left, BorderLayout.WEST);
      this.add(right, BorderLayout.CENTER);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   private void topPanel()
   {
      System.out.println("View-topPanel");
      
      top = new JPanel();

      top.add(createEvent);
      top.add(prev);
      top.add(next);
      top.add(quit);
   }

   /**   
    * Creates left SimpleCalendar panel with month view along with create event
    * and navigation buttons
    */
   private void leftPanel()
   {
      System.out.println("View-leftPanel");
      
      left = new JPanel(new BorderLayout());
      month = new JPanel(new GridBagLayout());

      left.add(monthLabel, BorderLayout.NORTH);
      left.add(month, BorderLayout.CENTER);
      
      drawMonth(getMonthDelay()); // Initially nothing
   }

   private Integer getMonthDelay()
   {
      GregorianCalendar gc = (GregorianCalendar) currentDay.clone();
      gc.set(Calendar.DATE, 1);
      return gc.get(Calendar.DAY_OF_WEEK);
   }

   /**
    * Creates right SimpleCalendar panel with day view
    */
   private void rightPanel()
   {
      System.out.println("View-rightPanel");

      right = new JPanel(new BorderLayout());
      day = new JPanel(new BorderLayout());
      dayTime = new JPanel(new GridLayout(24*2, 2));
      dayEvents = new JPanel(new BorderLayout());

      right.add(dayLabel, BorderLayout.NORTH);
      right.add(day, BorderLayout.CENTER);

      day.add(dayTime, BorderLayout.WEST);
      day.add(dayEvents, BorderLayout.CENTER);

      drawDay(events); // Draw the day
   }

   /**
    * Redraw the month and day view to reflect new day or view to look at
    * @param events
    */
   public void update(Events events, Integer i)
   {
      System.out.println("View-update");

      drawDay(events);
      drawMonth(i);
   }

   /**
    * Draw a month
    */
   public void drawMonth(Integer delay)
   {
      System.out.println("View-drawMonth");

      int days = 1;
      int countdown = delay;
      int max = currentDay.getActualMaximum(Calendar.DATE);
      GridBagConstraints c = new GridBagConstraints();
      
      String[] weekdayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
      JLabel weekday;
      JButton dayButton;

      // Draw weekday labels
      for (int i = 0; i < DAY_IN_WEEK; i++) // week
      {
         weekday = new JLabel(weekdayNames[i]);
         c.fill = GridBagConstraints.BOTH;
         c.gridx = i;
         c.gridy = 0;
         month.add(weekday, c);
      }

      // Draw on buttons
      for (int i = 0; i < WEEK_IN_MONTH && days < max + 1; i++) // week
      {
         for (int j = 0; j < DAY_IN_WEEK && days < max + 1; j++) // day
         {
            countdown--;
            if (days > countdown)
            {
               dayButton = new JButton(days + "");
               days++;
               c.fill = GridBagConstraints.BOTH;
               c.ipady = 20;
               c.gridx = j;
               c.gridy = i+1  ; // +1 because of weekday row
               month.add(dayButton, c);
            }
         }
      }
   }

   /**
    * Draw day on day panel using GridBagLayout
    * @param events
    */
   public void drawDay(Events events)
   {
      System.out.println("View-drawDay");

      // Draw time of day labels
      for (int i = 0; i < DAY_HOURS; i++) // Week
      {
         dayTime.add(new JLabel(i + ":00"));
         dayTime.add(new JLabel("")); // Extra space
      }

      // day.add(new DayViewComponent(day));
      // Draw rectangles and stuff
      // TODO test event remove later
      events = new Events(currentDay);
      events.add("zerohour", 0, 600);
      events.add("same end time", 800, 1400);
      events.add("same start time", 1400, 1530);
      events.add("long name for event off in the middle of nowhere", 1700, 2300);
      drawEvents(events);
      events = null;
   }

   /**
    * Internal method for drawing new events on a given day
    * @param events
    */
   private void drawEvents(Events events)
   {
      System.out.println("View-drawEvents");
      GridBagConstraints c = new GridBagConstraints();
      if (events != null)
      {
         System.out.println("drawing...");
         // Draw the day
         
         // Draw a pseudo event
         DayViewComponent dvc = new DayViewComponent(dayEvents, events);
         this.dayEvents.add(dvc);
      }
      else
      {
         // Draw simple background
      }
   }

   public void setMonthText(String m)
   {
      System.out.println("View-setMonthText");
      
      this.monthLabel.setText(m);
   }

   public void setDay(GregorianCalendar gc)
   {
      System.out.println("View-setDay");
      
      this.currentDay = gc;
   }

   public void setDayText(String d)
   {
      System.out.println("View-setDayText");
      
      this.dayLabel.setText(d);
   }

   public void setEvents(Events events)
   {
      System.out.println("View-setEvents");
      this.events = events;
   }

   public void repaint()
   {
      System.out.println("View-repaint");
      super.repaint();
   }

}
