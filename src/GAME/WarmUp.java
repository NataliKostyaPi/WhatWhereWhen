package GAME;

import gui.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kostya on 17.05.2016.
 */
public class WarmUp extends Screen{
    int time = 300;
    boolean isTimerStoped = true;
    Timer t1;
    public String getTime() {
        if(time%60 > 10)
        {return "" + (int)(time/60) +":" + time%60;}
        else
        {return "" + (int)(time/60) +":0" + time%60;}

    }

    public WarmUp(JFrame f) {
        super(f, "Data/FonWords.jpg", "Разминка");

    }

    @Override
    protected void setGui(JFrame frame, JPanel jpanel) {
        super.setGui(frame, jpanel);
        time = 300;
        isTimerStoped = true;

        String[] words = DBHandler.getInstance().loadWarmup();
        String randomWord = words[ (int)(Math.random() * words.length)];

        System.out.println("" + time);
        System.out.println("" + getTime());

        final JLabel timerLabel = new JLabel(getTime());
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(new Font(timerLabel.getFont().getName(), Font.PLAIN, 150));
        timerLabel.setForeground(Color.BLUE);
        timerLabel.setBounds(0, -80, 330, 300);
        timerLabel.setVisible(true);

        final JButton buttonStartTimer = new JButton("Запустить таймер");
        buttonStartTimer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                isTimerStoped = !isTimerStoped;
                if (isTimerStoped) {
                     buttonStartTimer.setText("Запустить таймер");
                } else {
                    buttonStartTimer.setText("Остановить таймер");
                }

                timerLabel.setText(getTime());
                t1 = new Timer(1000, new Runnable() {
                    @Override
                    public void run() {
                        if (time >= 1 && isTimerStoped == false) {
                            if (time < 21) {
                                timerLabel.setForeground(Color.RED);
                                if (time < 11) {
                                    SoundHandler.beepSound("Sounds/beep.wav");
                                }
                            } else {
                                timerLabel.setForeground(Color.BLACK);
                            }
                            timerLabel.setText(getTime());
                            time--;
                            t1 = new Timer(1000, this);
                        } else {
                            timerLabel.setText(getTime());
                            timerLabel.setForeground(Color.BLUE);
                            if (isTimerStoped == false) {
                                SoundHandler.beepSound("Sounds/beep-final.wav");
                                isTimerStoped = true;
                            }
                        }
                    }
                });


            }
        });
        buttonStartTimer.setBounds(330, 20, 200, 40);

        JButton buttonResetTimer = new JButton("Обнулить таймер");
        buttonResetTimer.addActionListener(new ActionListener() {
                                               public void actionPerformed(ActionEvent e) {
                                                   time = 300;
                                                   isTimerStoped = true;
                                                   timerLabel.setForeground(Color.BLUE);
                                                   timerLabel.setText(getTime());
                                                   buttonStartTimer.setText("Запустить таймер");
                                               }
                                           }
        );
        buttonResetTimer.setBounds(330, 80, 200, 40);
        jpanel.add(timerLabel);
        jpanel.add(buttonStartTimer);
        jpanel.add(buttonResetTimer);

        final JLabel word = new JLabel(randomWord);
        word.setHorizontalAlignment(JLabel.CENTER);
        word.setFont(new Font(word.getFont().getName(), Font.PLAIN, 100));
        word.setForeground(Color.BLUE);
        word.setBounds(0, 250, frame.getWidth(), 200);//300
        word.setVisible(true);
        jpanel.add(word);

        final JComboBox winnerText = new JComboBox();
        Team.setAllTeams(DBHandler.getInstance().loadTeams());
        for (int i = 0; i < Team.getAllTeams().size(); i++) {
            for (int j = 0; j < Team.getAllTeams().get(i).getPlayerNames().length; j++) {
                winnerText.addItem( Team.getAllTeams().get(i).getPlayerNames()[j] );
            }

        }

        //final JTextField  winnerText = new JTextField("Winner");
        winnerText.setBounds(frame.getWidth() - 240, 20, 200, 40);
        jpanel.add(winnerText);
        JButton saveWinner = new JButton("Сохранить победителя");
        saveWinner.addActionListener(new ActionListener() {
                                               public void actionPerformed(ActionEvent e) {
                                                   DBHandler.getInstance().insertWarmupWinner( ((JTextField)winnerText.getEditor().getEditorComponent()).getText() );
                                                   closeWindow();
                                               }
                                           }
        );
        saveWinner.setBounds(frame.getWidth() - 240, 80, 200, 40);
        jpanel.add(saveWinner);

    }
}
