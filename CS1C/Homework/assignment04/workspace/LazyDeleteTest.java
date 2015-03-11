package assignment04.workspace;

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class LazyDeleteTest
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k;
      LazySearchTree<Integer> search_tree = new LazySearchTree<Integer>();
      PrintObject<Integer> int_printer = new PrintObject<Integer>();

      search_tree.traverse(int_printer);

      System.out.println( "\ninitial size: " + search_tree.size() );
      search_tree.insert(50);
      search_tree.insert(20);
      search_tree.insert(30);
      search_tree.insert(70);
      search_tree.insert(10);
      search_tree.insert(60);

      System.out.println( "After populating -- traversal and sizes: ");
      search_tree.traverse(int_printer);
      System.out.println( "\ntree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "Collecting garbage on new tree - should be " +
            "no garbage." );
      search_tree.collectGarbage();
      System.out.println( "tree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      // test assignment operator
      LazySearchTree<Integer> search_tree2 
         = (LazySearchTree<Integer>)search_tree.clone();

      System.out.println( "\n\nAttempting 1 removal: ");
      if (search_tree.remove(20))
         System.out.println( "removed " + 20 );
      System.out.println( "tree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "Collecting Garbage - should clean 1 item. " );
      search_tree.collectGarbage();
      System.out.println( "tree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "Collecting Garbage again - no change expected. " );
      search_tree.collectGarbage();
      System.out.println( "tree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      // test soft insertion and deletion:

      System.out.println( "Adding 'hard' 22 - should see new sizes. " );
      search_tree.insert(22);
      search_tree.traverse(int_printer);
      System.out.println( "\ntree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "\nAfter soft removal. " );
      search_tree.remove(22);
      search_tree.traverse(int_printer);
      System.out.println( "\ntree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "Repeating soft removal. Should see no change. " );
      search_tree.remove(22);
      search_tree.traverse(int_printer);
      System.out.println( "\ntree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "Soft insertion. Hard size should not change. " );
      search_tree.insert(22);
      search_tree.traverse(int_printer);
      System.out.println( "\ntree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      System.out.println( "\n\nAttempting 100 removals: " );
      for (k = 100; k > 0; k--)
      {
         if (search_tree.remove(k))
            System.out.println( "removed " + k );
      }
      search_tree.collectGarbage();

      System.out.println( "\nsearch_tree now:");
      search_tree.traverse(int_printer);
      System.out.println( "\ntree 1 size: " + search_tree.size() 
         + "  Hard size: " + search_tree.sizeHard() );

      search_tree2.insert(500);
      search_tree2.insert(200);
      search_tree2.insert(300);
      search_tree2.insert(700);
      search_tree2.insert(100);
      search_tree2.insert(600);
      System.out.println( "\nsearch_tree2:\n" );
      search_tree2.traverse(int_printer);
      System.out.println( "\ntree 2 size: " + search_tree2.size() 
         + "  Hard size: " + search_tree2.sizeHard() );
   }
}


/* ---------------------- Run --------------------------

initial size: 0
After populating -- traversal and sizes: 
10 20 30 50 60 70 
tree 1 size: 6  Hard size: 6
Collecting garbage on new tree - should be no garbage.
tree 1 size: 6  Hard size: 6


Attempting 1 removal: 
removed 20
tree 1 size: 5  Hard size: 6
Collecting Garbage - should clean 1 item. 
tree 1 size: 5  Hard size: 5
Collecting Garbage again - no change expected. 
tree 1 size: 5  Hard size: 5
Adding 'hard' 22 - should see new sizes. 
10 22 30 50 60 70 
tree 1 size: 6  Hard size: 6

After soft removal. 
10 30 50 60 70 
tree 1 size: 5  Hard size: 6
Repeating soft removal. Should see no change. 
10 30 50 60 70 
tree 1 size: 5  Hard size: 6
Soft insertion. Hard size should not change. 
10 22 30 50 60 70 
tree 1 size: 6  Hard size: 6


Attempting 100 removals: 
removed 70
removed 60
removed 50
removed 30
removed 22
removed 10

search_tree now:

tree 1 size: 0  Hard size: 0

search_tree2:

10 20 30 50 60 70 100 200 300 500 600 700 
tree 2 size: 12  Hard size: 12

---------------------------------------------------------------------- */
