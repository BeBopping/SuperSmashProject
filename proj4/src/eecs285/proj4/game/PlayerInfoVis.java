package eecs285.proj4.game;

import net.java.games.input.Controller;
import eecs285.proj4.util.Sprite;
import eecs285.proj4.util.SelectableObject;

public class PlayerInfoVis extends SelectableObject {
	Controller controller;
	

	public PlayerInfoVis() {
		this(0,0,0,0);
	}
	
	public PlayerInfoVis(float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
	}

	public void setController(Controller controller){
		this.controller = controller;
	}
	
	public void step(double delta){
		if(currentState == State.BEGIN_MOUSE_PRESS){
			//pop level select state
			//push battle state
		}
	}

	public void render(double delta){
		if(currentState != State.NONE){
			// render hover
		}
	}
}