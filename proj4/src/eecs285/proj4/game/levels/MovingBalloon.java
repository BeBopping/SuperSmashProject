package eecs285.proj4.game.levels;

//import org.newdawn.slick.Color;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class MovingBalloon extends LevelObjectStretch{
	
	private float velX;
	private float velY;
	
	private float lastX;
	private float lastY;
	
	private double totalTime;
	
	MovingBalloon(Sprite sprite, float left, float right, float top, float bottom){
		super(sprite, left, right, top, bottom);
		this.velY = -2.0f;
		this.velX = 1.0f;
		this.totalTime = 0.0;
	}
	
	public void step(double delta){
		lastX = posX;
		lastY = posY;
		totalTime += delta;
		
		velX = (float)Math.sin(totalTime * 1.0f);
		this.posX += delta * velX;
		this.posY += delta * velY;
		if(this.posY < -15.0f){
			this.posY = 12.0f;
			this.posX = (float) ((Math.random() * 2 - 1) * 11.0f);
		}
	}
	
	public void render(double delta){
		float extraWidth = 0.3333f * getSizeX();
		Render.render(sprite, 
				getLeftEdge() - extraWidth, 
				getRightEdge() + extraWidth, 
				getTopEdge() - extraWidth*0.5f,
				getBottomEdge() + extraWidth*1.5f);
	}

	public float getPreviousLeftEdge(){ return lastX; }
	public float getPreviousRightEdge(){ return lastX + sizeX; }
	public float getPreviousTopEdge(){ return lastY; }
	public float getPreviousBottomEdge(){ return lastY + sizeY; }
	public float getGroundSpeed(){ return velX; }
	
}