import java.util.*;
import edu.princeton.cs.algs4.*;

public class Outcast {
	 // constructor takes a WordNet object
	private WordNet wordNetWork;
	private int[] distArray;
	public Outcast(WordNet wordnet){
		wordNetWork = wordnet;
	}        
	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns){
		String outcastString = nouns[0];
		int maxDist = -1;
		distArray = new int[nouns.length];
		for (int i : distArray)
			distArray[i] = 0;
		for (int i = 0; i < nouns.length; i++){
			for (int j = 0; j < nouns.length; j++){
				if (!nouns[i].equals(nouns[j]))
					distArray[i] += wordNetWork.distance(nouns[i],nouns[j]);
			}
		}
		for (int i = 0; i < distArray.length; i ++){
			if (distArray[i] > maxDist)
				outcastString = nouns[i];
				maxDist = distArray[i];
		}
		return outcastString;
	}
	// see test client below   
	public static void main(String[] args){
		WordNet wordnet = new WordNet(args[0], args[1]);
    	Outcast outcast = new Outcast(wordnet);
    	for (int t = 2; t < args.length; t++) {
        	In in = new In(args[t]);
        	String[] nouns = in.readAllStrings();
        	StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    	}
	}  
}	