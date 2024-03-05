package src;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;


public class Graph {
	private boolean isOriented;
	private int nbVertices;
	private int nbEdges;
	private HashMap<Integer, Vertex> vertices;
	private HashMap<Integer, Edge> edges;
	private HashMap<Integer, LinkedList<Integer>> adjacencyList;

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
					this.vertices = new HashMap<>();
					this.adjacencyList = new HashMap<>();
					for (int i = 0; i < this.nbVertices; i++) {
						line = scanner.nextLine();
						String[] parts = line.split(" ");
						vertices.put(Integer.parseInt(parts[0]), new Vertex(parts));
						adjacencyList.put(Integer.parseInt(parts[0]), new LinkedList<>());
					}
				}

				// Edges
				else if (line.startsWith("EDGES")) {
					this.edges = new HashMap<>();
					for(int i = 0; i < this.nbEdges; i++) {
						line = scanner.nextLine();
						String[] parts = line.split(" ");
						edges.put(i, new Edge(i, parts));
						adjacencyList.get(Integer.parseInt(parts[0])).add(i);
						if (!this.isOriented)
							adjacencyList.get(Integer.parseInt(parts[1])).add(i);
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
		sb.append("Number of edges: " + this.nbEdges + "\n\n");
		sb.append("Vertices:\n");
		for (Vertex vertex : vertices.values()) {
			sb.append(vertex + "\n");
		}
		sb.append("\nEdges:\n");
		for (Edge edge : edges.values()) {
			sb.append(edge + "\n");
		}
		sb.append("\nAdjacency list:\n");
		for (int vertex : adjacencyList.keySet()) {
			sb.append(vertex + " -> " + adjacencyList.get(vertex) + "\n");
		}
		return sb.toString();
	}
}