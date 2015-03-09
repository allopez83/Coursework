package assignment04.references;

public class LazySTNode<E extends Comparable<? super E>>
{
   // use public access so the tree or other classes can access members
   public LazySTNode<E> l_child, r_child;
   public E data;
   public LazySTNode<E> my_root; // needed to test for certain error

   public LazySTNode(E d, LazySTNode<E> lt, LazySTNode<E> rt)
   {
      l_child = lt;
      r_child = rt;
      data = d;
   }

   public LazySTNode()
   {
      this(null, null, null);
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
