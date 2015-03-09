package assignment06.workspace;

import java.text.NumberFormat;
import java.util.Locale;

import cs_1c.*;

public class Tester
{
   /*
    * User-input parameter(s), please follow the instructions or the test
    * program might crash/fail/blow up the world. Thank you!
    */
   // None!

   /*
    * The "do not change" parameters. These can actually be changed, but they're
    * not exactly intended to be changed.
    */
   // TODO delete timer stuff
   // Stuff for the timer
   private static NumberFormat tidy = NumberFormat.getInstance(Locale.US);
   private static long start_time, end_time;
   
   /**
    * This is simply used to shorten the System.out.println() call. Note that
    * the parameter needs to be a String
    * @param text String to print to console
    */
   private static void print(String text)
   {
      System.out.println(text);
   }

   // TODO delete timer stuff
   /**
    * Timer stuffs
    */
   private static void timerResult()
   {
      System.out.println(tidy.format((end_time - start_time) / 1e9) + " seconds");
   }

   public static void main(String[] args)
   {
      // For the timer
      tidy.setMaximumFractionDigits(4);

      // Add and remove comments for tests that you wish to conduct
//      hashTableIntegerTest();
//      hashTableTests();
      hashTableInteger();
//      hashTableString();
   }
   
   // Test Algorithms ---------------------------------------------------------
   
   /**
    * Tests HashQPwFind for EBookCompInt objects
    */
   private static void hashTableInteger()
   {
      print("EBook Test: EBookCompInt");
      EBookEntryReader eBookReader = new EBookEntryReader("catalog-short4.txt");
      // Make sure there won't be any errors
      if (!eBookReader.readError())
      {
         HashQPwFind<Integer, EBookCompInt> hash_table = new HashQPwFind<Integer, EBookCompInt>();
         int total = eBookReader.getNumBooks();
         EBookEntry book;
         for (int k = 0; k < total; k++)
         {
            book = eBookReader.getBook(k);
            hash_table.insert(new EBookCompInt(book));
         }

         int tableSize = hash_table.size();
         print("Searching for EBooks");
         for (int k = 0; k < 25; k++)
         {
            // Size of entire hash table is ~12,000 so 13,000 will be used
            int randomKey = (int) (Math.random() * 13000);
            try
            {
               EBookCompInt randomEntry = hash_table.find(randomKey);
               print(randomEntry.toString());
            } catch (Exception e)
            {
               print("not found");
            }

         }

         print("\nSearching for indexes not on hash table");
         try
         {
            EBookCompInt randomEntry = hash_table.find(50000);
            print(randomEntry.toString());
         } catch (Exception e)
         {
            print("not found");
         }
         try
         {
            EBookCompInt randomEntry = hash_table.find(987654321);
            print(randomEntry.toString());
         } catch (Exception e)
         {
            print("not found");
         }
      }
   }
   
   private static void hashTableString()
   {
      print("EBook Test: EBookCompString");
      EBookEntryReader eBookReader = new EBookEntryReader("catalog-short4.txt");
      // Make sure there won't be any errors
      if (!eBookReader.readError())
      {
         // Adding books
         HashQPwFind<String, EBookCompString> hash_table = new HashQPwFind<String, EBookCompString>();
         int total = eBookReader.getNumBooks();
         EBookEntry book;
         for (int k = 0; k < total; k++)
         {
            book = eBookReader.getBook(k);
            hash_table.insert(new EBookCompString(book));
         }

         // Search test
         print("Searching for EBooks");
         for (int k = 0; k < 25; k++)
         {
            int randomKey = (int) (Math.random() * 250);
            try
            {
//               print(hash_table.myhashKey(randomKey + "")+"");
               EBookCompString randomEntry = hash_table.find(randomKey + "");
               print(randomEntry.toString());
            } catch (Exception e)
            {
               print("not found");
            }

         }

         // Searching for keys not on table
         print("\nSearching for indexes not on hash table");
         try
         {
            EBookCompString randomEntry = hash_table.find("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            print(randomEntry.toString());
         } catch (Exception e)
         {
            print("not found");
         }
         try
         {
            EBookCompString randomEntry = hash_table.find("abcdefghijklmnopqrstuvwxyz");
            print(randomEntry.toString());
         } catch (Exception e)
         {
            print("not found");
         }
      }
   }

   /**
    * Tests HashQPwFind, not fit to turn in
    */
   private static void hashTableTests()
   {
      print("EBook Test: EBookCompInt");
      EBookEntryReader eBookReader = new EBookEntryReader("catalog-short4.txt");
      // Make sure there won't be any errors
      if (!eBookReader.readError())
      {
         print("Successfully read the ebooks");
         HashQPwFind<Integer, EBookCompInt> hash_table =
               new HashQPwFind<Integer, EBookCompInt>();
         int total = eBookReader.getNumBooks();
         EBookEntry book;

         for (int k = 0; k < total; k++)
         {
            book = eBookReader.getBook(k);
            hash_table.insert(new EBookCompInt(book));
         }

         // print("Table size: " + tableSize);

         int position = hash_table.PosKey(30170);
         print("Position of 'LonesomeHearts': " + position);
         print(hash_table.find(30170) + "");

         print("Searching for EBooks");
         for (int k = 0; k < 25; k++)
         {
            // The size of the entire hash table is ~12,000 so I'll use 10,000
            int randomKey = (int) (Math.random() * 10000);
            try
            {
               EBookCompInt randomEntry = hash_table.find(randomKey);
               print(randomEntry.toString());
            } catch (Exception e)
            {
               print("not found");
            }

         }

         print("\nSearching for indexes not on hash table");
         try
         {
            EBookCompInt randomEntry = hash_table.find(50000);
            print(randomEntry.toString());
         } catch (Exception e)
         {
            print("not found");
         }
         try
         {
            EBookCompInt randomEntry = hash_table.find(987654321);
            print(randomEntry.toString());
         } catch (Exception e)
         {
            print("not found");
         }
      }
   }

   /**
    * Some familiarization tests
    */
   private static void hashTableIntegerTest()
   {
      FHhashQP<Integer> hashTest = new FHhashQP<Integer>();

      for (int k = 0; k < 30; k++)
         hashTest.insert((int) (Math.random() * 40));
      hashTest.insert(5);
      hashTest.insert(80);
      print(hashTest.contains(5) + "");
      print(hashTest.contains(97+80) + "");
      print(hashTest.contains(6) + "");
   }
}
