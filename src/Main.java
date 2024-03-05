package src;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "graphe-nonoriente-01.gra";
        Graph graph = new Graph(filePath);

        System.out.println(graph);

        int sourceVertex = 0;
        Map<Integer, List<Integer>> resultats = graph.plusCourtsChemins(sourceVertex);

        System.out.println("Chemins les plus courts depuis le sommet " + sourceVertex + " :");
        for (Map.Entry<Integer, List<Integer>> entry : resultats.entrySet()) {
            System.out.println("Vers le sommet " + entry.getKey() + " : " + entry.getValue());
        }

        int sourceVertex2 = 0;
        int destinationVertex2 = 3;
        List<Integer> resultats2 = graph.trouverPlusCourtChemin(sourceVertex2, destinationVertex2);

        System.out.println("Chemin le plus court depuis le sommet " + sourceVertex2 + " vers le sommet " + destinationVertex2 + " : " + resultats2);
    }
}

