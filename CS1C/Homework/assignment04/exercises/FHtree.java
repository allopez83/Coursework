package assignment04.exercises;

public class FHtree<E>
{
   private int m_size;
   FHtreeNode<E> m_root;
   
   public FHtree() { clear(); }
   public boolean empty() { return (m_size == 0); }
   public int size() { return m_size; }
   public void clear() { m_size = 0; m_root = null; }

   // several items not shown.

   public FHtreeNode<E> add_child(FHtreeNode<E> tree_node, E x)
   {
      return null;
      // ...
   }

   public FHtreeNode<E> find(E x)
   {
      return find(m_root, x, 0);
   }

   public FHtreeNode<E> find(FHtreeNode<E> root, E x, int level)
   {
      return null;
      // ...
   }
}