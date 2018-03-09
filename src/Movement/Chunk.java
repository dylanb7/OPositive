package Movement;

import java.awt.Point;
import java.util.LinkedList;

import Entities.GridEntities;
import Entities.GridType;
import Opositive.Vector;

public class Chunk {

	private Grid grid;
	
	private LinkedList<Grid> loaded;
	
	private LinkedList<GridEncription> stored;
	
	private GridEncription encription;
	
	private int width, height;
	
	private boolean isLoaded;
	
	private int edgeLengths;
	
	private Movement movement;
	
	public Chunk(LinkedList<Grid> loaded, LinkedList<GridEncription> stored, GridEncription encription, int width, int height, Vector offset, Movement movement){
		this.isLoaded = false;
		this.loaded = loaded;
		this.stored = stored;
		this.encription = encription;
		this.width = width;
		this.height = height;
		this.movement = movement;
		this.edgeLengths = (int) Math.sqrt(encription.getString().length());
	}
	
	private void loadChunk(){
		isLoaded = true;
		String map = encription.getString();
		GridType type = GridType.Grass;
		grid = new Grid(edgeLengths, edgeLengths, width, height, encription.getPoint(), movement);
		for(int r = 0; r < edgeLengths; r++){
			for(int c = 0; c < edgeLengths; c++){
				char kind = map.charAt((r*edgeLengths)+c);
				if(kind == 'G')
					continue;
				grid.placeEntity(c, r, type.getEntFromChar(kind));
			}
		}
		stored.remove(encription);
		encription = null;
		loaded.add(grid);
		System.out.println("Loaded chunks: "+loaded.size());
		for(int i = 0; i < loaded.size(); i++)
			System.out.println(loaded.get(i).getPoint());
	}
	
	private void encriptChunk(){
		isLoaded = false;
		String map = "";
		LinkedList<GridEntities> ents = grid.getEntities();
		for(int r = 0; r < edgeLengths; r++){
			for(int c = 0; c < edgeLengths; c++){
				GridEntities ent = findEnt(ents, new Point(c,r));
				if(ent == null)
					map+='G';
				else
					map+=ent.getType().getValue();
			}
		}
		encription = new GridEncription(map, getPoint());
		loaded.remove(grid);
		grid = null;
		stored.add(encription);
		System.out.println("Saved chunks: "+stored.size());
	}
	
	private GridEntities findEnt(LinkedList<GridEntities> list,Point pos){
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).getPos().equals(pos))
				return list.get(i);
		return null;
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	public GridEncription getEncription(){
		return encription;
	}
	
	public boolean isLoaded(){
		return isLoaded;
	}
	
	public void Flip(){
		if(isLoaded)
			encriptChunk();
		else
			loadChunk();
	}
	
	public Point getPoint(){
		if(encription == null)
			return grid.getPoint();
		else
			return encription.getPoint();
	}
}
