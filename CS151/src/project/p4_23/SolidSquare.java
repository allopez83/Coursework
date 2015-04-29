package project.p4_23;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Represents number of rocks in a pit
 *
 */
public class SolidSquare implements DrawAbleShape
{
    int x;
    int y;
    int width;
    int height;

    SolidSquare(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
    }

    public void draw(Graphics2D g2)
    {
        // TODO Auto-generated method stub
        Rectangle2D.Double square = new Rectangle2D.Double(x, y, width, height);
        g2.fill(square);

    }

}
