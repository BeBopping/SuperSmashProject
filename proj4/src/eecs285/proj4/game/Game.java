package eecs285.proj4.game;

import static org.lwjgl.opengl.GL11.*;

import java.util.EmptyStackException;
import java.util.Stack;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import eecs285.proj4.game.screens.ScreenTitle;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.DisplayInfo;

public class Game {

	private double lastTime;
	private Stack<GameState> gameStates;
	private GameState savedGameState;
	private boolean stateChange;
	private final boolean FixedFPS = true;
	
	private static final long TimerTicksPerSecond = Sys.getTimerResolution();
	private static int FPS = 60;
	private static double SPF = (1.0d/(double)FPS);
	private static Game thisGame;
	
	public static void pushGameState(GameState gameState){
		thisGame.stateChange = true;
		if(!thisGame.gameStates.empty()){
			thisGame.savedGameState = thisGame.gameStates.peek();
		}
		
		thisGame.gameStates.push(gameState);
		System.out.println("Pushed onto stack: " + thisGame.gameStates.size());
		thisGame.resetScreenPos();
	}
	
	public static GameState popGameState(){
		GameState gameState = thisGame.gameStates.pop();
		System.out.println("Popped off of stack: " + thisGame.gameStates.size());
		thisGame.resetScreenPos();
		
		thisGame.stateChange = true;
		thisGame.savedGameState = gameState;
		
		return gameState;
	}
	
	// Initialize game
	private Game(int width, int height){
		DisplayInfo.SetDisplaySize(width, height);
		Game.thisGame = this;
		gameStates = new Stack<GameState>();
		lastTime = getTime();
		
		// Create Display
		try{
			Display.setDisplayMode(new DisplayMode(DisplayInfo.GetWidth(),DisplayInfo.GetHeight()));
			Display.create();
			glEnable(GL_TEXTURE_2D);
			glShadeModel(GL_SMOOTH);
			// TODO Isaiah: We may or may not want to use depth
			glDisable(GL_DEPTH_TEST);
			glDisable(GL_LIGHTING);
			
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			glClearDepth(1);
			
			glDisable(GL_DITHER);
			
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			//glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			
			//glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
			//glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );
			
			//glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			//glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			
			//glPixelStorei( GL_UNPACK_ALIGNMENT,1 ); //this line already is in there
			//glPixelStorei( GL_PACK_ALIGNMENT,1 ); //add this line
			
			//glViewport(0,0,DisplayInfo.GetWidth(),DisplayInfo.GetHeight());
			//glMatrixMode(GL_MODELVIEW);
			
			//glMatrixMode(GL_PROJECTION);
			//glLoadIdentity();
			//glOrtho(0, DisplayInfo.GetWidth(), DisplayInfo.GetHeight(), 0, 1, -1);
			//glMatrixMode(GL_MODELVIEW);
			
			resetScreenPos();
		}
		catch(LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		Assets.InitializeTextures();
		Assets.InitializeSounds();
		Assets.InitializeFonts();
	}
	
	private void resetScreenPos(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0f, 1.0f,
				1.0f, 0.0f,
				1.0f, -1.0f);
	}
	
	private double getTime(){
		return (double)Sys.getTime() / (double)TimerTicksPerSecond;
	}
	
	private void run(GameState firstState){
		pushGameState(firstState);
		
		try{
			while(!Display.isCloseRequested()){
				
				double delta = 0.0d;
				if(FixedFPS){
					// Wait to sync with screen
					Display.sync(FPS);
					delta = SPF;
				}
				else{
					double currentTime = getTime();
					delta = currentTime - lastTime;
					
					if(delta == 0.0f){
						continue;
					}
					lastTime = currentTime;
					
					if(delta > SPF){
						continue;
					}
				}
				
				GameState currentState = gameStates.peek();
				
				// Update stateChange
				if(stateChange){
					if(savedGameState != null){
						savedGameState.onDeactivate();
						savedGameState = null;
					}
					
					currentState.onActivate();
					stateChange = false;
				}
				
				// Update everything
				currentState.getInput(delta);
				currentState.step(delta);
					// TODO Isaiah: If we don't use depth, change this...
					glClear(GL_COLOR_BUFFER_BIT);// | GL_DEPTH_BUFFER_BIT);
				currentState.prerender(delta);
				currentState.render(delta);
				
				// Finalize display
				Display.update();
			}
		}
		catch(EmptyStackException except){
			// Not an error
		}
		finally{
			Display.destroy();
		}
	}
	
	public static void main(String[] argv){
		Game game = new Game(800, 600);
		game.run(new ScreenTitle());
	}
}