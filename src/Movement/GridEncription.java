package Movement;

import java.awt.Point;

public class GridEncription {

	private Point point;
	
	private String string;
	
	public GridEncription(String string, Point point){
		this.string = string;
		this.point = point;
	}
	
	public String getString(){
		return string;
	}
	
	public Point getPoint(){
		return point;
	}
	 
}
