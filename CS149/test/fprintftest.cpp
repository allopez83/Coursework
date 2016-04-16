/* fprintf example */
#include <stdio.h>        // for timestamp
#include <process.h>      // for getpid()
#include <windows.h>      // for timestamp
#include "psapi.h"        // name of methods

int main () {

  char *logName = "VMMS.LOG";
  int rc = 0;
  char *dest_ptr = logName;
  char c = 'z';
  int size = 12;

  SYSTEMTIME lt;
  GetLocalTime(&lt);
  // printf("yyyymmddhhmmss:  %04i%02i%02i%02i%02i%02i\n", lt.wYear, lt.wMonth, lt.wDay, lt.wHour, lt.wMinute, lt.wSecond);
  char exeName[MAX_PATH];
  GetModuleBaseName(GetCurrentProcess(), NULL, exeName, _MAX_FNAME);

  FILE *logFile = fopen(logName, "a");
  fprintf(logFile, "%04i%02i%02i%02i%02i%02i %s %i %s %i %i %i %i\n", lt.wYear, lt.wMonth, lt.wDay, lt.wHour, lt.wMinute, lt.wSecond, exeName, getpid(), "vmms_memset", rc, dest_ptr, c, size );

    return 0;
}
