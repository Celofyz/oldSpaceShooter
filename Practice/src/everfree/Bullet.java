package everfree;

import java.awt.Graphics;
import java.awt.Rectangle;

import everfree.entity.EntityA;

public class Bullet extends GameObject implements EntityA{
		
		private Textures tex;
		private Game game;
		private int ts = Textures.ts;
		
		public Bullet(double x, double y, Textures tex, Game game){
			super(x, y);
			this.tex = tex;
			this.game = game;
		}
		
		public void tick(){
			y -= 5;
			
		}
		
		public void render(Graphics g){
			g.drawImage(tex.bullet, (int) x, (int) y, null);
			g.drawImage(tex.bullet, (int) x - 10, (int) y + 5, null);
			g.drawImage(tex.bullet, (int) x + 10, (int) y + 5, null);
		}
		
		public Rectangle getBounds(){
			return new Rectangle((int)x, (int)y, ts, ts);
		}
		
		public double getY(){
			return y;
		}

		@Override
		public double getX() {
			return x;
		}
}