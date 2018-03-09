package GUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Entities.Entity;

public class Render extends JComponent{
	
	private LinkedList<Entity> entities;
	
	private JFrame frame;
	
	public Render(LinkedList<Entity> entities, JFrame frame){
		this.entities = entities;
		this.frame = frame;
	}
	
	public void setEntities(LinkedList<Entity> entities){
		this.entities = entities;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		Rectangle bounds = new Rectangle(0, 0, frame.getWidth(), frame.getHeight());
		for(int i = 0; i < entities.size(); i++){
			Entity ent = entities.get(i);
			if(ent == null || !bounds.intersects(ent))
				continue;
			g.drawImage(ent.getImage(), ent.x, ent.y, this);
		}
	}
	
}
