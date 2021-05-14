package medieval_Mayhem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	final int MENU = 0;
	final int MAP = 1;
    final int GAME = 2;
    final int END = 3;
    int currentState = MENU;
    Font titleFont;
	Font tiitleFont;
	Font tiiitleFont;
	Font EndFont;
	Timer frameDraw;
    GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		tiitleFont = new Font("Arial", Font.PLAIN, 15);
		tiiitleFont = new Font("Arial", Font.PLAIN, 15);
		EndFont = new Font("Arial", Font.PLAIN, 48);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
			loadImage ("map.jpg");
		
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == MAP){
		    drawMapState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	 void updateMenuState() {  }
	 void updateMapState()  {  }
	 void updateGameState() {  }
	 void updateEndState()  {  }
	 void drawMenuState(Graphics g) { 
		 g.setColor(Color.BLUE);
		 g.fillRect(0, 0, Medieval_Mayhem.WIDTH, Medieval_Mayhem.HEIGHT);
	 }
	 void drawMapState(Graphics g)  { 
		 if (gotImage) {
				g.drawImage(image, 0, 0, Medieval_Mayhem.WIDTH, Medieval_Mayhem.HEIGHT, null);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, Medieval_Mayhem.WIDTH, Medieval_Mayhem.HEIGHT);
			}
	 }
	 void drawGameState(Graphics g) {  }
	 void drawEndState(Graphics g)  {  }
	 
	 @Override
	 public void keyPressed(KeyEvent e) {
		 // TODO Auto-generated method stub

		 if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			 System.out.println("enter");
			 if (currentState == END) {
				 currentState = MENU;
			 } 
			 else if (currentState == MENU) {
				 currentState = MAP;
			 }
		 }   
		 if (e.getKeyCode()==KeyEvent.VK_UP) {
			 System.out.println("UP");
			 //rocketship.UP = true;
		 }
		 if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			 System.out.println("LEFT");
			 //rocketship.LEFT = true;
		 }
		 if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			 System.out.println("RIGHT");
			 //rocketship.RIGHT = true;
		 }
		 if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			 System.out.println("DOWN");
			 //rocketship.DOWN = true;
		 }
		 if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			 //objectManager.addProjectile(rocketship.getProjectile());
		 }
	 }

	 void loadImage(String imageFile) {
		 if (needImage) {
			 try {
				 image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				 gotImage = true;
				 System.out.println("Its working");
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 needImage = false;
		 }
	 }
	 @Override
	 public void actionPerformed(ActionEvent e) {
		 // TODO Auto-generated method stub
		 if(currentState == MENU){
				updateMenuState();
			}else if(currentState == MAP){
				updateMapState();
			}else if(currentState == GAME){
				updateGameState();
			}else if(currentState == END){
				updateEndState();
			}
			repaint();
	 }
	 @Override
	 public void keyTyped(KeyEvent e) {
		 // TODO Auto-generated method stub

	 }
	 @Override
	 public void keyReleased(KeyEvent e) {
		 // TODO Auto-generated method stub

	 }
}
