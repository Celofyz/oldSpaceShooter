package everfree;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	
	//poz x, poz y, width, height
	public Rectangle playButton = new Rectangle(30, Game.HEIGHT - 50 - 20, 117, 50);
	public Rectangle menuButton = new Rectangle(Game.WIDTH - 117 - 20, Game.HEIGHT - 50 - 20, 117, 50);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("HELP MENU", Game.WIDTH / 2 - 140, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 30, playButton.y + 36);
		g2d.draw(playButton);
		g.drawString("Menu", menuButton.x + 20, menuButton.y + 36);
		g2d.draw(menuButton);
	}
}