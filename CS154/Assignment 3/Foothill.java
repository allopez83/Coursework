public class Foothill
{
   public static void main(String[] args)
   {
      double final_flow;

      // build graph
      FHflowGraph<String> maxFlow = new FHflowGraph<String>();

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
      final_flow = maxFlow.findMaxFlow();

      System.out.println("Final flow: " + final_flow);

      maxFlow.showResAdjTable();
      maxFlow.showFlowAdjTable();
   }
}
