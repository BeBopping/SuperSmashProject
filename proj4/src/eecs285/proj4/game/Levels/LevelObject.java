package eecs285.proj4.game.Levels;

import eecs285.proj4.util.UtilObject;

public abstract class LevelObject extends UtilObject {
	public LevelObject(float left, float right, float top, float bottom){
		super(left, right, top, bottom);
	}

	public abstract void step(double delta);
	public abstract void render(double delta);
}