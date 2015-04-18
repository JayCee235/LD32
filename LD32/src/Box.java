import java.awt.Color;


public class Box extends Entity {

	public Box(Game game, int x, int y) {
		super(game, x, y);
		this.movespeed = 0;
		this.color = Color.green;
	}

	@Override
	public void tick() {
		if(this.killOffscreen()) {
			game.addEntity(new BrokenBox(this.game, this.x, this.y));
		}
	}

}
