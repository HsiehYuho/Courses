/* 
public Percolation(int n)  				//create n-by-n grid, with all sites blocked
public void open(int row, int col)      // open site (row, col) if it is not open already
public boolean isOpen(int row, int col)  // is site (row, col) open?
public boolean isFull(int row, int col)  // is site (row, col) full?
public boolean percolates()              // does the system percolate?
public static void main(String[] args)   // test client (optional)
*/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Scanner;

public class Percolation {
	private int gridLength; // gridLength^2 = total site in the grid
	private boolean[] isOpen; // isOpen is a boolean array keep track of the state of site 
	private WeightedQuickUnionUF percolation; // percolation represent the connectivity of the sites.
	private WeightedQuickUnionUF fullness; // i am not sure why heyalexchoi create this variate 
	private int virtualTopIndex; // initialize to 0, which used as the root of top layer
	private int virtualBottomIndex; // initialzie to N^2+1, which used as the root bottom layer
	// making sure the input i and j are within bounds
	private void checkBounds(int i, int j){
		if(i < 1 || i > gridLength){
			throw new IndexOutOfBoundsException("row index out of bounds");
		}
		if(j < 1 || j > gridLength){
			throw new IndexOutOfBoundsException("column index out of bounds");
		}
	}
	// convert 2D (x,y) into a 1D array index
	private  int convert2Dto1D(int i, int j){ // i = row, j = column
		checkBounds(i,j);
		return (i-1)*gridLength+j;
	}
	public Percolation(int n){
		gridLength = n;
		if (gridLength < 1){
			throw new IllegalArgumentException();
		}
		int arraySize = gridLength*gridLength+2; // the grid plus 2 virtual index []
		isOpen = new boolean[arraySize]; // keep track of the open status of the array, default is false
		percolation = new WeightedQuickUnionUF(arraySize); // set a data structure as an array to represent grid
		fullness = new WeightedQuickUnionUF(arraySize); // "full" means connected to the top 
		virtualTopIndex = 0; // initialized as zero
		virtualBottomIndex = gridLength*gridLength + 1; // initialized the point to the last position of the array 
		for(int i =1; i < gridLength+1; i++){ // connect top and bottom grids to virtual ones
			int topIndex = convert2Dto1D(1,i);
			int bottomIndex = convert2Dto1D(gridLength,i);
			percolation.union(virtualTopIndex,topIndex); 
			fullness.union(virtualTopIndex,topIndex);
			percolation.union(virtualBottomIndex,bottomIndex);
		}
		isOpen[virtualBottomIndex] = true;
		/*isOpen[virtualTopIndex] = true;	// test the status of isOpen array
		for (int i = 0; i < arraySize;i++){
			System.out.println(isOpen[i]);
		}*/
	}
	
	public void open(int row, int col){
		checkBounds(row,col);
		int gridIndex = convert2Dto1D(row,col);
		if (!(isOpen[gridIndex])){ // check the status of the input site
 			isOpen[gridIndex] = true;
 			if (row != 1 && isOpen(row-1,col)){ // if  not in the first row 
 				int upIndex = convert2Dto1D(row-1,col);
 				percolation.union(gridIndex,upIndex);
 				fullness.union(gridIndex,upIndex);
 				//System.out.println("merge with upper site");
 			}
 			if (row != gridLength && isOpen(row+1,col)){ // if not in the last row
				int downIndex = convert2Dto1D(row+1,col);
				percolation.union(gridIndex,downIndex);
				fullness.union(gridIndex,downIndex);
				//System.out.println("merge with lower site");		
			}
 			if (((col-1)%gridLength != 0) && isOpen(row,col-1)){ // if not in the left-most col
				int leftIndex = convert2Dto1D(row,col-1);
				percolation.union(gridIndex,leftIndex);
				fullness.union(gridIndex,leftIndex);
				//System.out.println("merge with left site");
			}
 			if ((col%gridLength != 0) && isOpen(row,col+1)){ // if not in the right-most col
				int rightIndex = convert2Dto1D(row,col+1);
				percolation.union(gridIndex,rightIndex);
				fullness.union(gridIndex,rightIndex);
				//System.out.println("merge with right site");						
			} 
		}
	}
	public boolean isOpen(int row, int col){
		checkBounds(row,col);
		int gridIndex = convert2Dto1D(row,col);
		return isOpen[gridIndex];
	}
	public boolean isFull(int row, int col){
		checkBounds(row,col);
		int gridIndex = convert2Dto1D(row,col);
		if(isOpen(row,col)){
			return fullness.connected(virtualTopIndex,gridIndex);	
		}
		else
			return false;
	}  
	public boolean percolates(){
		if(gridLength>1){
			return percolation.connected(virtualTopIndex,virtualBottomIndex);
		}
		else{
			return isOpen(1,1);
		}
	}		
	/*
	public static int[] test(int t,int n){


	} */            
	public static void main(String[] args){
		/*Scanner scanner = new Scanner(System.in);
    	System.out.println("input  n-by-n gridLength and T times computational experiments: ");
    	System.out.print("n , T = ");
    	int gridN = Integer.parseInt(scanner.next());
    	int computationalTimes = Integer.parseInt(scanner.next());
    	double[] reportArray = new double[computationalTimes];
    	for (int i = 0; i < computationalTimes; i++){
    		Percolation grid = new Percolation(gridN);
    		int count = 0;
    		while(!grid.percolates()){
    			int randomOpenXIndex = StdRandom.uniform(1,gridN+1);
				int randomOpenYIndex = StdRandom.uniform(1,gridN+1);
				if (!grid.isOpen(randomOpenXIndex,randomOpenYIndex)){
					count++;
				}
				grid.open(randomOpenXIndex,randomOpenYIndex);	
    		}
    		reportArray[i] = ((double) count / (gridN*gridN));
    	}
		double mean = StdStats.mean(reportArray);
		double stddev = StdStats.stddev(reportArray);
		double confidenceUp = (mean + 1.96*stddev / (Math.sqrt(computationalTimes)));
		double confidenceLow = (mean - 1.96*stddev / (Math.sqrt(computationalTimes)));
		System.out.printf("mean = %f \n",mean);
		System.out.printf("stddev = %f \n",stddev);
		System.out.printf("95%% confidence interval = %f , %f \n",confidenceLow, confidenceUp);
		*/
	} 	
}
