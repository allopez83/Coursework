/*********************************************************

Subject:
Assignment 3: Create a data type and main() that uses it

Author:
Hansen Wu

Change log:
2013.07.27
   Created Project
   Added Pizza and Order class
2013.07.28
   Working answer created
   Minor debugging
   Paste from console added
   Program improved
   New paste from console added

*********************************************************/

#include <iostream>
#include <string>

using namespace std;

class Pizza
{
public:
   enum pizzaType {DeepDish = 1, HandTossed, Pan };
   enum pizzaSize { Small = 1, Medium, Large};
   enum pizzaToppings { None = 0, Cheese, Pepperoni, Both };

   Pizza();
   bool setType(int selectedType);
   bool setSize(int selectedSize);
   bool setToppings(int selectedToppings);

   pizzaType getType();
   pizzaSize getSize();
   pizzaToppings getToppings();

   void outputDescription();
   double  computePrice();

private:
   pizzaType type;
   pizzaSize size;
   pizzaToppings toppings;
   string typeDescription;
   string sizeDescription;
   string toppingsDescription;
};

class Order
{
private:
   // Client allowed to have max of 20 orders
   static int const MAX_ORDERS = 20;
   Pizza orderArray[MAX_ORDERS];
   int orderQuantity;

   // Helper methods called by orderPizza() that ask for and set pizza specs
   void askUser_Type(Pizza &pizza);
   void askUser_Size(Pizza &pizza);
   void askUser_Topping(Pizza &pizza);

public:
   // Constructors
   Order();
   Order(int orders);
   // Getters and Setters
   bool setOrderQuantity(int input);
   int getOrderQuantity();
   // Other methods
   void client_orderQuantity();
   void orderPizza(); // Prompts user to specify all pizzas desired
   void showReceipt(); // Prints out a receipt of all the pizzas ordered

};

Pizza::Pizza()
{
   //Setting defaults values for the type, size, and toppings
   type = Pizza::DeepDish; 
   size = Pizza::Small;
   toppings = Pizza::None;
}

bool Pizza::setType(int selectedType)
{
   switch (selectedType) {
   case 1:
      type = Pizza::DeepDish;
      typeDescription = "Deep Dish";
      break;
   case 2:
      type = Pizza::HandTossed;
      typeDescription = "Hand Tossed";
      break;
   case 3:
      type = Pizza::Pan;
      typeDescription = "Pan";
      break;
   default:
      return false;
   }
   return true;
}

bool Pizza::setSize(int selectedSize)
{
   switch (selectedSize) {
   case 1:
      size = Pizza::Small;
      sizeDescription = "Small";
      break;
   case 2:
      size = Pizza::Medium;
      sizeDescription = "Medium";
      break;
   case 3:
      size = Pizza::Large;
      sizeDescription = "Large";
      break;
   default:
      return false;
   }
   return true;
}

bool Pizza::setToppings(int selectedToppings)
{
   switch (selectedToppings) {
   case 0:
      toppings = Pizza::None;
      toppingsDescription = "No toppings";
      break;
   case 1:
      toppings = Pizza::Cheese;
      toppingsDescription = "Cheese";
      break;
   case 2:
      toppings = Pizza::Pepperoni;
      toppingsDescription = "Pepperoni";
      break;
   case 3:
      toppings = Pizza::Both;
      toppingsDescription = "Both cheese and pepperoni";
      break;
   default:
      return false;
   }
   return true;
}

Pizza::pizzaType Pizza::getType()
{
   return type;
}

Pizza::pizzaSize Pizza::getSize()
{
   return size;
}

Pizza::pizzaToppings Pizza::getToppings()
{
   return toppings;
}

void Pizza::outputDescription()
{
   cout << "   Type: " << typeDescription << "\n"
           "   Size: " << sizeDescription << "\n"
           "   Toppings: " << toppingsDescription << "\n"
           "   Price: $" << computePrice() <<endl;
}

double Pizza::computePrice()
{
   /* Compute price using the following formula:
   Small pizza = $10 + $2 per topping
   Medium pizza = $14 + $2 per topping
   Large pizza = $17 + $2 per topping
   */
   double price = 0;
   switch (size){
   case Pizza::Small:
      price += 10;
      break;
   case Pizza::Medium:
      price += 14;
      break;
   case Pizza::Large:
      price += 17;
      break;
   }
   switch (toppings){
   case Pizza::Cheese:
   case Pizza::Pepperoni:
      price += 2;
      break;
   case Pizza::Both:
      price += 2*2;
      break;
   }
   return price;

}

// Default constructor
Order::Order()
{
   orderQuantity = 0;
}

// Overloaded constructor
Order::Order(int orders)
{
   if(!setOrderQuantity(orders))
      orderQuantity = 0;
}

// Specifies a number of pizzas to order
bool Order::setOrderQuantity(int input)
{
   // Only accepts values from 1 to 20
   if ((input < 1) || (MAX_ORDERS < input))
      return false;
   orderQuantity = input;
   return true;
}

int Order::getOrderQuantity()
{
   return orderQuantity;
}

/*
Prompts for and creates pizzas according to user input. This is
done with the "askUser" series of helper methods. The point of
this is because the orderArray[] is private, and using a method
for this makes the task modular and easy to handle in main().
*/
void Order::orderPizza()
{
   // Needs at least one pizza being ordered
   if (orderQuantity < 1)
      return;

   Pizza thePizza;
   int current;

   // Instructions message
   cout << "\nPlease place your order by selecting from the following menus.\n"
           "Please enter item number to select.\n";

   // Loop while there are still orders to be completed
   for (current = 0; current < orderQuantity; current++)
   {
      cout << "__________________________________________\n\n";
      // Create Pizza
      askUser_Type(thePizza);
      askUser_Size(thePizza);
      askUser_Topping(thePizza);
      // Put into orderArray
      orderArray[current] = thePizza;
      cout << "\nPizza #" << (current + 1) << " completed!" << endl;
   }
}

// Prints the order, along with a receipt detailing each pizza and the total cost
void Order::showReceipt()
{
   // Needs at least one pizza that was ordered
   if (orderQuantity < 1)
      return;

   int current;
   double price,
          totalPrice = 0.0;

   // Beginning message
	cout << "\n________________________________________________________________________________\n\n"
           "You ordered " << orderQuantity << " pizzas. Here is your order:\n";

   // Loop through entire orderArray, output pizza's description
   for (current = 0; current < orderQuantity; current++)
   {
      // Show the pizzas
      cout << "\nPizza #" << (current + 1) << endl;
      orderArray[current].outputDescription();
   }

   // Loop through orderArray, this time for receipt
   cout << "\n---- Receipt ----" << endl;
   for (current = 0; current < orderQuantity; current++)
   {
      // Get the price
      price = orderArray[current].computePrice();
      // Print price
      cout << "Pizza #" << (current + 1) << ":  $" << price << endl;
      totalPrice += price; // Add to total
   }
   cout << "\nTotal: $" << totalPrice <<  endl;
}

// Prompts client for and gives pizza a valid type
void Order::askUser_Type(Pizza &pizza)
{
   int type;
   cout << "Select pizza type;\nDeep dish (1)\nHand tossed (2)\nPan (3)\nEnter selection: ";
   cin >> type;
   while (!pizza.setType(type))
   {
      cout << "Please enter a valid selection for pizza type; Deep dish (1), Hand tossed (2), or Pan (3): ";
      cin >> type;
   }
}

// Prompts client for and gives pizza a valid size
void Order::askUser_Size(Pizza &pizza)
{
   int size;
   cout << "____________________\n\n"
           "Select pizza size;\nSmall (1)\nMedium (2)\nLarge (3)\nEnter selection: ";
   cin >> size;
   while (!pizza.setSize(size))
   {
      cout << "Please enter a valid selection for pizza size; Small (1), Medium (2), or Large (3):";
      cin >> size;
   }
}

// Prompts client for and gives pizza a valid topping
void Order::askUser_Topping(Pizza &pizza)
{
   int toppings;
   cout << "____________________\n\n"
           "Select pizza toppings;\nNone (0)\nCheese (1)\nPepperoni (2)\nBoth Cheese and Pepperoni (3)\nEnter selection: ";
   cin >> toppings;
   while (!pizza.setToppings(toppings))
   {
      cout << "Please enter a valid selection for pizza toppings; None (0), Cheese (1), Pepperoni (3), Both Cheese and Pepperoni (4):";
      cin >> toppings;
   }
}

int main()
{
   Order theOrder;
   int orderQuantity;

   // Welcome message
   cout << "****************************************************\n"
           "Welcome to City Pizza where the best pizza is made!\n"
           "****************************************************\n\n"
           "How many pizzas would you like to order? You may choose up to 20.\n"
           "Enter quantity: ";
   // Get number of orders; can be a method, but other than cleaner main(), it is not a pressing issue
   cin >> orderQuantity;
   while (!theOrder.setOrderQuantity(orderQuantity))
   {
      cout << "You can only set a quantity from 1 to 20. Enter a new quantity: ";
      cin >> orderQuantity;
   }

   // Use an order method because pizza array is private, and main is cleaner.
   theOrder.orderPizza();
   // Print the result
   theOrder.showReceipt();

   // Another happy customer!
   cout << "\nThank you for choosing City Pizza :) Have a great day! \n\n";

   return 0;
}

/* PASTE FROM CONSOLE ************************************
****************************************************
Welcome to City Pizza where the best pizza is made!
****************************************************

How many pizzas would you like to order? You may choose up to 20.
Enter quantity: 0
You can only set a quantity from 1 to 20. Enter a new quantity: 21
You can only set a quantity from 1 to 20. Enter a new quantity: 4

Please place your order by selecting from the following menus.
Please enter item number to select.
__________________________________________

Select pizza type;
Deep dish (1)
Hand tossed (2)
Pan (3)
Enter selection: 1
____________________

Select pizza size;
Small (1)
Medium (2)
Large (3)
Enter selection: 1
____________________

Select pizza toppings;
None (0)
Cheese (1)
Pepperoni (2)
Both Cheese and Pepperoni (3)
Enter selection: 1

Pizza #1 completed!
__________________________________________

Select pizza type;
Deep dish (1)
Hand tossed (2)
Pan (3)
Enter selection: 2
____________________

Select pizza size;
Small (1)
Medium (2)
Large (3)
Enter selection: 2
____________________

Select pizza toppings;
None (0)
Cheese (1)
Pepperoni (2)
Both Cheese and Pepperoni (3)
Enter selection: 2

Pizza #2 completed!
__________________________________________

Select pizza type;
Deep dish (1)
Hand tossed (2)
Pan (3)
Enter selection: 3
____________________

Select pizza size;
Small (1)
Medium (2)
Large (3)
Enter selection: 3
____________________

Select pizza toppings;
None (0)
Cheese (1)
Pepperoni (2)
Both Cheese and Pepperoni (3)
Enter selection: 3

Pizza #3 completed!
__________________________________________

Select pizza type;
Deep dish (1)
Hand tossed (2)
Pan (3)
Enter selection: 1
____________________

Select pizza size;
Small (1)
Medium (2)
Large (3)
Enter selection: 3
____________________

Select pizza toppings;
None (0)
Cheese (1)
Pepperoni (2)
Both Cheese and Pepperoni (3)
Enter selection: 1

Pizza #4 completed!

________________________________________________________________________________


You ordered 4 pizzas. Here is your order:

Pizza #1
   Type: Deep Dish
   Size: Small
   Toppings: Cheese
   Price: $12

Pizza #2
   Type: Hand Tossed
   Size: Medium
   Toppings: Pepperoni
   Price: $16

Pizza #3
   Type: Pan
   Size: Large
   Toppings: Both cheese and pepperoni
   Price: $21

Pizza #4
   Type: Deep Dish
   Size: Large
   Toppings: Cheese
   Price: $19

---- Receipt ----
Pizza #1:  $12
Pizza #2:  $16
Pizza #3:  $21
Pizza #4:  $19

Total: $68

Thank you for choosing City Pizza :) Have a great day!

Press any key to continue . . .
****************************************** END OF PASTE */
