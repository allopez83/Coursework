#include <windows.h>
#include <tchar.h> 
#include <stdio.h>
#include <strsafe.h>
#include <string.h>
#include <array>
#pragma comment(lib, "User32.lib")

struct fileItem {
   FILETIME time;       // last modify time
   bool file;           // true for file, false for folder
   int size;            // bytes of file
   char *name;          // name of the item
};
// fileItem dirContent[1000];
std::array<fileItem, 1000> dirContent;

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

   // shell dir \folder /zd
   //       1   2       3
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
      int position = 0;
      do
      {
         if (ffd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)
         {
            // printf("  %s     \t\t<DIR>\n", ffd.cFileName);
            dirContent[position].file = false;
         }
         else
         {
            if (!(ffd.dwFileAttributes & FILE_ATTRIBUTE_HIDDEN))  /* don't print hidden files */
            {
               filesize.LowPart = ffd.nFileSizeLow;
               filesize.HighPart = ffd.nFileSizeHigh;
               // printf("  %s     \t\t\t%ld bytes\n", ffd.cFileName, filesize.QuadPart);
               dirContent[position].file = true;
               dirContent[position].size = filesize.QuadPart;
            }
         }
         // Name, same for dirs and files
         dirContent[position].name = ffd.cFileName;

         // File time
         dirContent[position].time = ffd.ftLastWriteTime;
      }
      while (FindNextFile(hFind, &ffd) != 0);

      dwError = GetLastError();
      if (dwError != ERROR_NO_MORE_FILES) 
      {
         DisplayErrorBox(TEXT("FindFirstFile"));
      }

      FindClose(hFind);

      // Sort saved file structs
      std::sort(dirContent.begin(), dirContent.end(), CompareFileTime()); // need to modify
      
      // print to stdout
      for (fileItem item : dirContent) { // confirm this works
         printf("  %10s\n", item.time, item.file, item.size, item.name);
      }

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

