package eecs285.proj4.game.levels;

import org.newdawn.slick.Color;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class MovingLevelBlock extends LevelObjectStretch{
	
	private float lastX;
	private float lastY;
	
	private float velX;
	private float accelX; //How quickly it turns around.
	private boolean isFlipped; //True if the sprite is flipped.
	
	MovingLevelBlock(Sprite sprite, float left, float right, float top, float bottom){
		super(sprite, left, right, top, bottom);
		this.velX = 2.0f;
		this.accelX = 1.0f;
		this.isFlipped = false;
	}
	
	public void step(double delta){
		lastX = posX;
		lastY = posY;
		
		if(posX > 5.0f){
			this.velX -= delta * accelX;
		}
		if(posX < -5.0f){
			this.velX += delta * accelX;
		}
		if(velX > 2.0f){
			velX = 2.0f;
		}
		if(velX < -2.0f){
			velX = -2.0f;
		}
		if(velX < 0 & !isFlipped){
			isFlipped = true;
		}
		if(velX > 0 & isFlipped){
			isFlipped = false;
		}
		this.posX += delta * velX;	
	}
	
	public void render(double delta){
		Render.render(sprite, this, Color.white, isFlipped);
		//Render.render()
	}

	public float getPreviousLeftEdge(){ return lastX; }
	public float getPreviousRightEdge(){ return lastX + sizeX; }
	public float getPreviousTopEdge(){ return lastY; }
	public float getPreviousBottomEdge(){ return lastY + sizeY; }
}
