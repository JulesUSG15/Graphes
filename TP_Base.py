class Graph:
    def __init__(self, directed=False):
        self.directed = directed
        self.adjacency_list = {}
        self.vertex_degrees = {}

    def add_vertex(self, vertex):
        if vertex not in self.adjacency_list:
            self.adjacency_list[vertex] = []
            self.vertex_degrees[vertex] = 0

    def add_edge(self, source, destination):
        if source not in self.adjacency_list:
            self.add_vertex(source)
        if destination not in self.adjacency_list:
            self.add_vertex(destination)

        self.adjacency_list[source].append(destination)
        self.vertex_degrees[source] += 1

        if not self.directed:
            self.adjacency_list[destination].append(source)
            self.vertex_degrees[destination] += 1

    def import_graph(self, graph_data):
        for line in graph_data.split('\n'):
            parts = line.split()
            if len(parts) == 2:  # Assuming format: source destination
                self.add_edge(parts[0], parts[1])

    def display(self):
        for vertex, edges in self.adjacency_list.items():
            print(f"{vertex}: {', '.join(edges)}")

    def display_degrees(self):
        for vertex, degree in self.vertex_degrees.items():
            print(f"{vertex}: Degree {degree}")

# Example of graph data in simple text format (source destination pairs)
graph_data = """
0 1
0 2
1 2
2 3
"""

# Create an instance of Graph (non-directed by default)
g = Graph()

# Import graph data
g.import_graph(graph_data)

# Display graph and degrees
g.display()
g.display_degrees()
