package everfree;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import everfree.entity.EntityA;
import everfree.entity.EntityB;

public class Enemy extends GameObject implements EntityB{
	
	Random r = new Random();
	private Game game;
	private Textures tex;
	private Controller c;
	private double speed = (2 + (4 - 2) * r.nextDouble());
	private int ts = Textures.ts;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game){
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
	}
	
	public void tick(){
		y += speed;
		
		if(y > Game.HEIGHT){
			speed = (r.nextInt(2) + 2);
			y = -10;
			x = r.nextInt(Game.WIDTH - 64);
			Game.HEALTH -= 10;
		}
		
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			
			if(Physics.Collision(this, tempEnt)){
				
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed() + 1);
				Game.SCORE++;
			}	
		}
	}
	
	public void render(Graphics g){
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, ts, ts);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
