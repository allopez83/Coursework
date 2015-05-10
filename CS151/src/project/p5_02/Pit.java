package project.p5_02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Pit extends JPanel {
	private DrawAbleShape[] stones;
	public DrawAbleShape[] getStones() {
		return stones;
	}

	public void setStones(DrawAbleShape[] stones) {
		this.stones = stones;
	}

	private int x;
	private int y;
	private int width;
	private int height;
	private String shapeType;//shape of the pit
	
	
	
	public String getShapeType() {
		return shapeType;
	}

	public void setShapeType(String shapeType) {
		this.shapeType = shapeType;
	}

	public Pit(DrawAbleShape[] stones, int x, int y , int width, int height, String shapeType) {
		//super()
		this.stones = stones;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.shapeType= shapeType;
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (shapeType.equalsIgnoreCase("square"))
		{
			g2.setColor(Color.WHITE);
			g2.fillRoundRect(x, y, width, height, 25, 25);
			g2.setColor(Color.BLACK);
			g2.drawRoundRect(x, y, width, height, 25, 25);
			g2.setColor(Color.GRAY);
		}
		else if (shapeType.equalsIgnoreCase("circle"))
		{
			g2.setColor(Color.WHITE);
			g2.fillOval(x, y, width, height);
			g2.setColor(Color.BLACK);
			g2.drawOval(x, y, width, height);
			g2.setColor(Color.GRAY);
		}
			
		for (DrawAbleShape s : stones) {
			s.draw((Graphics2D) g);
		}

	}
}
