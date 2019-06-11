import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

public class ScoreBoard
{
  private int score;
  public ScoreBoard(int points)
  {
    score = points;
  }
  public ScoreBoard()
  {
    score = 0;
  }
  public int getScore()
  {
    return score;
  }
  public void setScore(int score)
  {
    this.score = score;
  }
  public void increaseScore(int score)
  {
    this.score+=score;
  }
  
  private void setupFont(Graphics g)
  {
    g.setColor(Color.WHITE);
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 60);//Temporarily set font to logical font in case of Exception error
    try
    {
      font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("SFSquareRoot-Bold.ttf"));
    }
    catch (FontFormatException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    font = font.deriveFont(75f);//Change font size
    g.setFont(font);
  }
  
  public void paintComponent(Graphics g, int x, int y)
  {
    setupFont(g);
    if(score>=100)//Checking for number of digits and adding preceding zeros accordingly
      g.drawString(String.valueOf(score),x,y);
    else if(score>=10)
      g.drawString("0"+String.valueOf(score),x,y);
    else
      g.drawString("00"+String.valueOf(score),x,y);//Draw current score
  }
  public void paintComponent(Graphics g, int x, int y,String precedingText)
  {
    setupFont(g);
    if(score>=100)//Checking for number of digits and adding preceding zeros accordingly
      g.drawString(precedingText+String.valueOf(score),x,y);
    else if(score>=10)
      g.drawString(precedingText+"0"+String.valueOf(score),x,y);
    else
      g.drawString(precedingText+"00"+String.valueOf(score),x,y);//Draw current score
  }
}
