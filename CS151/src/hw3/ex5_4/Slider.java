package hw3.ex5_4;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Slider extends JFrame
{

   JTextField[] fieldList;

   private void slider()
   {
      final Container contentPane = this.getContentPane();
      setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

      final int FIELD_WIDTH = 11;
      JTextField textField = new JTextField("initial text", FIELD_WIDTH);
      add(textField);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

}
