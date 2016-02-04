import math


""" Convert a number in the hundreds and below to it's word counterpart and returns it as a string
"""
def numToWord(n):
    names = {1: "one ", 2: "two ", 3: "three ", 4: "four ", 5: "five ", 6: "six ", 7: "seven ", 8: "eight ", 9: "nine ",
             10: "ten ", 11: "eleven ", 12: "twelve ", 13: "thirteen ", 14: "fourteen ", 15: "fifteen ", 16: "sixteen ",
             17: "seventeen ", 18: "eighteen ", 19: "nineteen ", 20: "twenty ", 30: "thirty ", 40: "forty ",
             50: "fifty ", 60: "sixty ", 70: "seventy ", 80: "eighty ", 90: "ninety "}
    values = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50, 60, 70, 80, 90, 100] # 100 to allow ninety to go through
    result = []
    # Hundreds
    if n >= 100:
        name = names[math.floor(n / 100)]
        result.append(name + "hundred ")
        n %= 100
    # Tens and ones
    while n != 0:
        done = False
        for v in values:
            if done:
                continue
            elif v > n:
                atleast = values[values.index(v) - 1]
                result.append(names[atleast])
                n -= atleast
                done = True
    return ''.join(result)

""" Converts an integer between -999,999,999 and 999,999,999 to word format and returns that as a string
"""
def spell(number):
    result = []
    # Negative check
    if number < 0:
        result.append("negative ")
        number *= -1
    # Check if >= million
    if number >= 1000000:
        result.append(numToWord(math.floor(number / 1000000)) + "million, ")
        number %= 1000000
    # Check if >= thousand
    if number >= 1000:
        result.append(numToWord(math.floor(number / 1000)) + "thousand, ")
        number %= 1000
    # Hundreds, tens, and ones
    result.append(numToWord(number))
    return ''.join(result)

print(spell(123))
print(spell(123456789))
print(spell(-987654321))
print(spell(98))

""" Output
one hundred twenty three
one hundred twenty three million, four hundred fifty six thousand, seven hundred eighty nine
negative nine hundred eighty seven million, six hundred fifty four thousand, three hundred twenty one
ninety eight

"""
