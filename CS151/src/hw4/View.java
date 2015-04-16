package hw4;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
   JPanel left, right, top, month;
   JButton next, prev, createEvent, quit;
   JLabel monthName;

   /**
    * Creates the primary window
    */
   public View()
   {
      System.out.println("View");

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
      month = new JPanel(new GridLayout(0, 7));

      createEvent = new JButton("Create Event");
      monthName = new JLabel("SomeMonth");

      left.add(createEvent, BorderLayout.NORTH);
      left.add(monthName, BorderLayout.CENTER);
      left.add(month, BorderLayout.SOUTH);
      
      month.add(new JButton("MonthHere"), BorderLayout.CENTER);
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
    * Draw specified day on right panel
    * @param day
    */
   public void drawDay(Day d)
   {
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
   }
}