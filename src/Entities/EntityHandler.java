package Entities;

import java.util.LinkedList;

import javax.swing.JFrame;

import GUI.Render;
import Movement.Grid;
import Movement.Movement;

public class EntityHandler {

	private LinkedList<Grid> loaded;
	
	private final int playerW, playerH;
	
	private Movement movement;
	
	private Player player;
	
	private Render render;
	
	public EntityHandler(JFrame frame, Render r){
		this.render = r;
		playerW = 60;
		playerH = 60;
		this.player = new Player((frame.getWidth()/2)-(playerW/2), (frame.getHeight()/2)-(playerH/2), playerW, playerH);
		this.movement = new Movement(frame, player);
	}
	
	public Movement getMovement(){
		return movement;
	}
	
	public void getEntitiesToPaint(){
		LinkedList<Grid> curr = movement.getLoaded();
		LinkedList<Entity> master = new LinkedList<>();
		for(int i = 0; i < curr.size(); i++)
			master.addAll(curr.get(i).getEntities());
		master.add(player);
		render.setEntities(master);
	}
	
}
