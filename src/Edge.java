package src;

import java.util.List;
import java.util.ArrayList;

class Edge {
	int idInitialVertex;
	int idFinalVertex;
	List<Integer> values;
	private int toId; //

	Edge(String[] args, boolean isOriented) {
		if (isOriented) {
			this.idInitialVertex = Integer.parseInt(args[0]);
			this.idFinalVertex = Integer.parseInt(args[1]);
		} else {
			this.idInitialVertex = Integer.parseInt(args[1]);
			this.idFinalVertex = Integer.parseInt(args[0]);
		}
		this.values = new ArrayList<>();
		for (int i = 2; i < args.length; i++) {
			this.values.add(Integer.parseInt(args[i]));
		}
		this.toId = Integer.parseInt(args[1]);
	}

	@Override
	public String toString() {
		return idInitialVertex + " " + idFinalVertex + " | " + values;
	}

	public int getToId() {
        return toId;
    }
}