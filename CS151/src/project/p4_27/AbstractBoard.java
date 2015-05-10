package project.p4_27;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;



	 abstract class AbstractBoard extends JPanel implements Board{
			protected Model myModel;
			protected JPanel pitBoard; //The board that contains all the pits in the middle
			protected JPanel mancalaStoreBoardLeft; //The large pit on the left
			protected JPanel mancalaStoreBoardRight; //The large pit on the right
			protected Pit[] pits	;//need to be generalize for different type of Pit
			public Pit[] getPits() {
				return pits;
			}


			//private DrawAbleShape stoneType;
			protected String shapeType;
			AbstractBoard(Model myModel)
			{
				super( new BorderLayout());
				this.setPreferredSize(new Dimension(600, 200));
				this.myModel = myModel;
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
				drawParts();
				
			}


			public void addPitListener(MouseListener l) {
				for (Pit p : pits) {
					p.addMouseListener(l);
				}
			
			}

		}


