""""
For this question, you need to refer to class Account and class SavingsAccount from the Module for "Week 7 : Inheritance"

Write a call to the __int__() method (from the previous question) on an object of class SavingsAccount. You need to provide both the code that creates an object of class SavingsAccount and the code that calls the __int__() method in order to test it.

"""
from savingsaccount import SavingsAccount

a = SavingsAccount()
a.balance = 1000
print(a)
print(a.__int__())
print(int(a))
