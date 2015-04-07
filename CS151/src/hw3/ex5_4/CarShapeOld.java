package hw3.ex5_4;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class CarShapeOld implements Shape
{
   private int width, height;
   private int bodyWidth, bodyHeight;
   private int wheelRadius;

   Rectangle2D body;
   Ellipse2D wheelL, wheelR;

   CarShapeOld()
   {
   }

   CarShapeOld(int w, int h, int c)
   {
      width = w;
      height = h;
      bodyWidth = w;
      bodyHeight = h / 2;
      wheelRadius = bodyHeight;
   }

   public void draw(Graphics2D g2)
   {
      body = new Rectangle(bodyWidth, bodyHeight);
      wheelL = new Ellipse2D.Double(0, bodyHeight, wheelRadius, wheelRadius);
      wheelL = new Ellipse2D.Double(0, bodyWidth - wheelRadius, wheelRadius,
            wheelRadius);
      g2.draw(body);
      g2.draw(wheelL);
      g2.draw(wheelR);
   }

   public boolean contains(double x, double y)
   {
      return this.contains(new Point2D.Double(x, y));
   }
   public boolean contains(Point2D p)
   {
      return body.contains(p);
   }

   public boolean contains(double x, double y, double w, double h)
   {
      return this.contains(new Rectangle2D.Double(x, y, w, h));
   }
   public boolean contains(Rectangle2D r)
   {
      if (body.intersects(r) && wheelL.intersects(r) && wheelR.intersects(r))
         return true;
      return false;
   }

   public boolean intersects(double x, double y, double w, double h)
   {
      return this.intersects(new Rectangle2D.Double(x, y, w, h));
   }
   public boolean intersects(Rectangle2D r)
   {
      if (body.intersects(r) && wheelL.intersects(r) && wheelR.intersects(r))
         return true;
      return false;
   }

   // public Rectangle getBounds()
   // {
   // // TODO Auto-generated method stub
   // return null;
   // }
   //
   //
   // public Rectangle2D getBounds2D()
   // {
   // // TODO Auto-generated method stub
   // return null;
   // }

}
