package medieval_Mayhem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Knight extends GameObject{
	int health = 4;
	int healthMax = 4;
	long swordTime = 150;
	long swordSwung;
	long invinceStart = 0;
	long invinceTime = 1000;
	float vel = 0f;
	float gravity = -3f;
	float velx = 0f;
	float vely = 0f;
	int swordOffsetX = 60;
	int swordOffsetY = -40;
	int swordwidth = 80;
	int swordheight = 20;
	int iswordOffsetX = 5;
	int iswordOffsetY = -13;
	int jumpPower = 35;
	int dir = 1;
	GameObject sword;
	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;
	boolean GROUNDED = true;
	boolean attacking = false;
	boolean invince = false;
	public static BufferedImage image;
	public static BufferedImage imageflipped;
	public static BufferedImage swordimage;
	public static BufferedImage swordflipped;
	public static BufferedImage image2;
	public static BufferedImage image2flipped;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Knight(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 15;
		if (needImage) {
			loadImage ("Knight.png");
			imageflipped = Flip(image);
			loadImage2 ("Sword.png");
			swordflipped = Flip(swordimage);
			loadImage3 ("Knight2.png");
			image2flipped = Flip(image2);
			
		}	
		
		sword = new GameObject( x + swordOffsetX, y +swordOffsetY,swordwidth,swordheight);
		sword.isActive = false;
		// 	TODO Auto-generated constructor stub1
	}
	void draw(Graphics g) {	
		if (sword.isActive) {
			g.drawImage(dir == 1 ? image2 : image2flipped, x - width / 2, y - height, width, height, null);
			
		}else {
			g.drawImage(dir == 1 ? image : imageflipped, x - width / 2, y - height, width, height, null);
		}
			g.drawImage(dir == 1 ? swordimage : swordflipped, sword.x - sword.width / 2, sword.y - sword.height, sword.width, sword.height, null);
			//g.setColor(Color.GRAY);
			//g.fillRect(sword.x - swordwidth / 2,sword.y - swordheight, swordwidth, swordheight);
		
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
		
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
	}
	void loadImage2(String imageFile) {
		
		try {
			swordimage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			gotImage = true;
		} catch (Exception e) {

		}
		needImage = false;
}
	void loadImage3(String imageFile) {
		
		try {
			image2 = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			gotImage = true;
		} catch (Exception e) {

		}
		needImage = false;
}
	
	//}
	void update() {
		super.update();
		if (UP) {
			if (GROUNDED) {
				vely = jumpPower;
				UP = false;
				GROUNDED = false;
				
			}
		}
		if (DOWN) {
			//down();
		}
		if (LEFT ) {
			velx-= speed;
			if (!sword.isActive) {
				dir = -1;
			}
		}
		if (RIGHT ) {
			velx += speed;
			if (!sword.isActive) {
				dir = 1; 
			}
		}
		if (!LEFT && !RIGHT) {
			if (velx > 0) {
				velx -= 5;
			}
			else if (velx < 0) {
				velx += 5;
			}
		}
		if (velx > speed) {
			velx = speed;
		}
		if (velx < -speed) {
			velx = -speed;
		}
		x += velx;
		vely += gravity;
		y -= (int)vely;
		if (y >= GamePanel.groundHeight) {
			y = GamePanel.groundHeight;
			vely = 0f;
			GROUNDED = true;
		}
		if (attacking) {
			attack();
			attacking = false;
		}
		if (sword.isActive) {
			sword.x = x + (dir == 1 ? swordOffsetX : -swordOffsetX);
			sword.y = y + swordOffsetY;
		} else {
			sword.x = x + (dir == 1 ? iswordOffsetX : -iswordOffsetX);
			sword.y = y + iswordOffsetY;
		}
		
		sword.update();
		if (swordSwung + swordTime < System.currentTimeMillis()) {
			sword.isActive = false;
		}
		if (System.currentTimeMillis() > invinceStart + invinceTime) {
			invince = false;
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
