package Entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Opositive.Utilities;
import Opositive.Vector;

public abstract class GridEntities extends Entity {

	private GridType type;
	
	private Point gridPos;
	
	public GridEntities(Vector current, GridType type) {
		super(current);
		this.type = type;
		setPos(new Point(0,0));
		
	}
	
	public void loadImage(){
		BufferedImage image = Utilities.loadImage(type.getPath());
		if(image == null){
			System.out.println("Fail");
			return;
		}
		Image temp  = image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.drawImage(temp, 0, 0, null);
		g.dispose();
		setImage(image);
	}

	public void setPos(Point pos){
		gridPos = pos;
	}
	
	public Point getPos(){
		return gridPos;
	}
	
	public GridType getType(){
		return type;
	}

	
}
