package medieval_Mayhem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Barbarian extends GameObject {
	float vely = 0f;
	float gravity = -3f;
	boolean GROUNDED = true;
	public static BufferedImage imageflipped;
	public static BufferedImage image;
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
		}
		// TODO Auto-generated constructor stub
	}	
	void update() {
		x+=speed * dir;
		vely += gravity;
		y -= (int)vely;
		if (y >= GamePanel.groundHeight) {
			y = GamePanel.groundHeight;
			vely = 0f;
			GROUNDED = true;
		}
		super.update();
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(dir == 1 ? image : imageflipped, x - width / 2, y - height, width, height, null);
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

