package cs_1c;

class AvlNode<E extends Comparable< ? super E > >
extends FHs_treeNode<E>
{
   protected int height;

   public AvlNode( 
         E x, 
         FHs_treeNode<E> l_child, 
         FHs_treeNode<E> r_child, 
         int ht )
   {
      super(x, l_child, r_child);
      if (!setHeight(ht)) 
         height = 0;
   }

   public AvlNode()
   {
      this(null, null, null, 0);
   }

   public int getHeight() { return height; }
   public boolean setHeight(int height)
   {
      if (height < -1) 
         return false;
      this.height = height;
      return true;
   }
};

public class FHavlTree<E extends Comparable< ? super E > >
extends FHsearch_tree<E>
{
   // public methods of AVL Tree --------------------------------
   // constructor
   public FHavlTree() { super(); }

   // a fun and informative touch
   public int showHeight() { return heightOf(m_root); }

   // note that public insert(), remove() and clone() do not need to be
   // overridden because they would be identical method bodies
   // the private method calls from those base class methodswill call the AVL 
   // versions supplied here

   // private helper methods of AVL tree ----------------------------

   // overrides base class by incorporating height information
   protected AvlNode<E> cloneSubtree(FHs_treeNode<E> root)
   {
      AvlNode<E> new_node;
      if (root == null)
         return null;

      // does not set my_root which must be done by caller
      new_node = new AvlNode<E>
      (
            root.data, 
            cloneSubtree(root.l_child), 
            cloneSubtree(root.r_child),
            root.getHeight()
      );
      return new_node;
   }

   // we'll make it static and remove the parameter, E
   protected static int heightOf(FHs_treeNode t)
      { return t == null? -1 : t.getHeight(); }


   protected FHs_treeNode<E> rotateWithLeftChild( 
      FHs_treeNode<E> k2 )
   {
      FHs_treeNode<E> k1 = k2.l_child;
      k2.l_child = k1.r_child;
      k1.r_child = k2;
      k2.setHeight( Math.max( heightOf(k2.l_child),  heightOf(k2.r_child) ) + 1 );
      k1.setHeight( Math.max( heightOf(k1.l_child),  k2.getHeight() ) + 1 );
      return k1;
   }

   protected FHs_treeNode<E> doubleWithLeftChild( 
      FHs_treeNode<E> k3 )
   {
      k3.l_child = rotateWithRightChild(k3.l_child);
      return rotateWithLeftChild(k3);
   }

   protected FHs_treeNode<E> rotateWithRightChild( 
      FHs_treeNode<E> k2 )
   {
      FHs_treeNode<E> k1 = k2.r_child;
      k2.r_child = k1.l_child;
      k1.l_child = k2;
      k2.setHeight( Math.max( heightOf(k2.l_child),  heightOf(k2.r_child) ) + 1 );
      k1.setHeight( Math.max( heightOf(k1.r_child),  k2.getHeight() ) + 1 );
      return k1;
   }

   protected FHs_treeNode<E> doubleWithRightChild( 
      FHs_treeNode<E> k3 )
   {
      k3.r_child = rotateWithLeftChild(k3.r_child);
      return rotateWithRightChild(k3);
   }

   protected FHs_treeNode<E> insert( FHs_treeNode<E> root, E x )
   {
      int compare_result;

      if (root == null)
      {
         m_size++;
         return new AvlNode<E>(x, null, null, 0);
      }

      compare_result = x.compareTo(root.data); 
      if ( compare_result < 0 )
      {
         root.l_child = insert(root.l_child, x);
         if(heightOf(root.l_child) - heightOf(root.r_child) == 2)
            if( x.compareTo(root.l_child.data) < 0 )
               root = rotateWithLeftChild(root);
            else
               root = doubleWithLeftChild(root); 
      }
      else if ( compare_result > 0 )
      {
         root.r_child = insert(root.r_child, x);
         if(heightOf(root.r_child) - heightOf(root.l_child) == 2)
            if( x.compareTo(root.r_child.data) > 0 )
               root = rotateWithRightChild(root);
            else
               root = doubleWithRightChild(root);
      }
      else
         return root; // duplicate

      // successfully inserted at this or lower level; adjust height
      root.setHeight( 
            Math.max( heightOf(root.l_child), heightOf(root.r_child))
            + 1);
      return root;
   }

   protected FHs_treeNode<E> remove( FHs_treeNode<E> root, E x  )
   {
      int compare_result;

      if (root == null)
         return null;

      compare_result = x.compareTo(root.data); 
      if ( compare_result < 0 )
      {
         root.l_child = remove(root.l_child, x);

         // rebalance - shortened left branch so right may now be higher by 2
         if(heightOf(root.r_child) - heightOf(root.l_child) == 2)
            if(heightOf(root.r_child.r_child) < heightOf(root.r_child.l_child))
               root = doubleWithRightChild(root);
            else
               root = rotateWithRightChild(root);
      }
      else if ( compare_result > 0 )
      {
         root.r_child = remove(root.r_child, x);

         // rebalance - shortened right branch so left may now be higher by 2
         if(heightOf(root.l_child) - heightOf(root.r_child) == 2)
            if(heightOf(root.l_child.l_child) < heightOf(root.l_child.r_child))
               root = doubleWithLeftChild(root);
            else
               root = rotateWithLeftChild(root);
      }

      // found the node
      else if (root.l_child != null && root.r_child != null)
      {
         root.data = findMin(root.r_child).data;
         root.r_child = remove(root.r_child, root.data);

         // rebalance - shortened right branch so left may now be higher by 2
         if(heightOf(root.l_child) - heightOf(root.r_child) == 2)
            if(heightOf(root.l_child.l_child) < heightOf(root.l_child.r_child))
               root = doubleWithLeftChild(root);
            else
               root = rotateWithLeftChild(root);
      }
      else
      {
         // no rebalancing needed at this external (1 + null children) node
         root =
            (root.l_child != null)? root.l_child : root.r_child;
         m_size--;
         return root;
      }
      
      // successfully removed and rebalanced at this and lower levels;
      // now adjust height
      root.setHeight( 
            Math.max( heightOf(root.l_child), heightOf(root.r_child))
            + 1);
      return root;
   }
}
