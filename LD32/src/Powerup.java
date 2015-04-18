
public enum Powerup {
	speed, power;
	
	int mem;
	
	public static Powerup randomPower() {
		int out = (int) (Math.random() * Powerup.list().length);
		return Powerup.list()[out];
	}
	
	public static Powerup[] list() {
		return Powerup.values();
	}
	
	public void apply(Entity e) {
		switch(this) {
		case speed:
			this.mem = e.movespeed;
			e.movespeed = e.movespeed*2;
			break;
		case power:
			this.mem = e.pow;
			e.pow = -e.pow;
			break;
		}
	}
	
	public void revert(Entity e) {
		switch(this) {
		case speed:
			e.movespeed = e.movespeed/2;
			break;
		case power:
			e.pow = -e.pow;
			break;
		}
	}

}
