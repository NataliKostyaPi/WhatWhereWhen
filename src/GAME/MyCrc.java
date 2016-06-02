package GAME;

import gui.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MyCrc extends MyPanel implements ActionListener {
    final private int na = 5;
    public static final int PICTURE_CENTER_X = 320;
    public static final int PICTURE_CENTER_Y = 310;
    public float speed = 0;
    private float pointA = 0;
    boolean isArrowSpinning = false;
    //private float cs = (float) (360.0 / 13.0);
    private Timer T;
    private AfterArrowChoseListener afterArrowChose;

    public void setAfterArrowChose(AfterArrowChoseListener afterArrowChose) {
        this.afterArrowChose = afterArrowChose;
    }

    public MyCrc() {
        super(null);
        this.setVisible(true);
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        T = new Timer(5, this);
    }


    public void start(long timeOfCharge) {
        speed = (float) (1 + timeOfCharge / 500);
        T.start();
        isArrowSpinning = true;

    }

    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    public void paintComponent(Graphics gt) {
        super.paintComponent(gt);
        Graphics2D g = (Graphics2D) gt;

        int x0 = PICTURE_CENTER_X;
        int y0 = PICTURE_CENTER_Y;
        int x1, y1, x2, y2;
        int r = 300;

        g.setStroke(new BasicStroke(10.0f));

        g.setColor(Color.RED);
        r = (r / 100) * 80;
        x1 = (int) Math.round(x0 + Math.cos((pointA) * Math.PI / 180) * r);
        y1 = (int) Math.round(y0 - Math.sin((pointA) * Math.PI / 180) * r);
        g.drawLine(x0, y0, x1, y1);

        r = (r / 100) * 95;
        x2 = (int) Math.round(x0 + Math.cos((pointA - na) * Math.PI / 180) * r);
        y2 = (int) Math.round(y0 - Math.sin((pointA - na) * Math.PI / 180) * r);
        g.drawLine(x1, y1, x2, y2);

        x2 = (int) Math.round(x0 + Math.cos((pointA + na) * Math.PI / 180) * r);
        y2 = (int) Math.round(y0 - Math.sin((pointA + na) * Math.PI / 180) * r);
        g.drawLine(x1, y1, x2, y2);

        pointA += speed;
        speed -= 0.005;

        if (speed <= 0) {
            speed = 0;
            if(isArrowSpinning == true) {
                afterArrowChose.onArrowFinish((int) (((pointA % 360) / 27.7) + 1));
                isArrowSpinning = false;
            }
            T.stop();
        }


    }

}

interface AfterArrowChoseListener{
public void onArrowFinish(int sectorChosen);
}

class MyTask extends TimerTask {
    public void run() {

    }
}
