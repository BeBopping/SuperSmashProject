package eecs285.proj4.game;

import eecs285.proj4.util.UtilObject;

public abstract class MovingObject extends UtilObject {
	protected float velX;
	protected float velY;
	
	public MovingObject(float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		velX = 0.0f;
		velY = 0.0f;
	}
	
	public float getVelocityX(){
		return velX;
	}
	
	public float getVelocityY(){
		return velY;
	}
}