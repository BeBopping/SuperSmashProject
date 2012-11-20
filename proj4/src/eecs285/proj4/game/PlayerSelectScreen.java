package eecs285.proj4.game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.SelectableObject;
import eecs285.proj4.util.Render;

public class PlayerSelectScreen implements GameState {
	private Window window;
	private Texture background;
	private TrueTypeFont titleFont;
	
	public PlayerSelectScreen(){
		window = new Window(0.0f, 100.0f, 0.0f, 100.0f);
		
		background = Assets.GetTexture("title_screen_background"); 
		titleFont = Assets.GetFont("title");
	}
	
	public void onActivate(){}
	public void onDeactivate(){}

	public void getInput(double delta){}

	public void step(double delta){}

	public void prerender(double delta){}
	
	public void render(double delta){
		Render.renderBackground(background);
		
		Render.render(titleFont, window, "Player Select", 
				window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
				window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
	}
}