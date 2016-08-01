/*

Experimentation code for understanding C increment
Author: Hansen Wu

Changelog:
2013.07.01: file created, copied program from modules to try out
2013.07.02: added in fake ascii code assignment
            added fake math assignment
            tested console input

*/

#include <iostream>
#include <string>

using namespace std;

int main()
{
   /* Code from modules */
   int x;
   double y;

   x = -234;
   y = 3.1345;

   cout << "X is " << x << " and Y is " << y << "\n" << endl;

   /* Fake ASCII assignment */
   cout << "\nFake ASCII assignment" << endl;
   int ascii_L, ascii_l, ascii_F, ascii_f;

   ascii_L = 'L';
   ascii_l = 'l';
   ascii_F = 'F';
   ascii_f = 'f';

   cout << "ASCII code of..."
      << "\nL: " << ascii_L
      << "\nl: " << ascii_l
      << "\nF: " << ascii_F
      << "\nf: " << ascii_f
      << "\n" << endl;

   /* Fake math assignment */
   cout << "\nFake math assignment" << endl;
   int val_x = -3, val_y = 10, result;

   cout << "x: " << val_x << "\ny: " << val_y << "\n" << endl;

   // x^3 + y^2
   result = (val_x*val_x*val_x) + (val_y*val_y);
   cout << "Result of x^3 + y^2: " << result << endl;

   // (x-y)/(x+y)
   result = (val_x - val_y) / (val_x + val_y);
   cout << "Result of (x-y)/(x+y): " << result << endl;

   /* Console input testing */
   cout << "\nConsole input testing" << endl;
   string userResponse;
   cout << "What is your name?" << endl;
   getline(cin, userResponse); // get response
   cout << "Hello, " + userResponse + ", have a nice day!"
           " PS: the 2nd character is " + userResponse[1] + " \n\n" << endl;

   return 0;
}
