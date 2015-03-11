import java.text.NumberFormat;
import java.util.Locale;

public class fragment1
{
   public static void main(String args[])
   {
      // Stuff for the timer
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long start_time, end_time;

      // Go!
      start_time = System.nanoTime();
      algorithm(100000);
      end_time = System.nanoTime();

      // Results
      String result = (tidy.format((end_time - start_time) / 1e3));
      System.out.println("Time: " + result + " microseconds.");
   }

   private static void algorithm(int n)
   {
      int sum = 0;
      for (int i = 0; i < n; i++)
         sum++;
   }
}