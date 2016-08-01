#include<iostream>
#include <string>
using namespace std;

// ---------------- the class prototype ---------------------------------
class Galaxy
{
private:
   string name;
   double magnitude;
public:
   // Constructors
   Galaxy();
   Galaxy(string my_name);
   Galaxy(string my_name, double my_mag);

   // mutators and accessors
   bool SetMagnitude(double mag);
   bool SetName(string theName);
   string GetName();
   double GetMagnitude();
};

// ------------------ the main method ---------------------------------
int  main()
{
   // declare the objects
   Galaxy gal_1, gal_2, defaultGalaxy;
      
   // try to set the data
   gal_1.SetName("X");
   gal_1.SetMagnitude(100);
   gal_2.SetName("Stephan's Third");
   gal_2.SetMagnitude(13.2);
   
   // let's see what happened
   cout << "Gal #1 name: " <<  gal_1.GetName() << endl;
   cout << "Gal #1 mag: " <<  gal_1.GetMagnitude() << endl;
   cout << "Gal #2 name: " <<  gal_2.GetName()<< endl;
   cout << "Gal #2 mag: " <<  gal_2.GetMagnitude()<< endl;

   // Alternatively, can try this
   // Galaxy gal_1("M31"), gal_2 = "Andromeda";

   return 0;
}

// ------------ Galaxy member functions definitions ------------
// default constructor
Galaxy::Galaxy()
{
   name = "undefined";
   magnitude = 0.0;   
}

// 1-parameter constructor
Galaxy::Galaxy(string my_name)
{
   if (my_name.length() > 0)
      name = my_name;
   else
      name = "undefined";
   magnitude = 0.0;   
}

// 2-parameter constructor
Galaxy::Galaxy(string my_name, double my_mag)
{
   if (my_name.length() > 1)
      name = my_name;
   else
      name = "undefined";
   if (my_mag >= -3 && my_mag <= 30)
      magnitude = my_mag;
   else
      magnitude = 0.0;   
}

// mutators "set" methods
bool Galaxy::SetMagnitude(double mag)
{
   if (mag < -3 || mag > 30)
     return false;
   // else
   magnitude = mag;
   return true; 
}

bool Galaxy::SetName(string theName)
{
   if (theName.length() < 2)
      return false;
   // else we fall through
   name = theName;
   return true;
}
// accessor "get" methods 
string Galaxy::GetName()
{
   return name;
}
double Galaxy::GetMagnitude()
{
   return magnitude;
}


/* ------------------ Paste of Run from Above Program ----------

Gal #1 name: undefined
Gal #1 mag: 0
Gal #2 name: Stephan's Third
Gal #2 mag: 13.2
Press any key to continue . . .

---------------------------------------------------------------- */
