package eecs285.proj4.game.levels;

import eecs285.proj4.game.GameAssets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.util.Render;
import eecs285.proj4.util.UtilObject;

public class LevelRayman extends Level {
	
	public LevelRayman(){
		super(-15.0f, 15.0f, -20.0f, 10.0f);
		
		levelName = "Rayman Level";
		minOutline = new UtilObject(-9.5f, 9.5f, 0.0f, 7.5f);
		background = GameAssets.GetTexture("level_rayman_background");
		
		solidObjects.add(new LevelObjectRepeating(GameAssets.GetSprite("level_rayman_ground"), -8.0f, 8.0f, 7.0f, 8.0f));
		solidObjects.add(new LevelObjectStretch(GameAssets.GetSprite("level_rayman_groundL"), -9.0f, -8.0f, 7.0f, 8.0f));
		solidObjects.add(new LevelObjectStretch(GameAssets.GetSprite("level_rayman_groundR"), 8.0f, 9.0f, 7.0f, 8.0f));
		
		platformObjects.add(new MovingBalloon(GameAssets.GetSprite("level_rayman_balloon"), 17.25f, 18.0f, 5.0f, 5.75f));
		platformObjects.add(new MovingBalloon(GameAssets.GetSprite("level_rayman_balloon"), 17.25f, 18.0f, 15.0f, 15.75f));
		platformObjects.add(new MovingBalloon(GameAssets.GetSprite("level_rayman_balloon"), 17.25f, 18.0f, 25.0f, 25.75f));
		platformObjects.add(new MovingBalloon(GameAssets.GetSprite("level_rayman_balloon"), 17.25f, 18.0f, 0.0f, 0.75f));
		platformObjects.add(new MovingBalloon(GameAssets.GetSprite("level_rayman_balloon"), 17.25f, 18.0f, 10.0f, 10.75f));
		platformObjects.add(new MovingBalloon(GameAssets.GetSprite("level_rayman_balloon"), 17.25f, 18.0f, 20.0f, 20.75f));

		// Starting points for each character
		startX[0] = -6.0f;
		startY[0] = 1.0f;
		startX[1] = 6.0f;
		startY[1] = 1.0f;
		startX[2] = -2.0f;
		startY[2] = 7.0f;
		startX[3] = 2.0f;
		startY[3] = 7.0f;

		Game.setMusic(GameAssets.GetMusic("music_rayman"));
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