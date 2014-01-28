import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

	private Node first = null;
	int size = 0;
	boolean isEmpty = true;

	private class Node {
		Item item;
		Node next;
		Node prev;
	}

	public Stack() { return new Stack() };
	public boolean isEmpty() { return size > 0 ? false : true; }
	public int size() { return size; }
	public void push();
	public Item pop() { 
		if (this.isEmpty()) {
			throw new RuntimeException;
		}
	}
	public Item peek() {
		if (this.isEmpty()) {
			throw new RuntimeException;
		}
	}
	public Iterator<Item> iterator() { return new ListIterator(); }

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() { return current != null; }
		public void remove() { /* not supported */ }
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	public static void main(String[] args);

}
