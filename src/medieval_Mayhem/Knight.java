package medieval_Mayhem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Knight extends GameObject{
	int health = 4;
	long swordTime = 500;
	long swordSwung;
	float vel = 0f;
	float gravity = -3f;
	int swordOffsetX = 40;
	int swordOffsetY = -40;
	int swordwidth = 80;
	int swordheight = 20;
	int jumpPower = 35;
	int dir = 1;
	GameObject sword;
	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;
	boolean GROUNDED = true;
	boolean attacking = false;
	public static BufferedImage image;
	public static BufferedImage imageflipped;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Knight(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 15;
		if (needImage) {
			loadImage ("Knight.png");
			imageflipped = Flip(image);
		}	
		sword = new GameObject( x + swordOffsetX, y +swordOffsetY,swordwidth,swordheight);
		sword.isActive = false;
		// 	TODO Auto-generated constructor stub1
	}
	void draw(Graphics g) {	
		if (gotImage) {
			g.drawImage(dir == 1 ? image : imageflipped, x - width / 2, y - height, width, height, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
		if (sword.isActive) {
			g.setColor(Color.GRAY);
			g.fillRect(sword.x - swordwidth / 2,sword.y - swordheight, swordwidth, swordheight);
		}
	}
	public void right() {
		if (x < 450) {
			x+=speed;
		}
	}public void left() {
		if (x > 0) {
			x-=speed;
		}
	}

	//}
	//}public void up() {
	//if (y > 0) {
	//y-=speed;
	//}
	//}public void down() {
	//if (y < 600) {
	//y+=speed;
	//}
	//}
	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;}
	}
	//}
	void update() {
		super.update();
		if (UP) {
			if (GROUNDED) {
				vel = jumpPower;
				UP = false;
				GROUNDED = false;
			}
		}
		if (DOWN) {
			//down();
		}
		if (LEFT ) {
			x-= speed;
			dir = -1;
		}
		if (RIGHT ) {
			x += speed;
			dir = 1; 
		}
		vel += gravity;
		y -= (int)vel;
		if (y >= GamePanel.groundHeight) {
			y = GamePanel.groundHeight;
			vel = 0f;
			GROUNDED = true;
		}
		if (attacking) {
			attack();
			attacking = false;
		}
		sword.x = x + (dir == 1 ? swordOffsetX : -swordOffsetX;
		sword.y = y + swordOffsetY;
		sword.update();
		if (swordSwung + swordTime < System.currentTimeMillis()) {
			sword.isActive = false;
		}
	}
	void attack() {
		sword.isActive = true;
		swordSwung = System.currentTimeMillis();
		System.out.println("attacked");
	}
	//public Projectile getProjectile() {
	//return new Projectile(x+width/2, y, 10, 10);
} 
//}

//}
