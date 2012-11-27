package eecs285.proj4.game.levels;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class LevelObjectRepeating extends LevelObject {
	Sprite sprite;
	Boolean horizontal;
	float ratioXOverY;
	
	public LevelObjectRepeating(Sprite sprite, float left, float right, float top, float bottom){
		super(left, right, top, bottom);
		
		this.sprite = sprite;
		
		float imageWidth = sprite.getPercentX() * sprite.getTexture().getImageWidth();
		float imageHeight = sprite.getPercentY() * sprite.getTexture().getImageHeight();
		
		if(imageWidth / imageHeight <= this.getSizeX() / this.getSizeY()){
			horizontal = true;
			//scale = this.getSizeY() / imageHeight;
		}
		else{
			horizontal = false;
			//scale = this.getSizeX() / imageWidth;
		}
		ratioXOverY = imageWidth / imageHeight;
	}

	public void step(double delta){}
	
	public void render(double delta){
		if(horizontal){
			//float imageWidth = sprite.getPercentX() * sprite.getTexture().getImageWidth();
			float width = ratioXOverY * getSizeY();
			
			for(float left=getLeftEdge(); left < getRightEdge(); left += width){//scale * imageWidth){
				final float right = Math.min(left + width, getRightEdge());
				
				Render.render(sprite, left, right, getTopEdge(), getBottomEdge());
			}
		}
		else{
			//float imageHeight = sprite.getPercentY() * sprite.getTexture().getImageHeight();
			float height = (getSizeX() / ratioXOverY);
			
			for(float top=getTopEdge(); top < getBottomEdge(); top += height){//scale * imageHeight){
				final float bottom = Math.min(top + height, getBottomEdge());
				
				Render.render(sprite, getLeftEdge(), getRightEdge(), top, bottom);
			}
		}
	}
}