package medieval_Mayhem;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Random rnd = new Random();
	ArrayList<Barbarian> barbs;
	int score = 0;
	int barbCount = 0;
	int barbMax = 5;
	//ArrayList<Projectile> projectiles;
	Knight knight;
	public ObjectManager( Knight knight) {
		this.knight = knight;
		//projectiles = new ArrayList<Projectile>();	
		barbs = new ArrayList<Barbarian>();
	}
	//public void addProjectile(Projectile projectile ) {
		//projectiles.add(projectile);
	//}
	public void addBarbarian() {
		barbs.add(new Barbarian(Medieval_Mayhem.WIDTH,GamePanel.groundHeight,64,128));
	}
	void update() {
		knight.update();
		for (Iterator<Barbarian> iterator = barbs.iterator(); iterator.hasNext();) {
			Barbarian barb = (Barbarian) iterator.next();
			if (knight.x > barb.x) {
				barb.dir = 1;
			}
			else {
				barb.dir = -1;		
			}
			barb.update();	
			if (barb.x < 0) {
				barb.isActive = false;
			}


		}
		/*for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = (Projectile) iterator.next();
			projectile.update();
			if (projectile.y < 0) {
				projectile.isActive = false;
			}
		}*/
		checkCollision();
		purgeObjects();
	}
	void draw(Graphics g) {
		knight.draw(g);
		for (Iterator<Barbarian> iterator = barbs.iterator(); iterator.hasNext();) {
			Barbarian barb = (Barbarian) iterator.next();
			barb.draw(g);

		}
		//for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
		//	Projectile projectile = (Projectile) iterator.next();
		//	projectile.draw(g);
		//}*/
	}
	void purgeObjects() {
		for (Iterator<Barbarian> iterator = barbs.iterator(); iterator.hasNext();) {
			Barbarian barb = (Barbarian) iterator.next();
			if (barb.isActive == false) {
				iterator.remove();
			}
		}
		/*for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = (Projectile) iterator.next();
			if (projectile.isActive == false) {
				iterator.remove();
			}
		}*/			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (barbCount < barbMax) {
			addBarbarian();
			barbCount++;
		}
	}
	void checkCollision() {
		for (Iterator<Barbarian> iterator = barbs.iterator(); iterator.hasNext();) {
			Barbarian barb = (Barbarian) iterator.next();
			if (knight.sword.isActive && knight.sword.collisionBox.intersects(barb.collisionBox)) {
				barb.health--;
				if (barb.health <= 0) {
					barb.isActive = false;
				}		
				break;
			}
			if (knight.collisionBox.intersects(barb.collisionBox)) {
				knight.health--;
				if (knight.health <= 0) {
					knight.isActive = false;
				}
				barb.isActive = false;
				break;
			}
			//for (Iterator iterator1 = projectiles.iterator(); iterator1.hasNext();) {
				//Projectile projectile = (Projectile) iterator1.next();
			//	if (projectile.collisionBox.intersects(alien.collisionBox)) {
				//	alien.isActive = false;
				//	projectile.isActive = false;
				//	score++;
				//}
			//}
		}
	}
	int getScore() {
		return score;
	}
}

