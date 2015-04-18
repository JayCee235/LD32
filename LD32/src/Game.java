import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;


public class Game extends JComponent{

	public Game(int w, int h) {
		Dimension s = new Dimension(w, h);
		this.setPreferredSize(s);
		this.setMaximumSize(s);
		this.setMinimumSize(s);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
