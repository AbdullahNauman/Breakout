
/* 
 * Name: Paddle.java 
 * Description: The Paddle class provides a constructor for the game paddle. It contains getter 
 * and setter methods for the width instance variable which changes every round along with methods for properly drawing the paddle onto the GamePanel. 
 * Authors: Christopher Moore and Abdullah Nauman
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends MovingObject implements KeyListener {

// Establishes the paddle's dimensions
	public static final int height = 10, origWidth = 40;
	private static int width = origWidth;

	public Paddle(int initXPos, int initYPos) {
		super(initXPos, initYPos, width, height);
	}

// User controls for moving the paddle
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			super.setObjVelX(1);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			super.setObjVelX(-1);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			super.setObjVelX(0);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			super.setObjVelX(0);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(52, 73, 94));
		g.fillRect((int) super.getLocation().getX(), (int) super.getLocation().getY(), width, height);
	}

// Getter method for height
	public double getHeight() {
		return height;
	}

// Getter and Setter method for width
	public void setWidth(int _w) {
		width = _w;
	}

	public double getWidth() {
		return width;
	}
}