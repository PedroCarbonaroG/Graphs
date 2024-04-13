package algorithms;

import entities.GraphGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphAlgorithms {

    /**
     * @brief Identifies articulations in a graph
     * by testing connectivity after removing each vertex.
     *
     * @param filePath The path to the file containing the graph.
     * @throws IOException if there is an error reading the file.
     */
    public static void identifyArticulations(String filePath) throws IOException {
        // Read graph from file
        LinkedList<Integer>[] graph = readGraphFromFile(filePath);

        int V = graph.length;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; ++i) {
            // Remove vertex i
            LinkedList<Integer>[] temp = new LinkedList[V];
            for (int j = 0; j < V; ++j) {
                temp[j] = new LinkedList<Integer>(graph[j]);
            }
            temp[i] = new LinkedList<Integer>();

            // Test connectivity
            if (!isConnected(temp, visited))
                System.out.println("Articulação encontrada: Vértice " + (i + 1));
        }
    }

    /**
     * @brief Reads a graph from a file and returns it.
     *
     * @param filePath The path to the file containing the graph.
     * @return LinkedList[] representing the graph read from the file.
     * @throws IOException if there is an error reading the file.
     */
    private static LinkedList<Integer>[] readGraphFromFile(String filePath) throws IOException {
        LinkedList<Integer>[] graph;

        // Read graph from file using GraphGenerator
        graph = GraphGenerator.getGraphFromFile(filePath);

        return graph;
    }

    /**
     * @brief Reads a graph from a file and returns it.
     *
     * @param filePath The path to the file containing the graph.
     * @return LinkedList[] representing the graph read from the file.
     * @throws IOException if there is an error reading the file.
     */
    protected static LinkedList<Integer>[] getGraphFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        String[] parts = line.split(" ");
        int V = Integer.parseInt(parts[0]);
        int E = Integer.parseInt(parts[1]);

        LinkedList<Integer>[] graph = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < E; i++) {
            line = bufferedReader.readLine();
            parts = line.split(":");
            int vertex = Integer.parseInt(parts[0].trim());
            String[] edges = parts[1].trim().split("-");
            for (String edge : edges) {
                if (!edge.trim().isEmpty()) {
                    int destination = Integer.parseInt(edge.trim());
                    graph[vertex - 1].add(destination - 1);
                }
            }
        }

        bufferedReader.close();

        return graph;
    }

    /**
     * @brief Checks if a graph is connected using Depth First Search (DFS).
     *
     * @param graph The graph to check.
     * @param visited Array to keep track of visited vertices.
     * @return true if the graph is connected, false otherwise.
     */
    private static boolean isConnected(LinkedList<Integer>[] graph, boolean[] visited) {
        Arrays.fill(visited, false);

        // Find a vertex with non-zero degree
        int i;
        for (i = 0; i < graph.length; ++i)
            if (!graph[i].isEmpty())
                break;

        // If no vertex with non-zero degree, return true (empty graph)
        if (i == graph.length)
            return true;

        // Start DFS traversal from a vertex with non-zero degree
        DFSUtil(i, graph, visited);

        // Check if all connected vertices are visited
        for (i = 0; i < graph.length; ++i)
            if (!visited[i] && !graph[i].isEmpty())
                return false;

        return true;
    }

    /**
     * @brief Depth First Search (DFS) utility function for isConnected method.
     *
     * @param v The current vertex.
     * @param graph The graph to traverse.
     * @param visited Array to keep track of visited vertices.
     */
    private static void DFSUtil(int v, LinkedList<Integer>[] graph, boolean[] visited) {
        visited[v] = true;

        for (int n : graph[v]) {
            if (!visited[n])
                DFSUtil(n, graph, visited);
        }
    }
}
