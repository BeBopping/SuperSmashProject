package eecs285.proj4.game.levels;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.util.Render;

public class LevelHardCoded extends Level {
	public LevelHardCoded(){
		super(-10.0f, 10.0f, -10.0f, 10.0f);
		
		levelName = "First Level";
		
		background = Assets.GetTexture("default_level_background");
		
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("platform"), -7.0f, 0.0f, 3.0f, 4.0f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("floor"), -8.0f, 8.0f, 6.0f, 7.0f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("floor"), 8.0f, 9.0f, 3.0f, 7.0f));

		startX[0] = -3.0f;
		startY[0] = 3.0f;
		startX[1] = 6.0f;
		startY[1] = 6.0f;
		startX[2] = -6.0f;
		startY[2] = 6.0f;
		startX[3] = 0.0f;
		startY[3] = 6.0f;
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