import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;


public class Game extends JComponent implements KeyListener, Runnable{
	
	private ArrayList<Entity> list, toRemove, toAdd;
	private boolean[] keys;
	
	private Player player;
	
	private int sdx, sdy;

	public Game(int w, int h) {
		Dimension s = new Dimension(w, h);
		this.setPreferredSize(s);
		this.setMaximumSize(s);
		this.setMinimumSize(s);
		
		list = new ArrayList<Entity>();
		toRemove = new ArrayList<Entity>();
		toAdd = new ArrayList<Entity>();
		keys = new boolean[256];
		
		this.player = new Player(this, 100, 100, keys);
		this.list.add(this.player);
		
		do {
			this.addEntity(new Box(this, (int) ((w-32) * Math.random()), (int) ((h-32)*Math.random())));
		} while (0.2 < Math.random());
		
		Powerup.speed.apply(player);
	}
	
	public void movescreen(int dx, int dy) {
		this.sdx += dx;
		this.sdy += dy;
	}
	
	private void mvscrn(int dx, int dy) {
		UW.frame.setLocation(UW.frame.getX() + dx, UW.frame.getY() + dy);
		for(int i = 1; i < list.size(); i++) {
			Entity e = this.list.get(i);
			e.movepx(-dx, -dy);
		}
	}
	
	public void tick() {
		for(int i = 0; i < list.size(); i++) {
			list.get(i).tick();
		}
		while(!this.toRemove.isEmpty()) {
			this.list.remove(this.toRemove.get(0));
			this.toRemove.remove(0);
		}
		while(!this.toAdd.isEmpty()) {
			this.list.add(this.toAdd.get(0));
			this.toAdd.remove(0);
		}
		
		int sx = sdx > 100? 100 : sdx;
		int sy = sdy > 100? 100 : sdy;
		sx = sx < -100? -100 : sx;
		sy = sy < -100? -100 : sy;
		
		mvscrn(sx, sy);
		
		this.sdx -= sx;
		this.sdy -= sy;
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < list.size(); i++) {
			list.get(i).draw(g);
		}
	}
	
	public void addEntity(Entity e) {
		this.toAdd.add(e);
	}
	
	public void removeEntity(Entity e) {
		this.toRemove.add(e);
	}
	
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			this.tick();
			this.repaint();
			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {

			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code >= 0 && code < keys.length) {
			keys[code] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code >= 0 && code < keys.length) {
			keys[code] = false;
		}
	}

	@Override
	
	public void keyTyped(KeyEvent arg0) {
		
	}

	public Player getPlayer() {
		return this.player;
	}
}
