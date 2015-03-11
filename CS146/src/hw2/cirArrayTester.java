package hw2;

public class cirArrayTester
{

   public static void main(String[] args)
   {
      cirArrayQueue<Integer> cirAr = new cirArrayQueue<Integer>();

      for (int i = 0; i < 15; i++)
         cirAr.enqueue(i);

      for (int i = 0; i < 15; i++){
         System.out.println(cirAr.currentSize);
         System.out.println(" > " + cirAr.dequeue());
      }
   }

}
