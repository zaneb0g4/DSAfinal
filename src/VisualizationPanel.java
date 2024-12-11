import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    DijkstraGraph graph;

    public VisualizationPanel(DijkstraGraph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw vertices
        for (WeightedVertex vertex : graph.vertices.values()) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            g.setColor(Color.RED);
            g.fillOval(x, y, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(vertex.label, x + 10, y + 20);
        }

        // Draw edges
        for (WeightedVertex vertex : graph.vertices.values()) {
            for (DijkstraEdge edge : vertex.edges) {
                int x1 = (int) (Math.random() * getWidth());
                int y1 = (int) (Math.random() * getHeight());
                int x2 = (int) (Math.random() * getWidth());
                int y2 = (int) (Math.random() * getHeight());

                g.setColor(Color.BLACK);
                g.drawLine(x1, y1, x2, y2);
                g.setColor(Color.BLUE);
                g.drawString(String.valueOf(edge.weight), (x1 + x2) / 2, (y1 + y2) / 2);
            }
        }
    }
}
