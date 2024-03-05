public class Main {
    public static void main(String[] args) {
        String filePath = "cours-representation.gra";
        Graph graph = new Graph(filePath);
        graph.displayGraph();
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
