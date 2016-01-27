"""
Dear $ADDRESSEE,

I would like you to vote for $CANDIDATE
because I think he is best for this state.

Sincerely,
$SENDER


Create a string containing the unchanging part of the letter, use "\n" for end of lines, and use "%s" as the placehoder for each of the variable parts of the letter.

Create a tuple for each letter. The tuple will contain 3 strings: addressee, candidate, sender. Because you will have a number of slightly different letters, you will need a list of these tuples, i.e. put these tuples into a list.

There will be no input from the keyboard for this assignment, all data will be hardcoded in your program. Your program must output 3 letters that differ only in the addressee, candidate and sender.

In order to receive full credit, your program must:

- use both lists and tuples,
- print the output in the format used in the sample letters above,
- have a comment as the first line that tells what the program does.
- follow all program guidelines

Challenge: Read any number of names of the addressee, candidate and sender from the user at the keyboard. Your program must repeatedly prompt the user and read all three strings, until the user indicates that she is finished. Only after the user had typed in ALL strings for ALL letters, your program then prints all the letters. If you succeed in implementing this challenge, please submit just your solution to the challenge and not the solution to the basic assignment.
"""

# Creates letters according to a template, substituting in the addressee, candidate, and sender according to hard coded preset data.

template = 'Dear %s,\n\nI would like you to vote for %s\nbecause I think he is best for this state.\n\nSincerely,\n%s'
letters = []
contacts = []

while True:
    userIn = input("Enter \"NEW\" for a new letter or \"EXIT\" to print and leave the program.\n")
    if userIn == 'NEW':
        addressee = input('Addressee: ')
        candidate = input('Candidate: ')
        sender = input('Sender: ')
        contact = (addressee, candidate, sender)
        letters.append(template % contact)

    if userIn == 'EXIT':
        for letter in letters:
            print(letter + '\n-----')
        break

""" Program Output
Enter "NEW" for a new letter or "EXIT" to print and leave the program.
NEW
Addressee: ADAM
Candidate: BETTY
Sender: CHARLIE
Enter "NEW" for a new letter or "EXIT" to print and leave the program.
NEW
Addressee: ANNE
Candidate: BOB
Sender: CARL
Enter "NEW" for a new letter or "EXIT" to print and leave the program.
EXIT
Dear ADAM,

I would like you to vote for BETTY
because I think he is best for this state.

Sincerely,
CHARLIE
-----
Dear ANNE,

I would like you to vote for BOB
because I think he is best for this state.

Sincerely,
CARL
-----
"""
