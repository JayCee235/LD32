import javax.swing.JFrame;


public class UW {
	public static final String TITLE = " Ludum Dare 32!";
	public static final int WIDTH = 800, HEIGHT = 600;
	
	public static JFrame frame;
	public static Game game;
	
	public static void main(String[] args) {
		frame = new JFrame(TITLE);
		game = new Game(WIDTH, HEIGHT);
		
		frame.add(game);
		frame.addKeyListener(game);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		game.start();
		
	}
	
	public static int clamp(int x, int min, int max) {
		int out = x < min ? min : x;
		out = out > max ? max : out;
		return out;
		
	}
}
