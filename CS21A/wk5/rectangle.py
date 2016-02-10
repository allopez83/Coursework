"""
    Defines class Rectangle and tests it by creating two Rectangle objects
"""
class Rectangle:
    """
        One object of this class stores one rectangle's length and width
    """
    def __init__ (self):
        """
            Sets both height and width to 0
        """
        self.height = 0
        self.width = 0

    def setData(self, height, width):
        """
            Sets the object's dimensions to height by width
        """
        self.height = height
        self.width = width

    def __str__(self):
        """
            returns a sentence that tells the height and width of the object
        """
        return "height = %i, and width = %i" % (self.height, self.width)


"""
     Creates two Rectangle objects and calls methods on them for testing purposes
"""
if __name__ == "__main__":
    r1 = Rectangle()
    print (r1)   # automatcially calls __str__(self) method and prints the returned value
    r1.setData(3,4)
    print (r1)
    r2 = Rectangle()
    r2.setData(5,6)
    print (r2)
    