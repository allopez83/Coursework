// README
// Compile program with:
// javac Maxflow.java
// Run with java Maxflow.class

// Note that this file is the main(), while FlowGraph is the actual engine.
// ALSO NOTE THAT MAPREDUCE ELEMENT IS INCOMPLETE. While this whole thing works, the mapreduce portion of it has not been implemented. It is supposed to be at the findMaxFlow() method. It would distribute flow discovery to multiple threads, and piece back the resulting flow findings.

public class Maxflow
{
   public static void main(String[] args)
   {
      double final_flow;

      // build graph
      FlowGraph<String> maxFlow = new FlowGraph<String>();

      maxFlow.addEdge("s", "a", 3);
      maxFlow.addEdge("s", "b", 2);
      maxFlow.addEdge("a", "b", 1);
      maxFlow.addEdge("a", "c", 3);
      maxFlow.addEdge("a", "d", 4);
      maxFlow.addEdge("b", "d", 2);
      maxFlow.addEdge("c", "t", 2);
      maxFlow.addEdge("d", "t", 3);

      // show the original flow graph
      maxFlow.showResAdjTable();
      maxFlow.showFlowAdjTable();

      maxFlow.setStartVert("s");
      maxFlow.setEndVert("t");
      // findMaxFlow is where the mapreduce part is supposed to come in but IS NOT IMPLEMENTED.
      final_flow = maxFlow.findMaxFlow();

      System.out.println("Final flow: " + final_flow);

      maxFlow.showResAdjTable();
      maxFlow.showFlowAdjTable();
   }
}
