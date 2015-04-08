package hw3.ex5_12;

import java.io.IOException;
import java.io.InputStreamReader;

public class DecoratorTester
{

   public static void main(String[] args)
   {
      String alphabets = "abcdefghijklmnopqrstuvwxyz";

      // Encryption
      System.out.println("Encrypt:");
      EncryptingWriter ec = new EncryptingWriter(System.out);
      ec.println(alphabets);
      String encrypted = ec.cipher(alphabets);

      // Decryption
      System.out.println("\nDecrypt:");
      DecryptingReader dr = new DecryptingReader(new InputStreamReader(
            System.in));
      System.out.println(dr.cipher(encrypted));
      try
      {
         System.out.println(dr.readLine());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}
