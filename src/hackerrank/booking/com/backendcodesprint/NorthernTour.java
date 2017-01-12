package hackerrank.booking.com.backendcodesprint;

import java.util.*;

/**
 * half of test cases passed. this is not correct yet
 */
public class NorthernTour {
    public static void main(String[] args) {

		/*
         * Enter your code here. Read input one STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
        Scanner in = new Scanner(System.in);
        Map<String, CityAndStay> cities = new HashMap<String, CityAndStay>();
        Map<CityAndStay, List<Edge>> graph = new HashMap<CityAndStay, List<Edge>>();
        CityAndStay start = new CityAndStay("Bevagna", 0);

        graph.put(start, new ArrayList<Edge>());
        cities.put(start.city, start);
        int n = in.nextInt();
        in.nextLine();
        // other cities
        for (int i = 0; i < n; i++) {
            String l = in.nextLine();
            String c[] = l.split(",");
            CityAndStay cs = new CityAndStay(c[0], Integer.valueOf(c[1]));
            cities.put(cs.city, cs);
            graph.put(cs, new ArrayList<Edge>());
        }
        // edges
        n = in.nextInt();// num edges
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String l = in.nextLine();
            String[] c = l.split(",");
            Edge e = new Edge(cities.get(c[0]), cities.get(c[1]),
                    Integer.valueOf(c[2]));
            CityAndStay city1 = e.either();
            // bidirectional
            graph.get(city1).add(e);
            graph.get(e.theOther(city1)).add(e);
        }
        in.close();
        int totalHours = 16 * 6;
        Queue<CityAndStay> q = new LinkedList<CityAndStay>();
        q.add(start);

        // now dfs starting one
        // System.out.println("g size "+graph);
        List<List<CityAndStay>> paths = new ArrayList<List<CityAndStay>>();
        List<CityAndStay> path = new ArrayList<CityAndStay>();
        dfsGetAllPaths(graph, start, path, totalHours, paths);
        // System.out.println(paths);
        int max = Integer.MIN_VALUE;
        List<CityAndStay> result = null;
        for (List<CityAndStay> l : paths) {
            if (max < l.size()) {
                max = l.size();
                result = l;
            }
        }
        if (result != null) {
            result.remove(start);
        }
        if (result != null && result.size() > 0) {
            for (CityAndStay s : result) {
                System.out.println(s.city);
            }
        } else {
            System.out.println("NONE");
        }

    }

    private static Edge getEdge(Map<CityAndStay, List<Edge>> graph, CityAndStay p1, CityAndStay p2) {
        for (Edge e : graph.get(p1)) {
            if (e.theOther(p1).equals(p2)) {
                return e;
            }
        }
        return null;
    }

    private static int calculatePathDuration(Map<CityAndStay, List<Edge>> graph, List<CityAndStay> path) {
        if (path.size() == 0) return 0;
        int total = 0;
        CityAndStay p1, p2;
        p1 = path.get(0);
        total = total + p1.duration;//p1's duraton is 0
        for (int i = 1; i < path.size(); i++) {
            p2 = path.get(i);
            Edge e = getEdge(graph, p1, p2);
            if (e == null) break;
            total = total + e.travelTime;
            if (total % 24 >= 16) {//sleep
                total += 24 - total % 24;
            }
            total += p2.duration;//including sleep?
            p1 = p2;
        }
        return total;
    }

    private static void dfsGetAllPaths(Map<CityAndStay, List<Edge>> graph,
                                       CityAndStay curCity, List<CityAndStay> path,
                                       int maxDu, List<List<CityAndStay>> paths) {
        // System.out.println(path);
        // System.out.println(curCity);
        // System.out.println(accuDu+travelTime+curCity.duration>maxDu);
        int totalTime = calculatePathDuration(graph, path);
        if (totalTime > maxDu || path.contains(curCity)) {


            paths.add(new ArrayList<CityAndStay>(path));
            // System.out.println("processed "+path);
            return;
        }
        path.add(curCity);// adding it the first
        // System.out.println(path);
        for (Edge e : graph.get(curCity)) {
            // System.out.println("process "+e.theOther(curCity));
            // -(curCity.duration/24)*8
            dfsGetAllPaths(graph, e.theOther(curCity), path,
                    maxDu, paths);

        }
        path.remove(curCity);
    }

}

class CityAndStay {
    String city;
    Integer duration;

    Integer totalDuration = Integer.MAX_VALUE;

    public CityAndStay(String city, Integer duration) {
        this.city = city;
        this.duration = duration;
    }

    public int hashCode() {
        return this.city.hashCode();
    }

    public boolean equals(Object o) {
        return (o instanceof CityAndStay && this.city
                .equals(((CityAndStay) o).city));
    }

    public String toString() {
        return this.city;
    }
}

class Edge {
    CityAndStay one, theOther;
    Integer travelTime;

    public Edge(CityAndStay one, CityAndStay theOther, Integer travelTime) {
        this.one = one;
        this.theOther = theOther;
        this.travelTime = travelTime;
    }

    CityAndStay either() {
        return this.one;
    }

    CityAndStay theOther(CityAndStay one) {
        Objects.requireNonNull(one);
        if (one.equals(this.one))
            return this.theOther;
        else if (one.equals(this.theOther))
            return this.one;
        else
            throw new IllegalArgumentException("Illegal endpoint");
    }

    public String toString() {
        return one.city + "-" + theOther.city;
    }
}
/**
 * input
 * 18
 * Amsterdam,30
 * California,30
 * Prag,30
 * Heg,30
 * Khulna,30
 * Mumbai,30
 * Delhi,30
 * Kabul,30
 * Colombo,30
 * Bali,30
 * Sydney,30
 * Rome,30
 * Kathmundu,30
 * Pokhra,30
 * Mayami,30
 * Vicecity,30
 * Sanandreas,30
 * Sandm,30
 * 10
 * Khulna,Prag,30
 * Bevagna,Prag,30
 * Bevagna,Mumbai,30
 * Bevagna,Sydney,30
 * Bali,Khulna,30
 * Bevagna,Heg,30
 * Prag,Colombo,30
 * Bevagna,California,30
 * Pokhra,Rome,30
 * Bevagna,Kabul,30
 * <p>
 * output
 * NONE
 */