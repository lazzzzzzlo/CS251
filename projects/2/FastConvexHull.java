import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class FastConvexHull {

  private Point2D[] pointSet;
  private ArrayList<ArrayList<Point2D>> convexHullList;
  private int numPoints;

  //Create an empty point set that can accommodate n points
  public FastConvexHull(int n) {
    pointSet = new Point2D[n];
    convexHullList = new ArrayList<ArrayList<Point2D>>(2);
  }

  //custom comparator
  class Point2DComparator implements Comparator<Point2D> {
    @Override
    public int compare(Point2D p, Point2D q) { //compare by pattern matching
      if (p.x() < q.x()) return -1;
      if (p.x() > q.x()) return +1;
      if (p.y() < q.y()) return -1;
      if (p.y() > q.y()) return +1;
      return 0;
    }
  }

  //Getters
  public Point2D[] getPointSet() { return this.pointSet; }
  public ArrayList<ArrayList<Point2D>> getConvexHullList() { return this.convexHullList; }

  //read points from StdIn and add them to the point set
  public void populatePointSet() {

    double x, y;

    while(!StdIn.isEmpty()) {
      x = StdIn.readDouble();
      y = StdIn.readDouble();
      this.add(x,y);
    }
  }

  //print point set
  private void printPointSet() {
    if (numPoints == 0) {
      StdOut.println("The point set is empty");
    }
    else {
      for(int i = 0; i < numPoints; i++)
        StdOut.println(this.pointSet[i].toString());
    }
  }

  //get number of edges of the convex hull
  public int getNumEdges() {
    if (this.convexHullList.get(0) == null)
      return 0;
    return this.convexHullList.get(0).size() + this.convexHullList.get(1).size();
  }

  //print the convex hull formed by the point set
  private void printConvexHull() {
    if (this.getNumEdges() == 0)
      StdOut.println("Convex hull is currently empty");
    else {
      int i = 0;
      for(ArrayList<Point2D> list : convexHullList)
        for(Point2D point : list) {
          StdOut.println(i + " " + point.x() + " " + point.y());
          i++;
        }
    }
  }

  // Add a point P(x,y) to the point set.
  public void add(double x, double y) {
    pointSet[numPoints++] = new Point2D(x, y);
  }

  /* Indicate on which side of the line passing through (x0,y0) 
     and (x1,y1) the point (x,y) is located (-1: right, 0: on the line, 1: left) */
  public static int whichSide(double x0, double y0, double x1, double y1, double x, double y) {

    Point2D p1 = new Point2D(x0,y0);
    Point2D p2 = new Point2D(x1,y1);
    Point2D p3 = new Point2D(x,y);

    return Point2D.ccw(p1, p2, p3);
  }

    //sort the point set by x coordinate, in case of a tie, use y
  private void sortPointSet() {
    Arrays.sort(this.pointSet, new Point2DComparator());
  }

  public void findConvexHull() {
    this.sortPointSet();
    ArrayList<Point2D> lowerList = new ArrayList<Point2D>();
    ArrayList<Point2D> upperList = new ArrayList<Point2D>();

    for(int i = 0; i < numPoints; i++) {
      while(lowerList.size() >= 2 && (whichSide(lowerList.get(lowerList.size() - 1).x(),
                                              lowerList.get(lowerList.size() - 1).y(),
                                              lowerList.get(lowerList.size() - 2).x(),
                                              lowerList.get(lowerList.size() - 2).y(),
                                              pointSet[i].x(),
                                              pointSet[i].y()) <= 0))
        lowerList.remove(lowerList.size() - 1);  
      lowerList.add(pointSet[i]);
    }

    for(int i = numPoints - 1; i > 0; i--) {
      while(upperList.size() >= 2 && (whichSide(upperList.get(upperList.size() - 1).x(), 
                                              upperList.get(upperList.size() - 1).y(),
                                              upperList.get(upperList.size() - 2).x(),
                                              upperList.get(upperList.size() - 2).y(),
                                              pointSet[i].x(),
                                              pointSet[i].y()) <= 0))
        upperList.remove(upperList.size() - 1);  
      upperList.add(pointSet[i]);
    }

    lowerList.remove(lowerList.size()-1);
    upperList.remove(upperList.size()-1);

    convexHullList.add(lowerList);
    convexHullList.add(upperList);
  }

  public static void main(String[] args) {
    FastConvexHull convexHull = new FastConvexHull(StdIn.readInt());
    convexHull.populatePointSet();
    convexHull.findConvexHull();
    convexHull.printConvexHull();
  }
}
