package src;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		String filePath = "cours-representation.gra";
		Graph graph = new Graph(filePath);
		System.out.println(graph);
	}
}