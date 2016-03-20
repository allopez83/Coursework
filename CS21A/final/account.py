"""
For these next two questions, you need to refer to class Account and class SavingsAccount from the Module for "Week 7 : Inheritance"

Define the __int__() method in class Account, and have it return the balance in the Account object that calls it. Please provide only the method definition, I know that the method definition belongs inside the class Account definition.

IMPORTANT NOTE: I am asking you to write a method called __int__() - as in "integer" - and NOT the __init__() method.
"""

""" One object of class Account represents one bank account """
class Account:
    def __init__(self):
        print ("Account constructor")
        self.balance = 0
        self.customer = "Mickey Mouse"

    def __str__(self):
        return "%s has a balance of %d" % (self.customer, self.balance)

    def __int__(self):
        return self.balance

if __name__ == "__main__":
    a = Account()
    a.balance = 1000
    print(a)
    print(str(a))
    print(int(a))
