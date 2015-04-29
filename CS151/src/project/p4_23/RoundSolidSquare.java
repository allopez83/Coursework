package project.p4_23;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundSolidSquare implements DrawAbleShape
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int arc;

    RoundSolidSquare(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
        this.arc = 10;
    }

    public void draw(Graphics2D g2)
    {
        // TODO Auto-generated method stub
        RoundRectangle2D.Double square = new RoundRectangle2D.Double(x, y,
                width, height, arc, arc);
        g2.fill(square);
    }

}
