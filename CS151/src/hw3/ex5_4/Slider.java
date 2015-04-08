package hw3.ex5_4;

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider extends JFrame
{
   DataModel model;
   Integer scale;

   public Slider(DataModel d)
   {
      model = d;
      
      final Container contentPane = this.getContentPane();
      setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

      JSlider slider = new JSlider();
      System.out.println(slider.getMinimum() + " " + slider.getValue() + " "
            + slider.getMaximum());

      ChangeListener l = new ChangeListener()
      {
         public void stateChanged(ChangeEvent e)
         {
            scale = slider.getValue();
            System.out.println("slider moved -> " + scale);
            d.update(scale);
         }
      };

      scale = slider.getValue();
      slider.addChangeListener(l);
      add(slider);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }
}
