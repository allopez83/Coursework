package hw3.ex5_2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * A class that implements an Observer object that displays a barchart view of a
 * data model.
 */
public class BarFrame extends JFrame implements ChangeListener
{
   /**
    * Constructs a BarFrame object
    * @param dataModel the data that is displayed in the barchart
    */
   public BarFrame(DataModel dataModel)
   {
      this.dataModel = dataModel;
      a = dataModel.getData();

      setLocation(0, 200);
      setLayout(new BorderLayout());

      Icon barIcon = new Icon()
      {
         public int getIconWidth() { return ICON_WIDTH; }
         public int getIconHeight() { return ICON_HEIGHT; }

         public void paintIcon(Component c, Graphics g, int x, int y)
         {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.red);

            double max = (a.get(0)).doubleValue();
            for (Double v : a)
            {
               double val = v.doubleValue();
               if (val > max)
                  max = val;
            }

            double barHeight = getIconHeight() / a.size();

            int i = 0;
            for (Double v : a)
            {
               double value = v.doubleValue();

               double barLength = getIconWidth() * value / max;

               Rectangle2D.Double rectangle = new Rectangle2D.Double(0,
                     barHeight * i, barLength, barHeight);
               i++;
               g2.fill(rectangle);
            }
         }
      };

      // Listen for mouse actions on bars
      MouseAdapter l = new MouseAdapter()
      {
         public void mouseClicked(MouseEvent click)
         {
            System.out.println("Mouse clicked: " + (click.getX() - 7) + ", "
                  + (click.getY() - 29));

            int chosenBar = (click.getY() - 29)
                  / (barIcon.getIconHeight() / a.size());
            System.out.println("Which bar: " + chosenBar);

            double barUsage = (double) (click.getX() - 8)
                  / (double) barIcon.getIconWidth();
            System.out.println("Amount of bar " + barUsage);

            double largest = 0;
            for (Double d : a) { if (d > largest) largest = d; }
            double newSize = largest * barUsage;

            // Update graph
            dataModel.update(chosenBar, newSize);
         }
      };
      this.addMouseListener(l);

      add(new JLabel(barIcon));

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   /**
    * Called when the data in the model is changed.
    * @param e the event representing the change
    */
   public void stateChanged(ChangeEvent e)
   {
      a = dataModel.getData();
      repaint();
   }

   private ArrayList<Double> a;
   private DataModel dataModel;

   private static final int ICON_WIDTH = 200;
   private static final int ICON_HEIGHT = 200;
}
