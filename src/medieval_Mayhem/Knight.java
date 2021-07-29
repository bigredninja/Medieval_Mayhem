package medieval_Mayhem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Knight extends GameObject{
	int health = 4;
	float vel = 0f;
	float gravity = -3f;
	int jumpPower = 35;
	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;
	boolean GROUNDED = true;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Knight(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 15;
		if (needImage) {
			loadImage ("Knight.png");
		}	
		// 	TODO Auto-generated constructor stub1
	}
	void draw(Graphics g) {	
		if (gotImage) {
			g.drawImage(image, x - width / 2, y - height, width, height, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
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
		}
		if (RIGHT ) {
			x += speed;
		}
		vel += gravity;
		y -= (int)vel;
		if (y >= GamePanel.groundHeight) {
			y = GamePanel.groundHeight;
			vel = 0f;
			GROUNDED = true;
		}
	}
	//public Projectile getProjectile() {
	//return new Projectile(x+width/2, y, 10, 10);
} 
//}

//}
