package eecs285.proj4.game;

import eecs285.proj4.util.UtilObject;

public abstract class SelectableObject extends UtilObject {
	public enum State {NONE, HOVERING, BEGIN_MOUSE_PRESS, CONTINUE_MOUSE_PRESS, END_MOUSE_PRESS}
	
	protected State currentState;
	
	public SelectableObject(float left, float right, float top, float bottom){
		super(left, right, top, bottom);
		currentState = State.NONE;
	}
	
	public void setClick(boolean clicked){
		if(clicked){
			if(currentState != State.BEGIN_MOUSE_PRESS && currentState != State.CONTINUE_MOUSE_PRESS){
				currentState = State.BEGIN_MOUSE_PRESS;

				Assets.GetSound("menu_select").playAsSoundEffect(1.0f, 1.0f, false);
			}
			else{
				currentState = State.CONTINUE_MOUSE_PRESS;
			}
		}
		else{
			if(currentState == State.BEGIN_MOUSE_PRESS || currentState == State.CONTINUE_MOUSE_PRESS){
				currentState = State.END_MOUSE_PRESS;
			}
		}
	}
	
	public void setHover(boolean hovering){
		if(hovering){
			if(currentState == State.NONE || currentState == State.END_MOUSE_PRESS){
				currentState = State.HOVERING;
				
				Assets.GetSound("menu_move").playAsSoundEffect(1.0f, 1.0f, false);
			}
		}
		else{
			if(currentState == State.HOVERING || currentState == State.END_MOUSE_PRESS){
				currentState = State.NONE;
			}
		}
	}
	
	public void resetState(){
		currentState = State.NONE;
	}
	
	public abstract void step(double delta);
	public abstract void render(double delta);
}