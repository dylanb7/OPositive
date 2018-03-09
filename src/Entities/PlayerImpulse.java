package Entities;

import Opositive.Vector;

public enum PlayerImpulse {
	UP(new Vector(0,-1)),DOWN(new Vector(0,1)),RIGHT(new Vector(1,0)),LEFT(new Vector(-1,0)),NONE(new Vector(0,0));
	
	private Vector v;
	
	private PlayerImpulse(Vector v){
		this.v = v;
	}
	
	public Vector getImpulse(){
		return v;
	}
}
