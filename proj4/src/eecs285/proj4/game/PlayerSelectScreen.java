package eecs285.proj4.game;

import java.util.ArrayList;

import net.java.games.input.Controller;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class PlayerSelectScreen implements GameState {
	private Window window;
	private Texture background;
	private TrueTypeFont titleFont;
	
	private ArrayList<SelectableFighter> selectableFighter;
	private ArrayList<Controller> controllers;
	private ArrayList<PlayerInfoVis> playerInfoVis;
	BattleInfo battleInfo;
	
	public PlayerSelectScreen(){
		window = new Window(0.0f, 100.0f, 0.0f, 100.0f);
		battleInfo = new BattleInfo();
		
		background = Assets.GetTexture("title_screen_background"); 
		titleFont = Assets.GetFont("title");
	}
	
	public void onActivate(){}
	public void onDeactivate(){}

	public void getInput(double delta){}

	public void step(double delta){

		// TODO Move this to when we actually want to select a level.
		battleInfo.setStock(5);
		battleInfo.setMinutes(2);
		Fighter[] fighters = new Fighter[4];
		fighters[0] = new FighterA(FighterTrait.Normal, 15, 75);
		fighters[1] = new FighterA(FighterTrait.Normal, 25, 65);
		fighters[2] = new FighterA(FighterTrait.Normal, 35, 55);
		fighters[3] = new FighterA(FighterTrait.Normal, 45, 45);
		battleInfo.setFighters(fighters);
		Game.pushGameState(new LevelSelectScreen(battleInfo));
	}

	public void prerender(double delta){}
	
	public void render(double delta){
		Render.renderBackground(background);
		
		Render.render(titleFont, window, "Player Select", 
				window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
				window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
	}
}