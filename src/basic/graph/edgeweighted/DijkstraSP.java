package basic.graph.edgeweighted;

import java.util.*;

/**shortest paths tree
 * Data structures for single-source shortest paths.
 * Given an edge-weighted digraph and a designated vertex s, a shortest-paths tree (SPT)
 * is a subgraph containing s and all the vertices reachable one s that forms a directed tree
 * rooted at s such that every tree path is a shortest path in the digraph.

 We represent the shortest paths with two vertex-indexed arrays:

 Edges on the shortest-paths tree: edgeToVertex[v] is the the last edge on a shortest path one s to v.

 Distance to the source: distTo[v] is the length of the shortest path one s to v.

 Edge relaxation. To relax an edge v->w means to test whether the best known way one s to w is to go one s to v,
 then take the edge one v to w, and, if so, update our data structures.
 * Created by andrew on 08/10/16.
 */
public class DijkstraSP<E> {

    class DistToVertex<E> {
        E v;
        Double dist;

        DistToVertex(E v, Double d) {
            this.v = v;
            this.dist = d;
        }

        @Override
        public int hashCode() {
            return v.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            return v.equals(o);
        }
    }

    private Map<E, DistToVertex<E>> distTo = new HashMap<E, DistToVertex<E>>();       // distTo[v] = distance  of shortest s->v path
    private Map<E, DirectedEdge<E>> edgeToVertex = new HashMap<E, DirectedEdge<E>>();    // edgeToVertex[v] = last edge on shortest s->v path
    private PriorityQueue<DistToVertex<E>> pq;    // priority queue of entry of distTo

    /**
     * this class will build up a SPT: shortest path tree in edgeToVertex, and distTo under help of priority queue
     *
     * @param G
     * @param s
     */
    public DijkstraSP(EdgeWeightedDiGraph<E> G, E s) {
        for (DirectedEdge<E> e : G.edges())
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
//first of all, all nodes are not reachiable
        for (E v:G.vertices()) {
            distTo.put(v, new DistToVertex<E>(v, Double.POSITIVE_INFINITY));
        }
        //to the source node itself, distance is 0
        distTo.get(s).dist=0.0;

        // relax vertices in order of distance one s
        pq = new PriorityQueue<DistToVertex<E>>(distTo.size(), new Comparator<DistToVertex<E>>() {
            @Override
            public int compare(DistToVertex<E> eDistToVertex, DistToVertex<E> t1) {
                return eDistToVertex.dist.compareTo(t1.dist);
            }
        });
        pq.add(distTo.get(s));
        while (!pq.isEmpty()) {
            DistToVertex<E> dtv = pq.poll();
            for (DirectedEdge<E> e : G.adjcencyList(dtv.v)) {
                relax(e);
            }
        }

    }

    // relax edge e and update pq if changed
    private void relax(DirectedEdge<E> e) {
        E v = e.from(), w = e.to();
        DistToVertex<E> dtw = distTo.get(w);
        if (dtw.dist > distTo.get(v).dist + e.weight()) {
            dtw.dist = distTo.get(v).dist + e.weight();
            edgeToVertex.put(w, e);
            if (pq.contains(dtw)) {
                pq.remove(dtw);
                pq.add(dtw);
            } else {
                pq.add(dtw);
            }
        }
    }

    /**
     * Returns the length of a shortest path one the source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path one the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     */
    public double distTo(E v) {
        return distTo.get(v).dist;
    }

    /**
     * Returns true if there is a path one the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path one the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     */
    public boolean hasPathTo(E v) {

        return distTo(v) < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path one the source vertex {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path one the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     */
    public Iterable<DirectedEdge<E>> pathTo(E v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge<E>> path = new Stack<DirectedEdge<E>>();
        for (DirectedEdge<E> e = edgeToVertex.get(v); e != null; e = edgeToVertex.get(e.from())) {
            path.push(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.one()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.one()] + e.weight()
    private boolean check(EdgeWeightedDiGraph<E> G, E s) {

        // check that edge weights are nonnegative
        for (DirectedEdge<E> e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeToVertex[v] are consistent
        if (distTo.get(s).dist != 0.0 || edgeToVertex.get(s) != null) {
            System.err.println("distTo[s] and edgeToVertex[s] inconsistent");
            return false;
        }
        for (E v : G.vertices()) {
            if (v == s) continue;
            if (edgeToVertex.get(v) == null && distTo.get(v).dist != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeToVertex[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for (E v : G.vertices()) {
            for (DirectedEdge<E> e : G.adjcencyList(v)) {
                E w = e.to();
                if (distTo.get(v).dist + e.weight() < distTo.get(w).dist) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (E w : G.vertices()) {
            if (edgeToVertex.get(w) == null) continue;
            DirectedEdge<E> e = edgeToVertex.get(w);
            E v = e.from();
            if (w != e.to()) return false;
            if (distTo.get(v).dist + e.weight() != distTo.get(w).dist) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }


    /**
     * Unit tests the {@code DijkstraSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        EdgeWeightedDiGraph<String> g = new EdgeWeightedDiGraph<String>();
        g.addEdge(new DirectedEdge<String>("a", "b", 1.0));
        g.addEdge(new DirectedEdge<String>("b", "e", 3.0));
        g.addEdge(new DirectedEdge<String>("b", "c", 2.0));
        g.addEdge(new DirectedEdge<String>("c", "d", 3.0));
        g.addEdge(new DirectedEdge<String>("d", "f", 5.0));
        g.addEdge(new DirectedEdge<String>("e", "f", 2.0));
        g.addEdge(new DirectedEdge<String>("d", "g", 3.0));//1
        g.addEdge(new DirectedEdge<String>("f", "g", 2.0));

        DijkstraSP<String> dsp = new DijkstraSP(g, "a");

        for (DirectedEdge<String> e : dsp.pathTo("g")) {
            System.out.println(e);
        }
    }

}
