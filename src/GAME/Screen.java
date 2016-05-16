package GAME;

import gui.MyPanel;

import javax.swing.*;

/**
 * Created by MisterY on 27.04.2016.
 */
public class Screen extends JDialog {
    private int sizeX = 1280;
    private int sizeY = 720;
    JPanel jp = new JPanel();


    public Screen(JFrame f) {
        super(f);
        this.setSize(f.getSize());
        sizeX = f.getSize().width;
        sizeY = f.getSize().height;
        this.setModal(true);
        setResizable(false);
        JPanel jp = new JPanel();
        setGui(f, jp);
        this.add(jp);
        this.setVisible(true);
    }

    public Screen(JFrame f, String screenTitle) {
        super(f, screenTitle);
        this.setSize(f.getSize());
        sizeX = f.getSize().width;
        sizeY = f.getSize().height;
        this.setModal(true);
        setResizable(false);
        JPanel jp = new JPanel();
        setGui(f, jp);
        this.add(jp);
        this.setVisible(true);
    }
    public Screen(JFrame f, String backgrundImage, String screenTitle) {
        super(f);
        this.setSize(f.getSize());
        sizeX = f.getSize().width;
        sizeY = f.getSize().height;
        this.setModal(true);
        setResizable(false);
        JPanel jp = new MyPanel(backgrundImage);
        setGui(f, jp);
        this.add(jp);
        this.setVisible(true);
    }
    public Screen(JFrame f, Object[] obj, String backgrundImage, String screenTitle) {
        super(f);
        this.setSize(f.getSize());
        sizeX = f.getSize().width;
        sizeY = f.getSize().height;
        this.setModal(true);
        setResizable(false);
        JPanel jp = new MyPanel(backgrundImage);
        setGui(f, jp, obj);
        this.add(jp);
        this.setVisible(true);
    }

    protected void setGui(JFrame frame, JPanel jpanel) {}
    protected void setGui(JFrame frame, JPanel jpanel, Object[] obj) {}
    public void closeWindow() {
        setVisible(false);
        dispose();
    }
    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
