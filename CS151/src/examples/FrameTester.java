package examples;

import java.awt.*;
import javax.swing.*;

public class FrameTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      
      final int FRAME_WIDTH = 300;
      final int FRAME_HEIGHT = 200;
      
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      
      JButton helloButton = new JButton("Say Hello");
      
      final int FIELD_WIDTH = 20;
      JTextField textField = new JTextField(FIELD_WIDTH);
      textField.setText("Click a button!");
      
      frame.setLayout(new FlowLayout());
      
      frame.add(helloButton);
      frame.add(textField);
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
