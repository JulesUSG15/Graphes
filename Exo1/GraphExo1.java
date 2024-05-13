package Exo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class GraphExo1 {

    // Variables
    private HashMap<Integer, VertexExo1> vertices; // Déclare une variable privée pour stocker les sommets du graphe

    // Constructor
    public GraphExo1(String filePath) throws FileNotFoundException {
        vertices = new HashMap<>();
        loadVertices(filePath); // Appelle la méthode loadVertices pour charger les sommets à partir du fichier
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
            vertexId++; // Incrémente l'ID du sommet pour le prochain sommet
        }
        scanner.close();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph:\n");
        for (Integer id : vertices.keySet()) {
            sb.append("Vertex ID " + id + ":\n" + vertices.get(id));
            sb.append("\n");
        }
        return sb.toString();
    }
}