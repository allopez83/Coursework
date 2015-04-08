package hw3.ex5_8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Presents a phone GUI for the voice mail system. When multiple Telephones are
 * created, closing the last one will exit the program.
 */
public class Telephone
{
   /**
    * Constructs a telephone with a speaker, keypad, and microphone.
    */
   public Telephone()
   {

      numberOfPhones = numberOfPhones + 1;
      JPanel speakerPanel = new JPanel();
      speakerPanel.setLayout(new BorderLayout());
      speakerPanel.add(new JLabel("Speaker:"), BorderLayout.NORTH);
      speakerText = new JTextArea(10, 25);
      speakerScrollField = new JScrollPane(speakerText,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      speakerPanel.add(speakerScrollField, BorderLayout.CENTER);

      String keyLabels = "123456789*0#";
      JPanel keyPanel = new JPanel();
      keyPanel.setLayout(new GridLayout(4, 3));
      for (int i = 0; i < keyLabels.length(); i++)
      {
         final String label = keyLabels.substring(i, i + 1);
         JButton keyButton = new JButton(label);
         keyPanel.add(keyButton);
         keyButton.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               connect.dial(label);
            }
         });
      }

      final JTextArea microphoneText = new JTextArea(10, 25);
      

      JButton speechButton = new JButton("Send speech");
      speechButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            connect.record(microphoneText.getText());
            microphoneText.setText("");
         }
      });

      JButton hangupButton = new JButton("Hangup");
      hangupButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            connect.hangup();
         }
      });

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(speechButton);
      buttonPanel.add(hangupButton);

      JScrollPane microphoneScrollField = new JScrollPane(microphoneText,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      JPanel microphonePanel = new JPanel();
      microphonePanel.setLayout(new BorderLayout());
      microphonePanel.add(new JLabel("Microphone:"), BorderLayout.NORTH);
      microphonePanel.add(microphoneScrollField, BorderLayout.CENTER);
      microphonePanel.add(buttonPanel, BorderLayout.SOUTH);

      final JFrame frame = new JFrame();

      frame.add(speakerPanel, BorderLayout.NORTH);
      frame.add(keyPanel, BorderLayout.CENTER);
      frame.add(microphonePanel, BorderLayout.SOUTH);

      // Replace the default close operation with a window event listener.
      frame.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent event)
         {
            if (numberOfPhones == 1)
               System.exit(0);
            else
            {
               numberOfPhones = numberOfPhones - 1;
               frame.dispose();
            }
         }
      });

      frame.pack();
      frame.setVisible(true);
   }

   /**
    * Give instructions to the mail system user.
    */
   public void speak(String output)
   {
      speakerText.setText(output);
   }

   public void run(Connection c)
   {
      connect = c;
   }

   private JTextArea speakerText;
   private JScrollPane speakerScrollField;
   private Connection connect;
   private static int numberOfPhones = 0;
}
