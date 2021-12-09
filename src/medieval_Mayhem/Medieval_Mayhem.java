package medieval_Mayhem;

import javax.swing.JFrame;

public class Medieval_Mayhem {
	public static int WIDTH = 1200;
	public static int HEIGHT = 800;
	JFrame frame;	
	GamePanel panel;
	Medieval_Mayhem(){
		frame = new JFrame();
		panel = new GamePanel();
	}
	void setup(){
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
		frame.addMouseListener(panel);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Medieval_Mayhem mayhem = new Medieval_Mayhem();
		mayhem.setup();
	}
}