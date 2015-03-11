package cs_1c;
import java.util.*;

// generic FHlinkedList class ------------------------------------
public class FHlinkedList<E> implements Cloneable, Iterable<E>, Collection<E>,
   Queue<E>, Deque<E>, List<E>
{
// definition of inner Node class ----------------------
   private class Node
   {
      Node prev, next;
      E data;

      Node( E x, Node prv, Node nxt )
      {
         prev = prv; next = nxt; data = x;
      }
     
      // defined in Node class because it only involves neighbor links
      void insertBefore(E x)
      {
         Node p_new = new Node(x, prev, this);
         prev.next = p_new;
         prev = p_new;
      }
      
      // defined in Node class because it only involves neighbor links
      E remove()
      {
         prev.next = next;
         next.prev = prev;
         return this.data;
      }
   };
   
   // Utility Pair class for returning (Node, index} pairs
   private class Pair
   {
      Node node;
      int index;
      
      Pair(Node node, int index) {this.node = node; this.index = index;}
   };
   
   // principal private data for FHlist
   private int m_size;
   private Node m_head, m_tail;
   
   // for internal use
   private static final int NOT_FOUND = -1;
   
   // for iterator concurrency testing
   private int modCount = 0;

   // constructors
   public FHlinkedList() { clear(); } 
   public FHlinkedList( Collection<? extends E> rhs )
   {
      clear();
      addAll(rhs);
   }
   
   // fundamental List operations
   public int size() { return m_size; }
   public boolean isEmpty() { return m_size == 0; }
   
   public void clear()
   {
      m_size = 0;
      m_head = new Node(null, null, null);
      m_tail = new Node(null, null, null);  // could set prev here...
      m_head.next = m_tail;
      m_tail.prev = m_head;  // ... but  do it here for clarity
      modCount++;
   }
   
   // private helper methods --------------------------
   
   // returns the Node in the index-th position of the list
   private Node getNode(int index)
   {
      Node p;
      int k;
      
      // we can't throw exception here because different callers have
      // different bounds they must obey -- so we let them throw it
      
      // use m_head or m_tail, whichever is closer
      if (index < m_size/2)
         for (k = 0, p = m_head.next; k < index; p = p.next, k++)
            ;
      else
         for (k = m_size, p = m_tail; k > index; p = p.prev, k--)
            ;
      return p;   
   }
   
   // returns both the node and index of first occurrence of Object o
   private Pair getFirstOccurrence(Object o)
   {
      Node p;
      int k;
      
      if(o != null)
      {
         for (k = 0, p = m_head.next; k < m_size; p = p.next, k++)
            if (o.equals(p.data) )
               return new Pair(p, k);
      }
       else
       {
          for (k = 0, p = m_head.next; k < m_size; p = p.next, k++)
             if (p.data == null )
                return new Pair(p, k);
       }
      return new Pair(null,NOT_FOUND);    
   }
  
   // returns both the node and index of first occurrence of Object o
   private Pair getLastOccurrence(Object o)
   {
      Node p;
      int k;

      if(o != null)
      {
         for (k = m_size-1, p = m_tail.prev; k > 0; p = p.prev, k--)
            if (o.equals(p.data) )
               return new Pair(p, k);
      }
      else
      {
         for (k = m_size-1, p = m_tail.prev; k > 0; p = p.prev, k--)
            if (p.data == null )
               return new Pair(p, k);   
      }
      return new Pair(null,NOT_FOUND);    
   }
   
   // FHlinkedList public methods ------------------------------------
   
   // accessors and mutators
   public E get(int index)
   {
      if (index < 0 || index >= m_size)
         throw new IndexOutOfBoundsException();
      return getNode(index).data;
   }

   public E getFirst()
   {
      if (m_size == 0)
         throw new NoSuchElementException();
      return m_head.next.data;
   }
   
   public E getLast()
   {
      if (m_size == 0)
         throw new NoSuchElementException();
      return m_tail.prev.data;
   }
   
   public E peek()
   {
      if (m_size == 0)
         return null;
      return m_head.next.data;
   }

   public E peekFirst()
   {
      if (m_size == 0)
         return null;
      return m_head.next.data;
   }
   
   public E peekLast()
   {
      if (m_size == 0)
         return null;
      return m_tail.prev.data;
   }
   
   // poll() equiv. to pollFirst()
   public E poll()
   {
      if (m_size == 0)
         return null;
      E ret_val = m_head.next.data;
      m_head.next.remove();
      modCount++;
      m_size--;
      return ret_val;
   }

   public E pollFirst()
   {
      if (m_size == 0)
         return null;
      E ret_val = m_head.next.data;
      m_head.next.remove();
      modCount++;
      m_size--;
      return ret_val;
   }
   
   public E pollLast()
   {
      if (m_size == 0)
         return null;
      E ret_val = m_tail.prev.data;
      m_tail.prev.remove();
      modCount++;
      m_size--;
      return ret_val;
   }

   
   public boolean contains(Object o)
   {
      return ( indexOf(o) != NOT_FOUND );
   }
   
   public E set(int index, E x)
   {
      E ret_val;
      Node p;
      
      if (index < 0 || index >= m_size)
         throw new IndexOutOfBoundsException();
      p = getNode(index);
      ret_val = p.data;
      p.data = x;
      return ret_val;
   }
   
   public boolean add(E x)
   {
      // low-level methods should be efficient - don't call other add()
      m_tail.insertBefore(x);
      m_size++;
      modCount++;
      return true;
   }

   public void add(int index, E x)
   {
      if (index < 0 || index > m_size) // == m_size allowed
         throw new IndexOutOfBoundsException();
      
      getNode(index).insertBefore(x);
      modCount++;
      m_size++;
   }
   
   public void addFirst(E x)
   {
      // low-level methods should be efficient - don't call other add()
      m_head.next.insertBefore(x);
      m_size++;
      modCount++; 
   }

   public void addLast(E x)
   {
      // low-level methods should be efficient - don't call other add()
      m_tail.insertBefore(x);
      m_size++;
      modCount++; 
   }
   
   public boolean offer(E x)
   {
      // low-level methods should be efficient - don't call add()
      m_tail.insertBefore(x);
      m_size++;
      modCount++;
      return true;
   }
   
   public boolean offerFirst(E x)
   {
      // low-level methods should be efficient - don't call addFirst()
      m_head.next.insertBefore(x);
      m_size++;
      modCount++; 
      return true;
   }

   public boolean offerLast(E x)
   {
      // low-level methods should be efficient - don't call other addLast()
      m_tail.insertBefore(x);
      m_size++;
      modCount++;
      return true;
   }
   
   // equiv. to removeFirst()
   public E pop()
   {
      // low-level methods should be efficient - don't call  rmvFst()
     if (m_size == 0)
         throw new NoSuchElementException();
      m_size--;
      modCount++;
      return m_head.next.remove();
   }
   
   // equiv. to addFirst()
   public void push(E x)
   {
      // low-level methods should be efficient - don't call other addFst()
      m_head.next.insertBefore(x);
      m_size++;
      modCount++; 
   }
   
   public E remove()
   {
      // low-level methods should be efficient - don't call other rmv()
      if (m_size == 0)
         throw new NoSuchElementException();
      m_size--;
      modCount++;
      return m_head.next.remove();
   }
   
   public E removeFirst()
   {
      // low-level methods should be efficient - don't call other rmv()
     if (m_size == 0)
         throw new NoSuchElementException();
      m_size--;
      modCount++;
      return m_head.next.remove();
   }
   
   public E removeLast()
   {
      // low-level methods should be efficient - don't call other rmv()
     if (m_size == 0)
         throw new NoSuchElementException();
      m_size--;
      modCount++;
      return m_tail.prev.remove();
   }
   
   public E remove(int index)
   {
      Node removed_node;
      
      if (index < 0 || index >= m_size)
         throw new IndexOutOfBoundsException();
      
      removed_node = getNode(index);
      m_size--;
      modCount++;
      return removed_node.remove();
   }
   
   public boolean remove(Object o)
   {
      Pair result = getFirstOccurrence(o);
      
      if (result.index == NOT_FOUND)
         return false;
      
      result.node.remove();
      m_size--;
      modCount++;
      return true;
   }
   
   public boolean removeFirstOccurrence(Object o)
   {
      // Since remove(o) is O(N), calling it adds minute overhead
      return remove(o);
   }
   
   public boolean removeLastOccurrence(Object o)
   {
      Pair result = getLastOccurrence(o);
      
      if (result.index == NOT_FOUND)
         return false;
      
      result.node.remove();
      m_size--;
      modCount++;
      return true;
   }
   
   protected void removeRange(int from_k, int to_k)
   {
      Node p;
      int k;
      
      if (from_k < 0 || from_k >= m_size || to_k > size() || to_k < from_k)
         throw new IndexOutOfBoundsException();
      
      for (k = from_k, p = getNode(from_k); k < to_k; k++, p = p.next)
         p.remove();
      m_size -= (to_k - from_k);
      modCount++;
   }
   
   public boolean addAll( Collection<? extends E> rhs )
   {
      // this is not a low-level or common function, so ok to call add()
      if (rhs.size() == 0)
         return false;
      for(E x : rhs)
         add( x );
      return true;
   }
   
   public boolean addAll( int index, Collection<? extends E> rhs )
   {
      // this is not a low-level or common function, so ok to call add()
      if (rhs.size() == 0)
         return false;
      
      int k = 0;
      for(E x : rhs)
         add(k++, x);
      return true;
   }
   
   // other FHlinkedList methods to satisfy java.util.collection
   public boolean containsAll(Collection<?> c)
   {
      for(Object o : c)
         if ( !contains(o) )
            return false;
      return true;
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHlinkedList<E> new_object = (FHlinkedList<E>)super.clone();
      
      // this is a shallow copy - we are not duplicating/cloning objects
      new_object.clear();  // but, we have to separate the head/tail/size
      new_object.addAll(this);  // and have distinct nodes
      
      return new_object;
   }
   
   public int indexOf(Object o)
   {
      return getFirstOccurrence(o).index;
   }
   
   public int lastIndexOf(Object o)
   {
      return getLastOccurrence(o).index;
   }
   
   public boolean equals(Object o)
   {
      Node p1, p2;
      FHlinkedList<E> that;
      E this_data, that_data;
      
      if ( !(o instanceof FHlinkedList<?>) )
         return false;
      
      that = (FHlinkedList<E>)o;
      if (that.size() != m_size)
         return false;
      
      for(p1 = m_head.next, p2 = that.m_head.next; p1 != m_tail;  
         p1 = p1.next, p2 = p2.next)
      {
         this_data = p1.data;
         that_data = p2.data;
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
      int k;
      Node p;
      
      Object[] ret_array = new Object[m_size];
      for (k = 0, p = m_head.next; k < m_size; k++, p = p.next)
         ret_array[k] = p.data;
      return ret_array;
   }
   
   public <T> T[] toArray(T[] user_array)
   {
      int k;
      Node p;
      Object[] ret_array;
      
      if (m_size > user_array.length)
         ret_array = new Object[m_size];
      else
         ret_array = user_array;
      
      for (k = 0, p = m_head.next; k < m_size; k++, p = p.next)
         ret_array[k] = p.data;
      
      if (m_size < user_array.length)
         ret_array[m_size] = null;
      
      return (T[])user_array;
   }
   
   public E element()
   {
      if (m_size == 0)
         throw new NoSuchElementException();
      return m_head.next.data;
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
   
   // this is the only method that should really be supported, but I don't
   public List<E> subList(int fromIndex, int toIndex)
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

   public java.util.Iterator<E> descendingIterator()
   { 
      return new FHdescendingIterator();
   }

   // internal Iterator class
   private class FHiterator implements java.util.Iterator<E>
   {
      // for use with derived FHlistIterator methods remove(), set().
      protected static final int NOT_VALID = -1;
      protected static final int NEXT = 10;
      protected static final int PREVIOUS = 11;
      
      protected Node m_current_node;
      protected int m_current_index;
      
      // for ConcurrentModificationExceptions
      protected int m_iter_mod_count = modCount;
      
      // for IllegalStateExceptions
      protected Node m_last_node_returned = null; // valid: any Node object
      protected int m_last_iteration = NOT_VALID; // valid: NEXT or PREVIUOS 
      
      // required interface implementations
      public boolean hasNext() { return m_current_index < m_size; }

      public E next()
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if( !hasNext() ) 
            throw new java.util.NoSuchElementException();
         m_last_node_returned = m_current_node;
         m_current_node = m_current_node.next;
         m_current_index++;
         m_last_iteration = NEXT;
         return m_last_node_returned.data;
      }

      public void remove()
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if (m_last_node_returned == null)
            throw new IllegalStateException ();
         m_last_node_returned.remove();
         if (m_last_iteration == NEXT)
            m_current_index--;
         m_size--;
         
         // since we don't call host remove, do not incr. m_iter_mod_count
         m_last_node_returned = null;
      }
      
      //  constructors (default access for package only)
      FHiterator() 
      {
         m_current_node = m_head.next;
         m_current_index = 0;
      }
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
         m_current_node = getNode(index);
         m_current_index = index;
      }
      
      public boolean hasPrevious() { return m_current_index > 0; }

      public E previous()
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if( !hasPrevious() ) 
            throw new java.util.NoSuchElementException();
         m_current_node = m_current_node.prev;
         m_last_node_returned = m_current_node;
         m_current_index--;
         m_last_iteration = PREVIOUS;
         return m_last_node_returned.data;
      }
      
      // next() and previous() guarantee 0 <= m_current_index < m_size
      public int nextIndex() { return m_current_index; }
      public int previousIndex() { return m_current_index - 1; }
      
      // set and add
      public void set(E x) 
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         if (m_last_node_returned == null)
            throw new IllegalStateException ();
         m_last_node_returned.data = x;
      }
      
      public void add(E x) 
      {
         if (m_iter_mod_count != modCount)
            throw new ConcurrentModificationException();
         m_current_node.insertBefore(x);
         m_current_index++;
         m_size++;
         
         // since we don't call host add, do not incr. m_iter_mod_count
         m_last_node_returned = null;
      }
   }
   
   // internal Descending Iterator class
   private class FHdescendingIterator extends FHlistIterator 
   {
      // this is required by Dequeu; it does everything backwards
      public E next()
      {
         return previous();
      }
   
      FHdescendingIterator() 
      {
         m_current_node = m_tail;
         m_current_index = m_size;
      }
   }
}
