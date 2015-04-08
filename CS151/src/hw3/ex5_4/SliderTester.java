package hw3.ex5_4;

/**
 * @author rayting
 * @copyright 2015-04-06
 *
 */
public class SliderTester
{
   public static void main(String[] args)
   {
      Integer scale = 50;
      
      System.out.println("datamodel");
      DataModel model = new DataModel(scale);
      
      System.out.println("carframe");
      CarFrame c = new CarFrame(model);

      System.out.println("attach");
      model.attach(c);

      System.out.println("slider");
      Slider s = new Slider(model);
   }
}
