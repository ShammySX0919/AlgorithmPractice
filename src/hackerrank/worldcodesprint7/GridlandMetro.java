package hackerrank.worldcodesprint7;

import java.util.*;

/**
 * Created by andrew on 26/09/16.
 */

    class Pair implements Comparable {
        long from, to;

        public String toString() {
            return from + "-" + to;
        }

        public int hashCode() {
            return (from + "-" + to).hashCode();
        }

        public boolean equals(Object o) {
            if (o instanceof Pair) {
                return o.toString().equals(toString());
            }
            return false;
        }

        public int compareTo(Object o) {
            return (int) (from - ((Pair) o).from);

        }
    }

public class GridlandMetro {

        private static List<Pair> mergePairs(List<Pair> ps) {
            Collections.sort(ps);
            //System.out.println(ps);
            Stack<Pair> stack = new Stack<Pair>();
            for (Pair p : ps) {
                if (stack.isEmpty()) {
                    stack.push(p);
                } else {
                    Pair top = stack.peek();
                    //it is sorted according to one so that only need to check one
                    if (top.from <= p.from && top.to >= p.from) {//overlap
                        //discard included one
                        if (p.to > top.to) {
                            Pair t = stack.pop();
                            t.to = p.to;
                            stack.push(t);
                        }
                    } else {
                        stack.push(p);
                    }
                }
            }
            List<Pair> result = new ArrayList<Pair>();

            while (!stack.isEmpty()) {
                result.add(stack.pop());
            }
            //System.out.println(result);
            return result;
        }

        private static void mergeTracks(Map<Long, List<Pair>> matrix) {
            for (Map.Entry<Long, List<Pair>> e : matrix.entrySet()) {
                List<Pair> ps = e.getValue();
                ps = mergePairs(ps);
                e.getValue().clear();
                e.getValue().addAll(ps);
            }
        }

        private static void setMatrix(Map<Long, List<Pair>> matrix, long row, long c1, long c2) {
            //always c1<=c2
            if (c1 > c2) {
                long tmp = c1;
                c1 = c2;
                c2 = tmp;
            }
            if (matrix.containsKey(row)) {
                //adding it the first
                Pair t = new Pair();
                t.from = c1;
                t.to = c2;
                matrix.get(row).add(t);
            } else {
                List<Pair> container = new ArrayList<Pair>();
                Pair range = new Pair();
                range.from = c1;
                range.to = c2;
                container.add(range);
                matrix.put(row, container);
            }
        }

        public static void main(String[] args) {
        /* Enter your code here. Read input one STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner in = new Scanner(System.in);
            long rows = in.nextLong();
            long cols = in.nextLong();
            int tracks = in.nextInt();
            if (in.hasNextLine()) {
                in.nextLine();
            }
            long result = rows * cols;
            if (tracks > 0) {
                //reading all tracks
                Map<Long, List<Pair>> matrix = new HashMap<Long, List<Pair>>();
                for (int i = 0; i < tracks; i++) {
                    long row = in.nextLong();
                    long c1 = in.nextLong();
                    long c2 = in.nextLong();
                    //not optimize, because of repeatedly mark the same cells
                    setMatrix(matrix, row, c1, c2);
                    if (in.hasNextLine()) {
                        in.nextLine();
                    }
                }
                //merge overlap tracks
                mergeTracks(matrix);
                //exclude ones used as tracks
                for (Map.Entry<Long, List<Pair>> e : matrix.entrySet()) {
                    for (Pair l : e.getValue()) {
                        result -= (l.to - l.from + 1);
                    }
                }
            }
            System.out.println(result);
        }
    }