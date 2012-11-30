package eecs285.proj4.game;

import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller;
import net.java.games.input.Component.Identifier;

public class InputSuperNintendo extends Input {
	private Controller controller;
	
	private static final Button ATTACK_PRIMARY = Identifier.Button._3;
	private static final Button ATTACK_SECONDARY = Identifier.Button._0;
	private static final Button JUMP_FIRST = Identifier.Button._2;
	private static final Button JUMP_SECOND = Identifier.Button._1;
	
	// Super Smartjoy 2
	//_0 = X
	//_1 = A
	//_2 = B
	//_3 = Y
	//_4 = select
	//_5 = start
	//_6 = left_bumper
	//_7 = right_bumber
	
	public InputSuperNintendo(Controller controller){
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
		
		controller.poll();
		
		normalAttack = controller.getComponent(ATTACK_PRIMARY).getPollData() > 0.75f;
		specialAttack = controller.getComponent(ATTACK_SECONDARY).getPollData() > 0.75f;
		jump = controller.getComponent(JUMP_FIRST).getPollData() > 0.75f
			|| controller.getComponent(JUMP_SECOND).getPollData() > 0.75f;
	
		xAxis = controller.getComponent(Identifier.Axis.X).getPollData();
		yAxis = controller.getComponent(Identifier.Axis.Y).getPollData();
	}
}