package cs1c_0409week1;

public class BasicGenerics
{
   public static void main(String[] args)
   {
      int x = 9;
      String y = "Hello";
      DisplayInAsterisks(x);
      DisplayInAsterisks(y);
   }

   static <E> void DisplayInAsterisks(E x)
   {
      E temp = x;
      System.out.println("*** " + temp + " ***");
   }

   static <E extends Comparable<E>> void DisplayInAsterisks2(E x)
   {
      E temp = x;
      x.compareTo(temp);
      System.out.println("*** " + temp + " ***");
   }

}
