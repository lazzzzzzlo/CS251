import java.lang.StringBuilder;

public class CriticalVertices {

    public static void main(String [] args) {
        In in = new In(args[0]);
        In temp = new In(args[0]);

        for(int i = 0; i < nEdges; i++) {
            int curr = temp.nextInt();
            if (curr > maxInt)
                maxInt = curr;
        }
        int [] vertices = new int[maxInt][maxInt];

        int n = Integer.parseInt(args[1]);
        EdgeWeightedDigraph ewd= new EdgeWeightedDigraph(in);
        DijkstraAllPairsSp sps = new DijkstraAllPairsSp(ewd);
        for(int i = 0; i < ewd.V(); i++) {
            for(int j = 0; j < ewd.V(); j++) {
                dEdge tEdge;
                int first = 0;
                for(DirectedEdge dEdge : sps.path(i, j)) {
                    if (!first)
                        continue;
                    vertices[dEdge.from()]++;
                    vertices[dEdge.to()]++;
                    temp = dEdge;
                }
                edges[temp.from()][temp.to()]--;
            }
        }

        Iterable<DirectedEdge> dEdges = ewd.edges();

        Collections.sort(dEdges, new Comparator<>() {
            public int compare(DirectedEdge e1, DirectedEdge2) {
                return edges[e1.either][e1.other] - 
            edgesInMST[e2.either][e2.other];
            }
        });

        if (n > ewd.V())
            n = ewd.V();

        StringBuilder out1 = new StringBuilder("");
        out1.append("Vertices with high betweeenness centrality: ");
        for(int i = 0; i < n; i++) {
            out1.append();
        }
    }
}

