package everfree;

import java.awt.MouseInfo;

public class Mouse {
	
	private double mx;
	private double my;
	
	void tick(){
		mx = MouseInfo.getPointerInfo().getLocation().x - Game.frame.getLocation().getX();
		my = MouseInfo.getPointerInfo().getLocation().y - Game.frame.getLocation().getY();
	}
	
	int getMX(){
		return (int) mx;
	}
	int getMY(){
		return (int) my;
	}
}
