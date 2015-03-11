package assignment_10.mainpackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Contains a program that creates Employee Objects
 * 
 * @author HW
 *
 */
public class GUIApp extends JFrame implements ActionListener
{
   private static final long serialVersionUID = 1L;

   private static final boolean TESTING_ACTION = true;

	private static final int WINDOW_WIDTH = 600,
							 WINDOW_HEIGHT = 290,
							 WINDOWS7_TITLEBAR = 30,
							 WINDOWS7_BORDER = 8;

   // Ordered as they appear on the GUI, top to bottom
   private JPanel mainPanel;
   private JPanel controlPanel;
   private JTextArea employeePrint;
   private JScrollPane scrollEmployeePrint;
   private JButton createNew;
   private JButton raise;
   private JButton printList;
   private JButton exit;

   public static void main(String[] args)
   {
      new GUIApp();
   }

   public GUIApp()
   {
      setContentPane(windowContent());

      // A window's size includes the borders of the window, too
		setSize(WINDOW_WIDTH + WINDOWS7_BORDER*2, WINDOW_HEIGHT + WINDOWS7_TITLEBAR + WINDOWS7_BORDER);
      setTitle("Employee Manager");
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(true);
   }

   private JPanel windowContent()
   {
      mainPanel = new JPanel(new BorderLayout());
      mainPanel.setLayout(null);

      /**
       * controlPanel is just going to contain fields, buttons, and labels. It
       * is ok for it to have a generated layout.
       */
      controlPanel = new JPanel();
      controlPanel.setSize(150, 290);
      controlPanel.setLocation(WINDOW_WIDTH - 150, 0);
      mainPanel.add(controlPanel);

		String example = "example 1\nexample 2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12\n13\n14\n15\n16\n17\n18";
		
		// Text box that will contain the Employee Objects when the are printed out
		employeePrint = new JTextArea(example, 18, 30);
		employeePrint.setEditable(false);
		mainPanel.add(employeePrint);

      // Allows user to scroll through employeePrint
		scrollEmployeePrint = new JScrollPane(employeePrint,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollEmployeePrint.setPreferredSize(new Dimension(WINDOW_WIDTH-150, WINDOW_HEIGHT-20));
		scrollEmployeePrint.setBounds(10, 10, (WINDOW_WIDTH-150-16), WINDOW_HEIGHT-20);	// Width has -16 so the scrollbar won't overlap the control buttons
      scrollEmployeePrint.getHorizontalScrollBar();
      scrollEmployeePrint.getVerticalScrollBar();
      mainPanel.add(scrollEmployeePrint);

      // Enters a menu for user to create new Employee Objects
      createNew = new JButton("New Employee");
      createNew.setPreferredSize(new Dimension(120, 30));
      createNew.addActionListener(this);
      controlPanel.add(createNew);

      // Enters a menu that will give all Employees a raise
      raise = new JButton("Give Raise");
      raise.setPreferredSize(new Dimension(120, 30));
      raise.addActionListener(this);
      controlPanel.add(raise);

      // Prints out the list of Employee Objects
      printList = new JButton("Print List");
      printList.setPreferredSize(new Dimension(120, 30));
      printList.addActionListener(this);
      controlPanel.add(printList);

      // Button to exit application
      exit = new JButton("Exit");
      exit.setPreferredSize(new Dimension(120, 30));
      exit.addActionListener(this);
      controlPanel.add(exit);

      return mainPanel;
   }

   public void actionPerformed(ActionEvent e)
   {
      if (TESTING_ACTION)
         System.out.println(" > Registered action performed");
   }

}
