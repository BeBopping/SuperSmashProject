package eecs285.proj4.game.levels;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.UtilObject;

public abstract class Level {
	public String levelName;
	public Texture background;
	public UtilObject maxOutline;
	public UtilObject minOutline;
	public ArrayList<LevelObject> solidObjects;
	public ArrayList<LevelObject> platformObjects;
	public float[] spawnX;
	public float[] spawnY;
	public float[] startX;
	public float[] startY;
	
	public Level(float left, float right, float top, float bottom) {
		solidObjects = new ArrayList<LevelObject>();
		platformObjects = new ArrayList<LevelObject>();
		
		maxOutline = new UtilObject(left, right, top, bottom);
		
		spawnX = new float[8];
		spawnY = new float[8];
		startX = new float[8];
		startY = new float[8];
		
		// Default values... Should be over-written by actual level
		spawnX[0] = maxOutline.getCenterX() - 2.0f;
		spawnY[0] = maxOutline.getCenterY() + 4.0f;
		spawnX[1] = maxOutline.getCenterX() + 2.0f;
		spawnY[1] = maxOutline.getCenterY() + 4.0f;
		spawnX[2] = maxOutline.getCenterX() - 6.0f;
		spawnY[2] = maxOutline.getCenterY() + 4.0f;
		spawnX[3] = maxOutline.getCenterX() + 6.0f;
		spawnY[3] = maxOutline.getCenterY() + 4.0f;
		spawnX[4] = maxOutline.getCenterX() - 2.0f;
		spawnY[4] = maxOutline.getCenterY();
		spawnX[5] = maxOutline.getCenterX() + 2.0f;
		spawnY[5] = maxOutline.getCenterY();
		spawnX[6] = maxOutline.getCenterX() - 6.0f;
		spawnY[6] = maxOutline.getCenterY();
		spawnX[7] = maxOutline.getCenterX() + 6.0f;
		spawnY[7] = maxOutline.getCenterY();
		
		for(int i=0; i<8; i++){
			startX[i] = spawnX[i];
			startY[i] = spawnY[i];
		}
	}
	
	public abstract void step(double delta);
	public abstract void render(double delta);
}