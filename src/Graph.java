package src;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
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

	public int shortestPath(String startId, String endId) {
		if (!vertexExists(startId) || !vertexExists(endId)) {
			System.out.println("One of the vertices does not exist.");
			return -1;
		}

		Queue<Vertex> queue = new LinkedList<>();
		Vertex startVertex = getVertexById(startId);
		queue.add(startVertex);

	// 	// Stocke les distances des sommets depuis le sommet de départ
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
}

