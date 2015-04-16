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
      System.out.println("Controller main()");
      
      View calView = new View();
      Model calMod = new Model(calView);

      // Add buttons in view to model
   }
}
