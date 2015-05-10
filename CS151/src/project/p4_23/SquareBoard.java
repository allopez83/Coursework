package project.p4_23;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class SquareBoard extends AbstractBoard {

	SquareBoard(Model myModel) {
		super(myModel);
		// TODO Auto-generated constructor stub
	}

	public void drawParts() {
		// We will use data from our Model to draw the pits here

		// This is just a test, we will repeatedly use shapes array for all the pit
		// Each shapes array will contain the ball (solid square or circle) for that pit
		DrawAbleShape[] shapes = new SolidSquare[3];
		shapes[0] = new SolidSquare(0, 0);
		shapes[1] = new SolidSquare(43, 10);
		shapes[2] = new SolidSquare(50, 20);

		DrawAbleShape[] shapes2 = new SolidSquare[2];
		shapes2[0] = new SolidSquare(30, 0);
		shapes2[1] = new SolidSquare(43, 10);

		pitBoard = new JPanel(new GridLayout(2, 6));
		

		for (int i = 0; i < 12; i++) {
			SquarePit p;
			if (i < 6) {
				p = new SquarePit(shapes);// Adding 3 solid squares to the pit
			} else {
				p = new SquarePit(shapes2);// Adding 2 solid squares to the pit
			}
			
			this.pits.add(p); // store all the pits to an array so we can use it later
			pitBoard.add(p);

		}
		
		this.leftPit = new SquareMancalaPit(shapes);
		leftPit.setPreferredSize(new Dimension(100, 300));
		this.rightPit = new SquareMancalaPit(shapes);
		rightPit.setPreferredSize(new Dimension(100, 300));
		
		mancalaStoreBoardLeft.add(this.leftPit, BorderLayout.CENTER);
		mancalaStoreBoardRight.add(this.rightPit, BorderLayout.CENTER);
		
	}

}
