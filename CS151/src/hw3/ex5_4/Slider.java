package hw3.ex5_4;

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Slider extends JFrame
{

   JTextField[] fieldList;

   public Slider()
   {
      final Container contentPane = this.getContentPane();
      setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

      JSlider slider = new JSlider();
      System.out.println(slider.getMinimum() + " " + slider.getValue() + " " + slider.getMaximum());
      add(slider);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

}
