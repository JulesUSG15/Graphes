package Exo2;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // === Exo 1 ===


            // === Exo 2 ===
            System.out.println("=== Exo 2 ===");
            Graph graph = new Graph("graphe-oriente-02.gra");
            for(int i = 0; i < graph.getNbVertices(); i++)
                System.out.println("Nombre de chemins de r à " + graph.getVertex(i).getName() + ": " + nbCheminVersSommet(graph, 9, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // === Exo 1 ===


    // === Exo 2 ===

    static int nbCheminVersSommet(Graph graph, int startId, int endId) {
        if(startId == endId) { // Si on est arrivé à la destination, on a trouvé un chemin
            return 1;
        }
        else {
            List<Integer> neighbors = graph.getNeighbors(startId);
            if(neighbors.isEmpty()) { // Si on est bloqué, on ne peut pas atteindre la destination
                return 0;
            }
            else {
                int nbChemin = 0;
                for(int neighbor : neighbors) { // somme des chemins possibles pour chaque voisin
                    nbChemin += nbCheminVersSommet(graph, neighbor, endId);
                }
                return nbChemin;
            }
        }
    }
}