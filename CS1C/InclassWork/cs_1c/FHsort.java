package cs_1c;

public class FHsort
{
   // insertion sort -----------------------------------------------------
   public static < E extends Comparable< ? super E > > 
   void insertionSort(E[] a)
   {
      int k, pos, array_size;
      E tmp;
       
      array_size = a.length;
      for(pos = 1; pos < array_size; pos++ )
      {
         tmp = a[pos];
         for(k = pos; k > 0 && tmp.compareTo(a[k-1]) < 0; k-- )
            a[k] = a[k-1];
         a[k] = tmp;
      }
   }
   
   // shell sort #1 -- using shell's outer loop -----------------------
   public static < E extends Comparable< ? super E > > 
   void shellSort1(E[] a)
   {
      int gap = 1;
      int k, pos, array_size;
      E tmp;
       
      array_size = a.length;
      for (gap = array_size/2;  gap > 0;  gap /= 2)
         for(pos = gap; pos < array_size; pos++ )
         {
            tmp = a[pos];
            for(k = pos; k >= gap && tmp.compareTo(a[k-gap]) < 0; k -= gap )
               a[k] = a[k-gap];
            a[k] = tmp;
         }
   }
   
   // mergesort and helpers -------------------------------------------
   // input array 1:  client[left_pos] ... client[right_pos-1]
   // input array 2:  client[right_pos] ... client[right_stop]
   // working[] array supplied by client to avoid local allocation
   protected static < E extends Comparable< ? super E > >
   void merge(E[] client, E[] working, 
      int left_pos, int right_pos, int right_stop)
   {
      int left_stop, working_pos, array_size;

      working_pos = left_pos;
      left_stop = right_pos - 1;
      array_size = right_stop - left_pos + 1;

      // as soon as we reach the end of either input array, stop
      while(left_pos <= left_stop && right_pos <= right_stop)
         if(client[left_pos].compareTo(client[right_pos]) < 0 )
            working[working_pos++] = client[left_pos++];
         else
            working[working_pos++] = client[right_pos++];

      // merge is over; copy the remainder of one or the other input array
      while(left_pos <= left_stop)
         working[working_pos++] = client[left_pos++];
      while( right_pos <= right_stop )
         working[working_pos++] = client[right_pos++];

      // copy back into client array
      for( ; array_size > 0; array_size--, right_stop-- )
         client[right_stop] = working[right_stop];
   }
   
   // mergesort internal function
   protected static < E extends Comparable< ? super E > >
   void mergeSort(E[] a, E[] working, int start, int stop)
   {
      int right_start;

      if (stop - start < 1)
         return;

      right_start = (start + stop)/2 + 1;
      mergeSort(a, working, start, right_start - 1);
      mergeSort(a, working, right_start, stop);
      merge(a, working, start, right_start, stop);
   }

   // mergesort public driver 
   public static < E extends Comparable< ? super E > >
   void mergeSort(E[] a)
   {
      if (a.length < 2)
         return;

      E[] working = (E[])new Comparable[a.length];
      mergeSort(a, working, 0, a.length - 1);
   }
   
   // heapsort and helpers -------------------------------------------
   // percolateDown()
   protected static < E extends Comparable< ? super E > >
   void percolateDown(E[] a, int hole, int array_size)
   {
      int child;
      E tmp;

      for( tmp = a[hole]; 2 * hole + 1 < array_size; hole = child )
      {
         child = 2 * hole + 1;
         // if 2 children, get the GREATER of the two (because MAX heap)
         if( child < array_size - 1 && a[child].compareTo(a[child + 1]) < 0)
            child++;
         if( tmp.compareTo(a[child]) < 0 )   // MAX heap, not min heap
            a[hole] = a[child];
         else
            break;
      }
      a[hole] = tmp;
   }
   
   // heapsort public driver
   public static < E extends Comparable< ? super E > >
   void heapSort(E[] a)
   {
      int k, array_size;
      E temp;

      // order the array using percolate down
      array_size = a.length;
      for(k = array_size/2; k >= 0; k-- )
         percolateDown(a, k, array_size);

      // now remove the max element (root) and place at end of array
      for(k = array_size - 1; k > 0; k-- )
      {
         // "remove" by placing at end of array
         temp = a[0];
         a[0] = a[k];
         a[k] = temp;
         percolateDown( a, 0, k );  // k represents the shrinking array size
      }
   }
   
   // quicksort and helpers -------------------------------------------
   // median3 sorts a[left], a[center] and a[right].
   // it leaves the smallest in a[left], the largest in a[right]
   // and median (the pivot) is moved "out-of-the-way" in a[right-1].
   // (a[center] has what used to be in a[right-1])
   protected static < E extends Comparable< ? super E > >
   E median3(E[] a, int left, int right)
   {
      int center;
      E tmp;

      // swaps are done in-line for speed;  each compound line is a swap
      center = (left + right) / 2;
      if(a[center].compareTo(a[left]) < 0)
         { tmp = a[center]; a[center] = a[left]; a[left] = tmp; }
      if(a[right].compareTo(a[left]) < 0)
         { tmp = a[right]; a[right] = a[left]; a[left] = tmp; }
      if(a[right].compareTo(a[center]) < 0)
         { tmp = a[right]; a[right] = a[center]; a[center] = tmp; }

      tmp = a[center]; a[center] = a[right-1]; a[right-1] = tmp;

      return a[right - 1];
   }
   
   protected static int QS_RECURSION_LIMIT = 15;
   
   public static boolean setRecursionLimit(int new_lim)
   {
      if (new_lim < 2 || new_lim > 1000)
         return false;
      QS_RECURSION_LIMIT = new_lim;
      return true;
   }

   protected static < E extends Comparable< ? super E > >
   void quickSort(E[] a, int left, int right)
   {
      E pivot, tmp;
      int i, j;

      if( left + QS_RECURSION_LIMIT <= right )
      {
         pivot = median3(a, left, right);
         for(i = left, j = right - 1; ; )
         {
            while( a[++i].compareTo(pivot) < 0 )
               ;
            while( pivot.compareTo(a[--j]) < 0)
               ;
            if(i < j)
               { tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
            else
               break;
         }

         // restore pivot
         tmp = a[i]; a[i] = a[right - 1]; a[right - 1] = tmp;

         // recursive calls on smaller sub-groups
         quickSort(a, left, i - 1);     
         quickSort(a, i + 1, right);    
      }
      else
         // non-recursive escape valve - insertion sort
         insertionSort(a, left, right);
   }
   
   // private insertion sort that works on sub-arrays --------------
   protected static < E extends Comparable< ? super E > > 
   void insertionSort(E[] a, int start, int stop)
   {
      int k, pos;
      E tmp;
 
      // we are not testing for ranges to keep times down - private so ok
      for(pos = start + 1; pos <= stop; pos++ )
      {
         tmp = a[pos];
         for(k = pos; k > 0 && tmp.compareTo(a[k-1]) < 0; k-- )
            a[k] = a[k-1];
         a[k] = tmp;
      }
   }
   
   // public quicksort
   public static < E extends Comparable< ? super E > >
   void quickSort( E[] a )
   {
       quickSort(a, 0, a.length - 1);
   }
}
