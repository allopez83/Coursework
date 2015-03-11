package assignment04.exercises;

public class FHtreeNode<E>
{
   // use protected access so the tree, in the same package,
   // or derived classes can access members
   protected FHtreeNode<E> first_child, sib, prev;
   protected E data;
   protected FHtreeNode<E> my_root; // needed to test for certain error

   public FHtreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld,
         FHtreeNode<E> prv)
   {
      first_child = chld;
      sib = sb;
      prev = prv;
      data = d;
      my_root = null;
   }

   public FHtreeNode()
   {
      this(null, null, null, null);
   }

   public E GetData()
   {
      return data;
   }

   // for use only by FHtree (default access)
   protected FHtreeNode(E d, FHtreeNode<E> sb, FHtreeNode<E> chld,
         FHtreeNode<E> prv, FHtreeNode<E> root)
   {
      this(d, sb, chld, prv);
      my_root = root;
   }
}