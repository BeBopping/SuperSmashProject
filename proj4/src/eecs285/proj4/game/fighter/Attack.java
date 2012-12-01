package eecs285.proj4.game.fighter;

import org.newdawn.slick.Color;
import org.omg.PortableServer.CurrentOperations;

import eecs285.proj4.game.Assets;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.SmallSprite;
import eecs285.proj4.util.UtilObject;

public class Attack {
	private Fighter owner;
	private CollisionBox[] boxes;
	private SpriteTimer[] sprites;
	
	private float duration;
	private float extraTime;		// After the last sprite, the player cannot perform another action 
	//private float maxChargeTime;
	
	private float currentTime;
	public boolean[] hitPlayers;
	
	public Attack(CollisionBox[] boxes, SpriteTimer[] sprites, Fighter owner, float extraTime){
		this.owner = owner;
		this.boxes = boxes;
		this.sprites = sprites;
		this.extraTime = extraTime;
		hitPlayers = new boolean[]{false, false, false, false, false, false, false, false};
		
		for(SpriteTimer spriteTimer : sprites){
			duration += spriteTimer.delay;
		}
		
		for(CollisionBox box : this.boxes){
			if(duration < box.delay + box.duration){
				System.out.println(owner + " Has an attack with a collision box that lasts longer than it's sprites...");
				System.exit(1);
			}
		}
		
		// This allows for things like changing direction imediately after the move is complete.
		duration += 0.001f;
	}
	
	public void step(double delta){
		currentTime += delta;
	}
	
	public void debugRender(double delta){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime)){
				UtilObject collisionBox = box.getBox(currentTime, owner);
				Render.render(Assets.GetTexture("square"), 
								collisionBox.getLeftEdge(), 
								collisionBox.getRightEdge(), 
								collisionBox.getTopEdge(), 
								collisionBox.getBottomEdge(),
								Color.red);
			}
		}
	}
	
	public boolean GetStationaryInAir(){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime) && box.isStationaryInAir){
				return true;
			}
		}
		return false; //currentTime > duration;
	}
	
	public boolean GetStationaryOnGround(){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime) && box.isStationaryOnGround){
				return true;
			}
		}
		// Returns true if we are in the "Extra time" segment.
		return currentTime > duration;
	}
	
	public boolean GetOverrideGravity(){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime) && box.isOverridingGravity){
				return true;
			}
		}
		return false;
	}
	
	public boolean GetCanChangeDirection(){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime) && !box.canChangeDirection){
				return false;
			}
		}
		return true;
	}
	
	public boolean isComplete(){
		return currentTime >= duration + extraTime;
	}
	
	public CollisionBox[] getCollisionBoxes(){
		return boxes;
	}
	
	public float getCurrentTime(){
		return currentTime;
	}
	
	public SmallSprite getSmallSprite(){
		float total = 0.0f;
		for(int i=0; i<sprites.length; i++){
			total += sprites[i].delay;
			if(currentTime < total){
				return sprites[i].sprite;
			}
		}
		//System.exit(1);
		return null;
	}
}