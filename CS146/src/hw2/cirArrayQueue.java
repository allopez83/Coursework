package hw2;

class cirArrayQueue<E>
{
   private StackedNode<E> start, end;
   private int size;
   int currentSize;
   private int defaultSize = 10;

   // Circular Array Stuff

   /**
    * Constructor
    */
   public cirArrayQueue()
   {
      start = null;
      end = null;
      size = defaultSize;
      currentSize = 0;
   }

   /**
    * Adds to end
    * @param data
    */
   public void enqueue(E data)
   {
      if (start == null && end == null)
      {
         end = new StackedNode<E>(data, null, null);
         start = end;
         currentSize = 1;
      }
      else
      {
         end.insertBehind(data);
         end = end.back;
         currentSize++;
         if (currentSize == 11) // trim if at limit
            this.dequeue();
      }
   }

   /**
    * Removes start element
    * @return
    */
   public E dequeue()
   {
      if (currentSize == 0)
      {
         return null;
      }
      if (currentSize == 1)
      {
         start = null;
         end = null;
         currentSize = 0;
         return null;
      }
      StackedNode<E> oldStart = start;
      start = start.back;
      E data = oldStart.remove();
      currentSize--;
      return data;
   }

   /**
    * Node class
    */
   private class StackedNode<E>
   {
      private StackedNode<E> front, back;
      private E data;

      /**
       * New null node
       */
      public StackedNode()
      {
         front = null;
         back = null;
         data = null;
      }

      /**
       * Create new node containing given data
       * @param x data it contains
       * @param fr node in front
       * @param ba node behind
       */
      public StackedNode(E x, StackedNode<E> fr, StackedNode<E> ba)
      {
         front = fr;
         back = ba;
         data = x;
      }

      /**
       * Creates node with x data and places right behind this one
       * @param x
       */
      public void insertBehind(E x)
      {
         StackedNode<E> newNode = new StackedNode<E>(x, this, null);
         this.back = newNode;
      }

      /**
       * Removes this node
       * @return
       */
      public E remove()
      {
         if (front != null)
            front.back = back;
         if (back != null)
            back.front = front;
         return data;
      }
   }
}