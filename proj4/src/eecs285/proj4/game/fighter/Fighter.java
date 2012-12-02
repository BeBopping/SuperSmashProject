package eecs285.proj4.game.fighter;

import static eecs285.proj4.game.Direction.*;
import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Direction;
import eecs285.proj4.game.MovingObject;
import eecs285.proj4.game.input.Input;
import eecs285.proj4.game.levels.LevelObject;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.SmallSprite;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public abstract class Fighter extends MovingObject {
	protected static final float GRAVITY = 100.0f;
	protected static final float MAX_HORIZONTAL = 100.0f;
	protected static final float MAX_VERTICAL = 1000.0f;
	protected static final float RECOIL_PERCENT = 0.25f;
	protected static final float RECOIL_TANGENT_PERCENT = 0.95f;
	protected static final float SPAWN_DELAY = 1.0f;
	protected static final float SPAWN_TIME = 1.0f;
	protected static final float SPAWN_LENIENCY = 3.0f;
	
	protected String fighterName;
	protected FighterTrait fighterTrait;
	protected Input input;
	
	public int hitPercent;
	public int stock;
	public int kills;
	public int deaths;
	public int suicides;
	
	// All units use meters and seconds
	//protected float minWalkSpeed;
	protected float maxWalkSpeed;
	protected float forwardAcceleration;
	protected float reverseAcceleration;
	//protected float runSpeed;
	protected float firstJumpSpeed;
	protected float firstJumpMinTime;
	protected float firstJumpMaxTime;
	protected float firstJumpLiniencyTime;
	protected float secondJumpSpeed;
	protected float secondJumpMinTime;
	protected float secondJumpMaxTime;
	protected float secondJumpLiniencyTime;
	protected float maxFallSpeed;
	protected float maxFallSpeedHorizontal;

	public boolean isVisible;
	public boolean isActive;
	protected boolean facingLeft;
	protected boolean onGround;
	protected boolean onGroundLast;
	//protected boolean ducking;
	//protected boolean running;
	protected boolean canDoubleJump;
	protected boolean isDoubleJumping;
	protected double flightTime;		// Is flying if > 0.0f
	protected double stunTime;			// Is stunned if > 0.0f
	protected double jumpTime;			// Is jumping if > 0.0f
	protected double spawnTime;
	public Attack currentAttack;
	protected boolean doNormalAttack;
	protected boolean doSpecialAttack;
	protected LevelObject currentStandingPlatform;
	
	protected SmallSprite currentSprite;
	protected float visualWidth;
	protected float visualHeight;
	public Color playerColor;
	
	protected Texture fighterTexture;
	protected float distancePerRunSprite;
	protected double spriteDistance;
	
	public Fighter(String name, FighterTrait trait,
			float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		fighterName = name;
		fighterTrait = trait;
		
		jumpTime = 1000.0f;
		onGround = true;
		isActive = true;
		isVisible = true;
		
		currentSprite = FighterSprites.stand[0];
	}
	
	public void SetInput(Input input){
		this.input = input;
	}
	
	public void SetStock(int stock){
		this.stock = stock;
	}
	
	public void SetColor(Color color){
		playerColor = color;
	}
	
	public boolean GetOnGround(){
		return onGround;
	}
	
	public boolean GetFacingLeft(){
		return facingLeft;
	}
	
	public String toString(){
		return fighterName;
	}
	
	public void getInput(double delta){
		input.getInput();
		
		// Update Primary attack
		if(!input.normalAttack){
			doNormalAttack = false;
		}
		else if(!input.normalAttackLast){
			doNormalAttack = true;
		}		
		
		// Update Secondary attack
		if(!input.specialAttack){
			doSpecialAttack = false;
		}
		else if(!input.specialAttackLast){
			doSpecialAttack = true;
		}
	}
	
	public void setSpawn(float xCenter, float yBase){
		posX = xCenter - sizeX*0.5f;
		posY = yBase - sizeY;
		lastPosX = posX;
		lastPosY = posY;
		velX = 0.0f;
		velY = 0.0f;
		
		stunTime = SPAWN_TIME;
	}
	
	public void setDie(){
		spawnTime = SPAWN_DELAY + SPAWN_TIME;
		stunTime = spawnTime;
		
		isVisible = false;
		isActive = false;
	}

	public void setVelocity(float velocityX, float velocityY){
		velX = velocityX;
		velY = velocityY;
	}
	
	public void step(double delta){
		onGroundLast = onGround;
		lastPosX = posX;
		lastPosY = posY;
		
		if(onGround){
			canDoubleJump = true;
		}
		
		if(flightTime > 0.0f){
			flightTime -= delta;
		}
		
		if(stunTime > 0.0f){
			stunTime -= delta;
			
			if(currentAttack != null){
				currentAttack = null;
			}
		}
		
		if(!isActive && stock != 0){
			if(spawnTime > -SPAWN_LENIENCY){
				spawnTime -= delta;
				
				if(spawnTime < SPAWN_TIME){
					isVisible = true;
	
					if(spawnTime < 0.0d && input.getAnyActivity()){
						isActive = true;
					}
				}
			}
			else{
				isVisible = true;
				isActive = true;
			}
		}
		
		if(!isActive){
			return;
		}
		
		doDuck(delta);
		doMovement(delta);
		doJump(delta);
		doAttack(delta);
		doPlayerDirection(delta);
		doSprite(delta);
		
		if(velX > MAX_HORIZONTAL) velX = MAX_HORIZONTAL;
		if(velX < -MAX_HORIZONTAL) velX = -MAX_HORIZONTAL;
		if(velY > MAX_VERTICAL) velY = MAX_VERTICAL;
		if(velY < -MAX_VERTICAL) velY = -MAX_VERTICAL;

		posX += delta * velX;
		posY += delta * velY;
		
		// Don't mess with on ground if we've disabled gravity.
		if(currentAttack == null || !currentAttack.GetOverrideGravity()){
			onGround = false;
		}
	}
	
	private void doJump(double delta){
		// Stop jumping if we override gravity
		if(stunTime > 0.0d || (currentAttack != null && currentAttack.GetOverrideGravity())){
			if(jumpTime > 0.0d){
				jumpTime = Math.max(firstJumpMaxTime, secondJumpMaxTime);
			}
		}
		
		// Stop jumping if we are stunned.
		//if(stunTime > 0.0d){
		//	if(jumpTime > 0.0d){
		//		jumpTime = 0.0d;
		//	}
		//	else{
		//		jumpTime -= delta;
		//	}
		//	return;
		//}
		
		if(onGround){
			if(input.jump){
				if(jumpTime >= -firstJumpLiniencyTime && jumpTime <= 0.0d){
					if(currentAttack != null || stunTime > 0.0d){
						jumpTime -= delta;
					}
					else{
						jumpTime = delta;
						
						Assets.GetSound("jump").playAsSoundEffect(1.0f, 1.0f, false);
					}
				}
				// This might nt be needed
				else if(jumpTime > 0.0d && jumpTime <= firstJumpMaxTime){
					jumpTime += delta;
				}
			}
			else{
				jumpTime = 0.0d;
			}
		}
		else{
			if(input.jump){
				if(isDoubleJumping){
					if(jumpTime > 0.0d && jumpTime <= secondJumpMaxTime){
						jumpTime += delta;
					}
					else{
						isDoubleJumping = false;
					}
				}
				else{
					if(jumpTime > 0.0d && jumpTime <= firstJumpMaxTime){
						jumpTime += delta;
					}
					else if(jumpTime <= 0.0d){
						if(currentAttack != null || stunTime > 0.0d){
							jumpTime -= delta;
						}
						else if(canDoubleJump && jumpTime >= -secondJumpLiniencyTime){
							Assets.GetSound("jump_air").playAsSoundEffect(1.0f, 1.0f, false);
							
							jumpTime = delta;
							canDoubleJump = false;
							isDoubleJumping = true;
						}
						else{
							jumpTime -= delta;
						}
					}
				}
			}
			else{
				if(jumpTime > 0.0d && ( (isDoubleJumping && jumpTime < secondJumpMinTime)
								      ||(!isDoubleJumping && jumpTime < firstJumpMinTime))){
					jumpTime += delta;
				}
				else{
					jumpTime = 0.0d;
				}
			}
		}
		
		if(jumpTime > 0.0d && jumpTime < firstJumpMaxTime){
			velY = -firstJumpSpeed;
		}
	}
	
	private void doDuck(double delta){}
	
	private void doMovement(double delta){

		if(currentAttack != null && currentAttack.GetOverrideGravity()){
			velY += delta * (GRAVITY*0.25f);
			velY *= 0.85f;
			velX *= 0.95f;
		}
		else{
			if(flightTime > 0.0f){
				velY += delta * (GRAVITY*0.5f);
			}
			else{
				velY += delta * GRAVITY;
			}
		}
		
		if(stunTime > 0.0f || flightTime > 0.0f){
			return;
		}
		
		if(onGround){
			float platformSpeed = 0;
			if(currentStandingPlatform != null){
				platformSpeed = currentStandingPlatform.getGroundSpeed();
			}
			
			if(input.xAxis != 0.0f && !(currentAttack != null && (currentAttack.GetStationaryOnGround() || currentAttack.GetOverrideGravity()))){
				float desiredSpeed = input.xAxis * maxWalkSpeed + platformSpeed;
				double rateOfChange;
				
				// Change speed faster if we are "in reverse"
				if(velX < desiredSpeed && velX < platformSpeed
				|| velX > desiredSpeed && velX > platformSpeed){
					rateOfChange = reverseAcceleration * delta;
				}
				else{
					rateOfChange = forwardAcceleration * delta;
				}
				
				// Move closer to desired speed
				if(velX - rateOfChange > desiredSpeed){
					velX -= rateOfChange;
				}
				else if(velX + rateOfChange < desiredSpeed){
					velX += rateOfChange;
				}
				else{
					velX = desiredSpeed;
				}
			}
			else{
				double rateOfChange = forwardAcceleration * delta;
				
				// Move closer to desired speed
				if(velX - rateOfChange > platformSpeed){
					velX -= rateOfChange;
				}
				else if(velX + rateOfChange < platformSpeed){
					velX += rateOfChange;
				}
				else{
					velX = platformSpeed;
				}
				
				//velX *= 0.75f;
			}
			
			if(velX > maxWalkSpeed) velX = maxWalkSpeed;
			if(velX < -maxWalkSpeed) velX = -maxWalkSpeed;
		}
		else{
			if(currentAttack == null || !currentAttack.GetStationaryInAir()){
				// Check if we are trying to go in the opposite direction that we are moving
				if(velX * input.xAxis < 0.0f){
					velX += (double)input.xAxis * (delta * (double)reverseAcceleration);
				}
				else{
					velX += (double)input.xAxis * (delta * (double)forwardAcceleration);
				}
			}
			
			if(velX > maxFallSpeedHorizontal) velX = maxFallSpeedHorizontal;
			if(velX < -maxFallSpeedHorizontal) velX = -maxFallSpeedHorizontal;
			if(velY > maxFallSpeed) velY = maxFallSpeed;
			if(velY < -maxFallSpeed) velY = -maxFallSpeed;
		}
	}
	
	private void doAttack(double delta){
		if(stunTime > 0.0f){
			return;
		}
		
		// Update current attack
		if(currentAttack != null){
			currentAttack.step(delta);
			if(currentAttack.isComplete()){
				currentAttack = null;
			}
		}
		
		// Pick a new attack
		if(currentAttack == null){
			if(doNormalAttack){
				doNormalAttack = false;
				
				// Action in x direction
				if(Math.abs(input.xAxis) > Math.abs(input.yAxis)){
					if(input.xAxis > 0.0f){
						if(onGround){
							currentAttack = GetAttackNormalGround(East);
						}
						else{
							currentAttack = GetAttackNormalAir(East);
						}
					}
					else{
						if(onGround){
							currentAttack = GetAttackNormalGround(West);
						}
						else{
							currentAttack = GetAttackNormalAir(West);
						}
					}
					return;
				}
				else if(Math.abs(input.xAxis) < Math.abs(input.yAxis)){
					if(input.yAxis > 0.0f){
						if(onGround){
							currentAttack = GetAttackNormalGround(South);
						}
						else{
							currentAttack = GetAttackNormalAir(South);
						}
					}
					else{
						if(onGround){
							currentAttack = GetAttackNormalGround(North);
						}
						else{
							currentAttack = GetAttackNormalAir(North);
						}
					}
					return;
				}
				else{
					if(onGround){
						currentAttack = GetAttackNormalGround(null);
					}
					else{
						currentAttack = GetAttackNormalAir(null);
					}
					return;
				}
			}
			
			if(doSpecialAttack){
				doSpecialAttack = false;
				
				// Action in x direction
				if(Math.abs(input.xAxis) > Math.abs(input.yAxis)){
					if(input.xAxis > 0.0f){
						currentAttack = GetAttackSpecial(East);
					}
					else{
						currentAttack = GetAttackSpecial(West);
					}
					return;
				}
				else if(Math.abs(input.xAxis) < Math.abs(input.yAxis)){
					if(input.yAxis > 0.0f){
						currentAttack = GetAttackSpecial(South);
					}
					else{
						currentAttack = GetAttackSpecial(North);
					}
					return;
				}
				else{
					currentAttack = GetAttackSpecial(null);
					return;
				}
			}
		}
	}
	
	public void doGetHit(double delta, float velX, float velY, float flightTime, float stunTime, float healthScaler, int damage){
		final float scale = 1.0f + (float)hitPercent / healthScaler;
		velX *= scale;
		velY *= scale;
		flightTime *= scale;
		stunTime *= scale;

		hitPercent += damage;
		this.flightTime = flightTime;
		this.stunTime = stunTime;
		
		if(onGround && velY > 0.0f){
			velY = -velY;
		}
		
		// Update x vel
		if(Math.abs(this.velX) < Math.abs(velX)){
			this.velX = velX;
		}
		else{
			this.velX += velX;
			//if(velX > 0.0f){
			//	this.velX = Math.abs(this.velX);
			//}
			//else{
			//	this.velX = -Math.abs(this.velX);
			//}
		}
	
		// Update y vel
		if(Math.abs(this.velY) < Math.abs(velY)){
			this.velY = velY;
		}
		else{
			this.velY += velY;
			//if(velY > 0.0f){
			//	this.velY = Math.abs(this.velY);
			//}
			//else{
			//	this.velY = -Math.abs(this.velY);
			//}
		}
		
	}
	
	private void doPlayerDirection(double delta){
		if(stunTime > 0.0f || flightTime > 0.0f || (currentAttack != null && !currentAttack.GetCanChangeDirection())){
			return;
		}
		
		if(currentAttack != null && currentAttack.GetCanChangeDirection()){
			if(input.xAxis != 0.0f){
				facingLeft = input.xAxis < 0.0f;
			}
		}
		else{
			if(facingLeft){
				if(velX >= 0 && input.xAxis > 0.0f){
					facingLeft = false;
				}
			}
			else{
				if(velX <= 0 && input.xAxis < 0.0f){
					facingLeft = true;
				}
			}
		}
	}
	
	private void doSprite(double delta){
		//if(stunTime > 0.0d){
		//	spriteDistance += delta * Math.abs(velX);
		//	currentSprite = FighterSprites.stun[0];
		//	return;
		//}
		
		if(currentAttack != null){
			spriteDistance = 0.0d;
			
			// Sprite can be null if attack is over
			currentSprite = currentAttack.getSmallSprite();
		}
		
		if(currentAttack == null || currentSprite == null){
			if(onGround){
				float tempVelX = velX;
				if(currentStandingPlatform != null){
					tempVelX -= currentStandingPlatform.getGroundSpeed();
				}
				
				if(Math.abs(tempVelX) > 0.5f){// && input.xAxis != 0.0f){
					// run 1 sprite-frame per half meter
					spriteDistance += delta * Math.abs(tempVelX);
					
					int index = 1 + (int)(spriteDistance / (double)distancePerRunSprite);
					index %= 8;
					
					// jump for now - Sprite is in the wrong spot
					currentSprite = FighterSprites.walk[index];
					return;
				}
				else{
					spriteDistance = 0.0d;
					currentSprite = FighterSprites.stand[0];
				}
			}
			// In Air
			else{
				spriteDistance = 0.0d;
				
				// Jumping up
				if(jumpTime > 0.0f && ((isDoubleJumping && jumpTime < secondJumpMaxTime) || (!isDoubleJumping && jumpTime < firstJumpMaxTime))){
					//float fraction;
					//if(isDoubleJumping){
					//	fraction = (float)jumpTime / secondJumpMaxTime;
					//}
					//else{
					//	fraction = (float)jumpTime / firstJumpMaxTime;
					//}
						
					//int index = Math.min((int)(fraction * 3), 2);
					
					currentSprite = FighterSprites.jump[0];
				}
				// Falling
				else{
					currentSprite = FighterSprites.fall[0];
					// Falling up
					//if(velY < 0.0f){
					//	currentSprite = FighterSprites.fall[0];
					//}
					// Falling down
					//else{
					//	float fraction = (float)velY / maxFallSpeed;
					//	
					//	int index = 1 + Math.min((int)(fraction * 3), 2);
					//	
					//	currentSprite = FighterSprites.fall[0];
					//}
				}
			}
		}
	}
	
	public void render(double delta){
		Render.startBatchRender(fighterTexture, playerColor);
		
		Render.batchRender(	currentSprite, 
							getCenterX() - visualWidth*0.5f, 
							getCenterX() + visualWidth*0.5f,
							getBottomEdge() - visualHeight, 
							getBottomEdge(), 
							facingLeft);
		
		Render.stopBatchRender();
		
		handleRender(delta);
	}
	
	public void collideWithSolid(Direction dir, float pos, LevelObject object){
		if(flightTime > 0.0f){
			switch(dir){
			case North:
				posY = pos;
				//posY = Math.min(pos, lastPosY);
				velY = Math.abs(this.velY)*RECOIL_PERCENT;
				velX = velX*RECOIL_TANGENT_PERCENT;
				break;
			case South:
				posY = pos - sizeY;
				//posY = Math.max(pos - sizeY, lastPosY);
				velY = -Math.abs(this.velY)*RECOIL_PERCENT;
				velX = velX*RECOIL_TANGENT_PERCENT;
				hitGround();
				currentStandingPlatform = object;
				break;
			case East:
				posX = pos - sizeX;
				//posX = Math.min(pos - sizeX, lastPosX);
				velX = -Math.abs(this.velX)*RECOIL_PERCENT;
				velY = velY*RECOIL_TANGENT_PERCENT;
				break;
			case West:
				posX = pos;
				//posX = Math.max(pos, lastPosX);
				velX = Math.abs(this.velX)*RECOIL_PERCENT;
				velY = velY*RECOIL_TANGENT_PERCENT;
				break;
			}
		}
		else{
			switch(dir){
			case North:
				posY = pos;
				//posY = Math.min(pos, lastPosY);
				velY = Math.max(velY, 0.0f);
				break;
			case South:
				posY = pos - sizeY;
				//posY = Math.max(pos - sizeY, lastPosY);
				velY = Math.min(velY, 0.0f);
				hitGround();
				currentStandingPlatform = object;
				break;
			case West:
				posX = pos;
				//posX = Math.min(pos, lastPosX);
				velX = Math.max(velX, 0.0f);
				break;
			case East:
				posX = pos - sizeX;
				//posX = Math.max(pos - sizeX, lastPosX);
				velX = Math.min(velX, 0.0f);
				break;
			}
		}
	}
	
	public void collideWithPlatform(float pos, LevelObject object){
		if(flightTime > 0.0f){
			posY = pos - sizeY;
			//posY = Math.max(pos - sizeY, lastPosY);
			velY = Math.min(velY, 0.0f)*RECOIL_PERCENT;
			velX = velX*RECOIL_TANGENT_PERCENT;
			hitGround();
			currentStandingPlatform = object;
		}
		else{
			if(input.yAxis <= 0.75f){
				posY = pos - sizeY;
				//posY = Math.max(pos - sizeY, lastPosY);
				velY = Math.min(velY, 0.0f);
				hitGround();
				currentStandingPlatform = object;
			}
		}
	}
	
	private void hitGround(){
		onGround = true;
		if(!onGroundLast){
			Assets.GetSound("hit_ground").playAsSoundEffect(1.0f, 1.0f, false);
		}
	}
	
	protected abstract Attack GetAttackNormalAir(Direction dir);
	protected abstract Attack GetAttackNormalGround(Direction dir);
	protected abstract Attack GetAttackSpecial(Direction dir);
	
	protected abstract void handleStep(double delta);
	protected abstract void handleRender(double delta);
}