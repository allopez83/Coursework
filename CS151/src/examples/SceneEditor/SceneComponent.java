package examples.SceneEditor;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * A component that shows a scene composed of shapes.
 */
public class SceneComponent extends JComponent
{
   private ArrayList<SceneShape> shapes;
   private Point mousePoint;

   public SceneComponent()
   {
      shapes = new ArrayList<SceneShape>();
      addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent event)
         {
            mousePoint = event.getPoint();
            for (SceneShape s : shapes)
            {
               if (s.contains(mousePoint))
                  s.setSelected(!s.isSelected());
            }
            repaint();
         }
      });
      addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseDragged(MouseEvent event)
         {
            Point lastMousePoint = mousePoint;
            mousePoint = event.getPoint();
            double dx = mousePoint.getX() - lastMousePoint.getX();
            double dy = mousePoint.getY() - lastMousePoint.getY();
            for (SceneShape s : shapes)
            {
               if (s.isSelected())
                  s.translate((int) dx, (int) dy);
            }
            repaint();
         }
      });
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (SceneShape s : shapes)
      {
         s.draw(g2);
         if (s.isSelected())
            s.drawSelection(g2);
      }
   }

   /**
    * Adds a shape to the scene.
    * @param s the shape to add
    */
   public void add(SceneShape s)
   {
      shapes.add(s);
      repaint();
   }

   /**
    * Removes all selected shapes from the scene.
    */
   public void removeSelected()
   {
      for (int i = shapes.size() - 1; i >= 0; i--)
      {
         SceneShape s = shapes.get(i);
         if (s.isSelected())
            shapes.remove(i);
      }
      repaint();
   }
}