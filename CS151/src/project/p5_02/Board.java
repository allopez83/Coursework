package project.p5_02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;



	 public class Board extends JPanel{
			
			protected JPanel pitBoard; //The board that contains all the pits in the middle
			protected JPanel mancalaStoreBoardLeft; //The large pit on the left
			protected JPanel mancalaStoreBoardRight; //The large pit on the right
			protected Pit[] pits	;//need to be generalize for different type of Pit
                        private int width; 
                        private int height;
			public Pit[] getPits() {
				return pits;
			}

        //        ***************

			//private DrawAbleShape stoneType;
			protected String shapeType;
			Board()
			{
				super( new BorderLayout());
				this.setPreferredSize(new Dimension(600, 200));
				
				//this.stoneType = stoneType;
				//this.pitShape = pitShape;
				
				pits = new Pit[14] ; 
				
				pitBoard = new JPanel(new GridLayout(2, 6));
				
				mancalaStoreBoardLeft = new JPanel(new FlowLayout());
				mancalaStoreBoardLeft.setPreferredSize(new Dimension(150, 200));// hardCoded sizing
				
				mancalaStoreBoardRight = new JPanel(new FlowLayout());
				mancalaStoreBoardRight.setPreferredSize(new Dimension(150, 200));// hardCoded sizing
				
				this.add(mancalaStoreBoardLeft, BorderLayout.WEST);
				this.add(mancalaStoreBoardRight, BorderLayout.EAST);
				this.add(pitBoard, BorderLayout.CENTER);
                                
                                createPits();
                                
                                int[] tester = new int[14];
                                for(int k = 0; k < 14; k ++)
                                {
                                    tester[k] = 0;
                                }
                                
				drawParts(tester);
				
			}
                        
			public void addPitListener(MouseListener l) {
				for (Pit p : pits) {
					p.addMouseListener(l);
				}
			
			}
                        
                        
            public void drawParts(int[] data ) {
                
//                this.removeAll();
		
		for (int i = 0; i < pits.length ; i++)
		{
			//Draw base on the data of the model
			//DrawAbleShape[] stones = new SolidSquare[myModel.getPits()[i]];
                        DrawAbleShape[] stones = new SolidSquare[data[i]];
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
			
			//pits[i] = new Pit (stones, 0, 0, width, height,"square");
                        pits[i].setStones(stones);
                        repaint();
                        /*
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
                                */
				
		}
		
         
		
		
	}
                        
         public void  createPits ()
             {
                 for (int i = 0; i < pits.length; i++) {
                     if (i == 0 || i == 13) {
                         //square = new Rectangle2D.Double(0, 0, 50, 150);
                         width = 50;
                         height = 150;
                     } else {
                         //square = new Rectangle2D.Double(0, 0, 60, 60);
                         width = 80;
                         height = 80;
                     }
                     DrawAbleShape[] stones = new SolidSquare[0];
                     pits[i] = new Pit(stones, 0, 0, width, height, "square");
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

             }
	

}

		


