package src;


class Vertex {
	String id;
	String name;
	int value;

	Vertex(String id, String name, int value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + value;
	}
}