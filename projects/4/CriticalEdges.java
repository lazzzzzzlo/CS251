import java.util.NoSuchElementException;
import java.util.Collections;

public class CriticalEdges {

    public static double getClusteringCoefficient(Graph g) {
        double clustCoeff = 0;

        return clustCoeff;
    }

    public static void main(String [] args) {
        In in = new In(args[0]);
        In temp = new In(args[0]);

        int n = Integer.parseInt(args[1]);
        int nVertices = in.nextInt();
        int nEdges = in.nextInt();

        int maxInt = -99999999;
        for(int i = 0; i < nEdges; i++) {
            int curr = temp.nextInt();
            if (curr > maxInt)
                maxInt = curr;
        }

        int [][] edgesInMST = new int[maxInt][maxInt];

        double weight;
        int edgesAdded = 0;

        /* Do 50 times:
         *  for every edge
         *      assign random weights to edges
         *      compute MSTs
         *  Store number of times the edge appears on the MST
         */
        for(int i = 0; i < 50; i++) {

            EdgeWeightedGraph egraph = new EdgeWeightedGraph(nVertices);

            for(int j = 0; j < nEdges; j++) {
                weight = StdRandom.uniform();
                Edge edge = new Edge(in.nextInt(), in.nextInt(), weight);
            }

            KruskalMST mst = new KruskalMST(egraph);
            for(Edge edge : mst.edges)
                //
        }

        /* sort edges by number of times they appear on
         * MSTs
         */
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                return edgesInMST[e1.either][e1.other] - 
            edgesInMST[e2.either][e2.other];
            }
        });

        // print top N edges, by number of times they appear on MSTs
        StdOut.println("Top edges:");
        for(int i = 0; i < n; i++) {
            StdOut.println("Edge " + edges[i].either + "-" + edges[i].other);
        }

        /*
         * for each vertex in graph:
         *      Compute total number of adjacent vertices / total number of possible edges among vertices
         *  The coefficient is 0 for a vertex with <= 1 adjacent vertices(no self loops).
         *  Average this across all vertices in the graph.
         */
        In graphIn = new In(args[0]);
        Graph g = new Graph(graphIn);
        double clusCoeff = getClusteringCoefficient(g);

        StdOut.println("Clustering coefficient of graph: " + clusCoeff);
    }
}
