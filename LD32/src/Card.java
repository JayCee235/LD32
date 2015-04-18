import java.awt.Color;
import java.awt.Graphics;


public class Card extends Entity{
	Direction d;

	public Card(Game game, int x, int y, Direction d) {
		super(game, x, y, 6);
		this.d = d;
	}
	
	public void tick() {
		this.move(this.d);
		this.killOffscreen();
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, 16, 16);
	}

}
