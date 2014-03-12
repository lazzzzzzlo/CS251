import java.awt.Color;
import java.util.ArrayList;

public class ConvexHullVisualizer {

  //draw the point set
  public static void drawPointSet(FastConvexHull convexHull) {
    StdDraw.clear(new Color(0,0,0));
    StdDraw.setXscale();
    StdDraw.setYscale();
    StdDraw.setPenColor(51,255,51);
    Point2D [] pointSet = convexHull.getPointSet();
    for(Point2D p : pointSet)
      StdDraw.point(p.x(), p.y());
  }

  //draw the convex hull
  public static void drawConvexHull(FastConvexHull convexHull) {

    ArrayList<ArrayList<Point2D>> convexHullList = convexHull.getConvexHullList();
    if (convexHull.getNumEdges() == 0)
      StdOut.println("Convex hull is currently empty");
    else {
      ArrayList<Point2D> lowerList = convexHullList.get(0);
      ArrayList<Point2D> upperList = convexHullList.get(1);
      Point2D p1;
      Point2D p2;

      for(int i = 0; i < lowerList.size() - 1; i++) {
        p1 = lowerList.get(i);
        p2 = lowerList.get(i+1);
        p1.drawTo(p2);
      }
      p1 = lowerList.get(lowerList.size() - 1);
      p2 = upperList.get(0);
      p1.drawTo(p2);
      for(int i = 0; i < upperList.size() - 1; i++) {
        p1 = upperList.get(i);
        p2 = upperList.get(i+1);
        p1.drawTo(p2);
      }
      p1 = upperList.get(upperList.size() - 1);
      p2 = lowerList.get(0);
      p1.drawTo(p2);
    }
  }

  public static void main(String[] args) {
    FastConvexHull convexHull = new FastConvexHull(StdIn.readInt());
    convexHull.populatePointSet();
    drawPointSet(convexHull);
    convexHull.findConvexHull();
    drawConvexHull(convexHull);
  }
}
