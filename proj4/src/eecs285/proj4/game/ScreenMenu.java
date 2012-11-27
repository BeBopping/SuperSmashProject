package eecs285.proj4.game;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import eecs285.proj4.util.DisplayInfo;
import eecs285.proj4.util.GameState;
import eecs285.proj4.util.SelectableObject;
import eecs285.proj4.util.Window;

import net.java.games.input.Controller;
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
	
	private boolean firstFrame;

	public ScreenMenu(){
		this(new Window(0.0f, 0.0f, 1.0f, 1.0f));
	}
	
	public ScreenMenu(Window window){
		this.window = window;
		currentMenuItem = 0;
		mouseLeftClick = false;
		moveDown = false;
		moveUp = false;
		selectKey = false;
		selectItem = false;
		firstFrame = true;
	}
	
	protected void initMenuItems(ArrayList<SelectableObject> menuItems){
		this.menuItems = menuItems;
	}
	
	public void onActivate(){}
	public void onDeactivate(){}
	
	public void getInput(double delta) {
		mouseX = ((float)Mouse.getX() / DisplayInfo.GetWidth()) * window.getSizeX() + window.getLeft();
		mouseY = ((DisplayInfo.GetHeight() - (float)Mouse.getY()) / DisplayInfo.GetHeight()) * window.getSizeY() + window.getTop();
		mouseLeftClick = Mouse.isButtonDown(0);
		
		//Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		// Key Up
		boolean tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W));
		//for(Controller controller : controllers){
		//	Component component = controller.getComponent(Identifier.Button.A);
		//	if(component != null){
		//		tempKeyPress = tempKeyPress || (component.getPollData() >= 0.75);
		//	}
		//	
		//	/*
		//	Component[] components = controller.getComponents();
		//	for(Component component : components){
		//		if(component != null){
		//			System.out.println(component.getIdentifier());
		//			System.out.println(component.getName());
		//			System.out.println(component.getPollData());
		//			
		//			//tempKeyPress = tempKeyPress || (component.getPollData() <= -0.75);
		//			//System.out.println(component);
		//			//System.out.println(component.getPollData());
		//		}
		//	}*/
		//}
		if(tempKeyPress && !moveUp){
			currentMenuItem = Math.max((currentMenuItem - 1), 0);
		}
		moveUp = tempKeyPress;
		
		// Key Down
		tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S));
		//for(Controller controller : controllers){
		//	Component component = controller.getComponent(Identifier.Axis.Y);
		//	if(component != null){
		//		tempKeyPress = tempKeyPress || (component.getPollData() >= 0.75);
		//	}
		//}
		if(tempKeyPress && !moveDown){
			currentMenuItem = Math.min((currentMenuItem + 1), menuItems.size()-1);
		}
		moveDown = tempKeyPress;
		
		// Key Select
		tempKeyPress = (Keyboard.isKeyDown(Keyboard.KEY_SPACE) || Keyboard.isKeyDown(Keyboard.KEY_RETURN));
		if(tempKeyPress && !selectKey && !firstFrame){
			selectItem = true;
		}
		else{
			selectItem = false;
		}
		selectKey = tempKeyPress;
	}

	public void step(double delta) {
		window.step(delta);
		
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