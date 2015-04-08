package hw3.ex5_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptingReader extends BufferedReader implements Cipher
{

   public DecryptingReader(Reader in)
   {
      super(in);
   }

   public String readLine() throws IOException
   {
      return cipher(super.readLine());
   }

   public String cipher(String s)
   {
      char current;
      char[] decrypt = new char[s.length()];

      for (int i = 0; i < s.length(); i++)
      {
         current = s.charAt(i);
         current -= shift;
         decrypt[i] = current;
      }

      return new String(decrypt);
   }

}
