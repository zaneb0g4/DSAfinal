import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class VisualizationPanel extends JPanel {
    DijkstraGraph graph;

    public VisualizationPanel(DijkstraGraph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 200;

        double angleStep = 2 * Math.PI / graph.vertices.size();
        int i = 0;

        HashMap<String, Point> vertexPositions = new HashMap<>();

        for (WeightedVertex vertex : graph.vertices.values()) {
            int x = (int) (centerX + radius * Math.cos(i * angleStep));
            int y = (int) (centerY + radius * Math.sin(i * angleStep));
            vertexPositions.put(vertex.label, new Point(x, y));
            g.setColor(Color.BLUE);
            g.fillOval(x - 15, y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(vertex.label, x - 10, y + 5);
            i++;
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
