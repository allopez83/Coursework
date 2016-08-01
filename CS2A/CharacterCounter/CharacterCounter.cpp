#include <string>
#include <iostream>
using namespace std;

// class Frequency prototype -----------------------
class Frequency
{
private:
   static const int MAX_SIZE = 100000;
   int count[MAX_SIZE];
   int size;
public:  
   Frequency(int size = 26);    
   int Get(int index);
   void Increment(int index);  
   void Decrement(int index);
};
// end of class Frequency prototype --------------

// class CharacterCounterprototype -----------------------
class CharacterCounter
{
private:
   Frequency letters;
   string user_string;
   
public:
   CharacterCounter(string str);  
   long GetCount(char let);
private:
   void CountOccurrences();

};
// end of CharacterCounter method definitions  --------------

int main()
{
   string user_phrase;

   cout << "Enter a phrase or sentence: " << endl;
   getline(cin, user_phrase);
   
   // create a CharacterCounter object for this phrase
   CharacterCounter freq(user_phrase);
   
   // display whole table
   for (char let = 'A'; let <= 'Z'; let++)
   {
      // every 5 items, generate a newline
      if ( (let - 'A') % 5 == 0)
         cout << endl;

      cout << let << ": " << freq.GetCount(let) << "     ";
   }   
}

// beginning of Frequency method definitions -------------
Frequency::Frequency(int size)
{
   if (size <= 0 || size > MAX_SIZE)
      size = MAX_SIZE;
   this->size = size;
   for (int k=0; k < size; k++)
      count[k] = 0;
}
int Frequency::Get(int index)
{
   if (index >= 0 && index < size)
      return count[index];
   else
      return -1;
}
void Frequency::Increment(int index)
{
   if (index >= 0 && index < size)
      count[index]++;
} 

void Frequency::Decrement(int index)
{
   if (index >= 0 && index < size)
      if (count[index] > 0)
         count[index]--;
}
// end of Frequency method definitions  --------------

// beginning of CharacterCounter  method definitions -------------
CharacterCounter::CharacterCounter(string str)
{   
   // if string is not good, use empty string
   if (str.length() >= 1 )
      user_string = str;
   else
      user_string = "";
   CountOccurrences();
}

void CharacterCounter::CountOccurrences()
{
   char let;
   int k;
   
   // letters[] automatically initialized to all 0s
   // scan the string and increment as we go
   for (k = 0; k < user_string.length(); k++)
   {
      let = toupper(user_string[k]);
      letters.Increment( let - 'A' );  // note cute conversion
   }
}

long CharacterCounter::GetCount(char let)
{
   char up_let;
   up_let = toupper(let);
   return letters.Get(up_let - 'A');
}
// end of CharacterCounter method definitions  --------------