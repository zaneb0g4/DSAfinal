import java.util.HashMap;

public class Graph {
    public HashMap<String, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    // Creates a new vertex with the given label
    // and adds it to the graph
    public void addVertex(String label) {
        if (!vertices.containsKey(label)) {
            vertices.put(label, new Vertex(label));
        }
    }

    // Adds an edge between the vertices with the given
    // labels to the graph
    public void addEdge(String label1, String label2) {
        Vertex v1 = vertices.get(label1);
        Vertex v2 = vertices.get(label2);
        if (v1 != null && v2 != null && v1.neighbors.contains(v2)) {
            v1.neighbors.add(v2);
            v2.neighbors.add(v1);
        }
    }

    // Checks to see if there is an edges between the
    // vertices with the given labels
    public boolean hasEdge(String label1, String label2) {
        Vertex v1 = vertices.get(label1);
        Vertex v2 = vertices.get(label2);
        return v1 != null && v2 != null && v1.neighbors.contains(v2);
    }

    // Removes the given vertex and all of its edges from
    // the graph
    public void removeVertex(String label) {
        Vertex a = vertices.remove(label);
        if (a != null) {
            for (Vertex neighbor : a.neighbors) {
                neighbor.neighbors.remove(a);
            }
        }
    }

    // Removes the edge between the given vertices
    public void removeEdge(String label1, String label2) {
        Vertex v1 = vertices.get(label1);
        Vertex v2 = vertices.get(label2);
        if (v1 != null && v2 != null) {
            v1.neighbors.remove(v2);
            v2.neighbors.remove(v1);
        }
    }

    public void printGraph() {
        int longest = 7;
        for (String str : vertices.keySet()) {
            longest = Math.max(longest, str.length() + 1);
        }

        String line = "Vertex ";
        for (int i = 7; i < longest; i++)
            line += " ";
        int leftLength = line.length();
        line += "| Adjacent Vertices";
        System.out.println(line);

        for (int i = 0; i < line.length(); i++) {
            System.out.print("-");
        }
        System.out.println();

        for (String str : vertices.keySet()) {
            Vertex v1 = vertices.get(str);

            for (int i = str.length(); i < leftLength; i++) {
                str += " ";
            }
            System.out.print(str + "| ");

            for (int i = 0; i < v1.neighbors.size() - 1; i++) {
                System.out.print(v1.neighbors.get(i).label + ", ");
            }

            if (!v1.neighbors.isEmpty()) {
                System.out.print(v1.neighbors.get(v1.neighbors.size() - 1).label);
            }

            System.out.println();
        }
    }
}
