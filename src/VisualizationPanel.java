import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class VisualizationPanel extends JPanel {
    DijkstraGraph graph;
    HashMap<String, Point> vertexPositions;
    HashSet<String> visitedNodes;
    HashSet<DijkstraEdge> visitedEdges;

    public VisualizationPanel(DijkstraGraph graph) {
        this.graph = graph;
        this.vertexPositions = new HashMap<>();
        this.visitedNodes = new HashSet<>();
        this.visitedEdges = new HashSet<>();
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

    public void animation(String startNode) {
        System.out.println("hi");
        new Thread(() -> {
            try {
                HashMap<String, Integer> distances = new HashMap<>();
                HashSet<String> completed = new HashSet<>();
                Queue<WeightedVertex> frontier = new LinkedList<>();

                for (String label : graph.vertices.keySet()) {
                    distances.put(label, Integer.MAX_VALUE);
                }
                distances.put(startNode, 0);

                frontier.add(graph.vertices.get(startNode));
                while (!frontier.isEmpty()) {
                    WeightedVertex current = null;
                    int min = Integer.MAX_VALUE;

                    for (WeightedVertex vertex : frontier) {
                        if (distances.get(vertex.label) < min) {
                            min = distances.get(vertex.label);
                            current = vertex;
                        }
                    }

                    frontier.remove(current);
                    completed.add(current.label);
                    visitedNodes.add(current.label);
                    repaint();
                    Thread.sleep(500);

                    for (DijkstraEdge edge : current.edges) {
                        WeightedVertex neighbor = edge.destination;
                        int newDistance = distances.get(current.label) + edge.weight;

                        if (newDistance < distances.get(neighbor.label)) {
                            distances.put(neighbor.label, newDistance);
                            visitedEdges.add(edge);
                            repaint();
                            Thread.sleep(500);
                        }

                        if (!completed.contains(neighbor.label) && !frontier.contains(neighbor)) {
                            frontier.add(neighbor);
                        }
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (WeightedVertex vertex : graph.vertices.values()) {
            for (DijkstraEdge edge : vertex.edges) {
                Point sourcePos = vertexPositions.get(edge.source.label);
                Point destPos = vertexPositions.get(edge.destination.label);
                if (visitedEdges.contains(edge)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.drawLine(sourcePos.x, sourcePos.y, destPos.x, destPos.y);
                int midX = (sourcePos.x + destPos.x) / 2;
                int midY = (sourcePos.y + destPos.y) / 2;
                g.setColor(Color.RED);
                g.drawString(String.valueOf(edge.weight), midX, midY);
            }
        }
        for (WeightedVertex vertex : graph.vertices.values()) {
            Point position = vertexPositions.get(vertex.label);
            if (visitedNodes.contains(vertex.label)) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillOval(position.x - 15, position.y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(vertex.label, position.x - 10, position.y + 5);
        }
    }
}
