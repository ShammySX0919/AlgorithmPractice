package dfs;

import java.util.*;

/**
 * Created by andrew on 23/09/16.
 */
class Edge {
    String from, to;
    double weight;

    public Edge(String a, String b, double w) {
        from = a;
        to = b;
        weight = w;
    }

    public String toString() {
        return from + "->" + to + ":" + weight;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object b) {
        if (b instanceof Edge && toString().equals(b.toString())) {
            return true;
        }
        return false;
    }
}

class EdgeWeightedGraph {
    Map<String, Set<Edge>> adj = new HashMap<>();

    public void addEdge(String a, String b, double weight) {
        Edge one = new Edge(a, b, weight);
        Edge two = new Edge(b, a, 1.0 / weight);
        if (adj.containsKey(a)) {
            adj.get(a).add(one);
        } else {
            Set<Edge> s = new HashSet<>();
            s.add(one);
            adj.put(a, s);
        }
        if (adj.containsKey(b)) {
            adj.get(b).add(two);
        } else {
            Set<Edge> s = new HashSet<Edge>();
            s.add(two);
            adj.put(b, s);
        }
    }

    public boolean contains(String a) {
        return adj.containsKey(a);
    }

    public List<Edge> getPath(String a, String b) {
        Set<Edge> visited = new HashSet<Edge>();
        List<Edge> path = new ArrayList<Edge>();
        path = dfsGetPath(a, b, path, visited);
        return path;
    }

    private List<Edge> dfsGetPath(String start, String end, List<Edge> path, Set<Edge> visited) {
        if (start.equals(end)) {
            //List<DirectedEdge> result = new ArrayList<DirectedEdge>();
            //result.addAll(path);
            return path;
        }
        if (adj.get(start) != null) {
            for (Edge e : adj.get(start)) {
                if (visited.add(e)) {
                    path.add(e);
                    List<Edge> r = dfsGetPath(e.to, end, path, visited);
                    if (r.size() > 0) {
                        return r;
                        //stop further processing
                    }
                    path.remove(e);//next loop starting from same path path
                }
            }
        }
        return new ArrayList<Edge>();
    }
}

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //prepare
        double[] result = new double[queries.length];
        EdgeWeightedGraph g = new EdgeWeightedGraph();
        for (int i = 0; i < values.length; i++) {
            g.addEdge(equations[i][0], equations[i][1], values[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0].equals(queries[i][1]) && g.contains(queries[i][0])) {
                result[i] = 1.0;
                continue;
            }
            List<Edge> r = g.getPath(queries[i][0], queries[i][1]);
            if (r.size() == 0) {
                result[i] = -1.0;
            } else {
                result[i] = 1;
                for (Edge e : r) {
                    result[i] *= e.weight;
                }
            }
        }
        return result;
    }
}
