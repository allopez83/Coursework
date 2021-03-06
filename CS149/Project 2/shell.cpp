#include <windows.h>
#include <tchar.h> 
#include <stdio.h>
#include <strsafe.h>
#include <string.h>
#pragma comment(lib, "User32.lib")

void DisplayErrorBox(LPTSTR lpszFunction);

int main(int argc, TCHAR *argv[])
{
   WIN32_FIND_DATA ffd;
   LARGE_INTEGER filesize;
   char szDir[MAX_PATH];
   HANDLE hFind = INVALID_HANDLE_VALUE;
   DWORD dwError=0;
   
   // If the directory is not specified as a command-line argument,
   // print usage.

   if (!((argc == 4) || (argc == 5)))
   {
      printf("# of argc = %d\n",argc);
      printf("\nUsage: %s <command and options>\n", argv[0]);
      printf("Command 1: dir <path> <option>\n");
      printf("Command 2: compare <file1> <file2> [optional masking character]\n");
      return (-1);
   }

   if (stricmp(argv[1], "dir") == 0)
   {
     // Check that the input path plus options is not longer than MAX_PATH.
     // Three characters are for the "\*" plus NULL appended below.

     if (strlen(argv[2]) > (MAX_PATH - 3))
     {
        printf("\nDirectory path is too long.\n");
        return (-1);
     }

     printf("\nDirectory of %s\n\n", argv[2]);

     // Prepare string for use with FindFile functions.  First, copy the
     // string to a buffer, then append '\*' to the directory name.

     strcpy(szDir, argv[2]);
     strcat(szDir, "\\*");

     // Find the first file in the directory.

     hFind = FindFirstFile(szDir, &ffd);
  
     if (hFind == INVALID_HANDLE_VALUE) 
     {
       DisplayErrorBox(TEXT("FindFirstFile"));
       return dwError;
     } 
   
     // List all the files in the directory with some info about them.

     do
     {
        if (ffd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)
        {
          printf("  %s     \t\t<DIR>\n", ffd.cFileName);
        }
        else
        {
          if (!(ffd.dwFileAttributes & FILE_ATTRIBUTE_HIDDEN))  /* don't print hidden files */
          {
            filesize.LowPart = ffd.nFileSizeLow;
            filesize.HighPart = ffd.nFileSizeHigh;
            printf("  %s     \t\t\t%ld bytes\n", ffd.cFileName, filesize.QuadPart);
          }
        }
     }
     while (FindNextFile(hFind, &ffd) != 0);
 
     dwError = GetLastError();
     if (dwError != ERROR_NO_MORE_FILES) 
     {
       DisplayErrorBox(TEXT("FindFirstFile"));
     }

     FindClose(hFind);
     return dwError;
  }
}

void DisplayErrorBox(LPTSTR lpszFunction) 
{ 
    // Retrieve the system error message for the last-error code

    LPVOID lpMsgBuf;
    LPVOID lpDisplayBuf;
    DWORD dw = GetLastError(); 

    FormatMessage(
        FORMAT_MESSAGE_ALLOCATE_BUFFER | 
        FORMAT_MESSAGE_FROM_SYSTEM |
        FORMAT_MESSAGE_IGNORE_INSERTS,
        NULL,
        dw,
        MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
        (LPTSTR) &lpMsgBuf,
        0, NULL );

    // Display the error message and clean up

    lpDisplayBuf = (LPVOID)LocalAlloc(LMEM_ZEROINIT, 
        (lstrlen((LPCTSTR)lpMsgBuf)+lstrlen((LPCTSTR)lpszFunction)+40)*sizeof(TCHAR)); 
    StringCchPrintf((LPTSTR)lpDisplayBuf, 
        LocalSize(lpDisplayBuf) / sizeof(TCHAR),
        TEXT("%s failed with error %d: %s"), 
        lpszFunction, dw, lpMsgBuf); 
    MessageBox(NULL, (LPCTSTR)lpDisplayBuf, TEXT("Error"), MB_OK); 

    LocalFree(lpMsgBuf);
    LocalFree(lpDisplayBuf);
}

