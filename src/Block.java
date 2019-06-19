import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	private int hardness;// Hardness variable to keep track of how
							// many times ball must hit before breaking.
	private int score;
	private boolean pointsGiven;
	public static final int width = 35, height = 12;// Width and height of blocks

	public Block(int hardness, int xPos, int yPos) {
		super(xPos, yPos, width, height);
		this.hardness = hardness;
		score = convertScore(hardness);
		pointsGiven = false;
	}

	public Block(int hardness) {
		super(width, height);
		score = convertScore(hardness);
		pointsGiven = false;
		this.hardness = hardness;
	}

	public int getHardness() {
		return hardness;
	}

	public void setHardness(int hardness) {
		this.hardness = hardness;
	}

	public int breakBlock() {
		if (hardness > 0)
			hardness -= 1;
		return hardness;
	}

	private Color getColor() {
		// rgb(52, 152, 219)
		if (hardness == 0)
			return new Color(0f, 0f, 0f, 0f);
		else if (hardness == 1)
			return new Color(243, 156, 18); // orange
		else if (hardness == 2)
			return new Color(155, 89, 182); // amethyst
		else if (hardness == 3)
			return new Color(52, 152, 219); // blue
		else if (hardness == 4)
			return new Color(231, 76, 60); // alizarin
		return null;
	}

	public void paintComponent(Graphics g) {
		g.setColor(getColor());
		g.fillRect((int) super.getLocation().getX(), (int) super.getLocation().getY(), width, height);
	}

	private int convertScore(int hardness) {
		return 2 * hardness - 1;
	}

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
