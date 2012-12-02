package eecs285.proj4.game.input;

import java.util.ArrayList;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class InputDetector {
	private static Input[] controllers = null;
	
	public static Input[] getAllInputDevices(){
		if(controllers == null){
			init();
		}
		return controllers;
	}
	
	private static void init(){
		ArrayList<Input> inputs = new ArrayList<Input>();
		
		// Add keyboard
		inputs.add(new InputKeyboard());

		// Add Controllers
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(Controller controller : con){
			String controllerName = controller.toString();
			
			//.indexOf("mouse") != -1
			if(controllerName.contains("mouse")){
				// We don't want the mouse
			}
			// Xbox 360 controller
			else if(controllerName.contains("XBOX")){
				inputs.add(new InputXbox360(controller));
			}
			// Super nintendo controller
			else if(controllerName.contains("Super")){
				inputs.add(new InputSuperNintendo(controller));
			}
		}
		
		controllers = inputs.toArray(new Input[]{});
	}
}