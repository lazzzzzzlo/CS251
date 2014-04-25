import java.lang.StringBuilder;

public class CriticalVertices {

    public static void main(String [] args) {

        //parse command-line args
        In in = new In(args[0]);
        In temp = new In(args[0]);
        In temp2 = new In(args[0]);

        int n = Integer.parseInt(args[1]);
        int nV = temp.nextInt();
        int nE = temp.nextInt();

        /* init vertex array to their correponding
         * index
         */
        int [] vertices = new int[nV];
        int [] vDistances = new int[nV];

        //Read edge-weighted digraph from an input file
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(in);

        /** Compute shortest path algorithm from each vertex in the graph
         *  to every other vertex.
         *  Compute sum of the distances from each vertesx in the graph 
         *  to every other vertex.
         */

        for(int i = 0; i < nV; i++) {

            DijkstraSP sp = new DijkstraSP(ewd, i);
            int dist = 0;

            for(int j = 1; j < nV; j++) {
                dist += sp.distTo(j);
                int first = 0; 
                DirectedEdge tempEdge;
                for(DirectedEdge de : pathTo(j)) {
                    if (!first) {
                        first++;
                        continue;
                    }
                    tempEdge = de;
                    vertices[de.from()]++;
                }
            }

            vDistances[i] = dist;
        }

        /** If n vertices to print is less than number of
         *  vertices, print number of vertices
         */

        // Sort vertices by high betweenness centrality
        int [] verticesByBetweenness = new int[nV];
        int tempMax = -99999;
        for(int i = 0; i < nV; i++) {
            int j;
            for(j = 0; j < nV; j++) {
                if (vertices[j] > tempMax)
                    tempMax = j;
            }
            verticesByBetweenness[i] = j;
            vertices[j] = -99999;
            tempMax = -99999;
        }

        // Sort vertices by high closeness centrality
        int [] verticesByCloseness = new int[nV];
        int tempMin = 9999999999;
        for(int i = 0; i < nV; i++) {
            int j;
            for(j = 0; j < nV; j++) {
                if (vertices[j] < tempMin)
                    tempMin = j;
            }
            verticesByCloseness[i] = j;
            vertices[j] = 9999999;
            tempMin = 9999999;
        }

        if (n < nV)
            n = nV;

        StringBuilder sb = new StringBuilder();
        sb.append("Vertices with high betweenness centrality:");

        for(int i = 0; i < n; i++) {
            sb.append(" ");
            sb.append(verticesByBetweenness[i]);
            if (i + 1 != n)
                sb.append(",");
        }
        StdOut.println(sb.toString());

        sb = new StringBuilder();
        sb.append("Vertices with high closeness centrality:");
        for(int i = 0; i < n; i++) {
            sb.append(" ");
            sb.append(verticesByCloseness[i]);
            if (i + 1 != n)
                sb.append(",");
        }
        StdOut.println(sb.toString());
    }
}

