import java.util.*;

public class GraphBFS {

    private Map<String, List<String>> graph = new HashMap<>();

    void addEdge(String src, String dest) {
        graph.putIfAbsent(src, new ArrayList<>());
        graph.putIfAbsent(dest, new ArrayList<>());
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }

    void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        System.out.println("BFS Traversal:");

        while (!queue.isEmpty()) {
            String city = queue.poll();
            System.out.print(city + " -> ");

            for (String neighbor : graph.get(city)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {

        GraphBFS g = new GraphBFS();

        g.addEdge("Hyderabad", "Bangalore");
        g.addEdge("Hyderabad", "Chennai");
        g.addEdge("Bangalore", "Mumbai");
        g.addEdge("Chennai", "Kolkata");
        g.addEdge("Mumbai", "Delhi");

        System.out.println("City Route Finder\n");

        g.bfs("Hyderabad");
    }
}