package hw3.ex5_4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.JComponent;

/**
 * A component that shows a scene composed of items.
 */
public class CarComponent extends JComponent
{
   private CarShape car;
   private Point mousePoint;

   public CarComponent()
   {
      car = new CarShape(20, 20, 50);
      this.addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent event)
         {
            mousePoint = event.getPoint();
            if (!car.contains(mousePoint))
               mousePoint = null;
         }
      });
      addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseDragged(MouseEvent event)
         {
            if (mousePoint == null)
               return;
            Point lastMousePoint = mousePoint;
            mousePoint = event.getPoint();
            double dx = mousePoint.getX() - lastMousePoint.getX();
            double dy = mousePoint.getY() - lastMousePoint.getY();
            car.translate((int) dx, (int) dy);
            repaint();
         }
      });
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      car.draw(g2);;
   }
}