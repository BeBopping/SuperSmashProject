package eecs285.proj4.util;

public interface GameState {
	public void onActivate();
	public void onDeactivate();
	
	public void getInput(double delta);
	public void step(double delta);
	public void prerender(double delta);
	public void render(double delta);
}