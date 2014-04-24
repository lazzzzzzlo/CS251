import java.util.NoSuchElementException;

public class SAP {
    Digraph dgraph;

    public SAP(Digraph G) {
        this.dgraph = G;
    }

    public int length (int v, int w) {

        return 0;
    }

    public int ancestor(int v, int w) {

        return 0;
    }

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
