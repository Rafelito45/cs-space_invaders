package spaceinvaders.entity;

import java.util.Random;

import spaceinvaders.Game;

/**
 * AlienShip object for Space Invaders.
 * Extends <strong>SpaceShip</strong>.
 * @author ostovargas
 *
 */
public class AlienShip extends SpaceShip {
	private Random random = new Random();	// random object
	private int shiftOffset = 0;			// keeps track of shifts
	protected double lastMove = 0;			// move timer
	private int n = 1;						// direction of shift
	private boolean attack;

	//private boolean isBehind = false;
	
	/**
	 * AlienShip constructor takes x and y coordinates.
	 * BufferedImage object instantiated and location set.
	 * @param x
	 * @param y
	 */
	public AlienShip(int x, int y) {
		super("res/alien.png");
		this.x = x;
		this.y = y;
		missileVelocity = 3;
	}
	
	/**
	 * Updates AlienShip state.
	 */
	public void tick() {
		move();
		if (attack) attack();
		if (missile != null) {
			missile.tick();
			// out of bounds
			if (missile.getY() > Game.HEIGHT) missile = null;
		}
	}
	
	/**
	 * Moves AlienShip.
	 */
	private void move() {
		if (System.currentTimeMillis() - lastMove > 1500) {
			int xOffset = (n) * 20;
			// shifts alien left and right four times
			shiftOffset = (shiftOffset + n) % 3;
			if (shiftOffset == 0) {
				n = -n;		// shifts left or right
			}
			
			x = x + xOffset;
			lastMove = System.currentTimeMillis();
		}
	}
	
	/**
	 * Fires a <em>Missile</em> object relative to
	 * AlienShip object's direction.
	 */
	@Override
	public void attack() {
		if (random.nextInt(500) == 250) { 
			super.attack();
		}
	}
	
	public boolean getAttack() {
		return attack;
	}
	
	public void setAttack(boolean attack) {
		this.attack = attack;
	}
}
