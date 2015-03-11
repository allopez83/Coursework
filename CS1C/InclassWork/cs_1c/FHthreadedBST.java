package cs_1c;
import java.util.*;

class FHthreadedNode<E extends Comparable< ? super E > >
{
   // use protected access so the tree, in the same package, 
   // or derived classes can access members 
   protected FHthreadedNode<E> l_child, r_child;
   protected E data;
   protected boolean l_thread, r_thread;

   public FHthreadedNode( E d, FHthreadedNode<E> lt, FHthreadedNode<E> rt,
         boolean l_th, boolean r_th, int ht)
   {
      l_child = lt; 
      r_child = rt;
      data = d;
      l_thread = l_th;
      r_thread = r_th;
   }
   
   public FHthreadedNode()
   {
      this(null, null, null, true, true, 0);
   }
}

public class FHthreadedBST<E extends Comparable< ? super E > >
   implements Cloneable
{
   protected int m_size;
   protected FHthreadedNode<E> m_root;
   
   public FHthreadedBST() { clear(); }
   public boolean empty() { return (m_size == 0); }
   public int size() { return m_size; }
   public void clear() { m_size = 0; m_root = null; }
   public int showHeight() { return findHeight(m_root, -1); }
   
   public E findMin() 
   {
      if (m_root == null)
         throw new NoSuchElementException();
      return findMin(m_root).data;
   }
   
   public E findMax() 
   {
      if (m_root == null)
         throw new NoSuchElementException();
      return findMax(m_root).data;
   }
   
   public E find( E x )
   {
      FHthreadedNode<E> result_node;
      result_node = find(m_root, x);
      if (result_node == null)
         throw new NoSuchElementException();
      return result_node.data;
   }
   
   public boolean contains(E x)  { return find(m_root, x) != null; }
   
   public boolean insert( E x )
   {
      int compare_result;
      
      if (m_root == null)
      {
         m_root = new FHthreadedNode<E>(x, null, null, true, true, 0);
         m_size++;
         return true;
      }
      
      FHthreadedNode<E> new_node, parent;
      parent = m_root;
      
      while(true) 
      {
         compare_result = x.compareTo(parent.data); 
         if ( compare_result < 0 )
         {
            if( !(parent.l_thread) )
               parent = parent.l_child;
            else
            {
               // place as new left child
               new_node = new FHthreadedNode<E>
               (x, parent.l_child, parent, true, true, 0);
               parent.l_child = new_node;
               parent.l_thread = false;
               break;
            }
         }
         else if ( compare_result > 0 )
         {
            if( !(parent.r_thread) )
               parent = parent.r_child;
            else
            {
               // place as new right child
               new_node = new FHthreadedNode<E>
               (x, parent, parent.r_child, true, true, 0);
               parent.r_child = new_node;
               parent.r_thread = false;
               break;
            }
         }
         else
            return false;  // duplicate
      }

      m_size++;
      return true;
   }
   
   public boolean remove( E x )
   {
      int old_size = m_size;
      m_root = remove(m_root, x);
      return (m_size != old_size);
   }
   
   public < F extends Traverser<? super E > > 
   void traverse(F func)
   {
      if (m_root == null)
         return;

      FHthreadedNode<E> node = findMin(m_root);
      do
      {
         func.visit(node.data);
         node = successor(node);
      } while (node != null);
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHthreadedBST<E> new_object = (FHthreadedBST<E>)super.clone();
      new_object.clear();  // can't point to other's data

      // builds a new subtree through insertion
      cloneSubtree(m_root, new_object);

      return new_object;
   }
   
   // private helper methods ----------------------------------------
   protected FHthreadedNode<E> findMin( FHthreadedNode<E> root ) 
   {
      if (root == null)
         return null;
      while ( !(root.l_thread) )
         root = root.l_child;
      return root;
   }
   
   protected FHthreadedNode<E> findMax( FHthreadedNode<E> root ) 
   {
      if (root == null)
         return null;
      while ( !(root.r_thread) )
         root = root.r_child;
      return root;
   }

   // very hard to remove recursion, so only adjust pred/succ links
   protected FHthreadedNode<E> remove( FHthreadedNode<E> root, E x  )
   {
      int compare_result;  // avoid multiple calls to compareTo()
      FHthreadedNode<E> temp_root;
     
      if (root == null)
         return null;

      compare_result = x.compareTo(root.data);
      if ( compare_result < 0 )
      {
         if (!root.l_thread)
            root.l_child = remove(root.l_child, x);
      }
      else if ( compare_result > 0 )
      {
         if (!root.r_thread)
            root.r_child = remove(root.r_child, x);
      }

      // found the node
      else if (!(root.l_thread) && !(root.r_thread))
      {
         // two real children
         root.data = findMin(root.r_child).data;
         root.r_child = remove(root.r_child, root.data);
      }
      else
      {
         // one or two "fake" children => at least one thread
         redirectThreadsPointingToMe(root);

         // if a full leaf, we have to modify one of parent's thread flags
         if (root.l_thread && root.r_thread)
         {
            temp_root = adjustParentThreadFlagsAndUnlink(root);
            
            // in case this was final node in tree
            if (root.l_child == null && root.r_child == null)
               m_root = null;
            
            root = temp_root;
         }
         else
            // at least one real child, so we copy to parent
            root = ( !(root.l_thread) )? root.l_child : root.r_child;
         
         m_size--;
      }
      return root;
   }
   
   void redirectThreadsPointingToMe( FHthreadedNode<E> node_to_remove )
   {
      FHthreadedNode<E>  min_node, max_node, node;

      // adjust nodes in root's subtree that "thread directly up" to root
      min_node = findMin(node_to_remove);
      max_node = findMax(node_to_remove);
      
      for (node = min_node; node != null; node = successor(node))
         if (node.l_thread && node.l_child == node_to_remove)
         {
            node.l_child = predecessor(node_to_remove);
            break;  // last of only two possible threads pointing up
         }
         else if (node.r_thread && node.r_child == node_to_remove)
         {
            node.r_child = successor(node_to_remove);
         }
   }

   // called when both flags are true, meaning one MUST be parent. find out
   // which one, so we can set parent's left of right thread flag to true
   protected FHthreadedNode<E> adjustParentThreadFlagsAndUnlink( 
         FHthreadedNode<E> node_to_remove )
   {
      FHthreadedNode<E> node;

      node = node_to_remove.r_child;  // successor is parent?
      if ( node != null )
      {
         if ( node.l_child == node_to_remove )
         {
            node.l_thread = true;
            return node_to_remove.l_child;
         }
      }

      // test both in case m_root is leaf
      node = node_to_remove.l_child;  // predecessor is parent?
      if ( node != null )
      {
         if ( node.r_child == node_to_remove )
         {
            node.r_thread = true;
            return node_to_remove.r_child;
         }
      }
      
      return null;  // shouldn't happen
   }
   
   protected FHthreadedNode<E> find( FHthreadedNode<E> root, E x )
   {
      int compare_result;

      if (root == null)
         return null;

      while (true)
      {
         if (root == null)
            return null;
         
         compare_result = x.compareTo(root.data); 
         if (compare_result < 0)
         {
            if(root.l_thread)
               return null;
            root = root.l_child;
         }
         else if (compare_result < 0)
         {
            if(root.r_thread)
               return null;
            root = root.r_child;
         }
         else 
            break;
      }
      return root;   // found
   }
   
   protected void cloneSubtree(FHthreadedNode<E> root,
         FHthreadedBST<E> new_tree)
   {
      // to overcome complex threading, simply add node into a new tree
      // and let the insert() algorithm naturally set the threads.
      // nodes will go into new tree root in equivalent order as old

      new_tree.insert(root.data);
      if ( !(root.l_thread) )
         cloneSubtree(root.l_child, new_tree);
      if ( !(root.r_thread) )
         cloneSubtree(root.r_child, new_tree);
   }
   
   protected int findHeight( FHthreadedNode<E> tree_node, int height ) 
   {
      int left_height, right_height;
      if (tree_node == null)
         return height;
      height++;
      left_height  = tree_node.l_thread ? height 
            : findHeight(tree_node.l_child, height);
         right_height = tree_node.r_thread ? height 
            : findHeight(tree_node.r_child, height);
      return (left_height > right_height)? left_height : right_height;
   }
   
   static protected FHthreadedNode successor( FHthreadedNode node ) 
   {
      if (node == null)
         return null;
      
      if (node.r_thread)
         return node.r_child;
      
      node = node.r_child;
      while ( !(node.l_thread) )
         node = node.l_child;
      return node;
   }
   
   static protected FHthreadedNode predecessor( FHthreadedNode node ) 
   {
      if (node == null)
         return null;
      
      if (node.l_thread)
         return node.l_child;
      
      node = node.l_child;
      while ( !(node.r_thread) )
         node = node.r_child;
      return node;
   }
}

