import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordNet {
   // constructor takes the name of the two input files
   private Map<Integer,String> idToSysets;      // <Key, Value>
   private Map<String, Set<Integer>> nounsToId;
   private Digraph graph;
   private SAP sap;
   public WordNet(String synsets, String hypernyms){
      idToSysets = new HashMap<>();
      nounsToId = new HashMap<>();
      initSynsets(synsets);
      graph = initHypernyms(hypernyms);
      sap = new SAP(graph);
      DirectedCycle cycle = new DirectedCycle(graph);
      if (cycle.hasCycle() || !checkOnlyOneRoot(graph)){
         throw new IllegalArgumentException("illegal graph");
      }
   }
   private void initSynsets(String synsets){
      In file = new In(synsets);
      while (file.hasNextLine()){
         // key = id, value = syns
         String[] line = file.readLine().split("\\,");
         int id = Integer.valueOf(line[0]);
         String syns = line[1];
         idToSysets.put(id,syns); // id is unique 
         // key = noun, value = id 
         String[] nouns = syns.split(" ");
         for (String noun : nouns){
            Set<Integer> idsOfNoun = nounsToId.get(noun);
            if (null == idsOfNoun){
               idsOfNoun = new HashSet<>();
            }
            idsOfNoun.add(id);
            nounsToId.put(noun,idsOfNoun);
         } 
      }
      /*
      for (int e : idToSysets.keySet()){
         StdOut.println(e + " "+idToSysets.get(e));
      } */       
      /*
      for ( String e : nounsToId.keySet()){
         StdOut.println(e + " "+nounsToId.get(e));
      }*/
   }
   // check the number of root of DAG is only ONE
   private boolean checkOnlyOneRoot(Digraph graph){
      int rootNumber = 0;
      for (int i = 0; i < graph.V(); i++){
         if (!graph.adj(i).iterator().hasNext()){     // root has no adj           
            rootNumber++;                             // cannot use adj(i).isEmpty()
            if (rootNumber > 1)                       // the graph.adj implement Bag on <int>
               return false;                          // implement Bag cannot be used as isEmpty()
         }
      }
      return rootNumber == 1;
   }

   private Digraph initHypernyms(String hypernyms){
      Digraph graph = new Digraph(idToSysets.size()); // constructor (Vertice = size)
      In file = new In (hypernyms);
      while (file.hasNextLine()){
         String[] vertice = (file.readLine().split("\\,"));
         for (int i = 1; i < vertice.length; i++){
            graph.addEdge(Integer.valueOf(vertice[0]),Integer.valueOf(vertice[i])); // create the graph
         }
      }
      return graph;
   }

   // returns all WordNet nouns
   public Iterable<String> nouns(){
      return nounsToId.keySet();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word){
      return nounsToId.containsKey(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB){
      if (!isNoun(nounA) || ! isNoun(nounB))
         throw new IllegalArgumentException("Both words must be nouns!");
      int mindist = Integer.MAX_VALUE;
      Set<Integer> synsetIdsOfA = nounsToId.get(nounA);
      Set<Integer> synsetIdsOfB = nounsToId.get(nounB);
      return sap.length(synsetIdsOfA,synsetIdsOfB);
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB){
      if (!isNoun(nounA) || ! isNoun(nounB))
         throw new IllegalArgumentException("Both words must be nouns!");
      Set<Integer> synsetIdsOfA = nounsToId.get(nounA);
      Set<Integer> synsetIdsOfB = nounsToId.get(nounB);
      int ancestorID = sap.ancestor(synsetIdsOfA,synsetIdsOfB);
      return idToSysets.get(ancestorID);
   }
   // do unit testing of this class
   public static void main(String[] args){
      WordNet wordNet = new WordNet(args[0], args[1]);
      while (!StdIn.isEmpty()) {
            String v = StdIn.readString();
            String w = StdIn.readString();
            if (!wordNet.isNoun(v)) {
                StdOut.println(v + " not in the word net");
                continue;
            }
            if (!wordNet.isNoun(w)) {
                StdOut.println(w + " not in the word net");
                continue;
            }
            int distance = wordNet.distance(v, w);
            String ancestor = wordNet.sap(v, w);
            StdOut.printf("distance = %d, ancestor = %s\n", distance, ancestor);
      }
   }
}