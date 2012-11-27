package eecs285.proj4.game;

public abstract class Input {
	public float xAxis;
	public float yAxis;
	public boolean primaryAttack;
	public boolean secondaryAttack;
	public boolean jump;
	public boolean start;
	public boolean back;
	public boolean block;
	protected boolean analogStick;
	
	public boolean usesAnalogStick(){
		return analogStick;
	}
	
	public abstract void getInput();
}