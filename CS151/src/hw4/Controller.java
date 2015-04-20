package hw4;

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
      model.setView(view);
      view.display();

      setUpButtons();
      // Add buttons in view to model
   }

   private void setUpButtons()
   {
      
   }
}
