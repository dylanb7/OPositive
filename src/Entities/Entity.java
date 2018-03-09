package Entities;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import Opositive.Vector;

public abstract class Entity extends Rectangle {

	private HashSet<String> testMasks;
	
	private String testMask;
	
	private Vector current;
	
	private Image currentImage;
	
	private Animation animation;
	
	public Entity(Vector current){
		this.current = current;
		testMasks = new HashSet<>();
	}
	
	public Entity(Vector current, Image image){
		this(current);
		currentImage = image;
	}
	
	public Entity(Vector current, int x, int y, int w, int h){
		this(current);
		setBounds(x,y,w,h);
	}
	
	public void setVector(Vector v){
		this.current = v;
	}
	
	public void setImage(Image image){
		currentImage = image;
	}
	
	public Image getImage(){
		return currentImage;
	}
	
	public void setAnimation(Animation animation){
		this.animation = animation;
	}
	
	public void runAnimation(){
		if(animation != null)
			animation.startAnimation();
	}
	
	public void stopAnimation(){
		if(animation != null)
			animation.stopAnimation();
	}
	
	public Animation getAnimation(){
		return animation;
	}
	
	public void setMask(String testMask){
		this.testMask = testMask;
	}
	
	public void addMask(Entity ent){
		testMasks.add(ent.testMask);
	}
	
	public boolean canContact(Entity ent){
		return testMasks.contains(ent.testMask);
	}
	
	public void moveByVector(){
		x+=current.getX();
		y+=current.getY();
	}
	
}

