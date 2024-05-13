package Exo1;

public class EdgeExo1 {
    private int fromId;  // ID du sommet de départ
    private int toId;    // ID du sommet d'arrivée

    // Constructeur
    public EdgeExo1(int fromId, int toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    // Getters
    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    @Override
    public String toString() {
        return "Edge from Vertex " + fromId + " to Vertex " + toId;
    }
}
