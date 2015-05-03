package project.p4_23;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Concrete implementation of StyleInterface. Has implementation of
 * StyleInterface methods that are universal to styles, but style-specific
 * aspects are left out, and requires non-abstract class to specify
 * @author HWU
 *
 */
abstract public class BoardStyle implements StyleInterface
{
    private Color styleColor;

    // Set pit type

    public BoardStyle()
    {
        // TODO Auto-generated constructor stub
    }

    public void colorComponent(JComponent jc)
    {
        jc.setBackground(styleColor);
        jc.setOpaque(true);
        jc.repaint();
    }

    public void boardStyle(AbstractBoard board, Model m)
    {
        System.out.println("new board style");
        board.removeAll();
        board = new RoundBoard(m);
        board.repaint();
    }

    public void setColor(Color c)
    {
        styleColor = c;
    }

    public void setPit()
    {
        // TODO
    }

}
