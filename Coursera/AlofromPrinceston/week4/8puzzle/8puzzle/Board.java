import java.util.*;
import edu.princeton.cs.algs4.*;

public class Board{
	private int[][] block;
	private int blockSize; 	
	private int[][] desireBlock;
	private Board perviousBlock;
	private static void printBlock(int[][] block){
		for (int i = 0; i < block.length; i++){
			for (int j = 0; j < block.length; j++){
				StdOut.print(block[i][j] + " ");
			}
			StdOut.println("");
		}
		StdOut.println("");
	}
	private int[][] copyBlock(int[][] blocks){
		int[][] copy = new int[blocks.length][blocks.length];
		for (int i = 0; i < block.length; i++)
			for (int j = 0; j < block.length; j++)
				copy[i][j] = blocks[i][j];

		return copy;
	}
	private int[][] swap(int r1,int c1, int r2,int c2){
		int[][] copy = copyBlock(this.block); // "private" in class can be called directly without instance
		int temp = copy[r1][c1];
		copy[r1][c1] = copy[r2][c2];
		copy[r2][c2] = temp;
		return copy;
	} 
	public Board(int[][] blocks){
		this.block = blocks;
		if (block.length != block[0].length)
			throw new IllegalArgumentException("the block is not N by N array");
		this.blockSize = block.length;
		this.desireBlock = new int[blockSize][blockSize];
		for(int i = 0, index = 0; i < blockSize; i++){
			for (int j = 0; j < blockSize; j++){
				desireBlock[i][j] = ++index; 		
			}
		}
		desireBlock[blockSize-1][blockSize-1] = 0;
//		printBlock(block);
//		StdOut.println("");
	}
	public int dimension(){
		return blockSize; // the length of row = block.length, the length of col = block[0].length
	}
	public int hamming(){
		int hammingNum = 0;
		for (int i = 0; i < blockSize; i++){
			for (int j = 0; j< blockSize; j++){
				if ((block[i][j] != 0 && desireBlock[i][j]!= 0) && block[i][j] != desireBlock[i][j])
					hammingNum++;
			}
		}
		if (block[blockSize-1][blockSize-1] == 0) 
			return hammingNum; // the last item of block is 0
		else 
			return (hammingNum+1);
	}
	public int manhattan(){
		int manhattanNum = 0;
		for (int i = 0; i < blockSize; i++){
			for (int j = 0; j < blockSize; j++){
				int correctIndex = i * blockSize + j + 1;
				if (block[i][j] != correctIndex){
					int temp = block[i][j];
					int xCoordinate = (temp-1) / blockSize;
					int yCoordinate = ((temp-1) % blockSize);
					if (temp == 0)
						continue;
					else{
						manhattanNum = manhattanNum + Math.abs(xCoordinate - i) + Math.abs(yCoordinate - j);		
					}
				}
			}
		}
		return manhattanNum;
	}
	public boolean isGoal(){
		boolean isGoal = true;
		for (int i = 0; i < blockSize; i++){
			for (int j = 0; j < blockSize; j++){
				if (block[i][j]!= desireBlock[i][j]){
					isGoal = false;
					break;
				}	

			}
		}
		return isGoal;
	}
	public Board twin(){
		int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
		int num = 0;
		for (int i = 0; i < blockSize; i++){
			for (int j = 0; j < blockSize; j++){
				if (num >= 2)
					break;
				if (block[i][j] != 0){
					if (x1 < 0){
						x1 = i; 
						y1 = j;
						num++;
					}
					else{
						x2 = i;
						y2 = j;
						num++;
					}
				}
			}
		}
		Board twinBlock = new Board(swap(x1,y1,x2,y2));
		return twinBlock;
	}
	public boolean equals(Object y){
		if (y == this) return true;
		if (y == null ) return false;
		if (y.getClass() != this.getClass()) return false;
		Board that = (Board) y;
		return Arrays.deepEquals(this.block,that.block);
	}
	public Iterable<Board> neighbors(){
		java.util.Stack<Board> neighborsStack = new java.util.Stack<Board>();
		int blankLocationX = -1;
		int blankLocationY = -1;
		for (int i = 0; i < blockSize; i++){
			for (int j = 0; j < blockSize; j++){
				if (block[i][j] == 0){
					blankLocationX = i;
					blankLocationY = j;
				}
			}
		}
		if (blankLocationX < 0 || blankLocationY < 0)
			throw new IllegalArgumentException("blankLocation < 0");
		if (blankLocationX > 0){
			int[][] neighbor = swap(blankLocationX,blankLocationY,blankLocationX-1,blankLocationY); 
			neighborsStack.push(new Board(neighbor));
		}
		if (blankLocationX < blockSize-1){
			int[][] neighbor = swap(blankLocationX,blankLocationY,blankLocationX+1,blankLocationY); 
			neighborsStack.push(new Board(neighbor));
		}
		if (blankLocationY > 0){
			int[][] neighbor = swap(blankLocationX,blankLocationY,blankLocationX,blankLocationY-1); 
			neighborsStack.push(new Board(neighbor));
		}	
		if (blankLocationY < blockSize-1){
			int[][] neighbor = swap(blankLocationX,blankLocationY,blankLocationX,blankLocationY+1); 
			neighborsStack.push(new Board(neighbor));
		}	
		return neighborsStack;

	}
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(blockSize + "\n");
		for (int i = 0; i < blockSize; i++){
			for (int j = 0; j< blockSize; j++){
				stringBuilder.append(String.format("%3d",block[i][j]));
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	public static void main(String[] args){
		/*
		In in = new In(args[0]);
    	int n = in.readInt();
    	int[][] blocks = new int[n][n];
    	for (int i = 0; i < n; i++)
        	for (int j = 0; j < n; j++)
            	blocks[i][j] = in.readInt();
    	Board initial = new Board(blocks);
		Iterable<Board> neighborsStack = initial.neighbors(); 
		for(Board e : neighborsStack)
			StdOut.print(e.toString()); 
		StdOut.print(initial.toString());
		Board twinBlock = initial.twin();
		StdOut.print(twinBlock.toString()); */
	}
}