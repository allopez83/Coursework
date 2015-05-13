package project.turnin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * GameView
 * 
 * This is the game board the contains the entire mancala game and all its
 * interface content.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class GameView extends JFrame {
	private JLabel playerA, playerB;
	private Board myBoard;
	private JPanel lowerPanel, infoPanel, buttonPanel, buttonMain;
	private JButton menuButton, styleButton, undoButton, quitButton,endTurnButton;
	private JTextArea infoArea;
	private JScrollPane scrollPane;
	private StyleMenu styleMenu;


	/**
	 * Default Game View Constructor
	 */
	public GameView() {
		// Frame

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Mancala");
		this.setLayout(new BorderLayout());
		this.setSize(800, 400);
		this.setResizable(false);

		// Board
		this.myBoard = new Board();

		// Lower

		lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(1, 2));
		lowerPanel.setSize(800, 100);

		// Info

		infoPanel = new JPanel();
		infoPanel.setSize(800, 100);

		infoArea = new JTextArea(5, 30);
		infoArea.setEditable(false);

		scrollPane = new JScrollPane(infoArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
		infoPanel.add(scrollPane);

		// Button

		buttonMain = new JPanel();
		buttonMain.setLayout(new BorderLayout());
		buttonMain.setSize(200, 100);

		endTurnButton = new JButton("End Turn");

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 2));
		buttonPanel.setSize(200, 100);

		menuButton = new JButton("Menu");
		menuButton.setSize(50, 25);
		styleButton = new JButton("Style");
		styleButton.setSize(50, 25);
		undoButton = new JButton("Undo");
		undoButton.setSize(50, 25);
		quitButton = new JButton("Quit");
		quitButton.setSize(50, 25);

		buttonPanel.add(menuButton);
		buttonPanel.add(styleButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(quitButton);

		buttonMain.add(endTurnButton, BorderLayout.PAGE_START);
		buttonMain.add(buttonPanel, BorderLayout.CENTER);

		lowerPanel.add(infoPanel);
		lowerPanel.add(buttonMain);

		this.add(this.myBoard, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);

		// Style Menu

		styleMenu = new StyleMenu();

		// Other

		playerA = new JLabel("PlayerA");
		playerB = new JLabel("PlayerB");


	}
	
	/**
	 * Returns the board that contains the Mancala pots;
	 * 
	 * @return myBoard: the game board;
	 */
	public Board getMyBoard() {
		return myBoard;
	}

	/**
	 * Sets the game board to a specific board.
	 * 
	 * @param myBoard board to set to the game board.
	 */
	public void setMyBoard(Board myBoard) {
		this.myBoard = myBoard;
	}

	// Add ActionListeners

	/**
	 * Adds a action Listener to the end turn button
	 * 
	 * @param al ActionListener
	 */
	void addEndTurnListener(ActionListener al) {
		endTurnButton.addActionListener(al);
	}

	/**
	 * Adds a action Listener to the Menu turn button
	 * 
	 * @param al ActionListener
	 */
	void addMenuListener(ActionListener al) {
		menuButton.addActionListener(al);
	}

	/**
	 * Adds a action Listener to the End Turn button
	 * 
	 * @param al ActionListener
	 */
	void addStyleListener(ActionListener al) {
		styleButton.addActionListener(al);
	}

	/**
	 * Adds a action Listener to the Undo button
	 * 
	 * @param al ActionListener
	 */
	void addUndoListener(ActionListener al) {
		undoButton.addActionListener(al);
	}

	/**
	 * Adds a action Listener to the Quit button
	 * 
	 * @param al ActionListener
	 */
	void addQuitListener(ActionListener al) {
		quitButton.addActionListener(al);
	}


	/**
	 * Sets the players names in the Game View.
	 * 
	 * @param a  userA's name.
	 * @param b  userB's name.
	 */
	public void setPlayers(String a, String b) {
		playerA.setText(a);
		playerB.setText(b);
		myBoard.setLabel(a, b);
		this.repaint();
	}

	/**
	 * Sets the userA's names in the Game View.
	 * 
	 * @param a userA's name.
	 */
	public void setPlayerA(String a) {
		playerA.setText(a);
		this.repaint();
	}

	/**
	 * Sets the userB's names in the Game View.
	 * 
	 * @param b userB's name.
	 */
	public void setPlayerB(String b) {
		playerB.setText(b);
		this.repaint();
	}

	/**
	 * This is the style pop out menu that allows the User's to change the color
	 * of the Game View.
	 */
	class StyleMenu extends JFrame {

		JPanel mPanel;

		JButton desertB, beachB, jungleB;

		/**
		 * Default Style Menu Constructor
		 */
		public StyleMenu() {
			this.setLocationRelativeTo(null);

			this.setTitle("Style");
			this.setResizable(false);

			mPanel = new JPanel();
			mPanel.setLayout(new GridLayout(1, 3));

			desertB = new JButton("Yellow");
			desertB.setBackground(Color.YELLOW);
			desertB.setContentAreaFilled(false);
			desertB.setOpaque(true);
			beachB = new JButton("Blue");
			beachB.setBackground(Color.BLUE);
			beachB.setContentAreaFilled(false);
			beachB.setOpaque(true);
			jungleB = new JButton("Green");
			jungleB.setBackground(Color.GREEN);
			jungleB.setContentAreaFilled(false);
			jungleB.setOpaque(true);

			mPanel.add(desertB);
			mPanel.add(beachB);
			mPanel.add(jungleB);
			mPanel.setSize(100, 25);
			this.add(mPanel);
			this.pack();
		}

		/**
		 * Adds a action Listener to the Yellow button
		 * 
		 * @param al ActionListener
		 */
		void addDesertListener(ActionListener al) {
			desertB.addActionListener(al);
		}

		/**
		 * Adds a action Listener to the Blue button
		 * 
		 * @param al ActionListener
		 */
		void addBeachListener(ActionListener al) {
			beachB.addActionListener(al);
		}

		/**
		 * Adds a action Listener to the Green button
		 * 
		 * @param al ActionListener
		 */
		void addJungleListener(ActionListener al) {
			jungleB.addActionListener(al);
		}

	}

	/**
	 * Displays a default error message to the user
	 * 
	 * @param user user to warn;
	 * @param message message to warn;
	 */
	public void errorMessage(String user, String message) {
		JOptionPane.showMessageDialog(new JFrame(), user + ", " + message);
	}

	/**
	 * Getter for InfoArea
	 * @return this infoArea
	 */
	public JTextArea getInfoArea() {
		return infoArea;
	}

	/**
	 * Getter for infoPanel
	 * @return this infoPanel
	 */
	public JPanel getInfoPanel() {
		return infoPanel;
	}

	/**
	 * Getter for buttonPanel
	 * @return this buttonPanel
	 */
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	/**
	 * Getter for styleMenu
	 * @return this styleMenu
	 */
	public StyleMenu getStyleMenu() {
		return styleMenu;
	}


}
