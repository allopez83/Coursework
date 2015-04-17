package hw4;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class DayViewComponent extends JComponent
{
   // Keep track of panel dimensions
   private JPanel panel;
   int panelWidth, panelHeight;
   Rectangle2D border;
   Events ev;
   
   public DayViewComponent(JPanel p)
   {
      panel = p;
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;

      // Update dimensions
      panelWidth = panel.getWidth();
      panelHeight = panel.getHeight();

      // Draw border
      Rectangle2D rect = new Rectangle2D.Float(panelWidth / 10,
            panelHeight / 10, panelWidth / 10 * 8, panelHeight / 10 * 8);
      g2.draw(rect);

   }

}
