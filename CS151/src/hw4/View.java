package hw4;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
   final int DAY_IN_WEEK = 7, WEEK_IN_MONTH = 6;
   JPanel left, right, top, month;
   JButton next, prev, createEvent, quit;
   JLabel monthLabel;
   String monthName;
   Day day;
   GregorianCalendar current;

   /**
    * Creates the primary window
    */
   public View()
   {
      System.out.println("View");
      
      current = new GregorianCalendar();
      day = new Day();
      day.setDay(current);
      monthName = "SomeMonth"; // TODO Temporary

      topPanel();
      leftPanel();
      rightPanel();

      // Main
      this.setLayout(new BorderLayout());
      this.add(top, BorderLayout.NORTH);
      this.add(left, BorderLayout.WEST);
      this.add(right, BorderLayout.EAST);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   private void topPanel()
   {
      System.out.println("topPanel");
      top = new JPanel();

      prev = new JButton("<-");
      next = new JButton("->");
      quit = new JButton("Quit");
      
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
      System.out.println("leftPanel");
      left = new JPanel(new BorderLayout());
      month = new JPanel(new GridBagLayout());

      createEvent = new JButton("Create Event");
      monthLabel = new JLabel("SomeMonth");

      left.add(createEvent, BorderLayout.NORTH);
      left.add(monthLabel, BorderLayout.CENTER);
      left.add(month, BorderLayout.SOUTH);
      drawMonth(day);
   }

   /**
    * Creates right SimpleCalendar panel with day view
    */
   private void rightPanel()
   {
      System.out.println("rightPanel");
      right = new JPanel(new BorderLayout());
      
      // drawDay()
      
      right.add(new JButton("Place Holder"));
   }
   
   /**
    * Redraw the month and day view to reflect new day or view to look at
    * @param d
    */
   public void update(Day d)
   {
      drawDay(d);
      drawMonth(d);
   }
   
   /**
    * Draw specified day on right panel
    * @param day
    */
   public void drawDay(Day d)
   {
      if (d == null)
      {
         
      }
      // TODO Stuff
   }

   /**
    * Draw specified day on right panel
    * @param day
    */
   public void drawMonth(Day d)
   {
      // TODO more stuff
      // Find total days in month
      
      // find which day of week the first day of month starts on
      
      // Loop through by drawing blanks where day doesn't exist, going to next
      // row when end of week reached, for all days in a month

      int days = 1;
      int max = d.getDay().getActualMaximum(Calendar.DATE);

      JButton button;
      // Draw on buttons
      GridBagConstraints c = new GridBagConstraints();
      for (int i = 0; i < WEEK_IN_MONTH && days < max + 1; i++) // week
      {
         for (int j = 0; j < DAY_IN_WEEK && days < max + 1; j++) // day
         {
            button = new JButton(days+"");
            days++;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = j;
            c.gridy = i;
            month.add(button, c);
         }
      }
   }

}
