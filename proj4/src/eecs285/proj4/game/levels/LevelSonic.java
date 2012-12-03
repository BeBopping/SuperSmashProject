package eecs285.proj4.game.levels;

import eecs285.proj4.game.GameAssets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.UtilObject;

public class LevelSonic extends Level{
	
	public LevelSonic(){
		super(-12.0f, 12.0f,-20.0f, 10.0f);
		
		levelName = "Second Level";
		minOutline = new UtilObject(-6.0f, 6.0f, 0.0f, 7.5f);
		background = GameAssets.GetTexture("level_sonic_background2");
		
		platformObjects.add(new LevelObjectStretch(GameAssets.GetSprite("level_sonic_platform"), -6.5f, -3.5f, 3.5f, 4.0f));
		platformObjects.add(new LevelObjectStretch(GameAssets.GetSprite("level_sonic_platform"), 3.5f, 6.5f, 3.5f, 4.0f));
		platformObjects.add(new LevelObjectStretch(GameAssets.GetSprite("level_sonic_platform"), -1.5f, 1.5f, 1.0f, 1.5f));
		solidObjects.add(new LevelObjectStretch(GameAssets.GetSprite("level_sonic_ground"), -8.0f, 8.0f, 6.0f, 7.0f));
		solidObjects.add(new MovingLevelBlock(GameAssets.GetSprite("level_sonic_block"), -2.5f, -1.0f, -0.5f, 1.0f, false));
		solidObjects.add(new MovingLevelBlock(GameAssets.GetSprite("level_sonic_block"), 1.0f, 2.5f, 2.0f, 3.5f, true));
		
		startX[0] = -5.0f;
		startY[0] = 3.5f;
		startX[1] = 5.0f;
		startY[1] = 3.5f;
		startX[2] = 2.0f;
		startY[2] = 6.0f;
		startX[3] = -2.0f;
		startY[3] = 6.0f;

		Game.setMusic(GameAssets.GetMusic("music_sonic"));
	}
	public void step(double delta){
		for(LevelObject object : solidObjects){
			object.step(delta);
		}
		
		for(LevelObject object : platformObjects){
			object.step(delta);
		}
	}

	public void render(double delta){
		Render.renderBackground(background);
		
		for(LevelObject object : solidObjects){
			object.render(delta);
		}
		
		for(LevelObject object : platformObjects){
			object.render(delta);
		}
	}
}