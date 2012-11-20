package eecs285.proj4.util;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class Render {

	// ************************************************************************
	// Render Stuff with Sprite
	public static void render(Sprite sprite, UtilObject gameObject){
		render(sprite, gameObject.getLeftEdge(), gameObject.getRightEdge(), gameObject.getTopEdge(), gameObject.getBottomEdge());
	}
	
	public static void render(Sprite sprite, UtilObject gameObject, Color color){
		render(sprite, gameObject.getLeftEdge(), gameObject.getRightEdge(), gameObject.getTopEdge(), gameObject.getBottomEdge(), color);
	}
	
	public static void render(Sprite sprite, float left, float right, float top, float bottom){
		render(sprite, left, right, top, bottom, Color.white);
	}
	
	public static void render(Sprite sprite, float left, float right, float top, float bottom, Color color){
		color.bind();
		sprite.getTexture().bind(); // or GL11.glBind(texture.getTextureID());
		
		glBegin(GL_QUADS);
			glTexCoord2f(sprite.getLeft(),sprite.getTop());
			glVertex2f(left,top);
			glTexCoord2f(sprite.getRight(),sprite.getTop());
			glVertex2f(right,top);
			glTexCoord2f(sprite.getRight(),sprite.getBottom());
			glVertex2f(right,bottom);
			glTexCoord2f(sprite.getLeft(),sprite.getBottom());
			glVertex2f(left,bottom);
		glEnd();
	}

	// ************************************************************************
	// Render Stuff with Texture
	public static void render(Texture texture, UtilObject gameObject){
		render(texture, gameObject.getLeftEdge(), gameObject.getRightEdge(), gameObject.getTopEdge(), gameObject.getBottomEdge(), Color.white);
	}
	
	public static void render(Texture texture, UtilObject gameObject, Color color){
		render(texture, gameObject.getLeftEdge(), gameObject.getRightEdge(), gameObject.getTopEdge(), gameObject.getBottomEdge(), color);
	}
	
	public static void render(Texture texture, float left, float right, float top, float bottom){
		render(texture, left, right, top, bottom, Color.white);
	}
	
	public static void render(Texture texture, float left, float right, float top, float bottom, Color color){
		color.bind();
		texture.bind(); // or GL11.glBind(texture.getTextureID());
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(left,top);
			glTexCoord2f(1,0);
			glVertex2f(right,top);
			glTexCoord2f(1,1);
			glVertex2f(right,bottom);
			glTexCoord2f(0,1);
			glVertex2f(left,bottom);
		glEnd();
	}

	// ************************************************************************
	// Batch render
	public static void startBatchRender(Texture texture, Color color){
		color.bind();
		texture.bind();
		glBegin(GL_QUADS);
	}
	
	public static void stopBatchRender(){
		glEnd();
	}
	
	// Batch render sprite
	public static void batchRender(Sprite sprite, UtilObject gameObject){
		batchRender(sprite, gameObject.getLeftEdge(), gameObject.getRightEdge(), gameObject.getTopEdge(), gameObject.getBottomEdge());
	}

	public static void batchRender(Sprite sprite, float left, float right, float top, float bottom){
		glTexCoord2f(sprite.getLeft(),sprite.getTop());
		glVertex2f(left,top);
		glTexCoord2f(sprite.getRight(),sprite.getTop());
		glVertex2f(right,top);
		glTexCoord2f(sprite.getRight(),sprite.getBottom());
		glVertex2f(right,bottom);
		glTexCoord2f(sprite.getLeft(),sprite.getBottom());
		glVertex2f(left,bottom);
	}
	
	// Batch render texture
	public static void batchRender(Texture texture, UtilObject gameObject){
		batchRender(texture, gameObject.getLeftEdge(), gameObject.getRightEdge(), gameObject.getTopEdge(), gameObject.getBottomEdge());
	}

	public static void batchRender(Texture texture, float left, float right, float top, float bottom){
		glTexCoord2f(0,0);
		glVertex2f(left,top);
		glTexCoord2f(1,0);
		glVertex2f(right,top);
		glTexCoord2f(1,1);
		glVertex2f(right,bottom);
		glTexCoord2f(0,1);
		glVertex2f(left,bottom);
	}
	
	// ************************************************************************
	// Batch render
	// Font-type , Window , text , position to Align at , height of text on the screen , part of text to align to position.
	// horizontal = 0.5, vertical = 0.0f would put the top of the horizontal center at the point (pointX, pointY) 
	public static void render(TrueTypeFont font, Window window, String text,
								float posX, float posY, float height, 
								float horizontal, float vertical, Color color){
		// TODO? It might be possible to simplify this
		float textHeight = font.getLineHeight();
		float textWidth = font.getWidth(text);
		
		float drawHeight = textHeight * (window.getSizeY() / height);
		float drawWidth = drawHeight * ((float)DisplayInfo.GetWidth() / (float)DisplayInfo.GetHeight());
		
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0.0f, drawWidth,
				drawHeight, 0.0f,
				1, -1);
		
		float left = posX - (horizontal * height * (textWidth / textHeight) * (drawHeight / drawWidth));
		float top = posY - (vertical * height);
		float percentX = (left - window.getLeft()) / window.getSizeX();
		float percentY = (top - window.getTop()) / window.getSizeY();
		
		font.drawString(percentX * drawWidth, percentY * drawHeight, text, color);
		
		glPopMatrix();
	}
	
	// ************************************************************************
	// Render background using the Window class
	public static void renderBackground(Texture texture){
		Color.white.bind();
		texture.bind(); // or GL11.glBind(texture.getTextureID());
		
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0.0f, 1.0f,
				1.0f, 0.0f,
				1, -1);
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2f(0,0);
			glTexCoord2f(1,0);
			glVertex2f(1,0);
			glTexCoord2f(1,1);
			glVertex2f(1,1);
			glTexCoord2f(0,1);
			glVertex2f(0,1);
			
			//glTexCoord2f(1,0);
			//glVertex2f(right,top);
			//glTexCoord2f(1,1);
			//glVertex2f(right,bottom);
			//glTexCoord2f(0,1);
			//glVertex2f(left,bottom);
		glEnd();
		
		glPopMatrix();
	}
	
	public static void renderBackground(Sprite sprite){
		Color.white.bind();
		sprite.getTexture().bind(); // or GL11.glBind(texture.getTextureID());
		
		final float sprite_left = sprite.getLeft();
		final float sprite_right = sprite.getRight();
		final float sprite_top = sprite.getTop();
		final float sprite_bottom = sprite.getBottom();
		
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0.0f, 1.0f,
				1.0f, 0.0f,
				1, -1);
		
		glBegin(GL_QUADS);
			glTexCoord2f(sprite_left,sprite_top);
			glVertex2f(0,0);
			glTexCoord2f(sprite_right,sprite_top);
			glVertex2f(1,0);
			glTexCoord2f(sprite_right,sprite_bottom);
			glVertex2f(1,1);
			glTexCoord2f(sprite_left,sprite_bottom);
			glVertex2f(0,1);
		glEnd();

		glPopMatrix();
	}
	
	private Render(){}
}