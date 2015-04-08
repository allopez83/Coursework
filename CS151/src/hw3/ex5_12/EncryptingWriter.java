package hw3.ex5_12;

import java.io.OutputStream;
import java.io.PrintWriter;

public class EncryptingWriter extends PrintWriter implements Cipher
{
   public EncryptingWriter(OutputStream out)
   {
      super(out);
   }

   public void println(String s)
   {
      System.out.println(cipher(s));
   }

   public String cipher(String s)
   {
      char current;
      char[] encrypt = new char[s.length()];

      for (int i = 0; i < s.length(); i++)
      {
         current = s.charAt(i);
         current += shift;
         encrypt[i] = current;
      }

      String result = new String(encrypt);
      return result;
   }

}
