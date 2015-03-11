package assignment04.exercises;

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class Foothill
{ 
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k;
      FHsearch_tree<Integer> search_tree = new FHsearch_tree<Integer>();
      PrintObject<Integer> printer = new PrintObject<Integer>();

      search_tree.traverse(printer);

      System.out.println("initial size: " + search_tree.size() );
      search_tree.insert(50);
      search_tree.insert(20);
      search_tree.insert(30);
      search_tree.insert(70);
      search_tree.insert(10);
      search_tree.insert(60);
      System.out.println("new size: " + search_tree.size() );
      System.out.println("traversal: ");
      search_tree.traverse(printer);

      FHsearch_tree<Integer> search_tree2 
         = (FHsearch_tree<Integer>)search_tree.clone();

      System.out.println("\n\nAttempting 100 removals:");
      for (k = 100; k > 0; k--)
         if (search_tree.remove(k))
            System.out.println("removed " + k );

      System.out.println("\nsearch_tree after deletes:");
      search_tree.traverse(printer);

      System.out.println("\nsearch_tree2 after deletes:");
      search_tree2.traverse(printer);
      System.out.println();

      System.out.println
      (
         search_tree2.contains(30)? "30 found" : "30 not found"
      );
      System.out.println
      (
         search_tree2.contains(65)? "65 found" : "65 not found"
      );
   }
}