import java.util.*;
import edu.princeton.cs.algs4.*;

public class PointSET{
	private SET<Point2D> pointSET; // the point in the plane
	public PointSET(){	
		pointSET = new SET<Point2D>();
	}
	public boolean isEmpty(){
		return pointSET.isEmpty();
	}
	public int size(){
		return pointSET.size();
	}
	public void insert(Point2D p){
		pointSET.add(p);
	}
	public boolean contains(Point2D p){
		return pointSET.contains(p);
	}
	public void draw(){
		for (Point2D p : pointSET) {
			StdDraw.point(p.x(), p.y());
		}
	}
	public Iterable<Point2D> range(RectHV rect){
		SET<Point2D> rangeSet = new SET<Point2D>();
		for (Point2D p: pointSET){
			if (rect.contains(p))
				rangeSet.add(p);
		}
		return rangeSet;
	}
	public Point2D nearest(Point2D p){
		double distance = Double.POSITIVE_INFINITY;
		Point2D nearestPoint = null;
		if (isEmpty()){
			return null;
		}
		for (Point2D q:pointSET){
			if (distance > p.distanceTo(q)){
				nearestPoint = q;
				distance = p.distanceTo(q);
			}
		}
		return nearestPoint;

	}
	public static void main(String[] args) {
/*		PointSET newPointSET = new PointSET();
		Point2D point1 = new Point2D(0,0);
		Point2D point2 = new Point2D(1,0);
		Point2D point3 = new Point2D(0,1);
		Point2D point4 = new Point2D(1,1);
		Point2D point5 = new Point2D(0.5,0.5);
		newPointSET.insert(point1);
		StdOut.println(newPointSET.contains(point1));
		newPointSET.insert(point2);
		newPointSET.insert(point3);
		newPointSET.insert(point4);
		newPointSET.insert(point5);
		Point2D nearestPoint = newPointSET.nearest(point1);
		nearestPoint.draw();*/
	}
}