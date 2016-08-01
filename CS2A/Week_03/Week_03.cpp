#include <iostream>
#include <string>

using namespace std;

void prompt(),
     ending(int result);
int inputFirst(),
    inputSecond(),
    numbersBetween(int start, int end);

int main()
{
   int startNum,
       endNum,
       result;

   prompt();
   startNum = inputFirst();
   endNum = inputSecond();
   result = numbersBetween(startNum, endNum);
   ending(result);

   cout << endl;

   return 0;
}

void prompt()
{
   string message = "Insert two numbers and the sum of all numbers between, including\n"
                    "the ones entered, will be shown.\n";
   cout << message << endl;
}

int inputFirst()
{
   int input;
   cout << "First number: ";
   cin >> input;
   return input;
}

int inputSecond()
{
   int input;
   cout << "Second number: ";
   cin >> input;
   return input;
}

int numbersBetween(int first, int second)
{
   int start,
       end = second,
       addition = first,
       result = first;

   for (start = first; start < end; start++)
   {
      addition++;
      result += addition;
   }

   return result;
}

void ending(int result)
{
   cout << "I got: " << result << endl;
}