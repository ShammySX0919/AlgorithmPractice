package basic.graph.edgeweighted;

import java.util.*;
/**
 * this is based on http://algs4.cs.princeton.edu/44sp/EdgeWeightedDirectedCycle.java.html
 */
/******************************************************************************
 *  Compilation:  javac EdgeWeightedGraph.java
 *  Execution:    java EdgeWeightedGraph filename.txt
 *  Dependencies: Bag.java DirectedEdge.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/43mst/tinyEWG.txt
 *                http://algs4.cs.princeton.edu/43mst/mediumEWG.txt
 *                http://algs4.cs.princeton.edu/43mst/largeEWG.txt
 *
 *  An edge-weighted undirected graph, implemented using adjacency lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java EdgeWeightedGraph tinyEWG.txt 
 *  8 16
 *  0: 6-0 0.58000  0-2 0.26000  0-4 0.38000  0-7 0.16000  
 *  1: 1-3 0.29000  1-2 0.36000  1-7 0.19000  1-5 0.32000  
 *  2: 6-2 0.40000  2-7 0.34000  1-2 0.36000  0-2 0.26000  2-3 0.17000  
 *  3: 3-6 0.52000  1-3 0.29000  2-3 0.17000  
 *  4: 6-4 0.93000  0-4 0.38000  4-7 0.37000  4-5 0.35000  
 *  5: 1-5 0.32000  5-7 0.28000  4-5 0.35000  
 *  6: 6-4 0.93000  6-0 0.58000  3-6 0.52000  6-2 0.40000
 *  7: 2-7 0.34000  1-7 0.19000  0-7 0.16000  5-7 0.28000  4-7 0.37000
 *
 ******************************************************************************/

/**
 *  The {@code EdgeWeightedGraph} class represents an edge-weighted
 *  graph of vertices named 0 through <em>V</em> - 1, where each
 *  undirected edge is of type {@link DirectedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the edges incident to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which 
 *  is a vertex-indexed array of @link{Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the edges incident to a given vertex, which takes
 *  time proportional to the number of such edges.
 *  <p>
 *  For additional documentation,
 *  see <a href="http://algs4.cs.princeton.edu/43mst">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class EdgeWeightedDiGraph<E> {
    private static final String NEWLINE = System.getProperty("line.separator");

    private HashMap<E,Set<DirectedEdge<E>>> adj = new HashMap<E,Set<DirectedEdge<E>>>();
    
    /**
     * Initializes an empty edge-weighted graph 
     *
     */
    public EdgeWeightedDiGraph() {
    }

    /**
     * Initializes a new edge-weighted graph that is a deep copy of {@code G}.
     *
     * @param  G the edge-weighted graph to copy
     */
    public EdgeWeightedDiGraph(EdgeWeightedDiGraph<E> G) {
    	Iterator<DirectedEdge<E>> it = G.edges().iterator();
        while (it.hasNext()) {
           	addEdge(it.next());
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int getNumVertices() {
        return adj.size();
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int getNumEdges() {
    	int cnt = 0;
    	for(Map.Entry<E, Set<DirectedEdge<E>>> e:adj.entrySet()){
    		cnt+=e.getValue().size();
    	}
        return cnt;
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     */
    public void addEdge(DirectedEdge<E> e) {
        if(!adj.containsKey(e.from())){
        	adj.put(e.from(), new HashSet<DirectedEdge<E>>());
        }
        if (!adj.containsKey(e.to())) {
            adj.put(e.to(), new HashSet<DirectedEdge<E>>());
        }
        adj.get(e.from()).add(e);
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     */
    public Iterable<DirectedEdge<E>> adjcencyList(E v) {
        if(adj.containsKey(v)){
    		return adj.get(v);
    	}
        return null;
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     */
    public int degree(E v) {
    	if(adj.containsKey(v)){
    		return adj.get(v).size();
    	}
        return 0;
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<DirectedEdge<E>> edges() {
        Set<DirectedEdge<E>> list = new HashSet<DirectedEdge<E>>();
        for(Map.Entry<E, Set<DirectedEdge<E>>> e:adj.entrySet()){
            for (DirectedEdge<E> directedEdge : e.getValue()) {
            	list.add(directedEdge);
            }
        }
        return list;
    }
    public Iterable<E> vertices() {
        return adj.keySet();
    }
    /**
     * Returns a string representation of the edge-weighted directed graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(getNumVertices() + " " + getNumEdges() + NEWLINE);
        for (Map.Entry<E, Set<DirectedEdge<E>>> e: adj.entrySet()) {
            s.append(e + ": ");
            for (DirectedEdge<E> directedEdge : e.getValue()) {
                s.append(directedEdge + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * BFS to check if they are connected
     * @param a
     * @param b
     * @return
     */
    public boolean connected(E a, E b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        Queue<E> queue = new LinkedList<E>();
        Set<E> visited = new HashSet<E>();
        queue.add(a);
        while (!queue.isEmpty()) {
            E c = queue.poll();
            if (c.equals(b)) {
                return true;
            }
            if (visited.add(c)) {
                if (adj.get(c) != null) {
                    for (DirectedEdge<E> more : adj.get(c)) {
                        queue.add(more.to());
                    }
                }
            }
        }
        return false;
    }

    public List<DirectedEdge<E>> getPath(E a, E b) {
        List<DirectedEdge<E>> path = new ArrayList<DirectedEdge<E>>();
        Set<DirectedEdge<E>> visited = new HashSet<DirectedEdge<E>>();
        if (connected(a, b)) {
            path = dfsPath(a, b, path, visited);
        }
        return path;
    }

    private List<DirectedEdge<E>> dfsPath(E start, E end, List<DirectedEdge<E>> path, Set<DirectedEdge<E>> visited) {
        if (start.equals(end)) {
            List<DirectedEdge<E>> result = new ArrayList<DirectedEdge<E>>();
            result.addAll(path);
            return result;
        }
        if (adj.get(start) != null) {
            for (DirectedEdge<E> directedEdge : adj.get(start)) {
                if (!visited.contains(directedEdge)) {
                    visited.add(directedEdge);
                    path.add(directedEdge);
                    List<DirectedEdge<E>> r = dfsPath(directedEdge.to(), end, path, visited);
                    if (r.size() > 0) return r;
                    path.remove(directedEdge);
                }
            }
        }
        return new ArrayList<DirectedEdge<E>>();
    }

    /**
     * Unit tests the {@code EdgeWeightedGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        EdgeWeightedDiGraph<String> g = new EdgeWeightedDiGraph<String>();
        g.addEdge(new DirectedEdge<String>("a", "b", 1.0));
        g.addEdge(new DirectedEdge<String>("b", "a", 1.0));
        g.addEdge(new DirectedEdge<String>("b", "c", 2.0));
        g.addEdge(new DirectedEdge<String>("b", "d", 3.0));
        g.addEdge(new DirectedEdge<String>("c", "e", 2.0));

        System.out.println(g.getPath("a", "e"));
        System.out.println(g.getPath("a", "b"));
        System.out.println(g.getPath("a", "y"));
    }

}
