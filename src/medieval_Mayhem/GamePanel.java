package medieval_Mayhem;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	@Override
	public void paintComponent(Graphics g){
		g.fillRect(0, 500, 60,100 );
	}
}
