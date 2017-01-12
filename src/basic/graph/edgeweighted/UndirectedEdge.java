package basic.graph.edgeweighted;

/**
 * Created by andrew on 11/01/17.
 */

public class UndirectedEdge<E> implements Comparable<UndirectedEdge<E>> {

    private final E one;
    private final E another;
    private final double weight;

    public UndirectedEdge(E one, E another, double weight) {
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.one = one;
        this.another = another;
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
    public E one() {
        return one;
    }
    public E another() {
        return another;
    }

    public int compareTo(UndirectedEdge<E> that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%s->%s %.5f", one().toString(), another().toString(), weight);
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
