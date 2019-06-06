import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends MovingObject implements KeyListener
{
  private static final int height = 10, width = 50;// Paddle dimensions

  public Paddle(int initXPos, int initYPos)
  {
    super(initXPos, initYPos, width, height);
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      super.setObjVelX(2);
    }
    else if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      super.setObjVelX(-2);
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
    g.setColor(Color.BLUE);
    g.fillRect((int)super.getLocation().getX(), (int)super.getLocation().getY(), width, height);
  }

  public double getHeight()
  {
    return height;
  }

  public double getWidth()
  {
    return width;
  }
}
