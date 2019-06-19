
/* 
 * Name: Main.java 
 * Description: The program begins executing in the Main class. This class creates the window container and creates a GamePanel object. The object is then added as frame to begin the game. 
 * Authors: Christopher Moore and Abdullah Nauman
 */

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame {
	public Main() {
		setTitle("Breakout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Using the player's screen size to position and scale JFrame to middle of user
		// display (found in online documentation)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		setSize(height * 9 / 16, (int) (height * 0.8));

		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setUndecorated(true);

		// Centering JFrame
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public static void main(String[] args) {
		Main frame = new Main();

		// Creating GamePanel object
		GamePanel game = new GamePanel();

		// Adding GamePanel object to frame
		frame.add(game);
		frame.setVisible(true);

	}
}