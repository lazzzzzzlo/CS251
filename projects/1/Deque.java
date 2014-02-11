import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private int N;
  private Node<Item> first;
  private Node<Item> last;

  private static class Node<Item> {
    private Item item;
    Node<Item> next;
  }

  public Deque() {
    first = null;
    last = null;
    N = 0;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public int size() {
    return N;
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    for(Item item : this)
      s.append(item + ",");
    return s.toString().substring(0, s.length() - 1);//remove last comma
  }

  public void addFirst(Item item) {
    Node<Item> oldfirst = first;
    first = new Node<Item>();
    first.item = item;
    first.next = oldfirst;
    if(first.next == null)
        last = first;
    N++;
  }

  public void addLast(Item item) {
    Node<Item> oldlast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty())
      first = last;
    else 
      oldlast.next = last;
    N++;
  }

  public Item removeFirst() { 
    if (isEmpty())
      throw new NoSuchElementException("Deque underflow");
    Item item = first.item;
    first = first.next;
    N--;
    return item; 
  }  

  public Item removeLast() {
    if (isEmpty())
      throw new NoSuchElementException("Deque underflow");
    Item item = last.item;
    last = last.next;
    N--;
    return item; 
  }

  public Iterator<Item> iterator() {

    return new DequeIterator<Item>(first);

  }

  private class DequeIterator<Item> implements Iterator<Item> {

    private Node<Item> current;

    public DequeIterator(Node<Item> first) {
      current = first;
    }

    public boolean hasNext()  { return current != null;                     }
    public void remove()      { throw new UnsupportedOperationException();  }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next; 
      return item;
    }
  }

  public static void main(String[] args) {
    Deque<String> deque = new Deque<String>();
    String command;
    String arg;
    while(!StdIn.isEmpty()) {
      command = StdIn.readString();
      if (command.equals("addFirst")) {
        deque.addFirst(StdIn.readString());
      }
      if (command.equals("addLast")) {
        deque.addLast(StdIn.readString());
      }
      if (command.equals("removeFirst")) {
        deque.removeFirst();
      }
      if (command.equals("removeLast")) {
        deque.removeLast();
      }
      if (command.equals("print")) {
        System.out.println(deque.toString());
      }
    }
  }
}

