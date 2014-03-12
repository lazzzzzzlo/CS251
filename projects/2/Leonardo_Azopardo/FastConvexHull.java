import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class FastConvexHull {

  private Point2DWithIndex[] pointSet;
  private ArrayList<ArrayList<Point2DWithIndex>> convexHullList;
  private Point2DWithIndex [] hullArray;
  private int numPoints;

  //Create an empty point set that can accommodate n points
  public FastConvexHull(int n) {
    pointSet = new Point2DWithIndex[n];
    convexHullList = new ArrayList<ArrayList<Point2DWithIndex>>(2);
  }

  //comparator to sort lexicographically
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

  //comparator to sort by index
  class Point2DWithIndexComparator implements Comparator<Point2DWithIndex> {
    @Override
    public int compare(Point2DWithIndex p, Point2DWithIndex q) { //compare by pattern matching
      return (p.i() - q.i() > 0) ? 1 : -1;
    }
  }

  //Getters
  public Point2DWithIndex[] getPointSet() { return this.pointSet; }
  public ArrayList<ArrayList<Point2DWithIndex>> getConvexHullList() { return this.convexHullList; }
  public Point2DWithIndex[] getHullArray() { return this.hullArray; }

  public int getNumEdges() {
    if (this.convexHullList.get(0) == null)
      return 0;
    return this.convexHullList.get(0).size() + this.convexHullList.get(1).size();
  }

  //read points from StdIn and add them to the point set
  public void populatePointSet() {

    int i;
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
  //sort convex hull by index
  public void initializeHullArray(ArrayList<ArrayList<Point2DWithIndex>> convexHullList) {
    hullArray = new Point2DWithIndex[this.getNumEdges()];
    int i = 0;
    for(ArrayList<Point2DWithIndex> list : convexHullList) {
        for(Point2DWithIndex point : list) {
          hullArray[i] = point;
          i++;
        }
    }
  }

  //get smallest index in hull array
  public int getMinIndex() {
    int min = Integer.MAX_VALUE;
    int minI = -1;
    for(int i = 0; i < hullArray.length; i++) {
      Point2DWithIndex p = hullArray[i];
      if (p.i() < min) {
        min = p.i();
        minI = i;
      }
    }
    return minI;
  }

  //print the convex hull formed by the point set
  private void printConvexHull() {
    this.initializeHullArray(this.convexHullList);
    int iMin = getMinIndex();
    for(int i = 0; i < hullArray.length; i++) { //goes through the array circularly
      Point2DWithIndex p = hullArray[(i + iMin) % hullArray.length]; 
      StdOut.println(p.i() + " " + p.x() + " " + p.y());
    }
  }

  // Add a point P(x,y) to the point set.
  public void add(double x, double y) {
    pointSet[numPoints] = new Point2DWithIndex(x, y, numPoints);
    numPoints++;
  }

  /* Indicate on which side of the line passing through (x0,y0) 
     and (x1,y1) the point (x,y) is located (-1: right, 0: on the line, 1: left) */
  public static int whichSide(double x0, double y0, double x1, double y1, double x, double y) {

    Point2D p1 = new Point2D(x0,y0);
    Point2D p2 = new Point2D(x1,y1);
    Point2D p3 = new Point2D(x,y);

    return -Point2D.ccw(p1, p2, p3);
  }

    //sort the point set by x coordinate, in case of a tie, use y
  private void sortPointSet() {
    Arrays.sort(this.pointSet, new Point2DComparator());
  }

  public void findConvexHull() { //Uses the fast convex hull algorithm provided
    sortPointSet();
    ArrayList<Point2DWithIndex> lowerList = new ArrayList<Point2DWithIndex>();
    lowerList.add(pointSet[0]);
    lowerList.add(pointSet[1]);

    for(int i = 2; i < numPoints; i++) { //fill lower list
      while(lowerList.size() >= 2 && (whichSide(lowerList.get(lowerList.size() - 1).x(),
                                              lowerList.get(lowerList.size() - 1).y(),
                                              lowerList.get(lowerList.size() - 2).x(),
                                              lowerList.get(lowerList.size() - 2).y(),
                                              pointSet[i].x(),
                                              pointSet[i].y()) < 0))
        lowerList.remove(lowerList.size() - 1);  
      lowerList.add(pointSet[i]);
    }

    ArrayList<Point2DWithIndex> upperList = new ArrayList<Point2DWithIndex>();
    upperList.add(pointSet[numPoints - 1]);
    upperList.add(pointSet[numPoints - 2]);

    for(int i = numPoints - 3; i > 0; i--) { //fill upper list
      while(upperList.size() >= 2 && (whichSide(upperList.get(upperList.size() - 1).x(), 
                                              upperList.get(upperList.size() - 1).y(),
                                              upperList.get(upperList.size() - 2).x(),
                                              upperList.get(upperList.size() - 2).y(),
                                              pointSet[i].x(),
                                              pointSet[i].y()) < 0))
        upperList.remove(upperList.size() - 1);  
      upperList.add(pointSet[i]);
    }

    lowerList.remove(lowerList.size()-1); //remove last from each list
    upperList.remove(upperList.size()-1);

    //merge the two
    this.convexHullList.add(lowerList);
    this.convexHullList.add(upperList);
  }

  public static void main(String[] args) {
    FastConvexHull convexHull = new FastConvexHull(StdIn.readInt());
    convexHull.populatePointSet();
    convexHull.findConvexHull();
    convexHull.printConvexHull();
  }
}
