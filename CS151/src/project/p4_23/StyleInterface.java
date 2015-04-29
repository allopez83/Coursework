package project.p4_23;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Depending on strategy used, will draw different color and shaped pit.
 * @author HWU
 *
 */
public interface StyleInterface
{
    /**
     * Colors a JComponent with a strategy-specific color
     * @param jc component color
     * @return
     */
    void colorComponent(JComponent jc);

    /**
     * Draws the game board with either round or square pits
     * @param panel
     */
    void boardStyle(AbstractBoard board, Model m);
}
