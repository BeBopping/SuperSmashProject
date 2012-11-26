package eecs285.proj4.game;

import static eecs285.proj4.game.FighterState.*;

import eecs285.proj4.util.Render;
import eecs285.proj4.util.Sprite;

public class FighterA extends Fighter {
	private static final float WIDTH = 5.0f;
	private static final float HEIGHT = 8.0f;
	private Sprite normalStance;
	
	public FighterA(FighterTrait trait, float centerX, float bottom){
		super("Fighter A", trait, centerX - WIDTH*0.5f, centerX + WIDTH*0.5f, bottom - HEIGHT, bottom);
		normalStance = Assets.GetSprite("floor");
	}

	public void step(double delta){
		this.velY += delta * GRAVITY;
		
		if(this.velX > TERMINALX) this.velX = TERMINALX;
		if(this.velX < -TERMINALX) this.velX = -TERMINALX;
		if(this.velY > TERMINALY) this.velY = TERMINALY;
		if(this.velY < -TERMINALY) this.velY = -TERMINALY;
		
		this.posY += delta * this.velY;
		this.posX += delta * this.velX;
	
		if(fighterState == Flying){
			if(flightTime <= 0.0f){
				fighterState = InAir;
			}
			
			flightTime -= delta;
		}
	
	}

	public void render(double delta){
		Render.render(normalStance, this);
	}

	public void getInput(double delta){
		
	}

	public void collideWithSolid(Direction dir, float pos){
		switch(dir){
		case North:
			this.posY = pos;
			break;
		case South:
			this.posY = pos - this.sizeY;
			break;
		case East:
			this.posX = pos - this.sizeX;
			break;
		case West:
			this.posX = pos;
			break;
		}
		
		if(fighterState == Flying){
			switch(dir){
			case North:
				this.posY = pos;
				this.velY = Math.abs(this.velY)*0.5f;
				break;
			case South:
				this.posY = pos - this.sizeY;
				this.velY = -Math.abs(this.velY)*0.5f;
				break;
			case East:
				this.posX = pos - this.sizeX;
				this.velX = -Math.abs(this.velX)*0.5f;
				break;
			case West:
				this.posX = pos;
				this.velX = Math.abs(this.velX)*0.5f;
				break;
			}
		}
	}
	
	public void collideWithPlatform(float pos){
		this.posY = pos - this.sizeY;
		this.velY = -Math.abs(this.velY);
	}
}