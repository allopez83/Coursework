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
