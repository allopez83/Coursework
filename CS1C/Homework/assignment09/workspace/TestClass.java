package assignment09.workspace;

import java.util.Iterator;
import cs_1c.Pair;

public class TestClass
{
   /*
    * User-input parameter(s); changing these will result in a change planned
    * and intended by the developer, please follow any provided instructions or
    * the test program might crash/fail/blow up the world (Ok, maybe not global
    * destruction). Thank you!
    */
   // Whether or not to print certain information; set as true to print.
   private static final boolean
         // Prints title of each error test
         PRINT_TEST_TITLE = true,
         // Prints the residual graph to show the connections
         PRINT_GRAPH = true;

   /*
    * The "do not change" parameters. These can actually be changed, but they're
    * not exactly intended to be changed. And there's probably nothng much to
    * change.
    */
   @SuppressWarnings("unchecked")
   private static FHflowVertex<String>[] verts = new FHflowVertex[50];
   private static FHflowGraph<String> maxFlow = new FHflowGraph<String>();

   /**
    * Test program that calls submethods to conduct specific tests. Use comments
    * to enable and disable tests as needed.
    */
   public static void main(String[] args)
   {
      userTest();
//      defaultTest();
   }

   /**
    * Test program created by user. Follows the graph shown in online modules.
    */
   private static void userTest()
   {
      Iterator<Pair<FHflowVertex<String>, Double>> pair_iter;
      Pair<FHflowVertex<String>, Double> pair;
      double weight = 0.0, added;
      boolean result;

      generateGraph(0);
      if (PRINT_GRAPH) maxFlow.showResAdjTable();

      // Residual weight
      weight = maxFlow.getCostOfResEdge(verts[0], verts[3]);
      errTest("Weight of edge from a to d: " + weight, weight == 4.0);

      // Residual add cost
      added = 2.0;
      result = maxFlow.addCostToResEdge(verts[0], verts[3], added);
      weight = maxFlow.getCostOfResEdge(verts[0], verts[3]);
      errTest("Add 2 to cost: " + result, weight == (4.0 + added));

      // Flow add cost
      added = 7.0;
      result = maxFlow.addCostToFlowEdge(verts[0], verts[3], added);
      for (pair_iter = verts[0].flow_adj_list.iterator(); pair_iter.hasNext();)
      {
         pair = pair_iter.next();
         if (verts[3].equals(pair.first))
         {
            weight = pair.second;
            break;
         }
      }
      errTest("Add 7 to cost from a to d: " + result, weight == added);

      maxFlow.clear();
      generateGraph(0);

      // Create flow path
      errTest("Set start as s", maxFlow.setStartVert("s"));
      errTest("Set end as e ('floating' vertex)", maxFlow.setEndVert("e"));
      errTest("Create invalid flow path", !maxFlow.establishNextFlowPath());

      errTest("Set end as t", maxFlow.setEndVert("t"));
      errTest("Create flow path", maxFlow.establishNextFlowPath());

      // Min weight test
      weight = 0; // Reset
      weight = maxFlow.getLimitingFlowOnResPath();
      errTest("Min weight: " + weight,
            (weight == 2.0 || weight == 1.0 || weight == 3.0));

      // Max flow
      weight = 0;
      weight = maxFlow.findMaxFlow();
      errTest("Max flow: " + weight, weight == 5.0);
      
      if (PRINT_GRAPH) maxFlow.showResAdjTable();
      if (PRINT_GRAPH) maxFlow.showFlowAdjTable();
   }

   /**
    * Test program directly from assignment instructions
    */
   private static void defaultTest()
   {
      double final_flow;

      generateGraph(1);
      
      // show the original flow graph
      maxFlow.showResAdjTable();
      maxFlow.showFlowAdjTable();

      maxFlow.setStartVert("s");
      maxFlow.setEndVert("t");
      final_flow = maxFlow.findMaxFlow();

      System.out.println("Final flow: " + final_flow);

      maxFlow.showResAdjTable();
      maxFlow.showFlowAdjTable();
   }

   /**
    * Creates a graph of a certain configuration according to the type given. If
    * no valid type is specified, nothing will happen and the method exits.
    * @param type integer representing the graph desired. Valid inputs are 0-1.
    */
   private static void generateGraph(int type)
   {
      switch (type)
      {
      case 0:
      {
         // Add vertices
         verts[0] = maxFlow.addToVertexSet("a");
         verts[1] = maxFlow.addToVertexSet("b");
         verts[2] = maxFlow.addToVertexSet("c");
         verts[3] = maxFlow.addToVertexSet("d");
         verts[4] = maxFlow.addToVertexSet("s");
         verts[5] = maxFlow.addToVertexSet("t");
         verts[6] = maxFlow.addToVertexSet("e"); // Not on the graph

         // Add edges
         maxFlow.addEdge("a", "b", 1);
         maxFlow.addEdge("a", "c", 3);
         maxFlow.addEdge("a", "d", 4);
         maxFlow.addEdge("b", "d", 2);
         maxFlow.addEdge("c", "t", 2);
         maxFlow.addEdge("d", "t", 3);
         maxFlow.addEdge("s", "a", 3);
         maxFlow.addEdge("s", "b", 2);
         break;
      }
      case 1:
      {
         maxFlow.addEdge("s", "a", 3);
         maxFlow.addEdge("s", "b", 2);
         maxFlow.addEdge("a", "b", 1);
         maxFlow.addEdge("a", "c", 3);
         maxFlow.addEdge("a", "d", 4);
         maxFlow.addEdge("b", "d", 2);
         maxFlow.addEdge("c", "t", 2);
         maxFlow.addEdge("d", "t", 3);
      }
      default:
         break;
      }
   }

   /**
    * Prints out a message along with "pass" or "error" depending on the
    * condition. Used to shorten and simplify testing code.
    * @param text message to print to console, should describe what the test is
    * @param intent boolean representing the intended result of the test. For
    *           this method to work, the intended result should return true.
    *           That represents the idea that, for an operation's result,
    *           "yes, this is what I want to get from the operation"
    */
   private static void errTest(String text, boolean intent)
   {
      // Prints text
      if (PRINT_TEST_TITLE) System.out.println(text);
      // Prints pass or fail to tell client if the result is the intended result
      System.out.println((intent == true) ? "  > Pass" : "!!> Error");
   }
}
