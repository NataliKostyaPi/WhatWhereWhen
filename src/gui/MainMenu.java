package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu {

    public static void main(String[] args) {
    	
    	
        final JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1280, 720));
        final JToolBar toolBar = new JToolBar();

        //Create the popup menu.
        final JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("Option 1") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Option 1 selected");
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Option 2") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Option 2 selected");
            }
        }));

        final JButton button = new JButton("Options");
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        toolBar.add(button);

        frame.getContentPane().add(toolBar, BorderLayout.NORTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main2(String[] args) {
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