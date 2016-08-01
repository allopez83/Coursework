#include <iostream>

using namespace std;

void Demo( int intVal, double doubleVal );

int main()
{
   int result = 6^2;
   cout << "6^2: " << result << endl;
}



void Demo( int intVal, double doubleVal )
{
   intVal = intVal * 2;
   doubleVal = double(intVal) + 3.5;
}
