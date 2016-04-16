/* 
  vmms.cpp - This file contains the code for each of the memory functions as well as initialization of the "shared" memory.
*/

#include "vmms_error.h"
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
int entry = 0;                        // Tracks next entry location

struct freeItem {
  char* addr;
  int size;
};
freeItem freeTable[MAX_TABLE_SIZE];   // Free memory
int entry = 0;                        // Tracks next entry location

/* Here are the 5 exported functions for the application programs to use */
__declspec(dllexport) char* vmms_malloc (  int size, int* error_code );
__declspec(dllexport) int vmms_memset ( char* dest_ptr, char c, int size );
__declspec(dllexport) int vmms_memcpy ( char* dest_ptr, char* src_ptr, int size );
__declspec(dllexport) int vmms_print ( char* src_ptr, int size );
__declspec(dllexport) int vmms_free ( char* mem_ptr );

/* Here are several exported functions specifically for mmc.cpp */
__declspec(dllexport) int mmc_initialize (  int boundry_size );
__declspec(dllexport) int mmc_display_memtable ( char* filename );
__declspec(dllexport) int mmc_display_memory ( char* filename );
 

__declspec(dllexport) int mmc_initialize (  int boundry_size )
{
  int rc = VMMS_SUCCESS;
  byte_boundry = boundry_size;
  return rc;
}

__declspec(dllexport) int mmc_display_memtable ( char* filename )
{
  printf("Size @ Address\n");
  for (int i = 0; i < freeEntry; i++)
    if (freeTable[i].size > 0)
      printf("%i @ %p\n", freeTable[i].size, freeTable[i].addr);

  return VMMS_SUCCESS;
}

__declspec(dllexport) int mmc_display_memory ( char* filename )
{
  for (int i = 0; i < mem_size; i++){
    printf("%c", mem_start[i]);
    if (!(i % byte_boundry))
      printf(" ");
    if (!(i% 4*byte_boundry))
      printf("\n");
  }

  return VMMS_SUCCESS;
}

// Allocate a piece of memory given the input size.
// If successful, returns a valid pointer.  Otherwise it returns NULL (0) and set the error_code.
__declspec(dllexport) char* vmms_malloc (  int size, int* error_code )
{
  // Ceiling of the size, multiple of DEFAULT_BOUNDRY
  // First look through free list to free mem location
  // Add to memTable

  int blocks;
  blocks = size / 8;
  if (size % 8 > 0)
    blocks++;
  int actualSize = blocks * DEFAULT_BOUNDRY;

  int largest = 0;
  int thisSize;
  char* addr
  // Exact fit or largest free space
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisSize = freeTable[i].size;
    if (thisSize == actualSize) { // exact
      largest = actualSize;
      addr = freeTable[i].addr;
      break;
    }
    else if (thisSize > largest.size) { // find largest
      largest = thisSize;
      addr = freeTable[i].addr
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

  memTable[entry] = m;
  entry++;

  *error_code = VMMS_SUCCESS;

  return addr;
  // return mem_start;             // for testing
}

// Set the destination buffer with a character of certain size.
// If successful, returns 0. Otherwise it returns an error code.
__declspec(dllexport) int vmms_memset ( char* dest_ptr, char c, int size )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // put hex of char c into dest_ptr
  // check if address is allocated to your pid

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  char* thisAddr, closestAddr = mem_start;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    if (thisAddr < dest_ptr && closestAddr < thisAddr) // closer to dest_ptr
      closestAddr = thisAddr;
  }

  if (desttoosmall)
    return MEM_TOO_SMALL;
  if (destaddrinvalid)
    return INVALID_DEST_ADDR;

  return rc;
}

// Copy the fixed number of bytes from source to destination. 
// If successful, returns 0.  Otherwise it returns an error code.
__declspec(dllexport) int vmms_memcpy ( char* dest_ptr, char* src_ptr, int size )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // check if address is allocated to your pid

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  if (desttoosmall)
    return MEM_TOO_SMALL;
  if (srcordestaddrinvalid)
    return INVALID_CPY_ADDR;

  return rc;
}

// Print the number of characters to STDOUT. 
// If size=0, then print until the first hex 0 to STDOUT.
// If successful, returns 0.  Otherwise it returns an error code.
__declspec(dllexport) int vmms_print ( char* src_ptr, int size )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  if (invalid addr)
    return INVALID_CPY_ADDR;

  if (size == 0) {

  }
  else {

  }

  return rc;
}

// Free the allocated memory.
// If successful, returns 0.  Otherwise it returns an error code.
__declspec(dllexport) int vmms_free ( char* mem_ptr )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // memset overwrite existing data with garbage data to the memory to be freed
  // set memory as free
  // Merge adjacent free mem locations
  // Sort free table according to mem address, and check adjacency by adding size to address, should match next address

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  if (invalid memory addr)
    return INVALID_MEM_ADDR;

  return rc;
}

