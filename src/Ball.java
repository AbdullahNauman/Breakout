import java.awt.Color;
import java.awt.Graphics;

public class Ball extends MovingObject {
	private final static int diameter = 15;// Ball dimensions
	public final static int maxVel = 1;// Max velocity of ball

	public Ball(int initXPos, int initYPos) {
		super(initXPos, initYPos, diameter, diameter);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(26, 188, 156));
		// rgb()
		g.fillOval((int) super.getLocation().getX(), (int) super.getLocation().getY(), diameter, diameter);
	}

	public int getDiameter() {
		return diameter;
	}
}
