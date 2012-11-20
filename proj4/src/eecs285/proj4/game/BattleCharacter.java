package eecs285.proj4.game;

import eecs285.proj4.util.UtilObject;

public abstract class BattleCharacter extends UtilObject {
	private int stock;
	
	public BattleCharacter(float width, float height) {
		super(0, width, 0, height);

	}
	
	public void setStock(int stock){
		this.stock = stock;
	}
	
	public int getStock(int stock){
		return stock;
	}
	
	public void step(double delta) {

	}

	public void render(double delta) {

	}
	
}