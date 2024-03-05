package src;

import java.util.ArrayList;
import java.util.List;

class Vertex {
	int id;
	String name;
	List<Integer> values;

	Vertex(String[] args) {
		this.id = Integer.parseInt(args[0]);
		this.name = args[1];
		this.values = new ArrayList<>();
		for (int i = 2; i < args.length; i++) {
			this.values.add(Integer.parseInt(args[i]));
		}
	}

	@Override
	public String toString() {
		return id + " " + name + " " + values;
	}
}