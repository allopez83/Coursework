# Problem 3
# Minimize comparisons for following block of code
def tax(income):
    taxRate = 0
    if income >20000 and income <30000:
        taxRate = .15
    if income >=30000 and income < 45000:
        taxRate = .20
    if income >=45000 and income <75000:
        taxRate = .30
    if income >=75000:
        taxrate = .40
    print(taxRate)
    
    taxRate = 0
    if income >20000:
        taxrate = .15
    if income >=30000:
        taxRate = .20
    if income >=45000:
        taxRate = .30
    else
        taxrate = .40
    print(taxRate)

tax(19999)
tax(20000)
tax(29999)
tax(30000)
tax(44999)
tax(45000)
tax(74999)
tax(75000)
# Didn't end up solving this

# Problem 4
# Create a function for finding factorial
def factorial(input):
    if input == 0:
        return 1
    return input * factorial(input - 1)

# Problem 5
# Create two Die and roll 10 times
class Die:
    def __init__(self):
        self.value = 1
    def __str__(self):
        return "This die has value : " + str(self.value)
    def roll(self):
        self.value = randrange(1,7)

d1 = Die()
d2 = Die()

for rollCount in range(0, 10):
    d1.roll()
    d2.roll()
    print("Roll #", rollCount+1)
    print("Die 1: ", d1, ", and Die 2: ", d2)

# Problem 6
# Read in a string and convert to number, handling exceptions
userInput = input("Please type a real number: ")
try:
    int(userInput)
except:
    print ("You typed a char that isn't appropriate in a real number.")
else:
    print("Thank you for following instructions.")
finally:
    print("I hope you play again.")
