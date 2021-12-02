package medieval_Mayhem;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed;
	boolean isActive;
	Rectangle collisionBox;
	GameObject(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		speed = 0;	
		isActive = true;
		collisionBox = new Rectangle(x - width / 2,y - height,width,height);
	}
	void update() {
		 collisionBox.setBounds(x - width/ 2, y - height, width, height); 

	}
	BufferedImage Flip (BufferedImage image) {
		AffineTransform tx;
		AffineTransformOp op;
		tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}
	BufferedImage Rotate (BufferedImage image) {
		final double rads = Math.toRadians(90);
		final double sin = Math.abs(Math.sin(rads));
		final double cos = Math.abs(Math.cos(rads));
		final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
		final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
		final AffineTransform at = new AffineTransform();
		at.translate(w / 2, h / 2);
		at.rotate(rads,0, 0);
		at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		return rotateOp.filter(image,null);

	}
}
