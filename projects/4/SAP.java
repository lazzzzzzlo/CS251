import java.util.NoSuchElementException;

public class SAP {
    Digraph dgraph;

    public SAP(Digraph G) {
        this.dgraph = G;
    }

    /*go through every vertex, check if it has a path to i
    **if the other vertex has that path too, then i is an ancestral
    **path. find shortest path repeating above process
    **return length of said path, -1 if not found
    */
    public int length (int v, int w) {
        int minL = -1, currL = -1;
        BreadthFirstDirectedPaths bfPathV = new BreadthFirstDirectedPaths(dgraph, v);
        BreadthFirstDirectedPaths bfPathW = new BreadthFirstDirectedPaths(dgraph, w);

        for(int i = 0; i < dgraph.V(); i++) {
            if (bfPathV.hasPathTo(i) && bfPathW.hasPathTo(i)) {
                currL = bfPathV.distTo(i) + bfPathW.distTo(i);
                if (currL < minL || minL < 0) {
                    minL = currL;
                }
            }
        }
        return minL;
    }

   /* go through every vertex, check if it has a path to i
    * if the other vertex has that path too, then i is an ancestral
    * path. find shortest path repeating above process
    * return ancestor with shortest path, -1 if not found
    */
    public int ancestor(int v, int w) {
        int aPath = -1, minL = -1, currL = -1;
        BreadthFirstDirectedPaths bfPathV = new BreadthFirstDirectedPaths(dgraph, v);
        BreadthFirstDirectedPaths bfPathW = new BreadthFirstDirectedPaths(dgraph, w);

        for(int i = 0; i < dgraph.V(); i++) {
            if (bfPathV.hasPathTo(i) && bfPathW.hasPathTo(i)) {
                currL = bfPathV.distTo(i) + bfPathW.distTo(i);
                if (currL < minL || minL < 0) {
                    minL = currL;
                    aPath = i;
                }
            }
        }
        return aPath;
    }

    /* Read input from file
     * find ancestor and length
     * print those
     */
    public static void main(String [] args) {
        In in = new In(args[0]);
        In testIn = new In(args[1]);
        Digraph digh = new Digraph(in);
        SAP sap = new SAP(digh);
        while(testIn.hasNextLine()) {
            try {
                int v = testIn.readInt();
                int w = testIn.readInt();
                StdOut.println("sap = " + sap.length(v, w) + "," + 
                        " ancestor = " + sap.ancestor(v, w));
            }
            catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
