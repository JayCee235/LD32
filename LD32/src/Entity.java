import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public abstract class Entity {
	protected int x, y, movespeed, hp;
	protected int frame;
	
	protected int w, h;
	protected Color color;
	
	protected Game game;
	
	public Entity(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		
		this.movespeed = 1;
		
		this.game = game;
	}
	
	public Entity(Game game, int x, int y, int move) {
		this.x = x;
		this.y = y;
		this.movespeed = move;
		
		this.game = game;
	}
	
	public abstract void tick();
	
	public void killOffscreen() {
		if(this.x + this.w < 0 || this.x > UW.WIDTH || this.y + this.h < 0 || this.y > UW.HEIGHT) {
			this.game.removeEntity(this);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, 32, 32);
	}
	
	public void move(Direction d) {
		this.x += this.movespeed * d.getX();
		this.y += this.movespeed * d.getY();
		this.frame += this.movespeed;
	}
}
