package Entities;

public enum GridType {
	
	Grass('G',"src/Images/Grass-1.png"), Chest('C',""), Rock('R',""),Tree('T',"src/Images/tree.png");
	
	private char value;
	
	private String path;
	
	private GridType(char c, String path){
		this.value = c;
		this.path = path;
	}
	
	public char getValue(){
		return value;
	}
	
	public String getPath(){
		return path;
	}
	
	public GridEntities getEntFromChar(char c){
		switch(c){
			case 'T':
				return new Tree();
		}
		return null;
	}
	
}
