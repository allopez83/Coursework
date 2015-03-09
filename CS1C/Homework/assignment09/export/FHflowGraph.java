package assignment09.export;

import java.util.*;
import cs_1c.*;

// --- FHflowVertex class -----------------------------------------------------
class FHflowVertex<E>
{
   protected static Stack<Integer> key_stack = new Stack<Integer>();
   protected static final int KEY_ON_DATA = 0, KEY_ON_DIST = 1;
   protected static int n_key_type = KEY_ON_DATA;
   protected static final double INFINITY = Double.MAX_VALUE;
   // HashSets containing capacity of adjacency paths
   protected HashSet<Pair<FHflowVertex<E>, Double>>
      flow_adj_list = new HashSet<Pair<FHflowVertex<E>, Double>>(),
      res_adj_list = new HashSet<Pair<FHflowVertex<E>, Double>>();
   protected E data;
   protected double dist;
   protected FHflowVertex<E> next_in_path; // for client-specific info

   /**
    * Creates a vertex containing the given data
    * @param x data to be contained in the vertex
    */
   public FHflowVertex(E x)
   {
      data = x;
      dist = INFINITY;
      next_in_path = null;
   }

   /**
    * Creates a vertex containing default data
    */
   public FHflowVertex()
   {
      this(null);
   }

   /**
    * Adds a vertex to the list of adjacent ones in the flow graph
    * @param neighbor vertex that this adjacent to
    * @param cost double value representing the cost of the path
    */
   public void addToFlowAdjList(FHflowVertex<E> neighbor, double cost)
   {
      flow_adj_list.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
   }

   /**
    * Adds a vertex to the list of adjacent ones in the flow graph
    * @param neighbor vertex that this is adjacent to
    * @param cost integer value representing the cost of the path
    */
   public void addToFlowAdjList(FHflowVertex<E> neighbor, int cost)
   {
      addToFlowAdjList(neighbor, (double) cost);
   }

   /**
    * Adds a vertex to the list of adjacent ones in the residual graph
    * @param neighbor vertex that this adjacent to
    * @param cost double value representing the cost of the path
    */
   public void addToResAdjList(FHflowVertex<E> neighbor, double cost)
   {
      res_adj_list.add(new Pair<FHflowVertex<E>, Double>(neighbor, cost));
   }

   /**
    * Adds a vertex to the list of adjacent ones in the residual graph
    * @param neighbor vertex that this is adjacent to
    * @param cost integer value representing the cost of the path
    */
   public void addToResAdjList(FHflowVertex<E> neighbor, int cost)
   {
      addToResAdjList(neighbor, (double) cost);
   }

   /**
    * Prints any vertices registered in this vertex's flow graph
    */
   public void showFlowAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
   
      System.out.print("Adj Flow List for " + data + ": ");
      for (iter = flow_adj_list.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         System.out.print( pair.first.data + "(" 
            + String.format("%3.1f", pair.second)
            + ") " );
      }
      System.out.println();
   }

   /**
    * Prints any vertices registered in this vertex's flow graph
    */
   public void showResAdjList()
   {
      Iterator<Pair<FHflowVertex<E>, Double>> iter;
      Pair<FHflowVertex<E>, Double> pair;
   
      System.out.print("Adj Res List for " + data + ": ");
      for (iter = res_adj_list.iterator(); iter.hasNext();)
      {
         pair = iter.next();
         System.out.print( pair.first.data + "(" 
            + String.format("%3.1f", pair.second)
            + ") " );
      }
      System.out.println();
   }

   /**
    * Determine if two vertices are equal; can be customized
    */
   public boolean equals(Object rhs)
   {
      @SuppressWarnings("unchecked")
      FHflowVertex<E> other = (FHflowVertex<E>) rhs;
      switch (n_key_type)
      {
      case KEY_ON_DIST:
         return (dist == other.dist);
      case KEY_ON_DATA:
         return (data.equals(other.data));
      default:
         return (data.equals(other.data));
      }
   }

   /**
    * Generates a hashcode for the vertex
    */
   public int hashCode()
   {
      switch (n_key_type)
      {
      case KEY_ON_DIST:
         Double d = dist;
         return (d.hashCode());
      case KEY_ON_DATA:
         return (data.hashCode());
      default:
         return (data.hashCode());
      }
   }

   /**
    * Sets key type, determining how equality of vertices is determined
    * @param which_type number of the type to use, can be 0 for the data or 1
    *           for the distance of the vertex.
    * @return boolean representing the success of the operation
    */
   public static boolean setKeyType(int which_type)
   {
      switch (which_type)
      {
      case KEY_ON_DATA:
      case KEY_ON_DIST:
         n_key_type = which_type;
         return true;
      default:
         return false;
      }
   }
   public static void pushKeyType() { key_stack.push(n_key_type); }
   public static void popKeyType() { n_key_type = key_stack.pop(); };
}

//--- FHflowGraph class -------------------------------------------------------

/**
 * Allows client to create a graph and find the maximum flow from a a specified
 * start to end node. All vertices and their paths need to be created
 * individually. In order for the max flow algorithm to work, a starting and
 * ending point also needs to be specified.
 * @author HW
 * 
 * @param <E> type of data that is contained in the vertices
 */
public class FHflowGraph<E>
{
   /*
    * User-input parameter(s); changing these will result in a change planned
    * and intended by the developer, please follow any provided instructions or
    * the test program might crash/fail/blow up the world (Ok, maybe not global
    * destruction). Thank you!
    */
   // Whether or not to print certain information; set as true to print.
   private static final boolean
      // Prints extra debugging info
      DEBUG = false;

   /*
    * The "do not change" parameters. These can actually be changed, but they're
    * not exactly intended to be changed. And there's probably nothng much to
    * change.
    */
   // Keeps track of all vertices in the graph
   protected HashSet<FHflowVertex<E>> vertex_set;
   // Used to hold the start and end of path
   protected FHflowVertex<E> start_vert, end_vert;

   // public graph methods --------------------------------
   public FHflowGraph()
   {
      vertex_set = new HashSet<FHflowVertex<E>>();
   }

   /**
    * Changes the starting vertex to one with the provided value. Required for many other methods to work.
    * @param x the data to use in searching for the vertex
    * @return boolean representing the success of the operation
    */
   public boolean setStartVert(E x)
   {
      FHflowVertex<E> vert;
      vert = getVertexWithThisData(x);
      if (vert == null)
         return false;
      start_vert = vert;
      return true;
   }

   /**
    * Changes the end vertex to one with the provided value. Required for many other methods to work.
    * @param x the data to use in searching for the vertex
    * @return boolean representing the success of the operation
    */
   public boolean setEndVert(E x)
   {
      FHflowVertex<E> vert;
      vert = getVertexWithThisData(x);
      if (vert == null)
         return false;
      end_vert = vert;
      return true;
   }

   /**
    * Create a vertex containing a certain data x and add it to the graph's
    * vertex_set HashSet
    * @param x the data to be contained in the vertex
    * @return the vertex that has been created
    */
   public FHflowVertex<E> addToVertexSet(E x)
   {
      FHflowVertex<E> ret_val, vert;
      boolean successful_insertion;
      Iterator<FHflowVertex<E>> iter;

      // save sort key for client
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      // build and insert vertex into master list
      ret_val = new FHflowVertex<E>(x);
      successful_insertion = vertex_set.add(ret_val);

      if (successful_insertion)
      {
         FHflowVertex.popKeyType(); // restore client sort key
         return ret_val;
      }

      // the vertex was already in the set, so get its ref
      for (iter = vertex_set.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         if (vert.equals(ret_val))
         {
            FHflowVertex.popKeyType(); // restore client sort key
            return vert;
         }
      }

      FHflowVertex.popKeyType(); // restore client sort key
      return null; // should never happen
   }

   /**
    * Prints the residual graph to the console
    */
   public void showResAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;

      System.out.println("------------------------ ");
      for (iter = vertex_set.iterator(); iter.hasNext();)
         (iter.next()).showResAdjList();
      System.out.println();
   }

   /**
    * Prints the flow graph to the console
    */
   public void showFlowAdjTable()
   {
      Iterator<FHflowVertex<E>> iter;

      System.out.println("------------------------ ");
      for (iter = vertex_set.iterator(); iter.hasNext();)
         (iter.next()).showFlowAdjList();
      System.out.println();
   }

   /**
    * Empties the graph, reseting it to a "clean slate".
    */
   public void clear()
   {
      vertex_set.clear();
   }

   /**
    * Create an edge from a source to dest vertex and give it a certain cost
    * @param source starting point of the edge
    * @param dest ending point of the edge
    * @param cost the desired weight of the edge
    */
   public void addEdge(E source, E dest, double cost)
   {
      FHflowVertex<E> src, dst;

      // put both source and dest into vertex list(s) if not already there
      src = addToVertexSet(source);
      dst = addToVertexSet(dest);

      // Add destination to source's adjacency list
      src.addToResAdjList(dst, cost);
      // Add a reverse edge
      dst.addToResAdjList(src, 0);
      // Add edge for flow graph
      src.addToFlowAdjList(dst, 0);
   }

   /**
    * Create an edge from a source to dest vertex and give it a certain cost
    * @param source starting point of the edge
    * @param dest ending point of the edge
    * @param cost the desired weight of the edge
    */
   public void addEdge(E source, E dest, int cost)
   {
      addEdge(source, dest, (double) cost);
   }

   public void showPath()
   {
      FHflowVertex<E> vert = start_vert;

      System.out.print(vert.data);
      // Go through entire path; end is reached when next is null
      while (vert.next_in_path != null)
      {
         // Advance the vertex down the path
         vert = vert.next_in_path;
         // Print data
         System.out.print(" -> " + vert.data);
      }
      System.out.println();
   }

   /**
    * Uses other methods to retrieve a maximum flow from start_vert to end_vert.
    * These two vertices need to be specified for this algorithm to work. If the
    * distance returned is zero, then the operation failed.
    * @return double representing the maximum possible flow from start_vert to
    *         end_vert. Zero means the operation failed.
    */
   public double findMaxFlow()
   {
      // Null check
      if (start_vert == null || end_vert == null)
         return 0.0;

      double min, flow = 0.0;

      // As long as a path still exists
      while (establishNextFlowPath())
      {
         if (DEBUG) showPath();
         // Find lowest capacity
         min = getLimitingFlowOnResPath();
         // Adjust using the lowest capacity
         adjustPathByCost(min);
         if (DEBUG) getLimitingFlowOnResPath();
         if (DEBUG) System.out.println("Subtracted from path: " + min);
         flow += min;
      }

      return flow; // Return total flow
   }

   /**
    * Finds a path from start_vert to end_vert. The path can be traced by
    * starting from start_vert, then using the next_in_path variable to travel
    * to end_vert.
    * @return boolean representing if a path was found
    */
   protected boolean establishNextFlowPath()
   {
      // Null check
      if (start_vert == null || end_vert == null)
         return false;

      Double cost_vw;
      FHflowVertex<E> w, v;
      Iterator< FHflowVertex<E> > iter;
      Pair<FHflowVertex<E>, Double> edge;
      Iterator<Pair<FHflowVertex<E>, Double>> edge_iter;
      Deque<FHflowVertex<E>> partially_processed_verts
      = new LinkedList<FHflowVertex<E>>();

      // Initialize vertex list and clear previous data
      for (iter = vertex_set.iterator(); iter.hasNext();)
      {
         v = iter.next();
         v.dist = FHflowVertex.INFINITY;
         v.next_in_path = null;
         v = null;
      }
      start_vert.dist = 0;
      partially_processed_verts.addLast(start_vert);

      // Create the path
      while (!partially_processed_verts.isEmpty())
      {
         // Get next vertex in the path
         v = partially_processed_verts.removeFirst();
         for (edge_iter = v.res_adj_list.iterator(); edge_iter.hasNext();)
         {
            edge = edge_iter.next();
            // Proceed on this path
            w = edge.first;
            cost_vw = edge.second;

            // Edge of zero doesn't need to be processed
            if (getCostOfResEdge(v, w) == 0.0)
               continue;

            // Preferably get min path to fill small gaps
            if (v.dist + cost_vw < w.dist)
            {
               if (DEBUG) System.out.println("Adding: " + v.data + " -> " +
                     w.data + " dist: " + edge.second);
               w.dist = v.dist + cost_vw;
               w.next_in_path = v;
               // Add w to PPV queue since it's a possible route
               partially_processed_verts.addLast(w);
               // If destination reached, use this path
               if (w.equals(end_vert))
               {
                  // Path created in reverse; correct it
                  reverseVertexPath();
                  return true;
               }
            }
         }
      }
      // Otherwise, there is no path to the end
      return false;
   }

   /**
    * Helper method mainly for establishNextFlowPath(). The vertex order is in
    * reverse, and this method straightens it out.
    */
   private void reverseVertexPath()
   {
      Stack<FHflowVertex<E>> path_stack = new Stack<FHflowVertex<E>>();
      FHflowVertex<E> vert;

      // Retrieve the vertices in the path
      for (vert = end_vert; vert != start_vert; vert = vert.next_in_path)
         path_stack.push(vert);

      // Recreate the path in correct order
      for (vert = start_vert; !path_stack.isEmpty(); vert = vert.next_in_path)
         vert.next_in_path = path_stack.pop();

      // Just to make sure, set end_vert as last node
      end_vert.next_in_path = null;
   }

   /**
    * Finds the minimum path value on the path from start_vert to end_vert.
    * Returns zero if the start_vert or end_vert is null.
    * @return double representing the minimum edge cost in the path. If
    *         start_vert or end_vert is null, or the path did not go from
    *         start_vert to end_vert, zero is returned.
    */
   protected double getLimitingFlowOnResPath()
   {
      // Null check
      if (start_vert == null || end_vert == null)
         return 0.0;

      FHflowVertex<E> vert = start_vert;
      double min = Double.MAX_VALUE, possible_min;

      // Go through entire path; end is reached when next vertex is null
      while (vert.next_in_path != null)
      {
         // Get cost of current edge
         possible_min = getCostOfResEdge(vert, vert.next_in_path);
         if (DEBUG) System.out.println(vert.data + " -> "  + vert.next_in_path.data + " val: " + possible_min);
         // See if it is a smaller value; save value if it is
         if (possible_min < min)
            min = possible_min;
         // Advance the vertex down the path
         vert = vert.next_in_path;
      }
      // If it followed a legitimate path
      if (vert.equals(end_vert))
         return min;
      // Path didn't go from start to end
      else
         return 0.0;
   }

   /**
    * Modifies the cost of vertices on the path from start_vert to end_vert.
    * Changes are applied to both the residual and flow graphs. The residual
    * edge has the value decreased by cost, reverse residual edge has cost
    * added, and the flow edge also has the edge added.
    * @param cost double amount to change the current edge costs by
    * @return boolean representing success of the operation
    */
   protected boolean adjustPathByCost(double cost)
   {
      // Null check
      if (start_vert == null || end_vert == null)
         return false;

      FHflowVertex<E> vert = start_vert;

      // Go through entire path; end is reached when next is null
      while (vert.next_in_path != null)
      {
         // Change the values at current edge
         addCostToResEdge(vert, vert.next_in_path, -cost); // In order
         addCostToResEdge(vert.next_in_path, vert, cost); // Reversed
         addCostToFlowEdge(vert, vert.next_in_path, cost);
         // Advance the vertex down the path
         vert = vert.next_in_path;
      }
      return true;
   }

   /**
    * Retrieves the cost of an edge in the residual graph. Uses src vertex as
    * the starting vertex, and searches its residual adjacency list for a vertex
    * dst. Helper to getLimitingFlowOnResPath().
    * @param src vertex to conduct the search on
    * @param dst vertex to search for
    * @return double representing the cost of the edge
    */
   protected double getCostOfResEdge(FHflowVertex<E> src, FHflowVertex<E> dst)
   {
      FHflowVertex<E> vert = null;
      Iterator<FHflowVertex<E>> vert_ter;
      Pair<FHflowVertex<E>, Double> pair;
      Iterator<Pair<FHflowVertex<E>, Double>> pair_iter;

      // Search for src vertex
      for (vert_ter = vertex_set.iterator(); vert_ter.hasNext();)
      {
         vert = vert_ter.next();
         // If the vertex matches
         if (src.equals(vert))
            break; // Found it!
      }
      // Search for dst vertex
      for (pair_iter = vert.res_adj_list.iterator(); pair_iter.hasNext();)
      {
         // Get the pair
         pair = pair_iter.next();
         // If the vertex matches
         if (dst.equals(pair.first))
            return pair.second; // Return value
      }

      // Should not reach here
      return 0.0;
   }

   /**
    * Searches for a vertex dst in src's residual adjacency list, and adds a
    * client-provided cost value to the edge's current cost value. Helper to
    * adjustPathByCost().
    * @param src vertex that edge starts from
    * @param dst vertex that edge stops at
    * @param cost double value representing the desired change to be added
    * @return boolean representing if the operation succeeded
    */
   protected boolean addCostToResEdge(FHflowVertex<E> src, FHflowVertex<E> dst,
         double cost)
   {
      // First see if vertices are not null
      if (src == null || dst == null)
         return false;

      boolean found = false;
      FHflowVertex<E> vert = null;
      Iterator<FHflowVertex<E>> vert_ter;
      Pair<FHflowVertex<E>, Double> pair;
      Iterator<Pair<FHflowVertex<E>, Double>> pair_iter;

      // Search for src vertex
      for (vert_ter = vertex_set.iterator(); vert_ter.hasNext();)
      {
         vert = vert_ter.next();
         // If the vertex matches
         if (src.equals(vert))
         {
            // Operation succeeded, end now
            found = true;
            break;
         }
      }
      // If src isn't in this graph
      if (!found)
         return false;
      // Search for dst and pair
      for (pair_iter = vert.res_adj_list.iterator(); pair_iter.hasNext();)
      {
         // Get a pair
         pair = pair_iter.next();
         // If the vertex matches
         if (dst.equals(pair.first))
         {
            // Change the value
            pair.second += cost;
            // Done
            return true;
         }
      }

      // Didn't find the vertex
      return false;
   }

   /**
    * Searches for a vertex dst in src's residual adjacency list, and adds a
    * client-provided cost value to the edge's current cost value. If the src
    * and dst vertices are found to be passed in with its order reversed, the
    * cost is subtracted instead of added. Helper to adjustPathByCost().
    * @param src vertex that edge starts from
    * @param dst vertex that edge stops at
    * @param cost double value representing the desired change to be added
    * @return boolean representing if the operation succeeded
    */
   protected boolean addCostToFlowEdge(FHflowVertex<E> src,
         FHflowVertex<E> dst, double cost)
   {
      // First see if vertices are ok
      if (src == null || dst == null)
         return false;

      boolean found = false;
      FHflowVertex<E> vert = null;
      Iterator<FHflowVertex<E>> vert_ter;
      Pair<FHflowVertex<E>, Double> pair = null;
      Iterator<Pair<FHflowVertex<E>, Double>> pair_iter;

      // Search for src vertex
      for (vert_ter = vertex_set.iterator(); vert_ter.hasNext();)
      {
         vert = vert_ter.next();
         // If the vertex matches
         if (src.equals(vert))
         {
            // Correct vertex found, end here
            found = true;
            break;
         }
      }
      // If src isn't in this graph
      if (!found)
         return false;
      // Reset found
      found = false;
      // Search for dst and pair
      for (pair_iter = vert.flow_adj_list.iterator(); pair_iter.hasNext();)
      {
         // Get a pair
         pair = pair_iter.next();
         // If the vertex matches
         if (dst.equals(pair.first))
         {
            // Change the value
            pair.second += cost;
            // Value changed, end here
            return true;
         }
      }
      // If program gets here, the correct edge wasn't found
      // Search for dst vertex
      for (vert_ter = vertex_set.iterator(); vert_ter.hasNext();)
      {
         vert = vert_ter.next();
         // If the vertex matches
         if (dst.equals(vert))
            break;
      }
      // Search for src and pair
      for (pair_iter = vert.flow_adj_list.iterator(); pair_iter.hasNext();)
      {
         // Get a pair
         pair = pair_iter.next();
         // If the vertex matches
         if (src.equals(pair.first))
         {
            // Found it, change the value and return true
            pair.second -= cost;
            return true;
         }
      }

      // Didn't find the vertex
      return false;
   }

   protected FHflowVertex<E> getVertexWithThisData(E x)
   {
      FHflowVertex<E> search_vert, vert;
      Iterator<FHflowVertex<E>> iter;

      // save sort key for client
      FHflowVertex.pushKeyType();
      FHflowVertex.setKeyType(FHflowVertex.KEY_ON_DATA);

      // build vertex with data = x for the search
      search_vert = new FHflowVertex<E>(x);

      // the vertex was already in the set, so get its ref
      for (iter = vertex_set.iterator(); iter.hasNext();)
      {
         vert = iter.next();
         if (vert.equals(search_vert))
         {
            FHflowVertex.popKeyType();
            return vert;
         }
      }

      FHflowVertex.popKeyType();
      return null; // not found
   }
}
