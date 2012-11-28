package eecs285.proj4.game.fighter;

import static eecs285.proj4.game.fighter.FighterState.*;

import eecs285.proj4.game.Direction;
import eecs285.proj4.game.Input;
import eecs285.proj4.game.MovingObject;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

import org.newdawn.slick.Color;

public abstract class Fighter extends MovingObject {
	protected static final float GRAVITY = 100.0f;
	protected static final float MAXHORIZONTAL = 100.0f;
	protected static final float MAXVERTICAL = 1000.0f;
	
	protected String fighterName;
	protected FighterTrait fighterTrait;
	protected Input input;
	
	private int hitPercent;
	private int stock;
	private int kills;
	private int deaths;
	private int suicides;
	
	// ALl units use meters and seconds
	protected float minWalkSpeed;
	protected float maxWalkSpeed;
	protected float runSpeed;
	protected float firstJumpSpeed;
	protected float firstJumpMinTime;
	protected float firstJumpMaxTime;
	protected float firstJumpLiniencyTime;
	protected float secondJumpSpeed;
	protected float secondJumpMinTime;
	protected float secondJumpMaxTime;
	protected float maxFallSpeed;
	protected float maxFallSpeedHorizontal;

	public FighterState fighterState;
	protected boolean canMove;
	protected boolean facingLeft;
	protected boolean onGround;
	//protected boolean ducking;
	protected boolean canDoubleJump;
	protected double flightTime;		// Is flying if > 0.0f
	protected double jumpTime;			// Is jumping if > 0.0f
	
	protected Sprite currentSprite;
	protected float visualWidth;
	protected float visualHeight;
	protected Color playerColor;
	
	public Fighter(String name, FighterTrait trait,
			float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		fighterName = name;
		fighterTrait = trait;
		fighterState = None;
		canMove = true;
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
	
	public String toString(){
		return fighterName;
	}
	
	public void getInput(double delta){
		input.getInput();
	}
	
	public void setSpawn(float xCenter, float yBase){
		posX = xCenter - sizeX*0.5f;
		posY = yBase - sizeY;
	}

	public void setVelocity(float velocityX, float velocityY){
		velX = velocityX;
		velY = velocityY;
	}
	
	public void step(double delta){
		lastPosX = posX;
		lastPosY = posY;
		
		if(onGround){
			canDoubleJump = true;
		}
		
		doDuck(delta);
		doMovement(delta);
		doJump(delta);
		doAction(delta);
		doPlayerDirection(delta);
		
		if(velX > MAXHORIZONTAL) velX = MAXHORIZONTAL;
		if(velX < -MAXHORIZONTAL) velX = -MAXHORIZONTAL;
		if(velY > MAXVERTICAL) velY = MAXVERTICAL;
		if(velY < -MAXVERTICAL) velY = -MAXVERTICAL;

		posX += delta * velX;
		posY += delta * velY;
		
		onGround = false;
	}
	
	private void doJump(double delta){
		// TODO : return when not allowed to jump
		
		if(onGround){
			if(input.jump){
				if(jumpTime >= -firstJumpLiniencyTime && jumpTime <= 0.0d){
					jumpTime = delta;
				}
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
				if(jumpTime >= -firstJumpLiniencyTime && jumpTime <= 0.0d){
					jumpTime -= delta;
				}
				else if(jumpTime > 0.0d && jumpTime <= firstJumpMaxTime){
					jumpTime += delta;
				}
			}
			else{
				if(jumpTime > 0.0d && jumpTime < firstJumpMinTime){
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
		//TODO : return in situations where we don't allow movement
		
		velX += (float)((double)input.xAxis * delta * 100.0d);
		velY += delta * GRAVITY;
		
		if(onGround){
			if(fighterState == None || fighterState == Ducking){
				velX *= 0.75f;
				
				if(velX > maxWalkSpeed) velX = maxWalkSpeed;
				if(velX < -maxWalkSpeed) velX = -maxWalkSpeed;
			}
		}
		else{
			if(fighterState == None){
				if(velX > maxFallSpeedHorizontal) velX = maxFallSpeedHorizontal;
				if(velX < -maxFallSpeedHorizontal) velX = -maxFallSpeedHorizontal;
				if(velY > maxFallSpeed) velY = maxFallSpeed;
				if(velY < -maxFallSpeed) velY = -maxFallSpeed;
			}
		}
		
		if(fighterState == Flying){
			if(flightTime <= 0.0f){
				fighterState = None;
			}
			
			flightTime -= delta;
		}
	}
	
	private void doAction(double delta){
		
	}
	
	private void doPlayerDirection(double delta){
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
		
		// Alternate function
		//if(input.xAxis != 0.0f){
		//	facingLeft = input.xAxis < 0.0f;
		//}
	}
	
	public void render(double delta){
		Render.render(currentSprite, getCenterX()-visualWidth*0.5f, getCenterX()+visualWidth*0.5f,
									 getBottomEdge()-visualHeight, getBottomEdge(), playerColor, facingLeft);
		
		handleRender(delta);
	}
	
	public void collideWithSolid(Direction dir, float pos){
		if(fighterState == Flying){
			switch(dir){
			case North:
				posY = Math.min(pos, lastPosY);
				velY = Math.abs(this.velY)*0.5f;
				break;
			case South:
				posY = Math.max(pos - sizeY, lastPosY);
				velY = -Math.abs(this.velY)*0.5f;
				onGround = true;
				break;
			case East:
				posX = Math.min(pos, lastPosX);
				velX = -Math.abs(this.velX)*0.5f;
				break;
			case West:
				posX = Math.max(pos - sizeX, lastPosX);
				velX = Math.abs(this.velX)*0.5f;
				break;
			}
		}
		else{
			handleCollideWithSolid(dir, pos);
		}
	}
	
	public void collideWithPlatform(float pos){
		if(fighterState == Flying){
			posY = Math.max(pos - sizeY, lastPosY);
			velY = -Math.abs(this.velY)*0.5f;
			onGround = true;
		}
		else{
			handleCollideWithPlatform(pos);
		}
	}

	protected abstract void handleStep(double delta);
	protected abstract void handleRender(double delta);
	protected abstract void handleCollideWithSolid(Direction dir, float pos);
	protected abstract void handleCollideWithPlatform(float pos);
}