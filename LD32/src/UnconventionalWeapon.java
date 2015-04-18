import javax.swing.JFrame;


public class UnconventionalWeapon {
	private static final String TITLE = " Ludum Dare 32!";
	private static final int WIDTH = 800, HEIGHT = 600;
	
	private JFrame frame;
	private Game game;
	
	public static void main(String[] args) {
		UnconventionalWeapon UW = new UnconventionalWeapon();
		
	}
	
	public UnconventionalWeapon() {
		this.frame = new JFrame(TITLE);
		this.game = new Game(WIDTH, HEIGHT);
		
		this.frame.add(this.game);
		this.frame.addKeyListener(this.game);
		
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.frame.setVisible(true);
		
		game.start();
		
	}
}
