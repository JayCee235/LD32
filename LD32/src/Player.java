import java.awt.event.KeyEvent;

public class Player {
	private int x, y;
	private int[] controls;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;

		this.controls = new int[] { KeyEvent.VK_W, KeyEvent.VK_A,
				KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_SPACE };
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
	
	
}
