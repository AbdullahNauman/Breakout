import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends MovingObject implements KeyListener
{
  private static final int initXPos = 10, initYPos = 400;// TODO Edit to ensure
                                                         // proper positioning
                                                         // on different sizes
  private final int height = 10, width = 50;// Paddle dimensions

  public Paddle()
  {
    super(initXPos,initYPos);
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      super.setObjVelX(1);
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      super.setObjVelX(-1);
    }
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      super.setObjVelX(0);
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      super.setObjVelX(0);
    }
  }

  public void keyTyped(KeyEvent e)
  {
  }

  public void paintComponent(Graphics g)
  {
    g.fillRect(super.getCurrentXPos(), super.getCurrentYPos(), width, height);
  }
}
