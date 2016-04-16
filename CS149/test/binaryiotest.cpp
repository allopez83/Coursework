#include <iostream>
#include <string.h>
#include <fstream>

using namespace std;

int main ()
{
    cout << "Start\n";

    // String tests
    // string b = "abc";
    // cout << b;
    // cout << b[1];

    char mem_start [16] = {0};

    char a = 'A';

    mem_start[0] = a;
    mem_start[1] = 'b';
    mem_start[2] = 'c';
    mem_start[3] = 'z'; // pointer math test

    ofstream myFile ("data.bin", ios::out | ios::binary);
    myFile.write (mem_start, 16);

    cout << "End\n";
    return 0;
}