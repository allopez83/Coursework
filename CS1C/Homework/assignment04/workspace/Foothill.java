package assignment04.workspace;

import java.util.NoSuchElementException;

/**
 * Test class for LazySearchTree which implements 'lazy deletion'
 * 
 * @author HW
 *
 */
public class Foothill
{
   private static final boolean CONSOLE_ACTIONS = false;
   public static void main(String[] args) throws Exception
   {
      // Used for holding boolean results
      boolean b_result;
      
      System.out.println("Start test:\n");
      LazySearchTree<Integer> search_tree = new LazySearchTree<Integer>();
      PrintObject<Integer> int_printer = new PrintObject<Integer>();

      System.out.println("\nSize after creation: " + search_tree.size());
      if (search_tree.size() == 0)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      if (CONSOLE_ACTIONS) System.out.println("\nAdding integers 0 to 100, increment by ten...");
      search_tree.insert(60);

      search_tree.insert(10);
      search_tree.insert(0);
      search_tree.insert(40);
      search_tree.insert(50);
      search_tree.insert(30);
      search_tree.insert(20);
      
      search_tree.insert(70);
      search_tree.insert(100);
      search_tree.insert(80);
      search_tree.insert(90);
      
      System.out.println("\nAdded contents (should be 0-100 increment by 10):");
      search_tree.traverse(int_printer);

      @SuppressWarnings("unchecked")
      LazySearchTree<Integer> cloned_tree = (LazySearchTree<Integer>) search_tree.clone();
      System.out.println("\nCloning tree");

      System.out.println("\n\nCurrent tree height: " + search_tree.showHeight());
      if (search_tree.showHeight() == 4)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      System.out.println("\nMinimum: " + search_tree.findMin());
      if (search_tree.findMin() == 0)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nMaximum: " + search_tree.findMax());
      if (search_tree.findMax() == 100)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      if (CONSOLE_ACTIONS) System.out.println("\nRemoving 10, 20, 70, -50, and 200");
      
      b_result = search_tree.remove(10);
      System.out.println("\nDelete 10: " + b_result);
      if (b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      b_result = search_tree.remove(20);
      System.out.println("Delete 20: " + b_result);
      if (b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      b_result = search_tree.remove(70);
      System.out.println("Delete 70: " + b_result);
      if (b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      b_result = search_tree.remove(70);
      System.out.println("Re-delete 70: " + b_result);
      if (!b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      b_result = search_tree.remove(-50);
      System.out.println("Delete -50: " + b_result);
      if (!b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      b_result = search_tree.remove(200);
      System.out.println("Delete 200: " + b_result);
      if (!b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      System.out.println("\nCurrent contents (should reflect deletion):");
      search_tree.traverse(int_printer);
      
      System.out.println("\n\nCurrent tree height: " + search_tree.showHeight());
      if (search_tree.showHeight() == 4)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      search_tree.remove(90);
      System.out.println("\nTree height soft deletion test (node 90 removed, reinserted later): "
            + search_tree.showHeight());
      if (search_tree.showHeight() == 3)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      search_tree.insert(90);

      search_tree.remove(40);
      search_tree.remove(30);
      search_tree.insert(20);
      
      System.out.println("\nFind node 20: " + search_tree.find(20));
      if (search_tree.find(20) == 20)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      b_result = search_tree.contains(20);
      System.out.println("Tree contains node 20: " + b_result);
      if (b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      search_tree.remove(20);
      System.out.println("\nNode 20 removed");
      
      System.out.print("\nFind node 20: ");
      try
      {
         System.out.println(search_tree.find(20) + "\n");
         if (search_tree.find(20) == 20)
            System.out.println("!> Error");
      } catch (NoSuchElementException e)
      {
         System.out.println("\n > Success");
      }
      b_result = search_tree.contains(20);
      System.out.println("Tree contains node 20: " + b_result);
      if (!b_result)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      search_tree.insert(40);
      search_tree.insert(30);

      System.out.println("\nSize before garbage collection");
      System.out.println("Size: " + search_tree.size());
      if (search_tree.size() == 8)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Hard size: " + search_tree.sizeHard());
      if (search_tree.sizeHard() == 11)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      search_tree.collectGarbage();
      System.out.println("\nSize after garbage collection");
      System.out.println("Size: " + search_tree.size());
      if (search_tree.size() == 8)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Hard size: " + search_tree.sizeHard());
      if (search_tree.sizeHard() == 8)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      System.out.println("\nPrinting trimmed tree");
      search_tree.traverse(int_printer);
      
      System.out.println("\n\nPrinting clone of original tree");
      cloned_tree.traverse(int_printer);
      
      // Clone Testing //
      System.out.println("\n\nSize of Clone");
      System.out.println("Size: " + cloned_tree.size());
      if (cloned_tree.size() == 11)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Hard size: " + cloned_tree.sizeHard());
      if (cloned_tree.sizeHard() == 11)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      System.out.println("Root of clone: " + cloned_tree.m_root.data);
      if (cloned_tree.m_root.data == 60)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      // Now time for extensive max and min tests()//
      if (CONSOLE_ACTIONS) System.out.println("\nClearing tree");
      search_tree.clear();
      
      System.out.println("\nCreating new tree");
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 60");
      search_tree.insert(60);

      if (CONSOLE_ACTIONS) System.out.println("\nInsert 70-110 increment 10");
      for (int k = 70; k < 115; k+=10)
         search_tree.insert(k);
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 50-10 decrement 10");
      for (int k = 50; k > 5; k-=10)
         search_tree.insert(k);

      if (CONSOLE_ACTIONS) System.out.println("\nInsert 65-95 increment 10");
      for (int k = 65; k < 100; k+=10)
         search_tree.insert(k);
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 55-25 decrement 10");
      for (int k = 55; k > 20; k-=10)
         search_tree.insert(k);
         
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 11, 12, 15");
      search_tree.insert(12);
      search_tree.insert(11);
      search_tree.insert(15);
      
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 105, 108, 109");
      search_tree.insert(108);
      search_tree.insert(105);
      search_tree.insert(109);

      System.out.println("\nSize of new tree");
      System.out.println("Size: " + search_tree.size());
      if (search_tree.size() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Hard size: " + search_tree.sizeHard());
      if (search_tree.sizeHard() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      System.out.println("\nMinimum: " + search_tree.findMin());
      if (search_tree.findMin() == 10)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 110)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      //Deleting the 'wing tips'
      System.out.println("\nSoft-delete max/min nodes (10 and 110)");
      search_tree.remove(10);
      search_tree.remove(110);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 11)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 109)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nSoft-delete nodes 11 and 109");
      search_tree.remove(11);
      search_tree.remove(109);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 12)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 108)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nSoft-delete nodes 12 and 108");
      search_tree.remove(12);
      search_tree.remove(108);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 15)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 105)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nSoft-delete nodes 15 and 105");
      search_tree.remove(15);
      search_tree.remove(105);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 20)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 100)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      // Testing part 2: deleting half the wing
      System.out.println("\nSoft-delete nodes 20 and 100");
      search_tree.remove(20);
      search_tree.remove(100);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 95)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nSoft-delete nodes 25 and 95");
      search_tree.remove(25);
      search_tree.remove(95);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 30)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 90)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nSoft-delete nodes 30 and 90");
      search_tree.remove(30);
      search_tree.remove(90);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 35)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 85)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nSoft-delete nodes 35 and 85");
      search_tree.remove(35);
      search_tree.remove(85);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 40)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 80)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      // Testing part 3: jumping hurdles
      System.out.println("\nRestore nodes 15 and 105");
      search_tree.insert(15);
      search_tree.insert(105);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 15)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 105)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nRestore nodes 12 and 108");
      search_tree.insert(12);
      search_tree.insert(108);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 12)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 108)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nRestore nodes 11 and 109");
      search_tree.insert(11);
      search_tree.insert(109);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 11)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 109)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("\nRestore nodes 10 and 110");
      search_tree.insert(10);
      search_tree.insert(110);
      System.out.println("Minimum: " + search_tree.findMin());
      if (search_tree.findMin() == 10)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Maximum: " + search_tree.findMax());
      if (search_tree.findMax() == 110)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      
      // Part 4: More than half gone
      if (CONSOLE_ACTIONS) System.out.println("Adding back all nodes...");
      search_tree.insert(60);

      if (CONSOLE_ACTIONS) System.out.println("\nInsert 70-110 increment 10");
      for (int k = 70; k < 115; k+=10)
         search_tree.insert(k);
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 50-10 decrement 10");
      for (int k = 50; k > 5; k-=10)
         search_tree.insert(k);

      if (CONSOLE_ACTIONS) System.out.println("\nInsert 65-95 increment 10");
      for (int k = 65; k < 100; k+=10)
         search_tree.insert(k);
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 55-25 decrement 10");
      for (int k = 55; k > 20; k-=10)
         search_tree.insert(k);
         
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 11, 12, 15");
      search_tree.insert(12);
      search_tree.insert(11);
      search_tree.insert(15);
      
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 105, 108, 109");
      search_tree.insert(108);
      search_tree.insert(105);
      search_tree.insert(109);

      System.out.println("\nSize after reinserting all elements");
      System.out.println("Size: " + search_tree.size());
      if (search_tree.size() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Hard size: " + search_tree.sizeHard());
      if (search_tree.sizeHard() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      // Delete all left-wing nodes

      if (CONSOLE_ACTIONS) System.out.println("\nRemove 55-10 decrement 5");
      for (int k = 55; k > 9; k -= 5)
         search_tree.remove(k);
      if (CONSOLE_ACTIONS) System.out.println("\nRemoving 11 and 12");
      search_tree.remove(12);
      search_tree.remove(11);
      
      System.out.println("\nMinimum: " + search_tree.findMin());
      if (search_tree.findMin() == 60)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      if (CONSOLE_ACTIONS) System.out.println("\nRemove 60-100 decrement 5");
      for (int k = 60; k <102; k +=5)
         search_tree.remove(k);
      
      search_tree.remove(110);

      System.out.println("\nMinimum: " + search_tree.findMin());
      if (search_tree.findMin() == 105)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      search_tree.remove(105);
      System.out.println("\nMinimum: " + search_tree.findMin());
      if (search_tree.findMin() == 108)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      search_tree.remove(108);
      System.out.println("\nMinimum: " + search_tree.findMin());
      if (search_tree.findMin() == 109)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      //Part 5: Last wing deletion
      if (CONSOLE_ACTIONS) System.out.println("Adding back all nodes...");
      search_tree.insert(60);

      if (CONSOLE_ACTIONS) System.out.println("\nInsert 70-110 increment 10");
      for (int k = 70; k < 115; k+=10)
         search_tree.insert(k);
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 50-10 decrement 10");
      for (int k = 50; k > 5; k-=10)
         search_tree.insert(k);

      if (CONSOLE_ACTIONS) System.out.println("\nInsert 65-95 increment 10");
      for (int k = 65; k < 100; k+=10)
         search_tree.insert(k);
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 55-25 decrement 10");
      for (int k = 55; k > 20; k-=10)
         search_tree.insert(k);
         
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 11, 12, 15");
      search_tree.insert(12);
      search_tree.insert(11);
      search_tree.insert(15);
      
      if (CONSOLE_ACTIONS) System.out.println("\nInsert 105, 108, 109");
      search_tree.insert(108);
      search_tree.insert(105);
      search_tree.insert(109);

      System.out.println("\nSize after reinserting all elements");
      System.out.println("Size: " + search_tree.size());
      if (search_tree.size() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
      System.out.println("Hard size: " + search_tree.sizeHard());
      if (search_tree.sizeHard() == 25)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      // Delete all right-wing nodes

      if (CONSOLE_ACTIONS) System.out.println("\nRemove 65-110 increment 5");
      for (int k = 65; k < 112; k += 5)
         search_tree.remove(k);
      if (CONSOLE_ACTIONS) System.out.println("\nRemoving 108 and 109");
      search_tree.remove(108);
      search_tree.remove(109);

      System.out.println("\nMaximum: " + search_tree.findMax());
      if (search_tree.findMax() == 60)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      if (CONSOLE_ACTIONS) System.out.println("\nRemove 60-20 increment 5");
      for (int k = 60; k > 18; k -= 5)
         search_tree.remove(k);
         
      System.out.println("\nMaximum: " + search_tree.findMax());
      if (search_tree.findMax() == 15)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      search_tree.remove(15);
      System.out.println("\nMaximum: " + search_tree.findMax());
      if (search_tree.findMax() == 12)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      search_tree.remove(12);
      System.out.println("\nMaximum: " + search_tree.findMax());
      if (search_tree.findMax() == 11)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");

      search_tree.remove(11);
      System.out.println("\nMaximum: " + search_tree.findMax());
      if (search_tree.findMax() == 10)
         System.out.println(" > Success");
      else
         System.out.println("!> Error");
   }
}
