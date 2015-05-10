package project.p5_02;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board to green.
 * @author Hansen Wu
 *
 */
public class GreenStyle extends BoardStyle
{
    public GreenStyle()
    {
        super.setColor(Color.GREEN);
    }
}
