package Movement;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import Entities.Grass;
import Entities.GridEntities;
import Opositive.Vector;

public class Grid {

	private int spaceW, spaceH, rows, cols;
	
	private int width,height;
	
	private LinkedList<GridEntities> entities;
	
	private Point pos;
	
	private Movement movement;
	
	public Grid(int rows, int cols, int w, int h, Point pos, Movement movement){
		entities = new LinkedList<>();
		this.width = (w/cols)*cols;
		this.height = (h/rows)*rows;
		this.spaceW = width/cols;
		this.spaceH = height/rows;
		this.pos = pos;
		this.rows = rows;
		this.cols = cols;
		this.movement = movement;
		Grass background = new Grass();
		background.setBounds(getBounds());
		background.loadImage();
		entities.add(background);
	}
	
	public Dimension getDimension(){
		return new Dimension(width, height);
	}
	
	public Rectangle getBounds(){
		Vector offset = movement.getOffset();
		return new Rectangle((pos.x*width)+offset.getX(), (pos.y*height)+offset.getY(), width, height);
	}
	
	public void placeEntity(int row, int col, GridEntities e){
		Vector offset = movement.getOffset();
		if(row > rows || col > cols)
			return; 
		e.setBounds((pos.x*width)+(row*spaceH)+offset.getX(),(pos.y*height)+(col*spaceW)+offset.getY(), spaceH, spaceW);
		e.setPos(new Point(row, col));
		e.loadImage();
		entities.add(e);
	}
	
	public void shiftGrid(Vector v){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).setVector(v);
			entities.get(i).moveByVector();
		}
	}
	
	public Point getPoint(){
		return pos;
	}
	
	public LinkedList<GridEntities> getEntities(){
		return entities;
	}
	
}
