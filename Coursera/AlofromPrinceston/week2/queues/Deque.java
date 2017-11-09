import java.util.*;
import edu.princeton.cs.algs4.*;

public class Deque<Item> implements Iterable<Item>{
	private int n; // Size of the deque
	private Node first, last; // the first pointer of deque and the last pointer of the deque
	
	private class Node{
		private Item item;
		private Node next, previous; 
	}

	// initialize a empty deque
	public Deque(){
		first = null;
		last = null;
		n = 0;
		assert check();
	}
	public boolean isEmpty(){
		return n == 0;
	}
	public int size(){
		return n;
	}
	public void addFirst(Item item){
		if (item == null){
			throw new NullPointerException("add null item");
		}
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		if (n != 0){
			first.next.previous = first;
		}
		if (n == 0){
			last = first;
			first.next = null;
			last.previous = null;
		}
		n++;
		assert check();
	}
	public void addLast(Item item){
		if (item == null){
			throw new NullPointerException("add null item");
		}
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.previous = oldLast;
		if (n!= 0){
			last.previous.next = last;
		}
		if (n == 0){
			first = last;
			first.next = null;
			last.previous = null;
		}
		n++;
		assert check();
	}
	public Item removeFirst(){
		if (n == 0){
			throw new NoSuchElementException("The deque is empty");
		}
		Item tempItem = first.item;
		Node temp = first.next;
		first = temp;
		n--;
		if (n!= 0){
			first.previous = null;
		}
		assert check();
		return tempItem;
	}
	public Item removeLast(){
		if (n == 0){
			throw new NoSuchElementException("The deque is empty");
		}
		Item tempItem = last.item;
		Node temp = last.previous;
		last = temp;
		n--;
		if(n!= 0){
			last.next = null;
		}
		assert check();
		return tempItem;
	}
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null; 
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
		public Item next(){
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	// check initial status
	private boolean check(){
		if (n < 0)
			return false;
		if (n == 0) {
			if (first != null || last != null)
				return false;
		}
		else if (n == 1){
			if (first == null || last == null)
				return false;
			if (first.previous != null || last.next != null)
				return false;
			if (first.next != null || last.previous != null)
				return false; 
		}
		return true;
	}

	/*public void print(){
		Node tempNode = first;
		StdOut.print("In Deque: ");
		while(tempNode != null){
			StdOut.print(tempNode.item);
			tempNode = tempNode.next;
		}
		StdOut.println();
	}*/

	// Unit tests 
	public static void main(String[] args){
		/*Deque<String> deque = new Deque<String>();
		boolean stop = false;
		// read command as an 5*2 array, 
		// the first one is the command, the second one is item
		int numberOfCommand = 15; // number of input txt command
		int commandItem = 2;
		String[][] items = new String[numberOfCommand][2];
		for (int i = 0; i < numberOfCommand; i++){
			for (int j = 0; j < 2; j++){
				items[i][j] = StdIn.readString();
			}
		}		
		for (int i = 0; i < numberOfCommand; i++){
			if (items[i][0].equals("q")) break;
			if (items[i][0].equals("af")){
				deque.addFirst(items[i][1]);
			}
			if (items[i][0].equals("al")){
				deque.addLast(items[i][1]);
			}
			if (items[i][0].equals("rf")){
				StdOut.println("Remove from first: "+ deque.removeFirst
			());
			}
			if (items[i][0].equals("rl")){
				StdOut.println("Remove from last: "+ deque.removeLast());
			}
			if (items[i][0].equals("print")){
				deque.print();
			}
		}
		StdOut.println("Size of Deque: " + deque.size());
		StdOut.println("Deque is empty?: " + deque.isEmpty());
		Iterator<String> iter = deque.iterator();
		while (iter.hasNext()){
			StdOut.println(iter.next());
		}*/
	}
	
}