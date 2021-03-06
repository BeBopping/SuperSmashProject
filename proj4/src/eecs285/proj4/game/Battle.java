package eecs285.proj4.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import eecs285.proj4.game.fighter.CollisionBox;
import eecs285.proj4.game.fighter.Fighter;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.game.levels.LevelObject;
import eecs285.proj4.game.screens.ScreenPause;
import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.UtilObject;
import eecs285.proj4.util.Window;
import eecs285.proj4.util.Render;

public class Battle implements GameState {
	private static final boolean DEBUG_MODE = false;
	public static final Color[] PLAYER_COLORS = new Color[]{
		Color.red, Color.blue, Color.green, Color.yellow, Color.pink, Color.magenta, Color.gray, Color.orange
	};
	
	private Window window;
	private Window hud;
	private TrueTypeFont titleFont;
	private TrueTypeFont percentageFont;

	private Fighter[] fighters;
	private Level level;
	private boolean timedMatch;
	private boolean stockMatch;
	float timeLeft;
	
	public Battle(BattleInfo battleInfo){
		level = GameAssets.GetLevel(battleInfo.getLevel());
		
		float aspectRatio = (float)DisplayInfo.GetWidth() / (float) DisplayInfo.GetHeight();
		
		if(aspectRatio >= 1.0f){
			float halfWidth = aspectRatio * 50.0f;
			float levelHalfHeight = (level.minOutline.getSizeX()*0.5f) / aspectRatio;
			hud = new Window(50.f - halfWidth, 50.f + halfWidth, 0.0f, 100.0f);
			window = new Window(level.minOutline.getLeftEdge(), 
								level.minOutline.getRightEdge(), 
								level.minOutline.getCenterY() - levelHalfHeight, 
								level.minOutline.getCenterY() + levelHalfHeight);
		}
		else{
			float halfHeight = (50.0f / aspectRatio);
			float levelHalfWidth = aspectRatio / (level.minOutline.getSizeY()*0.5f);
			hud = new Window(0.0f, 100.0f, 50.0f - halfHeight, 50.0f + halfHeight);
			window = new Window(level.minOutline.getCenterX() - levelHalfWidth, 
								level.minOutline.getCenterX() + levelHalfWidth, 
								level.minOutline.getTopEdge(), 
								level.minOutline.getBottomEdge());
		}
		
		titleFont = GameAssets.GetFont("title");
		percentageFont = GameAssets.GetFont("percentage");
		
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
		else{
			for(Fighter fighter : fighters){
				fighter.SetStock(-1);
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
		// Get fighter input
		for(Fighter fighter : fighters){
			fighter.getInput(delta);
		}
	}

	public void step(double delta){
		if(timedMatch){
			if(timeLeft <= 0.0f){
				endGame();
			}
			
			timeLeft -= delta;
		}
		
		// Check for player pause:
		boolean pause = false;
		for(Fighter fighter : fighters){
			if(fighter.input.back && ! fighter.input.backLast){
				pause = true;
			}
		}
		
		if(pause){
			Game.pushGameState(new ScreenPause());
		}
		
		// Update level
		level.step(delta);
		
		// Update fighters
		for(Fighter fighter : fighters){
			fighter.step(delta);
		}
		
		// Check for Fighter->Level collisions
		for(Fighter fighter : fighters){
			if(!fighter.isActive){ continue; }
			
			// Collide with solid
			for(LevelObject solid : level.solidObjects){
				if(fighter.getLeftEdge() < solid.getRightEdge() && fighter.getRightEdge() > solid.getLeftEdge()
				&& fighter.getTopEdge() < solid.getBottomEdge() && fighter.getBottomEdge() > solid.getTopEdge()){
					//Left
					if(fighter.getPreviousLeftEdge() >= solid.getPreviousRightEdge()){
						fighter.collideWithSolid(Direction.West, solid.getRightEdge() + 0.001f, solid);
					}
					
					//Right
					if(fighter.getPreviousRightEdge() <= solid.getPreviousLeftEdge()){
						fighter.collideWithSolid(Direction.East, solid.getLeftEdge() - 0.001f, solid);
					}
					
					//Top
					if(fighter.getPreviousTopEdge() >= solid.getPreviousBottomEdge()){
						fighter.collideWithSolid(Direction.North, solid.getBottomEdge() + 0.001f, solid);
					}
					
					//Bottom
					if(fighter.getPreviousBottomEdge() <= solid.getPreviousTopEdge()){
						fighter.collideWithSolid(Direction.South, solid.getTopEdge() - 0.001f, solid);
					}
				}
			}
			
			// Collide with platform
			for(LevelObject platform : level.platformObjects){
				if(fighter.getBottomEdge() > platform.getTopEdge() && fighter.getPreviousBottomEdge() <= platform.getPreviousTopEdge()
				&& fighter.getLeftEdge() < platform.getRightEdge() && fighter.getRightEdge() > platform.getLeftEdge()){
					fighter.collideWithPlatform(platform.getTopEdge() - 0.001f, platform);
				}
			}
		}
		
		// If fighters are overlapping on the ground, move them apart
		for(int i=1; i<fighters.length; i++){
			if(!fighters[i].isActive){ continue; }
			
			for(int j=0; j<i; j++){
				if(!fighters[j].isActive){ continue; }
				
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
			if(!attacker.isActive){ continue; }
			
			if(attacker.currentAttack != null){
				for(int i=0; i<fighters.length; i++){
					Fighter receiver = fighters[i];
				
					if(!receiver.isActive || attacker==receiver || attacker.currentAttack.hitPlayers[i]){
						continue;
					}
					
					boolean hitPlayer = false;
					
					// Check (collision box -> collision box) collisions
					if(receiver.currentAttack != null){
						for(CollisionBox attackerBox : attacker.currentAttack.getCollisionBoxes()){
							if(hitPlayer == true){ break; }
							
							if(!attackerBox.isAlive(attacker.currentAttack.getCurrentTime())){ continue; }
							
							UtilObject aBox = attackerBox.getBox(attacker.currentAttack.getCurrentTime(), attacker);
						
							for(CollisionBox receiverBox : receiver.currentAttack.getCollisionBoxes()){
								if(!receiverBox.isAlive(receiver.currentAttack.getCurrentTime())){ continue; }

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
						if(!attackerBox.isAlive(attacker.currentAttack.getCurrentTime())){ continue; }
						
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
		
		// Handle death
		int numAlive = 0;
		for(int i=0; i<fighters.length; i++){
			Fighter fighter = fighters[i];

			if(fighter.isActive){
				if(fighter.getTopEdge() - 4 > level.maxOutline.getBottomEdge()){
					// Fighter died
					if(stockMatch){
						fighter.stock--;
					}
					fighter.hitPercent = 0;
					fighter.deaths++;
					
					fighter.setSpawn(level.spawnX[i], level.spawnY[i]);
					fighter.setDie();
				}
			}

			if(fighter.stock != 0){
				++numAlive;
			}
		}
		
		if(numAlive < 2){
			endGame();
		}
		
		// Update window
		float left = level.minOutline.getLeftEdge();
		float right = level.minOutline.getRightEdge();
		float top = level.minOutline.getTopEdge();
		float bottom = level.minOutline.getBottomEdge();
		
		for(Fighter fighter : fighters){
			if(!fighter.isVisible){ continue; }
			
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
		
		// Render fighters
		for(Fighter fighter : fighters){
			if(!fighter.isVisible){ continue; }
			fighter.render(delta);
		}
		
		if(DEBUG_MODE){
			for(Fighter fighter : fighters){
				if(!fighter.isActive){ continue; }
				
				Render.render(GameAssets.GetTexture("square"), fighter, Color.blue);
				if(fighter.currentAttack != null){
					fighter.currentAttack.debugRender(delta);
				}
			}
		}

		// Render Hud
		hud.setupRender();

		float segmentSize = hud.getSizeX() / (float)(fighters.length + 1);
		float posX = hud.getLeft() + segmentSize;
		float posY = hud.getBottom() - 10;
		for(Fighter fighter : fighters){
			String percentage = String.valueOf(fighter.hitPercent) + '%';
			Render.render(percentageFont, hud, percentage, posX, posY, 10, 0.5f, 0.5f, fighter.playerColor);
			
			float imageSize = 3.0f;
			float imageSpacing = 0.5f;
			float stockPosY = posY + 5.0f;
			float stockPosX = posX - ((float)(fighter.stock-1) * 0.5f)*(imageSize + imageSpacing);
			for(int i=0; i<fighter.stock; i++){
				Render.render(GameAssets.GetTexture("circle"), 
								stockPosX, 
								stockPosX+imageSize, 
								stockPosY, 
								stockPosY+imageSize, 
								fighter.playerColor);
				stockPosX += imageSize + imageSpacing;
			}
			
			posX += segmentSize;
		}

		// Render Clock
		if(timedMatch){
			int timeVal = (int)Math.ceil(timeLeft);
			String text = "";
			if(timeVal >= 60){
				text += String.valueOf((int)((float)timeVal / 60.0f)) + ":";
				
				if(timeVal % 60 < 10){
					text += "0" + timeVal % 60;
				}
				else{
					text += timeVal % 60;
				}
			}
			else{
				text += timeVal % 60;
			}
			text += "  ";
	
			Render.render(GameAssets.GetTexture("white"), 40, 60, 1, 11, Color.gray);
			Render.render(titleFont, hud, text, 50, 6, 10, 0.5f, 0.5f, Color.black);
		}
	}
	
	private void endGame(){
		Game.popGameState();
	}
}