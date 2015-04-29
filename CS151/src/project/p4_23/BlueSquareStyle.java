package project.p4_23;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board. In this case, it applies a blue color scheme, and square pits.
 * @author HWU
 *
 */
public class BlueSquareStyle extends BoardStyle
{
    public BlueSquareStyle()
    {
        setColor(Color.BLUE);
    }
}
