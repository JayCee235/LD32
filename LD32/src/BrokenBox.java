import java.awt.Color;


public class BrokenBox extends Entity {
	Powerup p;
	
	public BrokenBox(Game game, int x, int y) {
		super(game, x, y);
		this.movespeed = 0;
		this.color = Color.white;
		
		this.p = Powerup.randomPower();
	}

	@Override
	public void tick() {
		if(this.collidingWith(game.getPlayer())) {
			this.die();
			game.getPlayer().addBuff(p);
		}
	}

}
