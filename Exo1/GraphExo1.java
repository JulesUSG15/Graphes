package Exo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphExo1 {

    // Variables
    private HashMap<Integer, VertexExo1> vertices; // Déclare une variable privée pour stocker les sommets du graphe
    private HashMap<Integer, LinkedList<Integer>> adjacencyList;

    // Constructor
    public GraphExo1(String filePath) throws FileNotFoundException {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
        loadVertices(filePath); // Appelle la méthode loadVertices pour charger les sommets à partir du fichier
        loadEdges();
    }

    // Method to load vertices from a file
    private void loadVertices(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath)); // Crée un Scanner pour lire le fichier

        while (scanner.hasNextLine()) { // Boucle tant qu'il y a des lignes à lire dans le fichier
            String line = scanner.nextLine().trim(); // Lit la ligne suivante et supprime les espaces de début et de fin
            if (line.length() != 9) { // Si la ligne ne contient pas exactement 9 caractères
                scanner.close(); // Ferme le scanner
                throw new IllegalArgumentException("Incorrect number of digits for a 3x3 matrix. Expected 9 digits but got " + line.length()); 
            }
            int[][] matrix = new int[3][3]; // Crée une nouvelle matrice 3x3
            for (int i = 0; i < 3; i++) { // Boucle sur les lignes de la matrice
                for (int j = 0; j < 3; j++) { // Boucle sur les colonnes de la matrice
                    matrix[i][j] = Character.getNumericValue(line.charAt(i * 3 + j)); // Convertit le caractère correspondant en nombre et l'ajoute à la matrice
                }
            }
            String vertexId = line; // Utilise la ligne comme ID de sommet
            vertices.put(Integer.parseInt(vertexId), new VertexExo1(Integer.parseInt(vertexId), matrix)); // Ajoute le sommet au graphe
            adjacencyList.put(Integer.parseInt(vertexId), new LinkedList<>());
        }
        scanner.close();
    }

    private void loadEdges() {
        for(Integer id : vertices.keySet()) {
            String matrix = Integer.toString(id);
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(3) + matrix.charAt(0) + matrix.charAt(2) + matrix.charAt(4) + matrix.charAt(1) + matrix.charAt(5) + matrix.charAt(6) + matrix.charAt(7) + matrix.charAt(8)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(1) + matrix.charAt(4) + matrix.charAt(2) + matrix.charAt(0) + matrix.charAt(3) + matrix.charAt(5) + matrix.charAt(6) + matrix.charAt(7) + matrix.charAt(8)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(0) + matrix.charAt(4) + matrix.charAt(1) + matrix.charAt(3) + matrix.charAt(5) + matrix.charAt(2) + matrix.charAt(6) + matrix.charAt(7) + matrix.charAt(8)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(0) + matrix.charAt(2) + matrix.charAt(5) + matrix.charAt(3) + matrix.charAt(1) + matrix.charAt(4) + matrix.charAt(6) + matrix.charAt(7) + matrix.charAt(8)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(0) + matrix.charAt(1) + matrix.charAt(2) + matrix.charAt(6) + matrix.charAt(3) + matrix.charAt(5) + matrix.charAt(7) + matrix.charAt(4) + matrix.charAt(8)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(0) + matrix.charAt(1) + matrix.charAt(2) + matrix.charAt(4) + matrix.charAt(7) + matrix.charAt(5) + matrix.charAt(3) + matrix.charAt(6) + matrix.charAt(8)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(0) + matrix.charAt(1) + matrix.charAt(2) + matrix.charAt(3) + matrix.charAt(7) + matrix.charAt(4) + matrix.charAt(6) + matrix.charAt(8) + matrix.charAt(5)));
            adjacencyList.get(id).add(Integer.parseInt("" + matrix.charAt(0) + matrix.charAt(1) + matrix.charAt(2) + matrix.charAt(3) + matrix.charAt(5) + matrix.charAt(8) + matrix.charAt(6) + matrix.charAt(4) + matrix.charAt(7)));
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        for (Integer id : vertices.keySet()) {
            sb.append("Vertex ID " + id + ":\n" + vertices.get(id));
            sb.append("\n");
        }
        sb.append("\nAdjacency list:\n");
        for (int vertex : adjacencyList.keySet()) {
            sb.append(vertex + " -> " + adjacencyList.get(vertex) + "\n");
        }
        return sb.toString();
    }
}