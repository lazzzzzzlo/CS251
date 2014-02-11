public class NumberOfCalls {

  private static RandomCollection<Integer> instantiate(int n) {
    RandomCollection<Integer> randCol = new RandomCollection<Integer>();
    for(int i = 1; i <= n; i++) {
      randCol.addAnywhere(i);
    }
    return randCol;
  }

  private static int getNumberOfCallsForComprehensiveCallout(RandomCollection<Integer> test, int n) {
    int numCallout = 0;

    for(int i = 1; i <= n; i++) {
      while(i != test.callout()) {
        numCallout++;
      }
    }
    return numCallout;
  }

  public static void main(String args[]) {
    int i;
    int numCalls = 0;
    while(!StdIn.isEmpty()) {
      RandomCollection<Integer> randCol = instantiate(StdIn.readInt());
      for(i = 0; i < 100000; i++) {
        numCalls += getNumberOfCallsForComprehensiveCallout(randCol, randCol.size());
      }
      StdOut.println(numCalls/100000);
    }
  }
}
