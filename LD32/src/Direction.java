
public enum Direction {
	up(0, -1), left(-1, 0), down(0, 1), right(1, 0);
	
	private int x, y;
	
	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Direction[] list() {
		return new Direction[] {up, left, down, right};
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
