package assignment01.workspace;

import cs_1c.*;
import java.text.*;
import java.util.*;

/**
 * CS1C, week 1, Part C
 * 
 * @author Hansen Wu
 * 
 */
public class SubsetSumC
{
   public static void main(String[] args) throws Exception
   {
      final int limit = 2700;
      ArrayList<iTunesEntry> masterList = new ArrayList<iTunesEntry>();
      ArrayList<Sublist<iTunesEntry>> collection =
            new ArrayList<Sublist<iTunesEntry>>();
      Sublist<iTunesEntry> list = new Sublist<iTunesEntry>(masterList),
            perfectList = null;
      boolean perfectFound = false;

      // Formatting and setting up the timer
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      long start_time, end_time;

      // Read the iTunes Data
      iTunesEntryReader tunesReader = new iTunesEntryReader("itunes_file.txt");

      // Test the success of the read:
      if (tunesReader.readError())
      {
         System.out.println("couldn't open " + tunesReader.getFileName()
               + " for input.");
         return;
      }

      // load the data_set ArrayList with the iTunes:
      int arraySize = tunesReader.getNumTunes();
      for (int i = 0; i < arraySize; i++)
         masterList.add(tunesReader.getTune(i));

      collection.clear();

      // Start the timer
      start_time = System.nanoTime();
      // This represents the 0 set
      collection.add(new Sublist<iTunesEntry>(masterList));

      collection.add(list);
      // Go through all 10 numbers in the master set
      for (int i = 0; i < masterList.size() && !perfectFound; i++)
      {
         int collectionSize = collection.size();
         // Go through all Sublists 'list' in 'collection'
         for (int k = 0; k < collectionSize && !perfectFound; k++)
         {
            list = collection.get(k);
            int songLength = masterList.get(i).getNTime();
            double sum = sum(list, songLength);
            if (sum <= limit)
            {
               try
               {
                  if (sum == limit)
                  {
                     perfectList = list.addItem(i, songLength);
                     perfectFound = true;
                  }
                  if (sum < limit)
                  {
                     collection.add(list.addItem(i, songLength));
                  }
               } catch (CloneNotSupportedException e)
               {
                  e.printStackTrace();
               }
            }
         }
      }
      // Find the largest Sublist, assign it to 'list'
      if (perfectList == null)
         list = largestSum(collection);
      else
         list = perfectList;
      System.out.println("iTunes SubsetSum\n" + "Max time limit: " + limit
            + "\nSum: " + list.getSum());

      // End the timer
      end_time = System.nanoTime();
      list.showList();

      // Report algorithm time
      System.out.println("Algorithm Elapsed Time: "
            + tidy.format((end_time - start_time) / 1e9) + " seconds.");
   }

   /**
    * Adds up the elements of a Sublist along with a new element
    * @param Sublist a Sublist containing numbers to add together
    * @param newElement double that will also be added
    * @return double representing the total value
    */
   private static double sum(Sublist<iTunesEntry> sublist, int songLength)
   {
      double total = 0;
      total += sublist.getSum();
      total += songLength;
      return total;
   }

   /**
    * Searches for the Sublist with the largest sum in a provided ArrayList
    * @param arrayList ArrayList that will be searched
    * @return Sublist that has the largest sum
    */
   private static Sublist<iTunesEntry> largestSum(
         ArrayList<Sublist<iTunesEntry>> arrayList)
   {
      Sublist<iTunesEntry> largestSublist = arrayList.get(0);
      // Go through all Sublists
      for (int k = 0; k < arrayList.size(); k++)
      {
         // Replace current largestSublist is a larger one is found 
         if (largestSublist.getSum() < arrayList.get(k).getSum())
         {
            largestSublist = arrayList.get(k);
         }
      }
      return largestSublist;
   }

   @SuppressWarnings("hiding")
   static class Sublist<iTunesEntry> implements Cloneable
   {
      // Total of all elements stored inside
      private int sum = 0;
      // Copy of masterList
      private ArrayList<iTunesEntry> originalObjects;
      // List of indices that work
      private ArrayList<Integer> indices;

      /**
       * Constructor for Sublist objects
       * @param dataSet master ArrayList of all elements to be considered
       */
      public Sublist(ArrayList<iTunesEntry> dataSet)
      {
         sum = 0;
         originalObjects = dataSet;
         indices = new ArrayList<Integer>();
      }

      /**
       * Retrieves the sum of all elements referred to in the Sublist
       * @return sum of all elements referred to in Sublist
       */
      double getSum()
      {
         return sum;
      }

      /**
       * Creates a deep copy of the Sublist
       * @return deep copy of Sublist
       */
      @SuppressWarnings("unchecked")
      public Object clone() throws CloneNotSupportedException
      {
         // Shallow copy
         Sublist<iTunesEntry> new_object = (Sublist<iTunesEntry>) super.clone();
         // Deep copy
         new_object.indices = (ArrayList<Integer>) indices.clone();
         return new_object;
      }

      /**
       * Adds a new element to the Sublist
       * @param index element to be added to the Sublist
       * @param songLength length of the element
       * @return the Sublist with added element
       * @throws CloneNotSupportedException
       */
      @SuppressWarnings("unchecked")
      Sublist<iTunesEntry> addItem(int index, int songLength)
            throws CloneNotSupportedException
      {
         Sublist<iTunesEntry> newList = (Sublist<iTunesEntry>) this.clone();
         newList.sum += songLength;
         newList.indices.add(index);
         return newList;
      }

      /**
       * Prints the Sublist to the console
       */
      void showList()
      {
         for (int k = 0; k < indices.size(); k++)
         {
            System.out.println("[" + k + "] : "
                  + originalObjects.get(indices.get(k)));
         }
      }
   }
}

/* PASTE FROM CONSOLE --------------------------------
iTunes SubsetSum
Max time limit: 2700
Sum: 2700.0
[0] : Carrie Underwood | Cowboy Casanova |  3:56
[1] : Rihanna | Russian Roulette |  3:48
[2] : Foo Fighters | All My Life |  4:23
[3] : Eric Clapton | Pretending |  4:43
[4] : Howlin' Wolf | Everybody's In The Mood |  2:58
[5] : Howlin' Wolf | Well That's All Right |  2:55
[6] : Reverend Gary Davis | Samson and Delilah |  3:36
[7] : Reverend Gary Davis | Twelve Sticks |  3:14
[8] : Roy Buchanan | Hot Cha |  3:28
[9] : Roy Buchanan | Green Onions |  7:23
[10] : Janiva Magness | You Were Never Mine |  4:36
Algorithm Elapsed Time: 0.0814 seconds.

*/