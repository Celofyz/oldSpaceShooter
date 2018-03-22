package everfree;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KayInput extends KeyAdapter{
	
	Game game;
	
	public KayInput(Game game){
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e){
		game.keyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		game.keyReleased(e);
	}

}
