/*********************************************************

Subject:
Assignment 5: Practice debugging code

Author:
Hansen Wu

Change log:
2013.08.01
   Project created
   Code pasted from instructions page

*********************************************************/



#include <iostream>
#include <stdlib.h>
#include <time.h>
using namespace std;

const int NUM_PRODUCTS = 32768;
const int LOW_VALUE = 0;
const int FAILURE = -1;

int lSearch(int arr [], int target);
int bSearch(int arr [], int target);
void showResults(int result, char str [], int target);

int main()
{
   int arr1[NUM_PRODUCTS], arr2[NUM_PRODUCTS];
   int randInt, result, target, k;

   srand((unsigned) time(NULL)); 

   for (k = 0; k < NUM_PRODUCTS; k++)
   {
      randInt = rand() % (NUM_PRODUCTS + 1) + LOW_VALUE;
      arr1[k] = randInt;
      arr2[k] = k;
   }

   cout << "Enter product id: ";          
   cin >> target;

   result = lSearch(arr1, target);
   showResults(result, "L Search", target);

   cout << "Enter product id: ";         
   cin >> target;

   result = bSearch(arr2, target);
   showResults(result, "B Search", target);

   cout << endl;
   return 0;
}


int lSearch(int arr [], int target)
{
   int position = 0;
   int result = -1;

   while (result == -1 && position < NUM_PRODUCTS)
   {
      if (arr[position] == target)
      {
         result = position;   
      }
      position++;
   }
   return result;
}

int bSearch(int arr [], int target)
{
   int middlePosition;
   int middleValue;
   int result = -1;
   int low = 0;
   int high = NUM_PRODUCTS - 1;

   while (result == -1 && low <= high)
   {
      middlePosition = (low + high) / 2;
      middleValue = arr[middlePosition];
      if (target == middleValue)
      {
         result = middlePosition;
      }
      else if (target < middleValue)
         high = middlePosition - 1;
      else
         low = middlePosition + 1;
   }
   return result;
}

void showResults(int result, char str [], int target)
{
   cout << endl << endl << str << endl << "-----------------" << endl;
   if (result != FAILURE)
      cout << "Found product id [" << target << "] in array position [" << result << "]" << endl << endl;
   else
      cout << "TARGET [" << target << "] not found." << endl << endl << endl;
}
