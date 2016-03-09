"""
Create a main program in its own module that thoroughly tests class Hand. You will have three modules/files to upload to your Etudes Assignment submission: card.py, hand.py and the module that contains your main program.
"""
import hand
import pickle

testHand = hand.Hand(0)
print("Hand with zero cards\n" + str(testHand))
testHand = hand.Hand(3)
print("\nNew hand with three cards\n" + str(testHand))
print("\nBlackjack value\n" + str(testHand.bjValue()))
testHand.hitMe()
print("\nAfter adding a card\n" + str(testHand))
value =  testHand.bjValue()
print("\nBlackjack value\n" + str(value))

print("\nPickling this hand...")
saveLocation = "pickedHand.pkl"
file = open(saveLocation, 'wb')
pickle.dump(testHand, file)
file.close()

print("\nThis is what I got back")
file = open(saveLocation, 'rb')
loadedHand = pickle.load(file)
print(loadedHand)
if loadedHand.bjValue() == value:
    print("The hand appears to match. That's some good vinegar!")

""" Program Output
Hand with zero cards
This hand has: no cards

New hand with three cards
This hand has: 9 of spades, 9 of diamonds, 8 of clubs, 

Blackjack value
26

After adding a card
This hand has: 9 of spades, 9 of diamonds, 8 of clubs, 6 of hearts, 

Blackjack value
32

Pickling this hand...

This is what I got back
This hand has: 9 of spades, 9 of diamonds, 8 of clubs, 6 of hearts, 
The hand appears to match. That's some good vinegar!
"""
