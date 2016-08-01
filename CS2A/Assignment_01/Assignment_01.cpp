/*********************************************************

Subject:
Assignment 1: using console input/output to calculate calories burned

Author:
Hansen Wu

Changelog:
2013.07.02
   project created
   program written
2013.07.03
   console run added

*********************************************************/

#include <iostream>
#include <string>
#include <sstream>

using namespace std;

int main()
{
   int user_mets,
       user_minutes;
   double user_lbs,
          user_kgs,
          equation_constant = 0.01750,
          lbs_per_kg = 2.20,
          cal_per_min,
          result;
   
   // Welcome and weight getting
   cout << "Hello, I'm here to calculate the number of calories burned!\n"
           "First off, what is your weight, in pounds? Numbers only, please!" << endl;
   cin >> user_lbs; // Get weight

   // METs
   cout << "\nOk, and how many METs, or metabloic equivalents, is the activity? Whole numbers only!" << endl;
   cin >> user_mets; // Get METs

   // Minutes
   cout << "\nAnd how many minutes were spent doing this activity? Whole numbers only!" << endl;
   cin >> user_minutes; // Get minutes

   // Calculate calories
   cout << "\nLet's see, that's ... ";
   // Convert weight to kgs
   user_kgs = user_lbs / lbs_per_kg;
   // Calculate calories burned
   cal_per_min = equation_constant * user_mets * user_kgs;
   result = cal_per_min * user_minutes;

   // Print result
   cout << result << " calories burned.\n\n" << endl;

   return 0;
}

/* PASTE FROM CONSOLE ************************************
Hello, I'm here to calculate the number of calories burned!
First off, what is your weight, in pounds? Numbers only, please!
110

Ok, and how many METs, or metabloic equivalents, is the activity? Whole numbers
only!
13

And how many minutes were spent doing this activity? Whole numbers only!
45

Let's see, that's ... 511.875 calories burned.


Press any key to continue . . .
****************************************** END OF PASTE */
