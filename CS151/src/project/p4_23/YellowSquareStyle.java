package project.p4_23;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board. In this case, it applies a yellow color scheme, and square
 * pits.
 * @author HWU
 *
 */
public class YellowSquareStyle extends BoardStyle
{
    public YellowSquareStyle()
    {
        super.setColor(Color.YELLOW);
        // super.setPit();
    }
}
