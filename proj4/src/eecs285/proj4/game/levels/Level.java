package eecs285.proj4.game.levels;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.UtilObject;

public abstract class Level extends UtilObject {
	public String levelName;
	public Texture background;
	public ArrayList<LevelObject> solidObjects;
	public ArrayList<LevelObject> platformObjects;
	public float[] spawnX;
	public float[] spawnY;
	public float[] startX;
	public float[] startY;
	
	public Level(float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		solidObjects = new ArrayList<LevelObject>();
		platformObjects = new ArrayList<LevelObject>();
		
		spawnX = new float[8];
		spawnY = new float[8];
		startX = new float[8];
		startY = new float[8];
		
		// Default values... Should be over-written by actual level
		spawnX[0] = getCenterX() - 2.0f;
		spawnY[0] = getCenterY();
		spawnX[1] = getCenterX() + 2.0f;
		spawnY[1] = getCenterY();
		spawnX[2] = getCenterX() - 6.0f;
		spawnY[2] = getCenterY();
		spawnX[3] = getCenterX() + 6.0f;
		spawnY[3] = getCenterY();
		spawnX[4] = getCenterX() - 2.0f;
		spawnY[4] = getCenterY() - 4.0f;
		spawnX[5] = getCenterX() + 2.0f;
		spawnY[5] = getCenterY() - 4.0f;
		spawnX[6] = getCenterX() - 6.0f;
		spawnY[6] = getCenterY() - 4.0f;
		spawnX[7] = getCenterX() + 6.0f;
		spawnY[7] = getCenterY() - 4.0f;
		
		for(int i=0; i<8; i++){
			startX[i] = spawnX[i];
			startY[i] = spawnY[i];
		}
	}
	
	public abstract void step(double delta);
	public abstract void render(double delta);
}