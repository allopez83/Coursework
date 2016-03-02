"""
Defines and tests class Currency
"""
from functools import total_ordering


@total_ordering
class Currency:
    """
    One object of class Currency stores one amount of money, dollars and cents.
    """

    def __add__(self, other):
        """
        returns the result of adding self to other
        """
        total = Currency(self.dollars, self.cents)
        total.dollars = total.dollars + other.dollars
        total.cents = total.cents + other.cents
        if total.cents > 100:
            total.cents = total.cents - 100
            total.dollars = total.dollars + 1
        return total

    def __init__(self, dollars=0, cents=0):
        """
        Sets the initial value of dollars and cents.
        """
        self.dollars = dollars
        self.cents = cents

    def __str__(self):
        return "$" + str(self.dollars) + "." + str(self.cents)

    def __eq__(self, other):
        """
        Returns True if self == other, False otherwise
        """
        return self.dollars == other.dollars and self.cents == other.cents

    def __lt__(self, other):
        """
        Returns True if self < other, False otherwise
        """
        if self.dollars < other.dollars:
            return True
        elif self.dollars > other.dollars:
            return False
        else:  # dollars are equal
            return self.cents < other.cents


if __name__ == "__main__":
    candyPrice = Currency(1, 17)  # $1.17
    print(candyPrice)
    bookPrice = Currency(12, 99)  # $12.99
    print(bookPrice)
    print(bookPrice + candyPrice)
    print(candyPrice < bookPrice)
    print(candyPrice > bookPrice)
    print(candyPrice == bookPrice)
    print(candyPrice <= bookPrice)
    print(Currency.__lt__.__doc__)
