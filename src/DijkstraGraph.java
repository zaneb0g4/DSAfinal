import java.util.*;
import java.util.LinkedList;

public class DijkstraGraph {
    HashMap<String, WeightedVertex> vertices;

    public DijkstraGraph() {
        vertices = new HashMap<>();
    }

    public void addVertex(String label) {
        if (!vertices.containsKey(label)) {
            WeightedVertex v1 = new WeightedVertex(label);
            vertices.put(label, v1);
        }
    }
    public void addEdge(String label1, String label2, int weight) {
        if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
            WeightedVertex v1 = vertices.get(label1);
            WeightedVertex v2 = vertices.get(label2);

            v1.edges.add(new DijkstraEdge(v1, v2, weight));
            v2.edges.add(new DijkstraEdge(v2, v1, weight));
        }
    }
    public void removeVertex(String label) {
        if (vertices.containsKey(label)) {
            WeightedVertex v1 = vertices.get(label);

            for (DijkstraEdge edge1: v1.edges) {
                WeightedVertex v2 = edge1.destination;

                for (DijkstraEdge edge2: v2.edges) {
                    if (edge2.destination.equals(v1)) {
                        v2.edges.remove(edge2);
                    }
                }
            }

            v1.edges.clear();
            vertices.remove(label);
        }
    }
    public void removeEdge(String label1, String label2) {
        if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
            WeightedVertex v1 = vertices.get(label1);
            WeightedVertex v2 = vertices.get(label2);

            for (DijkstraEdge edge1: v1.edges) {
                if (edge1.destination.equals(v2)) {
                    v1.edges.remove(edge1);
                }
            }

            for (DijkstraEdge edge2: v2.edges) {
                if (edge2.destination.equals(v1)) {
                    v2.edges.remove(edge2);
                }
            }

        }
    }
    public HashMap<String, Integer> dijkstra(String source) {
        HashSet<String> completed = new HashSet<>();
        HashMap<String, Integer> distances = new HashMap<>();
        for(String label : vertices.keySet()){
            distances.put(label, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        LinkedList<WeightedVertex> frontier = new LinkedList<>();
        frontier.add(vertices.get(source));
        while (!frontier.isEmpty()){
            WeightedVertex current = null;
            int min = Integer.MAX_VALUE;
            for(WeightedVertex vertex : frontier){
                if(distances.get(vertex.label) < min){
                    min = distances.get(vertex.label);
                    current = vertex;
                }
            }
            frontier.remove(current);
            completed.add(current.label);
            for(DijkstraEdge edge : current.edges){
                WeightedVertex neighbor = edge.destination;
                int newDistance = distances.get(current.label) + edge.weight;
                if (newDistance < distances.get(neighbor.label)){
                    distances.put(neighbor.label, newDistance);
                }
                if(!completed.contains(neighbor.label) && !frontier.contains(neighbor)){
                    frontier.add(neighbor);
                }
            }
        }
        return distances;
    }
}