package eecs285.proj4.game.Levels;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Levels.Level;
import eecs285.proj4.util.Render;

public class LevelHardCoded extends Level {
	public LevelHardCoded(){
		super(0.0f, 100.0f, 0.0f, 100.0f);
		
		levelName = "First Level";
		
		background = Assets.GetTexture("default_level_background");
		
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("platform"), 25.0f, 50.0f, 75.0f, 80.0f));

		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("floor"), 25.0f, 75.0f, 85.0f, 90.0f));
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