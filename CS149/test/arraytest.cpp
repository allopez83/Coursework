#include <iostream>
#include <string.h>

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


    printf("entire memory: %s\n", mem_start);

    char* pointer = mem_start;

    printf("Pointer: %c\n", *pointer);
    printf("Increment 3: %c\n", *(pointer+3));
    // cout << &mem_start;

    printf("start addr: %p\n", &mem_start[0]);
    printf("end addr: %p\n", &mem_start[16]);

    cout << "End\n";
    return 0;
}