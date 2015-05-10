package project.p4_23;

import javax.swing.JComponent;

/**
 * Context in course notes. Used to interface with, and apply the different
 * strategies.
 * @author HWU
 *
 */
public class Styler
{
    JComponent[] components;
    AbstractBoard board;
    Model model;

    public Styler()
    {
    }

    /**
     * Tracks components to be colored
     */
    public void setComponents(JComponent[] colorThese)
    {
        components = colorThese;
    }

    /**
     * Tracks game board to modify
     * @param b
     * @param gV 
     */
    public void setGameBoard(AbstractBoard b, Model m)
    {
        board = b;
        model = m;
    }

    public void changeStyle(StyleInterface si, GameView gV)
    {
        // Apply new color scheme
        for (JComponent jc : components)
            si.colorComponent(jc);

        // Change pit style
        si.boardStyle(board, model);
        gV.repaint();
    }

}
