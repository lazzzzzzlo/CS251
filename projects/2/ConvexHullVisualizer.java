import java.awt.Color;
import java.util.Arrays;
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

  public static void drawConvexHull(FastConvexHull convexHull) {
    convexHull.initializeHullArray(convexHull.getConvexHullList());
    Point2DWithIndex [] hullArray = convexHull.getHullArray();
    int i;
    int iMin = convexHull.getMinIndex();
    Point2DWithIndex p1;
    Point2DWithIndex p2;
    for(i = 0; i < hullArray.length - 1; i++) {   
      p1 = hullArray[(i + iMin) % hullArray.length];
      p2 = hullArray[(i + 1 + iMin) % hullArray.length];
      p1.drawTo(p2);
    }
    p1 = hullArray[(i + iMin) % hullArray.length];
    p2 = hullArray[iMin % hullArray.length];
    p1.drawTo(p2);
  }
  public static void main(String[] args) {
    FastConvexHull convexHull = new FastConvexHull(StdIn.readInt());
    convexHull.populatePointSet();
    drawPointSet(convexHull);
    convexHull.findConvexHull();
    drawConvexHull(convexHull);
    StdDraw.save("500p.png");
  }
}
