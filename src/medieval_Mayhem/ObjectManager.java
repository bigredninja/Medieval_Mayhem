package medieval_Mayhem;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Random rnd = new Random();
	//ArrayList<Alien> aliens;
	//int score = 0;
	//ArrayList<Projectile> projectiles;
	Knight knight;
	public ObjectManager( Knight knight) {
		this.knight = knight;
		//projectiles = new ArrayList<Projectile>();	
		//aliens = new ArrayList<Alien>();
	}
	/*public void addProjectile(Projectile projectile ) {
		projectiles.add(projectile);
	}
	public void addAlien() {
		aliens.add(new Alien(rnd.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}*/
	void update() {
		knight.update();
		/*for (Iterator iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = (Alien) iterator.next();
			alien.update();	
			if (alien.y > LeagueInvaders.HEIGHT) {
				alien.isActive = false;
			}


		}
		for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = (Projectile) iterator.next();
			projectile.update();
			if (projectile.y < 0) {
				projectile.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();*/
	}
	void draw(Graphics g) {
		knight.draw(g);
		/*for (Iterator iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = (Alien) iterator.next();
			alien.draw(g);

		}
		for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = (Projectile) iterator.next();
			projectile.draw(g);
		}*/
	}
	/*void purgeObjects() {
		for (Iterator iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = (Alien) iterator.next();
			if (alien.isActive == false) {
				iterator.remove();
			}
		}
		for (Iterator iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = (Projectile) iterator.next();
			if (projectile.isActive == false) {
				iterator.remove();
			}
		}			
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//addAlien();
	}
	/*void checkCollision() {
		for (Iterator iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = (Alien) iterator.next();
			if (spaceship.collisionBox.intersects(alien.collisionBox)) {
				spaceship.isActive = false;
				break;
			}
			for (Iterator iterator1 = projectiles.iterator(); iterator1.hasNext();) {
				Projectile projectile = (Projectile) iterator1.next();
				if (projectile.collisionBox.intersects(alien.collisionBox)) {
					alien.isActive = false;
					projectile.isActive = false;
					score++;
				}
			}
		}
	}
	int getScore() {
		return score;
	}*/
}

