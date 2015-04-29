package project.p4_20;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class SquarePit extends JPanel {
	DrawAbleShape[] shapes;

	public SquarePit(DrawAbleShape[] shapes) {
		this.shapes = shapes;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double square = new Rectangle2D.Double(0, 0, 60, 60);
		g2.draw(square);
		for (DrawAbleShape s : shapes) {
			s.draw((Graphics2D) g);
		}

	}
}
