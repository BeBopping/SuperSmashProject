package eecs285.proj4.util;

import static org.lwjgl.opengl.GL11.*;

public class Window {

	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;
	private float zNear;
	private float zFar;
	
	private float targetCenterX;
	private float targetCenterY;
	private double moveTimeLeft;

	private float targetSizeX;
	private float targetSizeY;
	private double zoomTimeLeft;
	
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
		if(zoomTimeLeft > 0.0f){
			if(zoomTimeLeft < delta){
				zoomTimeLeft = delta;
			}
			
			final double changeX = (double)(targetSizeX - sizeX) * (delta / zoomTimeLeft);
			final double changeY = (double)(targetSizeY - sizeY) * (delta / zoomTimeLeft);
			
			posX -= changeX * 0.5f;
			posY -= changeY * 0.5f;
			sizeX += changeX;
			sizeY += changeY;
			
			zoomTimeLeft -= delta;
		}
		
		if(moveTimeLeft > 0.0f){
			if(moveTimeLeft < delta){
				moveTimeLeft = delta;
			}
		
			final double changeX = (double)(targetCenterX - (posX + sizeX*0.5f)) * (delta / moveTimeLeft);
			final double changeY = (double)(targetCenterY - (posY + sizeY*0.5f)) * (delta / moveTimeLeft);
			posX += changeX;
			posY += changeY;
			
			moveTimeLeft -= delta;
		}
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
		posX = centerX - sizeX*0.5f;
		posY = centerY - sizeY*0.5f;
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
	public float getCenterX(){ return posX + sizeX*0.5f; }
	public float getCenterY(){ return posY + sizeY*0.5f; }
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
		targetSizeX = sizeX * percent;
		targetSizeY = sizeY * percent;
		zoomTimeLeft = seconds;
	}
	
	public void doZoom(float width, float height, double seconds){
		float percentX = width / sizeX;
		float percentY = height / sizeY;
		
		doZoom(Math.max(percentX,  percentY), seconds);
	}
	
	public void doMoveCenter(float centerX, float centerY, double seconds){
		targetCenterX = centerX;
		targetCenterY = centerY;
		moveTimeLeft = seconds;
	}
}