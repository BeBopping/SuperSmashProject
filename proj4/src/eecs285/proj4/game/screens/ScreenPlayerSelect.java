package eecs285.proj4.game.screens;

import java.util.ArrayList;


import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.game.GameAssets;
import eecs285.proj4.game.Battle;
import eecs285.proj4.game.BattleInfo;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.fighter.Fighter;
import eecs285.proj4.game.fighter.FighterTrait;
import eecs285.proj4.game.input.Input;
import eecs285.proj4.game.input.InputDetector;
import eecs285.proj4.game.input.InputKeyboard;
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
	private ArrayList<String> playerFighters;
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
		
		battleInfo = new BattleInfo();
		battleInfo.setMinutes(5);
		battleInfo.setStock(3);
		textColor = new Color(0.75f, 0.1f, 0.1f);
		
		stockArea = new UtilObject(70.0f, 82.0f, 20.0f, 25.0f);
		timeArea = new UtilObject(70.0f, 82.0f, 30.0f, 35.0f);
		startArea = new UtilObject(60.0f, 90.0f, 40.0f, 50.0f);
		
		background = GameAssets.GetTexture("player_screen_background"); 
		titleFont = GameAssets.GetFont("title");
		
		controllers = InputDetector.getAllInputDevices();
		selectableFighters = new ArrayList<SelectableFighter>();
		activePlayers = new ArrayList<Input>();
		pointers = new ArrayList<Pointer>();
		playerFighters = new ArrayList<String>();
		
		// Add selectable fighters
		selectableFighters.add(new SelectableFighter("fighter_mario", GameAssets.GetSprite("fighter_mario"), 10.0f, 25.0f, 20.0f, 35.0f));
		selectableFighters.add(new SelectableFighter("fighter_sonic", GameAssets.GetSprite("fighter_sonic"), 30.0f, 45.0f, 20.0f, 35.0f));
		//selectableFighters.add(new SelectableFighter("fighter_rayman", GameAssets.GetSprite("fighter_rayman"), 10.0f, 25.0f, 40.0f, 55.0f));
		selectableFighters.add(new SelectableFighter("fighter_spyro", GameAssets.GetSprite("fighter_spyro"), 20.0f, 35.0f, 40.0f, 55.0f));
	}
	
	public void onActivate(){
		Game.setMusic(GameAssets.GetMusic("music_menu"));
	}
	
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
						playerFighters.add(new String());
					}
				}
			}
			
			// Check if player is trying to back out.
			if(input.menuBack && !input.menuBackLast){
				if(activePlayers.isEmpty()){
					Game.popGameState();
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
		
		for(int i=0; i<activePlayers.size(); i++){
			//Check for 
			if(activePlayers.get(i).menuBack && !activePlayers.get(i).menuBackLast){
				if(playerFighters.get(i).isEmpty()){
					activePlayers.remove(i);
					pointers.remove(i);
					playerFighters.remove(i);
					resetColor();
					--i;
					continue;
				}
				else{
					playerFighters.set(i, new String());
				}
			}
			
			//Check for selecting player
			Pointer pointer = pointers.get(i);
			for(SelectableFighter fighter : selectableFighters){
				if(pointer.getLeftEdge() > fighter.getLeftEdge() && pointer.getLeftEdge() < fighter.getRightEdge()
				&& pointer.getTopEdge() > fighter.getTopEdge() && pointer.getTopEdge() < fighter.getBottomEdge()){
					Input input = activePlayers.get(i);
					
					fighter.setHover(pointer.getColor());
					
					if(input.menuSelect && !input.menuSelectLast){
						fighter.setClick();
						playerFighters.set(i, fighter.getFighterName());
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
			if(readyToStart()){
				if(pointer.getLeftEdge() > startArea.getLeftEdge() && pointer.getLeftEdge() < startArea.getRightEdge()
				&& pointer.getTopEdge() > startArea.getTopEdge() && pointer.getTopEdge() < startArea.getBottomEdge()){
					Input input = activePlayers.get(i);
					
					if(input.menuSelect && !input.menuSelectLast){
						int numPlayers = activePlayers.size();
						
						Fighter[] fighters = new Fighter[numPlayers];
						
						for(int j=0; j<numPlayers; ++j){
							fighters[j] = GameAssets.GetFighter(playerFighters.get(j), FighterTrait.Normal);
							fighters[j].SetInput(activePlayers.get(j));
						}
	
						//for(int j=numPlayers; j<4; ++j){
						//	fighters[j] = GameAssets.GetFighter("fighter_mario", FighterTrait.Normal);
						//	fighters[j].SetInput(new InputKeyboard());
						//}
						
						battleInfo.setFighters(fighters);
						Game.pushGameState(new ScreenLevelSelect(battleInfo));
						return;
					}
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
				window.getSizeY()/16.0f, 0.5f, 0.0f, Color.blue);
		
		// Render selectable fighters
		for(SelectableFighter fighter : selectableFighters){
			fighter.render(delta);
		}
		
		// Render Stock settings
		Render.render(titleFont, window, "Stock: ", 
				stockArea.getLeftEdge() - 5.0f, stockArea.getTopEdge(), 
				stockArea.getSizeY(), 1.0f, 0.0f, Color.blue);
		
		Render.render(GameAssets.GetTexture("white"), stockArea, Color.gray);
		
		String stockString = "None";
		int stock = battleInfo.getStock();
		if(stock > 0) stockString = String.valueOf(stock);
		Render.render(titleFont, window, stockString,
				stockArea.getLeftEdge() + 1.0f, stockArea.getTopEdge(), 
				stockArea.getSizeY(), 0.0f, 0.0f, textColor);
		
		// Render Time settings
		Render.render(titleFont, window, "Time: ", 
				stockArea.getLeftEdge() - 5.0f, timeArea.getTopEdge(), 
				stockArea.getSizeY(), 1.0f, 0.0f, Color.blue);

		Render.render(GameAssets.GetTexture("white"), timeArea, Color.gray);
		
		String timeString = "None";
		int time = battleInfo.getMinutes();
		if(time > 0) timeString = String.valueOf(time) + ":00";
		Render.render(titleFont, window, timeString,
				stockArea.getLeftEdge() + 1.0f, timeArea.getTopEdge(), 
				stockArea.getSizeY(), 0.0f, 0.0f, textColor);

		// Display start button
		if(readyToStart()){
			Render.render(GameAssets.GetTexture("white"), startArea, Color.red);
			Render.render(titleFont, window, "Start!",
					startArea.getCenterX(), startArea.getCenterY(), 
					startArea.getSizeY(), 0.65f, 0.5f, Color.black);
		}
		
		// Display selected character...
		int numPlayers = activePlayers.size();
		final float maxWidth = 23.0f;
		final float separation = 2.0f;
		float width = Math.min((window.getSizeX() / (float)numPlayers) - separation, maxWidth);
		float pos = window.getLeft() + separation;
		if(numPlayers > 4){
			 pos = window.getCenterX() - (float)(numPlayers - 1)*(0.5f * width + 0.5f*separation) - width*0.5f;
		}
		for(int i=0; i<numPlayers; i++){
			String fighterName = "fighter_empty";
			if(!playerFighters.get(i).isEmpty()){
				fighterName = playerFighters.get(i);
			}
			
			Render.render(GameAssets.GetSprite(fighterName), pos, pos+width, 75 - width*0.5f, 75 + width*0.5f, pointers.get(i).getColor(), false);
			
			pos += width + separation;
		}
		
		// Display pointers
		for(Pointer pointer : pointers){
			pointer.render(delta);
		}
	}
	
	private boolean readyToStart(){
		if(activePlayers.size() < 2){
			return false;
		}
		
		boolean allPlayersReady = true;
		for(String str : playerFighters){
			if(str.isEmpty()){
				allPlayersReady = false;
			}
		}
		return allPlayersReady;
	}
	
	private void resetColor(){
		for(int i=0; i<pointers.size(); ++i){
			pointers.get(i).setColor(Battle.PLAYER_COLORS[i]);
		}
	}
}