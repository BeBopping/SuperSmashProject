package eecs285.proj4.util;

import org.newdawn.slick.opengl.Texture;

public class Sprite {
	private Texture texture;
	private float left;
	private float right;
	private float top;
	private float bottom;
	
	public Sprite(Texture texture, float leftEdge, float rightEdge, float topEdge, float bottomEdge){
		this.texture = texture;
		left = leftEdge;
		right = rightEdge;
		top = topEdge;
		bottom = bottomEdge;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public float getLeft(){
		return left;
	}
	
	public float getRight(){
		return right;
	}
	
	public float getTop(){
		return top;
	}
	
	public float getBottom(){
		return bottom;
	}
	
	public float getPercentX(){
		return right - left;
	}
	
	public float getPercentY(){
		return bottom - top;
	}
}