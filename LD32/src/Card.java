import java.awt.Color;
import java.awt.Graphics;


public class Card extends Entity{
	Direction d;

	public Card(Game game, int x, int y, Direction d) {
		super(game, x, y, 6);
		this.d = d;
		this.color = color.blue;
		
		this.w = 16;
		this.h = 16;
	}
	
	public void tick() {
		this.move(this.d);
		if(this.killOffscreen()) {
			game.movescreen(5*d.getX(), 5*d.getY());
		}
	}

}
