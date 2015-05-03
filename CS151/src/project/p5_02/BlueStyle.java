package project.p5_02;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board to blue.
 * @author Hansen Wu
 *
 */
public class BlueStyle extends BoardStyle
{
    public BlueStyle()
    {
        super.setColor(Color.BLUE);
    }
}
