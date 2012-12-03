package eecs285.proj4.game.levels;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class LevelObjectRepeating2D extends LevelObject {
	private Sprite sprite;
	private float imageSizeX;
	private float imageSizeY;
	
	public LevelObjectRepeating2D(Sprite sprite, float left, float right, float top, float bottom, float imageSizeX, float imageSizeY){
		super(left, right, top, bottom);
		
		this.sprite = sprite;
		this.imageSizeX = imageSizeX;
		this.imageSizeY = imageSizeY;
	}

	public void step(double delta){}
	
	public void render(double delta){
		for(float left=getLeftEdge(); left < getRightEdge(); left += imageSizeX){
			final float right = Math.min(left + imageSizeX, getRightEdge());
			
			for(float top=getTopEdge(); top < getBottomEdge(); top += imageSizeY){
				final float bottom = Math.min(top + imageSizeY, getBottomEdge());
				
				Render.render(sprite, left, right, top, bottom);
			}
		}
	}
}