import java.util.LinkedList;
public class Vertex {
    String label;
    LinkedList<Vertex> neighbors;

    public Vertex(String label) {
        this.label = label;
        this.neighbors = new LinkedList<>();
    }
}
