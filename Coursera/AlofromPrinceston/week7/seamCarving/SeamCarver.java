import java.util.*;
import edu.princeton.cs.algs4.*;
import java.awt.Color;

public class SeamCarver{
	private Picture picture;
	private double[][] energy;
	private int[][] parent;
	private static final double MAX_ENERGY = 1000;
	// the energy and parent do not have same indexing
	public SeamCarver(Picture picture){
		if (picture == null)
			throw new IllegalArgumentException("Cannot be null pic");
		this.picture = new Picture(picture);
		this.energy = new double[picture.width()][picture.height()];
		this.parent = new int[picture.width()][picture.height()];
		for(int i = 0; i < picture.width(); i++){
			for (int j = 0; j < picture.height(); j++){
				energy[i][j] = energy(i,j);
			}
		}
	}
	public Picture picture(){
		return picture;
	}
	public int width(){
		return picture.width();
	}
	public int height(){
		return picture.height();
	}
	public double energy(int x, int y){
		if (x < 0 || x > picture.width()-1 || y < 0 || y > picture.height()-1)
			throw new IndexOutOfBoundsException();
		if (x == 0 || x == picture.width()-1 || y == 0 || y == picture.height()-1)
			return MAX_ENERGY;
		double energyX = gradient(picture.get(x-1,y),picture.get(x+1,y)); 
		double energyY = gradient(picture.get(x,y-1),picture.get(x,y+1));
		return Math.pow(energyX+energyY,0.5);
	}
	private double gradient(Color a,Color b){
		double difInR = a.getRed() - b.getRed();
		double difInG = a.getGreen() - b.getGreen();
		double difInB = a.getBlue() - b.getBlue();
		return Math.pow(difInR,2)+Math.pow(difInB,2)+Math.pow(difInG,2);
	}
	public int[] findHorizontalSeam(){
		transpose();
		int[] seam = findVerticalSeam();
		transpose();
		return seam;
	}
	public int[] findVerticalSeam(){
		int[] seam = new int[height()];
		double[] disTo = new double[width()];
		double[] upperDisto = new double[width()];
		for (int col = 0; col < height(); col++){
			for (int row = 0; row < width(); row++){
				relax(row,col,disTo,upperDisto);
			}
			System.arraycopy(disTo,0,upperDisto,0,width());
		}
		// be care of the index of energy[][]
		// energy = [width][height] parent = [height][width]
		int best = 0; 
		for (int i = 1; i < upperDisto.length; i++){ // the last array of the bottom
			if (upperDisto[i] < upperDisto[best])
				best = i;
		}
		seam[height()-1] = best;
		for (int i = height()-2; i >= 0; i--){
			seam[i] = parent[best][i+1];
			best = parent[best][i+1];
		}
		return seam;
	}
	private void relax(int col, int row, double[] disTo, double[] upperDisto){
		if (disTo.length == 1)
			return;
		if (row == 0){
			disTo[col] = MAX_ENERGY;
			parent[col][row] = 0;
			return;
		}
		if (col == 0){
			disTo[col] = Math.min(upperDisto[col],upperDisto[col+1]) + energy[col][row];
			if (upperDisto[col] < upperDisto[col+1])
				parent[col][row] = col;
			else 
				parent[col][row] = col+1;
			return;
		}
	 	if (col == width()-1){
			disTo[col] =  Math.min(upperDisto[col],upperDisto[col-1]) + energy[col][row];
			if (upperDisto[col-1] > upperDisto[col])
				parent[col][row] = col;
			else 
				parent[col][row] = col-1;
			return;
		}
		double mid = upperDisto[col];
		double left = upperDisto[col-1]; 
		double right = upperDisto[col+1];
		double min = Math.min(mid,Math.min(left,right));
		disTo[col] = min + energy[col][row];
		if (min == left){
			parent[col][row] = col-1;
		}
		else if (min == right){
			parent[col][row] = col+1; 
		}
		else{
			parent[col][row] = col;
		}	
	}
	private void transpose(){
		Picture transposePicture = new Picture(picture.height(),picture.width());
		double[][] newEnergy = new double[picture.height()][picture.width()];
		for (int i = 0; i < picture.width(); i++){
			for (int j = 0; j < picture.height(); j++){
				transposePicture.set(j,i,picture.get(i,j));
				newEnergy[j][i] = energy[i][j];
			}
		}
		energy = newEnergy;
		picture = transposePicture;
		parent = new int[picture.width()][picture.height()];
		/*
		for (int i = 0; i < transposePicture.width(); i++){
			for (int j = 0; j < transposePicture.height(); j++)
				transposePicture.set(i,j,picture.get(j,i));
		}
		this.picture = transposePicture;
		double[][] newEnergy = new double[picture.width()][picture.height()];
		int[][] newParent = new int[picture.height()][picture.width()];
		for (int i = 0; i < width(); i++){
			for (int j = 0; j < height(); j++)
				newEnergy[i][j] = energy(i,j);
		}
		this.energy = newEnergy;
		this.parent = newParent;
		*/
	}
	public void removeHorizontalSeam(int[] seam){
		if (seam.length != width())
			throw new IllegalArgumentException("The seam's size is not correct");
		this.picture = removeSeam(seam,false);
		energy = new double[width()][height()];
		for (int j = 0; j < height(); j++)
			for (int i = 0; i < width(); i++)
				energy[i][j] = energy(i,j);
	}
	public void removeVerticalSeam(int[] seam){
		if (seam.length != height())
			throw new IllegalArgumentException("The seam's size is not correct");
		this.picture = removeSeam(seam,true);
		energy = new double[width()][height()];
		for (int j = 0; j < height(); j++)
			for (int i = 0; i < width(); i++)
				energy[i][j] = energy(i,j);
	}
	private void check(int[] seam, int length){
		if (width() < 1 || height() < 1){
			throw new IllegalArgumentException("The width or heigth must > 1 ");
		}
		if (seam.length < 1){
			throw new IllegalArgumentException("The seam is less than 1");
		}
		for(int i = 0; i < seam.length-1; i++){
			if (seam[i] < 0 || seam[i] > length)
				throw new IllegalArgumentException();
			if (Math.abs(seam[i+1]-seam[i]) > 1)
				throw new IllegalArgumentException();
		}
	}
	private Picture removeSeam(int[] seam, boolean vertical){
		Picture p = null;
		if (vertical){
			check(seam,width());
			p = new Picture(width()-1,picture.height());
			for (int j = 0; j < height(); j++){
				for (int i = 0, k = 0; i < width(); i++ ){
					if (i != seam[j]){
						p.set(k,j,picture.get(i,j));
						k++;
					}
					
				}
			}
		}
		else{
			check(seam,height());
			p = new Picture(width(),height()-1);
			for (int i = 0; i < width(); i++){
				for (int j = 0, k = 0; j < height(); j++){
					if (j != seam[i]){
						p.set(i,k,picture.get(i,j));
						k++;
					}
				}
			}
		}
		return p;
	}
}