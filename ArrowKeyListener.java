import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowKeyListener implements KeyListener
{

    public void keyTyped(KeyEvent e)
    {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            System.out.println("Right typed.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            System.out.println("Left typed.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            System.out.println("Up typed.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            System.out.println("Down typed.");
        }
        else{
            System.out.println("Key typed: " + e.getKeyChar());
        }
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            System.out.println("Right pressed.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            System.out.println("Left pressed.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            System.out.println("Up pressed.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            System.out.println("Down pressed.");
        }
        else{
            System.out.println("Key pressed: " + e.getKeyChar());
        }
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            System.out.println("Right released.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
            System.out.println("Left released.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_UP ) {
            System.out.println("Up released.");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
            System.out.println("Down released.");
        }
        else{
            System.out.println("Key released: " + e.getKeyChar());
        }
    }
}