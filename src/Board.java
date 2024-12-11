import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Board {
    JFrame frame;
    public Board() throws IOException {
        frame = new JFrame("Search Algorithm Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize the DijkstraGraph and add some vertices and edges
        DijkstraGraph graph = new DijkstraGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "C", 10);

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(300, 1000));
        sidePanel.setBackground(Color.GRAY);
        frame.add(sidePanel, BorderLayout.EAST);

        // Create the VisualizationPanel and add it to the board
        VisualizationPanel visualizationPanel = new VisualizationPanel(graph);
        frame.add(visualizationPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }
}
