"""
Recall that a Line is defined by two Points in the (x, y) plane. Write class Line where one object represents a Line; the Line object is stored as two Point objects, start and end. Define appropriate instance variables and the following instance methods:

• __init__() sets the initial values for start and end to the two Point objects that are passed in through its parameters. Assume that the caller (e.g. main) passes in two Point objects to this method (i.e. no error checking required.)

• __str__() returns a string representation of the object that calls it.

"""

from point import Point

class Line:
    def __init__(self, start=Point(), end=Point()):
        # Ensure x and y are always float even if int passed in
        self.start = start
        self.end = end
    def __str__(self):
        return "Line from " + str(self.start) + " to " + str(self.end)
    """
    Write an additional method for class Line:

    • length() method returns the length of the line.

    Recall that the length of a line with start point (startX, startY) and end point (endX, endY) is:

                   square root of ( (startX-endX)2 + (startY-endY)2 ) )
    """
    def length(self):
        return ((self.start.x - self.end.x) ** 2 + (self.start.y - self.end.y) ** 2) ** 0.5


if __name__ == "__main__":
    l1 = Line()
    print(l1)
    print("length:", l1.length())
    p1 = Point(1, 1)
    p2 = Point(2, 2)
    l1 = Line(p1, p2)
    print(l1)
    print("length:", l1.length())
    p1 = Point(1, 1)
    p2 = Point(1, 5)
    l1 = Line(p1, p2)
    print(l1)
    p1 = Point(-1, -1)
    print("length:", l1.length())
    p2 = Point(-1, -5)
    l1 = Line(p1, p2)
    print(l1)
    print("length:", l1.length())
