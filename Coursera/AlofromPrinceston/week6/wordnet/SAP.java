import java.util.*;
import edu.princeton.cs.algs4.*;

public class SAP {
   private Map<String,Node> ancestorRecord; // String = v_w Node = a record of finding ancestor 
   private Digraph graph;
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph g){
      graph = new Digraph (g);
      ancestorRecord = new HashMap<>();

   }
   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w){
      if (!validIndex(v) || !validIndex(w)){
         throw new ArrayIndexOutOfBoundsException();
      }
      Node result = compareStringToFindNode(v,w);
      return result.distance;
   }
   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w){
      if (!validIndex(v) || !validIndex(w))
         throw new ArrayIndexOutOfBoundsException();
      Node result = compareStringToFindNode(v,w);
      return result.ancestor;
   }
   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w){
      if (!validIndex(v) || !validIndex(w))
         throw new ArrayIndexOutOfBoundsException();
      Node result = compareStringToFindNode(v,w);
      return result.distance;
   }
   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
      if (!validIndex(v) || !validIndex(w))  
         throw new ArrayIndexOutOfBoundsException();
      Node result = compareStringToFindNode(v,w);
      return result.ancestor;
   }
   // do unit testing of this class
   public static void main(String[] args){
      In in = new In(args[0]);
      Digraph graph = new Digraph(in);
      SAP sap = new SAP(graph);
      while (!StdIn.isEmpty()) {
         int v = StdIn.readInt();
         int w = StdIn.readInt();
         int length = sap.length(v, w);
         int ancestor = sap.ancestor(v, w);
         StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
      }
   }
   private boolean validIndex(int v){
      if (v < 0 || v > graph.V()){
         return false;
      }
      return true;
   }
   private boolean validIndex(Iterable<Integer> v){
      for (int vertex : v){
         if (!validIndex(vertex))
            return false;
      }
      return true;
   }
   private Node compareStringToFindNode(int v, int w){
      String key = v + "_" + w;
      if (ancestorRecord.containsKey(key)){
         return ancestorRecord.get(key);
      }
      else{
         Node hashNode = new Node(v,w);
         ancestorRecord.put(key,hashNode);
         return hashNode;
      }
   }
   private Node compareStringToFindNode(Iterable<Integer> v, Iterable<Integer> w){
      String key = v.toString() + "_" + w.toString();
      if (ancestorRecord.containsKey(key)){
         return ancestorRecord.get(key);
      }
      else{
         Node hashNode = new Node(v,w);
         ancestorRecord.put(key,hashNode);
         return hashNode;
      }
   }

   // Node = the record of ancestor and distance 
   private class Node{
      private int ancestor; // the common ancestor of pointA and pointB
      private int distance; 
      public Node(int v, int w){
         // the dis and edge to roots by bfs
         BreadthFirstDirectedPaths pointA = new BreadthFirstDirectedPaths(graph,v); 
         BreadthFirstDirectedPaths pointB = new BreadthFirstDirectedPaths(graph,w);
         processNode(pointA,pointB);
      }
      public Node(Iterable<Integer> v, Iterable<Integer> w){
         BreadthFirstDirectedPaths pointA = new BreadthFirstDirectedPaths(graph,v); 
         BreadthFirstDirectedPaths pointB = new BreadthFirstDirectedPaths(graph,w);
         processNode(pointA,pointB);
      }

      public void processNode(BreadthFirstDirectedPaths pointA, BreadthFirstDirectedPaths pointB){
         distance = Integer.MAX_VALUE;
         ancestor = -1;
         List<Integer> ancestors = new ArrayList<>();
         for (int i = 0; i < graph.V(); i++){
            if (pointA.hasPathTo(i) && pointB.hasPathTo(i))
               ancestors.add(i);
         }
         for (int candidateAncestor : ancestors){
            int minDist = pointA.distTo(candidateAncestor) + pointB.distTo(candidateAncestor);
            if (minDist < distance){
               distance = minDist;
               ancestor = candidateAncestor;
            }
         }
         if (distance == Integer.MAX_VALUE)
            distance = -1;
      }
   }

}

