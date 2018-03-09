package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Entities.EntityHandler;
import Opositive.Vector;

public class Frame extends JFrame {

	private EntityHandler handler;
	
	private Timer gameTimer;
	
	public Frame(){
		super("");
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(700,723);
		setLocation((screen.width/2)-(getWidth()/2),(screen.height/2)-(getHeight()/2));
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Render render = new Render(null,this);
		this.handler = new EntityHandler(this, render);
		add(render);
		validate();
	}
	
	public void startRepaints(){
		if(gameTimer == null)
			gameTimer = new Timer();
		gameTimer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				handler.getMovement().moveByVector(new Vector(0,10));
				handler.getEntitiesToPaint();
			}
		}, 0, 1000/30);
	}
	
	public void stopRepaints(){
		if(gameTimer == null)
			return;
		gameTimer.cancel();
		gameTimer.purge();
	}
	
}
