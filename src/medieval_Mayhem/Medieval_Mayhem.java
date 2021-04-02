package medieval_Mayhem;

import javax.swing.JFrame;

public class Medieval_Mayhem {
	public static int WIDTH = 500;
	public static int HEIGHT = 800;
	JFrame frame;	
	Medieval_Mayhem(){
		frame = new JFrame();
	}
	void setup(){
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Medieval_Mayhem mayhem = new Medieval_Mayhem();
		mayhem.setup();
	}
}
