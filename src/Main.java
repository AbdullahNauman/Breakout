import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main extends JFrame
{
  public Main()
  {
    setTitle("Breakout");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Getting screen size to start JFrame in middle of display and set
    // appropriate size(looked up)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    setSize(height * 9 / 16, (int) (height * 0.8));

    // setExtendedState(JFrame.MAXIMIZED_BOTH);
     setUndecorated(true);

    // Centering JFrame
    setLocationRelativeTo(null);
    setResizable(false);
  }

  public static void main(String[] args)
  {
    BorderLayout layout = new BorderLayout();
    Main frame = new Main();
    
    frame.setLayout(layout);
    
    GamePanel game = new GamePanel();
    frame.add(game,BorderLayout.CENTER);
    
    frame.setVisible(true);

  }
}