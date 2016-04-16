/* 
  vmms.cpp - This file contains the code for each of the memory functions as well as initialization of the "shared" memory.
*/

#include "vmms_error.h"
#include <string.h>

// #include <unistd.h>    // for getpid()
#include <process.h>      // for getpid()

#include <windows.h>      // for timestamp
#include <stdio.h>        // for timestamp

#include <fstream>        // write binary to file

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
struct memItem {
  int pid;
  char* addr;
  int requestSize;
  int actualSize;
};
memItem memTable[MAX_TABLE_SIZE];     // Allocated memory
struct freeItem {
  char* addr;
  int size;
};
freeItem freeTable[MAX_TABLE_SIZE];   // Free memory
#pragma data_seg ()

// Map table Structures/Entries
// 
// Mapping table and Free table would be implemented as a 2d array
// 1000 entries would be fine, since test shouldn't exceed 1000 operations

int memEntry = 0;  // Tracks next entry location
int freeEntry = 0; // Tracks next entry location

bool firstRun = true;

// Files
char* dumpFile = "VMMS.MEM";
char* logFile = "VMMS.LOG";

// Debugging
bool DEBUG_TRAVERSAL = true;
bool DEBUG_VARIABLES = true;

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
  if (DEBUG_TRAVERSAL) printf(" -> mmc_initialize\n");

  byte_boundry = boundry_size;
  freeItem initial;
  initial.addr = mem_start;
  initial.size = mem_size;
  freeTable[0] = initial;

  if (DEBUG_VARIABLES) printf("Initialized memory, %i @ %p, boundary of %i\n", freeTable[0].size, freeTable[0].addr, boundry_size);
  
  if (DEBUG_TRAVERSAL) printf(" <- mmc_initialize\n");
  return VMMS_SUCCESS;
}

__declspec(dllexport) int mmc_display_memtable ( char* filename )
{
  if (DEBUG_TRAVERSAL) printf(" -> mmc_display_memtable\n");

  printf("memTable\n");
  printf("pid, addr, requestSize, actualSize\n");
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    if (memTable[i].pid > 0)
      printf("%i %p %i %i\n", memTable[i].pid, memTable[i].addr, memTable[i].requestSize, memTable[i].actualSize);
  }
  printf("freeTable\n");
  printf("size, address\n");
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    if (freeTable[i].size > 0)
      printf("%i: %i %p\n", i, freeTable[i].size, freeTable[i].addr);
  }

  if (DEBUG_TRAVERSAL) printf(" <- mmc_display_memtable\n");
  return VMMS_SUCCESS;
}

__declspec(dllexport) int mmc_display_memory ( char* filename )
{
  if (DEBUG_TRAVERSAL) printf(" -> mmc_display_memory\n");

  for (int i = 0; i < mem_size; i++) {
    if (!(i % byte_boundry)) // Grouped by byte_boundary
      printf(" ");
    if (!(i % (4*byte_boundry))) // Four columns
      printf("\n");
    printf("%c", mem_start[i]);
  }
  
  if (DEBUG_TRAVERSAL) printf(" <- mmc_display_memory\n");
  return VMMS_SUCCESS;

}

// Allocate a piece of memory given the input size.
// If successful, returns a valid pointer.  Otherwise it returns NULL (0) and set the error_code.
__declspec(dllexport) char* vmms_malloc (  int size, int* error_code )
{
  if (DEBUG_TRAVERSAL) printf(" -> vmms_malloc\n");
  
  // Initialize memory
  if (firstRun) {
    freeTable[0].addr = mem_start;
    freeTable[0].size = mem_size;
    firstRun = false;
    if (DEBUG_VARIABLES) printf("Initialized memory, %i @ %p, boundary of %i\n", freeTable[0].size, freeTable[0].addr, byte_boundry);
  }

  // Actual size
  int blocks;
  blocks = size / byte_boundry;
  if (size % byte_boundry > 0)
    blocks++;
  int actualSize = blocks * byte_boundry;
  if (DEBUG_VARIABLES) printf("%s: %d\n", "actualSize", actualSize);
  
  printf("freeTable[0]: %i @ %p\n", freeTable[0].size, freeTable[0].addr);

  int largest = 0;
  int thisSize;
  int pos;
  char* addr;
  bool exact;
  // Exact fit or largest free space
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisSize = freeTable[i].size;
	// printf("Comparing %i to %i", thisSize, largest);
    if (thisSize == actualSize) { // exact
      largest = actualSize;
      addr = freeTable[i].addr;
      exact = true;
      if (DEBUG_VARIABLES) printf("Exact match!\n");
      break;
    }
    else if (thisSize > largest) { // find largest
      if (DEBUG_VARIABLES) printf("New largest #%d: %d, at %p\n", i, thisSize, freeTable[i].addr);
      largest = thisSize;
      addr = freeTable[i].addr;
      pos = i;
    }
  }

  // Size check
  if (largest < actualSize) { // No free space large enough
    printf("ERROR: OUT_OF_MEM!\n");
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
    if (DEBUG_VARIABLES) printf("before: %p\n", freeTable[pos].addr);
    freeTable[pos].addr = ((freeTable[pos].addr)+actualSize);
    freeEntry++;
    if (DEBUG_VARIABLES) printf("freeItem is now: %p\n", freeTable[pos].addr);
  }
  if (DEBUG_VARIABLES) printf("Size is now: %d\n", freeTable[pos].size);

  // New memItem
  memTable[memEntry].pid = getpid();
  memTable[memEntry].addr = addr;
  memTable[memEntry].requestSize = size;
  memTable[memEntry].actualSize = actualSize;
  memItem m = memTable[memEntry];
  memEntry++;

  // printf("placed memitem as entry %i\n", memEntry-1);
  if (DEBUG_VARIABLES) printf("Added memItem: %i, %p, %i, %i\n", m.pid, m.addr, m.requestSize, m.actualSize);

  *error_code = VMMS_SUCCESS;
  
  if (DEBUG_TRAVERSAL) printf(" <- vmms_malloc\n");
  return addr;
}

// Set the destination buffer with a character of certain size.
// If successful, returns 0. Otherwise it returns an error code.
__declspec(dllexport) int vmms_memset ( char* dest_ptr, char c, int size )
{
  if (DEBUG_TRAVERSAL) printf(" -> vmms_memset\n");

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  // Find closest memItem
  memItem m;
  char* thisAddr = mem_start;
  char* closestAddr = mem_start;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisAddr = memTable[i].addr;
    if (thisAddr <= dest_ptr && closestAddr <= thisAddr) { // closer to dest_ptr
      closestAddr = thisAddr;
      m = memTable[i];
    }
  }

  // m is now the closest memItem
  if (DEBUG_VARIABLES) printf("Closest memItem:\npid: %d\nsize: %d\naddr: %p\n", m.pid, m.requestSize, m.addr);

  if (m.pid != getpid()) { // Not allocated to current process
    printf("ERROR: INVALID_DEST_ADDR!\n");
    return INVALID_DEST_ADDR;
  }

  char* allocEnd; // End address of allocated memory
  char* reqEnd; // End address of requested memory
  allocEnd = m.addr + m.requestSize;
  reqEnd = dest_ptr + size;
  if (DEBUG_VARIABLES) printf("allocEnd: %p, reqEnd: %p\n", allocEnd, reqEnd);
  if (allocEnd < reqEnd) { // Request exceeds allocated size
    printf("ERROR: MEM_TOO_SMALL\n");
    return MEM_TOO_SMALL;
  }

  // Write to memory
  for (int i = 0; i < size; i++)
    dest_ptr[i] = c;

  // Write log and mem dump
  SYSTEMTIME lt;
  GetLocalTime(&lt);
  printf("yyyymmddhhmmss:  %04i%02i%02i%02i%02i%02i\n", lt.wYear, lt.wMonth, lt.wDay, lt.wHour, lt.wMinute, lt.wSecond);
  printf("%s %i %s\n", "vmmstest.exe", getpid(), "mms_memset");

  std::ofstream outbin(dumpFile, std::ios::binary);
  outbin.write (mem_start, mem_size);
  outbin.close();

  if (DEBUG_TRAVERSAL) printf(" <- vmms_memset\n");
  return VMMS_SUCCESS;

}

// Copy the fixed number of bytes from source to destination. 
// If successful, returns 0.  Otherwise it returns an error code.
__declspec(dllexport) int vmms_memcpy ( char* dest_ptr, char* src_ptr, int size )
{
  if (DEBUG_TRAVERSAL) printf(" -> vmms_memcpy\n");

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  if (DEBUG_VARIABLES) printf("%p -> %p\n", src_ptr, dest_ptr);

  // Find closest memItem
  memItem mDest;
  memItem mSrc;
  char* thisAddr = mem_start;
  char* closestDestAddr = mem_start;
  char* closestSrcAddr = mem_start;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisAddr = memTable[i].addr;
    // dest
    if (thisAddr <= dest_ptr && closestDestAddr <= thisAddr) {
      if (DEBUG_VARIABLES) printf("memTable[%i] is closer to dest_ptr\n", i);
      closestDestAddr = thisAddr;
      mDest = memTable[i];
    }
    // src
    if (thisAddr <= src_ptr && closestSrcAddr <= thisAddr) {
      if (DEBUG_VARIABLES) printf("memTable[%i] is closer to src_ptr\n", i);
      closestSrcAddr = thisAddr;
      mSrc = memTable[i];
    }
  }

  // mDest and mSrc are now the closest memItem
  if (DEBUG_VARIABLES) printf("Closest src:\npid: %d\nsize: %d\naddr: %p\n", mSrc.pid, mSrc.requestSize, mSrc.addr);
  if (DEBUG_VARIABLES) printf("Closest dest:\npid: %d\nsize: %d\naddr: %p\n", mDest.pid, mDest.requestSize, mDest.addr);

  // All memory readable, but only allocated memory can be written
  if (mDest.pid != getpid()) { // Not allocated to current process
    printf("ERROR: INVALID_CPY_ADDR!\n");
    return INVALID_CPY_ADDR;
  }

  char* allocEnd; // End address of allocated memory
  char* reqEnd; // End address of requested memory
  allocEnd = mDest.addr + mDest.requestSize;
  reqEnd = dest_ptr + size;
  if (DEBUG_VARIABLES) printf("allocEnd: %p, reqEnd: %p\n", allocEnd, reqEnd);
  if (allocEnd < reqEnd) { // Request exceeds allocated size
    printf("ERROR: MEM_TOO_SMALL\n");
    return MEM_TOO_SMALL;
  }

  // Copy
  for (int i = 0; i < size; i++) {
    if (DEBUG_VARIABLES) printf("Replacing %c -> %c\n", *(src_ptr+i), *(dest_ptr+i));
    char c = *(src_ptr + i);
    *(dest_ptr + i) = c;
  }

  // Write log and mem dump
  SYSTEMTIME lt;
  GetLocalTime(&lt);
  printf("yyyymmddhhmmss:  %04i%02i%02i%02i%02i%02i\n", lt.wYear, lt.wMonth, lt.wDay, lt.wHour, lt.wMinute, lt.wSecond);
  printf("%s %i %s\n", "vmmstest.exe", getpid(), "mms_memset");

  std::ofstream outbin(dumpFile, std::ios::binary);
  outbin.write (mem_start, mem_size);
  outbin.close();

  if (DEBUG_TRAVERSAL) printf(" <- vmms_memcpy\n");
  return VMMS_SUCCESS;
}

// Print the number of characters to STDOUT. 
// If size=0, then print until the first hex 0 to STDOUT.
// If successful, returns 0.  Otherwise it returns an error code.
__declspec(dllexport) int vmms_print ( char* src_ptr, int size )
{
  if (DEBUG_TRAVERSAL) printf(" -> vmms_print\n");

  memItem m;
  char* thisAddr = mem_start;
  char* closestAddr = mem_start;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    thisAddr = memTable[i].addr;
    if (thisAddr <= src_ptr && closestAddr <= thisAddr) { // closer to src_ptr
      closestAddr = thisAddr;
      m = memTable[i];
    }
  }

  // m is now the closest memItem
  if (DEBUG_VARIABLES) printf("Closest memItem:\npid: %d\nsize: %d\naddr: %p\n", m.pid, m.requestSize, m.addr);

  if (m.pid != getpid()) { // Not allocated to current process
    printf("ERROR: INVALID_CPY_ADDR!\n");
    return INVALID_CPY_ADDR;
  }

  char* position = src_ptr;
  char c = 'a'; // placeholder value
  // Until null
  if (size == 0) {
    for (int i = 0; c != 0; i++) {
      c = *(position+i);
      printf("%c", c);      
    }
  }
  // Until size limit
  else {
    for (int i = 0; i < size; i++) {
      c = *(position+i);
      printf("%c", c);      
    }
  }

  printf("\n"); // formatting

  if (DEBUG_TRAVERSAL) printf(" <- vmms_print\n");
  return VMMS_SUCCESS;
}

// Free the allocated memory.
// If successful, returns 0.  Otherwise it returns an error code.
__declspec(dllexport) int vmms_free ( char* mem_ptr )
{

  if (DEBUG_TRAVERSAL) printf(" -> vmms_free\n");

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  // Find mem_ptr in memTable
  memItem* m;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    if (memTable[i].addr == mem_ptr) {
      m = &memTable[i];
      if (DEBUG_VARIABLES) printf("Found entry %i: %p\n", i, (*m).addr);
      break;
    }
  }

  // Didn't find a valid mem allocation
  if ((*m).pid != getpid()){
    printf("ERROR: INVALID_MEM_ADDR!\n");
    return INVALID_MEM_ADDR;
  }

  // Overwrite with garbage
  vmms_memset((*m).addr, '\xFF', (*m).requestSize);

  // Attempt to merge freeItems
  int size = (*m).actualSize;
  char* addr = (*m).addr;
  freeItem* f;
  for (int i = 0; i < MAX_TABLE_SIZE; i++) {
    f = &freeTable[i];
    // Front of memItem
    if ((*f).addr + (*f).size == (*m).addr) {
      addr = (*f).addr;
      size += (*f).size;
      (*f).addr = 0;
      (*f).size = 0;
    }
    // Back of memItem
    if ((*m).addr + (*m).actualSize == (*f).addr) {
      size += (*f).size;
      (*f).addr = 0;
      (*f).size = 0;
    }
  }
  // New freeItem
  freeTable[freeEntry].size = size;
  freeTable[freeEntry].addr = addr;
  if (DEBUG_VARIABLES) printf("Free: %i@%p\n", freeTable[freeEntry].size, freeTable[freeEntry].addr);
  freeEntry++;

  // Remove old memItem
  (*m).pid = 0;
  (*m).addr = NULL;
  (*m).requestSize = 0;
  (*m).actualSize = 0;

  // Write log and mem dump
  SYSTEMTIME t;
  GetLocalTime(&t);
  printf("yyyymmddhhmmss:  %04i%02i%02i%02i%02i%02i\n", t.wYear, t.wMonth, t.wDay, t.wHour, t.wMinute, t.wSecond);
  printf("%s %i %s\n", "vmmstest.exe", getpid(), "mms_memset");

  std::ofstream outbin(dumpFile, std::ios::binary);
  outbin.write (mem_start, mem_size);
  outbin.close();

  if (DEBUG_TRAVERSAL) printf(" <- vmms_free\n");
  return VMMS_SUCCESS;
}

