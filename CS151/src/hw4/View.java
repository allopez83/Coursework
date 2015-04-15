package hw4;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * All GUI and visual related aspects of SimpleGUICalendar
 * @author Hansen Wu
 *
 */
public class View extends JFrame
{
   /**
    * Creates the primary window
    */
   public View()
   {
      System.out.println("View");
      
      // Left
      JPanel leftPanel = new JPanel(new BorderLayout());
      JButton createEvent = new JButton("Create Event");
      leftPanel.add(createEvent, BorderLayout.NORTH);
      
      // Right
      JPanel rightPanel = new JPanel(new BorderLayout());
      rightPanel.add(new JButton("Place Holder"));      
      
      // Main
      this.setLayout(new BorderLayout());
      this.add(leftPanel, BorderLayout.WEST);
      this.add(rightPanel, BorderLayout.EAST);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }
   
   /**
    * Draw specified day on the right frame
    * @param day
    */
   public void drawDay(Day d)
   {
      
   }
}