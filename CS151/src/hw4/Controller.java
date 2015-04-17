package hw4;

/**
 * Initializes the SimpleCalendar program
 * @author Hansen Wu
 *
 */
public class Controller
{
   public Controller()
   {
      System.out.println("Controller");
      
      Model calMod = new Model();
      View calView = new View();
      calMod.setView(calView);
      calView.display();

      // Add buttons in view to model
   }
}
