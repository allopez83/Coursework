package project.p5_09;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board to yellow.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class YellowStyle extends BoardStyle
{
    public YellowStyle()
    {
        super.setColor(Color.YELLOW);
    }
}
