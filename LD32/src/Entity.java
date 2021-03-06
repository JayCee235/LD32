import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public abstract class Entity {
	protected int x, y, microx, microy, movespeed, hp;
	protected int frame;
	
	protected int w, h;
	protected Color color;
	
	protected Game game;
	
	protected int pow, attackspeed;
	
	public Entity(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
		
		this.hp = 100;
		
		this.attackspeed = 21;
		
		this.movespeed = 1;
		
		this.game = game;
	}
	
	public Entity(Game game, int x, int y, int move) {
		this(game, x, y);
		this.movespeed = move;
	}
	
	public boolean damage(int dmg) {
		this.hp -= dmg;
		if(this.hp <= 0) {
			this.die();
			return true;
		}
		return false;
	}
	
	public void tick() {
		this.x += this.microx/100;
		this.y += this.microy/100;
		this.microx = this.microx % 100;
		this.microy = this.microy % 100;
	}
	
	public boolean killOffscreen() {
		if(!this.inBounds()) {
			this.game.removeEntity(this);
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g) {
		if (this.inBounds()) {
			g.setColor(this.color);
			g.fillRect(this.x, this.y, w, h);
		}
	}
	
	public void move(Direction d) {
		this.x += this.movespeed * d.getX();
		this.y += this.movespeed * d.getY();
		this.frame += this.movespeed;
	}
	
	public void move(Direction d, int dx, int dy) {
		this.x += dx * d.getX();
		this.y += dy * d.getY();
		this.frame += this.movespeed;
	}
	
	public void movepx(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public boolean inBounds() {
		return !(this.x + this.w < 0 || this.x > UW.WIDTH || this.y + this.h < 0 || this.y > UW.HEIGHT);
	}
	
	public boolean collidingWith(Entity e) {
		return this.x < e.x + e.w && this.y < e.y + e.h && e.x < this.x + this.w && e.y < this.y + this.h;
	}
	
	public void die() {
		this.game.removeEntity(this);
	}
}
