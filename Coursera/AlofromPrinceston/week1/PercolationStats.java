import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.Scanner;
public class PercolationStats{
	private double[] threshold;
	private int count;
	private int trials;
	public PercolationStats(int n, int trials){
		if (n <= 0 || trials <= 0 ){
			throw new IllegalArgumentException("both n and trials has to bigger than zero");
		}
		int gridN = n;
    	int computationalTimes = trials;
    	this.trials = trials;
    	threshold = new double[computationalTimes];
    	count = 0;
    	for (int i = 0; i < computationalTimes; i++){
    		Percolation grid = new Percolation(gridN);
    		int count = 0;
    		while(!grid.percolates()){
    			int randomOpenXIndex = StdRandom.uniform(1,gridN+1);
				int randomOpenYIndex = StdRandom.uniform(1,gridN+1);
				if (!grid.isOpen(randomOpenXIndex,randomOpenYIndex)){
					grid.open(randomOpenXIndex,randomOpenYIndex);	
					count++;
				}
				
    		}
    		threshold[i] = ((double) count / (gridN*gridN));
    	}
	}
	public double mean(){
		return StdStats.mean(threshold);
	}
	public double stddev(){
		return StdStats.stddev(threshold);
	}
	public double confidenceLo(){
		return (this.mean() - 1.96*this.stddev() / (Math.sqrt(trials)));
	}
	public double confidenceHi(){
		return (this.mean() + 1.96*this.stddev() / (Math.sqrt(trials)));
	}
	public static void main(String[] args){
		//Scanner scanner = new Scanner(System.in);
		//System.out.print("n , T = ");
    	//int gridN = Integer.parseInt(scanner.next());
    	//int computationalTimes = Integer.parseInt(scanner.next());
		int gridN = Integer.parseInt(args[0]);
		int computationalTimes = Integer.parseInt(args[1]);
		PercolationStats states = new PercolationStats(gridN,computationalTimes);
		System.out.printf("mean = %f \n",states.mean());
		System.out.printf("stddev = %f \n",states.stddev());
		System.out.printf("95%% confidence interval = %f , %f \n",states.confidenceLo(), states.confidenceHi());
	}
	
}