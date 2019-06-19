
/* 
 * Name: GamePanel.java 
 * Description: This is the primary class in the project. It is the primary panel for motion and graphics and and also computes all of the game mechanics.  
 * Authors: Christopher Moore and Abdullah Nauman
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;

	// Game paddle and ball
	private Paddle paddle;
	private Ball ball;

	/*
	 * Boolean variables for game structure: hasLost:chnages to true when ball moves
	 * below paddle. hasWon: changes to true when player clears all levels.
	 * hasReleased: changes to true when space bar is pressed to launch ball.
	 * redraw: changes to true when all blocks have been cleared.
	 */
	private boolean hasLost, hasWon, hasReleased, redraw;

	private Rectangle topBorder, leftBorder, rightBorder;

	// Array of blocks to be broken.
	private Block blocksToBreak[][];

	// Positioning for blocks and text within the panel.
	private final int sideBorderScale = 100, topBorderScale = 60, blockPosScale = 5, blockYSpaceScale = 22,
			textShiftX = -60, textShiftY = 12;// Scaling factors for border edges,

	private ScoreBoard scoring;

	// GamePanel Constructor
	public GamePanel() {
		setBackground(new Color(236, 240, 241));

		paddle = new Paddle(50, 800);
		paddle.setWidth(40);
		ball = new Ball(65, 785);

		initializeBlocks();

		scoring = new ScoreBoard();// Initialize score board

		hasLost = false;
		hasReleased = false;
		redraw = false;
		hasWon = false;

		// listen to key presses
		setFocusable(true);
		addKeyListener(paddle);
		addKeyListener(this);

		// call each object move() 120 frames per second
		timer = new Timer(1000 / 200, this);
		timer.start();
	}

	private void initializeBlocks() {
		blocksToBreak = new Block[8][14];
		for (int x = 0; x < blocksToBreak.length; x++) {
			for (int y = 0; y < blocksToBreak[x].length; y++) {
				int hardness = (blocksToBreak.length - x + 1) / 2;// Calculates hardness
				// level based on row
				blocksToBreak[x][y] = new Block(hardness);
			}
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Dimension panelSize = super.getSize();

		// redraw: changes to true when all blocks have been cleared.
		if (redraw == true) {
			initializeBlocks();
			redraw = false;
		}

		drawBlocks(g);
		drawBorder(g);
		paddle.paintComponent(g);// Draws paddle component of appropriate size and
		// in the correct position based on data stored in
		// Paddle class
		scoring.paintComponent(g, (int) (panelSize.getWidth() / 2) + textShiftX + (int) (panelSize.getWidth() / 4),
				(int) panelSize.getHeight() / blockPosScale - textShiftY);
		ball.paintComponent(g);
		// hasLost:chnages to true when ball moves below paddle.
		if (hasLost) {
			g.setColor(new Color(236, 240, 241));
			g.fillRect((int) leftBorder.getWidth(), (int) topBorder.getHeight(),
					(int) (panelSize.getWidth() - leftBorder.getWidth() - rightBorder.getWidth()),
					(int) (panelSize.getHeight() - topBorder.getHeight()));
			scoring.paintComponent(g, (int) (leftBorder.getWidth() + 20), (int) panelSize.getHeight() / 2,
					"Final Score: ");

			drawText(g, (int) (leftBorder.getWidth() + 20), (int) panelSize.getHeight() / 2 + 30,
					"Press space to play again", 30f);
		}
		// hasWon: changes to true when player clears all levels.
		if (hasWon) {
			g.setColor(new Color(236, 240, 241));
			g.fillRect((int) leftBorder.getWidth(), (int) topBorder.getHeight(),
					(int) (panelSize.getWidth() - leftBorder.getWidth() - rightBorder.getWidth()),
					(int) (panelSize.getHeight() - topBorder.getHeight()));
			scoring.paintComponent(g, (int) (leftBorder.getWidth() + 20), (int) panelSize.getHeight() / 2,
					"Final Score: ");

			drawText(g, (int) (leftBorder.getWidth() + 20), (int) panelSize.getHeight() / 2 + 30,
					"Nice job! Press space to play again", 30f);
		}
		// hasReleased: changes to true when space bar is pressed to launch ball.
		if (!hasReleased) {
			g.setColor(new Color(26, 188, 156));
			drawText(g, (int) (leftBorder.getWidth() + 20), (int) panelSize.getHeight() / 2 + 30,
					"Press space to release ball", 40f);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		// Checks loss condition and stops timer if true, and resets the paddle width.
		if (hasLost || hasWon) {
			paddle.setWidth(Paddle.origWidth);
			timer.stop();
		}

		if (!hasReleased)
			ball.setLocation((int) (paddle.getX() + paddle.getWidth() / 2 - ball.getWidth() / 2),
					(int) (paddle.getY() - ball.getHeight()));

		Dimension panelSize = super.getSize();

		topBorder = new Rectangle(0, 0, (int) panelSize.getWidth(), (int) panelSize.getHeight() / topBorderScale);// Initializing
		// border rectangles here because panel is not calculated before constructor
		leftBorder = new Rectangle(0, 0, (int) panelSize.getWidth() / sideBorderScale, (int) panelSize.getHeight());
		rightBorder = new Rectangle((int) panelSize.getWidth() - (int) panelSize.getWidth() / sideBorderScale, 0,
				(int) panelSize.getWidth() / sideBorderScale, (int) panelSize.getHeight());

		setBlockPos(panelSize);

		paddle.move();
		ball.move();

		collisionDetection(panelSize);

		repaint();
	}

	private void drawBorder(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int) topBorder.getX(), (int) topBorder.getY(), (int) topBorder.getWidth(),
				(int) topBorder.getHeight());
		g.fillRect((int) leftBorder.getX(), (int) leftBorder.getY(), (int) leftBorder.getWidth(),
				(int) leftBorder.getHeight());
		g.fillRect((int) rightBorder.getX(), (int) rightBorder.getY(), (int) rightBorder.getWidth(),
				(int) rightBorder.getHeight());
	}

	private void drawBlocks(Graphics g) {
		for (int x = 0; x < blocksToBreak.length; x++) {
			for (int y = 0; y < blocksToBreak[x].length; y++) {
				blocksToBreak[x][y].paintComponent(g);
			}
		}
	}

	private void collisionDetection(Dimension panelSize) {
		if (ball.intersects(leftBorder) || ball.intersects(rightBorder)) // Checks
			// for ball
			// collision
			// with
			// side
			// walls
			ball.setObjVelX(ball.getObjVelX() * -1);

		if (ball.intersects(topBorder)) // Checks for collision with top wall
			ball.setObjVelY(ball.getObjVelY() * -1);
		else if ((int) ball.getLocation().getY() == (int) panelSize.getHeight()) // Loss
			// condition
			// of
			// going
			// through
			// floor
			hasLost = true;

		if (ball.intersects(paddle)) // Checks for paddle-ball collision
		{
			Rectangle intersection = paddle.intersection(ball);
			if (intersection.getHeight() < intersection.getWidth()) {
				ball.setObjVelY(-Math.abs((ball.getObjVelY())));
				if (paddle.getObjVelX() > 0)// Ball spin characteristic
					ball.setObjVelX(-Ball.maxVel);
				else if (paddle.getObjVelX() < 0)
					ball.setObjVelX(Ball.maxVel);
			} else {
				ball.setObjVelX(ball.getObjVelX() * -1);
				if (ball.getCenterX() > paddle.getCenterX())// Moves ball out of
					// intersection
					ball.setLocation((int) paddle.getMaxX() + 1, (int) ball.getY());
				else
					ball.setLocation((int) paddle.getMinX() - 1, (int) ball.getY());
			}
		}

		if (paddle.intersects(leftBorder)) // Prevents paddle from moving off-screen
			paddle.setLocation((int) leftBorder.getWidth(), (int) paddle.getY());
		else if (paddle.intersects(rightBorder))
			paddle.setLocation((int) (rightBorder.getX() - paddle.getWidth()), (int) paddle.getY());

		// Checking for collision with blocks and decreases hardness when
		// intersecting
		int count = 0;
		for (int x = 0; x < blocksToBreak.length; x++) {
			for (int y = 0; y < blocksToBreak[x].length; y++) {
				if (blocksToBreak[x][y].intersects(ball) && blocksToBreak[x][y].getHardness() > 0) {
					Rectangle intersection = blocksToBreak[x][y].intersection(ball);

					blocksToBreak[x][y].breakBlock();

					if (intersection.getHeight() > intersection.getWidth()) {
						ball.setObjVelX(ball.getObjVelX() * -1);
						if (ball.getCenterX() > blocksToBreak[x][y].getCenterX())// Moves
																					// ball out
																					// of
																					// intersection
							ball.setLocation((int) blocksToBreak[x][y].getMaxX() + 1, (int) ball.getY());
						else
							ball.setLocation((int) blocksToBreak[x][y].getMinX() - 1, (int) ball.getY());
					} else {
						ball.setObjVelY(ball.getObjVelY() * -1);
						if (ball.getCenterY() > blocksToBreak[x][y].getCenterY())// Moves
																					// ball out
																					// of
																					// intersection
							ball.setLocation((int) ball.getX(), (int) blocksToBreak[x][y].getMaxY() + 1);
						else
							ball.setLocation((int) ball.getX(), (int) blocksToBreak[x][y].getMinY() - 1);
					}

					if (blocksToBreak[x][y].getHardness() == 0 && !blocksToBreak[x][y].isPointsGiven()) {
						scoring.increaseScore(blocksToBreak[x][y].getScore());
						blocksToBreak[x][y].setPointsGiven(true);
						count++;
					}
				}
			}
		}

		if (count == (blocksToBreak.length * blocksToBreak[0].length)) {

			int w = (int) paddle.getWidth();
			paddle.setWidth(w - 8);
			if ((int) paddle.getWidth() <= 0) {
				hasWon = true;
			} else {
				redraw = true;
				hasReleased = false;
				count = 0;
			}
		}
	}

	private void setBlockPos(Dimension panelSize) {
		int playingWidth = (int) (panelSize.getWidth() - rightBorder.getWidth() - leftBorder.getWidth());

		int padding = (int) ((playingWidth - blocksToBreak[0].length * Block.width)
				/ ((double) (blocksToBreak[0].length - 1)));
		double accuratePadding = (playingWidth - blocksToBreak[0].length * Block.width)
				/ ((double) (blocksToBreak[0].length - 1));// Double version of padding
		// variable so that error
		// from integer coordinate
		// system can be adjusted
		// later

		for (int x = 0; x < blocksToBreak.length; x++) {
			for (int y = 0; y < blocksToBreak[x].length; y++) {
				int xPos = (int) panelSize.getWidth() / sideBorderScale + (padding + Block.width) * y;
				int yPos = (int) panelSize.getHeight() / blockPosScale + x * blockYSpaceScale;
				blocksToBreak[x][y].setLocation(xPos + (int) (accuratePadding * (blocksToBreak[0].length - 1)
						- padding * (blocksToBreak[0].length - 1)) / 2, yPos);// Addition to xPos in argument 1 is to
				// adjust for error from
				// using integer coordinate system
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && (hasLost || hasWon)) {
			hasLost = false;
			initializeBlocks();
			ball.setLocation((int) paddle.getX(), (int) paddle.getY() - (int) ball.getHeight());
			scoring.setScore(0);
			timer.start();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && !hasReleased) {
			hasReleased = true;
			ball.setObjVelY(-1);
			if (paddle.getObjVelX() < 0)
				ball.setObjVelX(1);
			else if (paddle.getObjVelX() > 0)
				ball.setObjVelX(-1);
			else
				ball.setObjVelX((int) (Math.random() * 3) - 1);
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	private void setupFont(Graphics g, float size) {
		g.setColor(Color.WHITE);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, (int) size);// Temporarily
		// set font
		// to logical
		// font in
		// case of
		// Exception
		// error
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("SFSquareRoot-Bold.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = font.deriveFont(size);// Change font size
		g.setFont(font);
	}

	private void drawText(Graphics g, int x, int y, String text, float size) {
		setupFont(g, size);
		g.setColor(new Color(26, 188, 156));
		g.drawString(text, x, y);
	}
}