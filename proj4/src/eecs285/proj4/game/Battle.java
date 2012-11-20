package eecs285.proj4.game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class Battle implements GameState {
	private Window window;
	private Texture background;
	private TrueTypeFont titleFont;

	private BattleCharacter[] battleCharacters;
	private Level level;
	private boolean timedMatch;
	private boolean stockMatch;
	float timeLeft;
	
	public Battle(BattleInfo battleInfo){
		window = new Window(0.0f, 100.0f, 0.0f, 100.0f);
		
		background = Assets.GetTexture("title_screen_background");
		titleFont = Assets.GetFont("title");
		
		// Setup Characters
		int numPlayers = battleInfo.getPlayers().length;
		Player[] players = battleInfo.getPlayers();
		Fighter[] fighters = battleInfo.getFighters();
		battleCharacters = new BattleCharacter[numPlayers];
		
		for(int i=0; i<numPlayers; ++i){
			//battleCharacters[i] = new BattleCharacter(fighters[i], players[i]);
		}
		
		// Setup time
		if(battleInfo.getMinutes() > 0){
			timedMatch = true;
			timeLeft = 60.0f * battleInfo.getMinutes();
		}
		
		// Setup stock
		if(battleInfo.getStock() > 0){
			stockMatch = true;
			for(BattleCharacter character : battleCharacters){
				character.setStock(battleInfo.getStock());
			}
		}
		
	}
	
	public void onActivate(){}
	public void onDeactivate(){}

	public void getInput(double delta){}

	public void step(double delta){}

	public void prerender(double delta){}
	
	public void render(double delta){
		Render.renderBackground(background);
		
		Render.render(titleFont, window, "Battle", 
				window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
				window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
	}
}