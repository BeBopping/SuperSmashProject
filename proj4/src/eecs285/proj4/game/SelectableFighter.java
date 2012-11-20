package eecs285.proj4.game;

import eecs285.proj4.util.Sprite;
import eecs285.proj4.util.SelectableObject;

public class SelectableFighter extends SelectableObject {
	private String fighterName;
	private Sprite fighterImage;
	
	public SelectableFighter(String fighterName, Sprite fighterImage, float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		this.fighterName = fighterName;
		this.fighterImage = fighterImage;
	}

	public void step(double delta){
		if(currentState == State.BEGIN_MOUSE_PRESS){
			//
		}
	}

	public void render(double delta){
		if(currentState != State.NONE){
			// render hover
		}
	}
}