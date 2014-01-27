

public class Stack<Item> implements Iterable<Item> {

  private Node first = null;

  private class Node {
    Item item;
    Node next;
    Node prev;
  }

  public Stack();
  public boolean isEmpty(); 
  public int size();
  public void push();
  public Item pop();
  public Item peek();
  public Iterator<Item> iterator();
  public static void main(String[] args);

}
