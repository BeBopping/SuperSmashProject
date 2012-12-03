package eecs285.proj4.game.levels;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.UtilObject;

public class LevelMario extends Level {
	public LevelMario(){
		super(-15.0f, 15.0f, -20.0f, 10.0f);
		
		levelName = "First Level";
		minOutline = new UtilObject(-8.5f, 9.5f, 0.0f, 7.5f);
		background = Assets.GetTexture("level_mario_background");
		
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("level_mario_platform"), -7.0f, 0.0f, 3.0f, 4.0f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_mario_floor"), -8.0f, 9.0f, 6.0f, 7.0f));
		solidObjects.add(new LevelObjectStretch(Assets.GetSprite("level_mario_pipe"), 8.0f, 9.0f, 3.0f, 6.0f));

		// Starting points for each character
		startX[0] = -3.0f;
		startY[0] = 3.0f;
		startX[1] = 6.0f;
		startY[1] = 6.0f;
		startX[2] = -6.0f;
		startY[2] = 6.0f;
		startX[3] = 0.0f;
		startY[3] = 6.0f;

		Game.setMusic(Assets.GetMusic("music_mario"));
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