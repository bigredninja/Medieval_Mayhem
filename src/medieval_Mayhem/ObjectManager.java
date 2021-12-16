package medieval_Mayhem;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.Timer;


public class ObjectManager implements ActionListener{
	Random rnd = new Random();
	ArrayList<Barbarian> barbs;
	int score = 0;
	int barbCount = 0;
	int barbMax = 6;
	int barbHealth = 2;
	int barbSpeed = 10;
	//ArrayList<Projectile> projectiles;
	Knight knight;
	Timer spawn;
	public ObjectManager(Knight knight) {
		this.knight = knight;
		//projectiles = new ArrayList<Projectile>();	
		barbs = new ArrayList<Barbarian>();
		spawn = new Timer(1000, this);
	}
	//public void addProjectile(Projectile projectile ) {
		//projectiles.add(projectile);
	//}
	public void addBarbarian(int x) {
			Barbarian barb = new Barbarian(x,GamePanel.groundHeight,64,128);
			barb.speed = barbSpeed;
			barb.health = barbHealth;
			barbs.add(barb);
		
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
			

		}
		/*for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = (Projectile) iterator.next();
			projectile.update();
			if (projectile.y < 0) {
				projectile.isActive = false;
			}
		}*/
		
		if (score == barbMax) {
			//barbCount = 0;
		}
		
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
			Random rnd = new Random();
				if (rnd.nextBoolean()) {
					addBarbarian(1200);
				}
				else {
					addBarbarian(0);
				}
	//			addBarbarian(-100);
				barbCount++;
		} else {
			//((Timer)e.getSource()).stop();
		}
	}
	void checkCollision() {
		for (Iterator<Barbarian> iterator = barbs.iterator(); iterator.hasNext();) {
			Barbarian barb = (Barbarian) iterator.next();
			if (!barb.invince && knight.sword.isActive && knight.sword.collisionBox.intersects(barb.collisionBox)) {
				barb.health--;
				barb.velx = barb.speed * -barb.dir;
				barb.vely = barb.speed;
				barb.GROUNDED = false;
				barb.invince = true;
				barb.invinceStart = System.currentTimeMillis();
				
				if (barb.health <= 0) {
					barb.dead = true;
					int tmp = barb.width;
					barb.width = barb.height;	
					barb.height = tmp;
					score++;
				}		
				break;
			}
			if (!barb.dead && knight.collisionBox.intersects(barb.collisionBox)) {
				if (!knight.invince) {
					knight.health--;
					knight.invince = true;
					knight.invinceStart = System.currentTimeMillis();
					knight.velx = knight.speed * barb.dir;
					knight.vely = knight.speed;
					knight.GROUNDED = false;				
					if (knight.health <= 0) {
						knight.isActive = false;
					}
					barb.velx = barb.speed * -barb.dir;
					barb.vely = barb.speed;
					barb.GROUNDED = false;
				}
				
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

