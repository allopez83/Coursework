package project.p5_02;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Superclass holding common code that different styles will use. Subclasses
 * will specify a certain color that will be applied to the board.
 * @author HWU
 *
 */
abstract public class BoardStyle
{
    private Color styleColor;

    public BoardStyle()
    {
    }

    /**
     * Change color of a JComponent according to that of a specified subclass
     * @param jc component to color
     */
    public void colorComponent(JComponent jc)
    {
        jc.setBackground(styleColor);
        jc.setOpaque(true);
        jc.repaint();
    }

    /**
     * Used by subclasses to specify what color the style is
     * @param c Color the style uses 
     */
    protected void setColor(Color c)
    {
        styleColor = c;
    }

}
