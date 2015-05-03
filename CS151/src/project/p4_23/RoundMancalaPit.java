package project.p4_23;

import java.awt.geom.RoundRectangle2D;

/**
 * Creates a round shaped BigPit
 * @author HWU
 *
 */
public class RoundMancalaPit extends BigPit
{
    public RoundMancalaPit(DrawAbleShape[] shapes)
    {
        this.shapes = shapes;
    }

    protected void setPitType()
    {
        pit = new RoundRectangle2D.Double(0, 0, WIDTH, HEIGHT, ARC, ARC);
    }
}