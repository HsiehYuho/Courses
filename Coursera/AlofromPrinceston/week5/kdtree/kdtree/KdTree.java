import java.util.Queue;
import edu.princeton.cs.algs4.*;

public class KdTree{
	private static final boolean COMPAREX = true;
	private static final boolean COMPAREY = false;
	private static final RectHV UNIT_SQUARE = new RectHV(0.0,0.0,1.0,1.0);
	private int treeSize;
	private Node root; 
	private static class Node{
		private Point2D p;
		private boolean vertical;
//		private RectHV rect;
		private Node lb;
		private Node rt;
		public Node(Point2D p, Node lb, Node rt, boolean vertical){
			this.p = p;
			this.lb = lb;
			this.rt = rt;
			this.vertical = vertical;
		}
	} 
	public KdTree(){
		this.treeSize = 0;
		this.root = null;
	}
	public boolean isEmpty(){
		return this.treeSize == 0;
	}
	public int size(){
   		return this.treeSize;
   	}	
   	public void insert(Point2D p){
		this.root = insertNode(this.root,p,COMPAREX); // comparex = true;
	} 
	// return whether the point p is in the left subtree of node or right subtree of the node  
	private boolean isSmallerThanPoint(Point2D p, Node node){  
		if ((node.vertical && p.x() < node.p.x()) || (!node.vertical && p.y() < node.p.y() ))
			return true;
		else 
			return false;
	}
	private Node insertNode(Node node, Point2D p, boolean vertical){
		// if none, then create it
		if (node == null){
			treeSize++;
			return new Node(p,null,null,vertical);
		}
		// if have the same, return it
		else if (p.x() == node.p.x() && p.y() == node.p.y()){
			return node;
		}
		else if (isSmallerThanPoint(p,node)){
			node.lb = insertNode(node.lb,p,!node.vertical); // reverse with the previous layer
		}
		else{
			node.rt = insertNode(node.rt,p,!node.vertical); // reverse with the previous layer
		}
		return node;
	}
	public boolean contains(Point2D p){
		Node node = root;
		while (node!= null){
			if (node.p.equals(p))
				return true;
			else{
				if (isSmallerThanPoint(p,node))
					node = node.lb;
				else 
					node = node.rt;
			}
		}
		return false;
	}        
	public void draw(){
		StdDraw.setScale(0.0,1.0);
		doDraw(root,UNIT_SQUARE);
	}   
	private void doDraw(Node node, RectHV rect){
		if (node == null)
			return;
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		node.p.draw();
		Point2D fromPoint, toPoint;
		if (node.vertical){
			StdDraw.setPenColor(StdDraw.RED);
			fromPoint = new Point2D(node.p.x(),rect.ymin());
			toPoint = new Point2D(node.p.x(),rect.ymax());
		}
		else{
			StdDraw.setPenColor(StdDraw.BLUE);
			fromPoint = new Point2D(rect.xmin(),node.p.y());
			toPoint = new Point2D(rect.xmax(),node.p.y());
		}
		fromPoint.drawTo(toPoint);
		doDraw(node.lb,leftNodeRect(node.lb,rect));
		doDraw(node.rt,rightNodeRect(node.rt,rect));
	}               
	// draw leftNodeRect base on the node and the cuurent nodeRect
	private RectHV leftNodeRect(Node node, RectHV nodeRect){
		RectHV leftNodeRect;
		if (node == null)
			return nodeRect;
		if (node.vertical)
			leftNodeRect = new RectHV(nodeRect.xmin(),nodeRect.ymin(),node.p.x(),nodeRect.ymax());
		else
			leftNodeRect = new RectHV(nodeRect.xmin(),nodeRect.ymin(),nodeRect.xmax(),node.p.y());
		return leftNodeRect;
	}  
	private RectHV rightNodeRect(Node node, RectHV nodeRect){
		RectHV rightNodeRect;
		if (node == null)
			return nodeRect;
		if (node.vertical)
			rightNodeRect = new RectHV(node.p.x(),nodeRect.ymin(),nodeRect.xmax(),nodeRect.ymax());
		else
			rightNodeRect = new RectHV(nodeRect.xmin(),node.p.y(),nodeRect.xmax(),nodeRect.ymax());
		return rightNodeRect;
	}
	public Iterable<Point2D> range(RectHV rect){
   		SET<Point2D> rangeSet = new SET<Point2D>();
   		doRange(root,UNIT_SQUARE,rect,rangeSet);
   		return rangeSet;
	}         
	private void doRange(Node node, RectHV nodeRect, RectHV queryRect, SET<Point2D> rangeSet){
		if (node == null)
			return;
		if (queryRect.intersects(nodeRect)){
			if (queryRect.contains(node.p))
				rangeSet.add(node.p);
			// the query rect must intersects with certain rect
			doRange(node.lb,leftNodeRect(node,nodeRect),queryRect,rangeSet); 
			doRange(node.rt,rightNodeRect(node,nodeRect),queryRect,rangeSet);
		}
	}
	public Point2D nearest(Point2D p){
		return doNearest(root,UNIT_SQUARE,p,null);
	}
	private Point2D doNearest(Node node, RectHV nodeRect, Point2D queryPoint, Point2D nearestPoint){
		if (node == null)
			return nearestPoint;
		Point2D candidate = nearestPoint;
		double nearestDist = (candidate!= null)
			? queryPoint.distanceSquaredTo(candidate)
			: Double.MAX_VALUE;
		if (nearestDist > nodeRect.distanceSquaredTo(queryPoint)){
			double dist = queryPoint.distanceSquaredTo(node.p);
			if (dist < nearestDist)
				candidate = node.p;
			RectHV leftNodeRect = leftNodeRect(node,nodeRect);
			RectHV rightNodeRect = rightNodeRect(node,nodeRect);

			// explore left subtree
			if (isSmallerThanPoint(queryPoint,node)){
				candidate = doNearest(node.lb,leftNodeRect,queryPoint,candidate);
				// if the distance of leftNodeRect and queryPoint is smaller than current candidate 
				// then the nearestDist of candidate and queryPoint is << than right
				// so there is no need to calculate  
				candidate = doNearest(node.rt,rightNodeRect,queryPoint,candidate);
			}
			// explore right subtree
			else{
				candidate = doNearest(node.rt,rightNodeRect,queryPoint,candidate);
				candidate = doNearest(node.lb,leftNodeRect,queryPoint,candidate);	
			}
		}
		return candidate;
	}         
	public static void main(String[] args){
		String filename = args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the two data structures with point from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }
        kdtree.draw();
	}           
}