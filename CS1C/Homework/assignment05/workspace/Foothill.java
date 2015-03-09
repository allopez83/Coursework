package assignment05.workspace;

import java.util.NoSuchElementException;

import cs_1c.*;

public class Foothill
{
   /*
    * User-input parameter(s), please follow the instructions or the test
    * program might crash/fail/blow up the world. Thank you!
    */
   // Controls the display of the titles of each error test. True will display
   // the titles, false will result in a compressed view.
   private static final boolean PRINT_TEST_TITLE = false;

   /*
    * The "do not change" parameters. These can actually be changed, but they're
    * not exactly intended to be changed..
    */
   // This is the tree itself
   private static SplayTree<Integer> tree = new SplayTree<Integer>();
   // This uses traverser to print out the entire tree
   private static TreePrinter<Integer> treePrint = new TreePrinter<Integer>();
   // Used to simulate and test m_size
   private static int i = 0;
   // Used to store boolean results
   private static boolean resultB;
   // Stores integer results
   private static Integer resultI; 

   /**
    * The test program itself
    */
   public static <E> void main(String[] args)
   {
      print("Start test:\n");

      print("Initial tree contents:");
      tree.traverse(treePrint);

      print("\nEmpty tree tests");
      
      errTest("Remove 5: " + tree.remove(5), !tree.remove(5));
      errTest("Retrieving root: " + tree.showRoot(), tree.showRoot() == null);
      if (PRINT_TEST_TITLE) print("Superclass find 5: ");
      // Here's a messy try/catch! Oh boy!
      try
      {
         print("" + tree.find(5));
         print("!!> Error");
      } catch (NoSuchElementException e)
      {
         print("  > Pass");
      }
      
      print("\nTree insertion tests");
   
      print("Adding content into tree (0 to 90 increment 10)");
      for (int k = 0; k < 100; k += 10)
      {
         resultB = tree.insert(k);
         i++;
         errTest("Adding " + k + " into tree: " + resultB, resultB);
         errTest("m_size check: " + tree.size(), tree.size() == i);
      }
      print("Tree contents after adding values:");
      tree.traverse(treePrint); print("");
      errTest("Tree root: " + tree.showRoot(), tree.showRoot() == 90);
      print("\nAdding content into tree (45 to 5 decrement 10)");
      for (int k = 45; k > -5; k -= 10)
      {
         resultB = tree.insert(k);
         i++;
         errTest("Adding " + k + " into tree: " + resultB, resultB);
         errTest("m_size incrementing properly: " + tree.size(), tree.size() == i);
      }
      print("Tree contents after adding low values:");
      tree.traverse(treePrint); print("");
      errTest("Tree root: " + tree.showRoot(), tree.showRoot() == 5);
      print("\nAdding content into tree (55 to 85 increment 10)");
      for (int k = 55; k < 95; k += 10)
      {
         resultB = tree.insert(k);
         i++;
         errTest("Adding " + k + " into tree: " + resultB, resultB);
         errTest("m_size incrementing properly: " + tree.size(), tree.size() == i);
      }
      errTest("Tree root (last node was 85): " + tree.showRoot(), tree.showRoot() == 85);
      print("Tree contents after adding high values:");
      tree.traverse(treePrint); print("");
      errTest("Tree root: " + tree.showRoot(), tree.showRoot() == 85);

      print("\nOccupied tree content tests");

      resultI = tree.find(20);
      errTest("Superclass's find 20: " + resultI, resultI.equals(20));
      errTest("Tree root: " + tree.showRoot(), tree.showRoot() == 20);
      resultB = tree.remove(20); i--;
      errTest("Remove 20: " + resultB, resultB);
      resultB = tree.remove(20);
      print("Tree without 20:");
      tree.traverse(treePrint); print("");
      errTest("Remove 20 again: " + resultB, !resultB);
      print("Attempt to find 20 (deleted): ");
      try
      {
         print("" + tree.find(20));
         print("!!> Error");
      } catch (NoSuchElementException e)
      {
         print("  > Pass");
      }
      errTest("Tree size without 20: " + tree.size(), tree.size() == i);
      resultB = tree.insert(20);
      i++;
      errTest("Add back 20: " + resultB, resultB);
      errTest("Tree size with 20: " + tree.size(), tree.size() == i);
      print("Tree with 20 re-added:");
      tree.traverse(treePrint); print("");

      print("\nTree deletion test");
      for (int k = 0; k < 89; k += 5)
      {
         resultB = tree.remove(k);
         i--;
         errTest("Removing " + k + " from tree: " + resultB, resultB);
         errTest("m_size decrementing properly: " + tree.size(), tree.size() == i);
      }
      errTest("Tree root (only node is 90): " + tree.showRoot(), tree.showRoot() == 90);
      errTest("Tree size (only node is 90): " + tree.size(), tree.size() == 1);

      print("\n\nEnd of test");
   }

   /**
    * This is simply used to shorten the System.out.println() call. Note that
    * the parameter needs to be a String
    * @param text String to print to console
    */
   private static void print(String text)
   {
      System.out.println(text);
   }

   /**
    * Prints out a message along with "pass" or "error" depending on the
    * condition. Used to simplify the testing code.
    * @param text message to print to console, describing what the test is
    * @param intent boolean representing the intended result of the test. For
    *           this method to work, the intended result should return true.
    *           That represents the idea that, for an operation's result,
    *           "yes, this is what I want to get from the operation"
    */
   private static void errTest(String text, boolean intent)
   {
      // Prints text
      if (PRINT_TEST_TITLE) print(text);
      // Prints pass or fail to tell client if the result is the intended result
      print((intent == true) ? "  > Pass" : "!!> Error");
   }
}

class TreePrinter<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print("[" + x + "] ");
   }
}