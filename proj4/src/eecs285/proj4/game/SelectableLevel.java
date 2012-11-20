package eecs285.proj4.game;

import eecs285.proj4.util.Sprite;
import eecs285.proj4.util.SelectableObject;

public class SelectableLevel extends SelectableObject {
	private String levelName;
	private Sprite levelImage;
	
	public SelectableLevel(String levelName, Sprite levelImage, float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		this.levelName = levelName;
		this.levelImage = levelImage;
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