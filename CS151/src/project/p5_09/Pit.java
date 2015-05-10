package project.p5_09;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This is the Pit of the Mancala Game.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */

public class Pit extends JPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	private int pitNum;
	private Color color;

	private DrawAbleShape[] stones;

	/**
	 * Constructor that initialize a pit
	 * @param stones an array of stone that this pit will has
	 * @param x x-coordinate of this pit
	 * @param y y-coordinate of this pit
	 * @param width the width of this pit
	 * @param height the height of this pit
	 * @param pitNum the pit number to distinguish one pit from another
	 */

	public Pit(DrawAbleShape[] stones, int x, int y, int width, int height,	int pitNum) {

		this.stones = stones;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.pitNum = pitNum;
		this.color = Color.WHITE;
	}
	/**
	 * Overriding the paintComponent method to provide more specific painting for the pit
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 25, 25);
		g2.setColor(Color.BLACK);
		g2.drawRoundRect(x, y, width, height, 25, 25);
		g2.setColor(Color.GRAY);

		for (DrawAbleShape s : stones) {
			s.draw((Graphics2D) g);
		}

	}
	/**
	 * Getter for color
	 * @return this color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Setter for color
	 * @param color the new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Getter for the stones on the pit
	 * @return the array of stones on the pit
	 */
	public DrawAbleShape[] getStones() {
		return stones;
	}

	/**
	 * Getter for pitNum
	 * @return this pitNum
	 */
	public int getPitNum() {
		return this.pitNum;
	}

	/**
	 * Setter for stones
	 * @param stones the new array of stones
	 */
	public void setStones(DrawAbleShape[] stones) {
		this.stones = stones;
	}
}
