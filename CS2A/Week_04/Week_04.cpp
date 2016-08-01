#include <array>
#include <iostream>

using namespace std;

class A {
private:
   class B { };
};

int main()
{
   int someArray[10],
       location;
   someArray[0] = 5;
   
   int arraySize = sizeof(someArray)/sizeof(someArray[0]);
   cout << "Array has size: " << arraySize << endl;

   cin >> location;
   cout << someArray[location] << "\n";

   return 0;
}