import java.awt.Rectangle;
import java.util.Comparator;
import java.util.Random;

/**
 * 1.6 (15 Points) Define a class that provides getLength and getWidth methods.
 * Using the findMax routines in Figure 1.18 (listed below), write a main that
 * creates an array of Rectangle and finds the largest Rectangle first on the
 * basis of area, and then on the basis of perimeter. (15 Points)
 * 
 * getLength getWidth findMax array of Rectangles largest Rectangle - area and
 * parameter
 * 
 * @author Hansen Wu
 *
 */
public class findRectangle
{

   public static void main(String[] args)
   {
      // Set up, create rand Rectangles
      Rectangle[] recArray = new Rectangle[10];
      Rectangle r;
      Random randLen = new Random(), randWid = new Random();
      int wid, len;
      for (int i = 0; i < 10; i++)
      {
         wid = randWid.nextInt(19) + 1;
         len = randLen.nextInt(19) + 1;
         // Note that first param is width, second is height (aka len)
         r = new Rectangle(wid, len);
         recArray[i] = r;
         // Print out data to confirm results
         System.out.println(wid + ", " + len + " => " + wid * len + ", " + (wid + len));
      }
      r = new Rectangle();

      // Largest area
      Rectangle maxArea = findMax(recArray, new RecCompareArea());
      int area = maxArea.height * maxArea.width;
      System.out.println("Max area Rectangle is " + maxArea.height + ", "
            + maxArea.width + " and has area " + area);

      // Largest perimeter
      Rectangle maxPer = findMax(recArray, new RecComparePerimeter());
      int per = maxPer.height + maxPer.width;
      System.out.println("Max perimeter Rectangle is " + maxPer.height + ", "
            + maxPer.width + " and has perimeter " + per);
   }

   /**
    * Finds max in array by using provided compare()
    */
   public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp)
   {
      int maxIndex = 0;
      
      for (int i = 1; i < arr.length; i++)
         if (cmp.compare(arr[i], arr[maxIndex]) > 0)
            maxIndex = i;
      return arr[maxIndex];
   }
   
   
//   /**
//    * Finds max in array by using compare()
//    * Original method provided for HW assignment
//    */
//   public static <AnyType> AnyType findMaxOriginal(AnyType[] arr, Comparator<? super AnyType> cmp)
//   {
//      int maxIndex = 0;
//      
//      for (int i = 1; i < arr.length; i++)
//         if (cmp.compare(arr[i], arr[maxIndex]) > 0)
//            maxIndex = i;
//      return arr[maxIndex];
//   }
}


/**
 * compares rec area
 */
class RecCompareArea implements Comparator<Rectangle>
{
   public int compare(Rectangle a, Rectangle b)
   {
      Integer aArea = a.height * a.width,
              bArea = b.height * b.width;
      return aArea.compareTo(bArea);
   }
}

/**
 * compares rec parameter
 */
class RecComparePerimeter implements Comparator<Rectangle>
{
   public int compare(Rectangle a, Rectangle b)
   {
      // Perimeter comparison can be shortened by ignoring the mutliply by 2
      Integer aPer = a.height + a.width,
              bPer = b.height + b.width;
      return aPer.compareTo(bPer);
   }
}

/**
 * Adds missing methods (getLength and getWidth) for Rectangle
 */
class RecMethods
{
   public static double getLength(Rectangle r)
   {
      return r.getHeight();
   }

   public static double getWidth(Rectangle r)
   {
      return r.getWidth();
   }
}





