package GAME;

import gui.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class CL extends JDialog {

    private final String forceText = "Force:";
    boolean isFirstPress = true;
    long timeOfstart;

    public CL(final JFrame f, Team[] teams) {
        super(f, "���? ���? �����?");
        //
        this.setSize(1280, 760);
        this.setModal(true);
        setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLabel forceLabel = new JLabel(forceText);
        forceLabel.setFont(new Font(forceLabel.getFont().getName(), Font.PLAIN, 25));
        //idLabel.setHorizontalAlignment(JTextField.LEFT);
        TablePanel leftPanel = new TablePanel();
        leftPanel.setBounds(0,0,640,740);
        final MyCrc P = new MyCrc();
        P.setBounds(0,0,640,740);
        final JButton B1 = new JButton("START");

        B1.setVisible(true);
        P.setAfterArrowChose(new Runnable() {
            @Override
            public void run() {
                new Timer(1500, new Runnable() {
                    @Override
                    public void run() {
                        P.add(new QuestionPanel());
                        setVisible(false);
                        new QuestionScreen(f).setVisible(true);
                    }
                });
            }
        });
        B1.addActionListener(new ActionListener() {
                                 public void actionPerformed(ActionEvent e) {
                                     if (isFirstPress) {
                                         //поллучить время записать для использования в тстарте
                                         timeOfstart = System.currentTimeMillis();
                                         //TODO: отобразить на экране
                                         startDisplaingTime(P, forceLabel, timeOfstart);
                                     } else {
                                         //вычислить сколько времени прошло с момента первого нажатия
                                         P.start(System.currentTimeMillis() - timeOfstart);
                                         B1.setVisible(false);

                                     }
                                     isFirstPress = !isFirstPress;//change value
                                 }
                             }
        );

        B1.setBounds(MyCrc.PICTURE_CENTER_X - 40, MyCrc.PICTURE_CENTER_Y - 20, 80, 40);
        forceLabel.setBounds(MyCrc.PICTURE_CENTER_X, MyCrc.PICTURE_CENTER_Y * 2 + 10, 150, 20);
        P.setLayout(null);
        P.add(B1);
        P.add(forceLabel);
        setLayout(null);
        this.add(P);
        this.add(leftPanel);
        this.add(new TeamTable(teams));



    }

    private void startDisplaingTime(final MyCrc P, final JLabel forceLabel, final long timeOfstart) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long timer = System.currentTimeMillis();

                while (!isFirstPress) {
                    if (System.currentTimeMillis() - timer > 33) {
                        int forceNow = (int) (System.currentTimeMillis() - timeOfstart);
                        forceLabel.setText(forceText + ": " + forceNow);
                        forceLabel.setFont(new Font(forceLabel.getFont().getName(), Font.PLAIN, 15));
                        //forceLabel.setBounds(forceLabel.getX(),forceLabel.getY(),forceLabel.getWidth()*2,forceLabel.getHeight());
                        if (System.currentTimeMillis() - timeOfstart > 5000) {
                            P.start(System.currentTimeMillis() - timeOfstart);
                            isFirstPress = !isFirstPress;
                        }
                    }
                }
            }
        }).start();

    }
}