#include <iostream>
#include "windows.h"
#include "psapi.h"        // name of methods

// Windows C++ friendly getpid()

int main()
{
    CHAR nameBuffer[MAX_PATH];
    GetModuleBaseName(GetCurrentProcess(), NULL, nameBuffer, _MAX_FNAME);
    printf("%s\n", nameBuffer);
    system("pause");
    return 0;
}
