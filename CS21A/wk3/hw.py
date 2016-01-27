"""
Write a program that combines several dictionaries into a new dictionary with all the keys of the original dictionaries. If a key appears in more than one input dictionary, the value corresponding to that key in the new dictionary should be a list containing all the values encountered in the input dictionaries that correspond to that key.  If a key appears in only one of the input dictionaries, the value for that key in the resulting dictionary must be exactly as it was in the input dictionary.

There will be no user input for this assignment, hardcode all data in your program. You must prove that your program works for merging at least three dictionaries with at least some matching keys in the input dictionaries.

In order to receive full credit for this assignment:

- perform enough tests in the printout you turn in, in order to prove that it works

- test that your program can merge data sets that include two duplicate keys and three duplicate keys. That is, show a test where the same key appears in all three input dictionaries.

- include a comment at the top of the file that tells what the program does.

- Follow all program guidelines

"""

# Merges three input dictionaries and store results in newDictionary

# 'a' collision in input 1 and 3, 'd' collision in all inputs
input1 = {'a': 'apple', 'b': 'banana', 'd': 'durian'}
input2 = {'c': 'cherry', 'd': 'donut', 'g': 'grape fruit'}
input3 = {'a': 'apricot', 'd': 'date', 'e': 'egg'}
newDictionary = {}

collision = {}

# Find conflicts between 1 and 2
for key in input1:
    if key in input2:
        if key not in collision:
            collision[key] = []  # initialize empty list
        entry = collision[key]
        if input1.get(key) not in entry:
            collision[key].append(input3.get(key))
        if input2.get(key) not in entry:
            collision[key].append(input1.get(key))
# ... 2 and 3
for key in input2:
    if key in input3:
        if key not in collision:
            collision[key] = []  # initialize empty list
        entry = collision[key]
        if input2.get(key) not in entry:
            collision[key].append(input3.get(key))
        if input3.get(key) not in entry:
            collision[key].append(input1.get(key))
# ... 3 and 1
for key in input3:
    if key in input1:
        if key not in collision:
            collision[key] = []  # initialize empty list
        entry = collision[key]
        if input3.get(key) not in entry:
            collision[key].append(input3.get(key))
        if input1.get(key) not in entry:
            collision[key].append(input1.get(key))

# Print out result for test purposes
# print(collision)

# Merge
for key in input1:
    if key not in collision:
        newDictionary[key] = input1.get(key)
for key in input2:
    if key not in collision:
        newDictionary[key] = input2.get(key)
for key in input3:
    if key not in collision:
        newDictionary[key] = input3.get(key)
for key in collision:
    newDictionary[key] = collision.get(key)

print(newDictionary)

""" Output
{'g': 'grape fruit', 'e': 'egg', 'c': 'cherry', 'd': ['date', 'durian', 'date'], 'a': ['apricot', 'apple'], 'b': 'banana'}

"""
