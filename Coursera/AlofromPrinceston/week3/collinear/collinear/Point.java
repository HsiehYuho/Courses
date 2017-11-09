import java.util.*;
import edu.princeton.cs.algs4.*;

public class Point implements Comparable<Point>{
	private final int x;
	private final int y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void draw(){
		StdDraw.point(x,y);
	}
	public void drawTo(Point that){
		StdDraw.line(this.x,this.y,that.x,that.y);
	}
	public String toString(){
		return "(" + x + "," + y + ")";
	}
	@Override
	public int compareTo(Point that){
		if (this.y > that.y)
			return 1;
		else if (this.y < that.y)
			return -1;
		else if (this.y == that.y && this.x > that.x)
			return 1;
		else if (this.y == that.y && this.x < that.x)
			return -1;
		else
			return 0;
	}
	public double slopeTo(Point that){
		if ((this.y == that.y) && (this.x == that.x))
			return Double.NEGATIVE_INFINITY;
		else if (this.x == that.x)
			return Double.POSITIVE_INFINITY;
		else if (this.y == that.y)
			return +0;
		else 
			return (double)(this.y-that.y) / (this.x-that.x);
	}
	public Comparator<Point> slopeOrder(){
		return new Comparator<Point>(){ // special return which I never thought of before, a private class
			@Override
			public int compare(Point a, Point b){
				double threshold = slopeTo(a) - slopeTo(b);
				if (threshold > 0)
					return 1;
				else if (threshold < 0)
					return -1;
				else 
					return 0;
			}
		};		
	}
	public static void main(String[] args){
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] setOfPoints = new Point[n];
		for (int i = 0; i < n; i++){
			int x = in.readInt();
			int y = in.readInt();
			setOfPoints[i] = new Point(x,y);
		}
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0,32768);
		StdDraw.setYscale(0,32768);
		for (Point p: setOfPoints){
			p.draw();
		}
		StdDraw.show();		
		FastCollinearPoints collinear = new FastCollinearPoints(setOfPoints);
		for (LineSegment segment : collinear.segments()){
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}