import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Entity{
	private int[] controls;
	private boolean[] keys;
	
	private int cooldown;
	
	private Game game;
	
	private boolean buffed;
	
	private HashMap<Powerup, Integer> buffs;
	
	private int dx, dy, microdx, micrody;

	public Player(Game game, int x, int y, boolean[] keys) {
		super(game, x, y, 30);
		buffs = new HashMap<Powerup, Integer>();
		
		for(Powerup p : Powerup.list()) {
			buffs.put(p, 0);
		}
		
		this.color = Color.red;
		
		this.microdx = this.micrody = 0;
		
		pow = 5;
		
		this.keys = keys;
		
		this.controls = new int[] { KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, 
				KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT};
		
		this.game = game;
	}
	
	public void friction() {
		this.microdx *= 0.9;
		this.micrody *= 0.9;
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
	
	public boolean hasBuff(Powerup p) {
		return buffs.get(p) > 0;
	}
	
	public void addBuff(Powerup p) {
		buffs.put(p, 5 * 1000/60);
	}
	
	public void applyBuffs() {
		if (!buffed) {
			for (Powerup p : buffs.keySet()) {
				if (buffs.get(p) > 0) {
					p.apply(this);
				}
				if(p == Powerup.hp)
					buffs.put(p, 0);
			}
			this.buffed = true;
		}
	}
	
	public void revertBuffs() {
		if (buffed) {
			for (Powerup p : buffs.keySet()) {
				if (buffs.get(p) > 0) {
					p.revert(this);
				}
			}
			this.buffed = false;
		}
	}
	
	public void debuff() {
		for(Powerup p :buffs.keySet()) {
			buffs.put(p, 0);
		}
	}
	
	public void tick() {
		super.tick();
		this.applyBuffs();
		int movespeed = this.movespeed;
		
		if(keys[KeyEvent.VK_SHIFT]) {
			movespeed = 5;
		}
		
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
				game.addEntity(new Card(this.game, this.x+8, this.y+8, d[i-4], pow));
				this.cooldown = this.attackspeed;
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
		
		this.friction();
		this.revertBuffs();
		for(Powerup p : buffs.keySet()) {
			if (buffs.get(p) > 0) {
				buffs.put(p, buffs.get(p) - 1);
			}
		}
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
