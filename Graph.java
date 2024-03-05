import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {
    private boolean isOriented;
    private int nbVertices;
    private ArrayList<Vertex> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    // Constructeur
    public Graph(String filePath) {
        importGraph(filePath);
    }

    private void importGraph(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("ORIENTED:")) {
                    this.isOriented = line.split(" ")[1].trim().equals("true");
                } else if (line.startsWith("NB_VERTICES:")) {
                    this.nbVertices = Integer.parseInt(line.split(" ")[1].trim());
                } else if (line.startsWith("VERTICES")) {
                    for (int i = 0; i < this.nbVertices; i++) {
                        line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        vertices.add(new Vertex(parts[0], parts[1], Integer.parseInt(parts[2])));
                    }
                } else if (line.startsWith("EDGES")) {
                    while (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        String[] parts = line.split(" ");
                        edges.add(new Edge(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayGraph() {
        System.out.println("Graph is oriented: " + this.isOriented);
        System.out.println("Number of vertices: " + this.nbVertices);
        System.out.println("Vertices:");
        for (Vertex vertex : vertices) {
            System.out.println(vertex);
        }
        System.out.println("Edges:");
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }

     public int shortestPath(String startId, String endId) {
        if (!vertexExists(startId) || !vertexExists(endId)) {
            System.out.println("One of the vertices does not exist.");
            return -1;
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex startVertex = getVertexById(startId);
        queue.add(startVertex);

        // Stocke les distances des sommets depuis le sommet de départ
        int[] distances = new int[vertices.size()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = -1; // Initialisation avec -1 pour indiquer que le sommet n'a pas encore été visité
        }
        distances[Integer.parseInt(startVertex.id)] = 0;

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Edge e : edges) {
                if (e.initialVertex.equals(current.id)) {
                    Vertex next = getVertexById(e.finalVertex);
                    if (distances[Integer.parseInt(next.id)] == -1) {
                        queue.add(next);
                        distances[Integer.parseInt(next.id)] = distances[Integer.parseInt(current.id)] + 1;
                        if (next.id.equals(endId)) {
                            return distances[Integer.parseInt(next.id)];
                        }
                    }
                }
            }
        }

        return -1; // Retourner -1 si aucun chemin n'est trouvé
    }

    private boolean vertexExists(String id) {
        for (Vertex v : vertices) {
            if (v.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

    private Vertex getVertexById(String id) {
        for (Vertex v : vertices) {
            if (v.id.equals(id)) {
                return v;
            }
        }
        return null; // Doit gérer null dans le code appelant
    }

    // Classes Vertex et Edge ici
}

class Vertex {
    String id;
    String name;
    int value;

    Vertex(String id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + value;
    }
}

class Edge {
    String initialVertex;
    String finalVertex;
    int value1;
    int value2;

    Edge(String initialVertex, String finalVertex, int value1, int value2) {
        this.initialVertex = initialVertex;
        this.finalVertex = finalVertex;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return initialVertex + " -> " + finalVertex + " | " + value1 + ", " + value2;
    }
}
