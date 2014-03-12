class Point2DWithIndex extends Point2D {

  private int index;

  public Point2DWithIndex(double x, double y, int i) {
    super(x,y);
    index = i;
  }

  public int i() { return index; }

} 
