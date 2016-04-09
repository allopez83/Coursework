/* 
  vmms.cpp - This file contains the code for each of the memory functions as well as initialization of the "shared" memory.
*/

#include "vmms_error.h"
#include <string.h>

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
  int rc = VMMS_SUCCESS;

  /* Put your source code here */

  return rc;
}

__declspec(dllexport) int mmc_display_memory ( char* filename )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */

  return rc;
}

__declspec(dllexport) char* vmms_malloc (  int size, int* error_code )
{
  /* Put your source code here */
  // Ceiling of the size, multiple of DEFAULT_BOUNDRY
  // First look through free list to free mem location
  *error_code = VMMS_SUCCESS;
  return mem_start;             // for testing
}

__declspec(dllexport) int vmms_memset ( char* dest_ptr, char c, int size )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // put hex of char c into dest_ptr
  // check if address is allocated to your pid

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  return rc;
}


__declspec(dllexport) int vmms_memcpy ( char* dest_ptr, char* src_ptr, int size )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // check if address is allocated to your pid

  // fopen, fwrite, update vmem in binary (phys and virt is the same)

  return rc;
}


__declspec(dllexport) int vmms_print ( char* src_ptr, int size )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */

  return rc;
}

__declspec(dllexport) int vmms_free ( char* mem_ptr )
{
  int rc = VMMS_SUCCESS;

  /* Put your source code here */
  // memset overwrite existing data with garbage data to the memory to be freed
  // set memory as free
  // Merge adjacent free mem locations
  // Sort free table according to mem address, and check adjacency by adding size to address, should match next address

  // fopen, fwrite, update vmem in binary (phys and virt is the same)


  return rc;
}

