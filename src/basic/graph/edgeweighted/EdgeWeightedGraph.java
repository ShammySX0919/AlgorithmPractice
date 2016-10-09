package basic.graph.edgeweighted;

import java.util.*;

/**
 * http://algs4.cs.princeton.edu/43mst/EdgeWeightedGraph.java.html
 * Created by andrew on 08/10/16.
 */
public class EdgeWeightedGraph<E> {
    private static final String NEWLINE = System.getProperty("line.separator");
    private Map<E,List<DirectedEdge<E>>> adj = new HashMap<E, List<DirectedEdge<E>>>();
    private int edges=0;

    public EdgeWeightedGraph() {
     }

    public int getNumVertex() {
        return adj.size();
    }

    public int getNumEdges() {
        return edges;
    }


    public void addEdge(DirectedEdge<E> e) {
        E v = e.from();
        E w = e.to();
        if(adj.containsKey(v)){
            adj.get(v).add(e);
        }else{
            List<DirectedEdge<E>> l = new ArrayList<DirectedEdge<E>>();
            l.add(e);
            adj.put(v,l);
        }
        //put edge to another node's adjacency list
        if(adj.containsKey(w)){
            adj.get(w).add(e);
        }else{
            List<DirectedEdge<E>> l = new ArrayList<DirectedEdge<E>>();
            l.add(e);
            adj.put(w,l);
        }
        edges++;
    }

    /**
     * result can be null
     * @param v
     * @return
     */
    public Iterable<DirectedEdge<E>> getAdjacencyList(E v) {
        return adj.get(v);
    }

    public int degree(E v) {
        if(!adj.containsKey(v)){
            throw new RuntimeException("No such vertex in graph.");
        }
        return adj.get(v).size();
    }


    public Iterable<DirectedEdge<E>> edges() {
        List<DirectedEdge<E>> list = new ArrayList<DirectedEdge<E>>();
        for (Map.Entry<E,List<DirectedEdge<E>>> e:adj.entrySet()) {
            list.addAll(e.getValue());
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(getNumVertex() + " " + getNumEdges() + NEWLINE);
        for (Map.Entry<E,List<DirectedEdge<E>>> e:adj.entrySet()) {

            s.append(e.getKey() + ": ");
            for (DirectedEdge<E> eg : e.getValue()) {
                s.append(eg + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


}