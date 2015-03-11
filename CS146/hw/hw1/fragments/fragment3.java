public class fragment3
{
   public static void main(String args[])
   {
      // Stuff for the timer
      long start_time, end_time;

      // Go!
      start_time = System.nanoTime();
      algorithm(50);
      end_time = System.nanoTime();

      // Results
      System.out.println("Time: " + ((end_time - start_time) / 1e3) + " microseconds.");
   }

   private static void algorithm(int n)
   {
      int sum = 0;
      for (int i = 0; i < n; i++)
         for (int j = 0; j < n * n; j++)
            sum++;
   }
}
