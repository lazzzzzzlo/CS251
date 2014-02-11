import java.util.Scanner;

public class Palindrome {

  public static void main(String []args) {

    Scanner sc = new Scanner(System.in);
    int numStrings = sc.nextInt();
    String []strings = new String[numStrings];
    int i;

    for (i = 0; i < numStrings; i++) {
      strings[i] = sc.nextLine();
    }

    for(i = 0; i < numStrings; i++) {
      System.out.println(isPalindrome(strings[i]));
    }

  }

  public static String isPalindrome(String string) {

    Stack<Character> stack = new Stack<Character>();
    int stringLength = string.length();
    int i, match = 0;

    for(i = 0; i < stringLength; i++) {
      stack.push(string.charAt(i));
    }

    for(i = 0; i < stringLength; i++) {
      if (stack.pop().equals(string.charAt(i)))
        match++;
    }

    if (match == stringLength)
      return "Yes";
    else
      return  "No";
  }
}
