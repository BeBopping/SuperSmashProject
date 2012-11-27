package eecs285.proj4.game.fighter;

import static eecs285.proj4.game.fighter.FighterState.*;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Direction;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class FighterA extends Fighter {
	private static final float WIDTH = 0.8f;
	private static final float HEIGHT = 1.4f;
	private Sprite normalStance;
	
	public FighterA(FighterTrait trait){
		super("Fighter A", trait, 0.0f - WIDTH*0.5f, 0.0f + WIDTH*0.5f, 0.0f - HEIGHT, 0.0f);
		normalStance = Assets.GetSprite("floor");
		
		minWalkSpeed = 0.01f;
		maxWalkSpeed = 4.0f;
		runSpeed = 6.0f;
		firstJumpSpeed = 4.0f;
		firstJumpMaxTime = 1.5f;
		secondJumpSpeed = 5.5f;
		secondJumpMaxTime = 2.0f;
		maxFallSpeed = 20.0f;
		maxFallSpeedHorizontal = 4.0f;
		
		currentSprite = normalStance;
		visualWidth = 1.0f;
		visualHeight = 1.4f;
	}
	
	protected void handleStep(double delta){
		
	}

	protected void handleRender(double delta){
		
	}

	protected void handleCollideWithSolid(Direction dir, float pos){
		switch(dir){
		case North:
			posY = Math.min(pos, lastPosY);
			velY = Math.max(velY, 0.0f);
			break;
		case South:
			posY = Math.max(pos - sizeY, lastPosY);
			velY = Math.min(velY, 0.0f);
			onGround = true;
			//if(fighterState != Flying && fighterState != Ducking){
			//	fighterState = OnGround;
			//}
			break;
		case West:
			posX = Math.min(pos, lastPosX);
			velX = Math.max(velX, 0.0f);
			break;
		case East:
			posX = Math.max(pos - sizeX, lastPosX);
			velX = Math.min(velX, 0.0f);
			break;
		}
	}
	
	protected void handleCollideWithPlatform(float pos){
		this.posY = pos - this.sizeY;
		this.velY = -Math.abs(this.velY);
	}
}