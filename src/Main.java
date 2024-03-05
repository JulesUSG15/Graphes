package src;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "cours-representation.gra";
        Graph graph = new Graph(filePath);

        // Affiche le graphe pour vérifier sa structure
        System.out.println(graph);

        // Remplacez 0 par l'identifiant du sommet source à partir duquel vous souhaitez calculer le plus court chemin
        int sourceId = 0;

        Map<Integer, Integer> shortestPaths = graph.plusCourtCheminNbArcs(sourceId);

        System.out.println("Plus court chemin en nombre d'arcs depuis le sommet " + sourceId + " vers tous les autres sommets :");
        for (Map.Entry<Integer, Integer> entry : shortestPaths.entrySet()) {
            int targetId = entry.getKey();
            int distance = entry.getValue();
            System.out.println("Vers le sommet " + targetId + " : " + distance + " arc(s)");
        }
    }
}
