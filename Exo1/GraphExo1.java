package Exo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class GraphExo1 {

    // Variables
    private HashMap<Integer, VertexExo1> vertices; // Déclare une variable privée pour stocker les sommets du graphe
    private HashMap<Integer, HashSet<EdgeExo1>> adjacencyList;  // Liste d'adjacence pour stocker les arêtes

    // Constructor
    public GraphExo1(String filePath) throws FileNotFoundException {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
        loadVertices(filePath); // Appelle la méthode loadVertices pour charger les sommets à partir du fichier
        connectVertices();  // Crée les arêtes en fonction de la logique définie
    }

    // Method to load vertices from a file
    private void loadVertices(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath)); // Crée un Scanner pour lire le fichier
        int vertexId = 0; // Initialise un compteur pour les ID de sommets

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
            vertices.put(vertexId, new VertexExo1(vertexId, matrix)); // Ajoute un nouveau sommet à la HashMap avec l'ID et la matrice
            adjacencyList.put(vertexId, new HashSet<>());  // Initialise la liste d'adjacence pour chaque nouveau sommet
            vertexId++; // Incrémente l'ID du sommet pour le prochain sommet
        }
        scanner.close();
    }

    private void connectVertices() {
        // Méthode pour connecter les sommets avec des arêtes
        for (Integer fromId : vertices.keySet()) {
            for (Integer toId : vertices.keySet()) {
                if (fromId != toId) {
                    VertexExo1 fromVertex = vertices.get(fromId);
                    VertexExo1 toVertex = vertices.get(toId);
    
                    if (checkIfConnectedByRotation(fromVertex, toVertex)) {
                        adjacencyList.get(fromId).add(new EdgeExo1(fromId, toId));
                    }
                }
            }
        }
    }

    private boolean checkIfConnectedByRotation(VertexExo1 fromVertex, VertexExo1 toVertex) {
        // Méthode pour vérifier si deux sommets sont connectés par une rotation
        int[][] fromMatrix = fromVertex.getMatrix();
        int[][] toMatrix = toVertex.getMatrix();
        for (int i = 0; i < 4; i++) {
            if (compareMatrices(fromMatrix, toMatrix)) {
                return true;
            }
            toMatrix = rotateMatrix(toMatrix);
        }
        return false;
    }

    private boolean compareMatrices(int[][] matrix1, int[][] matrix2) {
        // Méthode pour comparer deux matrices
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotateMatrix(int[][] matrix) {
        // Méthode pour faire pivoter une matrice de 90 degrés dans le sens des aiguilles d'une montre
        int[][] rotatedMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotatedMatrix[i][j] = matrix[2 - j][i];
            }
        }
        return rotatedMatrix;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        for (Integer id : vertices.keySet()) {
            sb.append("Vertex ID " + id + ":\n" + vertices.get(id));
            sb.append("Connected to edges: ");
            for (EdgeExo1 edge : adjacencyList.get(id)) {
                sb.append(edge + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}