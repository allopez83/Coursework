package hw2;

public class FibonacciDemo
{
   private static int calls;

   public static long fib(int n)
   {
      calls++;
      if (n <= 1)
         return 1;
      else
         return fib(n - 1) + fib(n - 2);
   }

   public static void main(String[] args)
   {
      System.out.println("start");
      {
         calls = 0;
         for (int i = 0; i < 20; i++)
            System.out.println("i=" + i + " " + fib(i) + " has " + calls);
      }
   }

}
