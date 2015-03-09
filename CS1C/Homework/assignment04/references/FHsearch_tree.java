package assignment04.references;
import java.util.*;

import cs_1c.Traverser;

public class FHsearch_tree<E extends Comparable< ? super E > >
   implements Cloneable
{
   protected int m_size;
   protected FHs_treeNode<E> m_root;
   
   public FHsearch_tree() { clear(); }
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
      FHs_treeNode<E> result_node;
      result_node = find(m_root, x);
      if (result_node == null)
         throw new NoSuchElementException();
      return result_node.data;
   }
   public boolean contains(E x)  { return find(m_root, x) != null; }
   
   public boolean insert( E x )
   {
      int old_size = m_size;
      m_root = insert(m_root, x);
      return (m_size != old_size);
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
      traverse(func, m_root);
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHsearch_tree<E> new_object = (FHsearch_tree<E>)super.clone();
      new_object.clear();  // can't point to other's data

      new_object.m_root = cloneSubtree(m_root);
      new_object.m_size = m_size;
      
      return new_object;
   }
   
   // private helper methods ----------------------------------------
   protected FHs_treeNode<E> findMin( FHs_treeNode<E> root ) 
   {
      if (root == null)
         return null;
      if (root.l_child == null)
         return root;
      return findMin(root.l_child);
   }
   
   protected FHs_treeNode<E> findMax( FHs_treeNode<E> root ) 
   {
      if (root == null)
         return null;
      if (root.r_child == null)
         return root;
      return findMax(root.r_child);
   }
   
   protected FHs_treeNode<E> insert( FHs_treeNode<E> root, E x )
   {
      int compare_result;  // avoid multiple calls to compareTo()
      
      if (root == null)
      {
         m_size++;
         return new FHs_treeNode<E>(x, null, null);
      }
      
      compare_result = x.compareTo(root.data); 
      if ( compare_result < 0 )
         root.l_child = insert(root.l_child, x);
      else if ( compare_result > 0 )
         root.r_child = insert(root.r_child, x);

      return root;
   }

   protected FHs_treeNode<E> remove( FHs_treeNode<E> root, E x  )
   {
      int compare_result;  // avoid multiple calls to compareTo()
     
      if (root == null)
         return null;

      compare_result = x.compareTo(root.data); 
      if ( compare_result < 0 )
         root.l_child = remove(root.l_child, x);
      else if ( compare_result > 0 )
         root.r_child = remove(root.r_child, x);

      // found the node
      else if (root.l_child != null && root.r_child != null)
      {
         root.data = findMin(root.r_child).data;
         root.r_child = remove(root.r_child, root.data);
      }
      else
      {
         root =
            (root.l_child != null)? root.l_child : root.r_child;
         m_size--;
      }
      return root;
   }
   
   protected <F extends Traverser<? super E>> 
   void traverse(F func, FHs_treeNode<E> tree_node)
   {
      if (tree_node == null)
         return;

      traverse(func, tree_node.l_child);
      func.visit(tree_node.data);
      traverse(func, tree_node.r_child);
   }
   
   protected FHs_treeNode<E> find( FHs_treeNode<E> root, E x )
   {
      int compare_result;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compare_result = x.compareTo(root.data); 
      if (compare_result < 0)
         return find(root.l_child, x);
      if (compare_result > 0)
         return find(root.r_child, x);
      return root;   // found
   }
   
   protected FHs_treeNode<E> cloneSubtree(FHs_treeNode<E> root)
   {
      FHs_treeNode<E> new_node;
      if (root == null)
         return null;

      // does not set my_root which must be done by caller
      new_node = new FHs_treeNode<E>
      (
         root.data, 
         cloneSubtree(root.l_child), 
         cloneSubtree(root.r_child)
      );
      return new_node;
   }
   
   protected int findHeight( FHs_treeNode<E> tree_node, int height ) 
   {
      int left_height, right_height;
      if (tree_node == null)
         return height;
      height++;
      left_height = findHeight(tree_node.l_child, height);
      right_height = findHeight(tree_node.r_child, height);
      return (left_height > right_height)? left_height : right_height;
   }
}
