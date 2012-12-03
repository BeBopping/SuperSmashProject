package eecs285.proj4.game.screens;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import eecs285.proj4.game.GameAssets;
import eecs285.proj4.game.Game;
import eecs285.proj4.game.SelectableObject;
import eecs285.proj4.game.input.Input;
import eecs285.proj4.game.input.InputDetector;
import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.Window;

public class ScreenMenu implements GameState{
	protected Window window;
	protected Input[] controllers;
	
	private ArrayList<SelectableObject> menuItems;
	private int currentMenuItem;
	
	private float mouseX;
	private float mouseY;
	private boolean mouseLeftClick;
	private boolean selectItem;
	private boolean exitState;
	
	private boolean firstFrame;
	private boolean mouseEnabled;

	public ScreenMenu(){
		this(new Window(0.0f, 0.0f, 1.0f, 1.0f));
	}
	
	public ScreenMenu(Window window){
		this.window = window;
		this.controllers = InputDetector.getAllInputDevices();
		currentMenuItem = 0;
		mouseEnabled = false;
		
		float aspectRatio = (float)DisplayInfo.GetWidth() / (float) DisplayInfo.GetHeight();
		
		if(aspectRatio >= 1.0f){
			float levelHalfHeight = (window.getSizeX()*0.5f) / aspectRatio;
			window = new Window(window.getLeft(), 
								window.getRight(), 
								window.getCenterY() - levelHalfHeight, 
								window.getCenterY() + levelHalfHeight);
		}
		else{
			float levelHalfWidth = aspectRatio / (window.getSizeY()*0.5f);
			window = new Window(window.getCenterX() - levelHalfWidth, 
								window.getCenterX() + levelHalfWidth, 
								window.getTop(), 
								window.getBottom());
		}
	}
	
	protected void initMenuItems(ArrayList<SelectableObject> menuItems){
		this.menuItems = menuItems;
	}
	
	public void onActivate(){
		firstFrame = true; 
		mouseLeftClick = false;
		selectItem = false;
		exitState = false;
		
		// TODO : this is kind of odd... but might be necessary
		for(SelectableObject object : menuItems){
			object.resetState();
		}
		
		Game.setMusic(GameAssets.GetMusic("music_menu"));
	}
	
	public void onDeactivate(){}
	
	public void getInput(double delta) {
		mouseX = ((float)Mouse.getX() / DisplayInfo.GetWidth()) * window.getSizeX() + window.getLeft();
		mouseY = ((DisplayInfo.GetHeight() - (float)Mouse.getY()) / DisplayInfo.GetHeight()) * window.getSizeY() + window.getTop();
		mouseLeftClick = Mouse.isButtonDown(0);

		selectItem = false;
		exitState = false;
		
		for(Input input : controllers){
			input.getInput();
			
			if(input.yAxis <= -0.75f && input.yAxisLast > -0.75f){
				currentMenuItem = Math.max((currentMenuItem - 1), 0);
			}
			
			if(input.yAxis >= 0.75f && input.yAxisLast < 0.75f){
				currentMenuItem = Math.min((currentMenuItem + 1), menuItems.size()-1);
			}

			if(input.menuSelect && !input.menuSelectLast){
				selectItem = true;
			}

			if(input.menuBack && !input.menuBackLast){
				exitState = true;
			}
		}
	}

	public void step(double delta) {
		window.step(delta);
		
		if(exitState){
			GameAssets.GetSound("menu_back").playAsSoundEffect(1.0f, 1.0f, false);
			Game.popGameState();
			return;
		}
		
		int counter = 0;
		for(SelectableObject object : menuItems){
			if(mouseEnabled
			&& mouseX >= object.getLeftEdge()
			&& mouseX <= object.getRightEdge()
			&& mouseY >= object.getTopEdge()
			&& mouseY <= object.getBottomEdge())
			{
				currentMenuItem = -1;
				object.setHover(true);
				object.setClick(mouseLeftClick);
			}
			else if(counter != currentMenuItem){
				object.setHover(false);
			}

			if(!firstFrame){
				object.step(delta);
			}
			
			counter++;
		}
		
		if(currentMenuItem >= 0){
			menuItems.get(currentMenuItem).setHover(true);
			if(selectItem){
				menuItems.get(currentMenuItem).setClick(true);
			}
		}
		
		firstFrame = false;
	}

	public void prerender(double delta){
		window.setupRender();
	}
	
	public void render(double delta) {
		for(SelectableObject object: menuItems){
			object.render(delta);
		}
	}
	
	public Input[] getControllers(){
		return controllers;
	}
}