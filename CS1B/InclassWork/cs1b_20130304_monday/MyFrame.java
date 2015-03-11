package cs1b_20130304_monday;

import javax.swing.*;
import java.awt.FlowLayout;

/**
 * Hello world JFrame class
 * @author HW
 */
public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel prompt;
	private JButton okay;

	private void initialize() {
		this.setLayout(new FlowLayout());	// Lines up everything automatically
		
		prompt = new JLabel("Hello World!");
		this.add(prompt);	// This puts the prompt on Frame
		
		okay = new JButton("Understood");
		add(okay);
		
		this.setSize(100, 50);
		this.setVisible(true);
		this.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);

	}

	public MyFrame() {
		this.initialize();
	}

}









// Extra scrolling space