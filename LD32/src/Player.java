import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Entity{
	private int[] controls;
	private boolean[] keys;
	
	private int cooldown;
	
	private Game game;
	
	private int dx, dy, microdx, micrody;

	public Player(Game game, int x, int y, boolean[] keys) {
		super(game, x, y, 30);
		
		this.color = Color.red;
		
		this.microdx = this.micrody = 0;
		
		this.keys = keys;
		
		this.controls = new int[] { KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, 
				KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT};
		
		this.game = game;
	}
	
	public void stopOffscreen() {
		if(this.x < 0 || this.x > UW.WIDTH - this.w 
				|| this.y< 0 || this.y > UW.HEIGHT - this.h) {
			int rx = UW.clamp(this.x, 0, UW.WIDTH - this.w);
			int ry = UW.clamp(this.y, 0, UW.HEIGHT - this.h);
			int xx = x - rx;
			int yy = y - ry;
			if(rx != x) {
				this.dx = 0;
				this.microdx = 0;
				this.x = rx;
			}
			if(ry != y) {
				this.dy = 0;
				this.micrody = 0;
				this.y = ry;
			}
			game.movescreen(xx, yy);
			
			
		}
	}
	
	public void tick() {
		super.tick();
		Direction[] d = Direction.list();
		for(int i = 0; i < 4; i++) {
			if(keys[this.controls[i]]) {
				this.microdx += d[i].getX() * movespeed * 5;
				this.micrody += d[i].getY() * movespeed * 5;
				this.microdx = UW.clamp(microdx, -movespeed * 100, movespeed * 100);
				this.micrody = UW.clamp(micrody, -movespeed * 100, movespeed * 100);
				this.dx = this.microdx / 100;
				this.dy = this.micrody / 100;
			}
		}
		for(int i = 4; i < 8; i++) {
			if(this.cooldown == 0 && keys[this.controls[i]]) {
				game.addEntity(new Card(this.game, this.x+8, this.y+8, d[i-4]));
				this.cooldown = 20;
				this.microdx -= d[i-4].getX();
				this.micrody -= d[i-4].getY();
				
			}
		}
		if(this.cooldown > 0) {
			this.cooldown--;
		}
		
		this.microx += this.microdx;
		this.microy += this.micrody;
		
		this.stopOffscreen();
	}
	

	public int getUp() {
		return this.controls[0];
	}

	public int getDown() {
		return this.controls[3];
	}

	public int getLeft() {
		return this.controls[1];
	}

	public int getRight() {
		return this.controls[2];
	}

	public int getFire() {
		return this.controls[4];
	}
	
	

	public void setUp(int i) {
		this.controls[0] = i;
	}

	public void setDown(int i) {
		this.controls[3] = i;
	}

	public void setLeft(int i) {
		this.controls[1] = i;
	}

	public void setRight(int i) {
		this.controls[2] = i;
	}

	public void setFire(int i) {
		this.controls[4] = i;
	}

}
