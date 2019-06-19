
/* 
 * Name: Block.java 
 * Description: The Block class provides a constructor and methods for the blocks that are broken during the game. It contains the hardness, score, pointsGiven for each block, 
 * block and width and block height as instance variables along with methods for evaluating score and hardness and drawing the blocks onto the GamePanel.
 * Authors: Christopher Moore and Abdullah Nauman
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	// The hardness variable allows the program to keep track of how many times a
	// block must be hit by the ball before breaking.
	private int hardness;
	private int score;
	private boolean pointsGiven;

	// Provides the fixed width and height of the blocks.
	public static final int width = 35, height = 12;

	// Constructor for Block object.
	public Block(int hardness, int xPos, int yPos) {
		super(xPos, yPos, width, height);
		this.hardness = hardness;
		score = convertScore(hardness);
		pointsGiven = false;
	}

	// Single argument constructor for Block object.
	public Block(int hardness) {
		super(width, height);
		score = convertScore(hardness);
		pointsGiven = false;
		this.hardness = hardness;
	}

	// Getter and Setter methods for hardness.
	public int getHardness() {
		return hardness;
	}

	public void setHardness(int hardness) {
		this.hardness = hardness;
	}

	// Decrements hardness each time a block is hit by the ball.
	public int breakBlock() {
		if (hardness > 0)
			hardness -= 1;
		return hardness;
	}

	// Sets colors for each level of hardness.
	private Color getColor() {

		if (hardness == 0)
			return new Color(0f, 0f, 0f, 0f);

		else if (hardness == 1)
			// Orange Flat UI
			return new Color(243, 156, 18);

		else if (hardness == 2)
			// Amethyst Flat UI
			return new Color(155, 89, 182);

		else if (hardness == 3)
			// Belize Hole Flat UI
			return new Color(52, 152, 219);

		else if (hardness == 4)
			// Alizarin Red Flat UI
			return new Color(231, 76, 60);

		return null;
	}

	// Draws block object.
	public void paintComponent(Graphics g) {
		g.setColor(getColor());
		g.fillRect((int) super.getLocation().getX(), (int) super.getLocation().getY(), width, height);
	}

	// Evaluates the score fro each hardness level.
	private int convertScore(int hardness) {
		return 2 * hardness - 1;
	}

	// Other getter and setter methods.
	public int getScore() {
		return score;
	}

	public boolean isPointsGiven() {
		return pointsGiven;
	}

	public void setPointsGiven(boolean pointsGiven) {
		this.pointsGiven = pointsGiven;
	}

}
