package cs_1c;
import java.util.*;

// generic FHarrayList class ------------------------------------
public class FHarrayList<E> implements Cloneable, Iterable<E>, Collection<E>
{
   private static final int DEFAULT_CAPACITY = 100;
   private static final int NOT_FOUND = -1;
   private int modCount = 0;

   private int m_size;
   private E[] m_objects;

   // constructors
   public FHarrayList() { clear(); }
   public FHarrayList(int init_capacity) 
   { 
      clear(); 
      ensureCapacity(init_capacity);
   }
 
   public FHarrayList( Collection<? extends E> rhs )
   {
      clear();
      addAll(rhs);
   }
   
   // fundamental List operations
   public int size() { return m_size; }
   public boolean isEmpty() { return m_size == 0; }
   public void trimToSize() { ensureCapacity(m_size); }
   
   public void clear()
   {
      m_size = 0;
      m_objects = (E[])new Object[DEFAULT_CAPACITY];
      modCount++;
   }
   
   public void ensureCapacity(int minCapacity)
   {
      if (m_objects != null)
      {
         if( minCapacity <= m_objects.length )
            return;
      }

      E[] src_array = m_objects;
      m_objects = (E[])new Object[minCapacity];
      if (m_size > 0)
         System.arraycopy(src_array, 0, m_objects, 0, m_size);
   }
   
   // accessors and mutators
   public E get(int index)
   {
      // can't rely on m_objects[] to throw because m_size is < m_capacity!
      if (index < 0 || index >= m_size)
         throw new IndexOutOfBoundsException();
      return m_objects[index];
   }

   public E set(int index, E x)
   {
      E ret_val;
      
      if (index < 0 || index >= m_size)
         throw new IndexOutOfBoundsException();
      ret_val = m_objects[index];
      m_objects[index] = x;
      return ret_val;
   }

   public boolean contains(Object o)
   {
      return ( indexOf(o) != NOT_FOUND );
   }
   
   public boolean add(E x)
   {
      // low-level methods should be efficient - don't call other add() 
      if( m_objects.length == m_size )
         ensureCapacity(2*m_size + 1);
      m_objects[m_size++] = x;
      modCount++;
      return true;
   }

   public void add(int index, E x)
   {
      if (index < 0 || index > m_size)
         throw new IndexOutOfBoundsException();
      
      if( m_objects.length == m_size )
         ensureCapacity(2*m_size + 1);
      
      System.arraycopy(m_objects, index, m_objects, index + 1,
         m_size - index);

      m_objects[index] = x;
      m_size++;
      modCount++;
   }

   public E remove(int index)
   {
      E ret_val;
      
      if (index < 0 || index >= m_size)
         throw new IndexOutOfBoundsException();
      
      ret_val = m_objects[index];
      System.arraycopy(m_objects, index + 1, m_objects, index,
         m_size - index - 1);

      m_size--;
      modCount++;
      return ret_val;
   }
   
   public boolean remove(Object o)
   {
      int k = indexOf(o);
      
      if (k == NOT_FOUND)
         return false;
      
      remove(k);
      return true;
   }
   
   protected void removeRange(int from_k, int to_k)
   {
      if (from_k < 0 || from_k >= m_size || to_k > m_size || to_k < from_k)
         throw new IndexOutOfBoundsException();
      
      System.arraycopy(m_objects, to_k, m_objects, from_k,
         m_size - to_k );
      modCount++;
      m_size -= (to_k - from_k);
   }
   
   public boolean addAll( Collection<? extends E> rhs )
   {
      if (rhs.size() == 0)
         return false;
      for(E x : rhs)
         add( x );
      return true;
   }
   
   public boolean addAll( int index, Collection<? extends E> rhs )
   {
      if (rhs.size() == 0)
         return false;
      
      // not so efficient - consider System.arraycopy + explicit assignment
      int k = index;
      for(E x : rhs)
         add(k++, x);

      return true;
   }
   
   // other FHarrayList methods to satisfy java.util.collection
   public boolean containsAll(Collection<?> c)
   {
      for(Object o : c)
         if ( !contains(o) )
            return false;
      return true;
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHarrayList<E> new_object = (FHarrayList<E>)super.clone();

      // shallow copy according to Java spec ... but not TOO shallow
      // this is a shallow copy - we are not duplicating/cloning objects
      new_object.m_objects = (E[])m_objects.clone();

      return new_object;
   }
   
   public int indexOf(Object o)
   {
      int k;
      
      if (o != null)
      {
         for(k = 0; k < m_size;  k++)
            if (o.equals(m_objects[k]) )
               return k;
      }
      else
      {
         for(k = 0; k < m_size;  k++)
            if (m_objects[k] == null )
               return k;
      }   
      return NOT_FOUND;
   }
   
   public int lastIndexOf(Object o)
   {
      int k;
      
      if (o != null)
      {
         for(k = m_size - 1; k >= 0;  k--)
            if (o.equals(m_objects[k]) )
               return k;
      }
      else
      {
         for(k = m_size - 1; k >= 0;  k--)
            if (m_objects[k] == null )
               return k;
      }
      
      return NOT_FOUND;
   }
   
   public boolean equals(Object o)
   {
      int k;
      FHarrayList<E> that;
      E this_data, that_data;
      
      if ( !(o instanceof FHarrayList<?>) )
         return false;
      
      that = (FHarrayList<E>)o;
      if (that.size() != m_size)
         return false;
      
      for(k = 0; k < m_size;  k++)
      {
         this_data = m_objects[k];
         that_data = that.get(k);
         // we allow null values, so we have to test null==null first
         if (this_data == null || that_data == null)
         {
            if (this_data != null || that_data != null)
               return false;
         }
         else
         {
            if ( ! this_data.equals(that_data) )
               return false;
         }
      }
      
      return true;
   }
   
   public Object[] toArray() 
   {
      Object[] ret_array = m_objects.clone();
      return ret_array;
   }
   
   public <T> T[] toArray(T[] user_array)
   {
      int k;
      Object[] ret_array;
      
      if (m_size > user_array.length)
         ret_array = new Object[m_size];
      else
         ret_array = user_array;
      
      for (k = 0; k < m_size; k++)
         ret_array[k] = m_objects[k];
      
      if (m_size < user_array.length)
         ret_array[m_size] = null;
      
      return (T[])user_array;
   }
   
   // must be defined because this implements Collection
   public boolean retainAll(Collection<?> c)
   {
      throw new UnsupportedOperationException();
   }
   public boolean removeAll(Collection<?> c)
   {
      throw new UnsupportedOperationException();
   }
   
   // ------------- Iterator / ListIterator --------------
   public java.util.Iterator<E> iterator() { return new FHiterator(); }
   public java.util.ListIterator<E> listIterator() 
   { 
      return new FHlistIterator();
   }
   
   public java.util.ListIterator<E> listIterator(int index) 
   {
      if ( index < 0 || index >= m_size )
         throw new ArrayIndexOutOfBoundsException();
      return new FHlistIterator(index); 
   }

   // internal Iterator class
   private class FHiterator implements java.util.Iterator<E>
   {
      // for use with derived FHlistIterator methods remove(), set().
      protected static final int NOT_VALID = -1;
      protected static final int NEXT = 10;
      protected static final int PREVIOUS = 11;
      
      protected int m_current;
      
      // for ConcurrentModificationExceptions
      protected int m_iter_mod_count = modCount;
      
      // for IllegalStateExceptions
      protected int m_last_index_returned = NOT_VALID; // valid: 0 to m_size-1
      protected int m_last_iteration = NOT_VALID;   // valid: NEXT or PREVIOUS 
      
      // required interface implementations
      public boolean hasNext() { return m_current < m_size; }

      public E next()
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if( !hasNext() ) 
            throw new java.util.NoSuchElementException();
         m_last_index_returned = m_current++;
         m_last_iteration = NEXT;
         return m_objects[m_last_index_returned];
      }

      public void remove()
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if (m_last_index_returned == NOT_VALID)
            throw new IllegalStateException ();
         FHarrayList.this.remove(m_last_index_returned);
         if (m_last_iteration == NEXT)
            m_current--;
         m_iter_mod_count++;
         m_last_index_returned = NOT_VALID;
      }
      
      //  constructors (default access for package only)
      FHiterator() { m_current = 0; }
   }
   
   // internal ListIterator class
   private class FHlistIterator extends FHiterator 
      implements java.util.ListIterator<E>
   {
      
      //  constructors (default access for package only)
      FHlistIterator() { super(); }
      
      FHlistIterator(int index)
      {
         super();
         if ( index < 0 || index >= m_size )
            return;
         m_current = index;
      }
      
      public boolean hasPrevious() { return m_current > 0; }

      public E previous()
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if( !hasPrevious() ) 
            throw new java.util.NoSuchElementException();
         m_last_index_returned = --m_current;
         m_last_iteration = PREVIOUS;
         return m_objects[m_last_index_returned];
      }
      
      // next() and previous() guarantee 0 <= m_current <= m_size
      public int nextIndex() { return m_current; }
      public int previousIndex() { return m_current - 1; }
      
      // set and add
      public void set(E x) 
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if (m_last_index_returned == NOT_VALID)
            throw new IllegalStateException ();
         m_objects[m_last_index_returned] = x;
      }
      
      public void add(E x) 
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         FHarrayList.this.add(m_current++, x);
         m_iter_mod_count++;
         m_last_index_returned = NOT_VALID;
      }
   }
}
