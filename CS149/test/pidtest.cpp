#include <iostream>
#include <process.h>

// Windows C++ friendly getpid()

int main()
{
    printf("%i\n", getpid());
	system("pause");
    return 0;
}

