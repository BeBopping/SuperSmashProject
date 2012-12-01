package eecs285.proj4.game.fighter;

import eecs285.proj4.util.SmallSprite;

public class SpriteTimer {
	public SmallSprite sprite;
	public float delay;

	public SpriteTimer(SmallSprite sprite, float delay){
		this.sprite = sprite;
		this.delay = delay;
	}
}