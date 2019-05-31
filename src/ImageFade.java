import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ImageFade extends JPanel implements ActionListener
{
  private Image img;
  private Timer timer = new Timer(38, this);
  private float alphaImg = 0f;
  private boolean fadedIn;
  private double scale;
  private int yPos;

  public ImageFade(String fileName, double scale , int yPos)
  {
    img = new ImageIcon(fileName).getImage();
    fadedIn = false;
    this.scale = scale;
    this.yPos = yPos;
    timer.start();
  }

  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setComposite(
        AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaImg));//Adjusts opacity based on "alphaImg" value
    g2d.scale(scale, scale);//Scales image to fit into original window size
    g2d.drawImage(img, 10, yPos, null);//Draws image in middle of original window
  }

  public void actionPerformed(ActionEvent e)
  {
    if (!fadedIn)//Changes alphaImg for fading in
      alphaImg += 0.01f;
    else//Changes alphaImg for fading out
      alphaImg += -0.015f;
    if (alphaImg >= 1)
    {
      alphaImg = 1;
      fadedIn = true;//indicates that fading out sequences can be started
    }
    if (alphaImg <= 0 && fadedIn)
    {
      alphaImg = 0;
      timer.stop();
    }
    repaint();
  }

}
