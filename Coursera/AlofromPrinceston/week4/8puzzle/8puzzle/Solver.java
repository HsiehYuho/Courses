import java.util.*;
import edu.princeton.cs.algs4.*;

public class Solver{
	private class Node implements Comparable<Node>{
		private Board board;
		private Node previousNode;
		private int numOfMove = 0;
		public Node(Board board){
			this.board = board;
		}
		public Node(Board board, Node previousNode){
			this.board = board;
			this.previousNode = previousNode;
			this.numOfMove = previousNode.numOfMove + 1;
		}
		public int compareTo(Node thatNode){ // -1 means this node is less than that node
			return ((this.board.manhattan()+this.numOfMove) - (thatNode.board.manhattan()+thatNode.numOfMove));
		}
	}
	private Node lastNode;
	public Solver(Board initial){
		MinPQ<Node> minPQ = new MinPQ<Node>();
		MinPQ<Node> twinMinPQ = new MinPQ<Node>();
		minPQ.insert(new Node(initial));
		twinMinPQ.insert(new Node(initial.twin()));
		while(true){
			lastNode = solving(minPQ);
			if (lastNode != null || solving(twinMinPQ)!= null)
				return;
		}
	}	
	// hardest part
	private Node solving(MinPQ<Node> minPQ){
		if (minPQ.isEmpty()) return null;
		Node bestNode = minPQ.delMin();
		if (bestNode.board.isGoal()) return bestNode;
		for (Board neighbor : bestNode.board.neighbors()){
			if (bestNode.previousNode == null || !neighbor.equals(bestNode.previousNode.board)){ // optimized
				minPQ.insert(new Node(neighbor,bestNode));
			}
		}
		return null;

	}
	public boolean isSolvable(){
		return (lastNode != null) ;
	}
	public int moves(){
		if (isSolvable())
			return lastNode.numOfMove;
		else
			return -1;
	}
	public Iterable<Board> solution(){
		if (! isSolvable()) return null;
		java.util.Queue<Board> solution = new java.util.LinkedList<Board>();
		while(lastNode != null){
			solution.offer(lastNode.board);
			lastNode = lastNode.previousNode;
		}
		return solution;
	}
	public static void main(String[] args){
		In in = new In(args[0]);
    	int n = in.readInt();
    	int[][] blocks = new int[n][n];
    	for (int i = 0; i < n; i++)
        	for (int j = 0; j < n; j++)
            	blocks[i][j] = in.readInt();
    	Board initial = new Board(blocks);
    	Iterable<Board> neighborsStack = initial.neighbors(); 
    	Solver solver = new Solver(initial);
    	if (!solver.isSolvable()){
    		StdOut.println("No solution possible");
    	}
    	else{
    		StdOut.println("Minimum number of moves = " + solver.moves());
    		for (Board board : solver.solution())
    			StdOut.println(board.toString());
    	}
	}


}