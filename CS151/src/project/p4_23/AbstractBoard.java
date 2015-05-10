package project.p4_23;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class AbstractBoard extends JPanel implements Board
{
    protected Model myModel;
    protected JPanel pitBoard; // The board that contains all the pits in the
                               // middle
    protected JPanel mancalaStoreBoardLeft; // The large pit on the left
    protected JPanel mancalaStoreBoardRight; // The large pit on the right

    protected ArrayList<SmallPit> pits;
    protected BigPit leftPit;
    protected BigPit rightPit;

    AbstractBoard(Model myModel)
    {
        super(new BorderLayout());

        pits = new ArrayList<SmallPit>();
        this.myModel = myModel;
        mancalaStoreBoardLeft = new JPanel(new FlowLayout());
        mancalaStoreBoardLeft.setPreferredSize(new Dimension(150, 400));// hardCoded
                                                                        // sizing
        mancalaStoreBoardRight = new JPanel(new FlowLayout());
        mancalaStoreBoardRight.setPreferredSize(new Dimension(150, 400));// hardCoded
                                                                         // sizing
        drawParts();
        this.add(mancalaStoreBoardLeft, BorderLayout.EAST);
        this.add(mancalaStoreBoardRight, BorderLayout.WEST);
        this.add(pitBoard, BorderLayout.CENTER);
    }

    public void addPitListener(MouseListener l)
    {
        for (Pit p : pits)
        {
            p.addMouseListener(l);
        }
    }

    public void repaint(Graphics g)
    {
        System.out.println("boardrepaint");
        Graphics2D g2 = (Graphics2D) g;

        this.paintComponent(g2);
        super.repaint();

        for (Pit p : pits)
        {
            p.paintComponent(g2);
        }
        leftPit.paintComponent(g2);
        rightPit.paintComponent(g2);
    }

}
