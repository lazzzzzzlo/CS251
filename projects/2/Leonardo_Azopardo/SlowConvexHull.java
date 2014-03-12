import java.util.HashSet;

public class SlowConvexHull {

  private Point2DWithIndex[] pointSet;
  private HashSet<Point2DWithIndex> convexHullSet; //a hashset is used so repeated points are not added
  private int numPoints;

  //Create an empty point set that can accommodate n points
  public SlowConvexHull(int n) {
    pointSet = new Point2DWithIndex[n];
    convexHullSet = new HashSet<Point2DWithIndex>();
    numPoints = 0;
  }

  //Getters
  public Point2DWithIndex[] getPointSet() { return this.pointSet; }
  public HashSet<Point2DWithIndex> convexHullSet() { return this.convexHullSet; }

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

  //print the convex hull formed by the point set
  public void printConvexHull() {
    if (this.convexHullSet.size() == 0)
      StdOut.println("convex hull is empty");
    int i = 0;
    for(Point2DWithIndex point : convexHullSet)  {
      StdOut.println("" + point.i() + " " + point.x() + " " + point.y());
      i++;
    }
  }

  // Add a point P(x,y) to the point set.
  public void add(double x, double y) {
    pointSet[numPoints] = new Point2DWithIndex(x, y, numPoints);
    numPoints++;
  }

  //Add point to the convex set
  private void addToConvexSet(double x, double y, int index) {
    convexHullSet.add(new Point2DWithIndex(x,y,index));
  }

  /* Indicate on which side of the line passing through (x0,y0) 
     and (x1,y1) the point (x,y) is located (-1: right, 0: on the line, 1: left) */
  public static int whichSide(double x0, double y0, double x1, double y1, double x, double y) {

    Point2DWithIndex p1 = new Point2DWithIndex(x0,y0,0);
    Point2DWithIndex p2 = new Point2DWithIndex(x1,y1, 0);
    Point2DWithIndex p3 = new Point2DWithIndex(x,y, 0);

    return Point2DWithIndex.ccw(p1, p2, p3);

  }

  //Use the brute force algorithm to find the convex hull
  private void findConvexHull() {
    for(int i = 0; i < numPoints; i++) {
      Point2DWithIndex p1 = pointSet[i];

      for(int j = 0; j < numPoints; j++) {
        if (j == i) 
          continue;
        Point2DWithIndex p2 = pointSet[j];
        int count = 0;

        for(int k = 0; k < numPoints; k++) {
          if (k == i && k == j)
            continue;
          Point2DWithIndex p3 = pointSet[k];
          if (whichSide(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y()) >= 1)
            count++;
        }
        if (count == numPoints-2) {
          addToConvexSet(p1.x(), p1.y(), p1.i());
          addToConvexSet(p2.x(), p2.y(), p2.i());
          break;
        }
      }
    }
  }

  public static void main(String[] args) {
    SlowConvexHull convexHull = new SlowConvexHull(StdIn.readInt());
    convexHull.populatePointSet();
    convexHull.findConvexHull();
    convexHull.printConvexHull();
  }
}
