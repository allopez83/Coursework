package cs_1c;
import java.util.*;

public class FHbinHeap<E extends Comparable< ? super E > >
{
   static final int INIT_CAPACITY = 64; // perfect tree of size 63

   private E[] m_array;
   private int m_size;
   private int m_capacity;

   // public  methods -------------------------------------------
   public void makeEmpty() { m_size = 0; };
   public FHbinHeap() { this(INIT_CAPACITY); }
   public FHbinHeap(int capacity)
   {
      // choose a capacity that is exactly 2^N - 1 for some N (esthetic)
      // which leads to m_capacity 2^N, internally -- user asks for 127, we
      // reserve 128, if they want 128, we have to reserve 256.
      for (m_capacity = INIT_CAPACITY; 
         m_capacity <= capacity;
         m_capacity = 2 * m_capacity
         )
      {
         if (m_capacity < 0)
         {
            m_capacity = INIT_CAPACITY; // give up - overflow
            break;
         }
      }
      m_array = (E[]) new Comparable[m_capacity];  // Int or any Comparable
      makeEmpty();
   }

   public FHbinHeap(E[] items )
   {
      this(items.length);
      int k;

      m_size = items.length;

      // copy starting with position 1 - no ordering yet
      for(k = 0; k < items.length; k++ )
         m_array[k+1] = items[k];

      // order the heap
      orderHeap();
   }
 
   public boolean empty() { return m_size == 0; }
   
   public void insert( E x )
   {
      int hole;

      if( m_size == m_capacity - 1 )
         doubleCapacity();

      // percolate up
      hole = ++m_size;
      for( ; hole > 1 && x.compareTo(m_array[hole/2]) < 0; hole /= 2 )
         m_array[hole] = m_array[hole/2];
      m_array[hole] = x;
   }
   
   public E remove()
   {
      E min_object;

      if( empty() )
         throw new NoSuchElementException();

      min_object = m_array[1];
      m_array[1] = m_array[m_size--];
      percolateDown(1);

      return min_object;
   }

   public int size() { return m_size; }

   // private helper methods ------------------------------------
   private void orderHeap()
   {
      int k;

      for(k = m_size/2; k > 0; k-- )
         percolateDown(k);
   }
   
   private void percolateDown( int hole )
   { 
      int child;
      E tmp;

      for( tmp = m_array[hole]; 2 * hole <= m_size; hole = child )
      {
         child = 2 * hole;
         // if 2 children, get the lesser of the two
         if( child < m_size 
               && m_array[child + 1].compareTo(m_array[child]) < 0 )
            child++;
         if( m_array[child].compareTo(tmp) < 0 )
            m_array[hole] = m_array[child];
         else
            break;
      }
      m_array[hole] = tmp;
   }
   
   private void doubleCapacity()
   {
      E[] old_array = m_array;
      int old_capacity = m_capacity;

      m_capacity = 2 * old_capacity;
   
      // new will throw exceptions for us so no need to test anything
      m_array = (E[]) new Comparable[m_capacity];    // Int or any Comparable
      System.arraycopy(old_array, 0, m_array, 0, old_capacity);
   }
}
