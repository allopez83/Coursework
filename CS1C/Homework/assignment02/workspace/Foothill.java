package assignment02.workspace;

public class Foothill
{
   final static int TEST_SIZE = 10;
   static int errNum = 1;

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public static void main(String[] args)
   {
      System.out.println("Test Start\n");
      SparseMat testMat = new SparseMat(TEST_SIZE, TEST_SIZE, 0.);
      // Setting entries
      testMat.set(0, 0, "Zero");
      testMat.set(1, 1, 11.1);
      testMat.set(2, 2, 22.2);
      testMat.set(3, 3, 33.3);
      testMat.set(4, 4, 44.4);
      testMat.set(5, 5, 55.5);
      testMat.set(6, 6, 66.6);
      testMat.set(8, 8, 88.8);
      testMat.set(9, 9, 99.9);

      // Replacing entries
      testMat.set(5, 5, 5.);

      // Putting multiple entries in a row and out of order testing
      testMat.set(2, 5, 2.5);
      testMat.set(2, 0, 2.0);
      testMat.set(2, 9, 2.9);
      testMat.set(2, 3, 2.3);
      testMat.set(6, 9, 6.9);

      System.out.println("Error testing");

      // Insert out of bounds, should fail silently
      if (!testMat.set(5, 10, 10.)) // Too big horizontal
         System.out.println("err" + errNum + " set() out of bounds");
      errNum++;
      if (!testMat.set(5, -1, 10.)) // Too small horizontal
         System.out.println("err" + errNum + " set() out of bounds");
      errNum++;
      if (!testMat.set(10, 5, 10.)) // Too big vertical
         System.out.println("err" + errNum + " set() out of bounds");
      errNum++;
      if (!testMat.set(-1, 5, 10.)) // Too small vertical
         System.out.println("err" + errNum + " set() out of bounds");
      errNum++;

      // Retrieve out of bounds, should fail
      try
      {
         testMat.get(5, 10); // Too big horizontal
      } catch (Exception e)
      {
         System.out.println("err" + errNum + " get() out of bounds");
      }
      errNum++;
      try
      {
         testMat.get(5, -1); // Too small horizontal
      } catch (Exception e)
      {
         System.out.println("err" + errNum + " get() out of bounds");
      }
      errNum++;
      try
      {
         testMat.get(10, 5); // Too big vertical
      } catch (Exception e)
      {
         System.out.println("err" + errNum + " get() out of bounds");
      }
      errNum++;
      try
      {
         testMat.get(-1, 5); // Too small vertical
      } catch (Exception e)
      {
         System.out.println("err" + errNum + " get() out of bounds");
      }
      errNum++;

      System.out.println("Print test");

      // Show the whole list
      testMat.showSubSquare(0, TEST_SIZE);
      // Show top left corner
      testMat.showSubSquare(0, 5);
      // Show bottom right corner + clipping test
      testMat.showSubSquare(5, 10);

      System.out.println("Clone test, should NOT say 'new'");

      // Clone testMat
      SparseMat clonedMat = testMat.clone();
      testMat.set(1, 1, "new");
      clonedMat.showSubSquare(0, 3);

      System.out.println("Clear test");

      // Try clearing the matrix
      testMat.clear();
      testMat.set(6, 6, "new");
      testMat.copyValue(6, 6, 5, 2);
      testMat.showSubSquare(0, TEST_SIZE);

      // Print the clone
      clonedMat.showSubSquare(0, TEST_SIZE);

      System.out.print("Test End");
   }
}
