package project.turnin;

import java.awt.Color;

/**
 * Concrete strategy. Strategy implementation for initial view - changes color
 * of the board to green.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class GreenStyle extends BoardStyle
{
    public GreenStyle()
    {
        super.setColor(Color.GREEN);
    }
}
