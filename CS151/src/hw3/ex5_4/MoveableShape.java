package hw3.ex5_4;

import java.awt.*;

/**
 * A shape that can be moved around.
 */
public interface MoveableShape
{
   /**
    * Draws the shape. the graphics context
    * @param g2
    */
   void draw(Graphics2D g2);

   /**
    * Moves the shape by a given amount. the amount to translate in x-direction
    * the amount to translate in y-direction
    * @param dx
    * @param dy
    */
   void translate(int dx, int dy);
}
