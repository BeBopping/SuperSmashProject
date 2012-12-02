package eecs285.proj4.game.screens;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.MovingObject;
import eecs285.proj4.game.input.Input;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.Window;

public class Pointer extends MovingObject {
	private static final float MAX_VEL = 30.0f;
	private static final float MAX_ACCEL = 1.5f;
	
	private Input input;
	private Window window;
	private Texture texture;
	private Color color;
	
	public Pointer(Input input, Window window, Color color, float left, float top) {
		super(left, left + 4.0f, top, top + 4.0f);
		
		this.input = input;
		this.window = window;
		this.color = color;
		
		texture = Assets.GetTexture("pointer");
	}
	
	public void step(double delta){
		// Assume input has already been polled
		if(input.usesAnalogStick()){
			velX = (float)((input.xAxis * MAX_VEL) * delta);
			velY = (float)((input.yAxis * MAX_VEL) * delta);
		}
		else{
			if(Math.abs(input.xAxis) >= 0.75){
				if(velX * input.xAxis < 0.0f){
					velX = 0.0f;
				}
				else{
					velX += input.xAxis * delta * MAX_ACCEL;
				}
			}
			else{
				velX *= 0.85f;
			}

			if(Math.abs(input.yAxis) >= 0.75){
				if(velY * input.yAxis < 0.0f){
					velY = 0.0f;
				}
				else{
					velY += input.yAxis * delta * MAX_ACCEL;
				}
			}
			else{
				velY *= 0.85f;
			}
		}
		
		if(velX > MAX_VEL) velX = MAX_VEL;
		if(velX < -MAX_VEL) velX = -MAX_VEL;
		if(velY > MAX_VEL) velY = MAX_VEL;
		if(velY < -MAX_VEL) velY = -MAX_VEL;
		
		posX += velX;
		posY += velY;

		if(posX < window.getLeft()) posX = window.getLeft();
		if(posX > window.getRight() - getSizeX()) posX = window.getRight() - getSizeX();
		if(posY < window.getTop()) posY = window.getTop();
		if(posY > window.getBottom() - getSizeY()) posY = window.getBottom() - getSizeY();
	}
	
	public void render(double delta){
		Render.render(texture, this, color);
	}
}