import java.util.ArrayList;

public class SlowConvexHull2 {

  private Point2DWithIndex[] pointSet;
  private ArrayList<Point2DWithIndex[]> edgesArray;
  private int numPoints;

  //Create an empty point set that can accommodate n points
  public SlowConvexHull2(int n) {
    pointSet = new Point2DWithIndex[n];
    edgesArray = new ArrayList<Point2DWithIndex[]>();
    numPoints = 0;
  }

  //Getters
  public Point2D[] getPointSet() { return this.pointSet; }
  public ArrayList<Point2DWithIndex[]> edgesArray() { return this.edgesArray; }

  //read points from StdIn and add them to the point set
  private void populatePointSet() {

    double x, y;

    while(!StdIn.isEmpty()) {

      x = StdIn.readDouble();
      y = StdIn.readDouble();
      this.add(x,y);
    }
  }

  //print point set
  public void printPointSet() {
    for(int i = 0; i < numPoints; i++)
      StdOut.println(this.pointSet[i].toString());
  }

  public int getMinIndex() {
    int min = Integer.MAX_VALUE;
    int minI = -1;
    for(int i = 0; i < edgesArray.size(); i++) 
      for(Point2DWithIndex p : edgesArray.get(i))
        if (p.i() < min) {
          min = p.i();
          minI = i;
        }
    return minI;
  }

  //print convex hull in counter-clockwise order
  private void printConvexHull() {
    int iMin = getMinIndex();
    if (edgesArray.size() % 2 != 0) {
      for(int i = 0; i < (edgesArray.size() / 2) + 1 ;i++) {
        Point2DWithIndex p1 = edgesArray.get((i + iMin) % edgesArray.size()*2)[0];
        Point2DWithIndex p2 = edgesArray.get((i + iMin) % edgesArray.size()*2)[1];
        StdOut.println(p1.i() + " " + p1.x() + " " + p1.y());
        if (! (i == (edgesArray.size() / 2) ))
          StdOut.println(p2.i() + " " + p2.x() + " " + p2.y());
      }
    }
    else {

      for(int i = 0; i < (edgesArray.size() / 2) ;i++) {
        Point2DWithIndex p1 = edgesArray.get((i + iMin) % edgesArray.size()*2)[0];
        Point2DWithIndex p2 = edgesArray.get((i + iMin) % edgesArray.size()*2)[1];
        StdOut.println(p1.i() + " " + p1.x() + " " + p1.y());
        if (! (i == (edgesArray.size() / 2) - 1 ))
          StdOut.println(p2.i() + " " + p2.x() + " " + p2.y());
      }
    }
  }

  // Add a point P(x,y) to the point set.
  public void add(double x, double y) {
    pointSet[numPoints] = new Point2DWithIndex(x, y, numPoints);
    numPoints++;
  }

  private boolean hasEdge(Point2DWithIndex[] edge1) {
    for(Point2DWithIndex[] edge2 : edgesArray)
      if (edge1[0].equals(edge2[0]) && edge1[1].equals(edge2[1]))
        return true;
    return false;
  }

  //add edge to the edge array
  private void addToEdgesArray(Point2DWithIndex p1, Point2DWithIndex p2) {
      Point2DWithIndex [] points = new Point2DWithIndex[2];
      points[0] = p1;
      points[1] = p2;
      if (!hasEdge(points))
        edgesArray.add(points);
  }

  /* Indicate on which side of the line passing through (x0,y0) 
     and (x1,y1) the point (x,y) is located (-1: right, 0: on the line, 1: left) */
  public static int whichSide(double x0, double y0, double x1, double y1, double x, double y) {
    Point2D p1 = new Point2D(x0,y0);
    Point2D p2 = new Point2D(x1,y1);
    Point2D p3 = new Point2D(x,y);

    return Point2D.ccw(p1, p2, p3);
  }

  //Use the brute force algorithm to find the convex hull
  private void findConvexHull() {
    for(int i = 0; i < numPoints; i++) {
      Point2DWithIndex p1 = pointSet[i];
      for(int j = 0; j < numPoints; j++) {
        Point2DWithIndex p2 = pointSet[j];
        if (! p2.equals(p1)) {
          int count = 0;
          for(int k = 0; k < numPoints; k++) {
            Point2DWithIndex p3 = pointSet[k];
            if (! p3.equals(p1)  && ! p3.equals(p2)) {
              if (whichSide(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y()) == 1)
                count++;
            }
          }
          if (count == numPoints-2) {
            addToEdgesArray(p1,p2);
            break;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    SlowConvexHull2 convexHull = new SlowConvexHull2(StdIn.readInt());
    convexHull.populatePointSet();
    convexHull.findConvexHull();
    convexHull.printConvexHull();
  }
}
