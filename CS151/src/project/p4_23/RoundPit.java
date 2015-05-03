package project.p4_23;

import java.awt.geom.RoundRectangle2D;

/**
 * Creates a round shaped SmallPit
 * @author HWU
 *
 */
public class RoundPit extends SmallPit
{
    public RoundPit(DrawAbleShape[] shapes)
    {
        this.shapes = shapes;
    }

    protected void setPitType()
    {
        pit = new RoundRectangle2D.Double(0, 0, SIZE, SIZE, ARC, ARC);
    }
}
