package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Vertex {
	int id;
	String name;
	List<Integer> values;
	LinkedList<Edge> edges;

	Vertex(String[] args) {
		this.id = Integer.parseInt(args[0]);
		this.name = args[1];
		this.values = new ArrayList<>();
		for (int i = 2; i < args.length; i++) {
			this.values.add(Integer.parseInt(args[i]));
		}
		this.edges = new LinkedList<>();
	}

	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id + " " + name + " " + values + "\n Edges:\n");
		for (Edge edge : edges) {
			sb.append(edge + "\n");
		}
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public List<Edge> getEdges() {
        return edges;
    }
}