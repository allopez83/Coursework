package assignment05.workspace;

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class TestSplayTree
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k;
      SplayTree<Integer> search_tree = new SplayTree<Integer>();
      PrintObject<Integer> int_printer = new PrintObject<Integer>();

      search_tree.traverse(int_printer);

      System.out.println( "Initial size: " + search_tree.size() );
      for (k = 1; k <= 32; k++)
         search_tree.insert(k);
      System.out.println( "New size: " + search_tree.size() );

      System.out.println( "\nTraversal");
      search_tree.traverse(int_printer);
      System.out.println();

      for (k = -1; k < 10; k++)
      {
         // search_tree.contains(k);  // alternative to find() - different error return
         try
         {
             search_tree.find(k);
         }
         catch( Exception e )
         {
            System.out.println( " oops " );
         }
            System.out.println( "splay " + k + " --> root: " 
               + search_tree.showRoot() 
               + " height: " + search_tree.showHeight() );
      }
   }
}
