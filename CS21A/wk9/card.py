"""
Create a class Hand in its own module. One object of class Hand stores a number of Card objects. For this assignment you will submit three separate modules, one with the definition of class Card, one with the definition of class Hand and one with the main application that thoroughly tests class Hand.

Two alternatives for Extra Credit - you cannot get credit for both!

(+1 point): After you have thoroughly tested the class Hand and all of its methods, add code to your main program that stores one Hand object as a Pickle file and reads it back into a new Hand object. You are only eligible for this extra credit if class Hand has all four of the methods above working.

OR

(+2 points): After you have thoroughly tested the class Hand and all of its methods, add code to your main program that stores one Hand object as a text file on the disk and reads it back into a new Hand object. You are only eligible for this extra credit if class Hand has all four of the methods above working.

Notes:

-Start by making any and all modifications suggested by my comments to your previous submission of class Card from Assignment #6 "A Robust Card Object"

-Once your class Card is tested and working well, you will not make any further modifications to it for the purposes of class Hand.

-You can keep the test code for class Card intact. If it is indented inside an if __name__ == "__main__", then it will not be executed when your main program's module imports it.

-To save time, write and test one method for class Hand at a time.

-Under no circumstances are you to attempt the extra credit until you are completely finished with writing and testing all the methods in class Hand.


"""


""" One object of class card represents a playing card, with a suit and rank value
"""
class Card:
    suitDict = {'d': 'diamonds', 'c': 'clubs', 'h': 'hearts', 's': 'spades'}
    valueDict = {1: 'ace', 11: 'jack', 12: 'queen', 13: 'king'}
    """ rank: 1-13 int representing the card's ace through king value
        suit: char either d, c, h, or s representing suit diamonds, clubs, hearts, or spades
        By default, cards are ace of diamonds
    """
    def __init__(self, rank=1, suit='s'):
        # Verify valid parameters
        if type(rank) != int or type(suit) != str:
            raise TypeError()
        if rank < 1 or rank > 13:
            raise ValueError()
        if suit not in self.suitDict:
            raise ValueError
        # Apply values
        self.rank = rank
        self.suit = suit
    """ int between 1-13 representing the card's rank
    """
    def getRank(self):
        return self.rank
    """ char representing the card's suit, either d, c, h, or s
    """
    def getSuit(self):
        return self.suit
    """ what the card would be worth in blackjack
    """
    def bjValue(self):
        if self.rank > 10:
            return 10
        else:
            return self.rank
    """ String representation of the card
    """
    def __str__(self):
        if self.getRank() in Card.valueDict:  # if valueDict has key
            rank = Card.valueDict[self.getRank()]
        else:
            rank = self.getRank()
        return str(rank) + " of " + Card.suitDict[self.getSuit()]


""" Card test program if running card.py as __main__
"""
if __name__ == "__main__":
    # Default
    card = Card()
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())

    # Some values
    card = Card(1, "d")
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())
    card = Card(5, "c")
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())
    card = Card(10, "h")
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())

    # Face value test
    card = Card(11, "s")
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())
    card = Card(12, "s")
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())
    card = Card(13, "s")
    print(card, ", bjValue:", card.bjValue(), ", has rank:", card.getRank(), ", and suit:", card.getSuit())

    # Input checking

    # invalid type
    try:
        card = Card(1, 2)
    except:
        print("Caught error for invalid suit type")
    try:
        card = Card('d', 'd')
    except:
        print("Caught error for invalid rank type")

    # out of bounds
    try:
        card = Card(-1)
    except:
        print("Caught error for out-of-bounds rank")
    try:
        card = Card(14)
    except:
        print("Caught error for out-of-bounds rank")
    try:
        card = Card('test')
    except:
        print("Caught error for invalid suit")
    try:
        card = Card('z')
    except:
        print("Caught error for invalid suit")

""" Program Output:
ace of spades , bjValue: 1 , has rank: 1 , and suit: s
ace of diamonds , bjValue: 1 , has rank: 1 , and suit: d
5 of clubs , bjValue: 5 , has rank: 5 , and suit: c
10 of hearts , bjValue: 10 , has rank: 10 , and suit: h
jack of spades , bjValue: 10 , has rank: 11 , and suit: s
queen of spades , bjValue: 10 , has rank: 12 , and suit: s
king of spades , bjValue: 10 , has rank: 13 , and suit: s
Caught error for invalid suit type
Caught error for invalid rank type
Caught error for out-of-bounds rank
Caught error for out-of-bounds rank
Caught error for invalid suit
Caught error for invalid suit
"""
