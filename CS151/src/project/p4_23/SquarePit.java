package project.p4_23;

import java.awt.geom.Rectangle2D;

/**
 * Creates a square shaped SmallPit
 * @author HWU
 *
 */
public class SquarePit extends SmallPit
{
    public SquarePit(DrawAbleShape[] shapes)
    {
        this.shapes = shapes;
    }
    
    protected void setPitType()
    {
        pit = new Rectangle2D.Double(0, 0, 60, 60);
    }
}
