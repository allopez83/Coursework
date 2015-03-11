package cs1c_0416week2;

// Main file for iTunes project.  See Read Me file for details
// CS 1C, Foothill College, Michael Loceff, creator

import cs_1c.*;
import java.util.*;
import java.text.*;


//------------------------------------------------------
public class iTunesReader
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      // how we read the data from files
      iTunesEntryReader tunes_input = new iTunesEntryReader("itunes_file.txt");
      int array_size;

      // how we test the success of the read:
      if (tunes_input.readError())
      {
         System.out.println("couldn't open " + tunes_input.getFileName()
            + " for input.");
         return;
      }

      System.out.println(tunes_input.getFileName());
      System.out.println(tunes_input.getNumTunes());

      // create an array of objects for our own use:
      array_size = tunes_input.getNumTunes();
      iTunesEntry[] tunes_array = new iTunesEntry[array_size];
      for (int k = 0; k < array_size; k++)
         tunes_array[k] = tunes_input.getTune(k);

      // how we time our algorithms -------------------------
      Date start_time, end_time;
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      
      // show the array, unsorted
      for (int k = 0; k < array_size; k++)
         DisplayOneTune(tunes_array[k]);
      System.out.println();
      
      //get start time
      start_time = new Date();
      
      // sort
      iTunesEntry.setNSortType(iTunesEntry.SORT_BY_TIME);
      FoothillSort.<iTunesEntry>ArraySort(tunes_array);

      // do something interesting like search or sort or build a hash-table, then...

      // how we determine the time elapsed -------------------
      end_time = new Date();

      // show the sorted list
      for (int k = 0; k < array_size; k++)
         DisplayOneTune(tunes_array[k]);
      System.out.println();

      // report algorithm time
      System.out.println("\nAlgorithm Elapsed Time: "
         + tidy.format((end_time.getTime() - start_time.getTime()) / 1000.)
         + " seconds.\n");
   }

   static void DisplayOneTune(iTunesEntry tune)
   {
      System.out.println(tune.getSArtist() + " | "
         + tune.getSTitle() + " | "
         // cout << tune.GetNTime() << " | "; 
         + " " + tune.convertTimeToString());
   }
}