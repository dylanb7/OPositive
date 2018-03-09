package Entities;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {

	private Timer aTimer;
	
	private BufferedImage[] frames;
	
	private int currentImage, pause;
	
	private Entity ent;
	
	public Animation(BufferedImage[] frames, Entity entity, int pause){
		this.frames = frames;
		this.ent = entity;
		this.currentImage = 0;
		this.pause = pause;
	}
	
	public void setStartFrame(int index){
		if(index >= 0 || index < frames.length)
		currentImage = index;
	}
	
	public void startAnimation(){
		if(aTimer == null)
			aTimer = new Timer();
		aTimer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				if(currentImage >= frames.length)
					currentImage = 0;
				ent.setImage(frames[currentImage]);
				currentImage++;
			}
		}, 0, pause);
	}
	
	public void stopAnimation(){
		if(aTimer == null)
			return;
		aTimer.cancel();
		aTimer.purge();
	}
	
}
