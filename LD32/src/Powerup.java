
public enum Powerup {
	speed, power, shotspeed, hp;
	
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
		case shotspeed:
			this.mem = e.attackspeed;
			e.attackspeed /= 3;
			break;
		case hp:
			this.mem = e.hp;
			e.hp = UW.clamp(e.hp + 100, 200, e.hp + 100);
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
		case shotspeed:
			e.attackspeed *= 3;
			break;
		}
	}

}
