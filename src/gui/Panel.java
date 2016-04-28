package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

public class Panel extends JPanel
{
	public Image bg= GUI.getImage("Data/FonQ.jpg");

	public void paintComponent(Graphics g)
	{
		Graphics2D k= (Graphics2D) g;
		k.drawImage(bg,0,0,1280, 720,null);
	}
	public void paint(Graphics g)
    {
		Color Color = new Color(0, 215, 255);
		g.setColor(Color);
		g.drawOval(200, 110, 60, 60);
    }
}
