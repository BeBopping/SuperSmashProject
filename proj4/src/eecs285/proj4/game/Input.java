package eecs285.proj4.game;

public abstract class Input {
	public float xAxis;
	public float yAxis;
	public boolean normalAttack;
	public boolean specialAttack;
	public boolean jump;
	public boolean start;
	public boolean back;
	public boolean block;
	
	public float xAxisLast;
	public float yAxisLast;
	public boolean normalAttackLast;
	public boolean specialAttackLast;
	public boolean jumpLast;
	public boolean startLast;
	public boolean backLast;
	public boolean blockLast;
	
	protected boolean analogStick;
	
	public boolean usesAnalogStick(){
		return analogStick;
	}
	
	public abstract void getInput();
}