package GAME;

import gui.GUI;
import gui.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by kostya on 17.05.2016.
 */

public class StatisticsScreen extends JFrame {

    public StatisticsScreen() {
        super("JScrollPane Demonstration");
        System.out.println(System.currentTimeMillis());
        setSize(1280, 760);
        setResizable(false);
        this.setVisible(true);
        TeamStatitic.setAllStatistics(DBHandler.getInstance().loadStatistics());
        init();

    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        GUI gui = new GUI(1280, 720);
    }

    public void init() {

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(15, 0));
        JPanel butPan = new JPanel();
        butPan.setLayout(null);
        final JButton buttonBack = new JButton("Назад");
        buttonBack.addActionListener(new ActionListener() {
                                         boolean isFirstClick = true;

                                         public void actionPerformed(ActionEvent e) {
                                             StatisticsScreen.this.setVisible(false);
                                             GUI gui = new GUI(1280, 720);
                                         }
                                     }
        );
        buttonBack.setBounds(getWidth()-150, 0, 100, 50);
        buttonBack.setBorder(null);
        butPan.add(buttonBack);
        p.add(butPan);

        p.add(new DataPanel(this));

        ArrayList<DataPanel> dataPanels = new ArrayList<DataPanel>();

        for (int i = TeamStatitic.getAllStatistics().size() - 1; i >= 0; i--) {
            DataPanel d = new DataPanel(this);

                d.setData(""+TeamStatitic.getAllStatistics().get(i).getDateString(),
                        TeamStatitic.getAllStatistics().get(i).getTeam(),
                        "" + TeamStatitic.getAllStatistics().get(i).getTeamScore(),
                        TeamStatitic.getAllStatistics().get(i).getBestPlayer(),
                        "" + TeamStatitic.getAllStatistics().get(i).getPlayerScore());

            p.add(d);
        }

        JScrollPane scrollpane;
        scrollpane = new JScrollPane(p, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        getContentPane().add(scrollpane, BorderLayout.CENTER);
    }


    class DataPanel extends MyPanel {
        int shownStatistic = 0;
        String date;
        String teamName;
        String teamScore;
        String bestPlayer;
        String bestPlayerScore;
        JLabel dateValue;
        JLabel teamNameValue;
        JLabel teamScoreValue;
        JLabel bestPlayerNameValue;
        JLabel bestPlayerScoreValue;

        public void setData(String date, String teamName, String teamScore, String bestPlayer, String bestPlayerScore) {

            this.date = date;
            this.teamName = teamName;
            this.teamScore = teamScore;
            this.bestPlayer = bestPlayer;
            this.bestPlayerScore = bestPlayerScore;
            refresh();
        }



        public void init(JFrame frame){
            setLayout(new GridLayout(0, 5));
            DBHandler.getInstance().loadStatistics();

            dateValue = new JLabel("Дата");
            dateValue.setHorizontalAlignment(JLabel.CENTER);
            dateValue.setBounds(0, 0, frame.getWidth() / 5, 100);
            dateValue.setFont(new Font(dateValue.getFont().getName(), Font.PLAIN, 30));

            teamNameValue = new JLabel("Название команды");
            teamNameValue.setHorizontalAlignment(JLabel.CENTER);
            teamNameValue.setBounds((frame.getWidth() / 5), 0, frame.getWidth() / 5, 100);
            teamNameValue.setFont(new Font(teamNameValue.getFont().getName(), Font.PLAIN, 30));

            teamScoreValue = new JLabel("Счет команды");
            teamScoreValue.setHorizontalAlignment(JLabel.CENTER);
            teamScoreValue.setBounds((frame.getWidth() / 5) * 2, 200, frame.getWidth() / 5, 100);
            teamScoreValue.setFont(new Font(teamNameValue.getFont().getName(), Font.PLAIN, 30));


            bestPlayerNameValue = new JLabel("Лучший игрок");
            bestPlayerNameValue.setHorizontalAlignment(JLabel.CENTER);
            bestPlayerNameValue.setBounds((frame.getWidth() / 5) * 3, 0, frame.getWidth() / 5, 100);
            bestPlayerNameValue.setFont(new Font(teamNameValue.getFont().getName(), Font.PLAIN, 30));

            bestPlayerScoreValue = new JLabel("Счет игрока");
            bestPlayerScoreValue.setHorizontalAlignment(JLabel.CENTER);
            bestPlayerScoreValue.setBounds((frame.getWidth() / 5) * 5, 0, frame.getWidth() / 5, 100);
            bestPlayerScoreValue.setFont(new Font(teamNameValue.getFont().getName(), Font.PLAIN, 30));
            bestPlayerScoreValue.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 60));

            add(dateValue);
            add(teamNameValue);
            add(teamScoreValue);
            add(bestPlayerNameValue);
            add(bestPlayerScoreValue);

        }

        public DataPanel(JFrame frame) {
            super("Data/FonSat.png");

            init(frame);

        }

        private void refresh() {
            dateValue.setText(date);
            teamNameValue.setText(teamName);
            teamScoreValue.setText(teamScore);
            bestPlayerNameValue.setText(bestPlayer);
            bestPlayerScoreValue.setText(bestPlayerScore);

        }
    }
}
