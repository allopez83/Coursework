package assignment05.workspace;

import cs_1c.FHs_treeNode;
import cs_1c.FHsearch_tree;

/**
 * A Splay Tree, extends FHsearch_tree, requires the data being inserted to
 * extend comparable.
 * 
 * @author HW
 * @param <E> type of data that will be inserted into the tree
 * 
 */
public class SplayTree<E extends Comparable<? super E>> extends
      FHsearch_tree<E>
{
   /**
    * Splays the tree and inserts the given data into it.
    * @param x the data to be inserted into the tree
    * @return true if the data has actually been added, false if there is no
    *         change to the tree
    */
   public boolean insert(E x)
   {
      // If the tree has no contents
      if (m_root == null)
      {
         // Create new node and make it the root
         m_root = new FHs_treeNode<E>(x, null, null);
         // Increment m_size to show the new node
         m_size++;
         // And that's it! Return true since a node has been added
         return true;
      }

      // Splay the tree for x
      m_root = splay(m_root, x);
      // x compared to the root node, prevents multiple calls to compareTo
      int comparison = x.compareTo(m_root.data);
      // New node that will be inserted
      FHs_treeNode<E> new_node;

      // If x is to the left of the root
      if (comparison < 0)
      {
         // Make a new node at the tree's root containing x data, l_child is the
         // root's right child, and r_child is root itself
         m_root = new FHs_treeNode<E>(x, m_root.l_child, m_root);
         // Remove possible unwanted data by clearing root's left child. This
         // data is already in new_node's left child.
         m_root.r_child.l_child = null;
         // Increment m_size to show the change
         m_size++;
         // A node has been successfully added, return true
         return true;
      }
      // If x is to the right of the root
      else if (comparison > 0)
      {
         // Make a new node at the tree's root containing x data, l_child is the
         // root, and r_child is root's right child
         new_node = new FHs_treeNode<E>(x, m_root, m_root.r_child);
         // Remove possible unwanted data by clearing original root's left
         // child. This data is already in new_node's right child.
         new_node.l_child.r_child = null;
         // Assign new_node as the tree's root
         m_root = new_node;
         // Increment m_size to show the change
         m_size++;
         // A node has been successfully added, return true
         return true;
      }
      // Otherwise, the node is already in the tree
      else
         // Therefore, return true
         return false;
   }

   /**
    * Attempt to remove a node containing the given data from the tree
    * @return true if the data was removed, false if there is no change
    */
   public boolean remove(E x)
   {
      // If the tree is empty
      if (m_root == null)
         // x cannot be here, return false
         return false;

      FHs_treeNode<E> new_node;
      // Splay the tree for x
      m_root = splay(m_root, x);
      // If x is not equal to the new root, meaning it's not in the tree
      if (x.compareTo(m_root.data) != 0)
         // x is not here, return false
         return false;

      // If the left is null, everything is on the right
      if (m_root.l_child == null)
         // Set root node's r_child as new_node, will not include x as it is the
         // root node itself
         new_node = m_root.r_child;
      else
      {
         // Set new_root as root's l_child, new_root will not include x
         new_node = m_root.l_child;
         // Splay for x in new_node, setting everything on the left
         new_node = splay(new_node, x);
         // Set new_node's r_child as m_root's r_child, removing x from the tree
         new_node.r_child = m_root.r_child;
      }
      // Change the root to apply the change
      m_root = new_node;
      // Node successfully removed, decrement m_size
      m_size--;
      // Operation succeeded, return true
      return true;
   }

   /**
    * Takes the root node and returns the data contained inside
    * @return data contained inside the root node
    */
   public E showRoot()
   {
      if (m_root == null)
         return null;
      return m_root.data;
   }

   /**
    * Replacement for the private find() method, the public method still calls
    * this.
    * 
    * @param root the node that the search will be conducted on. Should always
    *           be m_root.
    * @param x data to be searched for
    * @return node that has replaced the position of the inserted node, or null
    *         if the operation did not complete
    */
   protected FHs_treeNode<E> find(FHs_treeNode<E> root, E x)
   {
      // Check if tree is occupied
      if (root == null)
         // Tree does not have any nodes, return null
         return null;

      // Splay tree for x
      root = splay(root, x);
      // Apply changes to m_root
      m_root = root;
      // Check if the target node has risen to the root node
      if (x.compareTo(root.data) == 0)
         // Found x, return the node
         return root;
      // Otherwise x isn't in the tree, return null
      return null;
   }

   /**
    * Splay the tree, searching for the data 'x'. An assignment should be made
    * when calling this. Otherwise, it's redundant. Usually this is done by
    * assigning the return value of this method to the tree's root.
    * @param root the node the splay method is on. When splay is called, this
    *           should be the root node of the entire tree.
    * @param x the data to be searched for while splaying
    * @return the new node that has replaced the one passed in. If the tree's
    *         root node was inserted, then the tree's new root is returned.
    */
   protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x)
   {
      // References to the L and R trees and their min/max node
      FHs_treeNode<E> left_tree = null, right_tree = null,
            left_tree_max = null, right_tree_min = null;
      // Make sure the node is not null
      while (root != null)
      {
         // Store compare result to prevent multiple calls
         int comparison = x.compareTo(root.data);
         // x is to the left of the node
         if (comparison < 0)
         {
            // If there isn't even a left child, break here
            if (root.l_child == null)
               break;
            // If x is also to the left of the node's l_child
            if (x.compareTo(root.l_child.data) < 0)
            {
               // Do a zig zig
               root = rotateWithLeftChild(root);
               // x is left of new root, but if l_child is null, break here
               if (root.l_child == null)
                  break;
            }

            // Now to add root to the R tree, or zig zag depending on situation.
            // If R tree is not occupied yet, assign it the root
            if (right_tree == null)
            {
               // Create and add new node with desired values from root
               right_tree = new FHs_treeNode<E>(root.data, null, root.r_child);
               // Assign right_tree_min to R tree to add nodes later
               right_tree_min = right_tree;
               // Move root to show the change
               root = root.l_child;
            }
            // Otherwise, R tree already has nodes
            else
            {
               // Create and add new node with desired values from root
               right_tree_min.l_child = new FHs_treeNode<E>(root.data, null, root.r_child);
               // Update right_tree_min and root
               right_tree_min = right_tree_min.l_child;
               root = root.l_child;
            }
         }
         // x is to the right of the node
         else if (comparison > 0)
         {
            // If root's r_child doesn't even exist, break here
            if (root.r_child == null)
               break;
            // If x is also to the right of root's r_child
            if (x.compareTo(root.r_child.data) > 1)
            {
               // Do a zig zig
               root = rotateWithRightChild(root);
               // x is right of new root, but if r_child is null, break here
               if (root.r_child == null)
                  break;
            }

            // Now to add root to the L tree, or zig zag depending on situation
            // If L tree is not occupied yet, assign it the root
            if (left_tree == null)
            {
               // Create and add new node with desired values from root
               left_tree = new FHs_treeNode<E>(root.data, root.l_child, null);
               // Assign left_tree_max to L tree to add nodes later
               left_tree_max = left_tree;
               // Move root to show the change
               root = root.r_child;
            }
            // Otherwise, L tree already has nodes
            else
            {
               // Create and add new node with desired values from root
               left_tree_max.r_child = new FHs_treeNode<E>(root.data, root.l_child, null);
               // Update left_tree_max and root
               left_tree_max = left_tree_max.r_child;
               root = root.r_child;
            }
         }
         // Otherwise, x is the root, splaying is done, break here
         else
            break;
      }

      // Now to reassemble the tree, starting with the left side
      if (left_tree != null)
      {
         // Add l_child of x to appropriate tree
         left_tree_max.r_child = root.l_child;
         // Attach L tree to the root
         root.l_child = left_tree;
      }
      // Check if R tree has anything
      if (right_tree != null)
      {
         // Add r_child of x to appropriate tree
         right_tree_min.l_child = root.r_child;
         // Attach R tree to the root
         root.r_child = right_tree;
      }

      // Return the tree's new root
      return root;
   }

   /**
    * Does a single left rotation using the given node as the center of
    * rotation.
    * @param k2 the node to be used as the center of rotation
    * @return the new node occupying k2's original location
    */
   protected FHs_treeNode<E> rotateWithLeftChild(FHs_treeNode<E> k2)
   {
      FHs_treeNode<E> k1 = k2.l_child;
      k2.l_child = k1.r_child;
      k1.r_child = k2;
      return k1;
   }

   /**
    * Does a single right rotation using the given node as the center of
    * rotation
    * @param k2 the node to be used as the center of rotation
    * @return the new node occupying k2's original location
    */
   protected FHs_treeNode<E> rotateWithRightChild(FHs_treeNode<E> k2)
   {
      FHs_treeNode<E> k1 = k2.r_child;
      k2.r_child = k1.l_child;
      k1.l_child = k2;
      return k1;
   }
}
