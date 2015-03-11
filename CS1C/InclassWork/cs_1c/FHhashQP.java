package cs_1c;
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
   public FHhashQP(int table_size)
   {
      int k;
      
      if (table_size < INIT_TABLE_SIZE)
         m_table_size = INIT_TABLE_SIZE;
      else
         m_table_size = nextPrime(table_size);

      allocateArray();  // uses m_table_size;
      m_max_lambda = INIT_MAX_LAMBDA;
   }

   public FHhashQP()
   {
      this(INIT_TABLE_SIZE);
   }
   
   public boolean insert( E x)
   {
      int bucket = findPos(x);

      if ( m_array[bucket].state == ACTIVE )
         return false;

      m_array[bucket].data = x;
      m_array[bucket].state = ACTIVE;

      // check load factor
      if( ++m_size > m_max_lambda * m_table_size )
         rehash();

      return true;
   }
   
   public boolean remove( E x )
   {
      int bucket = findPos(x);

      if ( m_array[bucket].state != ACTIVE )
         return false;

      m_array[bucket].state = DELETED;
      m_size--;
      return true;
   }
   
   public boolean contains(E x ) 
   {
      return m_array[findPos(x)].state == ACTIVE;
   }
   
   public int size()  { return m_size; }
   
   void makeEmpty()
   {
      int k, size = m_array.length;

      for(k = 0; k < size; k++)
         m_array[k].state = EMPTY;
      m_size = 0;
   }
   
   public boolean setMaxLambda( double lam )
   {
      if (lam < .1 || lam > INIT_MAX_LAMBDA )
         return false;
      m_max_lambda = lam;
      return true;
   }
   
   // protected methods of class ----------------------
   
   int findPos( E x )
   {
      int kth_odd_num = 1;
      int index = myhash(x);

      while ( m_array[index].state != EMPTY
         && !m_array[index].data.equals(x) )
      {
         index += kth_odd_num; // k squared = (k-1) squared + kth odd #
         kth_odd_num += 2;     // compute next odd #
         if ( index >= m_table_size )
            index -= m_table_size;
      }
      return index;
   }
   
   protected void rehash()
   {
      // we save old list and size then we can reallocate freely
      HashEntry<E>[] old_array = m_array;
      int k, old_table_size = m_table_size;;

      m_table_size = nextPrime(2*old_table_size);
      
      // allocate a larger, empty array
      allocateArray();  // uses m_table_size;

      // use the insert() algorithm to re-enter old data
      m_size = 0;
      for(k = 0; k < old_table_size; k++)
         if (old_array[k].state == ACTIVE)
            insert( old_array[k].data );
   }
   
   protected int myhash(E x)
   {
      int hash_val;

      hash_val = x.hashCode() % m_table_size;
      if(hash_val < 0)
         hash_val += m_table_size;

      return hash_val;
   }
   
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
   
   void allocateArray()
   {
      int k;
      
      m_array = new HashEntry[m_table_size];
      for (k = 0; k < m_table_size; k++)
         m_array[k] = new HashEntry<E>();
   }
}

