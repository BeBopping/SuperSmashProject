package eecs285.proj4.game.Levels;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import eecs285.proj4.util.UtilObject;

public abstract class Level extends UtilObject {
	public String levelName;
	public Texture background;
	public ArrayList<LevelObject> solidObjects;
	public ArrayList<LevelObject> platformObjects;

	public Level(float left, float right, float top, float bottom) {
		super(left, right, top, bottom);
		solidObjects = new ArrayList<LevelObject>();
		platformObjects = new ArrayList<LevelObject>();
	}
}