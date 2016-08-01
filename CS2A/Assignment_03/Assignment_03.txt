/*********************************************************

Subject:
Assignment 3: Create a data type and main() that uses it

Author:
Hansen Wu

Change log:
2013.07.17
   Project created
   main() completed
2013.07.19
   Pizza class completed
   Debugged
2013.07.21
   Further debugging
   Pizza class updated
   Added paste from console

*********************************************************/

#include <iostream>
#include <sstream>
#include <string>

using namespace std;

class Pizza
{
private:
   // Used to contain pizza specifications
   int pizza_type,
       pizza_size,
       pizza_pepperoni,
       pizza_cheese,
       pizza_price;
   // Private methods
   void computePrice();

public:
   // Constructors
   Pizza();
   Pizza(int type, int size, int pep, int che);
   // Used to modify pizza specifications, public so client can check values
   static const int TYPE_UNKNOWN = 0,
                    TYPE_DEEP_DISH = 1,
                    TYPE_HAND_TOSSED = 2,
                    TYPE_PAN = 3,
                    SIZE_UNKNOWN = 0,
                    SIZE_SMALL = 1,
                    SIZE_MEDIUM = 2,
                    SIZE_LARGE = 3;
   // Console output
   void outputDescription();
   // Setters
   bool setType(int type),
        setSize(int size),
        setTopping_Pepper(int pep),
        setTopping_Cheese(int che);
   //Getters
   string getType(),
          getSize();
   int getTopping_Pepper(),
       getTopping_Cheese(),
       getPrice();
};

// Main stuffs -------------------------------------------------------------------------
void welcome(),
     printPizza(Pizza pizza);
bool keepGoing();
// Get input
int getType(),
    getSize(),
    getTopping_pep(),
    getTopping_che(int pep);
// Validators
bool isNumber(char val),
     checkParameter(int val, int low, int high);

int main()
{
   Pizza thePizza;
   int type,
       size,
       topping_pep,
       topping_che;
   bool repeat = true;

   welcome();
   while (repeat)
   {
      // Get valid type and size
      type = getType();
      size = getSize();
      // Get pizza topping
      topping_pep = getTopping_pep();
      // If there is room for cheese, ask for cheese toppings
      if (topping_pep < 4)
         topping_che = getTopping_che(topping_pep);
      else
         topping_che = 0;
      // Print for client
      thePizza = Pizza(type, size, topping_pep, topping_che);
      printPizza(thePizza);
      repeat = keepGoing();
      cout << endl; // Extraline for clean output
   }
   cout << "Thank you for ordering!\n" << endl;
   // Done here
   return 0;
}

// Welcome message
void welcome()
{
   string message = "Make your own pizza today, digitally! Just enter your desired\n"
                    "pizza type, size, and number of toppings! It'll even be delivered!\n";
   cout << message << endl;
}

// Find a valid type
int getType()
{
   string message = "What type of pizza would you like? You may any from the list:\n"
                    "1. Deep Dish\n"
                    "2. Hand Tossed\n"
                    "3. Pan\n"
                    "Enter the number preceding the type you desire.",
          error = "Error: invalid input; a number from 1 to 3 is required.",
          input;
   bool valid = false;
   int type = 0;

   cout << message << endl;
   // While no valid input found
   while(!valid)
   {
      // Get a single number
      getline(cin, input);
      istringstream(input) >> type;
      // Check if valid and act accordingly
      if (!checkParameter(type, 1, 3) || !isNumber(input[0]))
         cout << error << endl;
      // Otherwise, flag the input as ok
      else
         valid = true;
   }

   cout << endl;
   return type;
}

// Find a valid size
int getSize()
{
   string message = "What size pizza would you like? You have three options:\n"
                    "1. Small ($10)\n"
                    "2. Medium ($14)\n"
                    "3. Large($17)\n"
                    "Enter the number preceding your choice.",
          error = "Error: invalid input; a number from 1 to 3 is required.",
          input;
   bool valid = false;
   int size = 0;

   cout << message << endl;
   // While no valid input found
   while(!valid)
   {
      // Get a single number
      getline(cin, input);
      istringstream(input) >> size;
      // Check if valid and act accordingly
      if (!checkParameter(size, 1, 3) || !isNumber(input[0]))
         cout << error << endl;
      // Otherwise, flag the input as ok
      else
         valid = true;
   }

   cout << endl;
   return size;
}

// Find a number of pepperoni toppings
int getTopping_pep()
{
   string message = "How many pepperoni toppings would you like? There is a limit of\n"
                    "four toppings, including cheese toppings. Remeber to leave room\n"
                    "if you want cheese! Toppings are also $2 each.",
          error = "Error: invalid input; a number from 0 to 4 is required.",
          input;
   bool valid = false;
   int peps = 0;

   cout << message << endl;
   // While no valid input found
   while(!valid)
   {
      // Get a single number
      getline(cin, input);
      istringstream(input) >> peps;
      // Check if valid and act accordingly
      if (!checkParameter(peps, 0, 4) || !isNumber(input[0]))
         cout << error << endl;
      // Otherwise, flag the input as ok
      else
         valid = true;
   }

   cout << endl;
   return peps;
}

// Find a number of pepperoni toppings
int getTopping_che(int pep)
{

   bool valid = false;
   int ches = 0, // Cheese hasn't been set yet, so give it zero for now
       limit = 4 - pep;
   string maxChe;
   // Give maxChe a value
   ostringstream cnvrt;
	cnvrt << limit;
	maxChe = cnvrt.str();
   // Create proper message to client
   string message = "How many cheese toppings would you like? You can have a maximum\n"
                    "of " + maxChe + ". Each topping is $2",
          error = "Error: invalid input; a number from 0 to " + maxChe + " is required.",
          input;

   cout << message << endl;
   // While no valid input found
   while(!valid)
   {
      // Get input
      getline(cin, input);
      istringstream(input) >> ches;
      // Check if valid and act accordingly
      if (!checkParameter(ches, 0, limit) || !isNumber(input[0]))
         cout << error << endl;
      // Otherwise, flag the input as ok
      else
         valid = true;
   }

   cout << endl;
   return ches;
}

/*
Checks if a character is a number or not. If it is, return true.
Otherwise, return false.

val: string to parse through
*/
bool isNumber(char val)
{
   // Number
   if (('0' <= val) && (val <= '9'))
      return true;
   // Something else
   else
      return false; // Abort!
}

/*
Checks if an int value is within the specified range (inclusive).

val: value to check
low: has to be higher than this
high: has to be lower than this
*/
bool checkParameter(int val, int low, int high)
{
   if ((low <= val) && (val <= high))
      return true;
   else
      return false;
}

// Prints out the pizza
void printPizza(Pizza pizza)
{
   string message_start = "Let's see now... you're ordering:\n"
                          "A ";
   cout << message_start;
   pizza.outputDescription();
}

// Another loop or break from it
bool keepGoing()
{
   string input,
          message_welcome = "Enter 'yes' to create another pizza, or 'no' to stop",
          message_error = "Error: no valid input. 'yes' to make another pizza or 'no' to stop.";

   cout << message_welcome << endl;
   // Keep going until boolean is returned
   while(true)
   {
      // Get input's first char
      getline(cin, input);
      // Return boolean value and exit
      if (input == "yes")
         return true;
      else if (input == "no")
         return false;
      // Else, print error message;
      else
         cout << message_error << endl;
   }
}

// Pizza methods -----------------------------------------------------------------------
Pizza::Pizza()
{
   // Give default values
   pizza_type = TYPE_UNKNOWN;
   pizza_size = SIZE_UNKNOWN;
   pizza_pepperoni = -1;
   pizza_cheese = -1;
   pizza_price = -1;
}

// Constructor that also applies certain values
Pizza::Pizza(int type, int size, int pep, int che)
{
   // Set to zero as default
   pizza_pepperoni = 0;
   pizza_cheese = 0;

   // Type
   if (!setType(type))
      pizza_type = TYPE_UNKNOWN;
   // Size
   if (!setSize(size))
      pizza_size = SIZE_UNKNOWN;
   // Pepperoni
   if (!setTopping_Pepper(pep))
      pizza_pepperoni = -1;
   // Cheese
   if (!setTopping_Cheese(che))
      pizza_cheese = -1;
   // Calculate price
   computePrice();
}

// Setters ------------------------------
bool Pizza::setType(int type)
{
   // TYPE_UNKNOWN is restricted
   if ((type < 0) && (4 < type))
      return false;
   pizza_type = type;
   return true;
}

bool Pizza::setSize(int size)
{
   // SIZE_UNKNOWN is restricted
   if ((size < 0) && (4 < size))
      return false;
   pizza_size = size;
   return true;
}

bool Pizza::setTopping_Pepper(int pep)
{
   // Cheese assumed to have valid number
   int limit = 4 - pizza_cheese;

   // Allowed to have no toppings
   if ((pep <= 0) && (limit <= pep) && (4 <= pep)) // Also deals with default value of -1
      return false;

   pizza_pepperoni = pep;
   return true;
}

bool Pizza::setTopping_Cheese(int che)
{
   // Pepperoni assumed to have valid number
   int limit = 4 - pizza_pepperoni;

   // Allowed to have no toppings
   if ((che <= 0) && (limit <= che) && (4 <= che)) // Also deals with default value of -1
      return false;

   pizza_cheese = che;
   return true;
}

// Getters ------------------------------
string Pizza::getType()
{
   if (pizza_type == TYPE_DEEP_DISH)
      return "deep dish";
   else if (pizza_type == TYPE_HAND_TOSSED)
      return "hand tossed";
   else if (pizza_type == TYPE_PAN)
      return "pan";
   else
      return "type_unknown";
}

string Pizza::getSize()
{
   if (pizza_size == SIZE_SMALL)
      return "small";
   else if (pizza_size == SIZE_MEDIUM)
      return "medium";
   else if (pizza_size == SIZE_LARGE)
      return "large";
   else
      return "size_unknown";
}

int Pizza::getTopping_Pepper()
{ return pizza_pepperoni; }

int Pizza::getTopping_Cheese()
{ return pizza_cheese; }

int Pizza::getPrice()
{ return pizza_price; }

// Other methods ------------------------------
void Pizza::computePrice()
{
   // Check if the pizza is complete, or valid
   if (pizza_type == TYPE_UNKNOWN || pizza_size == SIZE_UNKNOWN || pizza_cheese == -1 || pizza_pepperoni == -1)
   {
      pizza_price = -1;
      return;
   }

   int price = 0,
       toppings = 0,
       pricePerTopping = 2;

   // Price for pizza size
   if (pizza_size == SIZE_SMALL)
      price += 10;
   else if (pizza_size == SIZE_MEDIUM)
      price += 14;
   else if (pizza_size == SIZE_LARGE)
      price += 17;

   // Price for toppings
   toppings = pizza_cheese + pizza_pepperoni;
   price += toppings * pricePerTopping;

   // Set price
   pizza_price = price;
}

void Pizza::outputDescription()
{
   string type = getType(),
          size = getSize();
   int pep = getTopping_Pepper(),
       che = getTopping_Cheese();
   
   computePrice();
   cout << size + ", " + type + " pizza with " << pep << " pepperoni and " << che << " cheese toppings.\n"
        "That'll be $" << pizza_price << ", please!\n" << endl;

   return;
}

/* PASTE FROM CONSOLE ************************************
Make your own pizza today, digitally! Just enter your desired
pizza type, size, and number of toppings! It'll even be delivered!

What type of pizza would you like? You may any from the list:
1. Deep Dish
2. Hand Tossed
3. Pan
Enter the number preceding the type you desire.
1

What size pizza would you like? You have three options:
1. Small ($10)
2. Medium ($14)
3. Large($17)
Enter the number preceding your choice.
2

How many pepperoni toppings would you like? There is a limit of
four toppings, including cheese toppings. Remeber to leave room
if you want cheese! Toppings are also $2 each.
0

How many cheese toppings would you like? You can have a maximum
of 4. Each topping is $2
2

Let's see now... you're ordering:
A medium, deep dish pizza with 0 pepperoni and 2 cheese toppings.
That'll be $18, please!

Enter 'yes' to create another pizza, or 'no' to stop
yes

What type of pizza would you like? You may any from the list:
1. Deep Dish
2. Hand Tossed
3. Pan
Enter the number preceding the type you desire.
number two
Error: invalid input; a number from 1 to 3 is required.
two
Error: invalid input; a number from 1 to 3 is required.
123
Error: invalid input; a number from 1 to 3 is required.
0
Error: invalid input; a number from 1 to 3 is required.
2

What size pizza would you like? You have three options:
1. Small ($10)
2. Medium ($14)
3. Large($17)
Enter the number preceding your choice.
4
Error: invalid input; a number from 1 to 3 is required.
{}:?"*&^%
Error: invalid input; a number from 1 to 3 is required.
3

How many pepperoni toppings would you like? There is a limit of
four toppings, including cheese toppings. Remeber to leave room
if you want cheese! Toppings are also $2 each.
2

How many cheese toppings would you like? You can have a maximum
of 2. Each topping is $2
3
Error: invalid input; a number from 0 to 2 is required.
4
Error: invalid input; a number from 0 to 2 is required.
1

Let's see now... you're ordering:
A large, hand tossed pizza with 2 pepperoni and 1 cheese toppings.
That'll be $23, please!

Enter 'yes' to create another pizza, or 'no' to stop
yes

What type of pizza would you like? You may any from the list:
1. Deep Dish
2. Hand Tossed
3. Pan
Enter the number preceding the type you desire.
1

What size pizza would you like? You have three options:
1. Small ($10)
2. Medium ($14)
3. Large($17)
Enter the number preceding your choice.
1

How many pepperoni toppings would you like? There is a limit of
four toppings, including cheese toppings. Remeber to leave room
if you want cheese! Toppings are also $2 each.
4

Let's see now... you're ordering:
A small, deep dish pizza with 4 pepperoni and 0 cheese toppings.
That'll be $18, please!

Enter 'yes' to create another pizza, or 'no' to stop
nope
Error: no valid input. 'yes' to make another pizza or 'no' to stop.
no

Thank you for ordering!

Press any key to continue . . .
****************************************** END OF PASTE */
