import java.awt.Color;


public class Enemy extends Entity{

	private int microdx;
	private int micrody;

	public Enemy(Game game, int x, int y) {
		super(game, x, y, 8);
		
		this.color = Color.blue;
		
	}
	
	public void stopOffscreen() {
		if(this.x < 0 || this.x > game.getWIDTH() - this.w 
				|| this.y< 0 || this.y > game.getHEIGHT() - this.h) {
			int rx = UW.clamp(this.x, 0, UW.WIDTH - this.w);
			int ry = UW.clamp(this.y, 0, UW.HEIGHT - this.h);
			
			
		}
	}
	
	public void tick() {
		super.tick();
		
		Player p = game.getPlayer();
		double dx = p.x - this.x;
		double dy = p.y - this.y;
		
		double r =  Math.sqrt(dx*dx + dy*dy);
		
		dx /= r;
		dy /= r;
		
		int sx = (int) (dx * movespeed*10);
		int sy = (int) (dy * movespeed*10);
		
		microdx /= 2;
		micrody /= 2;
		
		this.microdx += sx;
		this.micrody += sy;
		
		microdx *= 2;
		micrody *= 2;
		
		this.microdx = UW.clamp(microdx, -movespeed * 100, movespeed * 100);
		this.micrody = UW.clamp(micrody, -movespeed * 100, movespeed * 100);
		
		this.microx += this.microdx;
		this.microy += this.micrody;
		
		if(this.collidingWith(game.getPlayer())) {
			game.getPlayer().damage(1);
		}
		
		this.killOffscreen();
		
		
		
	}

}
