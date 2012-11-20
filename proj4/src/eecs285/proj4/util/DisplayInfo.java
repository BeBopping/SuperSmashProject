package eecs285.proj4.util;

public class DisplayInfo {
	private static int windowWidth;
	private static int windowHeight;
	
	public static int GetWidth(){
		return windowWidth;
	}
	
	public static int GetHeight(){
		return windowHeight;
	}
	
	public static void SetDisplaySize(int width, int height){
		windowWidth = width;
		windowHeight = height;
	}
}