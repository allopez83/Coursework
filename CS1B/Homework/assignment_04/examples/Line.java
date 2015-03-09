package assignment_04.examples;

import java.awt.Point;

/**
 * One object of class Line contains two points on the (x,y) plane.
 */
public class Line implements Cloneable {
    private Point p1;
    private Point p2;
    
    public Line (int startx, int starty, int endx, int endy) {
        p1 = new Point (startx, starty);
        p2 = new Point (endx, endy);
    }
    public Line() {
        p1 = new Point (0,0);
        p2 = new Point(0,0);
    }
    /**
     * Creates new Point objects that are copies of start and end.
     * Makes the new Line object refer to the new Point objects.
     */
    public Line(Point start, Point end) {
        p1 = new Point (start);
        p2 = new Point (end);
    }

    /**
     * Returns a String containing the values for p1 and p2
     */
    public String toString() {
        return "p1 = " + p1 + ", and p2 = " + p2;
    }

    /**
     * Returns a new independent Line object containing the same values as this.
     */
    public Object clone() {
        return new Line(new Point(this.p1), new Point(this.p2));
    }

    /**
     * adds 1 to the x and y inside p1 and p2.
     */
    public void increment() // adds 1 to all x's and y's
    {
        p1.x++; // strange how class Point has PUBLIC data fields x and y!
        p1.y++;
        p2.x++;
        p2.y++;
    }
}