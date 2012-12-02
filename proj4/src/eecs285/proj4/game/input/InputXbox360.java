package eecs285.proj4.game.input;

import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Component.Identifier;

public class InputXbox360 extends Input {
	private Controller controller;
	
	private static final Button NORMAL_ATTACK = Identifier.Button._2;
	private static final Button SPECIAL_ATTACK = Identifier.Button._3;
	private static final Button JUMP_FIRST = Identifier.Button._0;
	private static final Button JUMP_SECOND = Identifier.Button._1;
	
	// Controller (XBOX 360 For Windows)
	//_0 = A
	//_1 = B
	//_2 = X
	//_3 = Y
	//_4 = left_bumper
	//_5 = right_bumber
	//_6 = back
	//_7 = start
	
	public InputXbox360(Controller controller){
		this.controller = controller;
		
		analogStick = true;
	}
	
	public void getInput(){
		xAxisLast = xAxis;
		yAxisLast = yAxis;
		normalAttackLast = normalAttack;
		specialAttackLast = specialAttack;
		jumpLast = jump;
		startLast = start;
		backLast = back;
		blockLast = block;
		menuSelectLast = menuSelect;
		menuBackLast = menuBack;
		
		controller.poll();

		menuSelect = controller.getComponent(Identifier.Button._0).getPollData() > 0.75f
				  || controller.getComponent(Identifier.Button._7).getPollData() > 0.75f;
		menuBack = controller.getComponent(Identifier.Button._1).getPollData() > 0.75f
				|| controller.getComponent(Identifier.Button._6).getPollData() > 0.75f;
		
		normalAttack = controller.getComponent(NORMAL_ATTACK).getPollData() > 0.75f;
		specialAttack = controller.getComponent(SPECIAL_ATTACK).getPollData() > 0.75f;
		jump = controller.getComponent(JUMP_FIRST).getPollData() > 0.75f
			|| controller.getComponent(JUMP_SECOND).getPollData() > 0.75f;
	
		xAxis = controller.getComponent(Identifier.Axis.X).getPollData();
		yAxis = controller.getComponent(Identifier.Axis.Y).getPollData();
		
		// only allow values from 0.5f to 1.0f;
		if(xAxis > -0.5f && xAxis < 0.5f) xAxis = 0.0f;
		if(yAxis > -0.5f && yAxis < 0.5f) yAxis = 0.0f;
		
		// Map values between 0.0f and 1.0f
		if(xAxis > 0.0f){
			xAxis = (xAxis - 0.5f) * 2.0f;
		}
		else if(xAxis < 0.0f){
			xAxis = (xAxis + 0.5f) * 2.0f;
		}
		
		if(yAxis > 0.0f){
			yAxis = (yAxis - 0.5f) * 2.0f;
		}
		else if(yAxis < 0.0f){
			yAxis = (yAxis + 0.5f) * 2.0f;
		}
	}
}