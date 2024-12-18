import java.util.LinkedList;

public class WeightedVertex {
    String label;
    LinkedList<DijkstraEdge> edges;
    public WeightedVertex(String label) {
        this.label = label;
        this.edges = new LinkedList<>();
    }
}
