package eecs285.proj4.input;

import org.lwjgl.input.Keyboard;

public class InputKeyboard extends Input {
	public InputKeyboard(){
		analogStick = false;
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
		
		menuSelect = (Keyboard.isKeyDown(Keyboard.KEY_RETURN) || Keyboard.isKeyDown(Keyboard.KEY_SPACE));
		menuBack = (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_BACK));
		
		normalAttack = (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_PERIOD) || Keyboard.isKeyDown(Keyboard.KEY_N));
		specialAttack = (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_SLASH) || Keyboard.isKeyDown(Keyboard.KEY_M));
		jump = (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_SEMICOLON))
			|| (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_APOSTROPHE))
			|| (Keyboard.isKeyDown(Keyboard.KEY_SPACE));
	
		xAxis = ((Keyboard.isKeyDown(Keyboard.KEY_D)? 1.0f: 0.0f) - (Keyboard.isKeyDown(Keyboard.KEY_A)? 1.0f: 0.0f));
		yAxis = ((Keyboard.isKeyDown(Keyboard.KEY_S)? 1.0f: 0.0f) - (Keyboard.isKeyDown(Keyboard.KEY_W)? 1.0f: 0.0f));
	}
}