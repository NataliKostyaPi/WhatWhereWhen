package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class ImageTest
{
  public static void main(String[] args) {
    ImagePanel panel = new ImagePanel(
                 new ImageIcon("www.jpeg").getImage());
    JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
 
class ImagePanel extends JPanel {
 
  private Image img;
 
  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }
 
  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(
                img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }
 
  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }
}