package assignment07.reference;

public class ShellSort
{
   // shell sort #1 -- using shell's outer loop
   public static <E extends Comparable<? super E>> void shellSort1(E[] a)
   {
      int gap = 1;
      int k, pos, array_size;
      E tmp;

      array_size = a.length;
      for (gap = array_size / 2; gap > 0; gap /= 2)
         for (pos = gap; pos < array_size; pos++)
         {
            tmp = a[pos];
            for (k = pos; k >= gap && tmp.compareTo(a[k - gap]) < 0; k -= gap)
               a[k] = a[k - gap];
            a[k] = tmp;
         }
   }
}