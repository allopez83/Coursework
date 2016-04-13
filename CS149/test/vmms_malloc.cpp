#include <iostream>


//////////////// DECLARATIONS //

// VMMS ERRORS

#define VMMS_SUCCESS      0 // success RC
#define OUT_OF_MEM    100 // Out of memory.
#define MEM_TOO_SMALL   101 // Destination memory buffer is too small for this operation.
#define INVALID_DEST_ADDR   102 // Invalid destination memory address.
#define INVALID_CPY_ADDR  103 // Invalid destination & source memory address.
#define INVALID_MEM_ADDR  104 // Invalid memory address. (includes freeing the same pointer twice)

// VMMS

#include <string.h>
#include <unistd.h>   // for getpid()

#define MAX_PHY_SIZE 8196    // 8K Bytes     ** Hardcode for testing !!
#define MAX_TABLE_SIZE 1024  // 1K entries
#define DEFAULT_BOUNDRY 8    // minimum 8 byte allocation block

// ************************************************************************
// Global Shared variables (shared by multiple instances of the DLL)
// ************************************************************************

/* Global shared memory section */
#pragma data_seg (".SHARED")  // Simulated Physical Mem // Hardcoded for now !!
int byte_boundry = DEFAULT_BOUNDRY;
int mem_size = MAX_PHY_SIZE;            // size of simulated phy mem (in bytes)
char mem_start [MAX_PHY_SIZE] = {0};    // simulated Phy Memory
#pragma data_seg ()

// Map table Structures/Entries
// 
// Mapping table and Free table would be implemented as a 2d array
// 1000 entries would be fine, since test shouldn't exceed 1000 operations
struct memItem {
  int pid;
  char* addr;
  int requestSize;
  int actualSize;
};
memItem memTable[MAX_TABLE_SIZE];     // Allocated memory
int memEntry = 0;                        // Tracks next entry location

struct freeItem {
  char* addr;
  int size;
};
freeItem freeTable[MAX_TABLE_SIZE];   // Free memory
int freeEntry = 0;                        // Tracks next entry location


// END DECLARATIONS //////////////////////



using namespace std;

int mmc_initialize(int boundry_size)
{
  int rc = VMMS_SUCCESS;
  byte_boundry = boundry_size;
  
  freeItem initial;
  initial.addr = mem_start;
  initial.size = MAX_PHY_SIZE;
  freeTable[0] = initial;

  return rc;
}

char* vmms_malloc (  int size, int* error_code )
{
  // Ceiling of the size, multiple of DEFAULT_BOUNDRY
  // First look through free list to free mem location
  // Add to memTable

  int blocks;
  blocks = size / byte_boundry;
  if (size % byte_boundry > 0)
    blocks++;
  int actualSize = blocks * DEFAULT_BOUNDRY;

  printf("%s: %d\n", "actualSize", actualSize);

  int largest = 0;
  int thisSize;
  char* addr;
  // Exact fit or largest free space
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisSize = freeTable[i].size;
    if (thisSize == actualSize) { // exact
      largest = actualSize;
      addr = freeTable[i].addr;
      printf("Exact match!\n");
      break;
    }
    else if (thisSize > largest) { // find largest
      printf("%s: %d\n", "New largest", thisSize);
      largest = thisSize;
      addr = freeTable[i].addr;
    }
  }

  if (largest < actualSize) { // No free space large enough
    *error_code = OUT_OF_MEM;
    return NULL;
  }

  memItem m;
  m.pid = getpid();
  m.addr = addr;
  m.requestSize = size;
  m.actualSize = actualSize;

  memTable[memEntry] = m;
  memEntry++;

  *error_code = VMMS_SUCCESS;

  return addr;
  // return mem_start;             // for testing
}

int main() {
  
  cout << "START\n";

  mmc_initialize(DEFAULT_BOUNDRY)

  int rc = 0;
  char *student_ptr;
  char *list;
  
  list = (char*) vmms_malloc(50, &rc);

  printf("%s: %d\n", "return code", rc);

  // if (list == NULL)
  //   return 1;

  // strcpy (list, "dummy1");
  // strcpy (list+10, "911");

  // printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);

  // system("pause");

  // rc = vmms_free((char*)list);

  cout << "END\n";

  return 0;
}

