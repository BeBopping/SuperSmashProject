package eecs285.proj4.game.fighter;

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
		
		//minWalkSpeed = 0.01f;
		maxWalkSpeed = 7.0f;
		forwardAcceleration = 20.0f;
		reverseAcceleration = 50.0f;
		//maxWalkSpeedFastestTime = 0.05f;
		firstJumpSpeed = 12.0f;
		firstJumpMinTime = 0.05f;
		firstJumpMaxTime = 0.3f;
		firstJumpLiniencyTime = 0.05f;
		secondJumpSpeed = 10.5f;
		secondJumpMinTime = 0.05f;
		secondJumpMaxTime = 0.3f;
		maxFallSpeed = 20.0f;
		maxFallSpeedHorizontal = 7.0f;
		
		currentSprite = normalStance;
		visualWidth = 1.0f;
		visualHeight = 1.4f;
	}
	
	protected void handleStep(double delta){
		
	}

	protected void handleRender(double delta){
		
	}

	protected void handleCollideWithSolid(Direction dir, float pos){
		
	}
	
	protected void handleCollideWithPlatform(float pos){
	}
}