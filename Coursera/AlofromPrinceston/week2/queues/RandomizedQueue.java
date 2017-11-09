import java.util.*;
import edu.princeton.cs.algs4.*;

public class RandomizedQueue<Item> implements Iterable<Item>{
	private int n; // the number of itmes
	private Node first;
	
	private class Node{
		private Item item;
		private Node next;
	}
	public RandomizedQueue(){
		first = null;
		n = 0;
		assert check();
	}
	public boolean isEmpty(){
		return n == 0;
	}
	public int size(){
		return n;
	}
	public void enqueue (Item item){
		if (item == null){
			throw new NullPointerException("add null item");
		}
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		n++;
		assert check();
	}
	public Item dequeue(){
		if (n == 0){
			throw new NoSuchElementException("The randQue is empty"); 
		}
		int removeIndex = StdRandom.uniform(1,n+1);
		Node temp = first;
		if (removeIndex == 1){
			Item tempItem = temp.item;
			first = first.next;
			n--;
			assert check();
			return tempItem;
		}
		else{
			for(int i = 1; i < removeIndex-1; i++){ // move temp node to the node before eager node 
				temp = temp.next;
			}
			Item tempItem = temp.next.item;
			if(temp.next == null){
				temp = null;
			}
			else{
				temp.next = temp.next.next;			
			}	
			n--;
			assert check();
			return tempItem;
		}
		
	}
	public Item sample(){
		if (n == 0){
			throw new NoSuchElementException("The randQue is empty"); 
		}
		int sampleIndex = StdRandom.uniform(1,n+1);
		Node temp = first;
		for(int i = 1; i < sampleIndex; i++){ // move temp node to the desired node  
				temp = temp.next;
			}
		return temp.item;
	}
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		public Item next(){
			if (!hasNext()) throw new NoSuchElementException();
			Item tempItem = current.item;
			current = current.next;
			return tempItem;
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	/*public void print(){
		Node temp = first;
		StdOut.print("In RandQue: ");
		while (!(temp == null)){
			StdOut.print(temp.item);
			temp = temp.next;
		}
		StdOut.println("");
	}
	*/
	private boolean check(){
		if (n < 0)
			return false;
		if (n == 0) {
			if (first != null)
				return false;
		}
		else if (n == 1){
			if (first == null)
				return false;
			if (first.next != null)
				return false; 
		}
		return true;
	}
	public static void main (String[] args){
/*		RandomizedQueue<String> randQue = new RandomizedQueue<String>();
		randQue.enqueue("a");
		randQue.enqueue("b");
		randQue.enqueue("c");
		randQue.enqueue("d");
		randQue.enqueue("e");
		randQue.print();
		StdOut.println("Sample : " + randQue.sample());
		Iterator<String> iter = randQue.iterator();
		while (iter.hasNext()){
			StdOut.println(iter.next());
		}
		randQue.print();
		StdOut.println("Dequeue : " + randQue.dequeue());
		randQue.print();
		StdOut.println("Dequeue : " + randQue.dequeue());
		randQue.print();
		StdOut.println("Dequeue : " + randQue.dequeue());
		StdOut.println("Dequeue : " + randQue.dequeue());
//		StdOut.println("Dequeue : " + randQue.dequeue());
		randQue.print();
		randQue.enqueue("f");
		randQue.print();
		StdOut.println("Dequeue : " + randQue.dequeue());
		randQue.enqueue("g");
		randQue.print();
		StdOut.println("Dequeue : " + randQue.dequeue());
		randQue.print();*/
	}

}	