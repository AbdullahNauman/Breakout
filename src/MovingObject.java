import java.awt.Graphics;

public abstract class MovingObject
{
  private int objVelX, objVelY;// Horizontal & vertical velocity of object
  private int currentXPos, currentYPos;

  public MovingObject(int initXPos, int initYPos)
  {
    objVelX = 0;
    objVelY = 0;
    currentXPos = initXPos;
    currentYPos = initYPos;
  }

  public void move()
  {
    currentXPos += objVelX;
    currentYPos += objVelY;
  }

  public int getCurrentXPos()
  {
    return currentXPos;
  }

  public void setCurrentXPos(int currentXPos)
  {
    this.currentXPos = currentXPos;
  }

  public int getCurrentYPos()
  {
    return currentYPos;
  }

  public void setCurrentYPos(int currentYPos)
  {
    this.currentYPos = currentYPos;
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
