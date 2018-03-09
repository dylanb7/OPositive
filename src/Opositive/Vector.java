package Opositive;

public class Vector {

	private int x,y;
	
	public Vector(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getMagnitude(){
		return (int)(Math.sqrt(x*x+y*y));
	}
	
	public void addVector(Vector v){
		x+=v.x;
		y+=v.y;
	}
	
	public Vector scale(int am){
		return new Vector(x*am,y*am);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}

