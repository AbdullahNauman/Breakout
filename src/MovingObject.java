import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class MovingObject extends Rectangle
{
  private int objVelX, objVelY;// Horizontal & vertical velocity of object

  public MovingObject(int initXPos, int initYPos,int width, int height)
  {
    super(initXPos,initYPos,width,height);
    objVelX = 0;
    objVelY = 0;
  }

  public void move()
  {
    super.translate(objVelX,objVelY);
  }

  public abstract void paintComponent(Graphics g);

  public int getObjVelX()
  {
    return objVelX;
  }

  public void setObjVelX(int ballVelX)
  {
    this.objVelX = ballVelX;
  }

  public int getObjVelY()
  {
    return objVelY;
  }

  public void setObjVelY(int ballVelY)
  {
    this.objVelY = ballVelY;
  }
}
