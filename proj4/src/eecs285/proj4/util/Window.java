package eecs285.proj4.util;

import static org.lwjgl.opengl.GL11.*;

public class Window {

	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;
	private float zNear;
	private float zFar;
	
	public Window(){
		this(0.0f, 1.0f, 0.0f, 1.0f);
	}
	
	public Window(float left, float right, float top, float bottom){
		sizeX = right - left;
		sizeY = bottom - top;
		posX = left;
		posY = top;
		zNear = 1.0f;
		zFar = -1.0f;
	}
	
	// Progress camera position and size
	public void step(double delta){
		// Update window values
		
	}
	
	// Set up camera position for this frame
	public void setupRender(){
		// Set world position and size
		glLoadIdentity();
		glOrtho(0.0f, sizeX,
				sizeY, 0.0f,
				zNear, zFar);
		glTranslatef(-posX, -posY, 0);
	}

	// Set information
	public void setCenter(float centerX, float centerY){
		posX = centerX - sizeX/2.0f;
		posY = centerY - sizeY/2.0f;
	}
	
	public void setCorner(float cornerX, float cornerY){
		posX = cornerX;
		posY = cornerY;
	}
	
	public void setSize(float width, float height){
		sizeX = width;
		sizeY = height;
	}
	
	// Get information
	public float getCenterX(){ return posX + sizeX/2.0f; }
	public float getCenterY(){ return posY + sizeY/2.0f; }
	public float getLeft(){ return posX; }
	public float getRight(){ return posX + sizeX; }
	public float getTop(){ return posY; }
	public float getBottom(){ return posY + sizeY; }
	public float getSizeX(){ return sizeX; }
	public float getSizeY(){ return sizeY; }
	
	// Do special actions to camera
	public void doShake(float percentShake, double seconds){
		
	}
	
	public void doZoom(float percent, double seconds){
		
	}
	
	public void doZoom(float width, float height, double seconds){
		
	}
	
	public void doMoveCenter(float centerX, float centerY, double seconds){
		
	}
}