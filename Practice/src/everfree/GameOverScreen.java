package everfree;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOverScreen {

	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 100, 150, 200, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 50, 250, 100, 50);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("GAME OVER", Game.WIDTH / 2 - 160, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Try Again", playButton.x + 35, playButton.y + 36);
		g2d.draw(playButton);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 36);
		g2d.draw(quitButton);
		g.drawString("SCORE: " + Game.SCORE, quitButton.x - 35, quitButton.y + 136);
	}
	
}
