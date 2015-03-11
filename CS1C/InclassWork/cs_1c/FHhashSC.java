package cs_1c;
import java.util.*;

public class FHhashSC<E>
{
   static final int INIT_TABLE_SIZE = 97;
   static final double INIT_MAX_LAMBDA = 1.5;
   
   protected FHlinkedList<E>[] m_lists;
   protected int m_size;
   protected int m_table_size;
   protected double m_max_lambda;

   public FHhashSC(int table_size)
   {
      int k;
      
      if (table_size < INIT_TABLE_SIZE)
         m_table_size = INIT_TABLE_SIZE;
      else
         m_table_size = nextPrime(table_size);

      allocateMListArray();  // uses m_table_size;
      m_max_lambda = INIT_MAX_LAMBDA;
   }
   
   public FHhashSC()
   {
      this(INIT_TABLE_SIZE);
   }
   

   public boolean contains( E x)
   { 
      FHlinkedList<E> the_list = m_lists[myhash(x)];

      return the_list.contains(x);
   }
   
   public void makeEmpty()
   {
      int k, size = m_lists.length;

      for(k = 0; k < size; k++)
         m_lists[k].clear();
      m_size = 0;  
   }
   
   public boolean insert( E x)
   {
      FHlinkedList<E> the_list = m_lists[myhash(x)];

      if ( the_list.contains(x) )
         return false;

      // not found so we insert
      the_list.addLast(x);

      // check load factor
      if( ++m_size > m_max_lambda * m_table_size )
         rehash();

      return true; 
   }
   
   public boolean remove( E x)
   {
      ListIterator<E> iter; 
      FHlinkedList<E> the_list = m_lists[myhash(x)];

      // do not use contains() because it involves two passes through list
      for (iter = the_list.listIterator(); iter.hasNext(); )
         if ( x.equals(iter.next()) )
         {
            iter.remove();
            m_size--;
            return true;
         }

      // not found
      return false;   
   }
   

   
   public int size()  { return m_size; }
   public boolean setMaxLambda( double lam )
   {
      if (lam < .1 || lam > 100.)
         return false;
      m_max_lambda = lam;
      return true;
   }

   // protected methods of class ----------------------
   protected void rehash()
   {
      // we save old list and size then we can reallocate freely
      FHlinkedList<E>[] old_lists = m_lists;
      int k, old_table_size = m_table_size;
      ListIterator<E> iter;

      m_table_size = nextPrime(2*old_table_size);
      
      // allocate a larger, empty array
      allocateMListArray();  // uses m_table_size;

      // use the insert() algorithm to re-enter old data
      m_size = 0;
      for(k = 0; k < old_table_size; k++)
         for(iter = old_lists[k].listIterator(); iter.hasNext() ; )
            insert( iter.next());
   }
   
   protected int myhash( E x)
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
   
   void allocateMListArray()
   {
      int k;
      
      m_lists = new FHlinkedList[m_table_size];
      for (k = 0; k < m_table_size; k++)
         m_lists[k] = new FHlinkedList<E>();
   }
}
