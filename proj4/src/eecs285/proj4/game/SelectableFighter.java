package eecs285.proj4.game;

import org.newdawn.slick.Color;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;
import eecs285.proj4.util.UtilObject;

public class SelectableFighter extends UtilObject {
	private static final float MAX_SELECTION_TIME = 0.5f;
	private String fighterName;
	private Sprite fighterImage;
	private float selectTimer;
	private boolean hovering;
	
	public SelectableFighter(String fighterName, Sprite fighterImage, float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		
		this.fighterName = fighterName;
		this.fighterImage = fighterImage;
	}

	public void step(double delta){
		if(selectTimer > 0.0f){
			selectTimer -= delta;
		}
		
		hovering = false;
	}

	public void render(double delta){
		float hoverValue = hovering? 0.8f : 1.0f;
		Color c = new Color(1.0f, hoverValue, hoverValue, 1.0f);
		
		Render.render(fighterImage, this, c, false);

		if(selectTimer > 0.0f){
			float value = (selectTimer / MAX_SELECTION_TIME) * 0.5f + 0.25f;
			Render.render(Assets.GetTexture("white"), this, new Color(1.0f, 1.0f, 1.0f, value));
		}
	}
	
	public void setClick(){
		selectTimer = MAX_SELECTION_TIME;
	}
	
	public void setHover(){
		hovering = true;
	}
	
	public String getFighterName(){
		return fighterName;
	}
}