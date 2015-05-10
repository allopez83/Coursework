package project.p4_27;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;


public class SquareBoard extends AbstractBoard {

	
SquareBoard(Model myModel) {
		super(myModel);
		// TODO Auto-generated constructor stub
	}

public void drawParts() {
		int width; 
		int height;
		for (int i = 0; i < pits.length ; i++)
		{
			//Draw base on the data of the model
			DrawAbleShape[] stones = new SolidSquare[myModel.getPits()[i]];
			for (int j = 0; j < stones.length ; j ++)
			{
				if (i % 2 ==0)
					stones[j] = new SolidSquare(20+j*5, 0+j*10, 10, 10);
				else
					stones[j] = new SolidSquare(10+j*5, 0+j*10, 10, 10);
			}
			
			//Rectangle2D.Double square;
			if (i ==0 || i == 13)
			{
				//square = new Rectangle2D.Double(0, 0, 50, 150);
				width= 50 ;
				height= 150;
			}
			else
			{
				 //square = new Rectangle2D.Double(0, 0, 60, 60);
				 width= 80 ;
				height= 80;
			}
			
			pits[i] = new Pit (stones, 0, 0, width, height,"square");
			if (i == 0)
			{
				pits[i].setPreferredSize(new Dimension(100, 300));
				mancalaStoreBoardLeft.add(pits[i]);

			}
			
			else if (i == 13)
			{
				pits[i].setPreferredSize(new Dimension(100, 300));
				mancalaStoreBoardRight.add(pits[i]);
			}
			
			else
			{
				pitBoard.add(pits[i]);
			}
				
		}
		
		// We will use data from our Model to draw the pits here

				// This is just a test, we will repeatedly use shapes array for all the
				// pit
				// Each shapes array will contain the ball (solid square or circle) for
				// that pit
				/*
				DrawAbleShape[] shapes = new SolidSquare[3];
				shapes[0] = new SolidSquare(30, 0, 10, 10);
				shapes[1] = new SolidSquare(43, 10, 10, 10);
				shapes[2] = new SolidSquare(50, 20, 10, 10);

				DrawAbleShape[] shapes2 = new SolidSquare[2];
				shapes2[0] = new SolidSquare(30, 0, 10, 10);
				shapes2[1] = new SolidSquare(43, 10, 10, 10);
				
				for (int i = 0; i < 12; i++) {
					SquarePit p;
					if (i < 6) {
						p = new SquarePit(shapes);// Adding 3 solid squares to the pit
					} else {
						p = new SquarePit(shapes2);// //Adding 2 solid squares to the
													// pit
					}
				
					this.pits.add(p); // store all the pits to an array so we can use it later
					pitBoard.add(p);

				//}
				
				this.leftPit = new SquareMancalaPit(shapes);
				leftPit.setPreferredSize(new Dimension(100, 300));
				this.rightPit = new SquareMancalaPit(shapes);
				rightPit.setPreferredSize(new Dimension(100, 300));
				
				mancalaStoreBoardLeft.add(this.leftPit, BorderLayout.CENTER);
				mancalaStoreBoardRight.add(this.rightPit, BorderLayout.CENTER);
				*/
		
		
	}

}
