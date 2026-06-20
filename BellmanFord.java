class BellmanFord {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static final int V = 7;
    static final int E = 10;

    static void bellmanFord(Edge[] edges, int source) {
        int[] distance = new int[V];

        // Initialize distances
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;

                if (distance[u] != Integer.MAX_VALUE &&
                    distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative-weight cycles
        for (int j = 0; j < E; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;

            if (distance[u] != Integer.MAX_VALUE &&
                distance[u] + weight < distance[v]) {
                System.out.println("Negative Weight Cycle Detected!");
                return;
            }
        }

        String[] airports = {
            "DEL", "MUM", "HYD", "BLR",
            "CHE", "KOL", "GOA"
        };

        System.out.println("Minimum Travel Cost from DEL:");
        System.out.println("-----------------------------");

        for (int i = 0; i < V; i++) {
            System.out.println(airports[i] + " : " + distance[i]);
        }
    }

    public static void main(String[] args) {

        Edge[] edges = new Edge[E];

        edges[0] = new Edge(0, 1, 6);   // DEL -> MUM
        edges[1] = new Edge(0, 2, 5);   // DEL -> HYD
        edges[2] = new Edge(1, 3, 2);   // MUM -> BLR
        edges[3] = new Edge(2, 1, -2);  // HYD -> MUM
        edges[4] = new Edge(2, 3, -5);  // HYD -> BLR (Discount Route)
        edges[5] = new Edge(1, 4, 4);   // MUM -> CHE
        edges[6] = new Edge(3, 4, 3);   // BLR -> CHE
        edges[7] = new Edge(4, 5, 2);   // CHE -> KOL
        edges[8] = new Edge(5, 6, 4);   // KOL -> GOA
        edges[9] = new Edge(3, 6, 7);   // BLR -> GOA

        bellmanFord(edges, 0); // Source = DEL
    }
}