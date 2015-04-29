package project.p4_23;

import java.awt.geom.Rectangle2D;

/**
 * Creates a square shaped BigPit
 * @author HWU
 *
 */
public class SquareMancalaPit extends BigPit
{
    public SquareMancalaPit(DrawAbleShape[] shapes)
    {
        this.shapes = shapes;
    }

    protected void setPitType()
    {
        pit = new Rectangle2D.Double(0, 0, WIDTH, HEIGHT);
    }
}