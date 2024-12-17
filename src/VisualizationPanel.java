import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VisualizationPanel extends JPanel {
    DijkstraGraph graph;
    HashMap<String, Point> vertexPositions;

    public VisualizationPanel(DijkstraGraph graph) {
        this.graph = graph;
        this.vertexPositions = new HashMap<>();
        generateVertexPositions();
    }

    private void generateVertexPositions() {
        int panelWidth = 800;
        int panelHeight = 800;
        int margin = 50;
        for (WeightedVertex vertex : graph.vertices.values()) {
            int x, y;
            boolean validPosition;
            do {
                x = margin + (int) (Math.random() * (panelWidth - 2 * margin));
                y = margin + (int) (Math.random() * (panelHeight - 2 * margin));
                validPosition = true;

                for (Point existing : vertexPositions.values()) {
                    if (existing.distance(x, y) < 50) {
                        validPosition = false;
                        break;
                    }
                }
            } while (!validPosition);

            vertexPositions.put(vertex.label, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (WeightedVertex vertex : graph.vertices.values()) {
            Point position = vertexPositions.get(vertex.label);
            g.setColor(Color.BLUE);
            g.fillOval(position.x - 15, position.y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(vertex.label, position.x - 10, position.y + 5);
        }

        for (WeightedVertex vertex : graph.vertices.values()) {
            for (DijkstraEdge edge : vertex.edges) {
                Point sourcePos = vertexPositions.get(edge.source.label);
                Point destPos = vertexPositions.get(edge.destination.label);
                g.setColor(Color.BLACK);
                g.drawLine(sourcePos.x, sourcePos.y, destPos.x, destPos.y);
                int midX = (sourcePos.x + destPos.x) / 2;
                int midY = (sourcePos.y + destPos.y) / 2;
                g.setColor(Color.RED);
                g.drawString(String.valueOf(edge.weight), midX, midY);
            }
        }
    }
}
