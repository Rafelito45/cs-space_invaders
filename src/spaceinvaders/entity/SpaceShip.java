package spaceinvaders.entity;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Abstract SpaceShip class,
 * design structure for all spaceships.
 * Extends <strong>Entity</strong>.
 * @author ostovargas
 *
 */
public abstract class SpaceShip extends Entity {
	protected Missile missile;		// spaceship artillery
	protected int missileVelocity;	// missile velocity
	
	public SpaceShip(String path) {
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Renders SpaceShip state.
	 */
	public void render(Graphics g) {
		super.render(g);
		if (missile != null) missile.render(g);
	}
	
	/**
	 * Fires a <em>Missile</em> object relative to
	 * SpaceShip object's direction.
	 */
	public void attack() {
		/* if there is no missile on screen,
		 * or it has been 1.5 seconds since last fire
		 */
		if (missile == null) {
			// instantiate new missile object
			missile = new Missile(this, getX() + (getWidth() / 2),
					y, missileVelocity);
		}
	}

	/**
	 * Collision behavior for SpaceShip's <em>Missile</em> object. 
	 * @param entity
	 */
	@Override
	public boolean collision(Entity entity) {
		/* were going to pass an entity object here
		 * and test it against the missile
		 */
		boolean collision = false;	// always set to false
		if (missile != null) {		// if there is a missile on screen
			if (missile.collision(entity)) {
				collision = true;	// yield true
				missile = null;		// dismiss missile from screen
			}
		}
		
		return collision;
	}
}
