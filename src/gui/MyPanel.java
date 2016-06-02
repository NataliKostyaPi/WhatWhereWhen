package gui;

import java.awt.*;
import java.io.File;

import javax.swing.JPanel;

public class MyPanel extends JPanel
{
	int sizeX = 1280;
	int sizeY = 720;
	public MyPanel(String path) {
		if(path == null) {
			bg = GUI.getImage("Data/11111.png");
			setOpaque(false);
		}else {
			bg = GUI.getImage(path);
		}
		setSize(new Dimension(sizeX,sizeY));
		setLayout(null);
	}
	private Image bg;

	@Override
	public void setBounds(int x, int y, int width, int height) {
		sizeX = width;
		sizeY = height;
		super.setBounds(x, y, width, height);
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D k= (Graphics2D) g;
		k.drawImage(bg,0,0,sizeX, sizeY,null);

	}
	public void changeBackground(String path){
		bg = GUI.getImage(path);

	}
	public void changeBackground(File file){
		bg = GUI.getImage(file);

	}
}
