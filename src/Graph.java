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

	public Map<Integer, List<Integer>> plusCourtsChemins(int source) {
		Map<Integer, Integer> distance = new HashMap<>(); // Distance du sommet source à tous les autres sommets
		Map<Integer, Integer> predecessor = new HashMap<>(); // Prédécesseur de chaque sommet dans le chemin le plus court
		Map<Integer, List<Integer>> shortestPaths = new HashMap<>(); // Pour stocker les chemins les plus courts
	
		for (int vertex : vertices.keySet()) {
			distance.put(vertex, Integer.MAX_VALUE); // Initialise les distances à l'infini
			predecessor.put(vertex, null); // Initialise les prédécesseurs à null
		}
		distance.put(source, 0); // La distance du sommet source à lui-même est 0
	
		Queue<Integer> queue = new LinkedList<>();
		queue.add(source); // Ajoute le sommet source à la file d'attente
	
		while (!queue.isEmpty()) {
			int current = queue.remove(); // Enlève le sommet en tête de la file d'attente
			for (int neighbor : adjacencyList.get(current)) {
				Edge edge = edges.get(neighbor);
				int neighborVertex = edge.idFinalVertex;
				// Si le graphe n'est pas orienté et que le sommet courant n'est pas le sommet initial de l'arc
				if (!isOriented && current != edge.idInitialVertex) {
					neighborVertex = edge.idInitialVertex;
				}
				// Si un chemin plus court est trouvé
				if (distance.get(current) + 1 < distance.get(neighborVertex)) {
					distance.put(neighborVertex, distance.get(current) + 1); // Met à jour la distance
					predecessor.put(neighborVertex, current); // Met à jour le prédécesseur
					queue.add(neighborVertex); // Ajoute le voisin à la file d'attente pour explorer ses voisins
				}
			}
		}
	
		// Construire les chemins les plus courts à partir des prédécesseurs
		for (int vertex : vertices.keySet()) {
			List<Integer> path = new ArrayList<>();
			for (Integer at = vertex; at != null; at = predecessor.get(at)) {
				path.add(0, at); // Ajoute au début de la liste pour inverser le chemin
			}
			if (!path.isEmpty()) {
				shortestPaths.put(vertex, path);
			}
		}
	
		return shortestPaths; // Retourne les chemins les plus courts
	}	

	public List<Integer> trouverPlusCourtChemin(int source, int destination) {
		if (source == destination) {
			List<Integer> cheminDirect = new LinkedList<>();
			cheminDirect.add(source);
			return cheminDirect;
		}

		Map<Integer, Integer> distance = new HashMap<>();
		Map<Integer, Integer> predecesseurs = new HashMap<>();
		for (int vertex : vertices.keySet()) {
			distance.put(vertex, Integer.MAX_VALUE);
			predecesseurs.put(vertex, null); // Initialise les prédécesseurs à null
		}
		distance.put(source, 0);

		Queue<Integer> queue = new LinkedList<>();
		queue.add(source);

		while (!queue.isEmpty()) {
			int current = queue.remove();
			if (current == destination) {
				break; // Arrête la recherche une fois la destination atteinte
			}
			for (int neighbor : adjacencyList.get(current)) {
				Edge edge = edges.get(neighbor);
				int neighborVertex = edge.idFinalVertex;
				if (!isOriented && current != edge.idInitialVertex) {
					neighborVertex = edge.idInitialVertex;
				}
				if (distance.get(current) + 1 < distance.get(neighborVertex)) {
					distance.put(neighborVertex, distance.get(current) + 1);
					predecesseurs.put(neighborVertex, current); // Enregistre le prédécesseur
					queue.add(neighborVertex);
				}
			}
		}

		// Reconstruire le chemin de la source à la destination
		List<Integer> chemin = new LinkedList<>();
		for (Integer at = destination; at != null; at = predecesseurs.get(at)) {
			chemin.add(0, at); // Ajoute au début de la liste pour inverser le chemin
		}

		// Vérifie si un chemin a été trouvé
		if (chemin.get(0) != source) {
			return new LinkedList<>(); // Retourne une liste vide si aucun chemin n'a été trouvé
		}

		return chemin;
	}

}