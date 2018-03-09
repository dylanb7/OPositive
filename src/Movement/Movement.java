package Movement;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JFrame;

import Entities.GridEntities;
import Entities.Player;
import Opositive.Vector;

public class Movement {
	
	private Vector offset;
	
	private final int chunkEnts = 100;
	
	private int gridW,gridH;
	
	private LinkedList<Chunk> chunks;
	
	private LinkedList<Grid> loaded;
	
	private LinkedList<GridEncription> stored;
	
	private JFrame frame;
	
	private Player player;
	
	private Rectangle bounds;
	
	public Movement(JFrame frame, Player player){
		offset = new Vector(0,0);
		chunks = new LinkedList<>();
		loaded = new LinkedList<>();
		stored = new LinkedList<>();
		gridW = frame.getWidth();
		gridH = frame.getHeight();
		this.frame = frame;
		this.player = player;
		bounds = new Rectangle(0,0,frame.getWidth(), frame.getHeight());
		inverseChunkAtPoint(new Point(0,0));
	}
	
	public void moveByVector(Vector v){
		offset.addVector(v);
		for(int i = 0; i < loaded.size(); i++){
			loaded.get(i).shiftGrid(v);
		}
		adjustLoading();
	}
	
	private void adjustLoading(){
		reloadPlayerChunk();
		Point playerChunkPoint = player.getCurrentChunk();
		for(int r = -1; r <= 1; r++){
			for(int c = -1; c <= 1; c++){
				if(r == c)
					continue;
				Point temp = new Point(playerChunkPoint.x+r, playerChunkPoint.y+c);
				Chunk chunk = getChunkAtPoint(temp);
				if(chunk == null || !chunk.isLoaded()){
					if(wouldIntersectScreen(temp))
						inverseChunkAtPoint(temp);
				}else {
					Grid g = chunk.getGrid();
					if(!g.getBounds().intersects(bounds))
						inverseChunkAtPoint(g.getPoint());
				}	
			}
		}
	}
	
	private boolean wouldIntersectScreen(Point p){
		int x = p.x*gridW+offset.getX();
		int y = p.y*gridH+offset.getY();
		Rectangle rect = new Rectangle(x, y, gridW, gridH);
		if(bounds.intersects(rect))
			return true;
		return false;
	}
	
	private Chunk getChunkAtPoint(Point p){
		for(int i = 0; i < chunks.size(); i++)
			if(chunks.get(i).getPoint().equals(p))
				return chunks.get(i);
		return null;
	}
	
	private void reloadPlayerChunk(){
		Point playerPoint = player.getCurrentChunk();
		Chunk curr = getChunkAtPoint(playerPoint);
		Point midPoint = new Point(frame.getWidth()/2,frame.getHeight()/2);
		if(curr.getGrid().getBounds().contains(midPoint))
			return;
		else{
			for(int i = 0; i < loaded.size(); i++)
				if(loaded.get(i).getBounds().contains(midPoint))
					player.setCurrentChunk(loaded.get(i).getPoint());
		}
	}
	
	private void inverseChunkAtPoint(Point p){
		Chunk chunk = getChunkAtPoint(p);
		if(chunk == null){
			makeChunk(p);
			inverseChunkAtPoint(p);
		}else
			chunk.Flip();
	}
	
	private void makeChunk(Point p){
		String gen = "";
		for(int i = 0; i < chunkEnts; i++){
			if(Math.random()<0.1)
				gen+="T";
			else
				gen+="G";
		}	
		GridEncription enc = new GridEncription(gen,p);
		Chunk chunk = new Chunk(loaded, stored, enc, gridW, gridH, offset, this);
		chunks.add(chunk);
	}
	
	private boolean checkForCollisions(Vector k){
		Vector v = player.getImpulse();
		Point current = player.getCurrentChunk();
		Point possible = new Point(current.x+v.getX(), current.y+v.getY());
		LinkedList<GridEntities> list = new LinkedList<>();
		list.addAll(getChunkAtPoint(current).getGrid().getEntities());
		Chunk chunk = getChunkAtPoint(possible);
		if(chunk != null && chunk.isLoaded())
			list.addAll(chunk.getGrid().getEntities());
		return true;
	}
	
	public Vector getOffset(){
		return offset;
	}
	
	public LinkedList<Grid> getLoaded(){
		return loaded;
	}
	
}
