package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu {
public static void main (String [] args) {
Reader r = new Reader("???? ???? ??????");
r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
r.setSize(1280, 720);
r.setVisible(true);
}
public static void main3(String[] args) {
	JFrame f = new JFrame();
	try {
		f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Untitled-1.jpg")))));
		f.setSize(1280, 720);
	} catch (IOException e) {
		e.printStackTrace();
	}
	f.pack();
	f.setVisible(true);
}
}

