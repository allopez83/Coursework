package project.p4_20;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class SquareMancalaPit extends JPanel {
	DrawAbleShape[] shapes;

	public SquareMancalaPit(DrawAbleShape[] shapes) {
		this.shapes = shapes;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double square = new Rectangle2D.Double(0, 0, 80, 150);
		g2.draw(square);
		//this.add(new Button());
		for (DrawAbleShape s : shapes) {
			s.draw((Graphics2D) g);
		}

	}
}