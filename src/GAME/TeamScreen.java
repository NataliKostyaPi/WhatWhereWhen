package GAME;

import gui.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MisterY on 27.04.2016.
 */
public class TeamScreen extends Screen {
    int height = 500;
    int teamNo;

    public TeamScreen(final JFrame f) {
        super(f, "Data/Fon1111.jpg", "Что? Где? Зубы лишние тварь?");


    }

    GeatherDataFromPanel[] geatherData;

    @Override
    protected void setGui(final JFrame frame, final JPanel jp) {


        this.setModal(true);
        geatherData = new GeatherDataFromPanel[2];
        JPanel leftPanel = preparePanel(1);
        JPanel rightPanel = preparePanel(2);
        JButton B1 = new JButton("START");
        B1.setBounds(getSizeX() / 3, getSizeY() - 90, getSizeX() / 3, 50);
        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO write in DATABASE
                Team team1 = geatherData[0].geatherTeam();
                Team team2 = geatherData[1].geatherTeam();
                printTeam(team1);
                printTeam(team2);
                DBHandler.getInstance().insert(team1);
                DBHandler.getInstance().insert(team2);
                dispose();
                CL cl = new CL(frame, new Team[]{team1, team2});
                cl.setVisible(true);
            }
        });


        JPanel gridPanel = new MyPanel(null);
        gridPanel.setLayout(null);

        leftPanel.setBounds(0, 0, getSizeX() / 2 - 75, getSizeY() - 100);
        rightPanel.setBounds(getSizeX() / 2 - 75, 0, getSizeX() / 2 - 75, getSizeY() - 100);
        gridPanel.setBounds(0, 0, getSizeX(), getSizeY() - 100);
        gridPanel.add(leftPanel);
        gridPanel.add(rightPanel);

        jp.setLayout(null);
        jp.add(gridPanel);
        jp.add(B1);

    }

    private void printTeam(Team team)
    {
        System.out.println(team.getTeamName());
        for (int i = 0; i < team.getPlayerNames().length; i++) {
            System.out.println(team.getPlayerNames()[i]);
        }
    }
    private JPanel preparePanel(int teamNo)
    {

        JPanel jp = new MyPanel(null);
        JLabel[] labels = new JLabel[7];
        final JTextField[] fields = new JTextField[7];
        labels[0] = new JLabel("Team " + teamNo + " name:");
        fields[0] = new JTextField();
        labels[1] = new JLabel("Player 1:");
        fields[1] = new JTextField();
        labels[2] = new JLabel("Player 2:");
        fields[2] = new JTextField();
        labels[3] = new JLabel("Player 3:");
        fields[3] = new JTextField();
        labels[4] = new JLabel("Player 4:");
        fields[4] = new JTextField();
        labels[5] = new JLabel("Player 5:");
        fields[5] = new JTextField();
        labels[6] = new JLabel("Player 6:");
        fields[6] = new JTextField();
        jp.setLayout(null);
        jp.setVisible(true);
        jp.setSize(250, height);
        int elementHeight = 35;
        for (int i = 0; i < labels.length; i++)
        {
            labels[i].setBounds(150, 20 + (10 * i) +  (i * elementHeight * 2), 415, elementHeight);
            labels[i].setHorizontalAlignment(JTextField.CENTER);
            jp.add(labels[i]);

        }
        for (int i = 0; i < labels.length; i++)
        {

            fields[i].setBounds(150, 20+elementHeight + (10 * i) +  (i * elementHeight * 2), 415, elementHeight);
            fields[i].setHorizontalAlignment(JTextField.CENTER);

            jp.add(fields[i]);
        }


        geatherData[teamNo-1] = new GeatherDataFromPanel() {
                @Override
                public Team geatherTeam() {
                    String[] teamPlayerNames = new String[fields.length];
              for (int i = 1; i < fields.length; i++) {
                  teamPlayerNames[i] = fields[i].getText();
              }
                return new Team(fields[0].getText(), teamPlayerNames);
                }};


        return jp;
    }



    private interface GeatherDataFromPanel{
        public Team geatherTeam();
    }
}

