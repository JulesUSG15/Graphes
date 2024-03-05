package src;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		String filePath = "graphe-communes.gra";
		Graph graph = new Graph(filePath);
		System.out.println(graph);

		// ID du sommet de départ pour calculer les plus courts chemins
        String startVertexId2 = "0";
        
        Map<Vertex, Integer> shortestPaths = graph.plusCourtCheminNbArcs(startVertexId2);
        if(shortestPaths != null) {
            System.out.println("Plus courts chemins depuis le sommet " + startVertexId2 + " :");
            for (Map.Entry<Vertex, Integer> entry : shortestPaths.entrySet()) {
                Vertex vertex = entry.getKey();
                Integer distance = entry.getValue();
                System.out.println("Vers le sommet " + vertex.id + " (" + vertex.name + ") : " + distance + " arc(s)");
            }
        } else {
            System.out.println("Le sommet de départ n'existe pas dans le graphe.");
        }

		String startVertexId = "0"; // ID du sommet de départ
		String endVertexId = "4";   // ID du sommet d'arrivée
		
		int shortestPathDistance = graph.shortestPath(startVertexId, endVertexId);
		if (shortestPathDistance != -1) {
			System.out.println("Le plus court chemin de " + startVertexId + " à " + endVertexId + " est de " + shortestPathDistance + " arc(s).");
		} else {
			System.out.println("Aucun chemin trouvé entre " + startVertexId + " et " + endVertexId);
		}

	}
}