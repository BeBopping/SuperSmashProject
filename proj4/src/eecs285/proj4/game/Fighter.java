package eecs285.proj4.game;

import net.java.games.input.Controller;

public abstract class Fighter extends MovingObject {
	protected static final float GRAVITY = 10.0f;
	protected static final float TERMINALX = 50.0f;
	protected static final float TERMINALY = 50.0f;
	
	protected String fighterName;
	protected FighterTrait fighterTrait;
	protected Controller controller;
	int hitPercent;
	int stock;
	int kills;
	int deaths;
	int suicides;
	
	protected FighterState fighterState;
	protected float flightTime;
	
	public Fighter(String name, FighterTrait trait,
			float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		fighterName = name;
		fighterTrait = trait;
		fighterState = FighterState.InAir;
	}
	
	public void SetController(Controller controller){
		this.controller = controller;
	}
	
	public void SetStock(int stock){
		this.stock = stock;
	}
	
	public String toString(){
		return fighterName;
	}
	
	public abstract void getInput(double delta);
	public abstract void collideWithSolid(Direction dir, float pos);
	public abstract void collideWithPlatform(float pos);
	
}