package cs_1c;
import java.util.*;

public class FHkruskal<E>
{
   private PriorityQueue<FHedge<E>> edge_heap;
   FHgraph<E> in_graph;

   /**
    * Uses setInGraph() to insert a graph, can then find the MST with algorithms
    * of this class
    * @param grph FHgraph to insert
    */
   public FHkruskal(FHgraph<E> grph)
   {
      this();
      setInGraph(grph);
   }
   
   /**
    * Default constructor, initializes edge_heap and sets in_graph to a null
    * value
    */
   public FHkruskal()
   {
      edge_heap = new PriorityQueue<FHedge<E>>();
      in_graph = null;
   }

   /**
    * Clears the edge_heap, containing edges from the graph
    */
   public void clear()
   {
      edge_heap.clear();
   }

   /**
    * Inserts a FHgraph object that can be used with other algorithms in this
    * class
    * @param grph FHgraph to insert
    */
   public void setInGraph(FHgraph<E> grph)
   { 
      in_graph = grph;
      clear();
   }

   /**
    * Applies Kruskal to a previously inserted graph, turning it into a minimum
    * spanning tree.
    * @return graph after Kruskal has been applied, or null if it failed
    */
   public FHgraph<E> genKruskal()
   {
      Iterator<FHvertex<E>> iter;
      // Contains all the hash sets of vertices
      LinkedList<HashSet<FHvertex<E>>> vertex_sets
         = new LinkedList<HashSet<FHvertex<E>>>();
      Iterator<HashSet<FHvertex<E>>> f_iter;
      HashSet<FHvertex<E>>
         // HashSet of vertices in the graph
         verts_in_graph,
         singleton,
         vert_set,
         vert_set_src = null,
         vert_set_dst = null;
      FHedge<E> smallest_edge;
      FHvertex<E>
         src,
         dst,
         vert;
      ArrayList<FHedge<E>> new_edges = new ArrayList<FHedge<E>>();
      int k, num_verts_found;
      FHgraph<E> out_graph = new FHgraph<E>();

      // Check if a graph is provided; abort if not
      if (in_graph == null)
         return null;

      // Get a local list of vertices
      verts_in_graph = in_graph.getVertSet();

      // Creates sets of vertices; used to prevent cycles
      for (k = 0, iter = verts_in_graph.iterator(); 
         iter.hasNext(); k++)
      {
         vert = iter.next();
         // Create a lonely HashSet with only one vertex in it
         singleton = new HashSet<FHvertex<E>>();
         singleton.add(vert);
         // Add the HashSet to the LinkedList
         vertex_sets.add( singleton );
      }

      // Generate binary heap of edges; abort if failed
      if (!buildEdgeHeap())
         return null;

      // test for empty to avoid inf. loop resulting from disconnected graph
      while (!edge_heap.isEmpty() && vertex_sets.size() > 1)
      {
         // pop smallest edge left in heap
         smallest_edge = edge_heap.remove();
         src = smallest_edge.source;
         dst = smallest_edge.dest;

         // Check if the source and destination are from different sets
         for (f_iter = vertex_sets.iterator(), num_verts_found = 0 ; 
            f_iter.hasNext()  &&  (num_verts_found < 2) ; )
         {
            vert_set = f_iter.next();
            if ( vert_set.contains(src) )
            {
               // Get the set that src is in
               vert_set_src = vert_set;
               num_verts_found++;
            }

            if ( vert_set.contains(dst) )
            {
               // Get the set that dst is in
               vert_set_dst = vert_set;
               num_verts_found++;
            }
         }
         // If the set that src and dst came from are same, reject
         if (vert_set_src == vert_set_dst)
            continue;

         // They are from different sets, take the union
         new_edges.add(smallest_edge);
         vert_set_src.addAll(vert_set_dst);
         vertex_sets.remove(vert_set_dst);
      }

      // Create a new graph
      out_graph.setGraph(new_edges);
      return out_graph;
   }

   /**
    * Generate a binary heap containing the edges of the input graph. Requires
    * in_graph to have a value.
    * @return boolean representing if the operation succeeded or failed
    */
   private boolean buildEdgeHeap()
   {
      HashSet<FHvertex<E>> verts_in_graph;
      Iterator<FHvertex<E>> vert_iter;
      Iterator<Pair<FHvertex<E>, Double>> edge_iter;
      FHvertex<E> src, dst;
      Pair<FHvertex<E>, Double> edge;
      double cost;

      // If no graph has been set, abort
      if (in_graph == null)
         return false;

      // Store vertices in local variable
      verts_in_graph = in_graph.getVertSet();
      // Iterate through all vertices
      for (vert_iter = verts_in_graph.iterator(); vert_iter.hasNext();)
      {
         // Store current vertex in variable src
         src = vert_iter.next();
         // Iterate through list of adjacent vertex
         for (edge_iter = src.adj_list.iterator(); edge_iter.hasNext();)
         {
            // Retrieve vertex's data
            edge = edge_iter.next();
            dst = edge.first;
            cost = edge.second;
            // Create new edge
            edge_heap.add(new FHedge<E>(src, dst, cost));
         }
      }
      // Operation succeeded
      return true;
   }
}
