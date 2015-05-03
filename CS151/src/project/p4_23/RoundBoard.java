package project.p4_23;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoundBoard extends AbstractBoard
{

    RoundBoard(Model myModel)
    {
        super(myModel);
        // TODO Auto-generated constructor stub
    }

    public void drawParts()
    {

        System.out.println("drawparts");
        // We will use data from our Model to draw the pits here

        // This is just a test, we will repeatedly use shapes array for all the
        // pit
        // Each shapes array will contain the ball (solid square or circle) for
        // that pit
        DrawAbleShape[] shapes = new RoundSolidSquare[3];
        shapes[0] = new RoundSolidSquare(0, 0);
        shapes[1] = new RoundSolidSquare(43, 10);
        shapes[2] = new RoundSolidSquare(50, 20);

        DrawAbleShape[] shapes2 = new RoundSolidSquare[2];
        shapes2[0] = new RoundSolidSquare(30, 0);
        shapes2[1] = new RoundSolidSquare(43, 10);

        pitBoard = new JPanel(new GridLayout(2, 6));

        for (int i = 0; i < 12; i++)
        {
            RoundPit p;
            if (i < 6)
            {
                p = new RoundPit(shapes);// Adding 3 solid squares to the pit
            }
            else
            {
                p = new RoundPit(shapes2);// //Adding 2 solid squares to the pit
            }

            this.pits.add(p); // store all the pits to an array so we can use it
                              // later
            pitBoard.add(p);
        }

        this.leftPit = new RoundMancalaPit(shapes);
        leftPit.setPreferredSize(new Dimension(100, 300));
        this.rightPit = new RoundMancalaPit(shapes);
        rightPit.setPreferredSize(new Dimension(100, 300));

        mancalaStoreBoardLeft.add(this.leftPit, BorderLayout.CENTER);
        mancalaStoreBoardRight.add(this.rightPit, BorderLayout.CENTER);

    }

}
