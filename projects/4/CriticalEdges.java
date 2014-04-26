import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Comparator;

public class CriticalEdges {

    /*
     * for each vertex in graph:
     *      Compute total number of adjacent vertices / total number of possible edges among vertices
     *  The coefficient is 0 for a vertex with <= 1 adjacent vertices(no self loops).
     *  Average this across all vertices in the graph.
     */
    public static double getClusteringCoefficient(Graph g) {

        double totalClustCoeff = 0;
        double possibleVertices = 0;
        double lengthToDouble = 0;

        for(int i = 0; i < g.V(); i++) {
            double tempClustCoeff = 0;
            int length = 0;
            for(Integer j : g.adj(i))
                length++;
            if (length > 1) {
                lengthToDouble = (double) length;
                possibleVertices = (lengthToDouble*(lengthToDouble - 1.0))/2.0;
                tempClustCoeff = lengthToDouble / possibleVertices;
            }
            totalClustCoeff += tempClustCoeff;
        }

        return totalClustCoeff / (double)g.V();
    }

    public static void main(String [] args) {
        In in = new In(args[0]);

        int n = Integer.parseInt(args[1]);
        int nVertices = in.readInt();
        int nEdges = in.readInt();

        final int [][] edgesInMST = new int[nVertices][nVertices];
        Edge[] edges = new Edge[nEdges];

        double weight;
        int edgesAdded = 0;

        /* Do 50 times:
         *  for every edge
         *      assign random weights to edges
         *      compute MSTs
         *  Store number of times the edge appears on the MST
         */
        for(int i = 0; i < 50; i++) { //do 50 times

            EdgeWeightedGraph egraph = new EdgeWeightedGraph(nVertices);

            In tempIn = new In(args[0]);
            tempIn.readInt();
            tempIn.readInt();

            for(int j = 0; j < nEdges; j++) { //for every edge
                weight = StdRandom.uniform();
                Edge edge = new Edge(tempIn.readInt(), tempIn.readInt(), weight);
                egraph.addEdge(edge);
                if (edgesAdded == 0) {
                    edges[j] = edge;
                }
            }
            edgesAdded++;

            KruskalMST mst = new KruskalMST(egraph); //compute MSTs using kruskal's algorithm

            for(Edge edge : mst.edges()) {
                edgesInMST[edge.either()][edge.other(edge.either())]++; //compute times every edge appears in MST 
            }
        }

        /* sort edges by number of times they appear on
         * MSTs
         */
        Arrays.sort(edges, new Comparator<Edge>(){
            public int compare(Edge e1, Edge e2){
                return edgesInMST[e1.either()][e1.other(e1.either())] - 
                       edgesInMST[e2.either()][e2.other(e2.either())];
        }});

        if (n > nEdges)
            n = nEdges;
        // print top N edges, by number of times they appear on MSTs
        StdOut.println("Top edges:");
        for(int i = 0; i < n; i++) {
            StdOut.println("Edge " + edges[i].either() + "-" + edges[i].other(edges[i].either()));
        }
        
        In graphIn = new In(args[0]);
        Graph g = new Graph(graphIn);
        double clusCoeff = getClusteringCoefficient(g);

        StdOut.println("Clustering coefficient of graph: " + clusCoeff);
    }
}
