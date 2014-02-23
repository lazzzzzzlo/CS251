public class SlowConvexHull {

  private Point2D[] pointSet;
  private int numPoints;

  //Create an empty point set that can accommodate n points
  public SlowConvexHull(int n) {
    pointSet = new Point2D[n];
    numPoints = 0;
  }

  //Return current number of points
  private int getNumPoints() {
    return numPoints;
  }

  //return point set
  private Point2D [] getPointSet() {

    return pointSet;
  
  }

  //print point set
  private void printPointSet() {
    for(int i = 0; i < numPoints; i++)
      StdOut.println(this.pointSet[i].toString());
  }

  // Add a point P(x,y) to the point set.
  public void add(double x, double y) {
    pointSet[numPoints++] = new Point2D(x, y);
  }

  /* Indicate on which side of the line passing through (x0,y0) 
     and (x1,y1) the point (x,y) is located (-1: right, 0: on the line, 1: left) */
  public static int whichSide(double x0, double y0, double x1, double y1, double x, double y) {

    Point2D point1 = new Point2D(x0,y0);
    Point2D point2 = new Point2D(x1,y1);
    Point2D point3 = new Point2D(x,y);

    return -Point2D.ccw(point1, point2, point3);

  }

  //read points from StdIn and add them to the point set
  private void populatePointSet() {

    double x, y;

    while(!StdIn.isEmpty()) {

      x = StdIn.readDouble();
      y = StdIn.readDouble();
      this.add(x,y);
    }
  }

  public static void main(String[] args) {
    SlowConvexHull convexHull = new SlowConvexHull(StdIn.readInt());
    convexHull.populatePointSet();
  }

}
