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

public class ScreenStartMenu extends ScreenMenu {
	private Texture background;
	
	public ScreenStartMenu(){
		super(new Window(0.0f, 100.0f, 0.0f, 100.0f));
		ArrayList<SelectableObject> menuItems = new ArrayList<SelectableObject>();
		menuItems.add(new StartItem(25.0f, 75.0f, 10.0f, 30.0f));
		menuItems.add(new StartItem(30.0f, 70.0f, 40.0f, 50.0f));
		menuItems.add(new StartItem(10.0f, 30.0f, 55.0f, 65.0f));
		menuItems.add(new StartItem(50.0f, 90.0f, 70.0f, 95.0f));
		
		initMenuItems(menuItems);
		
		background = GameAssets.GetTexture("title_screen_background");
	}

	public void onActivate(){
		super.onActivate();
		Game.setMusic(GameAssets.GetMusic("music_menu"));
	}
	
	public void render(double delta){
		Render.renderBackground(background);
		
		super.render(delta);
	}
	
	// Menu items:
	class StartItem extends SelectableObject{
		Texture highLight;
		TrueTypeFont font;
		boolean firstFrame;
		
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
			
			Render.render(font, window, "Test String", getCenterX(), getCenterY(), getSizeY()*0.5f, 0.5f, 0.5f, Color.blue);
		}
	}
}