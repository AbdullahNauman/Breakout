import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener
{
  Timer timer;
  private Paddle paddle;
  private Ball ball;
  private boolean hasLost;// Initialized as false in constructor. Changes to
                          // true when ball move below paddle

  public GamePanel()
  {
    setBackground(Color.BLACK);

    paddle = new Paddle(50, 800);
    ball = new Ball(65, 785);
    ball.setObjVelY(-2);
    ball.setObjVelX(-2);

    hasLost = false;

    // listen to key presses
    setFocusable(true);
    addKeyListener(paddle);

    // call each object move() 60 fps
    timer = new Timer(1000 / 60, this);
    timer.start();

  }

  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);

    drawBorder(g);

    paddle.paintComponent(g);// Draws paddle component of appropriate size and
                             // in the correct position based on data store in
                             // Paddle class
    ball.paintComponent(g);

  }

  public void actionPerformed(ActionEvent arg0)
  {
    if (hasLost)
      timer.stop();

    Dimension panelSize = super.getSize();

    paddle.move();

    if (ball.getCurrentXPos() == (int) panelSize.getWidth() / 40
        || ball.getCurrentXPos() == (int) panelSize.getWidth()
            - (int) panelSize.getWidth() / 40 - ball.getDiameter())// Checks for
                                                                   // ball
                                                                   // collisions
                                                                   // with side
                                                                   // walls
      ball.setObjVelX(ball.getObjVelX() * -1);

    if (ball.getCurrentYPos() == (int) panelSize.getHeight() / 30)// Checks for
                                                                  // collision
                                                                  // with top
                                                                  // wall
                                                                  // and losing
                                                                  // condition
                                                                  // of
                                                                  // going
                                                                  // through
                                                                  // the bottom
      ball.setObjVelY(ball.getObjVelY() * -1);
    else if (ball.getCurrentYPos() == (int) panelSize.getHeight())
      hasLost = true;
    
    if(ball.getCurrentYPos()==paddle.getCurrentYPos()-ball.getDiameter()/2 && (ball.getCurrentXPos()>paddle.getCurrentXPos()-ball.getDiameter() && ball.getCurrentXPos()<paddle.getCurrentXPos()+paddle.getWidth()))
      ball.setObjVelY(ball.getObjVelY() * -1);
    ball.move();
    repaint();
  }

  private void drawBorder(Graphics g)
  {
    g.setColor(Color.WHITE);
    Dimension panelSize = super.getSize();
    g.fillRect(0, 0, (int) panelSize.getWidth() / 40,
        (int) panelSize.getHeight());
    g.fillRect((int) panelSize.getWidth() - (int) panelSize.getWidth() / 40, 0,
        (int) panelSize.getWidth() / 40, (int) panelSize.getHeight());
    g.fillRect(0, 0, (int) panelSize.getWidth(),
        (int) panelSize.getHeight() / 30);
  }
}
