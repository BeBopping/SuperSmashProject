package eecs285.proj4.game.levels;

import eecs285.proj4.game.Assets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.UtilObject;

public class LevelRayman extends Level {
	
	public LevelRayman(){
		super(-15.0f, 15.0f, -20.0f, 10.0f);
		
		levelName = "Rayman Level";
		minOutline = new UtilObject(-9.5f, 9.5f, 0.0f, 7.5f);
		background = Assets.GetTexture("level_rayman_background");
		
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("level_spyro_bridge"), -4.5f, 4.5f, 1.0f, 2.0f));
		platformObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), 4.5f, 6.5f, 1.0f, 7.0f));
		platformObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -6.5f, -4.5f, 1.0f, 7.0f));
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("level_spyro_block"), 4.5f, 6.5f, 1.0f, 2.0f));
		platformObjects.add(new LevelObjectStretch(Assets.GetSprite("level_spyro_block"), -6.5f, -4.5f, 1.0f, 2.0f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_ground"), -8.5f, 8.5f, 7.0f, 8.0f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 7.2f, 8.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 8.2f, 9.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 9.2f, 10.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 10.2f, 11.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 11.2f, 12.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 12.2f, 13.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 13.2f, 14.2f));
		solidObjects.add(new LevelObjectRepeating(Assets.GetSprite("level_spyro_wall"), -8.5f, 8.5f, 14.2f, 15.2f));
		
		platformObjects.add(new MovingBalloon(Assets.GetSprite("level_rayman_balloon"), 0.0f, 1.5f, 5.0f, 6.5f));

		// Starting points for each character
		startX[0] = -5.0f;
		startY[0] = 1.0f;
		startX[1] = 5.0f;
		startY[1] = 1.0f;
		startX[2] = -3.0f;
		startY[2] = 7.0f;
		startX[3] = 3.0f;
		startY[3] = 7.0f;

		Game.setMusic(Assets.GetMusic("music_rayman"));
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