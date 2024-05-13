package src;

import java.util.List;
import java.util.ArrayList;

class Edge {
	int id;
	int idInitialVertex;
	int idFinalVertex;
	List<Integer> values;

	Edge(int id, String[] args) {
		this.id = id;
		this.idInitialVertex = Integer.parseInt(args[0]);
		this.idFinalVertex = Integer.parseInt(args[1]);
		this.values = new ArrayList<>();
		for (int i = 2; i < args.length; i++) {
			this.values.add(Integer.parseInt(args[i]));
		}
	}

	@Override
	public String toString() {
		return idInitialVertex + " " + idFinalVertex + " " + values;
	}

	public int getId() {
        return id;
    }

	public int getIdInitialVertex() {
		return idInitialVertex;
	}

	public int getIdFinalVertex() {
		return idFinalVertex;
	}
}