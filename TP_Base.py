from collections import deque 
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

def shortest_path_bfs(graph, start_vertex):
    visited = {vertex: False for vertex in graph.adjacency_list}
    distance = {vertex: float('inf') for vertex in graph.adjacency_list}
    queue = deque([start_vertex])
    visited[start_vertex] = True
    distance[start_vertex] = 0

    while queue:
        current_vertex = queue.popleft()
        for neighbor in graph.adjacency_list[current_vertex]:
            if not visited[neighbor]:
                queue.append(neighbor)
                visited[neighbor] = True
                distance[neighbor] = distance[current_vertex] + 1
    return distance

# Example usage
start_vertex = '0'
distances = shortest_path_bfs(g, start_vertex)
print(f"Shortest distances from vertex {start_vertex}: {distances}")

def bfs_shortest_paths(graph, start_vertex):
    visited = {vertex: False for vertex in graph.adjacency_list}
    distance = {vertex: float('inf') for vertex in graph.adjacency_list}
    predecessor = {vertex: None for vertex in graph.adjacency_list}
    queue = deque([start_vertex])
    visited[start_vertex] = True
    distance[start_vertex] = 0

    while queue:
        current_vertex = queue.popleft()
        for neighbor in graph.adjacency_list[current_vertex]:
            if not visited[neighbor]:
                queue.append(neighbor)
                visited[neighbor] = True
                distance[neighbor] = distance[current_vertex] + 1
                predecessor[neighbor] = current_vertex

    # Reconstruct paths from predecessor map
    paths = {}
    for vertex in graph.adjacency_list.keys():
        path = []
        current = vertex
        while current is not None:
            path.insert(0, current)
            current = predecessor[current]
        if path[0] == start_vertex:  # Only consider complete paths
            paths[vertex] = path

    return paths

# Assuming 'g' is our graph instance from previous examples
# Let's find and print all shortest paths from vertex '0'
all_shortest_paths = bfs_shortest_paths(g, '0')

for destination, path in all_shortest_paths.items():
    print(f"Shortest path from 0 to {destination}: {path}")


def bfs_shortest_path2(graph, start_vertex, end_vertex):
    visited = {vertex: False for vertex in graph.adjacency_list}
    distance = {vertex: float('inf') for vertex in graph.adjacency_list}
    predecessor = {vertex: None for vertex in graph.adjacency_list}
    queue = deque([start_vertex])
    visited[start_vertex] = True
    distance[start_vertex] = 0

    while queue:
        current_vertex = queue.popleft()
        if current_vertex == end_vertex:  # Stop if we reach the end vertex
            break
        for neighbor in graph.adjacency_list[current_vertex]:
            if not visited[neighbor]:
                queue.append(neighbor)
                visited[neighbor] = True
                distance[neighbor] = distance[current_vertex] + 1
                predecessor[neighbor] = current_vertex

    # Reconstruct the shortest path from start_vertex to end_vertex
    path = []
    current = end_vertex
    while current is not None:
        path.insert(0, current)
        current = predecessor[current]
        if current == start_vertex:
            path.insert(0, start_vertex)
            break

    if path[0] != start_vertex:  # If the start_vertex is not at the beginning, no path exists
        return []

    return path

# Testing the modified function for a specific start and end vertex
start_vertex = '0'
end_vertex = '3'
path = bfs_shortest_path2(g, start_vertex, end_vertex)
print(f"Shortest path from {start_vertex} to {end_vertex}: {path}")

# Testing the modified function for a specific start and end vertex
start_vertex = '0'
end_vertex = '3'
path = bfs_shortest_path2(g, start_vertex, end_vertex)
print(f"Shortest path from {start_vertex} to {end_vertex}: {path}")

