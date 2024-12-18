import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DijkstraGraph graph = new DijkstraGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("B", "D", 10);
        graph.addEdge("C", "E", 3);
        graph.addEdge("D", "F", 11);
        graph.addEdge("E", "D", 4);
        graph.addEdge("E", "F", 2);
        graph.addEdge("F", "G", 1);
        graph.addEdge("G", "H", 7);
        Board board = new Board(graph);
        board.startAnimation("A");
    }
}