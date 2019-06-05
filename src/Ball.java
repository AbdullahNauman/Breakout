import java.awt.Graphics;

public class Ball extends MovingObject
{
  private static final int initXPos = 10, initYPos = 400;// TODO Edit to ensure
                                                         // proper positioning
                                                         // on different sizes
  private final int diameter = 20;// Ball dimensions
  
  public Ball()
  {
    super(initXPos,initYPos);
  }

  public void paintComponent(Graphics g)
  {
    g.fillOval(super.getCurrentXPos(), super.getCurrentYPos(), diameter, diameter);
  }

}
