import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RandomCollection<Item> {

  private Item[] a;
  private int N = 0;

  public RandomCollection() { //implemented as a resizing array, since callout is the most common operation
    a = (Item []) new Object[2];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  private void resize(int capacity) { //sample resize
    assert capacity >= N;
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < N; i++)
      temp[i] = a[i];
    a = temp;
  }


  public int size() {
    return N;
  }

  public void addAnywhere(Item item) { //adds in order
    if (N == a.length) 
      resize(2*a.length);
    a[N++] = item;   
  }

  public Item removeRandomly() { //removes randomly in O(1)
    int i = StdRandom.uniform(N);
    if (isEmpty()) 
      throw new NoSuchElementException("Stack underflow");
    Item item = a[i];
    a[i] = a[N-1]; //discard returned element
    N--;
    if (N > 0 && N == a.length/4) //resize if necessary
      resize(a.length/2);
    return item;
  }

  public Item callout() { //gets element randomly in O(1)
    int i = StdRandom.uniform(N);
    return a[i];
  }

  public String toString() { 
    StringBuilder s = new StringBuilder(); 
    for(int i = 0; i < N;i++) 
      s.append(a[i] + ","); 
    return s.toString().substring(0, s.length() - 1); //remove last comma 
  }

  public static void main(String [] args) {
  }
}
