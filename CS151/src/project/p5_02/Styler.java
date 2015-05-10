package project.p5_02;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Interfaces with, and applies the different board styles.
 * @author HWU
 *
 */
public class Styler
{
    JComponent[] components;

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
     * Change the style of the board according to the BoardStyle passed in
     * @param style the style the board is going to be changed to
     */
    public void changeStyle(BoardStyle style)
    {
        // Apply new color scheme
        for (JComponent jc : components)
            style.colorComponent(jc);
    }

}
