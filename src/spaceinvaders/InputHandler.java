package spaceinvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Design influenced by Markus "Notch" Persson.
 * InputHandler is a modularized KeyListener that
 * can be implemented to any Game class.
 * @author ostovargas
 *
 */
public class InputHandler implements KeyListener {	
	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;
		
		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			
			if (pressed) {
				presses++;
			}
		}
		
		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}
	
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	
	/**
	 * Adds KeyListner implementation to any
	 * Game class client.
	 * @param game
	 */
	public InputHandler (Game game) {
		game.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Will call a helper method to manage
	 * KeyEvent's.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	/**
	 * Will call a helper method to manage
	 * KeyEvent's.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
		
	}
	
	private void toggle(KeyEvent e, boolean pressed) {
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
	}

}
