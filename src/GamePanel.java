import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener
{
  private Paddle paddle;
  private Ball ball;

  public GamePanel()
  {
    setBackground(Color.BLACK);
    
    paddle = new Paddle();
    ball = new Ball();
    
    //call step() 60 fps
    Timer timer = new Timer(1000/60, this);
    timer.start();
  }

  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);
    paddle.paintComponent(g);// Draws paddle component of appropriate size and
                             // in the correct position based on data store in
                             // Paddle class
    ball.paintComponent(g);
  }

  public void actionPerformed(ActionEvent arg0)
  {
     paddle.move();
     ball.move();
     repaint();
  }
}
