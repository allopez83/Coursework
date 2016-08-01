#include<iostream>
#include <string>
using namespace std;

// ---------------- the class prototype ---------------------------------
class Doctor
{
private:
   string name,
          phone;
public:
   // Constructors
   Doctor();
   Doctor(string name, string phone);

   // Getter
   string getName();
   string getPhoneNumber();
};

// ------------------ the main method ---------------------------------
int main() {
      cout << "Calling on-call doctor..." << endl;
      Doctor johnSmith("John Smith", "408 444-4444");
      cout << "Doctor's name: " << johnSmith.getName() << endl;
      cout << "Doctor's phone number: " << johnSmith.getPhoneNumber() << endl;
 
      return 0;
}

// ------------ Doctor member functions definitions ------------
Doctor::Doctor()
{
   name = "no_name";
   phone = "no_number";
}
Doctor::Doctor(string input_name, string input_phone)
{
   name = input_name;
   phone = input_phone;
}

string Doctor::getName()
{
   return name;
}
string Doctor::getPhoneNumber()
{
   return phone;
}