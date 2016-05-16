package cs_1c;

//class iTunesEntry -----------------------------------------------------
public class iTunesEntry implements Comparable<iTunesEntry>
{
   private String s_title, s_artist;
   private int n_time;

   public static final int MIN_STRING = 1;
   public static final int MAX_STRING = 300;
   public static final int MAX_TIME = 10000000;
   
   // comparable tools
   public static final int SORT_BY_TITLE = 0;
   public static final int SORT_BY_ARTIST = 1;
   public static final int SORT_BY_TIME = 2;

   private static int n_sort_key; 
   
   // default constructor
   iTunesEntry()
   {
      s_title = "";
      s_artist = "";
      n_time = 0;
   }
   
   // accessors
   public String getSTitle()  { return s_title; }
   public String getSArtist()  { return s_artist; }
   public int getNTime()  { return n_time; }   
   
   // mutators
   public boolean setSTitle(String s_arg)
   {
      if ( !validString(s_arg) )
         return false; 
      s_title = s_arg;
      return true;
   }
   
   public boolean setSArtist(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_artist = s_arg;
      return true;
   }
   public boolean setNTime(int n_arg)
   {
      if (n_arg < 0 || n_arg > MAX_TIME)
         return false;
      n_time = n_arg;
      return true;
   }
   
   public String getArtistLastName()
   {
      // search for first blank from end of string
      // assume no trailing spaces
      int k, length;

      length = s_artist.length();
      if ( length < 1 )
         return "";

      for (k = length-1; k >= 0; k--)
      {
         if (s_artist.charAt(k) == ' ')
            break;
      }

      if (k >= length-1 )
         return "";

      return s_artist.substring(k + 1, s_artist.length()-1);
   }
   
   private static int convertStringToSeconds(String s_to_cnvrt)
   {
      int colon_pos;
      int minutes, seconds;

      if (s_to_cnvrt.length() < 1)
         return 0;
      colon_pos = s_to_cnvrt.indexOf(":");
      if (colon_pos < 0 || colon_pos > iTunesEntry.MAX_STRING)
         return 0;

      try
      {
         minutes = Integer.parseInt( s_to_cnvrt.substring(0, colon_pos) );
         seconds = Integer.parseInt(
            s_to_cnvrt.substring(colon_pos + 1, 
                  s_to_cnvrt.length()-1) );
      }
      catch (NumberFormatException e)
      {
         return 0;
      }

      return minutes*60 + seconds;
   }
   
   public String convertTimeToString() 
   {
      int minutes, seconds;
      String s_seconds, s_minutes;

      minutes = n_time / 60;
      seconds = n_time % 60;

      s_minutes = "" + minutes;
      s_seconds = "" + seconds;

      if (s_seconds.length() < 2)
         s_seconds = "0" + s_seconds;

      return s_minutes + ":" + s_seconds;
   }

  
   public static boolean setNSortType( int which_type )
   {
      switch (which_type)
      {
      case SORT_BY_TITLE:
      case SORT_BY_ARTIST:
      case SORT_BY_TIME:
         n_sort_key = which_type;
         return true;
      default:
         return false;
      }
   }
   
  // we allow this to be defined by one field, not full identity - a choice
  public int compareTo(iTunesEntry other)
   {
      switch (n_sort_key)
      {
      case SORT_BY_TITLE:
         return (s_title.compareToIgnoreCase(other.s_title));
      case SORT_BY_ARTIST:
         // get last name from string
         // stack the last name before the first - no worries about trailing last
         return (getArtistLastName() + s_artist).compareToIgnoreCase(
            other.getArtistLastName() + other.s_artist );

      case SORT_BY_TIME:
         return (n_time - other.n_time);
      default:
         return 0;
      }
   }
   
   public String toString()
   {
      return s_artist + " | "
         + s_title + " | "
         + " " + convertTimeToString();
   }
   
   // utility for testing all String mutator  args
   private static boolean validString(String s_arg)
   {
      if (s_arg == null)
         return false;
      if (s_arg.length() < MIN_STRING || s_arg.length() > MAX_STRING)
         return false;
      return true;
   }
}