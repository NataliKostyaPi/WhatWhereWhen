package gui;

import java.awt.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel
{
	public MyPanel(String path) {
		if(path == null) {
			bg = GUI.getImage("Data/11111.png");
			setOpaque(false);
		}else {
			bg = GUI.getImage(path);
		}
		setSize(new Dimension(1280,720));
		setLayout(null);
	}
	private Image bg;

	public void paintComponent(Graphics g)
	{
		Graphics2D k= (Graphics2D) g;
		k.drawImage(bg,0,0,1280, 720,null);

	}
}
