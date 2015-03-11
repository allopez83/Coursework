package cs_1c;

//class EBookEntry -----------------------------------------------------
public class EBookEntry implements Comparable<EBookEntry>
{
   private String s_title, s_creator, s_subject;
   private int n_etext_num;

   public static final int MIN_STRING = 1;
   public static final int MAX_STRING = 300;
   public static final int MAX_ENTRY_LENGTH = 10000;
   public static final int MAX_ID = 100000;

   // comparable tools
   public static final int SORT_BY_TITLE = 0;
   public static final int SORT_BY_CREATOR = 1;
   public static final int SORT_BY_SUBJECT = 2;
   public static final int SORT_BY_ID = 3;

   private static int n_sort_key = SORT_BY_CREATOR; 

   // default constructor
   EBookEntry()
   {
      s_title = "";
      s_creator = "";
      s_subject = "";
      n_etext_num = 0;
   }

   // accessors
   public String getSTitle()  { return s_title; }
   public String getSCreator()  { return s_creator; }
   public String getSSubject()  { return s_subject; }
   public int getNEtextNum()  { return n_etext_num; }   

   // mutators
   public boolean setSTitle(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_title = s_arg;
      return true;
   }
   public boolean setSCreator(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_creator = s_arg;
      return true;
   }
   public boolean setSSubject(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_subject = s_arg;
      return true;
   }
   public boolean setNEtextNum(int n_arg)
   {
      if (n_arg < 1 || n_arg > MAX_ID)
         return false;
      n_etext_num = n_arg;
      return true;
   }

   public static boolean setNSortType( int which_type )
   {
      switch (which_type)
      {
      case SORT_BY_TITLE:
      case SORT_BY_CREATOR:
      case SORT_BY_SUBJECT:
      case SORT_BY_ID:
         n_sort_key = which_type;
         return true;
      default:
         return false;
      }
   }

   public int compareTo(EBookEntry other)
   {
      switch (n_sort_key)
      {
      case SORT_BY_TITLE:
         return (s_title.compareToIgnoreCase(other.s_title));
      case SORT_BY_CREATOR:
         return (s_creator.compareToIgnoreCase(other.s_creator));
      case SORT_BY_SUBJECT:
         return (s_subject.compareToIgnoreCase(other.s_subject));

      case SORT_BY_ID:
         return (n_etext_num - other.n_etext_num);
      default:
         return 0;
      }
   }

   public String toString()
   {
      return "   # " + n_etext_num + "  ---------------\n"
      + "   \"" + s_title + "\"\n"
      + "   by " + s_creator  + "\n"
      + "   re: " + s_subject + "\n\n";
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