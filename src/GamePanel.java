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
  private Block blocksToBreak[][];
  private final int sideBorderScale = 40, topBorderScale = 30, blockPosScale = 5, blockYSpaceScale = 22,
      textShiftX = -60, textShiftY = 12;// Scaling factors for border edges,
                                        // block positioning,
                                        // and text positionings
  private ScoreBoard scoring;

  public GamePanel()
  {
    setBackground(Color.BLACK);

    paddle = new Paddle(50, 800);
    ball = new Ball(65, 785);
    ball.setObjVelY(-2);// TODO CHANGE ME
    ball.setObjVelX(-2);

    blocksToBreak = new Block[8][14];
    for (int x = 0; x < blocksToBreak.length; x++)
    {
      for (int y = 0; y < blocksToBreak[x].length; y++)
      {
        int hardness = (blocksToBreak.length - x + 1) / 2;// Calculates hardness
                                                          // level based on row
        blocksToBreak[x][y] = new Block(hardness);
      }
    }

    scoring = new ScoreBoard();

    hasLost = false;

    // listen to key presses
    setFocusable(true);
    addKeyListener(paddle);

    // call each object move() 60 frames per second
    timer = new Timer(1000 / 60, this);
    timer.start();
  }

  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);

    Dimension panelSize = super.getSize();

    drawBorder(g);
    drawBlocks(g);

    paddle.paintComponent(g);// Draws paddle component of appropriate size and
                             // in the correct position based on data stored in
                             // Paddle class
    scoring.paintComponent(g, (int) (panelSize.getWidth() / 2) + textShiftX + (int) (panelSize.getWidth() / 4),
        (int) panelSize.getHeight() / blockPosScale - textShiftY);
    ball.paintComponent(g);

    if (hasLost)
    {
      g.setColor(Color.BLACK);
      g.fillRect((int) leftBorder.getWidth(), (int) topBorder.getHeight(),
          (int) (panelSize.getWidth() - leftBorder.getWidth() - rightBorder.getWidth()),
          (int) (panelSize.getHeight() - topBorder.getHeight()));
      g.setColor(Color.WHITE);
      scoring.paintComponent(g, (int) (leftBorder.getWidth() + 20), (int) panelSize.getHeight() / 2, "Final Score: ");// Draw
                                                                                                                      // final
                                                                                                                      // score
    }
  }

  public void actionPerformed(ActionEvent arg0)
  {
    if (hasLost) // Checks loss condition and stops timer if true
      timer.stop();

    Dimension panelSize = super.getSize();
    topBorder = new Rectangle(0, 0, (int) panelSize.getWidth(), (int) panelSize.getHeight() / topBorderScale);// Initializing
                                                                                                              // border
                                                                                                              // rectangles
                                                                                                              // here
                                                                                                              // because
                                                                                                              // panel
                                                                                                              // is
                                                                                                              // not
                                                                                                              // calculated
                                                                                                              // before
                                                                                                              // constructor
    leftBorder = new Rectangle(0, 0, (int) panelSize.getWidth() / sideBorderScale, (int) panelSize.getHeight());
    rightBorder = new Rectangle((int) panelSize.getWidth() - (int) panelSize.getWidth() / sideBorderScale, 0,
        (int) panelSize.getWidth() / sideBorderScale, (int) panelSize.getHeight());

    setBlockPos(panelSize);

    paddle.move();
    ball.move();

    collisionDetection(panelSize);

    repaint();
  }

  private void drawBorder(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillRect((int) topBorder.getX(), (int) topBorder.getY(), (int) topBorder.getWidth(), (int) topBorder.getHeight());
    g.fillRect((int) leftBorder.getX(), (int) leftBorder.getY(), (int) leftBorder.getWidth(),
        (int) leftBorder.getHeight());
    g.fillRect((int) rightBorder.getX(), (int) rightBorder.getY(), (int) rightBorder.getWidth(),
        (int) rightBorder.getHeight());
  }

  private void drawBlocks(Graphics g)
  {
    for (int x = 0; x < blocksToBreak.length; x++)
    {
      for (int y = 0; y < blocksToBreak[x].length; y++)
      {
        blocksToBreak[x][y].paintComponent(g);
      }
    }
  }

  private void collisionDetection(Dimension panelSize)
  {
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
      if (paddle.getX() >= ball.getX() + ball.getDiameter()-2 || paddle.getX() + paddle.getWidth()-2 <= ball.getX())//Checks for hitting side of paddle. Subtracted constants are for fine tuning purpose when paddle is moving. 
        ball.setObjVelX(ball.getObjVelX() * -1);
      else
      {
        ball.setObjVelY(ball.getObjVelY() * -1);

        if (paddle.getObjVelX() > 0)
          ball.setObjVelX(-Math.abs(ball.getObjVelX()));
        else if (paddle.getObjVelX() < 0)
          ball.setObjVelX(Math.abs(ball.getObjVelX()));
      }
    }

    if (paddle.intersects(leftBorder)) // Prevents paddle from moving off-screen
      paddle.setLocation((int) leftBorder.getWidth(), (int) paddle.getY());
    else if (paddle.intersects(rightBorder))
      paddle.setLocation((int) (rightBorder.getX() - paddle.getWidth()), (int) paddle.getY());

    // Checking for collision with blocks and decreases hardness when
    // intersecting
    for (int x = 0; x < blocksToBreak.length; x++)
    {
      for (int y = 0; y < blocksToBreak[x].length; y++)
      {
        if (blocksToBreak[x][y].intersects(ball) && blocksToBreak[x][y].getHardness() > 0)
        {
          blocksToBreak[x][y].breakBlock();
          if (blocksToBreak[x][y].getX() >= ball.getX() + ball.getDiameter()
              || blocksToBreak[x][y].getX() + Block.width <= ball.getX())
          {
            ball.setObjVelX(ball.getObjVelX() * -1);
          }
          else
          {
            ball.setObjVelY(ball.getObjVelY() * -1);
          }

          if (blocksToBreak[x][y].getHardness() == 0 && !blocksToBreak[x][y].isPointsGiven()) // Checking
                                                                                              // if
                                                                                              // points
                                                                                              // need
                                                                                              // to
                                                                                              // be
                                                                                              // given
                                                                                              // for
                                                                                              // the
                                                                                              // current
                                                                                              // block
          {
            scoring.increaseScore(blocksToBreak[x][y].getScore());
            blocksToBreak[x][y].setPointsGiven(true);
          }
        }
      }
    }

  }

  private void setBlockPos(Dimension panelSize)
  {
    int playingWidth = (int) (panelSize.getWidth() - rightBorder.getWidth() - leftBorder.getWidth());

    int padding = (int) ((playingWidth - blocksToBreak[0].length * Block.width)
        / ((double) (blocksToBreak[0].length - 1)));
    double accuratePadding = (playingWidth - blocksToBreak[0].length * Block.width)
        / ((double) (blocksToBreak[0].length - 1));// Double version of padding
                                                   // variable so that error
                                                   // from integer coordinate
                                                   // system can be adjusted
                                                   // later

    for (int x = 0; x < blocksToBreak.length; x++)
    {
      for (int y = 0; y < blocksToBreak[x].length; y++)
      {
        int xPos = (int) panelSize.getWidth() / sideBorderScale + (padding + Block.width) * y;
        int yPos = (int) panelSize.getHeight() / blockPosScale + x * blockYSpaceScale;
        blocksToBreak[x][y].setLocation(xPos
            + (int) (accuratePadding * (blocksToBreak[0].length - 1) - padding * (blocksToBreak[0].length - 1)) / 2,
            yPos);// Addition to xPos in argument 1 is to adjust for error from
                  // using integer coordinate system
      }
    }
  }
}
