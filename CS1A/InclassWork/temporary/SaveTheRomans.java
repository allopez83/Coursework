package temporary;

import bulletproof.*;

/**
 * A program that will convert an integer into it's roman numeral counterpart.
 * @author Giordan Clutario
 * @version 11/6/2012
 * A-MMMCMXCIX
 * B-3999
 * C-3=III 4=IV 7=VII 19=IX 88=LXXXVIII 499=CDXCIX 3988=MMMCMLXXXVIII
 */
public class SaveTheRomans
{
   public static void main(String[] args)
   {
      BPScanner keyboard = new BPScanner();
      while (true)
      {
         int input = keyboard
               .getIntegerFromUser(
                     "Type an integer ranging from 1 to 3999. Enter 0 to quit the program.\n",
                     0, 3999);
         if (input == 0)
            break;
         int con = integerToRoman(input);
         String roman = Integer.toString(input);
         System.out.println("The roman value is: " + roman);
      }
      System.out.println("Bye!");
   }

   public static int integerToRoman(int num)
   {
      String numtoString = Integer.toString(num);
      String romnum = new String();
      String[] romanNums =
      { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
      int[] arabicNums =
      { 1000, 900, 500,  400, 100,   90,  50,   40,  10,    9,   5,    4,   1 };

      for (int i = 1; i < 13; i++)
      {
         while (num >= arabicNums[i])
         {
            num -= arabicNums[i];
            romnum += romanNums[i];
         }
      }
      return Integer.parseInt(romnum);
   }
}
