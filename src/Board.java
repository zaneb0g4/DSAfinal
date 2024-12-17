import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Board {
    JFrame frame;
    public Board() throws IOException {
        frame = new JFrame("Search Algorithm Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DijkstraGraph graph = new DijkstraGraph();
        for (char label = 'A'; label <= 'J'; label++) {
            graph.addVertex(String.valueOf(label));
        }
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 7);
        graph.addEdge("C", "D", 3);
        graph.addEdge("C", "E", 8);
        graph.addEdge("D", "F", 5);
        graph.addEdge("E", "F", 1);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 9);
        graph.addEdge("H", "I", 3);
        graph.addEdge("I", "J", 4);
        graph.addEdge("E", "J", 2);

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(300, 1000));
        sidePanel.setBackground(Color.GRAY);
        frame.add(sidePanel, BorderLayout.EAST);

        VisualizationPanel visualizationPanel = new VisualizationPanel(graph);
        frame.add(visualizationPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }
}
