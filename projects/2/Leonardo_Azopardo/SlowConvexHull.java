import java.util.HashSet;

public class SlowConvexHull {

  private Point2D[] pointSet;
  private HashSet<Point2D> convexHullSet;
  private int numPoints;

  //Create an empty point set that can accommodate n points
  public SlowConvexHull(int n) {
    pointSet = new Point2D[n];
    convexHullSet = new HashSet<Point2D>();
    numPoints = 0;
  }

  //Getters

  public Point2D[] getPointSet() { return this.pointSet; }

  public HashSet<Point2D> convexHullSet() { return this.convexHullSet; }

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
    for(Point2D point : convexHullSet)  {
      StdOut.println("" + i + " " + point.x() + " " + point.y());
      i++;
    }
  }

  // Add a point P(x,y) to the point set.
  public void add(double x, double y) {
    pointSet[numPoints++] = new Point2D(x, y);
  }

  private void addToConvexSet(double x, double y) {
      convexHullSet.add(new Point2D(x,y));
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
      Point2D p1 = pointSet[i];

      for(int j = 0; j < numPoints; j++) {
        if (j == i) 
          continue;
        Point2D p2 = pointSet[j];
        int count = 0;

        for(int k = 0; k < numPoints; k++) {
          if (k == i && k == j)
            continue;
          Point2D p3 = pointSet[k];
          if (whichSide(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y()) >= 1)
            count++;
        }
        if (count == numPoints -2) {
          addToConvexSet(p1.x(), p1.y());
          addToConvexSet(p2.x(), p2.y());
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
