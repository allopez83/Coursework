#include <string>
#include <iostream>
using namespace std;

// method prototypes
bool FloatLargestToTop(string data[], int array_size);
void PrintArray(string title, string data[], int array_size);
void ArraySort(string array[], int array_size);
void swap(string &a, string &b);

int main ()
{
   string my_array[] = {"you", "me", "them", "us", "him", 
         "her", "he", "she"};

   // compute the size of the array
   short array_size = sizeof(my_array)/sizeof(my_array[0]);

   PrintArray("Before: ", my_array, array_size);
   ArraySort(my_array, array_size);
   PrintArray("After: ", my_array, array_size);
}

// returns true if a modification was made to the array
bool FloatLargestToTop(string data[], int top)
{
   bool changed = false;
   
   for (int k = 0; k < top; k++)
      if (data[k].compare(data[k+1]) > 0)
      {
         swap(data[k], data[k+1]);
         changed = true;
      }
   return changed;
}

// print out the array with the string as a title for the message box
void PrintArray(string title, string data[], int array_size)
{
   cout << title << "  ";
   
   for (int k = 0; k < array_size; k++)
   {
      cout << data[k] << "   ";
      // every fifth string, print newline
      if (k%5 == 4)
         cout << endl;
   }
   cout << endl;
   
}

void ArraySort(string array[], int array_size)
{
   int ever_shrinking_top;

    for (   ever_shrinking_top = array_size - 1;
            ever_shrinking_top > 0; 
            ever_shrinking_top--
        )
       if ( !FloatLargestToTop(array, ever_shrinking_top) )
          return;
}

void swap(string &a, string &b)
{
   string temp;

   temp = a;
   a = b;
   b = temp;
}