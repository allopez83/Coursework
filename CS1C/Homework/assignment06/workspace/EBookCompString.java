package assignment06.workspace;

import cs_1c.EBookEntry;

/**
 * Wrapper class for EBook objects, implements Comparable<String>
 * @author HW
 * 
 */
public class EBookCompString implements Comparable<String>
{
   EBookEntry data;

   /**
    * Create a new EBookCompString object containing an EBookEntry object
    * @param input EBookEntry object to be contained inside
    */
   public EBookCompString(EBookEntry input)
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
   public int compareTo(String key)
   {
      return data.getSCreator().compareTo(key);
   }

   /**
    * Checks if this EBookCompString is equal to the provided EBookCompString
    * @param input EBookCompString that will be checked for equality
    * @return true or false if it is equal or not equal, respectively
    */
   public boolean equals(EBookCompString input)
   {
      return data.equals(input.data);
   }

   /**
    * Get a hash code for the EBookEntry using its text number
    */
   public int hashCode()
   {
      return data.getSCreator().hashCode();
   }
}
