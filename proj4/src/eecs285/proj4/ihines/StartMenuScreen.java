package eecs285.proj4.ihines;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.MenuScreen;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.SelectableObject;
import eecs285.proj4.util.Render;

public class StartMenuScreen extends MenuScreen {
	private Texture background;
	
	public StartMenuScreen(){
		super(new Window(0.0f, 100.0f, 0.0f, 100.0f));
		ArrayList<SelectableObject> menuItems = new ArrayList<SelectableObject>();
		menuItems.add(new StartItem(25.0f, 75.0f, 10.0f, 30.0f));
		menuItems.add(new StartItem(30.0f, 70.0f, 40.0f, 50.0f));
		menuItems.add(new StartItem(10.0f, 30.0f, 55.0f, 65.0f));
		menuItems.add(new StartItem(50.0f, 90.0f, 70.0f, 95.0f));
		
		initMenuItems(menuItems);
		
		background = Assets.GetTexture("title_screen_background");
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
			highLight = Assets.GetTexture("select");
			font = Assets.GetFont("times_bold");
		}

		public void step(double delta){
			if(currentState == State.BEGIN_MOUSE_PRESS){
				Game.pushGameState(new TitleScreen());
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