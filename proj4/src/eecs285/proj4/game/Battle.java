package eecs285.proj4.game;

import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import eecs285.proj4.game.fighter.CollisionBox;
import eecs285.proj4.game.fighter.Fighter;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.game.levels.LevelObject;
import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.UtilObject;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class Battle implements GameState {
	private static final boolean DEBUG_MODE = true;
	public static final Color[] PLAYER_COLORS = new Color[]{
		Color.red, Color.blue, Color.green, Color.yellow, Color.pink, Color.magenta, Color.gray, Color.orange
	};
	
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
			float levelHalfWidth = aspectRatio * (level.maxOutline.getSizeX()/2.0f);
			hud = new Window(50.f - halfWidth, 50.f + halfWidth, 0.0f, 100.0f);
			window = new Window(level.maxOutline.getCenterX() - levelHalfWidth, level.maxOutline.getCenterX() + levelHalfWidth, level.maxOutline.getTopEdge(), level.maxOutline.getBottomEdge());
		}
		else{
			float halfHeight = (50.0f / aspectRatio);
			float levelHalfHeight = (level.maxOutline.getSizeY()/2.0f) / aspectRatio;
			hud = new Window(0.0f, 100.0f, 50.0f - halfHeight, 50.0f + halfHeight);
			window = new Window(level.maxOutline.getLeftEdge(), level.maxOutline.getRightEdge(), level.maxOutline.getCenterY() - levelHalfHeight, level.maxOutline.getCenterY() + levelHalfHeight);
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
		
		for(int i=0; i<fighters.length; i++){
			fighters[i].setSpawn(level.startX[i], level.startY[i]);
			fighters[i].SetColor(PLAYER_COLORS[i]);
		}
	}
	
	public void onActivate(){}
	public void onDeactivate(){}

	public void getInput(double delta){
		// TODO : Get rid of this
			boolean tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_BACK));
			
			Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
			for(Controller controller : controllers){
				controller.poll();
				Component component = controller.getComponent(Identifier.Button._6);
				if(component != null){
					if(component.getPollData() >= 0.75f){
						tempKeyPress = true;
					}
				}
			}
			
			if(tempKeyPress){
				Game.popGameState();
			}
		
		// Get fighter input
		for(Fighter fighter : fighters){
			fighter.getInput(delta);
		}
	}

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
				if(fighter.getLeftEdge() < solid.getRightEdge() && fighter.getRightEdge() > solid.getLeftEdge()
				&& fighter.getTopEdge() < solid.getBottomEdge() && fighter.getBottomEdge() > solid.getTopEdge()){
					//Left
					if(fighter.getPreviousLeftEdge() >= solid.getRightEdge()){
						fighter.collideWithSolid(Direction.West, solid.getRightEdge());
					}
					
					//Right
					if(fighter.getPreviousRightEdge() <= solid.getLeftEdge()){
						fighter.collideWithSolid(Direction.East, solid.getLeftEdge());
					}
					
					//Top
					if(fighter.getPreviousTopEdge() >= solid.getBottomEdge()){
						fighter.collideWithSolid(Direction.North, solid.getBottomEdge());
					}
					
					//Bottom
					if(fighter.getPreviousBottomEdge() <= solid.getTopEdge()){
						fighter.collideWithSolid(Direction.South, solid.getTopEdge());
					}
				}
			}
			
			// Collide with platform
			for(LevelObject platform : level.platformObjects){
				if(fighter.getBottomEdge() > platform.getTopEdge() && fighter.getPreviousBottomEdge() <= platform.getTopEdge()
				&& fighter.getLeftEdge() < platform.getRightEdge() && fighter.getRightEdge() > platform.getLeftEdge()){
					fighter.collideWithPlatform(platform.getTopEdge());
				}
			}
		}
		
		// If fighters are overlapping on the ground, move them apart
		for(int i=1; i<fighters.length; i++){
			for(int j=0; j<i; j++){
				// Are they overlapping?
				if(fighters[i].getLeftEdge() < fighters[j].getRightEdge() && fighters[i].getRightEdge() > fighters[j].getLeftEdge()
				&& fighters[i].getTopEdge() < fighters[j].getBottomEdge() && fighters[i].getBottomEdge() > fighters[j].getTopEdge()){
					// Are they in the right states and moving slowly?
					if(fighters[i].GetOnGround() && fighters[j].GetOnGround()
					&& Math.abs(fighters[i].getVelocityX()) < 1.1f && Math.abs(fighters[j].getVelocityX()) < 1.1f){
						// Move them apart.
						final float movement = 1.0f;
						Fighter leftFighter = fighters[i];
						Fighter rightFighter = fighters[j];
						
						if(fighters[i].getCenterX() > fighters[j].getCenterX()){
							leftFighter = fighters[j];
							rightFighter = fighters[i];
						}
						
						leftFighter.setVelocity(leftFighter.getVelocityX() - movement, leftFighter.getVelocityY());
						rightFighter.setVelocity(rightFighter.getVelocityX() + movement, rightFighter.getVelocityY());
					}
				}
			}
		}
		
		// Detecting attack collisions
		for(Fighter attacker : fighters){
			if(attacker.currentAttack != null){
				for(int i=0; i<fighters.length; i++){
					Fighter receiver = fighters[i];
					
					if(attacker==receiver || attacker.currentAttack.hitPlayers[i]){
						continue;
					}
					
					boolean hitPlayer = false;
					
					// Check (collision box -> collision box) collisions
					if(receiver.currentAttack != null){
						for(CollisionBox attackerBox : attacker.currentAttack.getCollisionBoxes()){
							if(hitPlayer == true){ break; }
							
							UtilObject aBox = attackerBox.getBox(attacker.currentAttack.getCurrentTime(), attacker);
						
							for(CollisionBox receiverBox : receiver.currentAttack.getCollisionBoxes()){

								UtilObject rBox = receiverBox.getBox(receiver.currentAttack.getCurrentTime(), receiver);
								
								if(aBox.getLeftEdge() < rBox.getRightEdge()
								&& aBox.getRightEdge() > rBox.getLeftEdge()
								&& aBox.getTopEdge() < rBox.getBottomEdge()
								&& aBox.getBottomEdge() > rBox.getTopEdge()){
									
									if(attackerBox.attackPriority > receiverBox.attackPriority - 2){
										float hitSpeedX = attackerBox.hitSpeedX;
										float hitSpeedY = attackerBox.hitSpeedY;
										
										if(attacker.GetFacingLeft()){
											hitSpeedX = -hitSpeedX;
										}
										
										// Get hit
										receiver.doGetHit(delta, hitSpeedX, hitSpeedY, 
														attackerBox.flightTime, attackerBox.stunTime, attackerBox.healthScaler,
														attackerBox.damage);
										
										attacker.currentAttack.hitPlayers[i] = true;
										hitPlayer = true;
										break;
									}
								}
							}
						}
					}
					
					// Check collision with player location
					for(CollisionBox attackerBox : attacker.currentAttack.getCollisionBoxes()){
						if(hitPlayer == true){ break; }
						
						UtilObject aBox = attackerBox.getBox(attacker.currentAttack.getCurrentTime(), attacker);
						
						if(aBox.getLeftEdge() < receiver.getRightEdge()
						&& aBox.getRightEdge() > receiver.getLeftEdge()
						&& aBox.getTopEdge() < receiver.getBottomEdge()
						&& aBox.getBottomEdge() > receiver.getTopEdge()
						){	
							float hitSpeedX = attackerBox.hitSpeedX;
							float hitSpeedY = attackerBox.hitSpeedY;
							
							if(attacker.GetFacingLeft()){
								hitSpeedX = -hitSpeedX;
							}
							
							// Get hit
							receiver.doGetHit(delta, hitSpeedX, hitSpeedY, 
											attackerBox.flightTime, attackerBox.stunTime, attackerBox.healthScaler, 
											attackerBox.damage);
							
							attacker.currentAttack.hitPlayers[i] = true;
							hitPlayer = true;
							break;
						}
					}
				}
			}
		}
		
		// Update window
		float left = level.minOutline.getLeftEdge();
		float right = level.minOutline.getRightEdge();
		float top = level.minOutline.getTopEdge();
		float bottom = level.minOutline.getBottomEdge();
		
		for(Fighter fighter : fighters){
			//if(isSpawned())
			left = Math.min(left, fighter.getLeftEdge() - 2.0f);
			right = Math.max(right, fighter.getRightEdge() + 2.0f);
			top = Math.min(top, fighter.getTopEdge() - 2.0f);
			bottom = Math.max(bottom, fighter.getBottomEdge() + 2.0f);
		}
		
		left = Math.max(left, level.maxOutline.getLeftEdge());
		right = Math.min(right, level.maxOutline.getRightEdge());
		top = Math.max(top, level.maxOutline.getTopEdge());
		bottom = Math.min(bottom, level.maxOutline.getBottomEdge());
		
		window.doZoom(right - left, bottom - top, 0.5f);
		window.doMoveCenter(left + (right - left)*0.5f, top + (bottom - top)*0.5f, 0.5f);
		
		window.step(delta);
		hud.step(delta);
	}

	public void prerender(double delta){}
	
	public void render(double delta){
		window.setupRender();
		
		level.render(delta);
		
		for(Fighter fighter : fighters){
			fighter.render(delta);
		}
		
		if(DEBUG_MODE){
			for(Fighter fighter : fighters){
				Render.render(Assets.GetTexture("square"), fighter, Color.blue);
				if(fighter.currentAttack != null){
					fighter.currentAttack.debugRender(delta);
				}
			}
		}

		String text = String.valueOf((int)Math.ceil(timeLeft));
		Render.render(titleFont, hud, text, 50, 10, 10, 0.5f, 0.5f, Color.black);
		
		//Render.render(titleFont, window, "Battle", 
		//		window.getCenterX(), window.getTop() + window.getSizeY()/4.0f, 
		//		window.getSizeY()/8.0f, 0.5f, 0.5f, Color.black);
	}
}