#include <iostream>
#include <string.h>

using namespace std;

int main ()
{
    cout << "Start\n";

    char mem_start [16] = {0};

    char a = 'A';
    // string b = "abc";
    // // cout << b;
    // cout << b[1];

    mem_start[0] = a;
    mem_start[1] = 'b';

    // cout << &mem_start;

    printf("%p\n", &mem_start[0]);
    printf("%p\n", &mem_start[16]);

    cout << "End\n";
    return 0;
}