package everfree;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import everfree.Game.STATE;

public class Menu2{
	
	Button button;
	MouseInput mi;
	
	Runnable button1 = new Runnable(){
		@Override
		public void run() {
			System.out.println("CLICK");
		}
	};
	
	Runnable button2 = new Runnable(){
		@Override
		public void run() {
			System.out.println("CLICKIIIN");
		}
	};

	public void render(Graphics g){
		/* X, Y, Width, Height, Text*/
		button = new Button(1, 20, 20, 150, 30, "text", g, button1);
		button = new Button(2, 20, 70, 150, 30, "text", g, button2);
	}
}
