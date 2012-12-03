package eecs285.proj4.game.fighter;

import static eecs285.proj4.game.Direction.*;
import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Direction;
import eecs285.proj4.util.UtilObject;

public class FighterSpyro extends Fighter {
	private static final float WIDTH = 2.0f;
	private static final float HEIGHT = 2.1f;
	
	public FighterSpyro(FighterTrait trait){
		super("Fighter Spyro", trait, 0.0f - WIDTH*0.5f, 0.0f + WIDTH*0.5f, 0.0f - HEIGHT, 0.0f);
		
		maxWalkSpeed = 5.0f;
		forwardAcceleration = 15.0f;
		reverseAcceleration = 40.0f;
		firstJumpSpeed = 10.0f;
		firstJumpMinTime = 0.05f;
		firstJumpMaxTime = 0.45f;
		firstJumpLiniencyTime = 0.35f;
		secondJumpSpeed = 11.5f;
		secondJumpMinTime = 0.05f;
		secondJumpMaxTime = 0.50f;
		secondJumpLiniencyTime = 0.5f;
		maxFallSpeed = 15.0f;
		maxFallSpeedHorizontal = 6.0f;
		
		visualWidth = 5.0f;
		visualHeight = 5.0f;
		
		fighterTexture = Assets.GetTexture("fighter_spyro");
		distancePerRunSprite = 0.5f;
	}

	// *******************************************************************
	// Normal Ground
	protected Attack GetAttackNormalGround(Direction dir){
		if(dir == null){
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-2.0f, 0.0f, -3.0f, -1.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -3.0f, -1.0f);		// From players center Base
			boxes[0].delay = 0.2f;
			boxes[0].duration = 0.2f;
			boxes[0].damage = 12;
			boxes[0].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 8.0f;
			boxes[0].hitSpeedY = -6.0f;
			boxes[0].flightTime = 0.45f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better
			
			boxes[1] = new CollisionBox();
			boxes[1].startBox = boxes[0].endBox; 								// From players center Base
			boxes[1].endBox = new UtilObject(0.0f, 2.0f, -1.95f, 0.05f);		// From players center Base
			boxes[1].delay = boxes[0].delay + boxes[0].duration;
			boxes[1].duration = 0.2f;
			boxes[1].damage = 12;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 8.0f;
			boxes[1].hitSpeedY = -6.0f;
			boxes[1].flightTime = 0.45f;
			boxes[1].stunTime = 0.1f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better
			
			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.65f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.0f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = true;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = false;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackGround[0], 0.25f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackGround[1], 0.2f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackGround[2], 0.2f);
			
			return new Attack(boxes, sprites, this, 0.25f);	// Delay after moves are done
		}
		else if(dir == North){
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-1.0f, 1.50f, -3.0f, -1.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-1.0f, 1.5f, -3.0f, -1.5f);		// From players center Base
			boxes[0].delay = 0.2f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 10;
			boxes[0].healthScaler = 250.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = -0.2f;
			boxes[0].hitSpeedY = -12.0f;
			boxes[0].flightTime = 0.35f;
			boxes[0].stunTime = 0.2f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better
			
			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.5f, 2.0f, -2.5f, -0.5f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.5f, 2.0f, -2.5f, -0.5f);		// From players center Base
			boxes[1].delay = 0.2f;
			boxes[1].duration = 0.3f;
			boxes[1].damage = 10;
			boxes[1].healthScaler = 250.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.2f;
			boxes[1].hitSpeedY = -12.0f;
			boxes[1].flightTime = 0.35f;
			boxes[1].stunTime = 0.2f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better

			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.65f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.0f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = true;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = false;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackGroundUp[0], 0.25f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackGroundUp[1], 0.2f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackGroundUp[2], 0.2f);
			
			return new Attack(boxes, sprites, this, 0.1f);	// Delay after moves are done
		}
		else if(dir == South){
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 2.5f, -1.0f, 0.1f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.5f, -1.0f, 0.1f);		// From players center Base
			boxes[0].delay = 0.3f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 10;
			boxes[0].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = -2.0f;
			boxes[0].hitSpeedY = 10.0f;
			boxes[0].flightTime = 0.45f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better

			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(-2.5f, 0.0f, -1.0f, 0.1f); 	// From players center Base
			boxes[1].endBox = new UtilObject(-2.5f, 0.0f, -1.0f, 0.1f);		// From players center Base
			boxes[1].delay = boxes[0].delay;
			boxes[1].duration = boxes[0].duration;
			boxes[1].damage = 10;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 2.0f;
			boxes[1].hitSpeedY = 10.0f;
			boxes[1].flightTime = 0.45f;
			boxes[1].stunTime = 0.1f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better

			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.9f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.0f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = true;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = false;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackGroundDown[0], 0.3f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackGroundDown[1], 0.3f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackGroundDown[2], 0.3f);
			
			return new Attack(boxes, sprites, this, 0.2f);	// Delay after moves are done
		}
		// On ground, we can only do a move in the direction we are facing.
		//else if((dir == East && !facingLeft) || (dir == West && facingLeft)){
		else{
			facingLeft = input.xAxis < 0.0f;
			
			CollisionBox[] boxes = new CollisionBox[2];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-0.5f, 2.25f, -1.5f, 0.05f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-0.5f, 2.25f, -1.5f, 0.05f);		// From players center Base
			boxes[0].delay = 0.5f;
			boxes[0].duration = 0.5f;
			boxes[0].damage = 12;
			boxes[0].healthScaler = 200.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 12.0f;
			boxes[0].hitSpeedY = -3.0f;
			boxes[0].flightTime = 0.35f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better
			
			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[1].delay = 0.0f;
			boxes[1].duration = 1.2f;
			boxes[1].damage = 0;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.0f;
			boxes[1].hitSpeedY = 0.0f;
			boxes[1].flightTime = 0.0f;
			boxes[1].stunTime = 0.0f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[5];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackGroundForward[0], 0.25f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackGroundForward[1], 0.25f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackGroundForward[2], 0.50f);
			sprites[3] = new SpriteTimer(FighterSprites.normalAttackGroundForward[1], 0.10f);
			sprites[4] = new SpriteTimer(FighterSprites.normalAttackGroundForward[0], 0.10f);
			
			return new Attack(boxes, sprites, this, 0.0f);	// Delay after moves are done
		}
	}
	
	// *******************************************************************
	// Normal Air
	protected Attack GetAttackNormalAir(Direction dir){
		if(dir == null){
			CollisionBox[] boxes = new CollisionBox[1];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-1.25f, 1.25f, -2.5f, 0.00f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-1.25f, 1.25f, -2.5f, 0.00f);		// From players center Base
			boxes[0].delay = 0.0f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 7;
			boxes[0].healthScaler = 600.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.0f;
			boxes[0].hitSpeedY = -5.0f;
			boxes[0].flightTime = 0.45f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 10; 			// The higher the better

			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackAir[0], 0.1f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackAir[1], 0.1f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackAir[2], 0.1f);
			
			return new Attack(boxes, sprites, this, 0.25f);	// Delay after moves are done
		}
		else if(dir == North){

			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-1.5f, 1.5f, -5.25f, -2.5f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-1.5f, 1.5f, -5.25f, -2.5f);		// From players center Base
			boxes[0].delay = 0.1f;
			boxes[0].duration = 0.2f;
			boxes[0].damage = 10;
			boxes[0].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = -0.2f;
			boxes[0].hitSpeedY = -15.0f;
			boxes[0].flightTime = 0.35f;
			boxes[0].stunTime = 0.2f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better
			
			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.25f, 1.75f, -4.0f, -1.5f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.25f, 1.75f, -4.0f, -1.5f);		// From players center Base
			boxes[1].delay = 0.1f;
			boxes[1].duration = 0.2f;
			boxes[1].damage = 10;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = -0.2f;
			boxes[1].hitSpeedY = -15.0f;
			boxes[1].flightTime = 0.35f;
			boxes[1].stunTime = 0.2f;
			boxes[1].isStationaryInAir = false;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better

			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.35f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.0f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = false;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = false;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackAirUp[0], 0.1f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackAirUp[1], 0.1f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackAirUp[2], 0.15f);
			
			return new Attack(boxes, sprites, this, 0.1f);	// Delay after moves are done
		}
		else if(dir == South){
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-1.0f, 1.25f, -0.5f, 0.05f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-1.0f, 1.25f, -0.5f, 0.05f);		// From players center Base
			boxes[0].delay = 0.05f;
			boxes[0].duration = 0.2f;
			boxes[0].damage = 8;
			boxes[0].healthScaler = 200.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = -0.05f;
			boxes[0].hitSpeedY = 5.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.05f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better

			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.25f, 1.35f, -2.0f, -0.5f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.25f, 1.35f, -2.0f, -0.5f);		// From players center Base
			boxes[1].delay = 0.05f;
			boxes[1].duration = 0.2f;
			boxes[1].damage = 8;
			boxes[1].healthScaler = 200.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.05f;
			boxes[1].hitSpeedY = 10.0f;
			boxes[1].flightTime = 0.25f;
			boxes[1].stunTime = 0.05f;
			boxes[1].isStationaryInAir = false;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better
			
			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.35f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.0f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = false;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = false;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackAirDown[0], 0.1f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackAirDown[1], 0.15f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackAirDown[2], 0.15f);
			
			return new Attack(boxes, sprites, this, 0.1f);	// Delay after moves are done
		}
		else if((dir == East && !facingLeft) || (dir == West && facingLeft)){
			CollisionBox[] boxes = new CollisionBox[2];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.25f, 2.0f, -1.75f, -0.05f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.25f, 2.0f, -1.75f, 0.05f);		// From players center Base
			boxes[0].delay = 0.1f;
			boxes[0].duration = 0.25f;
			boxes[0].damage = 9;
			boxes[0].healthScaler = 350.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 8.0f;
			boxes[0].hitSpeedY = -6.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better
			
			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[1].delay = 0.0f;
			boxes[1].duration = 0.55f;
			boxes[1].damage = 0;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.0f;
			boxes[1].hitSpeedY = 0.0f;
			boxes[1].flightTime = 0.0f;
			boxes[1].stunTime = 0.0f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better

			SpriteTimer[] sprites = new SpriteTimer[3];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackAirForward[0], 0.1f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackAirForward[1], 0.25f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackAirForward[2], 0.25f);
			
			return new Attack(boxes, sprites, this, 0.1f);	// Delay after moves are done
		}
		else{ // West
			CollisionBox[] boxes = new CollisionBox[2];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-2.0f, -0.25f, -3.00f, -0.05f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-2.0f, -0.25f, -3.0f, 0.05f);		// From players center Base
			boxes[0].delay = 0.1f;
			boxes[0].duration = 0.35f;
			boxes[0].damage = 9;
			boxes[0].healthScaler = 350.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = -8.0f;
			boxes[0].hitSpeedY = -6.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = false;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = false;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 0; 			// The higher the better
			
			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[1].delay = 0.0f;
			boxes[1].duration = 0.55f;
			boxes[1].damage = 0;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.0f;
			boxes[1].hitSpeedY = 0.0f;
			boxes[1].flightTime = 0.0f;
			boxes[1].stunTime = 0.0f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 			// The higher the better

			SpriteTimer[] sprites = new SpriteTimer[4];
			sprites[0] = new SpriteTimer(FighterSprites.normalAttackAirBackward[0], 0.100f);
			sprites[1] = new SpriteTimer(FighterSprites.normalAttackAirBackward[1], 0.175f);
			sprites[2] = new SpriteTimer(FighterSprites.normalAttackAirBackward[2], 0.175f);
			sprites[3] = new SpriteTimer(FighterSprites.normalAttackAirBackward[0], 0.100f);
			
			return new Attack(boxes, sprites, this, 0.0f);	// Delay after moves are done
		}
	}

	// *******************************************************************
	// Special
	protected Attack GetAttackSpecial(Direction dir){
		if(dir == null){
			CollisionBox[] boxes = new CollisionBox[5];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-1.5f, 1.5f, -4.75f, -3.75f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-1.5f, 1.5f, -4.75f, -3.75f);		// From players center Base
			boxes[0].delay = 0.4f;
			boxes[0].duration = 0.3f;
			boxes[0].damage = 18;
			boxes[0].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.1f;
			boxes[0].hitSpeedY = -10.0f;
			boxes[0].flightTime = 0.3f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 2; 			// The higher the better

			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(-1.5f, 1.5f, -1.25f, -0.25f); 	// From players center Base
			boxes[1].endBox = new UtilObject(-1.5f, 1.5f, -1.25f, -0.25f);		// From players center Base
			boxes[1].delay = 0.4f;
			boxes[1].duration = 0.3f;
			boxes[1].damage = 18;
			boxes[1].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.1f;
			boxes[1].hitSpeedY = 10.0f;
			boxes[1].flightTime = 0.3f;
			boxes[1].stunTime = 0.1f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = true;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 2; 			// The higher the better
			
			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(-2.25f, -1.25f, -4.0f, -1.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(-2.25f, -1.25f, -4.0f, -1.0f);		// From players center Base
			boxes[2].delay = 0.4f;
			boxes[2].duration = 0.3f;
			boxes[2].damage = 18;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 10.f;
			boxes[2].hitSpeedY = -0.1f;
			boxes[2].flightTime = 0.3f;
			boxes[2].stunTime = 0.1f;
			boxes[2].isStationaryInAir = true;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = true;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 2; 			// The higher the better
			
			boxes[3] = new CollisionBox();
			boxes[3].startBox = new UtilObject(1.25f, 2.25f, -4.0f, -1.0f); 	// From players center Base
			boxes[3].endBox = new UtilObject(1.25f, 2.25f, -4.0f, -1.0f);		// From players center Base
			boxes[3].delay = 0.4f;
			boxes[3].duration = 0.3f;
			boxes[3].damage = 18;
			boxes[3].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[3].hitSpeedX = -10.0f;
			boxes[3].hitSpeedY = -0.1f;
			boxes[3].flightTime = 0.3f;
			boxes[3].stunTime = 0.1f;
			boxes[3].isStationaryInAir = true;
			boxes[3].isStationaryOnGround = true;
			boxes[3].isOverridingGravity = true;
			boxes[3].canChangeDirection = false;
			boxes[3].attackPriority = 2; 			// The higher the better
			
			boxes[4] = new CollisionBox();
			boxes[4].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[4].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[4].delay = 0.0f;
			boxes[4].duration = 1.05f;
			boxes[4].damage = 0;
			boxes[4].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[4].hitSpeedX = 0.0f;
			boxes[4].hitSpeedY = 0.0f;
			boxes[4].flightTime = 0.0f;
			boxes[4].stunTime = 0.0f;
			boxes[4].isStationaryInAir = true;
			boxes[4].isStationaryOnGround = true;
			boxes[4].isOverridingGravity = true;
			boxes[4].canChangeDirection = false;
			boxes[4].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[4];
			sprites[0] = new SpriteTimer(FighterSprites.specialAttack[0], 0.2f);
			sprites[1] = new SpriteTimer(FighterSprites.specialAttack[1], 0.2f);
			sprites[2] = new SpriteTimer(FighterSprites.specialAttack[2], 0.3f);
			sprites[3] = new SpriteTimer(FighterSprites.specialAttack[3], 0.35f);
			
			return new Attack(boxes, sprites, this, 0.0f);	// Delay after moves are done
		}
		else if(dir == North){
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(1.0f, 2.25f, -5.0f, -3.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(1.0f, 2.25f, -5.0f, -3.0f);		// From players center Base
			boxes[0].delay = 0.3f;
			boxes[0].duration = 0.15f;
			boxes[0].damage = 13;
			boxes[0].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.3f;
			boxes[0].hitSpeedY = -10.0f;
			boxes[0].flightTime = 0.35f;
			boxes[0].stunTime = 0.15f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 2; 			// The higher the better

			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(-2.05f, 2.25f, -5.0f, -3.0f); 	// From players center Base
			boxes[1].endBox = new UtilObject(-2.05f, 2.25f, -5.0f, -3.0f);		// From players center Base
			boxes[1].delay = boxes[0].delay + boxes[0].duration;
			boxes[1].duration = 0.15f;
			boxes[1].damage = 13;
			boxes[1].healthScaler = 250.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.00f;
			boxes[1].hitSpeedY = -10.0f;
			boxes[1].flightTime = 0.35f;
			boxes[1].stunTime = 0.1f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = true;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 
			
			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.6f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 250.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.0f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = false;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = false;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[4];
			sprites[0] = new SpriteTimer(FighterSprites.specialAttackUp[0], 0.15f);
			sprites[1] = new SpriteTimer(FighterSprites.specialAttackUp[1], 0.15f);
			sprites[2] = new SpriteTimer(FighterSprites.specialAttackUp[2], 0.15f);
			sprites[3] = new SpriteTimer(FighterSprites.specialAttackUp[3], 0.15f);
			
			return new Attack(boxes, sprites, this, 0.3f);	// Delay after moves are done
		}
		else if(dir == South){
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(-1.25f, 1.25f, -1.5f, 0.05f); 	// From players center Base
			boxes[0].endBox = new UtilObject(-1.25f, 1.25f, -1.5f, 0.05f);		// From players center Base
			boxes[0].delay = 0.3f;
			boxes[0].duration = 0.2f;
			boxes[0].damage = 12;
			boxes[0].healthScaler = 250.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 0.0f;
			boxes[0].hitSpeedY = 10.0f;
			boxes[0].flightTime = 0.25f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 2; 			// The higher the better

			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(-2.0f, 2.25f, -1.5f, 0.05f); 	// From players center Base
			boxes[1].endBox = new UtilObject(-2.0f, 2.25f, -1.5f, -0.05f);		// From players center Base
			boxes[1].delay = boxes[0].delay + boxes[0].duration;
			boxes[1].duration = 0.3f;
			boxes[1].damage = 12;
			boxes[1].healthScaler = 250.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 0.00f;
			boxes[1].hitSpeedY = 10.0f;
			boxes[1].flightTime = 0.25f;
			boxes[1].stunTime = 0.1f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = false;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 0; 
			
			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = boxes[0].delay;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 200.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.00f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = true;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = true;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 
			
			SpriteTimer[] sprites = new SpriteTimer[6];
			sprites[0] = new SpriteTimer(FighterSprites.specialAttackDown[0], 0.15f);
			sprites[1] = new SpriteTimer(FighterSprites.specialAttackDown[1], 0.15f);
			sprites[2] = new SpriteTimer(FighterSprites.specialAttackDown[2], 0.2f);
			sprites[3] = new SpriteTimer(FighterSprites.specialAttackDown[3], 0.3f);
			sprites[4] = new SpriteTimer(FighterSprites.specialAttackDown[1], 0.15f);
			sprites[5] = new SpriteTimer(FighterSprites.specialAttackDown[0], 0.15f);
			
			return new Attack(boxes, sprites, this, 0.0f);	// Delay after moves are done
		}
		// Special move, we can only do a move in the direction we are facing.
		//else if((dir == East && !facingLeft) || (dir == West && facingLeft)){
		else{
			facingLeft = input.xAxis < 0.0f;
			
			CollisionBox[] boxes = new CollisionBox[3];
			boxes[0] = new CollisionBox();
			boxes[0].startBox = new UtilObject(0.0f, 2.0f, -4.74f, -3.0f); 	// From players center Base
			boxes[0].endBox = new UtilObject(0.0f, 2.0f, -4.75f, -3.0f);		// From players center Base
			boxes[0].delay = 0.4f;
			boxes[0].duration = 0.25f;
			boxes[0].damage = 15;
			boxes[0].healthScaler = 350.0f;			// scale = 1 + health/healthScaler
			boxes[0].hitSpeedX = 5.0f;
			boxes[0].hitSpeedY = -7.0f;
			boxes[0].flightTime = 0.35f;
			boxes[0].stunTime = 0.1f;
			boxes[0].isStationaryInAir = true;
			boxes[0].isStationaryOnGround = true;
			boxes[0].isOverridingGravity = true;
			boxes[0].canChangeDirection = false;
			boxes[0].attackPriority = 2; 			// The higher the better

			boxes[1] = new CollisionBox();
			boxes[1].startBox = new UtilObject(0.0f, 2.0f, -4.75f, -0.25f); 	// From players center Base
			boxes[1].endBox = new UtilObject(0.0f, 2.0f, -4.75f, -0.25f);		// From players center Base
			boxes[1].delay = boxes[0].delay + boxes[0].duration;
			boxes[1].duration = 0.25f;
			boxes[1].damage = 15;
			boxes[1].healthScaler = 350.0f;			// scale = 1 + health/healthScaler
			boxes[1].hitSpeedX = 7.00f;
			boxes[1].hitSpeedY = -4.0f;
			boxes[1].flightTime = 0.25f;
			boxes[1].stunTime = 0.1f;
			boxes[1].isStationaryInAir = true;
			boxes[1].isStationaryOnGround = true;
			boxes[1].isOverridingGravity = true;
			boxes[1].canChangeDirection = false;
			boxes[1].attackPriority = 2; 			// The higher the better
			
			boxes[2] = new CollisionBox();
			boxes[2].startBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f); 	// From players center Base
			boxes[2].endBox = new UtilObject(0.001f, 0.0f, 0.0001f, 0.0f);		// From players center Base
			boxes[2].delay = 0.0f;
			boxes[2].duration = 0.90f;
			boxes[2].damage = 0;
			boxes[2].healthScaler = 300.0f;			// scale = 1 + health/healthScaler
			boxes[2].hitSpeedX = 0.f;
			boxes[2].hitSpeedY = 0.0f;
			boxes[2].flightTime = 0.0f;
			boxes[2].stunTime = 0.0f;
			boxes[2].isStationaryInAir = true;
			boxes[2].isStationaryOnGround = true;
			boxes[2].isOverridingGravity = true;
			boxes[2].canChangeDirection = false;
			boxes[2].attackPriority = 0; 			// The higher the better
			
			SpriteTimer[] sprites = new SpriteTimer[4];
			sprites[0] = new SpriteTimer(FighterSprites.specialAttackForward[0], 0.20f);
			sprites[1] = new SpriteTimer(FighterSprites.specialAttackForward[1], 0.20f);
			sprites[2] = new SpriteTimer(FighterSprites.specialAttackForward[2], 0.25f);
			sprites[3] = new SpriteTimer(FighterSprites.specialAttackForward[3], 0.25f);
			
			return new Attack(boxes, sprites, this, 0.25f);	// Delay after moves are done
		}
	}
	
	protected void handleStep(double delta){
		
	}

	protected void handleRender(double delta){
		
	}
}