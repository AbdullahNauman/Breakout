import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle implements KeyListener
{
  private int paddleVelX;// Horizontal velocity of paddle
  private static final int initXPos = 10, initYPos = 400;// TODO Edit to ensure
                                                         // proper positioning
                                                         // on different sizes
  private int currentXPos;
  public Paddle()
  {
    paddleVelX = 0;
    currentXPos = initXPos;
  }

  public void move()
  {
    currentXPos+=paddleVelX;
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      paddleVelX = 1;
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      paddleVelX = -1;
    }
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      paddleVelX = 0;
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      paddleVelX = 0;
    }
  }

  public void keyTyped(KeyEvent e)
  {

  }

  public int getCurrentXPos()
  {
    return currentXPos;
  }

  public void setCurrentXPos(int currentXPos)
  {
    this.currentXPos = currentXPos;
  }

}
