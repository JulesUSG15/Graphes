package src;


class Edge {
	String initialVertex;
	String finalVertex;
	int value1;
	int value2;

	Edge(String initialVertex, String finalVertex, int value1, int value2) {
		this.initialVertex = initialVertex;
		this.finalVertex = finalVertex;
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	public String toString() {
		return initialVertex + " -> " + finalVertex + " | " + value1 + ", " + value2;
	}
}