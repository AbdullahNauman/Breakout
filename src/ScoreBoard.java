
/* 
 * Name: ScoreBoard.java 
 * Description: The ScoreBoard class provides a constructor and methods for the live score board in the right corner of the game window. It contains getter 
 * and setter methods for the score instance variable along with setupFont and paintComponent methods for properly drawing the score onto the GamePanel. 
 * Authors: Christopher Moore and Abdullah Nauman
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

public class ScoreBoard {
	private int score;

	public ScoreBoard(int points) {
		score = points;
	}

	public ScoreBoard() {
		score = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void increaseScore(int score) {
		this.score += score;
	}

	private void setupFont(Graphics g) {

		// Sets font color to turquoise with an RGB value of (26, 188, 156)
		g.setColor(new Color(26, 188, 156));

		// Temporarily sets font to logical font in case of exception error
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 60);

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("SFSquareRoot-Bold.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Sets font size
		font = font.deriveFont(75f);
		g.setFont(font);

	}

	public void paintComponent(Graphics g, int x, int y) {
		this.paintComponent(g, x, y, "");
	}

	public void paintComponent(Graphics g, int x, int y, String precedingText) {
		setupFont(g);

		// Checks the number of digits and adds preceding zeros accordingly
		if (score >= 100)
			g.drawString(precedingText + String.valueOf(score), x, y);
		else if (score >= 10)
			g.drawString(precedingText + "0" + String.valueOf(score), x, y);

		// Draws the current score
		else
			g.drawString(precedingText + "00" + String.valueOf(score), x, y);
	}
}
