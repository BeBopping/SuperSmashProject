package eecs285.proj4.game;

public abstract class Input {
	public float xAxis;
	public float yAxis;
	public boolean a;
	public boolean b;
	public boolean x;
	public boolean y;
	public boolean start;
	public boolean back;
	public boolean bumperLeft;
	public boolean bumperRight;
	protected boolean analogStick;
	
	public boolean usesAnalogStick(){
		return analogStick;
	}
	
	public abstract void getInput();
}