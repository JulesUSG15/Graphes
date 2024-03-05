package src;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;


public class Graph {
	private boolean isOriented;
	private int nbVertices;
	private int nbEdges;
	private HashMap<Integer, Vertex> vertices = new HashMap<>();

	// Constructeur
	public Graph(String filePath) {
		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextLine()) {
				// Header
				String line = scanner.nextLine();
				if (line.startsWith("ORIENTED:")) {
					this.isOriented = line.split(" ")[1].trim().equals("true");
				}
				else if (line.startsWith("NB_VERTICES:")) {
					this.nbVertices = Integer.parseInt(line.split(" ")[1].trim());
				}
				else if (line.startsWith("NB_EDGES:")) {
					this.nbEdges = Integer.parseInt(line.split(" ")[1].trim());
				}

				// Vertices
				else if (line.startsWith("VERTICES")) {
					for (int i = 0; i < this.nbVertices; i++) {
						line = scanner.nextLine();
						String[] parts = line.split(" ");
						vertices.put(Integer.parseInt(parts[0]), new Vertex(parts));
					}
				}

				// Edges
				else if (line.startsWith("EDGES")) {
					for(int i = 0; i < this.nbEdges; i++) {
						line = scanner.nextLine();
						String[] parts = line.split(" ");
						vertices.get(Integer.parseInt(parts[0])).addEdge(new Edge(parts, true));
						if(!isOriented) {
							vertices.get(Integer.parseInt(parts[1])).addEdge(new Edge(parts, false));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Graph is oriented: " + this.isOriented + "\n");
		sb.append("Number of vertices: " + this.nbVertices + "\n");
		sb.append("Vertices:\n");
		for (Vertex vertex : vertices.values()) {
			sb.append(vertex + "\n");
		}
		return sb.toString();
	}

	public Map<Integer, Integer> plusCourtCheminNbArcs(int sourceId) {
        // Initialisation
        Map<Integer, Integer> distances = new HashMap<>();
        for (int vertexId : vertices.keySet()) {
            distances.put(vertexId, Integer.MAX_VALUE); // On initialise la distance à l'infini
        }
        distances.put(sourceId, 0); // Distance du sommet source à lui-même est 0

        // File pour le parcours en largeur
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sourceId);

        // Parcours en largeur
        while (!queue.isEmpty()) {
            int currentId = queue.poll(); // Récupération du sommet courant
            Vertex currentVertex = vertices.get(currentId);

            // Pour chaque voisin du sommet courant
            for (Edge edge : currentVertex.getEdges()) {
                int neighborId = edge.getToId();
                // Si on a trouvé un chemin plus court
                if (distances.get(neighborId) == Integer.MAX_VALUE) {
                    distances.put(neighborId, distances.get(currentId) + 1); // Mise à jour de la distance
                    queue.add(neighborId); // Ajout du voisin à explorer
                }
            }
        }

        return distances;
    }

	
}