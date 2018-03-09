package Entities;

import java.awt.Point;
import java.awt.image.BufferedImage;

import Opositive.Utilities;
import Opositive.Vector;

public class Player extends Entity {

	private Animation walkingLeft, walkingRight, walkingUp, walkingDown, idle;
	
	private PlayerImpulse currentImpulse;
	
	private Point currentChunk;
	
	private int speedScalar;
	
	public Player(int x, int y, int width, int height) {
		super(new Vector(0,0));
		setBounds(x,y,width,height);
		currentChunk = new Point(0,0);
		currentImpulse = PlayerImpulse.NONE;
		speedScalar = 1;
		loadAnimations();
		this.setAnimation(idle);
	}
	
	public void loadAnimations(){
		final int frameTime = 500;
		String[] paths = {};
		BufferedImage[] images = new BufferedImage[paths.length];
		for(int i = 0; i < paths.length; i++){
			BufferedImage image = Utilities.loadImage(paths[i]);
			image = (BufferedImage) image.getScaledInstance(width, height, 0);
			images[i] = image;
		}
		walkingRight = new Animation(images, this, frameTime);
		String[] idlePaths = {};
		BufferedImage[] idleImages = new BufferedImage[idlePaths.length];
		for(int i = 0; i < idlePaths.length; i++){
			BufferedImage image = Utilities.loadImage(idlePaths[i]);
			image = (BufferedImage) image.getScaledInstance(width, height, 0);
			idleImages[i] = image;
		}
		idle = new Animation(idleImages,this,frameTime);
		Animation[] animations = {walkingDown,walkingLeft,walkingUp};
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < images.length; j++){
				images[i] = rotateClockwise90(images[j]);
			}
			animations[i] = new Animation(images,this,frameTime);
		}
	}
	
	
	
	private BufferedImage rotateClockwise90(BufferedImage src) {
	    int w = src.getWidth();
	    int h = src.getHeight();
	    BufferedImage dest = new BufferedImage(h, w, src.getType());
	    for (int y = 0; y < h; y++) 
	        for (int x = 0; x < w; x++) 
	            dest.setRGB(y,x,src.getRGB(x,y));
	    return dest;
	}
	
	public void setCurrentChunk(Point point){
		this.currentChunk = point;
	}
	
	public Point getCurrentChunk(){
		return currentChunk;
	}
	
	public Vector getImpulse(){
		return currentImpulse.getImpulse();
	}
	
	public Vector getMovementAm(){
		return currentImpulse.getImpulse().scale(speedScalar);
	}

}
