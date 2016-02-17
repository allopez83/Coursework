import random

secretNumber = random.randint(1,10)
userInput = input ("Pick a number ")
try:
    userGuess = int(userInput)
except:              # executed only when an Error IS raised
    print ("you must type a number") 
else:                # executed only when an Error IS NOT raised
    if userGuess == secretNumber:    
        print ("You got it!")
    else:
        print ("Wrong, the number is "+ str(secretNumber))
finally:
   print ("goodbye")