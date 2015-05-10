package project.p5_09;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This is the Manacal game board, it contains the actual playable surface for
 * the game. It contains 14 pits: two of them are Mancala storage, and the rest
 * are playable pits. Also contains labels for the user.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class Board extends JPanel {

	private JPanel pitBoard; // The board that contains all the pits in the
								// middle
	private JPanel mancalaStoreBoardLeft; // The large pit on the left
	private JPanel mancalaStoreBoardRight; // The large pit on the right
	private Pit[] pits;// array of 14 pits
	private int width;
	private int height;

	private JLabel userA, userB;

	private JPanel userAP, userBP;

	/**
	 * Returns the pits of the board
	 * 
	 * @return myPits: game pits
	 */
	public Pit[] getPits() {
		return pits;
	}
	
	/**
	 * Default Board Constructor.
	 */
	Board() {
		super(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 200));

		pits = new Pit[14];

		pitBoard = new JPanel(new GridLayout(2, 6));

		userA = new JLabel("");
		userA.setHorizontalAlignment(SwingConstants.CENTER);
		userB = new JLabel("");
		userB.setHorizontalAlignment(SwingConstants.CENTER);
	
		userAP = new JPanel(new BorderLayout());
		userBP = new JPanel(new BorderLayout());

		mancalaStoreBoardLeft = new JPanel(new FlowLayout());
		mancalaStoreBoardLeft.setPreferredSize(new Dimension(150, 200));

		mancalaStoreBoardRight = new JPanel(new FlowLayout());
		mancalaStoreBoardRight.setPreferredSize(new Dimension(150, 200));
		
		userAP.add(mancalaStoreBoardLeft, BorderLayout.CENTER);
		userAP.add(userA, BorderLayout.PAGE_END);
		userBP.add(mancalaStoreBoardRight, BorderLayout.CENTER);
		userBP.add(userB, BorderLayout.PAGE_END);

		this.add(userAP, BorderLayout.WEST);
		this.add(userBP, BorderLayout.EAST);
		this.add(pitBoard, BorderLayout.CENTER);
		
		createPits();

		int[] starter = new int[14];
		for (int k = 0; k < 14; k++) {
			starter[k] = 0;
		}

		drawParts(starter);
	}

	/**
	 * Left side of Mancala board
     * @return the mancalaStoreBoardLeft
     */
    public JPanel getMancalaStoreBoardLeft()
    {
        return mancalaStoreBoardLeft;
    }

    /**
     * Right side of Mancala board
     * @return the mancalaStoreBoardRight
     */
    public JPanel getMancalaStoreBoardRight()
    {
        return mancalaStoreBoardRight;
    }

    /**
     * Panel that user A's pits are onon
     * @return the userAP
     */
    public JPanel getUserAP()
    {
        return userAP;
    }

    /**
     * Panel that user B's pits are on
     * @return the userBP
     */
    public JPanel getUserBP()
    {
        return userBP;
    }

    /**
	 * Sets the tables for display on the board
	 * 
	 * @param userA userA's name.
	 * @param userB userB's name.
	 */
	public void setLabel(String userA, String userB) {
		this.userA.setText(userA);
		this.userB.setText(userB);
	}

	/**
	 * Adds MousListeners to the individual pits
	 * 
	 * @param l MouseListener.
	 */
	public void addPitListener(MouseListener l) {
		for (Pit p : pits) {
			p.addMouseListener(l);
		}

	}

	/**
	 * Draws the shapes of the pots with the correct amount of stones
	 * 
	 * @param data integer array of amount of stones in the pots
	 */
	public void drawParts(int[] data) {

		for (int i = 0; i < pits.length; i++) {

			DrawAbleShape[] stones = new SolidStone[data[i]];
			int x = 5;
			int y = 0;
			for (int j = 0; j < stones.length; j++) {
				if (j % 5 == 0) {
					x = 5;
					y = y + 10;
				}
				stones[j] = new SolidStone(x, y, 10, 10);
				x = x + 15;
			}

			pits[i].setStones(stones);
			repaint();

		}

	}

	/**
	 * Creates and Draws Pits for the board.
	 */
	public void createPits() {
		for (int i = 0; i < pits.length; i++) {
			if (i == 0 || i == 13) {
				width = 80;
				height = 200;
			} else {
				width = 80;
				height = 80;
			}
			DrawAbleShape[] stones = new SolidStone[0];
			pits[i] = new Pit(stones, 0, 0, width, height, i);
			if (i == 0) {
				pits[i].setPreferredSize(new Dimension(100, 300));
				mancalaStoreBoardLeft.add(pits[i]);
			}

			else if (i == 13) {
				pits[i].setPreferredSize(new Dimension(100, 300));
				mancalaStoreBoardRight.add(pits[i]);
			}

			else {
				pitBoard.add(pits[i]);
			}
		}

	}

}
