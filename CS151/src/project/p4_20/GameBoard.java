package project.p4_20;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public abstract class GameBoard extends JPanel implements Board{
	protected Model myModel;
	protected JPanel pitBoard; //The board that contains all the pits in the middle
	protected JPanel mancalaStoreBoardLeft; //The large pit on the left
	protected JPanel mancalaStoreBoardRight; //The large pit on the right
	protected ArrayList<SquarePit> pits	;//need to be generalize for different type of Pit
	protected SquareMancalaPit leftPit;//need to be generalize for different type of Pit
	protected SquareMancalaPit rightPit;//need to be generalize for different type of Pit
	GameBoard(Model myModel)
	{
		super( new BorderLayout());
		
		pits = new ArrayList<SquarePit>();
		this.myModel = myModel;
		mancalaStoreBoardLeft = new JPanel(new FlowLayout());
		mancalaStoreBoardLeft.setPreferredSize(new Dimension(150, 400));// hardCoded sizing
		mancalaStoreBoardRight = new JPanel(new FlowLayout());
		mancalaStoreBoardRight.setPreferredSize(new Dimension(150, 400));// hardCoded sizing
		
		drawParts();
		this.add(mancalaStoreBoardLeft, BorderLayout.EAST);
		this.add(mancalaStoreBoardRight, BorderLayout.WEST);
		this.add(pitBoard, BorderLayout.CENTER);

		
	}
	public void addPitListener ( MouseListener l) {
		for (SquarePit p : pits)
		{
			p.addMouseListener(l);
		}
	}
}
