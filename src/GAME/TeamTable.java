package GAME;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MisterY on 27.04.2016.
 */
public class TeamTable extends JPanel {
    TeamTable(Team[] teams)
    {
        this.setVisible(true);
        this.setSize(600, 720);
        this.setLocation(650, 0);

        JLabel[] team1labels = new JLabel[8];
        JLabel[] team2labels = new JLabel[8];


        team1labels[0] = new JLabel("Team " + 1 + " name: " + teams[0].getTeamName());
        team2labels[0] = new JLabel("Team " + 2 + " name: " + teams[1].getTeamName());
        team1labels[1] = new JLabel("Score:");
        team2labels[1] = new JLabel("Score:");
        team1labels[2] = new JLabel("Question 1:");
        team2labels[2] = new JLabel("Question 1:");
        team1labels[3] = new JLabel("Question 2:");
        team2labels[3] = new JLabel("Question 2:");
        team1labels[4] = new JLabel("Question 3:");
        team2labels[4] = new JLabel("Question 3:");
        team1labels[5] = new JLabel("Question 4:");
        team2labels[5] = new JLabel("Question 4:");
        team1labels[6] = new JLabel("Question 5:");
        team2labels[6] = new JLabel("Question 5:");
        team1labels[7] = new JLabel("Question 6:");
        team2labels[7] = new JLabel("Question 6:");
        setLayout(new GridLayout(8,2));
        for (int i = 0; i < team1labels.length; i++) {
            team1labels[i].setHorizontalAlignment(JTextField.CENTER);
            team1labels[i].setHorizontalAlignment(JTextField.CENTER);
            add(team1labels[i]);
            add(team2labels[i]);
        }

    }

}
