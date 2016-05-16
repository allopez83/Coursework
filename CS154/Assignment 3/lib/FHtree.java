package cs_1c;

public class FHtree<E> implements Cloneable
{
   private int m_size;
   FHtreeNode<E> m_root;
   
   public FHtree() { clear(); }
   public boolean empty() { return (m_size == 0); }
   public int size() { return m_size; }
   public void clear() { m_size = 0; m_root = null; }
   
   public FHtreeNode<E> find(E x) { return find(m_root, x, 0); }
   public boolean remove(E x) { return remove(m_root, x); }
   public void  display()  { display(m_root, 0); }
   
   public FHtreeNode<E> add_child( FHtreeNode<E> tree_node,  E x )
   {
      // empty tree? - create a root node if user passes in null
      if (m_size == 0)
      {
         if (tree_node != null)
            return null; // error something's fishy.  tree_node can't right
         m_root = new FHtreeNode<E>(x, null, null, null);
         m_root.my_root = m_root;
         m_size = 1;
         return m_root;
      }
      if (tree_node == null)
         return null; // error inserting into non_null tree with a null parent
      if (tree_node.my_root != m_root)
         return null;  // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      FHtreeNode<E> new_node = new FHtreeNode<E>(x, 
         tree_node.first_child, null, tree_node, m_root);  // sb, chld, prv, rt
      tree_node.first_child = new_node;
      if (new_node.sib != null)
         new_node.sib.prev = new_node;
      ++m_size;
      return new_node;  
   }
   

   
   public FHtreeNode<E> find(FHtreeNode<E> root, E x, int level)
   {
      FHtreeNode<E> retval;

      if (m_size == 0 || root == null)
         return null;

      if (root.data.equals(x))
         return root;

      // otherwise, recurse.  don't process sibs if this was the original call
      if ( level > 0 && (retval = find(root.sib, x, level)) != null )
         return retval;
      return find(root.first_child, x, ++level);
   }
   
   public boolean remove(FHtreeNode<E> root, E x)
   {
      FHtreeNode<E> tn = null;

      if (m_size == 0 || root == null)
         return false;
     
      if ( (tn = find(root, x, 0)) != null )
      {
         remove_node(tn);
         m_size--;
         return true;
      }
      return false;
   }
   
   private void remove_node(FHtreeNode<E> node_to_delete )
   {
      if (node_to_delete == null || m_root == null)
         return;
      if (node_to_delete.my_root != m_root)
         return;  // silent error, node does not belong to this tree

      // remove all the children of this node
      while (node_to_delete.first_child != null)
         remove_node(node_to_delete.first_child);

      if (node_to_delete.prev == null)
         m_root = null;  // last node in tree
      else if (node_to_delete.prev.sib == node_to_delete)
         node_to_delete.prev.sib = node_to_delete.sib; // adjust left sibling
      else
         node_to_delete.prev.first_child = node_to_delete.sib;  // adjust parent

      // adjust the successor sib's prev pointer
      if (node_to_delete.sib != null)
         node_to_delete.sib.prev = node_to_delete.prev;
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHtree<E> new_object = (FHtree<E>)super.clone();
      new_object.clear();  // can't point to other's data

      new_object.m_root = cloneSubtree(m_root);
      new_object.m_size = m_size;
      new_object.set_my_roots(new_object.m_root);
      
      return new_object;
   }
   
   private FHtreeNode<E> cloneSubtree(FHtreeNode<E> root)
   {
      FHtreeNode<E> new_node;
      if (root == null)
         return null;

      // does not set my_root which must be done by caller
      new_node = new FHtreeNode<E>
      (
         root.data, 
         cloneSubtree(root.sib), cloneSubtree(root.first_child),
         null
      );
      
      // the prev pointer is set by parent recursive call ... this is the code:
      if (new_node.sib != null)
         new_node.sib.prev = new_node;
      if (new_node.first_child != null)
         new_node.first_child.prev = new_node;
      return new_node;
   }
   
   // recursively sets all my_roots to m_root
   private void set_my_roots(FHtreeNode<E> tree_node)
   {
      FHtreeNode<E> child;

      if (m_root == null)
         return;

      tree_node.my_root = m_root;
      for (child = tree_node.first_child; child != null; child = child.sib)
         set_my_roots(child);
   }
   
   // define this as a static member so recursive display() does not need
   // a local version
   final static String blank_string = "                                    ";
   
   protected void  display(FHtreeNode<E> tree_node, int level) 
   {
      FHtreeNode<E> child;
      String indent;

      // stop runaway indentation/recursion
      if  (level > (int)blank_string.length() - 1)
      {
         System.out.println( blank_string + " ... " );
         return;
      }

      indent = blank_string.substring(0, level);

      // pre-order processing done here ("visit")
      System.out.println( indent + tree_node.data ) ;
      
      // recursive step done here
      for (child = tree_node.first_child; 
           child != null; 
           child = child.sib)
         display(child, level+1);
   }
   
   public < F extends Traverser< ? super E > > 
   void traverse(F func)
   {
      traverse(func, m_root);
   }
   
   protected <F extends Traverser<? super E>> 
   //  public <F extends Traverser<E>>  // also works but less flexibly
   void traverse(F func, FHtreeNode<E> tree_node)
   {
      FHtreeNode<E> child;
      if (tree_node == null)
         return;

      func.visit(tree_node.data);

      for (child = tree_node.first_child; child != null; child = child.sib)
         traverse(func, child);
   }
}
