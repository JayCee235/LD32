
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
	
	public static Direction getDirection(int x, int y) {
		int dx = UW.clamp(x, -1, 1);
		int dy = UW.clamp(y, -1, 1);
		
		if(x == -1) return left;
		if(x == 1) return right;
		if(y == -1) return up;
		if(y == 1) return down;
		return down;
	}
}
