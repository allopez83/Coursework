
/*
Questions:

1. memcpy - does the src size need to be checked?
2. are the pointers passed in referring to the start or any part of a block?
3. what are "the return values 4 bytes & its parameters" for the vmms log?

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
#include <fstream>    // write binary to file

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

// Files
char dump[] = "VMMS.MEM";
char log[] = "VMMS.LOG";

// Debugging
bool DEBUG_TRAVERSAL = false;
bool DEBUG_VARIABLES = false;


// END DECLARATIONS //////////////////////


using namespace std;

int mmc_initialize(int boundry_size) {
  if (DEBUG_TRAVERSAL) printf(" -> mmc_initialize\n");

  byte_boundry = boundry_size;
  freeItem initial;
  initial.addr = mem_start;
  initial.size = mem_size;
  freeTable[0] = initial;

  if (DEBUG_VARIABLES) printf("%s: %p\n", "addr", freeTable[0].addr);
  if (DEBUG_VARIABLES) printf("%s: %d\n", "size", freeTable[0].size);
  
  if (DEBUG_TRAVERSAL) printf(" <- mmc_initialize\n");
  return VMMS_SUCCESS;
}

int mmc_display_memtable ( char* filename )
{
  if (DEBUG_TRAVERSAL) printf(" -> mmc_display_memtable\n");

  printf("Size @ Address\n");
  for (int i = 0; i < freeEntry; i++) {
    if (freeTable[i].size > 0)
      printf("%i @ %p\n", freeTable[i].size, freeTable[i].addr);
  }

  if (DEBUG_TRAVERSAL) printf(" <- mmc_display_memtable\n");

  return VMMS_SUCCESS;
}

int mmc_display_memory ( char* filename )
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

char* vmms_malloc (  int size, int* error_code ) {
  if (DEBUG_TRAVERSAL) printf(" -> vmms_malloc\n");

  // Actual size
  int blocks;
  blocks = size / byte_boundry;
  if (size % byte_boundry > 0)
    blocks++;
  int actualSize = blocks * DEFAULT_BOUNDRY;
  if (DEBUG_VARIABLES) printf("%s: %d\n", "actualSize", actualSize);

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
int vmms_memset ( char* dest_ptr, char c, int size ) {
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
  // time_t now = time(0);
  // struct tm tstruct = *localtime(&now);
  // char str[50];
  // strftime(str, sizeof(str), "%Y%m%d%H%M%S", &tstruct);
  // printf("%s %s %i %s\n", str, "vmmstest.exe", getpid(), "mms_memset");
  
  // ofstream dumpStream (dump, ios::out | ios::binary);
  // dumpStream.write (mem_start, mem_size);
  // dumpStream.close();

  if (DEBUG_TRAVERSAL) printf(" <- vmms_memset\n");
  return VMMS_SUCCESS;
}

// Copy the fixed number of bytes from source to destination. 
// If successful, returns 0.  Otherwise it returns an error code.
int vmms_memcpy ( char* dest_ptr, char* src_ptr, int size ) {
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
  // time_t now = time(0);
  // struct tm tstruct = *localtime(&now);
  // char str[50];
  // strftime(str, sizeof(str), "%Y%m%d%H%M%S", &tstruct);
  // printf("%s %s %i %s\n", str, "vmmstest.exe", getpid(), "mms_memset");
  
  // ofstream dumpStream (dump, ios::out | ios::binary);
  // dumpStream.write (mem_start, mem_size);
  // dumpStream.close();

  if (DEBUG_TRAVERSAL) printf(" <- vmms_memcpy\n");
  return VMMS_SUCCESS;
}

// Print the number of characters to STDOUT. 
// If size=0, then print until the first hex 0 to STDOUT.
// If successful, returns 0.  Otherwise it returns an error code.
int vmms_print ( char* src_ptr, int size ) {
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
// mem_ptr only points to start of allocated memory block
int vmms_free ( char* mem_ptr ) {
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
  // time_t now = time(0);
  // struct tm tstruct = *localtime(&now);
  // char str[50];
  // strftime(str, sizeof(str), "%Y%m%d%H%M%S", &tstruct);
  // printf("%s %s %i %s\n", str, "vmmstest.exe", getpid(), "mms_memset");
  
  // ofstream dumpStream (dump, ios::out | ios::binary);
  // dumpStream.write (mem_start, mem_size);
  // dumpStream.close();

  if (DEBUG_TRAVERSAL) printf(" <- vmms_free\n");
  return VMMS_SUCCESS;
}

int main() {
  if (DEBUG_TRAVERSAL) printf(" -> main\n");

  if (DEBUG_VARIABLES) printf("PID: %i\n", getpid());

  mmc_initialize(DEFAULT_BOUNDRY);

  int rc = 0;
  char *list;
  
  list = (char*) vmms_malloc(50, &rc);

  printf("%s: %d\n", "return code", rc);

  if (list == NULL)
    return 1;

  strcpy (list, "dummy1");
  strcpy (list+10, "911");

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
  printf("freeTable:\n");
  for (int i = 0; i < freeEntry; i++)
    if (freeTable[i].size > 0)
      printf("  %i @ %p\n", freeTable[i].size, freeTable[i].addr);
  
  // Last merge should combine all free space
  vmms_free(first);
  printf("free first malloc:\n");
  for (int i = 0; i < freeEntry; i++)
    if (freeTable[i].size > 0)
      printf("  %i @ %p\n", freeTable[i].size, freeTable[i].addr);
  
  vmms_free(third);
  printf("free third malloc:\n");
  for (int i = 0; i < freeEntry; i++)
    if (freeTable[i].size > 0)
      printf("  %i @ %p\n", freeTable[i].size, freeTable[i].addr);
  
  vmms_free(second);
  printf("free second malloc:\n");
  for (int i = 0; i < freeEntry; i++)
    if (freeTable[i].size > 0)
      printf("  %i @ %p\n", freeTable[i].size, freeTable[i].addr);

  if (DEBUG_TRAVERSAL) printf(" <- main\n");
  return 0;
}

