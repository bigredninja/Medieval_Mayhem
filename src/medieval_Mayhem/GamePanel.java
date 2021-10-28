package medieval_Mayhem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	final int MENU = 0;
	final int MAP = 1;
	final int GAME = 2;
	final int END = 3;
	final int STAGE1X = 200;
	final int STAGE1Y = 440;
	final int STAGESIZEX = 150; 
	final int STAGESIZEY = 50;
	final int STAGE2X = 519;
	final int STAGE2Y = 410;
	final int STAGE3X = 519;
	final int STAGE3Y = 260;
	final int KNIGHTWIDTH = 64;
	final int KNIGHTHEIGHT = 128;
	int currentState = MENU;
	Font titleFont;
	Font tiitleFont;
	Font tiiitleFont;
	Font EndFont;
	String string;
	Timer frameDraw;
	int stage = 0;
	static int groundHeight = Medieval_Mayhem.HEIGHT * 2 / 3;
	Knight knight;
	ObjectManager objectManager;
	int knightX = 600;
	int knightY = groundHeight;
	int speed = 10;
	public static BufferedImage heart_Health;
	void startGame() {
		objectManager.spawn.start();
		objectManager.barbCount = 0;
		knight.health = knight.healthMax;
		knight.isActive = true;
	}
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 44);
		tiitleFont = new Font("Arial", Font.PLAIN, 15);
		tiiitleFont = new Font("Arial", Font.PLAIN, 15);
		EndFont = new Font("Arial", Font.PLAIN, 48);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		loadImage ("map.jpg");
		knight = new Knight(knightX,knightY,KNIGHTWIDTH,KNIGHTHEIGHT);
		objectManager = new ObjectManager(knight);
		if (needImage) {
			loadImage("Heart health.png");
		}
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
	void updateGameState() {
		objectManager.update();
		if (knight.isActive == false) {
			currentState = END;
		}
		if (objectManager.barbs.size() == 0 && objectManager.barbCount == objectManager.barbMax) {
			currentState = MAP;
			objectManager.spawn.stop(); 
		}
	}
	void updateEndState()  {  }
	void drawMenuState(Graphics g) { 
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Medieval_Mayhem.WIDTH, Medieval_Mayhem.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		string = "Click M1 to go to Map then select your stage";
		int width = g.getFontMetrics().stringWidth(string);
		g.drawString(string, Medieval_Mayhem.WIDTH/2 - width/2, Medieval_Mayhem.HEIGHT/2);
	}
	void drawMapState(Graphics g)  { 
		if (gotImage) {
			g.drawImage(image, 0, 0, Medieval_Mayhem.WIDTH, Medieval_Mayhem.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Medieval_Mayhem.WIDTH, Medieval_Mayhem.HEIGHT);
		}
		g.setColor(Color.RED);
		g.drawRect(STAGE1X, STAGE1Y, STAGESIZEX, STAGESIZEY);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Stage 1", STAGE1X, STAGE1Y + STAGESIZEY / 4 * 3);

		g.setColor(Color.RED);
		g.drawRect(STAGE2X, STAGE2Y, STAGESIZEX, STAGESIZEY);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Stage 2", STAGE2X, STAGE2Y + STAGESIZEY / 4 * 3);

		g.setColor(Color.RED);
		g.drawRect(STAGE3X, STAGE3Y, STAGESIZEX, STAGESIZEY);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Stage 3", STAGE3X, STAGE3Y + STAGESIZEY / 4 * 3);
	}
	void drawGameState(Graphics g) { 
		g.setColor(Color.CYAN);
		g.fillRect(0, 0,Medieval_Mayhem.WIDTH , groundHeight );
		g.setColor(Color.GREEN);
		g.fillRect(0, groundHeight,Medieval_Mayhem.WIDTH , Medieval_Mayhem.HEIGHT );
		objectManager.draw(g);	
	}
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
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("UP");
			knight.UP = true;
		}
		if (e.getKeyCode()==KeyEvent.VK_A) {
			System.out.println("LEFT");
			knight.LEFT = true;
		}
		if (e.getKeyCode()==KeyEvent.VK_D) {
			System.out.println("RIGHT");
			knight.RIGHT = true;
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
		if (e.getKeyCode()==KeyEvent.VK_A) {
			System.out.println("LEFT");
			knight.LEFT = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_D) {
			System.out.println("RIGHT");
			knight.RIGHT = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("UP");
			knight.UP = false;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MAP) {


			if (mapClicked(e,STAGE1X,STAGE1X + STAGESIZEX,STAGE1Y,STAGE1Y + STAGESIZEY)) {
				System.out.println("clicked");
				currentState = GAME;
				objectManager.barbMax = 6;
				startGame();
			}
			else if (mapClicked(e,STAGE2X,STAGE2X + STAGESIZEX,STAGE2Y,STAGE2Y + STAGESIZEY)) {
				System.out.println("clicked");
				currentState = GAME;
				objectManager.barbMax = 12;
				startGame();
			}
			else if (mapClicked(e,STAGE3X,STAGE3X + STAGESIZEX,STAGE3Y,STAGE3Y + STAGESIZEY)) {
				System.out.println("clicked"); 
				currentState = GAME;
				objectManager.barbMax = 18;
				startGame();
			}
		}
		else if (currentState == GAME) {
			knight.attack();
		} 
		else if (currentState == MENU) {
			currentState = MAP;
		} else if (currentState == END) {
			currentState = MENU;
		} 
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	boolean mapClicked(MouseEvent e,int xmin,int xmax,int ymin,int ymax) {
		System.out.println(e.getX() + " " + e.getY());
		if (e.getX() >= xmin + 7 && e.getX() <= xmax + 7 && e.getY() >= ymin + 30 && e.getY() <= ymax + 30) {
			return true; 
		}
		return false;

	}
}
