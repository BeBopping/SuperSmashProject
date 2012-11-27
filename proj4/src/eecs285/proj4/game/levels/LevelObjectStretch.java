package eecs285.proj4.game.levels;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class LevelObjectStretch extends LevelObject {
	Sprite sprite;
	
	public LevelObjectStretch(Sprite sprite, float left, float right, float top, float bottom){
		super(left, right, top, bottom);
		
		this.sprite = sprite;
	}

	public void step(double delta){}
	
	public void render(double delta){
		Render.render(sprite, this);
	}
}