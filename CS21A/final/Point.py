"""
Define a class Point, where one object of class Point represents a point in the (x, y) plane. Define appropriate instance variables and the following instance methods:

• __init__() sets the initial values for x and y to (0, 0) unless the caller (e.g. main) passes in arguments. If the caller passes in arguments, then set x and y to the arguments that the caller passes in.

• __str__() returns a string representation of the object that calls it.
"""

class Point:
    def __init__(self, x=0.0, y=0.0):
        # Ensure x and y are always float even if int passed in
        self.x = float(x)
        self.y = float(y)
    def __str__(self):
        return "(" + str(self.x) + ", " + str(self.y) + ")"

if __name__ == "__main__":
    p1 = Point()
    print(p1)
    p1 = Point(1, 2)
    print(p1)
