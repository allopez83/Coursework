package assignment01.export;

import cs_1c.*;
import java.text.*;
import java.util.*;

/**
 * CS1C, week 1, Part B
 * 
 * @author Hansen Wu
 * 
 */
public class SubsetSumB
{
   public static void main(String[] args) throws Exception
   {
      boolean perfectFound = false;
      final int limit = 2700;
      final ArrayList<iTunesEntry> masterList = new ArrayList<iTunesEntry>();
      ArrayList<Sublist> collection = new ArrayList<Sublist>();
      Sublist list = new Sublist(masterList), perfectSublist = null;

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
      collection.add(new Sublist(masterList)); // this represents the 0 set

      collection.add(list);
      // Go through all 10 numbers in the master set
      for (int i = 0; i < masterList.size() && !perfectFound; i++)
      {
         int collectionSize = collection.size();
         // Go through all Sublists 'list' in 'collection'
         for (int k = 0; k < collectionSize && !perfectFound; k++)
         {
            list = collection.get(k);
            double sum = sum(list, masterList.get(i));
            if (sum <= limit)
            {
               try
               {
                  if (sum == limit)
                  {
                     perfectSublist = list.addItem(i);
                     perfectFound = true;
                  }
                  if (sum < limit)
                  {
                     collection.add(list.addItem(i));
                  }
               } catch (CloneNotSupportedException e)
               {
                  e.printStackTrace();
               }
            }
         }
      }
      // Find the largest Sublist, assign it to 'list'
      if (perfectSublist == null)
         list = largestSum(collection);
      else
         list = perfectSublist;
      System.out.println("iTunesEntry SubsetSum\n" + "Max time limit: " + limit
            + "\nTotal time of songs: " + list.getSum());

      // End the timer
      end_time = System.nanoTime();
      list.showSublist();

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
   private static double sum(Sublist sublist, iTunesEntry newElement)
   {
      double total = 0;
      total += sublist.getSum();
      total += newElement.getNTime();
      return total;
   }

   /**
    * Searches for the Sublist with the largest sum in a provided ArrayList
    * @param arrayList ArrayList that will be searched
    * @return Sublist that has the largest sum
    */
   private static Sublist largestSum(ArrayList<Sublist> arrayList)
   {
      Sublist largestSublist = arrayList.get(0);
      // Go through all Sublists
      for (int k = 0; k < arrayList.size(); k++)
      {
         // Replace current largestSublist is a larger one is found
         if (largestSublist.getSum() < arrayList.get(k).getSum())
            largestSublist = arrayList.get(k);
      }
      return largestSublist;
   }

   static class Sublist implements Cloneable
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
      int getSum()
      {
         return sum;
      }

      /**
       * Creates a copy of the Sublist
       * @return copy of Sublist
       */
      @SuppressWarnings("unchecked")
      public Object clone() throws CloneNotSupportedException
      {
         // Shallow copy
         Sublist new_object = (Sublist) super.clone();
         // Deep copy
         new_object.indices = (ArrayList<Integer>) indices.clone();
         return new_object;
      }

      /**
       * Adds a new element to the Sublist
       * @param index element to be added to the Sublist
       * @return the Sublist with added element
       * @throws CloneNotSupportedException
       */
      Sublist addItem(int index) throws CloneNotSupportedException
      {
         Sublist newList = (Sublist) this.clone();
         newList.sum += originalObjects.get(index).getNTime();
         newList.indices.add(index);
         return newList;
      }

      /**
       * Prints the Sublist to the console
       */
      void showSublist()
      {
         for (int k = 0; k < indices.size(); k++)
         {
            // Retrieve index value, then use it to retrieve the element
            System.out.println("[" + k + "] : "
                  + originalObjects.get(indices.get(k)));
         }
      }
   }
}

/* START OF OUTPUT --------------------------------
iTunesEntry SubsetSum
Max time limit: 2700
Total time of songs: 2700
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
Algorithm Elapsed Time: 0.1118 seconds.

END OF OUTPUT -------------------------------- */