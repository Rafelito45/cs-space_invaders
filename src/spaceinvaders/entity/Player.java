package spaceinvaders.entity;

import spaceinvaders.Game;
import spaceinvaders.InputHandler;

/**
 * Player object for Space Invaders.
 * Extends <strong>SpaceShip</strong>.
 * @author ostovargas
 *
 */
public class Player extends SpaceShip {
	private InputHandler input;				// KeyListener Class
	
	/**
	 * Player constructor takes input from a client.
	 * BufferedImage object instantiated and location set.
	 * @param input
	 */
	public Player(InputHandler input, int id) {
		super("res/player.png");
		this.input = input;	// client input
		// start halfway across screen and near bottom
		x = (Game.WIDTH / 2) - (img.getWidth() / 2);
		y = Game.HEIGHT - (Game.WIDTH / 16);
		missileVelocity = -2;
	}
	
	/**
	 * Updates Player state.
	 * Responds to key events to move Player
	 * and fire missiles.
	 */
	public void tick() {
		int xa = 0;					// x offset
		if (input.left.down) xa--;	// x offset -> left
		if (input.right.down) xa++;	// x offset -> right
		move(xa);					// move method
		if (input.attack.down) attack();
		if (missile != null) {
			missile.tick();
			// out of bounds
			if (missile.getY() < 0) missile = null;
		}
	}

	/**
	 * Moves Player either left or right.
	 * @param xa	-- in negative or positive direction
	 */
	private void move(int xa) {
		if (x == 0) {
			if (xa > 0) x += xa;
		} else if (x + getWidth() == Game.WIDTH) {
			if (xa < 0) x += xa;
		} else {
			x += xa;
		}
	}
}
