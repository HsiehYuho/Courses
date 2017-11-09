import java.util.*;
import edu.princeton.cs.algs4.*;
//use arraylist to count the lineSegment
public class BruteCollinearPoints{
	private Point[] setOfPoints;
	private LineSegment[] setOfLines;
	public BruteCollinearPoints(Point[] points){
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
		for (int i = 0; i < setOfPoints.length-3; i++){
			for (int j = i+1; j < setOfPoints.length-2; j++ ){
				for (int m = j+1; m < setOfPoints.length-1; m++){
					for (int n = m+1; n < setOfPoints.length; n++){
						if (setOfPoints[i].slopeTo(setOfPoints[j]) == setOfPoints[i].slopeTo(setOfPoints[m])){
							if (setOfPoints[i].slopeTo(setOfPoints[j]) == setOfPoints[i].slopeTo(setOfPoints[n])){
								lineSegmentArrayList.add(new LineSegment(setOfPoints[i],setOfPoints[n]));
							}
						} 
							
					}
				}
			}
		}
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