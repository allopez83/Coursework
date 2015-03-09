package assignment07.reference;

public class mergesort
{
   // input array 1: client[left_pos] ... client[right_pos-1]
   // input array 2: client[right_pos] ... client[right_stop]
   // working[] array supplied by client to avoid local allocation
   protected static <E extends Comparable<? super E>> void merge(E[] client,
         E[] working, int left_pos, int right_pos, int right_stop)
   {
      int left_stop, working_pos, array_size;

      working_pos = left_pos;
      left_stop = right_pos - 1;
      array_size = right_stop - left_pos + 1;

      // as soon as we reach the end of either input array, stop
      while (left_pos <= left_stop && right_pos <= right_stop)
         if (client[left_pos].compareTo(client[right_pos]) < 0)
            working[working_pos++] = client[left_pos++];
         else
            working[working_pos++] = client[right_pos++];

      // merge is over; copy the remainder of one or the other input array
      while (left_pos <= left_stop)
         working[working_pos++] = client[left_pos++];
      while (right_pos <= right_stop)
         working[working_pos++] = client[right_pos++];

      // copy back into client array
      for (; array_size > 0; array_size--, right_stop--)
         client[right_stop] = working[right_stop];
   }

   // mergesort internal function
   protected static <E extends Comparable<? super E>> void mergeSort(E[] a,
         E[] working, int start, int stop)
   {
      int right_start;

      if (stop - start < 1)
         return;

      right_start = (start + stop) / 2 + 1;
      mergeSort(a, working, start, right_start - 1);
      mergeSort(a, working, right_start, stop);
      merge(a, working, start, right_start, stop);
   }

   // mergesort public driver
   public static <E extends Comparable<? super E>> void mergeSort(E[] a)
   {
      if (a.length < 2)
         return;

      @SuppressWarnings("unchecked")
      E[] working = (E[]) new Comparable[a.length];
      mergeSort(a, working, 0, a.length - 1);
   }
}
