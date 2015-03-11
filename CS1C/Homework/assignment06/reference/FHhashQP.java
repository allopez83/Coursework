package assignment06.reference;
import java.util.*;

// FHhashQP class --------------------------------------------
public class FHhashQP<E>
{
   protected static final int ACTIVE = 0;
   protected static final int EMPTY = 1;
   protected static final int DELETED = 2;

   static final int INIT_TABLE_SIZE = 97;
   static final double INIT_MAX_LAMBDA = 0.49;

   protected HashEntry<E>[] m_array;
   protected int m_size;
   protected int m_table_size;
   protected double m_max_lambda;

   // public methods ---------------------------------
   /**
    * Constructor, creates a new hash table with given size. If it's smaller
    * than the required minimum size, the given size is ignored and the required
    * size is used instead. No matter the number, it's first checked what the
    * next prime number is, and it'll be used instead.
    * @param table_size requested size of the table
    */
   public FHhashQP(int table_size)
   {
      int k;

      // If input size is less than minimum required size
      if (table_size < INIT_TABLE_SIZE)
         // Set size as min required size
         m_table_size = INIT_TABLE_SIZE;
      else
         // Otherwise, input size is acceptable
         m_table_size = nextPrime(table_size);

      // Create the array
      allocateArray();
      // Set the max accepted capacity (lambda) of hash table
      m_max_lambda = INIT_MAX_LAMBDA;
   }

   /**
    * Create a default hash table with the default size
    */
   public FHhashQP()
   {
      this(INIT_TABLE_SIZE);
   }
   
   /**
    * Inserts some data into the hash table.
    * @param x the data to be inserted
    * @return true if successful, false if failed
    */
   public boolean insert(E x)
   {
      // Find the index an element would go in
      int bucket = findPos(x);

      // Oops, already occupied
      if (m_array[bucket].state == ACTIVE)
         // Don't make any changes and simply return false
         return false;

      // Change the data contained in the location
      m_array[bucket].data = x;
      // Set appropriate status
      m_array[bucket].state = ACTIVE;

      // Make sure load capacity has not been breached
      if (++m_size > m_max_lambda * m_table_size)
         // If it is, rehash the table
         rehash();

      // Operation successful, return true
      return true;
   }

   /**
    * Remove a given data from the table
    * @param x the data to be removed
    * @return true if successful, false if failed
    */
   public boolean remove(E x)
   {
      // Find the index of the data
      int bucket = findPos(x);

      // If it's already marked as deleted
      if (m_array[bucket].state != ACTIVE)
         // Done here, just return false
         return false;

      // Set the status as deleted
      m_array[bucket].state = DELETED;
      // Set m_size accordingly
      m_size--;
      // Operation successful, return true
      return true;
   }

   /**
    * Check if a given data is in the table
    * @param x data to search for
    * @return true if it is there, false if it is not
    */
   public boolean contains(E x)
   {
      // Find the data and check if it is marked appropriately
      return m_array[findPos(x)].state == ACTIVE;
   }

   /**
    * Retrieve the number of elements in the hash table marked as active
    * @return the number of active elements in the hash table
    */
   public int size()  { return m_size; }

   /**
    * Applies lazy deletion to all elements of the hash table, "removing" all
    * data
    */
   void makeEmpty()
   {
      int k, size = m_array.length;

      // Go through all elements of the array
      for (k = 0; k < size; k++)
         // Mark as empty
         m_array[k].state = EMPTY;
      // Set the size as zero to show it has been emptied
      m_size = 0;
   }

   /**
    * Sets the maximum acceptable capacity of the list. It affects the number of
    * rehashes that will be done. This capacity is determined by: number of
    * elements / number of slots. For example, 50 objects in a size 100 hash
    * table would be a capacity of 0.5.
    * @param lam acceptable capacity, or lambda
    * @return true if change is successful, false if it isn't
    */
   public boolean setMaxLambda(double lam)
   {
      // If the desired size is outside specified boundaries
      if (lam < .1 || lam > INIT_MAX_LAMBDA)
         // Can't do that, return false
         return false;
      // Otherwise, set the new value
      m_max_lambda = lam;
      // Success, return true
      return true;
   }

   // protected methods of class ----------------------
   
   /**
    * Search the hash table for the location of some specified data.
    * @param x data to search for
    * @return the index the data is on
    */
   int findPos(E x)
   {
      int kth_odd_num = 1;
      // Get hashkey for the data
      int index = myhash(x);

      // If that location is occupied, and the data there is not x
      while (m_array[index].state != EMPTY && !m_array[index].data.equals(x))
      {
         // Simulate adding a number to the index and squaring it each time
         index += kth_odd_num; // k squared = (k-1) squared + kth odd #
         kth_odd_num += 2; // compute next odd #
         // If it gets to the end of the table, have it loop around
         if (index >= m_table_size)
            index -= m_table_size;
      }
      // If program gets here, an acceptable index has been found, return it
      return index;
   }
   
   /**
    * Reconstruct the hash table with a bigger size, preventing lambda from
    * being exceeded.
    */
   protected void rehash()
   {
      // Save old list and size
      HashEntry<E>[] old_array = m_array;
      int k, old_table_size = m_table_size;

      // Double the size and find the next prime after it
      m_table_size = nextPrime(2 * old_table_size);

      // Create a new array with the new size
      allocateArray(); // uses m_table_size;

      // Use the insert() algorithm to re-enter old data
      m_size = 0;
      // Go through all elements of the old table
      for (k = 0; k < old_table_size; k++)
         // If there is data in that index
         if (old_array[k].state == ACTIVE)
            // Insert into the new reconstructed array
            insert(old_array[k].data);
   }

   /**
    * Find the hashkey for the given data
    * @param x data to generate key from
    * @return the hashkey
    */
   protected int myhash(E x)
   {
      int hash_val;

      // Generate the hashkey
      hash_val = x.hashCode() % m_table_size;
      if (hash_val < 0)
         hash_val += m_table_size;

      return hash_val;
   }

   /**
    * Find the next prime of the provided number. The number itself will also be
    * checked.
    * @param n number to find the next prime of
    * @return a number the input or higher that is prime
    */
   protected static int nextPrime(int n)
   {
      int k, candidate, loop_lim;

      // loop doesn't work for 2 or 3
      if (n <= 2 )
         return 2;
      else if (n == 3)
         return 3;

      for (candidate = (n%2 == 0)? n+1 : n ; true ; candidate += 2)
      {
         // all primes > 3 are of the form 6k +/- 1
         loop_lim = (int)( (Math.sqrt((double)candidate) + 1)/6 );

         // we know it is odd.  check for divisibility by 3
         if (candidate%3 == 0)
            continue;

         // now we can check for divisibility of 6k +/- 1 up to sqrt
         for (k = 1; k <= loop_lim; k++)
         {
            if (candidate % (6*k - 1) == 0)
               break;
            if (candidate % (6*k + 1) == 0)
               break;
         }
         if (k > loop_lim)
            return candidate;
      }
   }
   
   /**
    * Refreshes the Hash Table, emptying all values and allocating the amount of
    * space specified according to m_table_size
    */
   void allocateArray()
   {
      int k;
      
      // Assign m_array a new blank array, removing any previous one
      m_array = new HashEntry[m_table_size];
      for (k = 0; k < m_table_size; k++)
         // Set every entry in m_array as a default HashEntry object 
         m_array[k] = new HashEntry<E>();
   }
}

