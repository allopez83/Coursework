/*********************************************************

Subject:
Assignment 2: use console input/output and methods to calculate clothing sizes

Author:
Hansen Wu

Change log:
2013.07.11
   Project created
2013.07.12
   Program completed
2013.07.13
   Console output cleaned
   Thoroughly debugged
   Added "paste from console"

*********************************************************/

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

// Console output
void welcome();
void printResults(double hat, double jacket, double waist);
// Get and check input
int getAge();
double getHeight();
double getWeight();
bool checkParameter(int val, int low, int high);
bool checkParameter(double val, int low, int high);
bool numbersOnly(string val);
// Calculate sizes
double getSize_Hat(double height, double weight);
double getSize_Jacket(int age, double height, double weight);
double getSize_Waist(int age, double weight);
// Continue or exit program
bool keepGoing();

int main()
{
   int user_age;
   double user_height_inch,
          user_weight_lb,
          size_hat,
          size_jacket,
          size_waist;
   bool repeat = true;

   welcome();
   while (repeat)
   {
      // Get info user
      user_age = getAge();
      user_height_inch = getHeight();
      user_weight_lb = getWeight();

      // Calculate clothing sizes
      size_hat = getSize_Hat(user_height_inch, user_weight_lb);
      size_jacket = getSize_Jacket(user_age, user_height_inch, user_weight_lb);
      size_waist = getSize_Waist(user_age, user_weight_lb);

      // Print and ask for another go
      printResults(size_hat, size_jacket, size_waist);
      repeat = keepGoing();
      cout << endl; // Extra line for clean output
   }
   cout << "Goodbye, then!\n" << endl;

   return 0;
}

// Print welcome message
void welcome()
{
   string message = "Hello, I'm here to calculate clothing size using age, height, and\n"
                    "weight! Simply follow the given instructions, and I'll tell you\n"
                    "what someone's hat size, jacket size, and waist size in inches.\n";
   cout << message << endl;
}

// Gets and checks client age
int getAge()
{
   int age;
   bool acceptable = false,
        numbers,
        parameter;
   string input,
          message_welcome = "First off, I need to be given your age. I'll ignore any decimals\n"
                            "given to me, so keep it simple. Anything other than numbers and\n"
                            "my precise calculations will be thrown off!",
          message_error = "What? That can't be correct! I'm afraid that's not a value I can\n"
                          "use! I'll only accept whole numbers between 1 and 200. Now give\n"
                          "me an acceptable age!";

   cout << message_welcome << endl;
   // While the input is not acceptable
   while (!acceptable)
   {
      // Get input and convert to number
      getline(cin, input);
      istringstream(input) >> age;
      // Check if valid
      numbers = numbersOnly(input);
      parameter = checkParameter(age, 1, 200);
      // If any of the tests report bad input, tell the client
      if (!numbers || !parameter)
         cout << message_error << endl;
      // Otherwise, flag the input as ok
      else
         acceptable = true;
   }
   cout << endl; // Extra line for clean output

   return age;
}

// Gets and checks client height
double getHeight()
{
   double height;
   bool acceptable = false,
        numbers,
        parameter;
   string input,
          message_welcome = "Ok, and now what's your height in inches? I'll only accept numbers,\n"
                            "but decimals are ok.",
          message_error = "I can't use that; that can't possibly be your height! It\n"
                          "has be at least between 12 and 180!";

   cout << message_welcome << endl;
   // While the input is not acceptable
   while (!acceptable)
   {
      // Get input and convert to number
      getline(cin, input);
      istringstream(input) >> height;
      // Check if valid
      numbers = numbersOnly(input);
      parameter = checkParameter(height, 12, 180);
      // If any of the tests report bad input, tell the client
      if (!numbers || !parameter)
         cout << message_error << endl;
      // Otherwise, flag the input as ok
      else
         acceptable = true;
   }
   cout << endl; // Extra line for clean output

   return height;
}

// Gets and check's client weight
double getWeight()
{
   double weight;
   bool acceptable = false,
        numbers,
        parameter;
   string input,
          message_welcome = "Thanks, now what is your weight, in pounds? Numbers only, and\n"
                            "I'll accept decimals here.",
          message_error = "You're kidding me, I can't work with that kind of weight! It has\n"
                          "to be between 0 and 1500";

   cout << message_welcome << endl;
   // While the input is not acceptable
   while (!acceptable)
   {
      // Get input and convert to number
      getline(cin, input);
      istringstream(input) >> weight;
      // Check if valid
      numbers = numbersOnly(input);
      parameter = checkParameter(weight, 0, 1500);
      // If any of the tests report bad input, tell the client
      if (!numbers || !parameter)
         cout << message_error << endl;
      // Otherwise, flag the input as ok
      else
         acceptable = true;
   }
   cout << endl; // Extra line for clean output

   return weight;
}

// Calculate hat size with given parameters
double getSize_Hat(double height, double weight)
{
   double equationConstant = 2.9;
   return ((weight/height) * equationConstant);
}

// Calculate jacket size with given parameters
double getSize_Jacket(int age, double height, double weight)
{
   double eq_ageModifier = 0,
          oneEigthInch = 0.125,
          result;
   int eq_constant = 288;

   // Find the age modifier
   age -= 40;
   while (age >= 0)
   {
      eq_ageModifier += oneEigthInch;
      age -= 10;
   }
   // Find jacket size
   result = ((height * weight) / eq_constant) + eq_ageModifier;

   return result;
}

// Calculate waist size with given parameters
double getSize_Waist(int age, double weight)
{
   double eq_ageModifier = 0,
          eq_constant = 5.7,
          oneTenthInch = 0.1,
          result;

   // Find the age modifier
   age -= 30;
   while (age >= 0)
   {
      eq_ageModifier += oneTenthInch;
      age -= 2;
   }
   // Find waist size
   result = ((weight / eq_constant) + eq_ageModifier);

   return result;
}

// Prints out calculated sizes
void printResults(double hat, double jacket, double waist)
{
   cout << "Great! all provided values appear to be fine. According to my\n"
           "calculations, I have come up with the following sizes:\n"
           "Hat size:        " << hat << "\n"
           "Jacket size:     " << jacket << "\n"
           "Waist in inches: " << waist << "\n"
           << endl;
}

// Decide whether or not to continue loop
bool keepGoing()
{
   string input,
          message_welcome = "Do you want me to perform another calculation? Reply with a 'y'\n"
                            "or 'n'.",
          message_error = "What? Your response has to at least start with a 'y' or an 'n' or\n"
                          "else I don't know what to do. Now do you want to perform another\n"
                          "calculation?";
   char result;

   cout << message_welcome << endl;
   // Keep going until boolean is returned
   while(true)
   {
      // Get input's first char
      getline(cin, input);
      result = input[0];
      // Return boolean value and exit
      if ((result == 'y') || (result == 'Y'))
         return true;
      else if ((result == 'n') || (result == 'N'))
         return false;
      // Else, print error message;
      else
         cout << message_error << endl;
   }
}

/*
Checks if an int value is within the specified range (exclusive).

val: value to check
low: has to be higher than this
high: has to be lower than this
*/
bool checkParameter(int val, int low, int high)
{
   if ((val > high) || (val < low))
      return false;
   else
      return true;
}

/*
Checks if a double value is within the specified range (exclusive).

val: value to check
low: has to be higher than this
high: has to be lower than this
*/
bool checkParameter(double val, int low, int high)
{
   if ((val > high) || (val < low))
      return false;
   else
      return true;
}

/*
Checks if there is anything other than letters or decimals in the input, such as
letters or punctuation marks. If there is, then return false. Else, return true.
This is to address the issue of certain letters being able to sneak past the
parameter check due to them being converted into numbers, and ending up as
acceptable ones.

val: string to parse through
*/
bool numbersOnly(string val)
{
   int size = val.length(),
       location;
   char current;
   
   // Check the entire string
   for (location = 0; location < size; location++)
   {
      current = val[location];
      // Number
      if ('0' <= current && current <= '9')
         continue;
      // Decimal
      else if (current == '.')
         continue;
      // Something else
      else
         return false; // Abort!
   }

   // Seems like there's only numbers here, and maybe a decimal!
   return true;
}

/* PASTE FROM CONSOLE ************************************
Hello, I'm here to calculate clothing size using age, height, and
weight! Simply follow the given instructions, and I'll tell you
what someone's hat size, jacket size, and waist size in inches.

First off, I need to be given an age. I'll ignore any decimals
given to me, so keep it simple. Anything other than numbers and
my precise calculations will be thrown off!
17

Ok, and now what's the subject's height in inches? I'll only
accept numbers, but decimals are ok.
65.5

Thanks, now what is the person's weight, in pounds? Numbers only,
and I'll accept decimals here.
112.5

Great! all provided values appear to be fine. According to my
calculations, I have come up with the following sizes:
Hat size:        4.98092
Jacket size:     25.5859
Waist in inches: 19.7368

Do you want me to perform another calculation? Reply with a 'y'
or 'n'.
y

First off, I need to be given an age. I'll ignore any decimals
given to me, so keep it simple. Anything other than numbers and
my precise calculations will be thrown off!
abcde
What? That can't be correct! I'm afraid that's not a value I can
use! I'll only accept whole numbers between 1 and 200. Now give
me an acceptable age!
0
What? That can't be correct! I'm afraid that's not a value I can
use! I'll only accept whole numbers between 1 and 200. Now give
me an acceptable age!
-4
What? That can't be correct! I'm afraid that's not a value I can
use! I'll only accept whole numbers between 1 and 200. Now give
me an acceptable age!
500000
What? That can't be correct! I'm afraid that's not a value I can
use! I'll only accept whole numbers between 1 and 200. Now give
me an acceptable age!
#$%^&*
What? That can't be correct! I'm afraid that's not a value I can
use! I'll only accept whole numbers between 1 and 200. Now give
me an acceptable age!
50

Ok, and now what's the subject's height in inches? I'll only
accept numbers, but decimals are ok.
jgpoew
I can't use that; that can't possibly be someone's height! It
has be at least between 12 and 180!
1566
I can't use that; that can't possibly be someone's height! It
has be at least between 12 and 180!
-456
I can't use that; that can't possibly be someone's height! It
has be at least between 12 and 180!
50

Thanks, now what is the person's weight, in pounds? Numbers only,
and I'll accept decimals here.
[]'/"}{
You're kidding me, I can't work with that kind of weight! It has
to be between 0 and 1500
fdsa
You're kidding me, I can't work with that kind of weight! It has
to be between 0 and 1500
-456
You're kidding me, I can't work with that kind of weight! It has
to be between 0 and 1500
5464664
You're kidding me, I can't work with that kind of weight! It has
to be between 0 and 1500
1

Great! all provided values appear to be fine. According to my
calculations, I have come up with the following sizes:
Hat size:        0.058
Jacket size:     0.423611
Waist in inches: 1.27544

Do you want me to perform another calculation? Reply with a 'y'
or 'n'.
maybe
What? Your response has to at least start with a 'y' or an 'n' or
else I don't know what to do. Now do you want to perform another
calculation?
why?
What? Your response has to at least start with a 'y' or an 'n' or
else I don't know what to do. Now do you want to perform another
calculation?
fine!
What? Your response has to at least start with a 'y' or an 'n' or
else I don't know what to do. Now do you want to perform another
calculation?
No!

Goodbye, then!

Press any key to continue . . .
****************************************** END OF PASTE */
