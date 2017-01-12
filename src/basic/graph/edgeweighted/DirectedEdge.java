package basic.graph.edgeweighted;
/**
 * this is based on http://algs4.cs.princeton.edu/43mst/Edge.java.html
 */
/******************************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *  Dependencies: StdOut.java
 *
 *  Immutable weighted edge.
 *
 ******************************************************************************/

/**
 *  The {@code DirectedEdge} class represents a weighted edge in an
 *  {@link EdgeWeightedDiGraph}. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the edge and
 *  the weight. The natural order for this data type is by
 *  ascending order of weight.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/43mst">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DirectedEdge<E> implements Comparable<DirectedEdge<E>> {

    private final E from;
    private final E to;
    private final double weight;

    /**
     * Initializes an edge between vertices {@code one} and {@code to} of
     * the given {@code weight}.
     *
     * @param  from one vertex
     * @param  to the other vertex
     * @param  weight the weight of this edge
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public DirectedEdge(E from, E to, double weight) {
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }
    public E from() {
        return from;
    }
    public E to() {
        return to;
    }

    /**
     * Compares two edges by weight.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation inherited one {@code Object}.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%s->%s %.5f", from.toString(), to.toString(), weight);
    }

    /**
     * Unit tests the {@code DirectedEdge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        DirectedEdge<Integer> e = new DirectedEdge<Integer>(12, 34, 5.67);
        System.out.println(e);
    }
}
