#include <windows.h>
#include <stdio.h>

void main()
{
    SYSTEMTIME lt;    
    GetLocalTime(&lt);    
    printf("yyyymmddhhmmss:  %04i%02i%02i%02i%02i%02i\n", lt.wYear, lt.wMonth, lt.wDay, lt.wHour, lt.wMinute, lt.wSecond);
}
