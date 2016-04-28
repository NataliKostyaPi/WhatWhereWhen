package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class Reader extends JFrame{
	public Reader(String s){
		super (s);
		setLayout(new FlowLayout());

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
