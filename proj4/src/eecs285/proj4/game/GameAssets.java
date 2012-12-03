package eecs285.proj4.game;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import eecs285.proj4.game.fighter.Fighter;
import eecs285.proj4.game.fighter.FighterMario;
import eecs285.proj4.game.fighter.FighterSpyro;
import eecs285.proj4.game.fighter.FighterTrait;
import eecs285.proj4.game.levels.Level;
import eecs285.proj4.game.levels.LevelMario;
import eecs285.proj4.game.levels.LevelRayman;
import eecs285.proj4.game.levels.LevelSonic;
import eecs285.proj4.game.levels.LevelSpyro;
import eecs285.proj4.util.Sprite;

public class GameAssets {
	private static boolean texturesInitialized = false;
	private static boolean soundsInitialized = false;
	private static boolean fontsInitialized = false;
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	private static HashMap<String, TrueTypeFont> fonts = new HashMap<String, TrueTypeFont>();
	private static HashMap<String, Audio> sounds = new HashMap<String, Audio>();
	private static HashMap<String, Audio> music = new HashMap<String, Audio>();
	
	public static void InitializeTextures(){
		if(!texturesInitialized){
			try {
				Texture texture; 
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Button.png"));
				textures.put("button", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Menu_Selector.png"));
				textures.put("select", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Sky.png"));
				textures.put("start_menu_background", texture);

				texture = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("TitleScreen.jpg"));
				textures.put("title_screen_background", texture);
				textures.put("about_screen_background", texture);
				textures.put("how_to_play_screen_background", texture);
				//textures.put("level_mario_background", texture);
				
				sprites.put("example_sprite", new Sprite(texture, 0.0f, 0.1f, 0.0f, 0.1f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Platforms.png"));
				textures.put("platforms", texture);
				//sprites.put("level_mario_floor", new Sprite(texture, 0.0f, 0.0625f, 0.0f, 0.0625f));
				sprites.put("level_mario_platform", new Sprite(texture, 0.0625f, 0.125f, 0.0f, 0.0625f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("marioStageBackground.png"));
				textures.put("level_mario_background", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("marioStageGround.png"));
				sprites.put("level_mario_floor", new Sprite(texture, 0.0f, 1.0f, 0.0f, 1.0f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("marioStagePipe.png"));
				sprites.put("level_mario_pipe", new Sprite(texture, 0.0f, 1.0f, 0.0f, 0.75f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("sonicLevelBG.png"));
				textures.put("level_sonic_background", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("sonicLevelBG2.png"));
				textures.put("level_sonic_background2", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("sonicLevelBlock.png"));
				sprites.put("level_sonic_block", new Sprite(texture, 0.0f, 1.0f, 0.0f, 1.0f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("sonicLevelGround.png"));
				sprites.put("level_sonic_ground", new Sprite(texture, 0.0f, 0.625f, 0.0f, 1.0f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("sonicLevelPlatform.png"));
				sprites.put("level_sonic_platform", new Sprite(texture, 0.0f, 1.0f, 0.0f, 1.0f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("spyrolevelBG.png"));
				textures.put("level_spyro_background", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("spyrolevelBlock.png"));
				sprites.put("level_spyro_block", new Sprite(texture, 0.0f, .75f, 0.0f, .75f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("spyrolevelBridge.png"));
				sprites.put("level_spyro_bridge", new Sprite(texture, 0.0f, .75f, 0.0f, .75f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("spyrolevelGround.png"));
				sprites.put("level_spyro_ground", new Sprite(texture, 0.0f, 0.75f, 0.0f, 0.5f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("spyrolevelWall.png"));
				sprites.put("level_spyro_wall", new Sprite(texture, 0.0f, .74f, 0.0f, 0.624f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("raymanLevelBG.png"));
				textures.put("level_rayman_background", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("raymanLevelGround.png"));
				sprites.put("level_rayman_ground", new Sprite(texture, 0.0f, 0.75f, 0.0f, 0.75f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("raymanLevelGroundLeft.png"));
				sprites.put("level_rayman_groundL", new Sprite(texture, 0.0f, 0.75f, 0.0f, 0.75f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("raymanLevelGroundRight.png"));
				sprites.put("level_rayman_groundR", new Sprite(texture, 0.0f, 0.75f, 0.0f, 0.75f));
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("raymanLevelBalloon.png"));
				sprites.put("level_rayman_balloon", new Sprite(texture, 0.0f, 1.0f, 0.0f, 1.0f));
				
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Square.png"));
				textures.put("square", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Circle.png"));
				textures.put("circle", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Pointer.png"));
				textures.put("pointer", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("White.png"));
				textures.put("white", texture);
				
				// Fighters...
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("FighterMario.png"));
				textures.put("fighter_mario", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("FighterSpyro.png"));
				textures.put("fighter_spyro", texture);
				
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("FighterSonic.png"));
				textures.put("fighter_sonic", texture);
				
				// Player select fighters
				texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("Fighters.png"));
				textures.put("fighters", texture);
				sprites.put("fighter_mario",  new Sprite(texture, 0.000f, 0.125f, 0.0f, 0.125f));
				sprites.put("fighter_sonic",  new Sprite(texture, 0.125f, 0.250f, 0.0f, 0.125f));
				sprites.put("fighter_rayman", new Sprite(texture, 0.250f, 0.375f, 0.0f, 0.125f));
				sprites.put("fighter_spyro",  new Sprite(texture, 0.375f, 0.500f, 0.0f, 0.125f));
				sprites.put("fighter_empty",  new Sprite(texture, 0.875f, 1.000f, 0.875f, 1.000f));
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			texturesInitialized = true;
		}
	}
	
	public static void InitializeFonts(){
		if(!fontsInitialized){
			Font awtFont = new Font("Times New Roman", Font.BOLD, 48);
			TrueTypeFont font = new TrueTypeFont(awtFont, false);
			fonts.put("times_bold", font);
			fonts.put("title", font);
			fonts.put("percentage", font);
			
			fontsInitialized = true;
		}
	}
	

	// TODO: figure out how to use sounds (Online)
	public static void InitializeSounds(){
		if(!soundsInitialized){
			
			try {
				Audio oggEffect;
				
				// Sound effects
				// Menu
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/Menu1.ogg"));
				sounds.put("menu_move", oggEffect);
				sounds.put("menu_select", oggEffect);
				sounds.put("menu_new_player", oggEffect);

				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/Menu2.ogg"));
				sounds.put("menu_move_2", oggEffect);
				
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/MenuBack.ogg"));
				sounds.put("menu_back", oggEffect);
				
				// Game sounds
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/Explode.ogg"));
				sounds.put("explode", oggEffect);
				
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/SurgeContact.ogg"));
				sounds.put("electric", oggEffect);
				
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/HitGround.ogg"));
				sounds.put("hit_ground", oggEffect);

				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/Jump.ogg"));
				sounds.put("jump", oggEffect);
				
				oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("audio/Jump2.ogg"));
				sounds.put("jump_air", oggEffect);
				
				
				
				// Music
				oggEffect = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("audio/Hybrid Orchestral Rock.ogg"));
				music.put("music_menu", oggEffect);

				oggEffect =  AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("audio/Pipes.ogg"));
				music.put("music_mario", oggEffect);
				
				oggEffect =  AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("audio/Chemical Juice.ogg"));
				music.put("music_sonic", oggEffect);
				
				oggEffect =  AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("audio/Disconnected.ogg"));
				music.put("music_rayman", oggEffect);
				
				oggEffect =  AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("audio/Dark Hollow Moonlight.ogg"));
				music.put("music_spyro", oggEffect);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			soundsInitialized = true;
		}
	}


	public static Texture GetTexture(String label){
		return textures.get(label);
	}
	
	public static Sprite GetSprite(String label){
		return sprites.get(label);
	}
	
	public static TrueTypeFont GetFont(String label){
		return fonts.get(label);
	}
	
	public static Audio GetSound(String label){
		return sounds.get(label);
	}
	
	public static Audio GetMusic(String label){
		return music.get(label);
	}
	
	public static Level GetLevel(String string){
		if(string.equals("level_mario")){
			return new LevelMario();
		}
		else if(string.equals("level_sonic")){
			return new LevelSonic();
		}
		else if(string.equals("level_spyro")){
			return new LevelSpyro();
		}
		else if(string.equals("level_rayman")){
			return new LevelRayman();
		}
		return null;
	}
	
	public static Fighter GetFighter(String string, FighterTrait trait){
		if(string.equals("fighter_mario")){
			return new FighterMario(trait);
		}
		else if(string.equals("fighter_sonic")){
			//return new FighterSonic(trait)();
		}
		else if(string.equals("fighter_rayman")){
			//return new FighterRayman(trait)();
		}
		else if(string.equals("fighter_spyro")){
			return new FighterSpyro(trait);
		}
		return new FighterMario(trait);
	}
	
	private GameAssets(){}
}