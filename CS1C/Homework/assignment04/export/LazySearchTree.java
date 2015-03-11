package assignment04.export;

import java.util.*;
import cs_1c.*;

/**
 * A binary tree that utilizes lazy deletion. Lazy deletion is where nodes are
 * marked as deleted (flagged), but not actually removed until specified.
 * 
 * Note that the term "flagged" denotes that a node's 'deleted' boolean is true.
 * This is a result of soft deletion, which only marks the node for deletion but
 * does not actually remove it. Actual removal is done with hard deletion.
 * 
 * The methods findMin() and findMax() have been successfully tested against
 * disgusting and confusing trees!
 * 
 * @author HW
 * 
 * @param <E> type of data that is stored in the tree
 */
public class LazySearchTree<E extends Comparable<? super E>> implements
      Cloneable
{
   /** Total number of undeleted nodes in the tree */
   protected int m_size;
   /** Total number of undeleted and deleted nodes in the tree */
   protected int m_size_hard;
   /** Reference to the tree's root node */
   protected LazySTNode<E> m_root;

   /**
    * Initialize a new LazySearchTree by calling clear()
    */
   public LazySearchTree() { clear(); }
   /**
    * Checks if the tree is empty. True means the tree is empty, false means the
    * tree is not empty.
    * @return boolean response with true being empty and false being not empty
    */
   public boolean empty() { return (m_size == 0); }
   /**
    * Retrieves the number of undeleted nodes in the tree
    * @return an integer representing the number of nodes in the tree
    */
   public int size() { return m_size; }
   /**
    * Retrieves the number of undeleted and deleted nodes in the tree
    * @return an integer representing the number of undeleted and deleted nodes
    *         in the tree
    */
   public int sizeHard() { return m_size_hard; }
   /**
    * Chops down whatever tree exists. Done by setting the size counter to zero, and root as
    * null. By setting root as null, Java's garbage collector will destroy
    * whatever is in the tree since there are no nodes pointing to the data.
    */
   public void clear() { m_size = 0; m_size_hard = 0; m_root = null; }
   /**
    * Finds the height of the tree using a method with recursion
    * @return integer representing the tree's height
    */
   public int showHeight() { return findHeight(m_root, -1); }

   /**
    * Find the minimum value in the tree using a recursion method
    * @return data contained inside the minimum node
    */
   public E findMin()
   {
      if (m_root == null)
         throw new NoSuchElementException();
      return findMin(m_root).data;
   }

   /**
    * Find the maximum value in the tree using a recursion method
    * @return data contained inside the maximum
    */
   public E findMax()
   {
      if (m_root == null)
         throw new NoSuchElementException();
      return findMax(m_root).data;
   }

   /**
    * Finds a node containing the given data. Returns the node's data (the same
    * data as entered) to the caller. If not found, this method will return
    * null.
    * @param x data that is to be searched for in the tree
    * @return The data node that was searched for. Essentially the same data
    *         that was entered. If not found, returns null.
    */
   public E find(E x)
   {
      LazySTNode<E> result_node;
      result_node = find(m_root, x);
      if (result_node == null)
         throw new NoSuchElementException();
      return result_node.data;
   }

   /**
    * Checks if the data is contained in the tree using find().
    * @param x data to be searched for
    * @return true if the tree has it, false if the tree doesn't
    */
   public boolean contains(E x)
   {
      return find(m_root, x) != null;
   }

   /**
    * Insert data into the tree. The data will be wrapped into a node and
    * inserted in order onto the tree. The method will return true/false
    * depending on if the node was added to the tree. False does not necessarily
    * mean the data was not entered, since the node might have already existed.
    * It just means the size did not change.
    * @param x data to be inserted into the tree
    * @return true if data entered, false if data was not entered. Checked by
    *         comparing size before/after.
    */
   public boolean insert(E x)
   {
      int old_size = m_size;
      m_root = insert(m_root, x);
      return (m_size != old_size);
   }

   /**
    * Removes a node from the tree using 'lazy deletion'. The node will be
    * marked as deleted, but still exist in the tree.
    * @param x data that will be deleted
    * @return true if the node has been marked as deleted, false if operation
    *         failed
    */
   public boolean remove(E x)
   {
      LazySTNode<E> target = find(m_root, x);
      return removeSoft(target);
   }

   /**
    * Goes through the tree from low to highest value, executing a specified
    * action at each node.
    * @param func an object whose data type is defined by the client. This
    *           'func' object needs to come from a class that implements
    *           'Traverser'. This func object contains the action the client
    *           wants executed at each node.
    */
   public <F extends Traverser<? super E>> void traverse(F func)
   {
      traverse(func, m_root);
   }

   /**
    * Creates a deep copy of the specified tree.
    */
   public Object clone() throws CloneNotSupportedException
   {
      @SuppressWarnings("unchecked")
      LazySearchTree<E> cloned_tree = (LazySearchTree<E>) super.clone();
      cloned_tree.clear(); // can't point to other's data

      cloned_tree.m_root = cloneSubtree(m_root);
      cloned_tree.m_size = m_size;
      cloned_tree.m_size_hard = m_size_hard;
      cloned_tree.set_my_roots(cloned_tree.m_root);

      return cloned_tree;
   }

   /**
    * Prunes the tree. Done by removing all nodes flagged for deletion, meaning
    * its deleted boolean is true. This method will call another recursive
    * method which execute the actual deletion. Note that after this delete,
    * data is permanently lost. Client is advised to check if m_size and
    * m_size_hard are zero, if possible.
    */
   public void collectGarbage()
   {
      // Call recursive method to remove nodes
      removeHard(m_root);
   }

   // private helper methods ----------------------------------------
   /**
    * Uses recursion to find the 'minimum' node by continually going down the
    * left side until the next left pointer is null. If the node provided in
    * null in the first place, then returns null.
    * @param root node that the recursion is currently on. The first inserted
    *           value should be the 'top' of a selected tree or subtree.
    * @return Null if the given root is null. Otherwise it goes to the minimum
    *         node and returns a reference of it to the caller.
    */
   protected LazySTNode<E> findMin(LazySTNode<E> root)
   {
      // If this tree is empty
      if (root == null)
         return null;

      LazySTNode<E> possible_node;
      // If the node is deleted, l_child is null, but r_child exists
      if (root.deleted && root.l_child == null && root.r_child != null)
         // There's something to the right, check there
         possible_node = findMin(root.r_child);
      // If the node is deleted and is also a leaf
      else if (root.deleted && root.l_child == null && root.r_child == null)
         // Dead end, return the node to prevent null return
         return root; // Root is deleted, but that'll be sorted out later
      // If left child is null, meaning this node is the min. Also, the node
      // isn't flagged so it exists and there aren't any tricks here
      else if (root.l_child == null && !root.deleted)
         // This is the minimum value, return to client
         return root;
      // Otherwise, which is almost all cases, continue as normal
      else
         // Cleared to continue recursion
         possible_node = findMin(root.l_child);

      // In the rare case that the possible node is flagged, the current node is
      // also flagged, but a right child exists on the current node
      if (possible_node.deleted && root.deleted && root.l_child != null
            && root.r_child != null)
         // Try the right child and to see if it gives a value
         possible_node = findMin(root.r_child);

      // If the possible node is flagged, meaning it is 'not there'
      if (possible_node.deleted)
         // Return parent instead
         return root;

      // End of recursion
      return possible_node;
   }

   /**
    * Uses recursion to find the 'maximum' node by continually going down the
    * right side until the next right pointer is null. If the node provided in
    * null in the first place, then returns null.
    * @param root node that the recursion is currently on. The first inserted
    *           value should be the 'top' of a selected tree or subtree.
    * @return Null if the given root is null. Otherwise it goes to the maximum
    *         node and returns a reference of it to the caller.
    */
   protected LazySTNode<E> findMax(LazySTNode<E> root)
   {
      // If this tree is empty
      if (root == null)
         return null;

      LazySTNode<E> possible_node;
      // If the node is deleted, r_child is null, but l_child exists
      if (root.deleted && root.r_child == null && root.l_child != null)
         // There's something to the left, check there
         possible_node = findMax(root.l_child);
      // If the node is deleted and is also a leaf
      else if (root.deleted && root.r_child == null && root.l_child == null)
         // Dead end, return the node to prevent null return
         return root; // Root is deleted, but that'll be sorted out later
      // If right child is null, meaning this node is the max. Also, the node
      // isn't flagged so it exists and there aren't any tricks here
      else if (root.r_child == null && !root.deleted)
         // This is the minimum value, return to client
         return root;
      // Otherwise, which is almost all cases, continue as normal
      else
         // Cleared to continue recursion
         possible_node = findMax(root.r_child);

      // In the rare case that the possible node is flagged, the current node is
      // also flagged, but a left child exists on the current node
      if (possible_node.deleted && root.deleted && root.r_child != null
            && root.l_child != null)
         // Try the left child and to see if it gives a value
         possible_node = findMax(root.l_child);

      // If the possible node is flagged, meaning it is 'not there'
      if (possible_node.deleted)
         // Return parent instead
         return root;

      // End of recursion
      return possible_node;
   }

   /**
    * Uses recursion to find the height of the tree. The first call should
    * insert the node whose height is in question, and zero for the height
    * @param tree_node node whose height is being calculated. Should be the
    *           target initially
    * @param height the current height counted to. Should be zero initially.
    * @return An integer representing the height of the higher end. If the
    *         recursion has reached to end of a tree, it returns the height so
    *         far.
    */
   protected int findHeight(LazySTNode<E> tree_node, int height)
   {
      int left_height, right_height;
      // Case A: node is null, meaning the end has been reached
      // Case B: node is soft deleted, and it is also a leaf
      if (tree_node == null
            || (tree_node.deleted && tree_node.l_child == null && tree_node.r_child == null))
         return height;
      height++;
      left_height = findHeight(tree_node.l_child, height);
      right_height = findHeight(tree_node.r_child, height);
      return (left_height > right_height) ? left_height : right_height;
   }

   /**
    * Uses recursion to search for a node that contains the given data. On the
    * first call, root should be the root node of the tree or subtree that is to
    * be searched. x would refer to the data that is being searched for. If the
    * node is not found, or the node provided is null in the first place, then
    * the method returns null.
    * @param root initially is the node that the search will start from.
    *           Afterwards, it becomes the node that the search is on.
    * @param x data that is being searched for
    * @return returns the node that contains the data being searched for. If not
    *         found, return null.
    */
   protected LazySTNode<E> find(LazySTNode<E> root, E x)
   {
      // Avoid calling compareTo() more than once
      int compare_result;

      // If this node doesn't exist
      if (root == null)
         return null;

      // Ask for directions: see where the target is relative to current node
      compare_result = x.compareTo(root.data);
      // If target is less than current node
      if (compare_result < 0)
         return find(root.l_child, x); // Go left
      // If target is greater than current node
      else if (compare_result > 0)
         return find(root.r_child, x); // Go right

      // If program reaches here, the node has been found!
      // So, check if the node is flagged for deletion
      else if (compare_result == 0 && root.deleted)
         // Node is flagged, so for the client, it's basically 'not here'
         return null;

      return root; // found
   }

   /**
    * Uses recursion to insert data into the tree. The data is wrapped into a
    * node object and placed in the tree in order.
    * @param root the node that the insert method is on. Should initially be the
    *           tree's root node.
    * @param x data that is to be inserted into the tree
    * @return location of a node, which one is returned varies on the
    *         conditions. Generally it's the node that is created.
    */
   protected LazySTNode<E> insert(LazySTNode<E> root, E x)
   {
      // If the tree is empty, meaning this is the first node in the tree
      if (m_size_hard == 0)
      {
         m_root = new LazySTNode<E>(x, null, null, null);
         m_size++;
         m_size_hard++;
         m_root.my_root = m_root;
         return m_root;
      }

      // Used to avoid calling multiple comparisons
      int compare_result;

      // Found an appropriate spot!
      if (root == null)
      {
         // Increase size to show the added node
         m_size++;
         m_size_hard++;
         // Create the node and assign the my_root pointer
         LazySTNode<E> new_node = new LazySTNode<E>(x, null, null, m_root);
         // Return the node so it can be attached to the tree
         return new_node;
      }
      // Store compareTo() result to avoid multiple calls
      compare_result = x.compareTo(root.data);
      // If the data is less than the current node, go left and map it out
      if (compare_result < 0)
         root.l_child = insert(root.l_child, x);
      // If the data is greater than the current node, go right and map it out
      else if (compare_result > 0)
         root.r_child = insert(root.r_child, x);

      // If program reaches here without inserting, there is already a node here
      // So, check if the node is flagged for deletion
      else if (compare_result == 0 && root.deleted)
      {
         // Remove deletion flag, in other words, restore the node
         root.deleted = false;
         m_size++;
      }

      return root;
   }

   /**
    * Soft removes a node from the tree. Done by changing the deleted boolean to
    * true, signaling that the node has been 'removed'.
    * @param node the node to be deleted
    * @return true if the node has been flagged, false if the node didn't exist
    *         or is already flagged
    */
   protected boolean removeSoft(LazySTNode<E> tree_node)
   {
      if (tree_node != null && !tree_node.deleted)
      {
         tree_node.deleted = true;
         m_size--;
         return true;
      }
      return false;
   }

   /**
    * Uses recursion to traverse through the tree's branches and hard remove
    * nodes that are flagged for deletion. Flagged nodes have their 'deleted'
    * boolean as true. If the node has children, they are arranged to
    * accommodate for the new format.
    * @param root the node that the method is on. Should initially be the root
    *           node of the tree, or of a targeted subtree.
    * @return location of a node, used to recreate references and accommodate
    *         for the removed node
    */
   protected LazySTNode<E> removeHard(LazySTNode<E> root)
   {
      // If the current node is null, the target won't be here
      if (root == null)
         return null;

      // Recurse to the left side of the tree or subtree
      root.l_child = removeHard(root.l_child);
      // Recurse to the right side of the tree or subtree
      root.r_child = removeHard(root.r_child);
      // Check if node is flagged for deletion, if yes then do the deletion
      if (root.deleted)
      {
         // Found the node, and it has children on L and R
         if (root.l_child != null && root.r_child != null)
         {
            LazySTNode<E> replacement = findMin(root.r_child);
            // Find min on right side, use it to replace current node so the
            // entire subtree attached to current node isn't deleted
            root.data = replacement.data;
            // Now flag replacement for deletion
            replacement.deleted = true;
            // Unflag this node to prevent deleting it
            root.deleted = false;
            // Search for and remove the replacement node
            removeHard(root);
         }
         // Or the target node has one or no children
         else
         {
            // Replace target node with child, if no children, makes node null
            root = (root.l_child != null) ? root.l_child : root.r_child;
            // Decrease size to reflect the change
            m_size_hard--;
         }
      }
      // Done, return reference so tree is not destroyed
      return root;
   }

   /**
    * Recursively goes to each element of the tree from lowest value to highest
    * value, executing a client-specified action each time.
    * @param func an object containing the action the client wants executed on
    *           each object.
    * @param tree_node root node the recursion is on. Initially, this is the
    *           root node of the entire tree
    */
   protected <F extends Traverser<? super E>> void traverse(F func,
         LazySTNode<E> tree_node)
   {
      // There is no node here
      if (tree_node == null)
         return;

      // Go to the left side of the tree or subtree
      traverse(func, tree_node.l_child);
      // If the node has not been flagged
      if (!tree_node.deleted)
         // Execute action on current node
         func.visit(tree_node.data);
      // Go to the right side of the tree or subtree
      traverse(func, tree_node.r_child);
   }

   /**
    * Recursively clones each node in a tree, returning the pointer to the root
    * node of the cloned tree
    * @param root node the method is currently on. This is initially the root
    *           node of the original tree being cloned
    * @return null if root is null, otherwise returns a pointer to the node that
    *         was cloned. In the end of recursion, returns the root node of
    *         cloned tree.
    */
   protected LazySTNode<E> cloneSubtree(LazySTNode<E> root)
   {
      LazySTNode<E> new_node;
      if (root == null)
         return null;

      // does not set my_root which must be done by caller
      new_node = new LazySTNode<E>
      (
         root.data, 
         cloneSubtree(root.l_child), 
         cloneSubtree(root.r_child),
         null
      );
      return new_node;
   }

   /**
    * Sets the my_roots pointer in each node so that they point to the correct
    * node. Used for cloning.
    * @param tree_node the node that the operation is on. Initially, tree_node
    *           should be pointing to the root node of the newly cloned tree.
    */
   protected void set_my_roots(LazySTNode<E> tree_node)
   {
      // Oops, one of the following is null, better go back
      if (m_root == null || tree_node == null)
         return;

      // Go to the left side of the tree or subtree
      set_my_roots(tree_node.l_child);
      // Go to the right side of the tree or subtree
      set_my_roots(tree_node.r_child);
      // Set the root
      tree_node.my_root = m_root;
   }

   /**
    * A nested class for node objects. LazySTNodes are for search trees that use
    * lazy deletion.
    * @author HW
    * 
    * @param <E> type of data that is contained in the node
    */
   @SuppressWarnings("hiding")
   class LazySTNode<E extends Comparable<? super E>>
   {
      // Use public access so the tree or other classes can access members

      // Pointer to the child node on L and R (This is a binary tree...)
      public LazySTNode<E> l_child, r_child;
      // Data that is contained in the Node
      public E data;
      // Pointer to the root node of the tree, needed for error testing
      public LazySTNode<E> my_root;
      // Flag representing if this node is marked to be deleted
      public boolean deleted;

      /**
       * Creates a new binary tree node using given parameters
       * @param d data contained in the node
       * @param lt child on the left
       * @param rt child on the right
       * @param root root node of the tree this node belongs to
       */
      public LazySTNode(E d, LazySTNode<E> lt, LazySTNode<E> rt,
            LazySTNode<E> root)
      {
         data = d;
         l_child = lt;
         r_child = rt;
         my_root = root;
         deleted = false;
      }

      /**
       * Creates a new binary tree node with null data, and null child on the
       * left and right
       */
      public LazySTNode()
      {
         this(null, null, null, null);
      }

      // function stubs -- for use only with AVL Trees when we extend
      public int getHeight()
      {
         return 0;
      }

      boolean setHeight(int height)
      {
         return true;
      }
   }
}
