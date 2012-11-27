package eecs285.proj4.game;

import net.java.games.input.Controller;
import net.java.games.input.Component.Identifier;

public class InputXbox extends Input {
	private Controller controller;
	
	public InputXbox(Controller controller){
		this.controller = controller;
		
		analogStick = true;
	}
	
	public void getInput(){
		a = controller.getComponent(Identifier.Button.A).getPollData() > 0.75f;
		b = controller.getComponent(Identifier.Button.B).getPollData() > 0.75f;
		x = controller.getComponent(Identifier.Button.X).getPollData() > 0.75f;
		y = controller.getComponent(Identifier.Button.Y).getPollData() > 0.75f;
	
		xAxis = controller.getComponent(Identifier.Axis.X).getPollData();
		yAxis = controller.getComponent(Identifier.Axis.Y).getPollData();
	}
}