package everfree;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import everfree.Game.STATE;
import everfree.entity.EntityA;

public class MouseInput implements MouseListener{
	
	public LinkedList<Button> b = new LinkedList<Button>();

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		
		if(mx >= b.get(1).x && mx <= b.get(1).x + b.get(1).width){
			if(my >= b.get(1).y && my <= b.get(1).y + b.get(1).height){
				b.get(1).code.run();
			}
		}
		/*
		 * 	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 150, 100, 50);
		 * 	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 250, 100, 50);
		 * 	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 50, 350, 100, 50);
		 */

		
		/*if(Game.State == STATE.MENU){
			
			//Play Button
			if(mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50){
				if(my >= 150 && my <= 200){
					//Pressed Play Button
					Game.State = Game.STATE.GAME;
					//System.out.println("Play");
				}
			}
			
			// Help Button
			if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
				if (my >= 250 && my <= 300) {
					// Pressed Help Button
					Game.State = Game.STATE.HELP;
				}
			}
			// Quit Button
			if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
				if (my >= 350 && my <= 400) {
					// Pressed Help Button
					System.exit(1);;
					//System.out.println("Quit");
				}
			}
		}else if(Game.State == STATE.OVER){
			
			//Play Button
			if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100){
				if(my >= 150 && my <= 200){
					//Pressed Play Button
					Game.State = Game.STATE.GAME;
					//System.out.println("Play");
				}
			}
			
			// Quit Button
			if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50) {
				if (my >= 250 && my <= 300) {
					// Pressed Help Button
					//Game.State = Game.STATE.GAME;
					System.exit(1);
				}
			}
		}else if(Game.State == STATE.HELP){
			
			//Play Button
			if(mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2 + 100){
				if(my >= 150 && my <= 200){
					//Pressed Play Button
					Game.State = Game.STATE.GAME;
					//System.out.println("Play");
				}
			}
			
			// Quit Button
			if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 67) {
				if (my >= 250 && my <= 300) {
					// Pressed Help Button
					Game.State = Game.STATE.MENU;
				}
			}
		}*/
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	public void ListAdd(Button button){
		b.add(button);
	}
}
