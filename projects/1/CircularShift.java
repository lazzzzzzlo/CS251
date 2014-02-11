public class CircularShift {

  public static void main(String args[]) {

    int mode = StdIn.readInt();

    if (mode == 1) { //if user only wants to shift
      Deque<Character> deque = new Deque<Character>();
      int shift = StdIn.readInt();
      while(!StdIn.isEmpty()) {
        StdIn.readChar();
        deque.addLast(StdIn.readChar());
      }
      StdOut.println(shiftDeque(shift, deque).replaceAll(","," "));
    }
    else if (mode == 2) { //compare Deques
      Deque<Character> deque1 = new Deque<Character>();
      Deque<Character> deque2 = new Deque<Character>();
      char c;

      //populate both deques start
      while(!StdIn.isEmpty()) {
        StdIn.readChar();
        c = StdIn.readChar();
        if (c == '-') {
          break;
        }
        deque1.addLast(c);
      }

      while(!StdIn.isEmpty()) {
        StdIn.readChar();
        deque2.addLast(StdIn.readChar());
      }
      //end

      StdOut.println(dequesAreEquivalent(deque1, deque2));
   }
    else {
      StdOut.println("Invalid test");
    }
  }

  public static String shiftDeque(int shift, Deque<Character> deque) { 

    for(int i = 0; i < shift; i++) {
      char c = deque.removeFirst();
      deque.addLast(c);
    }

    return deque.toString();
  }

  public static String dequesAreEquivalent(Deque<Character> deque1, Deque<Character> deque2) {
    if (deque1.size() != deque2.size())
        return "No";
    for(int i = 0; i <= deque1.size(); i++) { //shifts until it matches
      if (deque1.toString().equals(shiftDeque(i, deque2)))
        return "Yes";
    }
    return "No";
  }

}
