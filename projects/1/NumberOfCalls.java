public class NumberOfCalls {

  private static RandomCollection<Integer> instantiate(int n) {
    RandomCollection<Integer> randCol = new RandomCollection<Integer>();
    for(int i = 1; i <= n; i++) {
      randCol.addAnywhere(i);
    }
    return randCol;
  }

  private static boolean elementNotCalled(int [] n, int size) {
    for(int i = 0; i < size; i++)
      if (n[i] == -1)
        return false;
    return true;
  }

  private static int getNumberOfCallsForComprehensiveCallout(RandomCollection<Integer> test, int n) {
    int [] toCall = new int[test.size()];
    for(int i = 0; i < n; i++)
      toCall[i] = -1;

    int numCallout = 0;
    while(!elementNotCalled(toCall, test.size())) {
      toCall[test.callout() - 1] = 0;
      numCallout++;
    }

    return numCallout;
  }

  public static void main(String args[]) {

    int i;
    int numCalls = 0;
    while(!StdIn.isEmpty()) {
      numCalls = 0;
      RandomCollection<Integer> randCol = instantiate(StdIn.readInt());
      for(i = 0; i < 100000; i++) {
        numCalls += getNumberOfCallsForComprehensiveCallout(randCol, randCol.size());
      }
      StdOut.println((double) numCalls/100000);
    }
  }
}
