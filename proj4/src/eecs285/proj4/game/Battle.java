package eecs285.proj4.game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import eecs285.proj4.game.Levels.Level;
import eecs285.proj4.game.Levels.LevelObject;
import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class Battle implements GameState {
	private Window window;
	private Window hud;
	private TrueTypeFont titleFont;

	private Fighter[] fighters;
	private Level level;
	private boolean timedMatch;
	private boolean stockMatch;
	float timeLeft;
	
	public Battle(BattleInfo battleInfo){
		level = Assets.GetLevel(battleInfo.getLevel());
		
		float aspectRatio = (float)DisplayInfo.GetWidth() / (float) DisplayInfo.GetHeight();
		
		if(aspectRatio >= 1.0f){
			float halfWidth = aspectRatio * 50.0f;
			float levelHalfWidth = aspectRatio * (level.getSizeX()/2.0f);
			hud = new Window(50.f - halfWidth, 50.f + halfWidth, 0.0f, 100.0f);
			window = new Window(level.getCenterX() - levelHalfWidth, level.getCenterX() + levelHalfWidth, level.getTopEdge(), level.getBottomEdge());
		}
		else{
			float halfHeight = (50.0f / aspectRatio);
			float levelHalfHeight = (level.getSizeY()/2.0f) / aspectRatio;
			hud = new Window(0.0f, 100.0f, 50.0f - halfHeight, 50.0f + halfHeight);
			window = new Window(level.getLeftEdge(), level.getRightEdge(), level.getCenterY() - levelHalfHeight, level.getCenterY() + levelHalfHeight);
		}
		
		titleFont = Assets.GetFont("title");
		
		// Setup Characters
		fighters = battleInfo.getFighters();
		
		// Setup time
		if(battleInfo.getMinutes() > 0){
			timedMatch = true;
			timeLeft = 60.0f * battleInfo.getMinutes();
		}
		
		// Setup stock
		if(battleInfo.getStock() > 0){
			stockMatch = true;
			for(Fighter fighter : fighters){
				fighter.SetStock(battleInfo.getStock());
			}
		}
	}
	
	public void onActivate(){}
	public void onDeactivate(){}

	public void getInput(double delta){}

	public void step(double delta){
		if(timedMatch){
			if(timeLeft <= 0.0f){
				// TODO : Handle endGame
				Game.popGameState();
				//Game.pushGameState(gameState);
			}
			
			timeLeft -= delta;
		}
		
		// Update level
		level.step(delta);
		
		// Update fighters
		for(Fighter fighter : fighters){
			fighter.step(delta);
		}
		
		// Check for Fighter->Level collisions
		for(Fighter fighter : fighters){
			// Collide with solid
			for(LevelObject solid : level.solidObjects){
				if(fighter.getTopEdge() < solid.getBottomEdge() && fighter.getBottomEdge() > solid.getTopEdge()
				&& fighter.getLeftEdge() < solid.getRightEdge() && fighter.getRightEdge() > solid.getLeftEdge()){
					//Left
					if(fighter.getLeftEdge() > solid.getLeftEdge()){
						fighter.collideWithSolid(Direction.West, solid.getRightEdge());
					}
					
					//Right
					if(fighter.getRightEdge() < solid.getRightEdge()){
						fighter.collideWithSolid(Direction.East, solid.getLeftEdge());
					}
					
					//Top
					if(fighter.getTopEdge() > solid.getBottomEdge()){
						fighter.collideWithSolid(Direction.North, solid.getBottomEdge());
					}
					
					//Bottom
					if(fighter.getBottomEdge() < solid.getTopEdge()){
						fighter.collideWithSolid(Direction.South, solid.getTopEdge());
					}
				}
			}
			
			// Collide with platform
			for(LevelObject platform : level.platformObjects){
				if(fighter.getBottomEdge() > platform.getTopEdge() && fighter.getBottomEdge() - fighter.getVelocityY() < platform.getTopEdge()
				&& fighter.getLeftEdge() < platform.getRightEdge() && fighter.getRightEdge() > platform.getLeftEdge()){
					fighter.collideWithSolid(Direction.South, platform.getTopEdge());
				}
			}
		}
	}

	public void prerender(double delta){}
	
	public void render(double delta){
		window.setupRender();
		
		level.render(delta);
		
		for(Fighter fighter : fighters){
			fighter.render(delta);
		}
		
		Render.render(titleFont, window, "Battle", 
				window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
				window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
		
		String text = String.valueOf((int)Math.ceil(timeLeft));
		Render.render(titleFont, hud, text, 50, 10, 10, 0.5f, 0.5f, Color.black);
	}
}