import java.util.LinkedList;
import javax.swing.*;

public class WeightedVertex {
    String label;
    LinkedList<DijkstraEdge> edges;
    public WeightedVertex(String label) {
        this.label = label;
        this.edges = new LinkedList<>();
    }
}
