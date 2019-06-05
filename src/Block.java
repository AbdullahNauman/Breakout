import java.awt.Color;
import java.awt.Graphics;

public class Block
{
  private int hardness, xPos, yPos;// Hardness variable to keep track of how
                                   // many times ball must hit before breaking.
                                   // X and Y position for drawing
  private int width, height;//Width and height of blocks

  public Block(int hardness, int xPos, int yPos)
  {
    this.hardness = hardness;
    this.xPos = xPos;
    this.yPos = yPos;
    width = 80;
    height = 40;
  }

  public int getHardness()
  {
    return hardness;
  }

  public void setHardness(int hardness)
  {
    this.hardness = hardness;
  }

  public int getxPos()
  {
    return xPos;
  }

  public void setxPos(int xPos)
  {
    this.xPos = xPos;
  }

  public int getyPos()
  {
    return yPos;
  }

  public void setyPos(int yPos)
  {
    this.yPos = yPos;
  }
  
  private Color getColor()
  {
    if(hardness ==0)
      return new Color(0f,0f,0f,0f);
    if(hardness == 1)
      return Color.YELLOW;
    else if(hardness == 2)
      return Color.GREEN;
    else if(hardness == 3)
      return Color.ORANGE;
    else if(hardness == 4)
      return Color.RED;
    return null;
  }
  public void paintComponent(Graphics g)
  {
    g.setColor(getColor());
    g.fillRect(xPos, yPos, width, height);
  }
}
