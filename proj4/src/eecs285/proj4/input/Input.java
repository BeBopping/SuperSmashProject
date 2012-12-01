package eecs285.proj4.input;

public abstract class Input {
	public float xAxis;
	public float yAxis;
	public boolean normalAttack;
	public boolean specialAttack;
	public boolean jump;
	public boolean start;
	public boolean back;
	public boolean block;
	public boolean menuSelect;
	public boolean menuBack;
	
	public float xAxisLast;
	public float yAxisLast;
	public boolean normalAttackLast;
	public boolean specialAttackLast;
	public boolean jumpLast;
	public boolean startLast;
	public boolean backLast;
	public boolean blockLast;
	public boolean menuSelectLast;
	public boolean menuBackLast;
	
	protected boolean analogStick;
	
	public boolean usesAnalogStick(){
		return analogStick;
	}
	
	public abstract void getInput();
	
	// Must call getInput first
	public boolean getAnyActivity(){
		return (xAxis != 0.0f || yAxis != 0.0f || normalAttack || specialAttack || jump);
	}
}