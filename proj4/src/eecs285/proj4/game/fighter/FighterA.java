package eecs285.proj4.game.fighter;

import static eecs285.proj4.game.Direction.*;
import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Direction;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;
import eecs285.proj4.util.UtilObject;

public class FighterA extends Fighter {
	private static final float WIDTH = 0.8f;
	private static final float HEIGHT = 1.4f;
	private Sprite normalStance;
	
	public FighterA(FighterTrait trait){
		super("Fighter A", trait, 0.0f - WIDTH*0.5f, 0.0f + WIDTH*0.5f, 0.0f - HEIGHT, 0.0f);
		normalStance = Assets.GetSprite("floor");
		
		maxWalkSpeed = 7.0f;
		forwardAcceleration = 20.0f;
		reverseAcceleration = 50.0f;
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

	// *******************************************************************
	// Normal Ground
	protected Attack GetAttackNormalGround(Direction dir){
		if(dir == null){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 1.5f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 4;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if(dir == North){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-0.5f, 0.5f, -1.0f, 0.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-0.5f, 0.5f, -2.5f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.2f;
			boxes[0].hitSpeedY = -10.0f;
			boxes[0].oppositeHitSpeedX = 0.2f;
			boxes[0].oppositeHitSpeedY = -10.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.2f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if(dir == South){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 0.5f, -0.5f, 0.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 1.5f, -0.5f, -0.0f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.25f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 1.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -0.1f;
			boxes[0].flightTime = 0.05f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			
			return new Attack(boxes, this);
		}
		else if((dir == East && !facingLeft) || (dir == West && facingLeft)){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else{ // West
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
	}
	
	// *******************************************************************
	// Normal Air
	protected Attack GetAttackNormalAir(Direction dir){
		if(dir == null){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 1.5f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 4;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if(dir == North){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-0.5f, 0.5f, -2.0f, -1.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-0.5f, 0.5f, -2.5f, -1.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 600.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.2f;
			boxes[0].hitSpeedY = -10.0f;
			boxes[0].oppositeHitSpeedX = 0.2f;
			boxes[0].oppositeHitSpeedY = -10.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.2f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if(dir == South){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-0.5f, 0.5f, -0.5f, 0.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-0.5f, 0.5f, 0.0f, 0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 8;
			boxes[0].healthScaler = 100.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.0f;
			boxes[0].hitSpeedY = 5.0f;
			boxes[0].oppositeHitSpeedX = 0.0f;
			boxes[0].oppositeHitSpeedY = 5.0f;
			boxes[0].flightTime = 0.15f;
			boxes[0].stunTime = 0.05f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			
			return new Attack(boxes, this);
		}
		else if((dir == East && !facingLeft) || (dir == West && facingLeft)){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else{ // West
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
	}

	// *******************************************************************
	// Special
	protected Attack GetAttackSpecial(Direction dir){
		if(dir == null){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if(dir == North){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if(dir == South){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else if((dir == East && !facingLeft) || (dir == West && facingLeft)){
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
		else{ // West
			// Punch right
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 1.0f, -1.0f, -0.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -1.0f, -0.5f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.1f;
			boxes[0].damage = 6;
			boxes[0].healthScaler = 500.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 10.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].oppositeHitSpeedX = -3.0f;
			boxes[0].oppositeHitSpeedY = -8.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;

			return new Attack(boxes, this);
		}
	}
	
	protected void handleStep(double delta){
		
	}

	protected void handleRender(double delta){
		
	}
}