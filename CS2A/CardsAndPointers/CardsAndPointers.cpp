#include <string>
#include <iostream>
#include <sstream>
#include <cctype>

using namespace std;

enum Suit { clubs, diamonds, hearts, spades };


// class Card prototype -----------------------
class Card
{
private:
   char value;
   Suit suit;
public:
   Card(char value = 'A', Suit suit = spades);
   string ToString();
   bool Set(char value = 'A', Suit suit = spades);
   char GetVal();
   Suit GetSuit();
};  // end of class Card prototype --------------

int main()
{
   Card *card1, *card2, *card3;
   card1 = new Card;
   card2 = new Card('5');
   card3 = new Card('9', hearts);
   
   Card *card4 = new Card('j', clubs), *card5 = new Card('1', diamonds);

   if ( ! card1->Set(2, clubs) )
      cout << "incorrect value (2, clubs) passed to card::Set()\n\n";
   if ( ! card1->Set('2', clubs) )
      cout << "incorrect value ('2', clubs) passed to card::Set()\n\n";

   cout << card1->ToString() << endl << card2->ToString() << endl << 
      card3->ToString() << endl << card4->ToString() << endl 
      << card5->ToString() << endl<< endl;

   *card1 = *card4; 
   cout << "after assigning *card4 to *card1:\n";
   cout << card1->ToString() << endl << card4->ToString() <<  endl << endl;

   card1 = card4; 
   cout << "after assigning card4 to card1:\n";
   cout << card1->ToString() << endl << card4->ToString() <<  endl << endl;

   delete card1;
   delete card2;
   delete card3;
   // delete card4;  if you don't omit this you'll get a run-time error
   delete card5;

}

// beginning of Card method definitions -------------

// constructor
Card::Card(char value, Suit suit)
{
   // if not valid, set to Ace of Spades
   if ( !Set(value, suit) )
      Set('A', spades);
}

// stringizer
string Card::ToString()
{
   string ret_val;
   char str_val[2];

   // convert char to a CString
   str_val[0] = value;
   str_val[1] = '\0';

   // convert from CString to s-c string
   ret_val = string(str_val);

   if (suit == spades)
      ret_val += " of Spades";
   else if (suit == hearts)
      ret_val += " of Hearts";
   else if (suit == diamonds)
      ret_val += " of Diamonds";
   else if (suit == clubs)
      ret_val += " of Clubs";

   return ret_val;
}

// mutator
bool Card::Set(char value, Suit suit)
{
   char up_val;

   // convert to uppercase to simplify
   up_val = toupper((int)value);

   // check for validity
   if (
      up_val == 'A' || up_val == 'K'
      || up_val == 'Q' || up_val == 'J'
      || up_val == 'T'
      || (up_val >= '2' && up_val <= '9')
      )
   {
      this->suit = suit;
      this->value = up_val;
      return true;
   }
   else
      return false;
}

// accessors
char Card::GetVal()
{
   return value;
}
Suit Card::GetSuit()
{
   return suit;
}
  // end of Card method definitions  --------------