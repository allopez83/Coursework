package cs_1c;

import java.io.*;
import java.util.*;

//class iTunesEntryReader -----------------------------------------------------
public class iTunesEntryReader
{
   ArrayList<iTunesEntry> tunes = new ArrayList<iTunesEntry>();
   private int num_tunes;
   private boolean file_open_error;
   private String tune_file;
   
   // helper
   private boolean readOneEntry(BufferedReader infile, iTunesEntry tune)
   {
      String file_title, file_artist, file_time;
      int n_time;

      try
      {
      if ( infile.ready() )
         file_artist = infile.readLine();
      else
         return false;

      if ( infile.ready() )
         file_title = infile.readLine();
      else
         return false;

      if ( infile.ready() )
         file_time = infile.readLine();
      else
         return false;
      }
      catch (IOException e)
      {
         return false;
      }
      
      // convert string to int
      try
      {
         n_time = Integer.parseInt(file_time);
      }
      catch (NumberFormatException e)
      {
         return false;
      }

      tune.setSArtist(file_artist);
      tune.setSTitle(file_title);
      tune.setNTime(n_time);

      return true;
   }
   
   // helper
   private boolean isDataLine(String line)
   {
      if (line.length() < 1)
         return false;  
      if (line.equals("#") )
         return true;
      return false;    
   }
   
   // constructor
   public iTunesEntryReader(String file_name)
   {
      iTunesEntry tune;
      BufferedReader in_file;
      String line;

      num_tunes = 0;
      file_open_error = false;
      tune_file = "NO FILE NAME PROVIDED";

      if (file_name.length() == 0)
      {
         file_open_error = true;
         return;
      }
      tune_file = file_name;

      // open file for reading
      try
      {  
         // ------- open and read the file
         in_file = new BufferedReader( 
            new FileReader(file_name) );
         
         while ( in_file.ready() )
         {
            line = in_file.readLine();
            if (isDataLine(line))
            {
               tune = new iTunesEntry();  // we allocate a new tune
               if ( !readOneEntry(in_file, tune) )
               {
                  file_open_error = true;
                  break;
               }
               tunes.add(tune);
               num_tunes++;
            }
         }
         in_file.close();
      }
      catch( FileNotFoundException e)
      {
         file_open_error = true;
      } 
      catch( IOException e)
      {
         file_open_error = true;
      }       
   }
   
   // accessors
   public iTunesEntry getTune(int k)
   {
      if (k < 0 || k >= num_tunes)
         return new iTunesEntry();  // dummy return
      return tunes.get(k);
   }
   
   public String getFileName() { return tune_file; }
   public boolean readError() { return file_open_error; }
   public int getNumTunes() { return num_tunes; }

}
