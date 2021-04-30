package medieval_Mayhem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Knight {
	boolean UP = false;
	boolean DOWN = false;
	boolean LEFT = false;
	boolean RIGHT = false;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	


	//Knight(int x, int y, int width, int height) {
		//super(x, y, width, height);
		//speed = 10;
		//if (needImage) {
			//loadImage ("rocket.png");
		//}	
		// TODO Auto-generated constructor stub1
	//}
	void draw(Graphics g) {	
		if (gotImage) {
		//	g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			//g.fillRect(x, y, width, height);
		}
	}
	public void right() {
		//if (x < 450) {
			//x+=speed;
		}
	//}public void left() {
		//if (x > 0) {
			//x-=speed;
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
			needImage = false;
		}
	//}
	//void update() {
		//super.update();
		if (UP) {
			//up();
		}
		if (DOWN) {
			//down();
		}
		if (LEFT) {
			//left();
		}
		if (RIGHT) {
			right();
		}
	//}
	//public Projectile getProjectile() {
		//return new Projectile(x+width/2, y, 10, 10);
	} 
}

//}
