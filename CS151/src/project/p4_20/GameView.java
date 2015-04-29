package project.p4_20;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameView extends JFrame {

	JPanel boardPanel, lowerPanel, infoPanel, buttonPanel;

	JTextArea infoArea;
	JScrollPane scrollPane;

	JButton menuButton, styleButton, undoButton, quitButton;

	JLabel playerA, playerB;

	// MancalaBoard mB;
	GameBoard myBoard;

	StyleMenu styleMenu;

	public GameView(GameBoard myBoard) {
		// Frame

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Mancala");
		this.setLayout(new BorderLayout());
		this.setSize(800, 400);
		this.setResizable(false);

		// Board
		this.myBoard = myBoard;


		// Lower

		lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(1, 2));
		lowerPanel.setSize(800,100);

		// Info

		infoPanel = new JPanel();
		infoPanel.setSize(800, 100);

		infoArea = new JTextArea(5, 30);
		infoArea.setEditable(false);
		// infoArea.setSize(800,100);

		// ****WORD WRAP****

		scrollPane = new JScrollPane(infoArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setSize(800,100);
		infoPanel.add(scrollPane);

		// Button

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

		lowerPanel.add(infoPanel);
		lowerPanel.add(buttonPanel);
	
		this.add(this.myBoard, BorderLayout.CENTER);
		// this.add(boardPanel, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);

		// Style Menu

		styleMenu = new StyleMenu();

		// Other

		playerA = new JLabel("PlayerA");
		playerB = new JLabel("PlayerB");

		// this.pack();

	}

	// Add ActionListeners

	void addMenuListener(ActionListener al) {
		menuButton.addActionListener(al);
	}

	void addStyleListener(ActionListener al) {
		styleButton.addActionListener(al);
	}

	void addUndoListener(ActionListener al) {
		undoButton.addActionListener(al);
	}

	void addQuitListener(ActionListener al) {
		quitButton.addActionListener(al);
	}

	/*
	 * void addPotListener(MouseListener l) { myPot.addMouseListener(l);
	 * myPot2.addMouseListener(l); }
	 */
	// Methods

	public void setPlayers(String a, String b) {
		playerA.setText(a);
		playerB.setText(b);
		this.repaint();
	}

	public void setPlayerA(String a) {
		playerA.setText(a);
		this.repaint();
	}

	public void setPlayerB(String b) {
		playerB.setText(b);
		this.repaint();
	}

	class StyleMenu extends JFrame {

		JPanel mPanel;

		JButton desertB, beachB, jungleB;

		public StyleMenu() {
			this.setLocationRelativeTo(null);

			this.setTitle("Style");
			this.setResizable(false);

			mPanel = new JPanel();
			mPanel.setLayout(new GridLayout(1, 3));

			desertB = new JButton("Desert");
			desertB.setBackground(Color.YELLOW);
			desertB.setContentAreaFilled(false);
			desertB.setOpaque(true);
			beachB = new JButton("Beach");
			beachB.setBackground(Color.BLUE);
			beachB.setContentAreaFilled(false);
			beachB.setOpaque(true);
			jungleB = new JButton("Jungle");
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

		void addDesertListener(ActionListener al) {
			desertB.addActionListener(al);
		}

		void addBeachListener(ActionListener al) {
			beachB.addActionListener(al);
		}

		void addJungleListener(ActionListener al) {
			jungleB.addActionListener(al);
		}

	}

}
