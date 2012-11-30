package eecs285.proj4.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.SelectableObject;
import eecs285.proj4.util.Window;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

public class ScreenMenu implements GameState{
	protected Window window;
	private ArrayList<SelectableObject> menuItems;
	private int currentMenuItem;
	
	private float mouseX;
	private float mouseY;
	private boolean mouseLeftClick;
	private boolean moveDown;
	private boolean moveUp;
	private boolean selectKey;
	private boolean selectItem;
	private boolean exitKey;
	private boolean exitState;
	
	private boolean firstFrame;

	public ScreenMenu(){
		this(new Window(0.0f, 0.0f, 1.0f, 1.0f));
	}
	
	public ScreenMenu(Window window){
		this.window = window;
		currentMenuItem = 0;
	}
	
	protected void initMenuItems(ArrayList<SelectableObject> menuItems){
		this.menuItems = menuItems;
	}
	
	public void onActivate(){
		firstFrame = true; 
		mouseLeftClick = false;
		moveDown = false;
		moveUp = false;
		selectKey = false;
		selectItem = false;
		exitKey = false;
		exitState = false;
		
		// TODO : this is kind of odd... but might be necessary
		for(SelectableObject object : menuItems){
			object.resetState();
		}
	}
	
	public void onDeactivate(){}
	
	public void getInput(double delta) {
		mouseX = ((float)Mouse.getX() / DisplayInfo.GetWidth()) * window.getSizeX() + window.getLeft();
		mouseY = ((DisplayInfo.GetHeight() - (float)Mouse.getY()) / DisplayInfo.GetHeight()) * window.getSizeY() + window.getTop();
		mouseLeftClick = Mouse.isButtonDown(0);

		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(Controller controller : controllers){
			controller.poll();
		}
		
		// Key Up
		boolean tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W));
		for(Controller controller : controllers){
			// TODO : this is not desirable
			if (controller.toString().indexOf("mouse") != -1) continue;
			
			Component component = controller.getComponent(Identifier.Axis.Y);
			if(component != null){
				if(component.getPollData() <= -0.75f){
					tempKeyPress = true;
				}
			}
		}
				
		if(tempKeyPress && !moveUp){
			currentMenuItem = Math.max((currentMenuItem - 1), 0);
		}
		moveUp = tempKeyPress;
		
		// Key Down
		tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S));
		for(Controller controller : controllers){
			// TODO : this is not desirable
			if (controller.toString().indexOf("mouse") != -1) continue;
			
			Component component = controller.getComponent(Identifier.Axis.Y);
			if(component != null){
				if(component.getPollData() >= 0.75f){
					tempKeyPress = true;
				}
			}
		}
		
		if(tempKeyPress && !moveDown){
			currentMenuItem = Math.min((currentMenuItem + 1), menuItems.size()-1);
		}
		moveDown = tempKeyPress;
		
		// Key Select
		tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_RETURN));
		for(Controller controller : controllers){
			Component component = controller.getComponent(Identifier.Button._0);
			if(component != null){
				if(component.getPollData() >= 0.75f){
					System.out.println(controller);
					tempKeyPress = true;
				}
			}
			
			component = controller.getComponent(Identifier.Button._7);
			if(component != null){
				if(component.getPollData() >= 0.75f){
					tempKeyPress = true;
				}
			}
		}
		
		if(tempKeyPress && !selectKey && !firstFrame){
			selectItem = true;
		}
		else{
			selectItem = false;
		}
		selectKey = tempKeyPress;
		
		// Key Exit
		tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_BACK));
		for(Controller controller : controllers){
			Component component = controller.getComponent(Identifier.Button._1);
			if(component != null){
				if(component.getPollData() >= 0.75f){
					tempKeyPress = true;
				}
			}
			
			component = controller.getComponent(Identifier.Button._6);
			if(component != null){
				if(component.getPollData() >= 0.75f){
					tempKeyPress = true;
				}
			}
		}
		
		if(tempKeyPress && !exitKey && !firstFrame){
			exitState = true;
		}
		else{
			exitState = false;
		}
		exitKey = tempKeyPress;
	}

	public void step(double delta) {
		window.step(delta);
		
		if(exitState){
			Game.popGameState();
			return;
		}
		
		int counter = 0;
		for(SelectableObject object : menuItems){
			if(mouseX >= object.getLeftEdge()
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
}