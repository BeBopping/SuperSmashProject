package eecs285.proj4.game;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.game.fighter.Fighter;
import eecs285.proj4.game.fighter.FighterA;
import eecs285.proj4.game.fighter.FighterTrait;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class ScreenPlayerSelect implements GameState {
	int enters;
	
	private Window window;
	private Texture background;
	private TrueTypeFont titleFont;
	
	private ArrayList<SelectableFighter> selectableFighter;
	private ArrayList<Controller> controllers;
	private ArrayList<PlayerInfoVis> playerInfoVis;
	BattleInfo battleInfo;
	
	public ScreenPlayerSelect(){
		window = new Window(0.0f, 100.0f, 0.0f, 100.0f);
		battleInfo = new BattleInfo();
		
		background = Assets.GetTexture("title_screen_background"); 
		titleFont = Assets.GetFont("title");
	}
	
	public void onActivate(){ }//enters = 0; }
	public void onDeactivate(){}

	public void getInput(double delta){
		int inputType = 0;
		Controller con = null;
		
		// Keyboard
		boolean tempKeyPress = Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		if(tempKeyPress){
			enters++;
			
			inputType = 1;
		}
		
		// Controller
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		// Key Up
		tempKeyPress = false;
		for(int i=0; i<controllers.length; i++){
			controllers[i].poll();
			Component component;
			
			// A
			component = controllers[i].getComponent(Identifier.Button._0);
			if(component != null){
				if(component.getPollData() >= 0.75){
					tempKeyPress = true;
					System.out.println(component.getName());
				}
			}
			
			// Start
			component = controllers[i].getComponent(Identifier.Button._7);
			if(component != null){
				if(component.getPollData() >= 0.75){
					tempKeyPress = true;
					System.out.println(component.getName());
				}
			}
			
			if(tempKeyPress){
				tempKeyPress = true;
				inputType = 2;
				con = controllers[i];
				
				enters++;
			}
		}
		
		
		if(enters >=2){
			if(inputType == 0){
				enters = 0;
				return;
			}
			
			// TODO Move this to when we actually want to select a level.
			battleInfo.setStock(5);
			battleInfo.setMinutes(2);
			Fighter[] fighters = new Fighter[4]; //8
			fighters[0] = new FighterA(FighterTrait.Normal);
			fighters[0].SetInput(inputType == 1? new InputKeyboard() : new InputXbox360(con));
			fighters[1] = new FighterA(FighterTrait.Normal);
			fighters[1].SetInput(inputType == 1? new InputKeyboard() : new InputXbox360(con));
			fighters[2] = new FighterA(FighterTrait.Normal);
			fighters[2].SetInput(inputType == 1? new InputKeyboard() : new InputXbox360(con));
			fighters[3] = new FighterA(FighterTrait.Normal);
			fighters[3].SetInput(inputType == 1? new InputKeyboard() : new InputXbox360(con));
			
			//fighters[4] = new FighterA(FighterTrait.Normal);
			//fighters[4].SetInput(inputType == 1? new InputKeyboard() : new InputXbox(con));
			//fighters[5] = new FighterA(FighterTrait.Normal);
			//fighters[5].SetInput(inputType == 1? new InputKeyboard() : new InputXbox(con));
			//fighters[6] = new FighterA(FighterTrait.Normal);
			//fighters[6].SetInput(inputType == 1? new InputKeyboard() : new InputXbox(con));
			//fighters[7] = new FighterA(FighterTrait.Normal);
			//fighters[7].SetInput(inputType == 1? new InputKeyboard() : new InputXbox(con));
			
			battleInfo.setFighters(fighters);
			Game.pushGameState(new ScreenLevelSelect(battleInfo));
		}
	}

	public void step(double delta){

	}

	public void prerender(double delta){}
	
	public void render(double delta){
		Render.renderBackground(background);
		
		Render.render(titleFont, window, "Player Select", 
				window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
				window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
	}
}