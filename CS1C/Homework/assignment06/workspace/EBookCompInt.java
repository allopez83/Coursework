package assignment06.workspace;

import cs_1c.EBookEntry;

/**
 * Wrapper class for EBook objects, implements Comparable<Integer>
 * @author HW
 *
 */
public class EBookCompInt implements Comparable<Integer>
{
   EBookEntry data;

   /**
    * Create a new EBookCompInt object containing an EBookEntry object
    * @param input EBookEntry object to be contained inside
    */
   public EBookCompInt(EBookEntry input)
   {
      data = input;
   }

   /**
    * Returns a string representation of the EBookEntry contained inside
    */
   public String toString()
   {
      return data.toString();
   }

   /**
    * Compares a provided number with the EBookEntry's text number
    */
   public int compareTo(Integer key)
   {
      return data.getNEtextNum() - key;
   }

   /**
    * Checks if this EBookCompInt is equal to the provided EBookCompInt
    * @param input EBookCompInt to be checked for equality
    * @return true or false if it is equal or not equal, respectively
    */
   public boolean equals(EBookCompInt input)
   {
      return data.equals(input.data);
   }

   /**
    * Get a hash code for the EBookEntry using its text number
    */
   public int hashCode()
   {
      return data.getNEtextNum();
   }
}
