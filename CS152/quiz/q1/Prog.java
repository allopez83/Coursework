/**
 *   This question uses Java.
 *
 *   You are given an array of numbers and a separator string.
 *   Return a string consisting of all POSITIVE numbers separated by
 *   the given string. If there are no positive elements, return "". 
 *
 *   For example, if the array contains 11 -23 58 0 12
 *   and the separator is ", ", you return the string "11, 58, 12".
 */

import java.util.*;

public class Prog
{
   public static void main(String[] args) {
      int[] input = {-1, -2};
      String s = ", ";
      System.out.println(posToString(input, s));
   }

   public static String posToString(int[] numbers, String separator) {
      // Positive numbers
      ArrayList<Integer> pos = new ArrayList<Integer>();
      for (int i : numbers)
         if (i > 0) 
            pos.add(i);

      // To string
      String result = "";
      if (pos.size() > 0) {
         for (int i : pos)
            result += i + separator;
         result = result.substring(0, result.length() - separator.length());
      }


      return result;
   }
}
