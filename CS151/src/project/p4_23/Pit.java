package project.p4_23;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JPanel;

/**
 * High level, containing the universal aspects of pits.
 * @author HWU
 *
 */
public abstract class Pit extends JPanel
{
    protected final int ARC = 20; // For round pits
    DrawAbleShape[] shapes; // Not sure what this does for MancalaPit, but I left it here
    Shape pit; // Determines if pit is rounded or not

    abstract protected void setPitType();

    public void paintComponent(Graphics g)
    {
        System.out.println("pitpaint");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setPitType();
        g2.draw(pit);
        // Found following line in BigPit, not sure if needed
        // this.add(new Button());
        for (DrawAbleShape s : shapes)
        {
            s.draw((Graphics2D) g);
        }
    }

}
