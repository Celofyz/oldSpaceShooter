package everfree;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class Button{
	
	public Rectangle rectangle;
	public MouseInput minput;
	public boolean enabled;
	public int ID;
	public int x; //pozycja w pozioime
	public int y; //pozycja w pionie
	public int width; //szeroko��
	public int height; //wysoko��
	Runnable code;
	
	public Button(int ID, int x, int y, int width, int height, String text, Graphics g, Runnable code){
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.code = code;
		
		rectangle = new Rectangle(x, y, width, height);
		
		Graphics2D g2d = (Graphics2D) g;
		
		int f = 30;
		Font fnt1 = new Font("arial", Font.BOLD, f);
		g2d.setFont(fnt1);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		g2d.drawString(text, x + width / 2 - fontMetrics.stringWidth(text) / 2, y + height - 5);
		g2d.draw(rectangle);
	}
	public void getMouse(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
	}
}
