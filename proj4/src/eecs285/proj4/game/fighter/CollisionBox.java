package eecs285.proj4.game.fighter;

import eecs285.proj4.util.UtilObject;

// Positions are relative to bottom center of character
public class CollisionBox {
	public UtilObject startBox;
	public UtilObject endBox;
	
	public float delay;
	public float duration;
	
	public float hitSpeedX;
	public float hitSpeedY;
	public int damage;
	public float flightTime;
	public float stunTime;
	public float healthScaler;				// scale = 1 + health/healthScaler
	public boolean isStationaryInAir;
	public boolean isStationaryOnGround;
	public boolean isOverridingGravity;
	public boolean canChangeDirection;
	
	public int attackPriority; // The higher the better
	
	public boolean isAlive(double currentTime){
		if(currentTime >= delay && currentTime < duration + delay){
			return true;
		}
		return false;
	}
	
	public UtilObject getBox(double currentTime, Fighter owner){
		float val = (float)((currentTime - (double)delay) / (double)duration);
		
		float left = (startBox.getLeftEdge() + (endBox.getLeftEdge() - startBox.getLeftEdge()) * val);
		float right = (startBox.getRightEdge() + (endBox.getRightEdge() - startBox.getRightEdge()) * val);
		float top = (startBox.getTopEdge() + (endBox.getTopEdge() - startBox.getTopEdge()) * val);
		float bottom = (startBox.getBottomEdge() + (endBox.getBottomEdge() - startBox.getBottomEdge()) * val);
		
		/*
		// Heisenbug!!!!
		if(owner.GetFacingLeft()){
			float temp = left;
			left = -right;
			right = -temp;
		}
		*/
		
		// Solution to heisenbug
		if(owner.GetFacingLeft()){
			left += right;
			right -= left;
			left = -(left + right);
		}
		
		
		UtilObject result = new UtilObject(	owner.getCenterX() + left, owner.getCenterX() + right, 
											owner.getBottomEdge() + top, owner.getBottomEdge() + bottom);
		
		return result;
	}
}