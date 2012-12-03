package eecs285.proj4.game.screens;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.game.GameAssets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.SelectableObject;
import eecs285.proj4.game.screens.ScreenMenu;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class ScreenPause extends ScreenMenu {
	//private Texture background;
	private TrueTypeFont titleFont;
	private GameState previouseState;
	
	//private BattleInfo battleInfo;
	
	public ScreenPause(){
		super(new Window(0.0f, 100.0f, 0.0f, 100.0f));
		
		ArrayList<SelectableObject> menuItems = new ArrayList<SelectableObject>();
		menuItems.add(new ContinueItem(25.0f, 75.0f, 30.0f, 45.0f));
		menuItems.add(new ExitItem(25.0f, 75.0f, 50.0f, 65.0f));
	
		initMenuItems(menuItems);
		
		//background = GameAssets.GetTexture("level_screen_background");
		titleFont = GameAssets.GetFont("title");
		
		previouseState = Game.popGameState();
	}

	public void render(double delta){
		//Render.renderBackground(background);
		previouseState.prerender(delta);
		previouseState.render(delta);
		
		Render.render(titleFont, window, "Pause Menu", 
				window.getCenterX(), window.getTop() + 1.0f, 
				window.getSizeY()/16.0f, 0.5f, 0.0f, Color.red);
		
		super.render(delta);
	}
	
	// Menu items:
	class ContinueItem extends SelectableObject{
		Texture highLight;
		TrueTypeFont font;
		
		public ContinueItem(float left, float right, float top, float bottom) {
			super(left, right, top, bottom);
			highLight = GameAssets.GetTexture("select");
			font = GameAssets.GetFont("times_bold");
		}

		public void step(double delta){
			if(currentState == State.BEGIN_MOUSE_PRESS){
				Game.popGameState();
				Game.pushGameState(previouseState);
			}
		}

		public void render(double delta){
			if(currentState != State.NONE){
				Render.render(highLight, this);
			}
			
			Render.render(font, window, "Continue", getCenterX(), getCenterY(), getSizeY()*0.5f, 0.5f, 0.5f, Color.blue);
		}
	}
	
	class ExitItem extends SelectableObject{
		Texture highLight;
		TrueTypeFont font;
		
		public ExitItem(float left, float right, float top, float bottom) {
			super(left, right, top, bottom);
			highLight = GameAssets.GetTexture("select");
			font = GameAssets.GetFont("times_bold");
		}

		public void step(double delta){
			if(currentState == State.BEGIN_MOUSE_PRESS){
				Game.popGameState();
			}
		}

		public void render(double delta){
			if(currentState != State.NONE){
				Render.render(highLight, this);
			}
			
			Render.render(font, window, "Exit", getCenterX(), getCenterY(), getSizeY()*0.5f, 0.5f, 0.5f, Color.blue);
		}
	}
}