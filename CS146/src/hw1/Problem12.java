package hw1;

/**
 * 
 * @author Hansen Wu
 * @course CS146
 * @assignment hw1
 * @problem 1.2 - Recursive method for number of 1s in binary representation of
 *          number N
 *
 */
public class Problem12
{

   public static void main(String[] args)
   {
      for (int i = 0; i < 100; i++)
      {
         System.out.println(i + " " + Integer.toBinaryString(i) + " has "
               + binaryOnes(i));
      }
   }

   /**
    * Determines the quantity of 1s in the binary representation of a number
    * @param n find 1s in this number's binary form
    * @return quantity of 1s in number n
    */
   private static int binaryOnes(int n)
   {
      if (n == 1 || n == 0)
         return n;
      else if (n % 2 == 0)
         return binaryOnes(n / 2); // even
      else
         return binaryOnes(n / 2) + 1; // odd
   }
}
