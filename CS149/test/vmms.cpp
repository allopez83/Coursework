#include <iostream>

/*
Questions:

1. memcpy - does the src size need to be checked?
*/

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

int mmc_initialize(int boundry_size) {
  printf(" -> mmc_initialize\n");

  int rc = VMMS_SUCCESS;
  byte_boundry = boundry_size;
  
  freeItem initial;
  initial.addr = mem_start;
  initial.size = MAX_PHY_SIZE;
  freeTable[0] = initial;

  printf("%s: %p\n", "addr", freeTable[0].addr);
  printf("%s: %d\n", "size", freeTable[0].size);
  
  printf(" <- mmc_initialize\n");
  return rc;
}

char* vmms_malloc (  int size, int* error_code ) {
  printf(" -> vmms_malloc\n");

  // Ceiling of the size, multiple of DEFAULT_BOUNDRY
  // First look through free list to free mem location
  // Add to memTable

  // Actual size
  int blocks;
  blocks = size / byte_boundry;
  if (size % byte_boundry > 0)
    blocks++;
  int actualSize = blocks * DEFAULT_BOUNDRY;
  printf("%s: %d\n", "actualSize", actualSize);

  int largest = 0;
  int thisSize;
  int i;
  int pos;
  char* addr;
  bool exact;
  // Exact fit or largest free space
  for (i = 0; i < MAX_TABLE_SIZE; i++) {
    thisSize = freeTable[i].size;
    if (thisSize == actualSize) { // exact
      largest = actualSize;
      addr = freeTable[i].addr;
      exact = true;
      printf("Exact match!\n");
      break;
    }
    else if (thisSize > largest) { // find largest
      printf("New largest #%d: %d, at %p\n", i, thisSize, freeTable[i].addr);
      largest = thisSize;
      addr = freeTable[i].addr;
      pos = i;
    }
  }

  // Size check
  if (largest < actualSize) { // No free space large enough
    *error_code = OUT_OF_MEM;
    return NULL;
  }

  // Update freeTable
  freeTable[pos].size -= actualSize;
  if (exact) {
    // Remove freeItem
    freeTable[pos].addr = NULL;
  } else {
    // Decrease size
    printf("before: %p\n", freeTable[pos].addr);
    freeTable[pos].addr = ((freeTable[pos].addr)+actualSize);
    freeEntry++;
    printf("after: %p\n", freeTable[pos].addr);
  }
  printf("Size is now: %d\n", freeTable[pos].size);

  // New memItem
  memItem m;
  m.pid = getpid();
  m.addr = addr;
  m.requestSize = size;
  m.actualSize = actualSize;
  printf("Adding memItem: %i, %p, %i, %i\n", m.pid, m.addr, m.requestSize, m.actualSize);
  memTable[memEntry] = m;
  memEntry++;

  *error_code = VMMS_SUCCESS;
  
  printf(" <- vmms_malloc\n");
  return addr;
}

// Set the destination buffer with a character of certain size.
// If successful, returns 0. Otherwise it returns an error code.
int vmms_memset ( char* dest_ptr, char c, int size ) {
  printf(" -> vmms_memset\n");

  int rc = VMMS_SUCCESS;

  // put hex of char c into dest_ptr
  // check if address is allocated to your pid
  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  // Find closest memItem
  memItem m;
  char* thisAddr = mem_start;
  char* closestAddr = mem_start;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisAddr = memTable[i].addr;
    if (thisAddr <= dest_ptr && closestAddr < thisAddr) { // closer to dest_ptr
      closestAddr = thisAddr;
      m = memTable[i];
    }
  }

  // m is now the closest memItem
  printf("Closest memItem:\npid: %d\nsize: %d\naddr: %p\n", m.pid, m.requestSize, m.addr);

  if (m.pid != getpid()) // Not allocated to current process
    return INVALID_DEST_ADDR;

  char* allocEnd; // End address of allocated memory
  char* reqEnd; // End address of requested memory
  allocEnd = m.addr + m.requestSize;
  reqEnd = dest_ptr + size;
  printf("allocEnd: %p, reqEnd: %p\n", allocEnd, reqEnd);
  if (allocEnd < reqEnd) // Request exceeds allocated size
    return MEM_TOO_SMALL;

  // Write to memory
  for (int i = 0; i < size; i++)
    dest_ptr[i] = c;

  printf(" <- vmms_memset\n");

  return rc;
}

// Copy the fixed number of bytes from source to destination. 
// If successful, returns 0.  Otherwise it returns an error code.
int vmms_memcpy ( char* dest_ptr, char* src_ptr, int size ) {
  printf(" -> vmms_memcpy\n");

  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // check if address is allocated to your pid
  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  // printf("%p -> %p\n", src_ptr, dest_ptr);

  // Find closest memItem
  memItem mDest;
  memItem mSrc;
  char* thisAddr = NULL;
  char* closestDestAddr = NULL;
  char* closestSrcAddr = NULL;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisAddr = memTable[i].addr;
    // dest
    if (thisAddr <= dest_ptr && closestDestAddr < thisAddr) {
      // printf("memTable[%i] is closer to dest_ptr\n", i);
      closestDestAddr = thisAddr;
      mDest = memTable[i];
    }
    // src
    if (thisAddr <= src_ptr && closestSrcAddr < thisAddr) {
      // printf("memTable[%i] is closer to src_ptr\n", i);
      closestSrcAddr = thisAddr;
      mSrc = memTable[i];
    }
  }

  // mDest and mSrc are now the closest memItem
  printf("Closest src:\npid: %d\nsize: %d\naddr: %p\n", mSrc.pid, mSrc.requestSize, mSrc.addr);
  printf("Closest dest:\npid: %d\nsize: %d\naddr: %p\n", mDest.pid, mDest.requestSize, mDest.addr);

  // All memory readable, but only allocated memory can be written
  if (mDest.pid != getpid()) // Not allocated to current process
    return INVALID_CPY_ADDR;

  char* allocEnd; // End address of allocated memory
  char* reqEnd; // End address of requested memory
  allocEnd = mDest.addr + mDest.requestSize;
  reqEnd = dest_ptr + size;
  printf("allocEnd: %p, reqEnd: %p\n", allocEnd, reqEnd);
  if (allocEnd < reqEnd) // Request exceeds allocated size
    return MEM_TOO_SMALL;

  // TODO copy the memory
  for (int i = 0; i < size; i++) {
    printf("Replacing %c -> %c\n", *(src_ptr+i), *(dest_ptr+i));
    char c = *(src_ptr + i);
    *(dest_ptr + i) = c;
  }

  printf(" <- vmms_memcpy\n");

  return rc;
}

// Print the number of characters to STDOUT. 
// If size=0, then print until the first hex 0 to STDOUT.
// If successful, returns 0.  Otherwise it returns an error code.
int vmms_print ( char* src_ptr, int size ) {
  int rc = VMMS_SUCCESS;

  /* 
  if (invalid addr)
    return INVALID_CPY_ADDR;

  if (size == 0) {
    TODO print until hex 0, or null character
  }
  else {

  }

  return rc;
  */
}

// Free the allocated memory.
// If successful, returns 0.  Otherwise it returns an error code.
int vmms_free ( char* mem_ptr ) {
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // memset overwrite existing data with garbage data to the memory to be freed
  // set memory as free
  // Merge adjacent free mem locations
  // Sort free table according to mem address, and check adjacency by adding size to address, should match next address

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  /*
  if (invalid memory addr)
    return INVALID_MEM_ADDR;

  return rc;
  */
}

int main() {
  printf(" -> main\n");

  mmc_initialize(DEFAULT_BOUNDRY);

  int rc = 0;
  char *student_ptr;
  char *list;
  
  list = (char*) vmms_malloc(50, &rc);

  printf("%s: %d\n", "return code", rc);

  if (list == NULL)
    return 1;

  strcpy (list, "dummy1");
  strcpy (list+10, "911");

  printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);
  // dummy1

  printf("%i\n", vmms_memset(list, 'z', 4));
  printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);
  // zzzzy1

  printf("%i\n", vmms_memcpy(list, list+10, 3));
  printf("list address = %8x; Name = %s; ID = %s\n", list, list, (char*)list+10);
  // 911zy1

  // system("pause");

  // rc = vmms_free((char*)list);

  printf(" <- main\n");

  return 0;
}

