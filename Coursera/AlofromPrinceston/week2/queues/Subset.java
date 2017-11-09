import java.util.*;
import edu.princeton.cs.algs4.*;

public class Subset{
	public static void main(String[] args){
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		int outPutNumber = Integer.valueOf(args[0]);
		while (!StdIn.isEmpty()){
			String item = StdIn.readString();
			q.enqueue(item);
		}
		while (outPutNumber>0){
			StdOut.println(q.dequeue());
			outPutNumber --;
		}

	}
}