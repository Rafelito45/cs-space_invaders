package spaceinvaders.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 * Abstract Entity class.
 * Design structure for all game elements.
 * @author ostovargas
 *
 */
public abstract class Entity {
	/**
	 * Coordinates for entity.
	 */
	protected int x, y;
	
	/**
	 * Graphic image for entity.
	 */
	protected BufferedImage img;
	
	/**
	 * Updates Entity state.
	 */
	public void tick(){
		move();
	}
	
	/**
	 * Render Entity state.
	 * @param g
	 */
	public void render(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	/**
	 * x coordinate of Entity.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * y coordinate of Entity.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Width of Entity.
	 * @return img.getWidth();
	 */
	public int getWidth() {
		return img.getWidth();
	}
	
	/**
	 * Height of Entity.
	 * @return img.getHeight();
	 */
	public int getHeight() {
		return img.getHeight();
	}
	
	/**
	 * Move Entity.
	 */
	private void move() {}
	
	/**
	 * Tests for collision of other entities by comparing bounds.
	 * @param entity	-- an Entity object
	 * @return boolean
	 */
	public abstract boolean collision(Entity entity);
}
