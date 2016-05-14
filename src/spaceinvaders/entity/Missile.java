package spaceinvaders.entity;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Missile object for Space Invaders.
 * Extends <strong>Entity</strong>
 * @author ostovargas
 *
 */
public class Missile extends Entity {
	private int vel;	// velocity of missile
	
	/**
	 * Missile constructor.
	 * Instantiated from given coordinates and
	 * travels by given velocity.
	 * BufferedImage object instantiated and location set.
	 * <em>Polymorphic</em> to <strong>SpaceShip</strong> object.
	 * @param x
	 * @param y
	 * @param vel
	 */
	public Missile(SpaceShip spaceShip, int x, int y, int vel) {
		if (spaceShip instanceof Player) {
			try {	// missile.png loaded
				img = ImageIO.read(new File("res/missile.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {	// alien_missile.png loaded
				img = ImageIO.read(new File("res/alien_missile.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.x = x;		// x coordinate of firing object
		this.y = y;		// y coordinate of firing object
		this.vel = vel;		// velocity of missile
	}
	
	/**
	 * Updates Missile state.
	 */
	public void tick() {
		move(vel);
	}
	
	/**
	 * Moves Missile.
	 * @param ya
	 */
	private void move(int ya) {
		y += ya;
	}
	
	/**
	 * Compares bounds of Missile object to
	 * another Entity for collision behavior.
	 */
	@Override
	public boolean collision(Entity entity) {
		int missile_x = x;				// x coordinate of missile
		int missile_y = y;				// y coordinate of missile
		// tests bottom end of missile instead
		if (entity instanceof Player) {
			missile_x = x + getWidth();
			missile_y = y + getHeight();
		}
		int entity_x = entity.getX();			// x coordinate of entity
		int entity_y = entity.getY();			// y coordinate of entity
		int entity_width = entity.getWidth();		// width of entity
		int entity_height = entity.getHeight();		// height of entity
		// returns result of comparison
		return (missile_x > entity_x && missile_x < entity_x + entity_width &&
				missile_y > entity_y && missile_y < entity_y + entity_height);
	}
}
