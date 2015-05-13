package project.turnin;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board to blue.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class BlueStyle extends BoardStyle
{
    public BlueStyle()
    {
        super.setColor(Color.BLUE);
    }
}
