"""

1)   __init__(numCardsInHand) takes an integer as parameter and constructs a Hand object with numCardsInHand Card objects inside it. These Card objects are generated randomly. For simplicity, assume an infinite number of decks.

2)   bjValue() returns the blackjack value for the whole Hand of cards

3)   __str__() returns a string containing all the Cards in the Hand

4)   hitMe() adds one randomly generated Card to the Hand
"""

import card
import random

""" Represents playing cards that would go in a user's hand using the Card class
"""
class Hand:
    hand = []
    """ Creates new Hand with a specified number of randomly drawn cards
    """
    def __init__(self, numCardsInHand):
        if numCardsInHand != 0:
            for cardNumber in range(numCardsInHand):
                self.hitMe()

    """ Represents the Hand object as a string, showing all cards held
    """
    def __str__(self):
        contents = "This hand has: "
        if len(self.hand) == 0:
            contents += "no cards"
        else:
            for card in self.hand:
                contents += str(card) + ", "
        return contents

    """ Add another randomly drawn card to user's Hand
    """
    def hitMe(self):
        newCard = card.Card(random.randint(1, 13), random.choice("dchs"))
        self.hand.append(newCard)

    """ Determines the total black jack value for the cards in this hand
    """
    def bjValue(self):
        value = 0
        for card in self.hand:
            value += card.bjValue()
        return value
