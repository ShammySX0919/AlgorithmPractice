package basic.graph.edgeweighted.mst;

import basic.graph.edgeweighted.UndirectedEdge;

import java.util.*;

/**
 * Created by andrew on 11/01/17.
 */
public class Kruskal<E> {

    class Graph<E> {
        Map<E, Set<UndirectedEdge<E>>> g = new HashMap<E, Set<UndirectedEdge<E>>>();

        public void addEdge(UndirectedEdge<E> edge) {
            if (g.containsKey(edge.one())) {
                g.get(edge.one()).add(edge);
            } else {
                Set<UndirectedEdge<E>> s = new HashSet<UndirectedEdge<E>>();
                s.add(edge);
                g.put(edge.one(), s);
            }
            if (g.containsKey(edge.another())) {
                g.get(edge.another()).add(edge);
            } else {
                Set<UndirectedEdge<E>> s = new HashSet<UndirectedEdge<E>>();
                s.add(edge);
                g.put(edge.another(), s);
            }
        }

        public Set<UndirectedEdge<E>> edges() {
            Set<UndirectedEdge<E>> s = new HashSet<UndirectedEdge<E>>();
            for (Map.Entry<E, Set<UndirectedEdge<E>>> e : g.entrySet()) {
                s.addAll(e.getValue());
            }
            return s;
        }
    }

    class MST<E> {
        Set<UndirectedEdge<E>> mstEdges = new HashSet<UndirectedEdge<E>>();
        //instead of using union-fold structure, I use a set here to demonstrate
        Set<E> mstVertices = new HashSet<E>();

        MST(Graph<E> g) {
            //1.put edges to priority queue, edge is comparable by weight
            PriorityQueue<UndirectedEdge<E>> pq = new PriorityQueue<>();
            for (UndirectedEdge<E> edge : g.edges()) {
                pq.offer(edge);
            }
            int targetSize = g.edges().size() - 1;
            while (!pq.isEmpty() && mstEdges.size() < targetSize) {
                //pick out the one with minimum weight
                UndirectedEdge<E> e = pq.poll();
                //add it to MST if it does not make a cycle
                if (!mstVertices.contains(e.one()) && !mstVertices.contains(e.another())) {
                    mstEdges.add(e);
                    //then add vertices
                    mstVertices.add(e.one());
                    mstVertices.add(e.another());
                }
            }
        }

        Set<UndirectedEdge<E>> getEdges() {
            return mstEdges;
        }
    }

}
