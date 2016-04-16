#include <iostream>
#include <string.h>

using namespace std;

int main ()
{
    cout << "Start\n";

    int byte_boundry = 2;
    char mem_start [17] = {"1234567890123456"};

    for (int i = 0; i < 16; i++) {
        if (!(i % byte_boundry))
            printf(" ");
        if (!(i % (4*byte_boundry)))
            printf("\n");
        printf("%c", mem_start[i]);
    }

    cout << "End\n";
    return 0;
}