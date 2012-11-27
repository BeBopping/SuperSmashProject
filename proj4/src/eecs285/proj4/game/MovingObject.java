package eecs285.proj4.game;

import eecs285.proj4.util.UtilObject;

public abstract class MovingObject extends UtilObject {
	protected float lastPosX;
	protected float lastPosY;
	protected float velX;
	protected float velY;
	
	public MovingObject(float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		lastPosX = posX;
		lastPosY = posY;
	}
	
	public float getVelocityX(){
		return velX;
	}
	
	public float getVelocityY(){
		return velY;
	}
	
	public float getPreviousLeftEdge(){
		return lastPosX;
	}
	
	public float getPreviousRightEdge(){
		return lastPosX + sizeX;
	}
	
	public float getPreviousTopEdge(){
		return lastPosY;
	}
	
	public float getPreviousBottomEdge(){
		return lastPosY + sizeY;
	}
}