package eecs285.proj4.game.fighter;

import org.newdawn.slick.Color;

import eecs285.proj4.game.Assets;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.UtilObject;

public class Attack {
	private CollisionBox[] boxes;
	private float duration;
	//private float maxChargeTime;
	
	private float currentTime;
	public boolean[] hitPlayers;
	
	public Attack(CollisionBox[] boxes){
		this.boxes = boxes;
		hitPlayers = new boolean[]{false, false, false, false, false, false, false, false};
		
		for(CollisionBox box : this.boxes){
			if(duration < box.delay + box.duration){
				duration = box.delay + box.duration;
			}
		}
		
		// This allows for things like changing direction imediately after the move is complete.
		duration += 0.01f;
	}
	
	public void step(double delta){
		currentTime += delta;
	}
	
	public void debugRender(double delta, UtilObject object, boolean facingLeft){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime)){
				UtilObject collisionBox = box.getBox(currentTime, facingLeft);
				Render.render(Assets.GetTexture("square"), 
								object.getCenterX() + collisionBox.getLeftEdge(), 
								object.getCenterX() + collisionBox.getRightEdge(), 
								object.getBottomEdge() + collisionBox.getTopEdge(), 
								object.getBottomEdge() + collisionBox.getBottomEdge(),
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
		return false;
	}
	
	public boolean GetStationaryOnGround(){
		for(CollisionBox box : boxes){
			if(box.isAlive(currentTime) && box.isStationaryOnGround){
				return true;
			}
		}
		return false;
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
		return currentTime >= duration;
	}
	
	public CollisionBox[] getCollisionBoxes(){
		return boxes;
	}
	
	public float getCurrentTime(){
		return currentTime;
	}
}