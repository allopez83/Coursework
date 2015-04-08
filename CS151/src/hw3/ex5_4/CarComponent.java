package hw3.ex5_4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * A component that shows a scene composed of items.
 */
public class CarComponent extends JComponent
{
   private DataModel m;
   private CarShape car;

   public CarComponent(DataModel model)
   {
      System.out.println("CC");
      m = model;
   }

   public void paintComponent(Graphics g)
   {
      System.out.println("CC paintComponent");

      car = new CarShape(20, 20, m.scale * 3);
      Graphics2D g2 = (Graphics2D) g;
      car.draw(g2);
   }
}