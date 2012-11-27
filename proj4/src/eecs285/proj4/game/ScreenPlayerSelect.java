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
		Input input = null;
		
		// Keyboard
		boolean tempKeyPress = Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		if(tempKeyPress){
			enters++;
			
			input = new InputKeyboard();
		}
		
		// Controller
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		// Key Up
		tempKeyPress = false;
		for(int i=0; i<controllers.length; i++){
			
			Component component = controllers[i].getComponent(Identifier.Button.A);
			if(component != null){
				if(component.getPollData() >= 0.75){
					System.out.println(component.getName());
					tempKeyPress = true;
					input = new InputXbox(controllers[i]);
					
					enters++;
				}
			}
		}
		
		
		if(enters >=2){
			if(input == null){
				enters = 0;
				return;
			}
			
			// TODO Move this to when we actually want to select a level.
			battleInfo.setStock(5);
			battleInfo.setMinutes(2);
			Fighter[] fighters = new Fighter[4];
			fighters[0] = new FighterA(FighterTrait.Normal, 25, 75);
			fighters[0].SetInput(input);
			fighters[1] = new FighterA(FighterTrait.Normal, 35, 65);
			fighters[1].SetInput(input);
			fighters[2] = new FighterA(FighterTrait.Normal, 45, 55);
			fighters[2].SetInput(input);
			fighters[3] = new FighterA(FighterTrait.Normal, 65, 45);
			fighters[3].SetInput(input);
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