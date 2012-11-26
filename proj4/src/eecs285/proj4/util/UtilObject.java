package eecs285.proj4.util;

public abstract class UtilObject {
	protected float posX;
	protected float posY;
	protected float sizeX;
	protected float sizeY;
	
	public UtilObject(float left, float right, float top, float bottom){
		posX = left;
		posY = top;
		sizeX = right - left;
		sizeY = bottom - top;
	}
	
	public float getLeftEdge(){ return posX; }
	public float getRightEdge(){ return posX + sizeX; }
	public float getTopEdge(){ return posY; }
	public float getBottomEdge(){ return posY + sizeY; }
	public float getCenterX(){ return posX + sizeX*0.5f; }
	public float getCenterY(){ return posY + sizeY*0.5f; }
	public float getSizeX(){ return sizeX; }
	public float getSizeY(){ return sizeY; }
	
	public abstract void step(double delta);
	public abstract void render(double delta);
}