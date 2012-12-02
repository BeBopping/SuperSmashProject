package eecs285.proj4.game.levels;

import eecs285.proj4.util.UtilObject;

public abstract class LevelObject extends UtilObject {
	public LevelObject(float left, float right, float top, float bottom){
		super(left, right, top, bottom);
	}

	public abstract void step(double delta);
	public abstract void render(double delta);
	
	public float getPreviousLeftEdge(){ return getLeftEdge(); }
	public float getPreviousRightEdge(){ return getRightEdge(); }
	public float getPreviousTopEdge(){ return getTopEdge(); }
	public float getPreviousBottomEdge(){ return getBottomEdge(); }
	public float getGroundSpeed(){ return 0.0f; }
}