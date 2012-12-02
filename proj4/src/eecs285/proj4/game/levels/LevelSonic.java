package eecs285.proj4.game.levels;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.UtilObject;

public class LevelSonic extends Level{
	
	//private MovingLevelBlock movingBlock;
	
	public LevelSonic(){
		super(-12.0f, 12.0f,-20.0f, 10.0f);
		//movingBlock = new MovingLevelBlock(Assets.GetSprite("level_sonic_block"), -1.0f, 1.0f, 3.0f, 5.0f);
		
		levelName = "Second Level";
		minOutline = new UtilObject(-6.0f, 6.0f, 0.0f, 7.5f);
		background = Assets.GetTexture("level_sonic_background2");
		
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("level_sonic_platform"), -7.5f, -5.5f, 3.0f, 3.5f));
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("level_sonic_platform"), 5.5f, 7.5f, 3.0f, 3.5f));
		solidObjects.add(new LevelObjectStretch(Assets.GetSprite("level_sonic_ground"), -8.0f, 8.0f, 6.0f, 7.0f));
		solidObjects.add(new MovingLevelBlock(Assets.GetSprite("level_sonic_block"), -2.5f, -1.0f, 1.0f, 2.5f));
		
		startX[0] = -7.0f;
		startY[0] = 3.0f;
		startX[1] = 7.0f;
		startY[1] = 3.0f;
		startX[2] = 4.0f;
		startY[2] = 6.0f;
		startX[3] = -4.0f;
		startY[3] = 6.0f;
		
	}
	public void step(double delta){
		for(LevelObject object : solidObjects){
			object.step(delta);
		}
		
		for(LevelObject object : platformObjects){
			object.step(delta);
		}
		//movingBlock.step(delta);
		
	}

	public void render(double delta){
		Render.renderBackground(background);
		
		for(LevelObject object : solidObjects){
			object.render(delta);
		}
		
		for(LevelObject object : platformObjects){
			object.render(delta);
		}
		
		//movingBlock.render(delta);
	}
}