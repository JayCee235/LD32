import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Card extends Entity{
	Direction d;

	public Card(Game game, int x, int y, Direction d, int pow) {
		super(game, x, y, 10);
		this.d = d;
		this.color = color.blue;
		
		this.w = 16;
		this.h = 16;
		
		this.pow = pow;
		
		if(this.pow < 0) {
			this.movespeed = 30;
		}
	}
	
	public void tick() {
		this.move(this.d);
		for(Enemy e : game.enemies) {
			if(this.collidingWith(e)) {
				e.damage(20);
				this.die();
				break;
			}
		}
		
		
		if(this.killOffscreen()) {
			if (this.pow >= 0) {
				game.movescreen(pow * d.getX(), pow * d.getY());
			} else {
				this.forceScreen();
			}
		}
	}
	
	public void forceScreen() {
		JFrame frame = UW.frame;
		int x = frame.getX();
		int y = frame.getY();
		int w = frame.getWidth();
		int h = frame.getHeight();
		Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		int dx = 0;
		int dy = 0;
		
		switch(d) {
		case up:
			dy = -y;
			break;
		case down:
			dy = (int) (s.getHeight() - h) - y;
			break;
		case left:
			dx = -x;
			break;
		case right:
			dx = (int) (s.getWidth() - w) - x;
			break;
		}
		
		game.movescreen(dx, dy);
	}

}
