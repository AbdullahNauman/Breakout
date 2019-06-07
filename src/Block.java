import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle
{
  private int hardness;// Hardness variable to keep track of how
                       // many times ball must hit before breaking.
  private int score;
  private boolean justBroken,pointsGiven;
  public static final int width = 35, height = 12;// Width and height of blocks

  public Block(int hardness, int xPos, int yPos)
  {
    super(xPos, yPos, width, height);
    this.hardness = hardness;
    score = convertScore(hardness);
    setJustBroken(false);
    setPointsGiven(false);
  }

  public Block(int hardness)
  {
    super(width, height);
    this.hardness = hardness;
  }

  public int getHardness()
  {
    return hardness;
  }

  public void setHardness(int hardness)
  {
    this.hardness = hardness;
  }

  public int breakBlock()
  {
    if (hardness > 0)
      hardness -= 1;
    return hardness;
  }

  private Color getColor()
  {
    if (hardness == 0)
      return new Color(0f, 0f, 0f, 0f);
    else if (hardness == 1)
      return Color.YELLOW;
    else if (hardness == 2)
      return Color.GREEN;
    else if (hardness == 3)
      return Color.ORANGE;
    else if (hardness == 4)
      return Color.RED;
    return null;
  }

  public void paintComponent(Graphics g)
  {
    g.setColor(getColor());
    g.fillRect((int) super.getLocation().getX(),
        (int) super.getLocation().getY(), width, height);
  }
  
  private int convertScore(int hardness)
  {
    return 2*hardness-1;
  }
  
  public int getScore()
  {
    return score;
  }

  public boolean isPointsGiven()
  {
    return pointsGiven;
  }

  public void setPointsGiven(boolean pointsGiven)
  {
    this.pointsGiven = pointsGiven;
  }

  public boolean isJustBroken()
  {
    return justBroken;
  }

  public void setJustBroken(boolean justBroken)
  {
    this.justBroken = justBroken;
  }
}
