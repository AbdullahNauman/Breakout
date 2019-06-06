import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
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
  private Rectangle topBorder, leftBorder, rightBorder;

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
    topBorder = new Rectangle(0, 0, (int) panelSize.getWidth(),
        (int) panelSize.getHeight() / 30);
    leftBorder = new Rectangle(0, 0, (int) panelSize.getWidth() / 40,
        (int) panelSize.getHeight());
    rightBorder = new Rectangle(
        (int) panelSize.getWidth() - (int) panelSize.getWidth() / 40, 0,
        (int) panelSize.getWidth() / 40, (int) panelSize.getHeight());

    paddle.move();
    ball.move();

    if (ball.intersects(leftBorder) || ball.intersects(rightBorder)) // Checks
                                                                     // for
                                                                     // ball
                                                                     // collisions
                                                                     // with
                                                                     // side
                                                                     // walls
      ball.setObjVelX(ball.getObjVelX() * -1);

    if (ball.intersects(topBorder)) // Checks for
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
    else if ((int) ball.getLocation().getY() == (int) panelSize.getHeight())
      hasLost = true;

    if (ball.intersects(paddle))
      ball.setObjVelY(ball.getObjVelY() * -1);

    repaint();
  }

  private void drawBorder(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect((int) topBorder.getX(), (int) topBorder.getY(),
        (int) topBorder.getWidth(), (int) topBorder.getHeight());
    g.fillRect((int) leftBorder.getX(), (int) leftBorder.getY(),
        (int) leftBorder.getWidth(), (int) leftBorder.getHeight());
    g.fillRect((int) rightBorder.getX(), (int) rightBorder.getY(),
        (int) rightBorder.getWidth(), (int) rightBorder.getHeight());
  }
}
