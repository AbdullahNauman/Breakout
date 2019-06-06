import java.awt.Color;
import java.awt.Graphics;

public class Ball extends MovingObject
{
  private final int diameter = 15;// Ball dimensions
  
  public Ball(int initXPos, int initYPos)
  {
    super(initXPos,initYPos);
  }

  public void paintComponent(Graphics g)
  {
    g.setColor(Color.WHITE);
    g.fillOval(super.getCurrentXPos(), super.getCurrentYPos(), diameter, diameter);
  }

  public int getDiameter()
  {
    return diameter;
  }
}
