package project.turnin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This is the Initial View of the game. It lets users enter name, choose number of stones,
 * ,choose a style, and start the game.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class InitialView extends JFrame {
	private JLabel labelA, labelB, colorLabel;
	private JTextField userA, userB;
	private JButton start, quit, c1;
	private JRadioButton c2, c3;
	private JPanel mPanel, textPanel, customizePanel, buttonPanel;

	public InitialView() {
		// Frame
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("New Game");
		this.setLayout(new GridLayout(2, 1));

		// Main Panel
		mPanel = new JPanel();
		mPanel.setLayout(new GridLayout(1, 2));

		// Text Panel
		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2, 2));

		labelA = new JLabel("Player 1");
		labelA.setHorizontalAlignment(SwingConstants.CENTER);
		labelB = new JLabel("Player 2");
		labelB.setHorizontalAlignment(SwingConstants.CENTER);

		userA = new JTextField();
		userB = new JTextField();

		textPanel.add(labelA);
		textPanel.add(userA);
		textPanel.add(labelB);
		textPanel.add(userB);

		// Buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));

		start = new JButton("Start");
		quit = new JButton("Quit");
		buttonPanel.add(start);
		buttonPanel.add(quit);

		// Color
		customizePanel = new JPanel();
		customizePanel.setLayout(new GridLayout(1, 3));

		c1 = new JButton("Style");
		c2 = new JRadioButton("4 beans");
		c2.setSelected(true);
		c3 = new JRadioButton("3 beans");

		customizePanel.add(c1);
		customizePanel.add(c2);
		customizePanel.add(c3);

		// Add to Frame
		mPanel.add(textPanel);
		mPanel.add(customizePanel);
		this.add(mPanel);
		this.add(buttonPanel);

		this.setVisible(true);
		this.setSize(500, 150);
		this.pack();

	}

	// Start and Quit Listeners
	/**
	 * Method to add Start Listener
	 * @param al the ActionListener
	 */
	void addStartListener(ActionListener al) {
		start.addActionListener(al);
	}

	/**
	 * Method to add Quit Listener
	 * @param al the ActionListener
	 */
	void addQuitListener(ActionListener al) {
		quit.addActionListener(al);
	}

	// Color Listeners

	/**
	 * Method to add Color Listener
	 * @param al the ActionListener
	 */
	void addc1Listener(ActionListener al) {
		c1.addActionListener(al);
	}

	/**
	 * Method to add Color Listener
	 * @param al the ActionListener
	 */
	void addc2Listener(ActionListener al) {
		c2.addActionListener(al);
	}

	/**
	 * Method to add Color Listener
	 * @param al the ActionListener
	 */
	void addc3Listener(ActionListener al) {
		c3.addActionListener(al);
	}

	/**
	 * Getter for JButton c2
	 * @return this JButton c2
	 */
	public JRadioButton getC2() {
		return c2;
	}

	/**
	 * Getter for JButton c3
	 * @return this JButton c3
	 */
	public JRadioButton getC3() {
		return c3;
	}

	/**
	 * Getter for userA
	 * @return this userA
	 */
	public JTextField getUserA() {
		return userA;
	}

	/**
	 * Getter for userB
	 * @return this userB
	 */
	public JTextField getUserB() {
		return userB;
	}
	
	/**
	 * Getter for textPanel
	 * @return this textPanel
	 */
	public JPanel getTextPanel() {
		return textPanel;
	}
	
	/**
	 * Getter for buttonPanel
	 * @return this buttonPanel
	 */
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	
	public JPanel getCustomizePanel ()
	{
	    return customizePanel;
	}

}
