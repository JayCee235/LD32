import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Entity{
	private int x, y;
	private int[] controls;
	private boolean[] keys;

	public Player(int x, int y, boolean[] keys) {
		super(x, y, 5);
		
		this.keys = keys;
		
		this.controls = new int[] { KeyEvent.VK_W, KeyEvent.VK_A,
				KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_SPACE };
	}
	
	public void tick() {
		super.tick();
		Direction[] d = Direction.list();
		for(int i = 0; i < 4; i++) {
			if(keys[this.controls[i]]) {
				this.move(d[i]);
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
