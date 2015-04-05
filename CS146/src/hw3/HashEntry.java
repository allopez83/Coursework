package hw3;

public class HashEntry<E>
{
   public E data;
   public int state;
   
   public HashEntry( E x, int st )
   {
      data = x;
      state = st;
   }

   public HashEntry()
   {
      this(null, FHhashQP.EMPTY);
   }
}
