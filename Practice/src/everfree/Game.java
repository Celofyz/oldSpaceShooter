package everfree;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;


import everfree.entity.EntityA;
import everfree.entity.EntityB;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	
	public final String TITLE = "Space Game";
	public static JFrame frame;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private boolean is_shooting = false;
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	
	private int enemy_count = 1;
	private int enemy_killed = 0;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu2 menu;
	private GameOverScreen over;
	private Help help;
	private Mouse m;
	
	MouseEvent mouseevent;
	public int mx;
	public int my;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	public static int HEALTH = 100 * 5;
	public static int SCORE = 0;
	public static int speed = 6;
	
	public static enum STATE{
		MENU,
		GAME,
		HELP,
		OVER,
	};
	
	public static STATE State = STATE.GAME;
	
	public void init(){
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			spriteSheet = loader.loadImage("/tileSet.png");
			background = loader.loadImage("/space.png");
		}catch(IOException e){
			e.printStackTrace();
		}
		
		this.addKeyListener(new KayInput(this));
		this.addMouseListener(new MouseInput());
		
		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(WIDTH/2, HEIGHT/2, tex, this, c);
		m = new Mouse();
		menu = new Menu2();
		over = new GameOverScreen();
		help = new Help();
		
		c.createEnemy(enemy_count);
		
		ea = c.getEntityA();
		eb = c.getEntityB();
	}
	
	private synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running)
			return;
		
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){
		
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) /ns;
			lastTime = now;
			if(delta >=1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				System.out.println("SCORE = " + SCORE);
				updates = 0;
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void tick(){
		m.tick();
		
		if(State == STATE.GAME){
			p.tick();
			c.tick();
		}		
		if(enemy_killed >= enemy_count){
			enemy_count += 1;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
		if(HEALTH <= 0){
			State = STATE.OVER;
		}
	}
	
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		///////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, null);
		
		if(State == STATE.GAME){
			p.render(g);
			c.render(g);
			
			g.setColor(Color.gray);
			g.fillRect(6, 6, 500, 25);
			
			g.setColor(Color.green);
			g.fillRect(6, 6, HEALTH, 25);
			
			g.setColor(Color.white);
			g.drawRect(6, 6, 500, 25);
			
			g.drawString("SCORE: " + SCORE, 510, 25);
			
		}else if(State == STATE.MENU){
			menu.render(g);
		}else if(State == STATE.OVER){
			over.render(g);
		}else if(State == STATE.HELP){
			help.render(g);
		}
		
		///////////
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(State == STATE.GAME){
			if(key == KeyEvent.VK_RIGHT){
				if(right == false){
					p.setVelX(speed);
					right = true;
				}
			}else if(key == KeyEvent.VK_LEFT){
				if(left == false){
					p.setVelX(-speed);
					left = true;
				}
			}else if(key == KeyEvent.VK_DOWN){
				if(down == false){
					p.setVelY(speed);
					down = true;
				}	
			}else if(key == KeyEvent.VK_UP){
				if(up == false){
					p.setVelY(-speed);
					up = true;
				}
			}else if(key == KeyEvent.VK_SPACE && !is_shooting){
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(State == STATE.GAME){
			if(key == KeyEvent.VK_RIGHT){
				p.setVelX(-speed);
				right = false;
			} else if(key == KeyEvent.VK_LEFT){
				p.setVelX(speed);
				left = false;
			} else if(key == KeyEvent.VK_DOWN){
				p.setVelY(-speed);
				down = false;
			} else if(key == KeyEvent.VK_UP){
				p.setVelY(speed);
				up = false;
			} else if(key == KeyEvent.VK_SPACE){
				is_shooting = false;
			}
		}
	}
	
	public static void main (String[] args){
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getLocation();
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
}