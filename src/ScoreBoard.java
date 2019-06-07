import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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
  
  public void paintComponent(Graphics g, int x, int y)
  {
    g.setColor(Color.WHITE);
    g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
    g.drawString(String.valueOf(score),x,y);
  }
}
