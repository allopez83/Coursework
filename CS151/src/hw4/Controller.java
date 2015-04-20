package hw4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Initializes the SimpleCalendar program
 * @author Hansen Wu
 *
 */
public class Controller
{
   View view;
   Model model;

   public Controller()
   {
      System.out.println("Controller");

      model = new Model();
      view = new View();
      
      setUpButtons();
      
      model.setView(view);
      view.display();

   }

   /**
    * Gives buttons their listeners
    */
   private void setUpButtons()
   {
      view.addQuitListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println(" > Quit pressed");
//            model.quit();
         }
      });

      view.addPrevListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println(" > Prev pressed");
            model.prev();
         }
      });

      view.addNextListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println(" > Next pressed");
            model.next();
         }
      });

      view.addCreateListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println(" > Create pressed");
//            model.createMenu();
         }
      });

      view.addMonthViewListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println(" > MonthView pressed");
            JButton source = (JButton) e.getSource();
            System.out.println(source.getText());
//            model.jumpToDay();
         }
      });
   }
}
