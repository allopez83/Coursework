// This is a simple test program

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <io.h>
#include <process.h>      // for getpid()

#include "vmms.h"

// Debugging
bool DEBUG_TRAVERSAL;
bool DEBUG_VARIABLES;

int main() {
  if (DEBUG_TRAVERSAL) printf(" -> main\n");

  if (DEBUG_VARIABLES) printf("PID: %i\n", getpid());

  int rc = 0;
  char *list;
  
  list = (char*) vmms_malloc(50, &rc);

  printf("%s: %d\n", "return code", rc);

  if (list == NULL)
    return 1;

  strcpy (list, "dummy1");
  strcpy (list+10, "911");
  
  // system("pause");

  printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);
  // dummy1

  printf("memset returned code: %i\n", vmms_memset(list, 'z', 4));
  printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);
  // zzzzy1

  printf("memcpy returned code: %i\n", vmms_memcpy(list, list+10, 3));
  printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);
  // 911zy1

  printf("print returned code: %i\n", vmms_print(list, 5)); // 911zy
  printf("print returned code: %i\n", vmms_print(list+10, 0)); // 911
  printf("print returned code: %i\n", vmms_print(list, 50)); // 911zy1911

  // system("pause");

  printf("free returned code: %i\n", vmms_free((char*)list));

  
  printf("\nvmms_free test\n");
  // malloc
  char* first;
  char* second;
  char* third;
  first = vmms_malloc(10, &rc);
  second = vmms_malloc(10, &rc);
  third = vmms_malloc(10, &rc);
  // State of memory
  printf("first malloc: %p\n", first);
  printf("second malloc: %p\n", second);
  printf("third malloc: %p\n", third);
  
  // Last merge should combine all free space
  printf("free first malloc:\n");
  vmms_free(first);
  
  printf("free third malloc:\n");
  vmms_free(third);
  
  printf("free second malloc:\n");
  vmms_free(second);
  
  system("pause");

  if (DEBUG_TRAVERSAL) printf(" <- main\n");
  return 0;
}
