package medieval_Mayhem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.management.relation.Role;

public class Barbarian extends GameObject {
	float velx = 0f;
	float vely = 0f;
	float gravity = -3f;
	boolean GROUNDED = true;
	boolean invince = false;
	boolean dead = false;
	long invinceStart = 0;
	long invinceTime = 500;
	public static BufferedImage imageflipped;
	public static BufferedImage image;
	public static BufferedImage imageDead;
	public static BufferedImage imageDeadFlipped;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	int dir = -1;
	int health = 2;
	Barbarian(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
			loadImage ("Barbarian.png");
			imageflipped = Flip(image);
			imageDead = Rotate(image);
			imageDeadFlipped = Flip(imageDead);
		}
		// TODO Auto-generated constructor stub
	}	
	void update() {
		if (GROUNDED) {
			velx += dir;
		}
		if (velx > speed) {
			velx = speed;
		}
		if (velx < -speed) {
			velx = -speed;
		}
		x+=velx;
		vely
			+= gravity;
		y -= (int)vely;
		if (y >= GamePanel.groundHeight) {
			y = GamePanel.groundHeight;
			vely = 0f;
			GROUNDED = true;
			if (dead) {
				isActive = false;
			}
		}
		if (System.currentTimeMillis() > invinceStart + invinceTime) {
			invince = false;
		}
		super.update();
	}
	void draw(Graphics g) {
		if (gotImage) {
			if (dead) {
				g.drawImage(dir == 1 ? imageDeadFlipped : imageDead, x - width / 2, y - height, width, height, null);
			} else {
				g.drawImage(dir == 1 ? image : imageflipped, x - width / 2, y - height, width, height, null);
			}
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		
	}
	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true; 
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}

