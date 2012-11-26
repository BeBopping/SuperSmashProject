package eecs285.proj4.game;

public class BattleInfo {
	private Fighter[] fighters;
	private String levelName;
	private int minutes;
	private int stock;
	
	public void setFighters(Fighter[] fighters){
		this.fighters = fighters;
	}
	
	public void setLevel(String levelName){
		this.levelName = levelName;
	}
	
	public void setMinutes(int minutes){
		this.minutes = minutes;
	}
	
	public void setStock(int stock){
		this.stock = stock;
	}
	
	public Fighter[] getFighters(){
		return fighters;
	}
	
	public String getLevel(){
		return levelName;
	}
	
	public int getMinutes(){
		return minutes;
	}
	
	public int getStock(){
		return stock;
	}
}