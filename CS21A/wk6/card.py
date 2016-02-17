"""
The __init__() method should not allow a Card object to be initialized with a 'c' for the rank and a 7 for the suit. More generally, the __init__() method should raise an Error when any inappropriate values are sent into its parameters. This will ensure that there will never be a meaningless Card object in any program ever.

For this assignment, you will add code to the __init__() method that raises a:

1) TypeError when the type of the first parameter is not an int.

2) TypeError when the type of the second parameter is not a string.

3) ValueError when the value of the first parameter is not in the range 1..13 inclusive.

4) ValueError when the value of the second parameter is not one of the strings in the set {'s', 'h', 'c', 'd'}
"""

""" Card Object
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


""" Card test program
"""
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
