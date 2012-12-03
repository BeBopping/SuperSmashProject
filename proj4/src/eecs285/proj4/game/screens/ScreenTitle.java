package eecs285.proj4.game.screens;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.game.GameAssets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.SelectableObject;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class ScreenTitle extends ScreenMenu {
	private Texture background;
	private TrueTypeFont titleFont;
	
	public ScreenTitle(){
		super(new Window(0.0f, 100.0f, 0.0f, 100.0f));
		ArrayList<SelectableObject> menuItems = new ArrayList<SelectableObject>();
		menuItems.add(new StartItem(35.0f, 65.0f, 85.0f, 95.0f));
		
		initMenuItems(menuItems);
		
		background = GameAssets.GetTexture("title_screen_background"); 
		titleFont = GameAssets.GetFont("title");
	}
	
	public void render(double delta){
		Render.renderBackground(background);
		
		//Render.render(titleFont, window, "Title of Game", 
		//		window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
		//		window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
		
		super.render(delta);
	}
	
	// Menu items:
	class StartItem extends SelectableObject{
		Texture highLight;
		TrueTypeFont font;
		
		public StartItem(float left, float right, float top, float bottom) {
			super(left, right, top, bottom);
			highLight = GameAssets.GetTexture("select");
			font = GameAssets.GetFont("times_bold");
		}

		public void step(double delta){
			if(currentState == State.BEGIN_MOUSE_PRESS){
				Game.pushGameState(new ScreenPlayerSelect());
			}
		}

		public void render(double delta){
			if(currentState != State.NONE){
				Render.render(highLight, this);
			}
			
			Render.render(font, window, "Press Start", getCenterX(), getCenterY(), getSizeY()*0.5f, 0.5f, 0.5f, Color.white);
		}
	}
}