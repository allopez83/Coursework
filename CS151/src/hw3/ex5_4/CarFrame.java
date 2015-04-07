package hw3.ex5_4;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A class that implements an Observer object that displays a barchart view of a
 * data model.
 */
public class CarFrame extends JFrame implements ChangeListener
{
   CarComponent car = new CarComponent();
   /**
    * Constructs a BarFrame object
    * @param dataModel the data that is displayed in the barchart
    */
   public CarFrame()
   {
      this.setSize(400, 300);
      setLocation(0, 100);
      this.setLayout(new BorderLayout());

      this.add(new JTextField("initial text", 0));
      
      paint(car);
//      this.paintComponents(car);
      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

   /**
    * Called when the data in the model is changed.
    * @param e the event representing the change
    */
   public void stateChanged(ChangeEvent e)
   {
      repaint();
   }
}
