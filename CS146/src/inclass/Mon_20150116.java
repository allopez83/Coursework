package inclass;

import java.util.ArrayList;

/**
 * Dependency Example
 * 
 * @author Hansen Wu
 *
 */
public class Mon_20150116
{
   class Business {}
   class Hotel extends Business {}
   class Inn extends Hotel {}
   
   public static void main(String[] args)
   {
      System.out.println("fdsa");
      ArrayList<Hotel> al = new ArrayList<Hotel>();
      // If new ArrayList<Hotel> is changed to Business or Inn, it will not be
      // compiled. Sub/Superclasses will not be accepted.
   }
}
