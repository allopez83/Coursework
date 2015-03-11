package assignment06.workspace;

import java.util.NoSuchElementException;
import cs_1c.*;

/**
 * Hash table with find method
 * @author HW
 *
 * @param <KeyType> data type that will be used with the find() method
 * @param <E> data type that will be stored in the hash table
 */
public class HashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E>
{
   /**
    * Search for an entry in the hash table using the given key
    * @param key data to use in searching for an element
    * @return the element if found, throw NoSuchElementException if not found
    */
   public E find(KeyType key)
   {
      HashEntry<E> hashEntry;
      int index;

      index = PosKey(key);
      hashEntry = super.m_array[index];

      if (hashEntry.state != ACTIVE)
         throw new NoSuchElementException();

      return hashEntry.data;
   }
   
   /**
    * Generate a hash key using the given data
    * @param key data to create hash key from
    * @return the created hash key
    */
   protected int myhashKey(KeyType key)
   {
      int hash_val;

      // Generate the hash key
      hash_val = key.hashCode() % m_table_size;
      if (hash_val < 0)
         hash_val += m_table_size;

      return hash_val;
   }
   
   /**
    * Find the position of an element by it's key
    * @param key key to use in searching for the element
    * @return the position inside the hash table of the found element
    */
   protected int PosKey(KeyType key)
   {
      int kth_odd_num = 1;
      // Get hash key for the data
      int index = myhashKey(key);

      // If that location is occupied, and the data there is not x
      while (m_array[index].state != EMPTY && m_array[index].data.compareTo(key) != 0)
      {
         // Simulate adding a squared number each time
         index += kth_odd_num; // k squared = (k-1) squared + kth odd #
         kth_odd_num += 2; // compute next odd #
         // If it gets to the end of the table, have it loop around
         if (index >= m_table_size)
            index -= m_table_size;
      }
      // If program gets here, an acceptable index has been found, return it
      return index;
   }
}