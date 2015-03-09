package assignment01.exercises;

public class ListLimit
{
   private static final int target = 37;
   private static final int[] S =
   { 5, 14, 17, 9, 7, 4, 11, 18, 13, 6 };

   private static int[][] Col = new int[100][];
   private static int[] Sum = new int[100];

   public static void main(String[] args)
   {
      // Initialize array to hold arrays determined to be valid
      Col[0] = new int[1];
      // Sublist that will contain elements to be added
      int[] L = new int[1];

      // Go through all 10 numbers in list
      for (int i = 0; i < S.length; i++)
      {
         // System.out.println("S location: " + i);
         int[][] newCol = Col.clone();

         // Go through all sublist 'L' in 'Col'
         for (int k = 0; Col[k] != null; k++)
         {
            // System.out.println("Col location: " + k);
            L = Col[k];
            int x = S[i];
            int sum = sum(L, x);
            // System.out.println("Sum of operation: " + sum);
            if (sum <= target)
            {
               int placement = nextNull(newCol);
               L = addToEnd(L, x);
               newCol[placement] = L;
               Sum[placement] = sum;
            }
            if (sum == target)
            {
               break;
            }
         }
         Col = newCol;
      }

      L = largestSum(Col);
      System.out.println(arrayToString(L));
   }

   private static String arrayToString(int[] array)
   {
      String result = "{ ";
      for (int k = 0; k < array.length; k++)
      {
         result += array[k] + " ";
      }
      result += "}";
      return result;
   }

   private static int[] largestSum(int[][] array)
   {
      int[] largestArray = null;
      int largestValue = 0;
      for (int k = 0; k < Sum.length; k++)
      {
         if (largestValue < Sum[k])
         {
            largestArray = Col[k].clone();
            largestValue = Sum[k];
         }
      }
      System.out.println("Largest value found: " + largestValue);
      return largestArray;
   }

   /**
    * Extends a given integer array and adds a new integer to the end of it
    * @param array integer array that will be extended
    * @param newElement integer to be added
    * @return the array after being extended
    */
   private static int[] addToEnd(int[] array, int newElement)
   {
      int[] newArray = new int[array.length + 1];
      for (int k = 0; k < array.length; k++)
      {
         newArray[k] = array[k];
      }
      // System.out.println("old length: " + array.length+
      // " new length: " + newArray.length);
      newArray[newArray.length - 1] = newElement;
      return newArray;
   }

   /**
    * Finds the last place of an array holding a valid entry, stops at the FIRST
    * null entry
    * 
    * @param array array that will be studied
    * @return integer location of the first null entry
    */
   private static int nextNull(int[][] array)
   {
      int location = 0;
      for (int k = 0; array[k] != null; k++)
      {
         location++;
      }
      // System.out.println("Found next null at: " + location);
      return location;
   }

   /**
    * Adds up the elements of an array and a new element
    * 
    * @param array an array containing integers to be added
    * @param newElement integer that will also be added
    * @return integer representing the total value
    */
   private static int sum(int[] array, int newElement)
   {
      int total = 0;
      // Go through all elements of the array
      for (int k = 0; k < array.length; k++)
      {
         total += array[k];
      }
      total += newElement;
      return total;
   }
}