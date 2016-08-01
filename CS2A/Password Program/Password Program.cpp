#include <iostream>
#include <string>
using namespace std;
 
int main()
{ 
   string password;
   bool validated;
   char letter;
   int length;
 
   // Get password candidate from console, until it passes our tests
   validated = false;
   while (!validated)
   {
      // get the password candidate from user
      cout << "Enter a password, please ('q' to quit): ";
      getline(cin, password);
      
      length = password.length();
      // test for quit first
      if ( length == 1 
         && ( password[0] == 'q'  || password[0] == 'Q') )
      {
         cout << "No password  defined.\n";
         break;  // from loop
      }
      
      // test for reasonable length
      if (length < 6 || length > 15)
      {
         cout << "Password must be between 6 and 15 characters.\n";
         continue;
      }
      
      validated = true;   // assume innocent entering loop
      // allow only letters and numbers
      for (int k = 0; k < length; k++)
      {
         letter = password[k];   // store in local variable so we can reuse
         if (letter >= 'a' && letter <= 'z')
            continue; //  the for loop, we have a good lower case letter
         else if (letter >= 'A' && letter <= 'Z')
            continue; //  the for loop, we have a good upper case letter
         else if (letter >= '0' && letter <= '9')
            continue; //  the for loop, we have a good numeral
         else
         {
            //  this letter is not one of the three legal types
            cout << "Use only A-Z, a-z or 0-9, please.";
            validated = false;
            break;   // from the for loop leaving validated as false
         }
      }
      
      // if the above loop yielded an error, we try again
      if (!validated)
         continue;
      
      // if we made it here, it is the proper length and has legal chars
      // but is the first character a letter?
      letter = password[0];
      if (letter >= '0' && letter <= '9')
      {
         cout << "First character must be a letter (non-numeric).\n";
         validated = false;
         continue;
      }
      else
      {
         // they passed the final test
         cout << "Your password " + password + " has been accepted.\n";
         break;   // this is not really needed but is safe
      }
   }
   
   cout << "\nGood bye.\n\n";
}
    

/* ----------- Output ----------------

Enter a password, please ('q' to quit): hi mom
Use only A-Z, a-z or 0-9, please.
Enter a password, please ('q' to quit): sdfkj(*&
Use only A-Z, a-z or 0-9, please.
Enter a password, please ('q' to quit): 8thhing
First character must be a letter (non-numeric).
Enter a password, please ('q' to quit): sdf
Password must be between 6 and 15 characters.
Enter a password, please ('q' to quit): adfa sdfjasdlfkjasdlfkjasdf
Password must be between 6 and 15 characters.
Enter a password, please ('q' to quit): sdf sdf
Use only A-Z, a-z or 0-9, please.
Enter a password, please ('q' to quit): asdfASDF
Your password asdfASDF has been accepted.

Good bye.

------------------------------------- */