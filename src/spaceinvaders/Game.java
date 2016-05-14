package spaceinvaders;

import java.awt.BorderLayout;
import java.awt.Canvas;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferInt;



import javax.swing.JFrame;

import spaceinvaders.DLList.ListNode;
import spaceinvaders.entity.AlienShip;
import spaceinvaders.entity.Player;

/**
 * Game class for Space Invaders.
 * Extends Canvas and implements Runnable.
 * @author ostovargas
 *
 */
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final String NAME = "Space Invaders";	// window name
	public static final int WIDTH = 256;			// 4:3 ratio
	public static final int HEIGHT = 192;			// 4:3 ratio
	
	// boolean for pausing game
	private volatile boolean running = false;
	// Game class becomes a KeyListener of InputHandler class
	private InputHandler input = new InputHandler(this);
	
	private Player player;					// player
	private DLList[] aliens;				// list of aliens
	private int alienCount;					// number of aliens on screen
	private int score = 0;					// game score
	
	/**
	 * Game constructor.
	 * Initializes windowing properties.
	 */
	public Game() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);	// (canvas)game centered inside jframe
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Starts game loop thread.
	 */
	public void start() {
		running = true;
		new Thread(this).start();
	}
	
	/**
	 * Stops game loop thread.
	 */
	public void stop() {
		running = false;
	}
	
	/**
	 * Ends game.
	 */
	public void gameOver() {
		stop();
		System.out.println("Game Over");
		System.out.println("Score: " + score);
		System.exit(0);
	}

	/**
	 * Initializes Entity objects.
	 */
	private void init() {
		AlienShip newAlien;		// temporary AlienShip reference
		
		player = new Player(input, 0);	// instantiate player
		aliens = new DLList[10];	// instantiate array of singly linked lists ...
						// ... each array will hold a list of four aliens in a column
		for (int i = 0; i < aliens.length; i++) {
			aliens[i] = new DLList();	// singly linked list of four aliens will be created
			for (int j = 0; j < 4; j++) {
				// generate a new alien 28 y units upward in a column; 20 x units rightward per array
				newAlien = new AlienShip(i * 20, 100 - j * 28);
				// first alien in list set to attack; rest are not
				newAlien.setAttack(j == 0 ? true : false);
				// insert new alien in a "queue" fashion
				aliens[i].insert(newAlien);
				
				alienCount++;
			}
		}
	}
	
	/**
	 * Game loop, update, and rendering cycle.
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();	// timer commences
		final double fps = 60D;			// frames per second
		double unprocessed = 0;
		double nsPerTick = 1000000000D / fps;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		
		init(); // initialize all entities
		
		// game loop
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();			// update game assets
				unprocessed -= 1;	// set delta back to zero seconds
				shouldRender = true;
			}
			
			// thread sleep; gives jvm time
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (shouldRender) {
				frames++;
				render();		// render game assets
			}
			
			// keeps track of ticks and frames per second in milliseconds
			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	/**
	 * Updates all game elements.
	 */
	public void tick() {
		ListNode node;		// temporary ListNode reference for list traversal
		AlienShip alien;	// temporary AlienShip reference
		
		// updates player object
		player.tick();
		
		// updates all alien objects
		for (int i = 0; i < aliens.length; i++) {
			node = aliens[i].getHead();			// begin with head of list
			for (int j = 0; j < aliens[i].size(); j++) {	// size of particular list
				node.getData().tick();			// tick alien within node
				node = node.getNext();			// proceed to next node
			}
		}
		
		// collision tests
		for (int i = 0; i < aliens.length; i++) {
			node = aliens[i].getHead();		// begin with head of list
			for (int j = 0; j < aliens[i].size(); j++) {	// size of particular list
				alien = (AlienShip) node.getData();	// cast to AlienShip in order to access methods
				
				if (player.collision(alien)) {		// if player's missile hits alien
					// foremost alien in list & not last -- prevent null reference
					if (j == 0 && aliens[i].size() > 1) {
						alien = (AlienShip) node.getNext().getData();	// reference next alien in column
						alien.setAttack(true);				// set attack to true
					}
					
					aliens[i].remove(node);	// remove eliminated alien
					alienCount--;
					score += 10;		// increment score
				}
				else if (alien.collision(player)) {
					player = null;	// player revival (potential expansion ...)*
					System.out.println("You Lost.");
					gameOver();	// terminates game
				}
				
				node = node.getNext();	// traverse to next node
			}
		}
		
		if (alienCount == 0) {
			System.out.println("You Won!");
			gameOver();
		}
	}
	
	/**
	 * Renders all game elements.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////////////
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (player != null) {
			player.render(g);
		}
		
		ListNode node;		// temporary ListNode reference for list traversal
		for (int i = 0; i < aliens.length; i++) {
			node = aliens[i].getHead();			// begin with head of list
			for (int j = 0; j < aliens[i].size(); j++) {	// size of particular list
				node.getData().render(g);		// render alien within node
				node = node.getNext();			// traverse to next node
			}
		}
		//////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		new Game().start();
	}
}
