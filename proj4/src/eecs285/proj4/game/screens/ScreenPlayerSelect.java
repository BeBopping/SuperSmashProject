package eecs285.proj4.game.screens;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Battle;
import eecs285.proj4.game.BattleInfo;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.PlayerInfoVis;
import eecs285.proj4.game.SelectableFighter;
import eecs285.proj4.game.fighter.Fighter;
import eecs285.proj4.game.fighter.FighterMario;
import eecs285.proj4.game.fighter.FighterTrait;
import eecs285.proj4.game.input.Input;
import eecs285.proj4.game.input.InputDetector;
import eecs285.proj4.game.input.InputKeyboard;
import eecs285.proj4.game.input.InputXbox360;
import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.UtilObject;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class ScreenPlayerSelect implements GameState {
	private Window window;
	private Texture background;
	private TrueTypeFont titleFont;
	
	private Input[] controllers;
	private ArrayList<SelectableFighter> selectableFighters;
	private ArrayList<Input> activePlayers;
	private ArrayList<Pointer> pointers;
	private ArrayList<PlayerInfoVis> playerInfoVis;
	private BattleInfo battleInfo;
	private UtilObject stockArea;
	private UtilObject timeArea;
	private UtilObject startArea;
	private Color textColor;
	
	public ScreenPlayerSelect(){
		float aspectRatio = (float)DisplayInfo.GetWidth() / (float) DisplayInfo.GetHeight();
		
		if(aspectRatio >= 1.0f){
			window = new Window(0.0f, 100.0f, 
								50.0f - (50.0f / aspectRatio), 
								50.0f + (50.0f / aspectRatio));
		}
		else{
			window = new Window(50.0f - (aspectRatio / 50.0f), 
								50.0f + (aspectRatio / 50.0f), 
								0.0f, 100.0f);
		}
		
		//window = new Window(0.0f, 100.0f, 0.0f, 100.0f);
		battleInfo = new BattleInfo();
		battleInfo.setMinutes(5);
		battleInfo.setStock(3);
		textColor = new Color(0.75f, 0.1f, 0.1f);
		
		stockArea = new UtilObject(70.0f, 82.0f, 20.0f, 25.0f);
		timeArea = new UtilObject(70.0f, 82.0f, 30.0f, 35.0f);
		startArea = new UtilObject(60.0f, 90.0f, 40.0f, 50.0f);
		
		background = Assets.GetTexture("title_screen_background"); 
		titleFont = Assets.GetFont("title");
		
		controllers = InputDetector.getAllInputDevices();
		selectableFighters = new ArrayList<SelectableFighter>();
		activePlayers = new ArrayList<Input>();
		pointers = new ArrayList<Pointer>();
		playerInfoVis = new ArrayList<PlayerInfoVis>();
		
		// Add selectable fighters
		selectableFighters.add(new SelectableFighter("Mario", Assets.GetSprite("fighter_mario"), 10.0f, 25.0f, 20.0f, 35.0f));
		selectableFighters.add(new SelectableFighter("Mario", Assets.GetSprite("fighter_mario"), 30.0f, 45.0f, 20.0f, 35.0f));
		selectableFighters.add(new SelectableFighter("Mario", Assets.GetSprite("fighter_mario"), 10.0f, 25.0f, 40.0f, 55.0f));
		selectableFighters.add(new SelectableFighter("Mario", Assets.GetSprite("fighter_mario"), 30.0f, 45.0f, 40.0f, 55.0f));
	}
	
	public void onActivate(){ }//enters = 0; }
	public void onDeactivate(){}

	public void getInput(double delta){
		
		// Check for new players
		for(Input input : controllers){
			input.getInput();
			
			// Check if player is trying to join
			if(input.menuSelect && !input.menuSelectLast){
				
				// Add a new player if they are not already part of the list
				boolean exists = false;
				for(Input active : activePlayers){
					if(active == input){
						exists = true;
					}
				}
				
				// Add them as a new player
				if(!exists){
					int playerNum = activePlayers.size();
					
					if(playerNum < 8){
						activePlayers.add(input);
						pointers.add(new Pointer(	input, 
													window,
													Battle.PLAYER_COLORS[playerNum],
													window.getCenterX() + ((float)playerNum - 3.5f) * 10.0f,
													window.getCenterY() + 10.0f));
						playerInfoVis.add(new PlayerInfoVis());
					}
				}
			}
			
			// Check if player is trying to back out.
			if(input.menuBack && !input.menuBackLast){
				if(activePlayers.isEmpty()){
					Game.popGameState();
				}
				
				// Remove a player if they are already part of the list
				for(int i=0; i<activePlayers.size(); ++i){
					if(activePlayers.get(i) == input){
						activePlayers.remove(i);
						pointers.remove(i);
						playerInfoVis.remove(i);
						--i;
					}
				}
			}
		}
	}

	public void step(double delta){
		for(Pointer pointer : pointers){
			pointer.step(delta);
		}
		
		for(SelectableFighter fighter : selectableFighters){
			fighter.step(delta);
		}
		
		//Check for selecting player
		for(int i=0; i<activePlayers.size(); i++){
			Pointer pointer = pointers.get(i);
			
			for(SelectableFighter fighter : selectableFighters){
				if(pointer.getLeftEdge() > fighter.getLeftEdge() && pointer.getLeftEdge() < fighter.getRightEdge()
				&& pointer.getTopEdge() > fighter.getTopEdge() && pointer.getTopEdge() < fighter.getBottomEdge()){
					Input input = activePlayers.get(i);
					
					fighter.setHover();
					
					if(input.menuSelect && !input.menuSelectLast){
						fighter.setClick();
					}
				}
			}
			
			// Change stock
			if(pointer.getLeftEdge() > stockArea.getLeftEdge() && pointer.getLeftEdge() < stockArea.getRightEdge()
			&& pointer.getTopEdge() > stockArea.getTopEdge() && pointer.getTopEdge() < stockArea.getBottomEdge()){
				Input input = activePlayers.get(i);
				
				if(input.menuSelect && !input.menuSelectLast){
					int stock = battleInfo.getStock() + 1;
					if(stock > 10){
						stock = 0;
					}
					
					battleInfo.setStock(stock);
				}
			}
			
			// Change time
			if(pointer.getLeftEdge() > timeArea.getLeftEdge() && pointer.getLeftEdge() < timeArea.getRightEdge()
			&& pointer.getTopEdge() > timeArea.getTopEdge() && pointer.getTopEdge() < timeArea.getBottomEdge()){
				Input input = activePlayers.get(i);
				
				if(input.menuSelect && !input.menuSelectLast){
					int time = battleInfo.getMinutes() + 1;
					if(time > 10){
						time = 0;
					}
					
					battleInfo.setMinutes(time);
				}
			}
			
			// Start game
			if(pointer.getLeftEdge() > startArea.getLeftEdge() && pointer.getLeftEdge() < startArea.getRightEdge()
			&& pointer.getTopEdge() > startArea.getTopEdge() && pointer.getTopEdge() < startArea.getBottomEdge()){
				Input input = activePlayers.get(i);
				
				if(input.menuSelect && !input.menuSelectLast){
					int numPlayers = activePlayers.size();
					
					Fighter[] fighters = new Fighter[numPlayers]; //8
					for(int j=0; j<numPlayers; ++j){
						fighters[j] = new FighterMario(FighterTrait.Normal);
						fighters[j].SetInput(activePlayers.get(j));
					}

					battleInfo.setFighters(fighters);
					Game.pushGameState(new ScreenLevelSelect(battleInfo));
					return;
				}
			}
		}
	}

	public void prerender(double delta){}
	
	public void render(double delta){
		window.setupRender();
		
		Render.renderBackground(background);
		
		Render.render(titleFont, window, "Player Select", 
				window.getCenterX(), window.getTop() + 1.0f, 
				window.getSizeY()/16.0f, 0.5f, 0.0f, Color.black);
		
		// Render selectable fighters
		for(SelectableFighter fighter : selectableFighters){
			fighter.render(delta);
		}
		
		// Render Stock settings
		Render.render(titleFont, window, "Stock: ", 
				stockArea.getLeftEdge() - 5.0f, stockArea.getTopEdge(), 
				stockArea.getSizeY(), 1.0f, 0.0f, Color.black);
		
		Render.render(Assets.GetTexture("white"), stockArea, Color.gray);
		
		String stockString = "None";
		int stock = battleInfo.getStock();
		if(stock > 0) stockString = String.valueOf(stock);
		Render.render(titleFont, window, stockString,
				stockArea.getLeftEdge() + 1.0f, stockArea.getTopEdge(), 
				stockArea.getSizeY(), 0.0f, 0.0f, textColor);
		
		// Render Time settings
		Render.render(titleFont, window, "Time: ", 
				stockArea.getLeftEdge() - 5.0f, timeArea.getTopEdge(), 
				stockArea.getSizeY(), 1.0f, 0.0f, Color.black);

		Render.render(Assets.GetTexture("white"), timeArea, Color.gray);
		
		String timeString = "None";
		int time = battleInfo.getMinutes();
		if(time > 0) timeString = String.valueOf(time) + ":00";
		Render.render(titleFont, window, timeString,
				stockArea.getLeftEdge() + 1.0f, timeArea.getTopEdge(), 
				stockArea.getSizeY(), 0.0f, 0.0f, textColor);

		
		// Render start button
		Render.render(Assets.GetTexture("white"), startArea, Color.red);
		Render.render(titleFont, window, "Start!",
				startArea.getCenterX(), startArea.getCenterY(), 
				startArea.getSizeY(), 0.65f, 0.5f, Color.black);
		
		// Display pointers
		for(Pointer pointer : pointers){
			pointer.render(delta);
		}
	}
}