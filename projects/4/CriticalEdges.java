import NoSuchElementException;

public class CriticalEdges {

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

        for(int i = 0; i < 50; i++) {

            EdgeWeightedGraph egraph = new EdgeWeightedGraph(nVertices);

            for(int j = 0; j < nEdges; j++) {
                weight = StdRandom.uniform();
                Edge edge = new Edge(in.nextInt(), in.nextInt(), weight)
                    egraph.addEdge(edge);
                if (!edgesAdded) {
                    edges[j] = edge;
                    edgesAdded++;
                }
            }

            KruskalMST mst = new KruskalMST(egraph);
            for(Edge edges : mst.edges)
                edgesInMST[edges.either][edges.other]++;
        }

        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge e1, e2) {
                return edgesInMST[e1.either][e1.other] - 
            edgesInMST[e2.either][e2.other];
            }
        });
        StdOut.println("Top edges:");
        for(int i = 0; i < n; i++) {
            StdOut.println("Edge " + edges[i].either + "-" + edges[i].other);
        }
        StdOut.println("Clustering coefficient of graph: " + );
}

