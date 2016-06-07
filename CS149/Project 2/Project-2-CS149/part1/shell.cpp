#include <windows.h>
#include <tchar.h> 
#include <stdio.h>
#include <strsafe.h>
#include <string.h>
#include <algorithm>
#pragma comment(lib, "User32.lib")

struct fileItem {
   FILETIME time;       // last modify time
   bool isFile;           // true for file, false for folder
   int size;            // bytes of file
   char *name;          // name of the item
};
fileItem dirContent[1000];

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
   if (stricmp(argv[1], "dir") == 0) {
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

      printf(" ----- reading dir\n");
      // List all the files in the directory with some info about them.
      int position = -1;
      do
      {
         position++;
         if (ffd.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)
         {
            // printf("  %s     \t\t<DIR>\n", ffd.cFileName);
            dirContent[position].isFile = false;
            // printf("Found a dir\n");
         }
         else
         {
            if (!(ffd.dwFileAttributes & FILE_ATTRIBUTE_HIDDEN))  /* don't print hidden files */
            {
               filesize.LowPart = ffd.nFileSizeLow;
               filesize.HighPart = ffd.nFileSizeHigh;
               // printf("  %s     \t\t\t%ld bytes\n", ffd.cFileName, filesize.QuadPart);
               dirContent[position].isFile = true;
               dirContent[position].size = filesize.QuadPart;
               // printf("Found a file\n");
            }
         }
         // Name, same for dirs and files
         // dirContent[position].name = ffd.cFileName;
         // printf("copy");
         // strncpy(dirContent[position].name, ffd.cFileName, sizeof(ffd.cFileName));
         dirContent[position].name = (char *)malloc(strlen(ffd.cFileName)+1);
         strcpy(dirContent[position].name, ffd.cFileName);

         // File time
         dirContent[position].time = ffd.ftLastWriteTime;
         
         // SYSTEMTIME systime;023.
         // printf("Conversion %i\n", FileTimeToSystemTime(&dirContent[position].time));

         // printf("position: %d, time:?, isFile: %i, size: %i, name: %s\n",
         //    position,
         //    // dirContent[position].time.dwLowDateTime,
         //    dirContent[position].isFile,
         //    dirContent[position].size,
         //    dirContent[position].name
         // );
      }
      while (FindNextFile(hFind, &ffd) != 0);
      
      for (int i = 0; i <= position; i++) {

         // printf("position: %d, time:?, isFile: %i, size: %i, name: %s\n",
         //    i,
         //    // dirContent[position].time.dwLowDateTime,
         //    dirContent[i].isFile,
         //    dirContent[i].size,
         //    dirContent[i].name
         //    );

         // SYSTEMTIME stUTC, stLocal;
         // FileTimeToSystemTime(&dirContent[i].time, &stUTC);
         // SystemTimeToTzSpecificLocalTime(NULL, &stUTC, &stLocal);
         // printf("%d: time: %02d-%02d-%d %02d:%02d, isFile: %i, size: %i, name: %s\n",
         //    i,
         //    stLocal.wYear, stLocal.wMonth, stLocal.wDay, stLocal.wHour, stLocal.wMinute,
         //    dirContent[i].isFile,
         //    dirContent[i].size,
         //    dirContent[i].name
         // );
      }
      // printf("end of dirContent\n");

      dwError = GetLastError();
      if (dwError != ERROR_NO_MORE_FILES) 
      {
         DisplayErrorBox(TEXT("FindFirstFile"));
      }

      FindClose(hFind);

      printf(" ----- sorting contents\n");

      // if (input == "/zd")

      // Sort saved file structs
      // std::sort(dirContent.begin(), dirContent.end(), CompareFileTime()); // need to modify
      // if (dirContent == NULL) {
      //    return(0);
      // } else {
      if (_tcscmp(argv[3], _T("/zs")) == 0) {
         printf("sort by size\n");
         for (int i=1; i <= position; i++) {
            // key value
            int value = dirContent[i].size;
            //compare it to previous value
            int j = i-1;
            //while j never reaches to 0 index, and value is less than its previous index
            while (j >= 0 && value > dirContent[j].size) {
               fileItem temp = dirContent[j];
               dirContent[j] = dirContent[j+1];
               dirContent[j+1] = temp;
               j--;
               // printf("%s %s\n", dirContent[j+1].name, temp.name);
            }
         }
      } else if (_tcscmp(argv[3], _T("/zd")) == 0) {
         printf("sort by date\n");
         for (int i=1; i <= position; i++) {
            // key value
            FILETIME value = dirContent[i].time;
            //compare it to previous value
            int j = i-1;
            //while j never reaches to 0 index, and value is less than its previous index
            while (j >= 0 && CompareFileTime(&value, &dirContent[j].time) == 1) {
               fileItem temp = dirContent[j];
               dirContent[j] = dirContent[j+1];
               dirContent[j+1] = temp;
               j--;
               // printf("%s %s\n", dirContent[j+1].name, temp.name);
            }
         }
      }
      // }
      
      printf(" ----- printing to stdout\n");

      printf(" %18s | %10s | %10s | %s\n", "TIME", "DIR/FILE", "SIZE", "NAME");
      printf(" %18s + %10s + %10s + %s\n", "-----", "-----", "-----", "-----");
      
      for (int i = 0; i <= position; i++) {
         fileItem item = dirContent[i]; // go through all contents

         char time[20];
         SYSTEMTIME stUTC, stLocal;
         FileTimeToSystemTime(&dirContent[i].time, &stUTC);
         SystemTimeToTzSpecificLocalTime(NULL, &stUTC, &stLocal);
         sprintf(time, "%02d-%02d-%2d %02d:%02d", stLocal.wYear, stLocal.wMonth, stLocal.wDay, stLocal.wHour, stLocal.wMinute);
         // printf("%d: time: %02d-%02d-%d %02d:%02d, isFile: %i, size: %i, name: %s\n",
         //    i,
         //    stLocal.wYear, stLocal.wMonth, stLocal.wDay, stLocal.wHour, stLocal.wMinute,
         //    dirContent[i].isFile,
         //    dirContent[i].size,
         //    dirContent[i].name
         // );
         
         if (item.isFile) {
            // It's a file
            printf(" %18s | %10s | %10i | %s\n", time, "", item.size, item.name);
         } else {
            // It's a folder
            printf(" %18s | %10s | %10i | %s\n", time, "<DIR>", 0, item.name);
         }
      }

      printf(" ----- end\n");

      return dwError;
   }

   else if(stricmp(argv[1], "compare") == 0) {
      if (strlen(argv[2]) > (MAX_PATH - 3) || strlen(argv[3]) > (MAX_PATH - 3)) {
         printf("\nDirectory path is too long.\n");
         return (-1);
      }
      printf("Comparing...\n");

      //Generating references to the files
      FILE * file1;
      FILE * file2;
      file1 = fopen(argv[2], "rb");
      file2 = fopen(argv[3], "rb");

      //Getting sizes of files
      fseek(file1, 0L, SEEK_END);
      fseek(file2, 0L, SEEK_END);
      if(ftell(file1) != ftell(file2)) {
         printf("\nFiles are different sizes. Cannot compare.\n");
         return (-1);
      }

      int filesize = ftell(file1);

      fseek(file1, 0L, SEEK_SET);
      fseek(file2, 0L, SEEK_SET);

      if(argv[4] != NULL) {
         printf("Character Test\n");
         TCHAR * skipChar = argv[4];

         for(int i = 0; i < filesize; i++) {
            fseek(file1, 0L + i, SEEK_SET);
            fseek(file2, 0L + i, SEEK_SET);
            char char1 = fgetc(file1);
            char char2 = fgetc(file2);

            if(memcmp(skipChar, &char1, 1) != 0) {
               if(char1 != char2) {
                  printf("\nDiscrepency found at position %i in file, between %s (%c) and %s (%c)\n", i, argv[2], char1, argv[3], char2);
                  return (-1);
               }
            }
         }
      } else {
         for(int i = 0; i < filesize; i++) {
            fseek(file1, 0L + i, SEEK_SET);
            fseek(file2, 0L + i, SEEK_SET);
            char char1 = fgetc(file1);
            char char2 = fgetc(file2);

            if(char1 != char2) {
               printf("\nDiscrepency found at position %i in file, between %s (%c) and %s (%c)\n", i, argv[2], char1, argv[3], char2);
               return (-1);
            }
         }
      }
      printf("Files compare OK\n");
      return dwError;
   }
}

void DisplayErrorBox(LPTSTR lpszFunction) {
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
