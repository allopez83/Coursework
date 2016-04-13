#include <iostream>
#include <string.h>

using namespace std;

int main ()
{
    cout << "Start\n";

    char mem_start [16] = {0};

    char a = 'A';
    string b = "abc";
    // cout << b;
    cout << b[1];

    mem_start[0] = a;
    mem_start[1] = 'a';

    cout << mem_start;

    cout << "End\n";
    return 0;
}