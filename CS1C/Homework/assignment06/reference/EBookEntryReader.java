package assignment06.reference;

import java.io.*;
import java.util.*;

//class EBookEntryReader -----------------------------------------------------
public class EBookEntryReader
{
   ArrayList<EBookEntry> books = new ArrayList<EBookEntry>();
   private int num_books;
   private boolean file_open_error;
   private String book_file;

   // constructor
   public EBookEntryReader(String file_name)
   {
      EBookEntry book;
      BufferedReader in_file;
      String line, entry_string;

      num_books = 0;
      file_open_error = false;
      book_file = "NO FILE NAME PROVIDED";

      if (file_name.length() == 0)
      {
         file_open_error = true;
         return;
      }
      book_file = file_name;

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
               entry_string = readOneEntry(in_file, line); // expands line to entry
               if (entry_string == null)
               {
                  file_open_error = true;
                  break;
               }
               // if not an English entry, we'll have prob with chars
               if ( !entry_string.contains( ">en<" ) )
                  continue;

               book = readOneBook(entry_string);
               books.add(book);
               num_books++;
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
   public EBookEntry getBook(int k)
   {
      if (k < 0 || k >= num_books)
         return new EBookEntry();  // dummy return
      return books.get(k);
   }

   public String getFileName() { return book_file; }
   public boolean readError() { return file_open_error; }
   public int getNumBooks() { return num_books; }

   // helpers
   // reads lines from the input stream, concatenating until it finds
   // the terminating tag, "</pgterms:etext>".  Result is a single long
   // line containing entire record wrapped in 
   // "<pgterms:etext> ... </pgterms:etext>" which it returns to client.
   // assumes first line containing <pgterms:etext> is already in 
   // line parameter - required for call
   private String readOneEntry(BufferedReader infile, String line)
   {
      String full_entry = line;
      String next_line = "";
      try
      {
         while ( infile.ready() 
               && full_entry.length() < EBookEntry.MAX_ENTRY_LENGTH - 20 )
         {

            next_line = infile.readLine();
            full_entry += next_line;
            if ( next_line.equals("</pgterms:etext>") )
               break;
         }
      }
      catch (IOException e)
      {
         return null;
      }

      // if we have an unterminated entry, force it to be terminated.
      if ( !next_line.equals("</pgterms:etext>") )
         full_entry += "</pgterms:etext>";

      return full_entry;
   }

   // reads one string in the above record (all lines of the record
   // are assumed to be concatenated into a single line prior to 
   // this call surrounded by <pgterms:etext> ... </pgterms:etext> )
   // and leaves data in an EBookEntry object for return to client
   private EBookEntry readOneBook(String entry_string)
   {
      EBookEntry book = new EBookEntry();

      book.setNEtextNum(readIDFromLine(entry_string));
      book.setSCreator(readStringFromEntry(entry_string, "<dc:creator"));
      book.setSTitle(readStringFromEntry(entry_string, "<dc:title"));
      book.setSSubject(readStringFromEntry(entry_string, "<dc:subject"));

      return book;
   }

   // helpers
   private boolean isDataLine(String line)
   {
      if (line.length() < 15)
         return false;

      // check for a line of the form "<pgterms:etext --- "

      if ( line.substring(0,14).equals("<pgterms:etext") )
         return true;
      return false;    
   }

   int readIDFromLine(String line)
   {
      int start_num_pos;
      int num_length;

      start_num_pos = line.indexOf("ID=\"etext") + 9;
      num_length = line.substring(start_num_pos).indexOf( "\"");

      if (start_num_pos < 0 || start_num_pos > EBookEntry.MAX_STRING 
            || num_length < 0 || num_length > 20 )
         return 0;
      else
         return Integer.valueOf(line.substring(start_num_pos, 
               start_num_pos + num_length));
   }

   String readStringFromEntry (String entry_string, String delimiter)
   {
      int start, stop, k;
      String string_after_delimiter;

      if (delimiter.length() < 1 || entry_string.length() < delimiter.length())
         return "(no data found)";

      // first find "<dc:title", e.g.
      start = entry_string.indexOf(delimiter);
      if (start < 0)
         return "(no data found)";
      string_after_delimiter = entry_string.substring(start+delimiter.length());

      // we're looking for something like ">A ..." rather than ">< ... "
      for (k = 0; k < string_after_delimiter.length() - 1; k++)
      {
         // rather than using isLetter() we check manually to avoid foreign
         if (string_after_delimiter.charAt(k) == '>' 
            &&
            // home-made isLetter()
            (
                  (string_after_delimiter.charAt(k+1) >='a' 
                     && string_after_delimiter.charAt(k+1) <= 'z')
                                       ||
                  (string_after_delimiter.charAt(k+1) >='A' 
                     && string_after_delimiter.charAt(k+1) <= 'Z') 
            )
         )
            break;
      }
      if (k == string_after_delimiter.length() - 1)
         return "(no data found)";

      // we've got the starting position for the raw data
      start = k + 1;
      string_after_delimiter = string_after_delimiter.substring(start); // cut tags
      stop = string_after_delimiter.indexOf("<");   // by above condition, cannot be 0

      return string_after_delimiter.substring(0, stop);
   }
}
