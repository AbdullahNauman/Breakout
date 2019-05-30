import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main
{

    public static void main(String[] args) 
    {

        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.addKeyListener(new ArrowKeyListener());

        frame.add(panel);

        frame.setSize(500, 500);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}
