import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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

  public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {

    PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
    int numCalls = 0;
    for(int i = 1; i <= 200; i++) {
      numCalls = 0;
      RandomCollection<Integer> randCol = instantiate(i);
      for(int j = 0; j < 100000; j++) {
        numCalls += getNumberOfCallsForComprehensiveCallout(randCol, randCol.size());
      }
      writer.println("" + i + "," +(double) numCalls/100000);
    }
    writer.close();
  }
}
