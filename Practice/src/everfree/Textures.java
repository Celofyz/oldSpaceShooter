package everfree;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage player, bullet, enemy;
	
	private SpriteSheet ss;
	public static int ts = 64; //TileSet size
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures(){
		player = ss.grabImage(1, 1, ts, ts);
		bullet = ss.grabImage(1, 2, ts, ts);
		enemy = ss.grabImage(2, 1, ts, ts);
	}
}
