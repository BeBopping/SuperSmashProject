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
		controller.poll();
		
		a = controller.getComponent(Identifier.Button._0).getPollData() > 0.75f;
		b = controller.getComponent(Identifier.Button._1).getPollData() > 0.75f;
		x = controller.getComponent(Identifier.Button._2).getPollData() > 0.75f;
		y = controller.getComponent(Identifier.Button._3).getPollData() > 0.75f;
	
		xAxis = controller.getComponent(Identifier.Axis.X).getPollData();
		yAxis = controller.getComponent(Identifier.Axis.Y).getPollData();
	}
	
	//_0 = A
	//_1 = B
	//_2 = X
	//_3 = Y
	//_4 = left_bumper
	//_5 = right_bumber
	//_6 = back
	//_7 = start
}