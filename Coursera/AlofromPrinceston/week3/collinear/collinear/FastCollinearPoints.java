import java.util.*;
import edu.princeton.cs.algs4.*;

public class FastCollinearPoints{
	private LineSegment[] setOfLines;
	private Point[] setOfPoints;
	private double[] setOfSlopes;
	/*private class SlopeNode{
		private double slope;
		private int index;
		private SlopeNode(double slope, int index){
			this.slope = slope;
			this.index = index;
		}
	}*/
	public FastCollinearPoints(Point[] points){
		if (points == null)
			throw new NullPointerException("null points");
		for (Point unit : points){
			if (unit == null) 
				throw new IllegalArgumentException("one point is null");
		}
		this.setOfPoints = points;
		Arrays.sort(setOfPoints);
		checkDuplicate(setOfPoints);
	}
	public int numberOfSegments(){
		return setOfLines.length;
	}
	public LineSegment[] segments(){
		ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<LineSegment>();
		for (int i = 0; i < setOfPoints.length-3;i++){
			// sort by points at first
			Arrays.sort(setOfPoints);
			// sort the set of points by slope order of i, the index 0  = slope negative infinity
			Arrays.sort(setOfPoints,setOfPoints[i].slopeOrder()); 	
			// compare to the p and i, and p and i+1, +2, +3...  i+3 means there are 3 more points
			for (int p = 0, first = 1, last = 2; last < setOfPoints.length; last++){
				// find the last point collinear to p point
				while (last < setOfPoints.length && 
					Double.compare(setOfPoints[p].slopeTo(setOfPoints[first]),
					setOfPoints[p].slopeTo(setOfPoints[last])) == 0){
					last ++;
				}
				//  make sure segement is unique!!!!, make sure not backward!!! 
				if (last - first >= 3 && setOfPoints[p].compareTo(setOfPoints[first]) < 0){
					lineSegmentArrayList.add(new LineSegment(setOfPoints[p],setOfPoints[last-1]));
				} 
				first = last;
			}
		}
			

	/*	ArrayList<Point> pointsArrayList = new ArrayList<Point>();
		ArrayList<LineSegment> lineSegmentArrayList = new ArrayList<LineSegment>();
		for (int i =0; i < setOfPoints.length-1; i++){
			for (int j = i+1; j < setOfPoints.length; j++){
					pointsArrayList.add(setOfPoints[i].slopeTo(setOfPoints[j]));
			}
			Collections.sort(pointsArrayList);
			int m = 0;
			while(m < pointsArrayList.size()){
				if (pointsArrayList.get(m).slopeTo())
			}
			if (pointsArrayList.size() > 3){
				lineSegmentArrayList.add(
					new LineSegment(setOfPoints[i],pointsArrayList.get(pointsArrayList.size()-1)));
			}
		} */
		setOfLines = new LineSegment[lineSegmentArrayList.size()];
		lineSegmentArrayList.toArray(setOfLines); 
		return setOfLines;

	}
	private boolean checkDuplicate(Point[] setOfPoints){
		for (int i = 0; i<setOfPoints.length-1; i++){
			for (int j = i+1; j < setOfPoints.length; j++){
				if (setOfPoints[i].compareTo(setOfPoints[j]) == 0)
					throw new IllegalArgumentException("Duplicated entries in given points");
			}
		}
		return true;
	}
}