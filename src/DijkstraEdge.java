public class DijkstraEdge implements Comparable<DijkstraEdge> {
    WeightedVertex source;
    WeightedVertex destination;
    int weight;

    public DijkstraEdge(WeightedVertex source, WeightedVertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public boolean equals(Object other) {
        if (other instanceof DijkstraEdge) {
            DijkstraEdge otherEdge = (DijkstraEdge) other;
            return this.source.equals(otherEdge.source) && this.destination.equals(otherEdge.destination) ||
                    this.source.equals(otherEdge.destination) && this.destination.equals(otherEdge.source);
        }
        return false;
    }

    @Override
    public String toString() {
        return source.label + "->" + destination.label;
    }

    @Override
    public int compareTo(DijkstraEdge o) {
        return this.weight - o.weight;
    }
}
